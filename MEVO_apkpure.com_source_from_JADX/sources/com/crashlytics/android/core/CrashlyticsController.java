package com.crashlytics.android.core;

import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.core.LogFileManager.DirectoryProvider;
import com.crashlytics.android.core.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash.FatalException;
import io.fabric.sdk.android.services.common.Crash.LoggedException;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStore;
import io.fabric.sdk.android.services.settings.PromptSettingsData;
import io.fabric.sdk.android.services.settings.SessionSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CrashlyticsController {
    private static final int ANALYZER_VERSION = 1;
    static final FilenameFilter ANY_SESSION_FILENAME_FILTER = new C03634();
    private static final String COLLECT_CUSTOM_KEYS = "com.crashlytics.CollectCustomKeys";
    private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private static final String EVENT_TYPE_CRASH = "crash";
    private static final String EVENT_TYPE_LOGGED = "error";
    static final String FATAL_SESSION_DIR = "fatal-sessions";
    private static final String GENERATOR_FORMAT = "Crashlytics Android SDK/%s";
    private static final String[] INITIAL_SESSION_PART_TAGS = new String[]{SESSION_USER_TAG, SESSION_APP_TAG, SESSION_OS_TAG, SESSION_DEVICE_TAG};
    static final String INVALID_CLS_CACHE_DIR = "invalidClsFiles";
    static final Comparator<File> LARGEST_FILE_NAME_FIRST = new C03612();
    static final int MAX_INVALID_SESSIONS = 4;
    private static final int MAX_LOCAL_LOGGED_EXCEPTIONS = 64;
    static final int MAX_OPEN_SESSIONS = 8;
    static final int MAX_STACK_SIZE = 1024;
    static final String NONFATAL_SESSION_DIR = "nonfatal-sessions";
    static final int NUM_STACK_REPETITIONS_ALLOWED = 10;
    private static final Map<String, String> SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    static final String SESSION_APP_TAG = "SessionApp";
    static final String SESSION_BEGIN_TAG = "BeginSession";
    static final String SESSION_DEVICE_TAG = "SessionDevice";
    static final String SESSION_EVENT_MISSING_BINARY_IMGS_TAG = "SessionMissingBinaryImages";
    static final String SESSION_FATAL_TAG = "SessionCrash";
    static final FilenameFilter SESSION_FILE_FILTER = new C03601();
    private static final Pattern SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    private static final int SESSION_ID_LENGTH = 35;
    static final String SESSION_NON_FATAL_TAG = "SessionEvent";
    static final String SESSION_OS_TAG = "SessionOS";
    static final String SESSION_USER_TAG = "SessionUser";
    private static final boolean SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT = false;
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST = new C03623();
    private final AppData appData;
    private final CrashlyticsBackgroundWorker backgroundWorker;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    private final CrashlyticsCore crashlyticsCore;
    private final DevicePowerStateListener devicePowerStateListener;
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final FileStore fileStore;
    private final HandlingExceptionCheck handlingExceptionCheck;
    private final HttpRequestFactory httpRequestFactory;
    private final IdManager idManager;
    private final LogFileDirectoryProvider logFileDirectoryProvider;
    private final LogFileManager logFileManager;
    private final PreferenceManager preferenceManager;
    private final ReportFilesProvider reportFilesProvider;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;
    private final String unityVersion;

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$1 */
    static class C03601 implements FilenameFilter {
        C03601() {
        }

        public boolean accept(File file, String str) {
            return (str.length() != ClsFileOutputStream.SESSION_FILE_EXTENSION.length() + 35 || str.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION) == null) ? null : true;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$2 */
    static class C03612 implements Comparator<File> {
        C03612() {
        }

        public int compare(File file, File file2) {
            return file2.getName().compareTo(file.getName());
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$3 */
    static class C03623 implements Comparator<File> {
        C03623() {
        }

        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$4 */
    static class C03634 implements FilenameFilter {
        C03634() {
        }

        public boolean accept(File file, String str) {
            return CrashlyticsController.SESSION_FILE_PATTERN.matcher(str).matches();
        }
    }

    private static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            return (CrashlyticsController.SESSION_FILE_FILTER.accept(file, str) != null || CrashlyticsController.SESSION_FILE_PATTERN.matcher(str).matches() == null) ? null : true;
        }
    }

    static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String str) {
            this.string = str;
        }

        public boolean accept(File file, String str) {
            return (str.contains(this.string) == null || str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION) != null) ? null : true;
        }
    }

    static class InvalidPartFileFilter implements FilenameFilter {
        InvalidPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            if (ClsFileOutputStream.TEMP_FILENAME_FILTER.accept(file, str) == null) {
                if (str.contains(CrashlyticsController.SESSION_EVENT_MISSING_BINARY_IMGS_TAG) == null) {
                    return null;
                }
            }
            return true;
        }
    }

    private static final class SendReportRunnable implements Runnable {
        private final Context context;
        private final Report report;
        private final ReportUploader reportUploader;

        public SendReportRunnable(Context context, Report report, ReportUploader reportUploader) {
            this.context = context;
            this.report = report;
            this.reportUploader = reportUploader;
        }

        public void run() {
            if (CommonUtils.canTryConnection(this.context)) {
                Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Attempting to send crash report at time of crash...");
                this.reportUploader.forceUpload(this.report);
            }
        }
    }

    static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String str) {
            this.sessionId = str;
        }

        public boolean accept(File file, String str) {
            file = new StringBuilder();
            file.append(this.sessionId);
            file.append(ClsFileOutputStream.SESSION_FILE_EXTENSION);
            boolean z = false;
            if (str.equals(file.toString()) != null) {
                return false;
            }
            if (str.contains(this.sessionId) != null && str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION) == null) {
                z = true;
            }
            return z;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsController$5 */
    class C07335 implements CrashListener {
        C07335() {
        }

        public void onUncaughtException(Thread thread, Throwable th) {
            CrashlyticsController.this.handleUncaughtException(thread, th);
        }
    }

    private static final class LogFileDirectoryProvider implements DirectoryProvider {
        private static final String LOG_FILES_DIR = "log-files";
        private final FileStore rootFileStore;

        public LogFileDirectoryProvider(FileStore fileStore) {
            this.rootFileStore = fileStore;
        }

        public File getLogFileDir() {
            File file = new File(this.rootFileStore.getFilesDir(), LOG_FILES_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
    }

    private static final class PrivacyDialogCheck implements SendCheck {
        private final Kit kit;
        private final PreferenceManager preferenceManager;
        private final PromptSettingsData promptData;

        /* renamed from: com.crashlytics.android.core.CrashlyticsController$PrivacyDialogCheck$1 */
        class C07341 implements AlwaysSendCallback {
            C07341() {
            }

            public void sendUserReportsWithoutPrompting(boolean z) {
                PrivacyDialogCheck.this.preferenceManager.setShouldAlwaysSendReports(z);
            }
        }

        public PrivacyDialogCheck(Kit kit, PreferenceManager preferenceManager, PromptSettingsData promptSettingsData) {
            this.kit = kit;
            this.preferenceManager = preferenceManager;
            this.promptData = promptSettingsData;
        }

        public boolean canSendReports() {
            Activity currentActivity = this.kit.getFabric().getCurrentActivity();
            if (currentActivity != null) {
                if (!currentActivity.isFinishing()) {
                    final CrashPromptDialog create = CrashPromptDialog.create(currentActivity, this.promptData, new C07341());
                    currentActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            create.show();
                        }
                    });
                    Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Waiting for user opt-in.");
                    create.await();
                    return create.getOptIn();
                }
            }
            return true;
        }
    }

    private final class ReportUploaderFilesProvider implements ReportFilesProvider {
        private ReportUploaderFilesProvider() {
        }

        public File[] getCompleteSessionFiles() {
            return CrashlyticsController.this.listCompleteSessionFiles();
        }

        public File[] getInvalidSessionFiles() {
            return CrashlyticsController.this.getInvalidFilesDir().listFiles();
        }
    }

    private final class ReportUploaderHandlingExceptionCheck implements HandlingExceptionCheck {
        private ReportUploaderHandlingExceptionCheck() {
        }

        public boolean isHandlingException() {
            return CrashlyticsController.this.isHandlingException();
        }
    }

    CrashlyticsController(CrashlyticsCore crashlyticsCore, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker, HttpRequestFactory httpRequestFactory, IdManager idManager, PreferenceManager preferenceManager, FileStore fileStore, AppData appData, UnityVersionProvider unityVersionProvider) {
        this.crashlyticsCore = crashlyticsCore;
        this.backgroundWorker = crashlyticsBackgroundWorker;
        this.httpRequestFactory = httpRequestFactory;
        this.idManager = idManager;
        this.preferenceManager = preferenceManager;
        this.fileStore = fileStore;
        this.appData = appData;
        this.unityVersion = unityVersionProvider.getUnityVersion();
        crashlyticsCore = crashlyticsCore.getContext();
        this.logFileDirectoryProvider = new LogFileDirectoryProvider(fileStore);
        this.logFileManager = new LogFileManager(crashlyticsCore, this.logFileDirectoryProvider);
        this.reportFilesProvider = new ReportUploaderFilesProvider();
        this.handlingExceptionCheck = new ReportUploaderHandlingExceptionCheck();
        this.devicePowerStateListener = new DevicePowerStateListener(crashlyticsCore);
        this.stackTraceTrimmingStrategy = new MiddleOutFallbackStrategy(1024, new StackTraceTrimmingStrategy[]{new RemoveRepeatsStrategy(10)});
    }

    void enableExceptionHandling(UncaughtExceptionHandler uncaughtExceptionHandler) {
        openSession();
        this.crashHandler = new CrashlyticsUncaughtExceptionHandler(new C07335(), uncaughtExceptionHandler);
        Thread.setDefaultUncaughtExceptionHandler(this.crashHandler);
    }

    synchronized void handleUncaughtException(final Thread thread, final Throwable th) {
        Logger logger = Fabric.getLogger();
        String str = CrashlyticsCore.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Crashlytics is handling uncaught exception \"");
        stringBuilder.append(th);
        stringBuilder.append("\" from thread ");
        stringBuilder.append(thread.getName());
        logger.mo2494d(str, stringBuilder.toString());
        this.devicePowerStateListener.dispose();
        final Date date = new Date();
        this.backgroundWorker.submitAndWait(new Callable<Void>() {
            public Void call() throws Exception {
                CrashlyticsController.this.crashlyticsCore.createCrashMarker();
                CrashlyticsController.this.writeFatal(date, thread, th);
                SettingsData awaitSettingsData = Settings.getInstance().awaitSettingsData();
                SessionSettingsData sessionSettingsData = awaitSettingsData != null ? awaitSettingsData.sessionData : null;
                CrashlyticsController.this.doCloseSessions(sessionSettingsData);
                CrashlyticsController.this.doOpenSession();
                if (sessionSettingsData != null) {
                    CrashlyticsController.this.trimSessionFiles(sessionSettingsData.maxCompleteSessionsCount);
                }
                if (!CrashlyticsController.this.shouldPromptUserBeforeSendingCrashReports(awaitSettingsData)) {
                    CrashlyticsController.this.sendSessionReports(awaitSettingsData);
                }
                return null;
            }
        });
    }

    void submitAllReports(float f, SettingsData settingsData) {
        if (settingsData == null) {
            Fabric.getLogger().mo2507w(CrashlyticsCore.TAG, "Could not send reports. Settings are not available.");
            return;
        }
        new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(settingsData.appData.reportsUrl), this.reportFilesProvider, this.handlingExceptionCheck).uploadReports(f, shouldPromptUserBeforeSendingCrashReports(settingsData) ? new PrivacyDialogCheck(this.crashlyticsCore, this.preferenceManager, settingsData.promptData) : new AlwaysSendCheck());
    }

    void writeToLog(final long j, final String str) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.logFileManager.writeToLog(j, str);
                }
                return null;
            }
        });
    }

    void writeNonFatalException(final Thread thread, final Throwable th) {
        final Date date = new Date();
        this.backgroundWorker.submit(new Runnable() {
            public void run() {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.doWriteNonFatal(date, thread, th);
                }
            }
        });
    }

    void cacheUserData(final String str, final String str2, final String str3) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeUserData(CrashlyticsController.this.getCurrentSessionId(), new UserMetaData(str, str2, str3));
                return null;
            }
        });
    }

    void cacheKeyData(final Map<String, String> map) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeKeyData(CrashlyticsController.this.getCurrentSessionId(), map);
                return null;
            }
        });
    }

    void openSession() {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                CrashlyticsController.this.doOpenSession();
                return null;
            }
        });
    }

    private String getCurrentSessionId() {
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        return listSortedSessionBeginFiles.length > 0 ? getSessionIdFromSessionFile(listSortedSessionBeginFiles[0]) : null;
    }

    private String getPreviousSessionId() {
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        return listSortedSessionBeginFiles.length > 1 ? getSessionIdFromSessionFile(listSortedSessionBeginFiles[1]) : null;
    }

    static String getSessionIdFromSessionFile(File file) {
        return file.getName().substring(0, 35);
    }

    boolean hasOpenSession() {
        return listSessionBeginFiles().length > 0;
    }

    boolean finalizeSessions(final SessionSettingsData sessionSettingsData) {
        return ((Boolean) this.backgroundWorker.submitAndWait(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                if (CrashlyticsController.this.isHandlingException()) {
                    Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Skipping session finalization because a crash has already occurred.");
                    return Boolean.FALSE;
                }
                Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Finalizing previously open sessions.");
                CrashlyticsController.this.doCloseSessions(sessionSettingsData, true);
                Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Closed all previously open sessions");
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    private void doOpenSession() throws Exception {
        Date date = new Date();
        String clsuuid = new CLSUUID(this.idManager).toString();
        Logger logger = Fabric.getLogger();
        String str = CrashlyticsCore.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Opening a new session with ID ");
        stringBuilder.append(clsuuid);
        logger.mo2494d(str, stringBuilder.toString());
        writeBeginSession(clsuuid, date);
        writeSessionApp(clsuuid);
        writeSessionOS(clsuuid);
        writeSessionDevice(clsuuid);
        this.logFileManager.setCurrentSession(clsuuid);
    }

    void doCloseSessions(SessionSettingsData sessionSettingsData) throws Exception {
        doCloseSessions(sessionSettingsData, false);
    }

    private void doCloseSessions(SessionSettingsData sessionSettingsData, boolean z) throws Exception {
        trimOpenSessions(z + 8);
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (listSortedSessionBeginFiles.length <= z) {
            Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "No open sessions to be closed.");
            return;
        }
        writeSessionUser(getSessionIdFromSessionFile(listSortedSessionBeginFiles[z]));
        if (sessionSettingsData == null) {
            Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Unable to close session. Settings are not loaded.");
        } else {
            closeOpenSessions(listSortedSessionBeginFiles, z, sessionSettingsData.maxCustomExceptionEvents);
        }
    }

    private void closeOpenSessions(File[] fileArr, int i, int i2) {
        Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Closing open sessions.");
        while (i < fileArr.length) {
            File file = fileArr[i];
            String sessionIdFromSessionFile = getSessionIdFromSessionFile(file);
            Logger logger = Fabric.getLogger();
            String str = CrashlyticsCore.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Closing session: ");
            stringBuilder.append(sessionIdFromSessionFile);
            logger.mo2494d(str, stringBuilder.toString());
            writeSessionPartsToSessionFile(file, sessionIdFromSessionFile, i2);
            i++;
        }
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream clsFileOutputStream) {
        if (clsFileOutputStream != null) {
            try {
                clsFileOutputStream.closeInProgressStream();
            } catch (ClsFileOutputStream clsFileOutputStream2) {
                Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "Error closing session file stream in the presence of an exception", clsFileOutputStream2);
            }
        }
    }

    private void deleteSessionPartFilesFor(String str) {
        for (File delete : listSessionPartFilesFor(str)) {
            delete.delete();
        }
    }

    private File[] listSessionPartFilesFor(String str) {
        return listFilesMatching(new SessionPartFileFilter(str));
    }

    File[] listCompleteSessionFiles() {
        List linkedList = new LinkedList();
        Collections.addAll(linkedList, listFilesMatching(getFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(linkedList, listFilesMatching(getNonFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(linkedList, listFilesMatching(getFilesDir(), SESSION_FILE_FILTER));
        return (File[]) linkedList.toArray(new File[linkedList.size()]);
    }

    File[] listSessionBeginFiles() {
        return listFilesMatching(new FileNameContainsFilter(SESSION_BEGIN_TAG));
    }

    private File[] listSortedSessionBeginFiles() {
        File[] listSessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(listSessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        return listSessionBeginFiles;
    }

    private File[] listFilesMatching(FilenameFilter filenameFilter) {
        return listFilesMatching(getFilesDir(), filenameFilter);
    }

    private File[] listFilesMatching(File file, FilenameFilter filenameFilter) {
        return ensureFileArrayNotNull(file.listFiles(filenameFilter));
    }

    private File[] listFiles(File file) {
        return ensureFileArrayNotNull(file.listFiles());
    }

    private File[] ensureFileArrayNotNull(File[] fileArr) {
        return fileArr == null ? new File[null] : fileArr;
    }

    private void trimSessionEventFiles(String str, int i) {
        File filesDir = getFilesDir();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(SESSION_NON_FATAL_TAG);
        Utils.capFileCount(filesDir, new FileNameContainsFilter(stringBuilder.toString()), i, SMALLEST_FILE_NAME_FIRST);
    }

    void trimSessionFiles(int i) {
        i -= Utils.capFileCount(getFatalSessionFilesDir(), i, SMALLEST_FILE_NAME_FIRST);
        Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, i - Utils.capFileCount(getNonFatalSessionFilesDir(), i, SMALLEST_FILE_NAME_FIRST), SMALLEST_FILE_NAME_FIRST);
    }

    private void trimOpenSessions(int i) {
        Set hashSet = new HashSet();
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        i = Math.min(i, listSortedSessionBeginFiles.length);
        for (int i2 = 0; i2 < i; i2++) {
            hashSet.add(getSessionIdFromSessionFile(listSortedSessionBeginFiles[i2]));
        }
        this.logFileManager.discardOldLogFiles(hashSet);
        retainSessions(listFilesMatching(new AnySessionPartFileFilter()), hashSet);
    }

    private void retainSessions(File[] fileArr, Set<String> set) {
        int length = fileArr.length;
        int i = 0;
        while (i < length) {
            File file = fileArr[i];
            String name = file.getName();
            Matcher matcher = SESSION_FILE_PATTERN.matcher(name);
            if (matcher.matches()) {
                if (!set.contains(matcher.group(1))) {
                    Logger logger = Fabric.getLogger();
                    String str = CrashlyticsCore.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Trimming session file: ");
                    stringBuilder.append(name);
                    logger.mo2494d(str, stringBuilder.toString());
                    file.delete();
                }
                i++;
            } else {
                fileArr = Fabric.getLogger();
                set = CrashlyticsCore.TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Deleting unknown file: ");
                stringBuilder2.append(name);
                fileArr.mo2494d(set, stringBuilder2.toString());
                file.delete();
                return;
            }
        }
    }

    private File[] getTrimmedNonFatalFiles(String str, File[] fileArr, int i) {
        if (fileArr.length <= i) {
            return fileArr;
        }
        Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[]{Integer.valueOf(i)}));
        trimSessionEventFiles(str, i);
        i = new StringBuilder();
        i.append(str);
        i.append(SESSION_NON_FATAL_TAG);
        return listFilesMatching(new FileNameContainsFilter(i.toString()));
    }

    void cleanInvalidTempFiles() {
        this.backgroundWorker.submit(new Runnable() {
            public void run() {
                CrashlyticsController.this.doCleanInvalidTempFiles(CrashlyticsController.this.listFilesMatching(new InvalidPartFileFilter()));
            }
        });
    }

    void doCleanInvalidTempFiles(File[] fileArr) {
        final Set hashSet = new HashSet();
        for (File file : fileArr) {
            Logger logger = Fabric.getLogger();
            String str = CrashlyticsCore.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Found invalid session part file: ");
            stringBuilder.append(file);
            logger.mo2494d(str, stringBuilder.toString());
            hashSet.add(getSessionIdFromSessionFile(file));
        }
        if (hashSet.isEmpty() == null) {
            fileArr = getInvalidFilesDir();
            if (!fileArr.exists()) {
                fileArr.mkdir();
            }
            for (File file2 : listFilesMatching(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    if (str.length() < 35) {
                        return false;
                    }
                    return hashSet.contains(str.substring(0, 35));
                }
            })) {
                Logger logger2 = Fabric.getLogger();
                String str2 = CrashlyticsCore.TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Moving session file: ");
                stringBuilder2.append(file2);
                logger2.mo2494d(str2, stringBuilder2.toString());
                if (!file2.renameTo(new File(fileArr, file2.getName()))) {
                    logger2 = Fabric.getLogger();
                    str2 = CrashlyticsCore.TAG;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Could not move session file. Deleting ");
                    stringBuilder2.append(file2);
                    logger2.mo2494d(str2, stringBuilder2.toString());
                    file2.delete();
                }
            }
            trimInvalidSessionFiles();
        }
    }

    private void trimInvalidSessionFiles() {
        File invalidFilesDir = getInvalidFilesDir();
        if (invalidFilesDir.exists()) {
            File[] listFilesMatching = listFilesMatching(invalidFilesDir, new InvalidPartFileFilter());
            Arrays.sort(listFilesMatching, Collections.reverseOrder());
            Set hashSet = new HashSet();
            for (int i = 0; i < listFilesMatching.length && hashSet.size() < 4; i++) {
                hashSet.add(getSessionIdFromSessionFile(listFilesMatching[i]));
            }
            retainSessions(listFiles(invalidFilesDir), hashSet);
        }
    }

    private void writeFatal(Date date, Thread thread, Throwable th) {
        Flushable flushable = null;
        Closeable clsFileOutputStream;
        try {
            String currentSessionId = getCurrentSessionId();
            if (currentSessionId == null) {
                Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "Tried to write a fatal exception while no session was open.", null);
                CommonUtils.flushOrLog(null, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(null, "Failed to close fatal exception file output stream.");
                return;
            }
            recordFatalExceptionAnswersEvent(currentSessionId, th.getClass().getName());
            File filesDir = getFilesDir();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(currentSessionId);
            stringBuilder.append(SESSION_FATAL_TAG);
            clsFileOutputStream = new ClsFileOutputStream(filesDir, stringBuilder.toString());
            try {
                Flushable newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                try {
                    writeSessionEvent(newInstance, date, thread, th, "crash", true);
                    CommonUtils.flushOrLog(newInstance, "Failed to flush to session begin file.");
                } catch (Exception e) {
                    date = e;
                    flushable = newInstance;
                    try {
                        Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred in the fatal exception logger", date);
                        CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    } catch (Throwable th2) {
                        date = th2;
                        CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                        throw date;
                    }
                } catch (Throwable th3) {
                    date = th3;
                    flushable = newInstance;
                    CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    throw date;
                }
            } catch (Exception e2) {
                date = e2;
                Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred in the fatal exception logger", date);
                CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            }
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Exception e3) {
            date = e3;
            clsFileOutputStream = null;
            Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred in the fatal exception logger", date);
            CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Throwable th4) {
            date = th4;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            throw date;
        }
    }

    void writeExternalCrashEvent(final SessionEventData sessionEventData) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.doWriteExternalCrashEvent(sessionEventData);
                }
                return null;
            }
        });
    }

    private void doWriteExternalCrashEvent(SessionEventData sessionEventData) throws IOException {
        Closeable clsFileOutputStream;
        Flushable flushable = null;
        try {
            String previousSessionId = getPreviousSessionId();
            if (previousSessionId == null) {
                Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "Tried to write a native crash while no session was open.", null);
                CommonUtils.flushOrLog(null, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(null, "Failed to close fatal exception file output stream.");
                return;
            }
            r4 = new Object[2];
            int i = 0;
            r4[0] = sessionEventData.signal.code;
            r4[1] = sessionEventData.signal.name;
            recordFatalExceptionAnswersEvent(previousSessionId, String.format(Locale.US, "<native-crash [%s (%s)]>", r4));
            if (sessionEventData.binaryImages != null && sessionEventData.binaryImages.length > 0) {
                i = 1;
            }
            String str = i != 0 ? SESSION_FATAL_TAG : SESSION_EVENT_MISSING_BINARY_IMGS_TAG;
            File filesDir = getFilesDir();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(previousSessionId);
            stringBuilder.append(str);
            clsFileOutputStream = new ClsFileOutputStream(filesDir, stringBuilder.toString());
            try {
                Flushable newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                try {
                    NativeCrashWriter.writeNativeCrash(sessionEventData, new LogFileManager(this.crashlyticsCore.getContext(), this.logFileDirectoryProvider, previousSessionId), new MetaDataStore(getFilesDir()).readKeyData(previousSessionId), newInstance);
                    CommonUtils.flushOrLog(newInstance, "Failed to flush to session begin file.");
                } catch (Exception e) {
                    sessionEventData = e;
                    flushable = newInstance;
                    try {
                        Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred in the native crash logger", sessionEventData);
                        CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    } catch (Throwable th) {
                        sessionEventData = th;
                        CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                        throw sessionEventData;
                    }
                } catch (Throwable th2) {
                    sessionEventData = th2;
                    flushable = newInstance;
                    CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    throw sessionEventData;
                }
            } catch (Exception e2) {
                sessionEventData = e2;
                Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred in the native crash logger", sessionEventData);
                CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            }
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Exception e3) {
            sessionEventData = e3;
            clsFileOutputStream = null;
            Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred in the native crash logger", sessionEventData);
            CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Throwable th3) {
            sessionEventData = th3;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            throw sessionEventData;
        }
    }

    private void doWriteNonFatal(Date date, Thread thread, Throwable th) {
        Closeable clsFileOutputStream;
        String currentSessionId = getCurrentSessionId();
        Flushable flushable = null;
        if (currentSessionId == null) {
            Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "Tried to write a non-fatal exception while no session was open.", null);
            return;
        }
        recordLoggedExceptionAnswersEvent(currentSessionId, th.getClass().getName());
        try {
            Logger logger = Fabric.getLogger();
            String str = CrashlyticsCore.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Crashlytics is logging non-fatal exception \"");
            stringBuilder.append(th);
            stringBuilder.append("\" from thread ");
            stringBuilder.append(thread.getName());
            logger.mo2494d(str, stringBuilder.toString());
            String padWithZerosToMaxIntWidth = CommonUtils.padWithZerosToMaxIntWidth(this.eventCounter.getAndIncrement());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(currentSessionId);
            stringBuilder2.append(SESSION_NON_FATAL_TAG);
            stringBuilder2.append(padWithZerosToMaxIntWidth);
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), stringBuilder2.toString());
            try {
                Flushable newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                try {
                    writeSessionEvent(newInstance, date, thread, th, EVENT_TYPE_LOGGED, false);
                    CommonUtils.flushOrLog(newInstance, "Failed to flush to non-fatal file.");
                } catch (Exception e) {
                    date = e;
                    flushable = newInstance;
                    try {
                        Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred in the non-fatal exception logger", date);
                        CommonUtils.flushOrLog(flushable, "Failed to flush to non-fatal file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                        trimSessionEventFiles(currentSessionId, 64);
                    } catch (Throwable th2) {
                        date = th2;
                        CommonUtils.flushOrLog(flushable, "Failed to flush to non-fatal file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                        throw date;
                    }
                } catch (Throwable th3) {
                    date = th3;
                    flushable = newInstance;
                    CommonUtils.flushOrLog(flushable, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                    throw date;
                }
            } catch (Exception e2) {
                date = e2;
                Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred in the non-fatal exception logger", date);
                CommonUtils.flushOrLog(flushable, "Failed to flush to non-fatal file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                trimSessionEventFiles(currentSessionId, 64);
            }
        } catch (Exception e3) {
            date = e3;
            clsFileOutputStream = null;
            Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred in the non-fatal exception logger", date);
            CommonUtils.flushOrLog(flushable, "Failed to flush to non-fatal file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
            trimSessionEventFiles(currentSessionId, 64);
        } catch (Throwable th4) {
            date = th4;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(flushable, "Failed to flush to non-fatal file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
            throw date;
        }
        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
        try {
            trimSessionEventFiles(currentSessionId, 64);
        } catch (Date date2) {
            Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred when trimming non-fatal files.", date2);
        }
    }

    private void writeBeginSession(String str, Date date) throws Exception {
        Flushable newInstance;
        Flushable flushable = null;
        Closeable clsFileOutputStream;
        try {
            File filesDir = getFilesDir();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(SESSION_BEGIN_TAG);
            clsFileOutputStream = new ClsFileOutputStream(filesDir, stringBuilder.toString());
            try {
                newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
            } catch (Throwable th) {
                str = th;
                CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close begin session file.");
                throw str;
            }
            try {
                SessionProtobufHelper.writeBeginSession(newInstance, str, String.format(Locale.US, GENERATOR_FORMAT, new Object[]{this.crashlyticsCore.getVersion()}), date.getTime() / 1000);
                CommonUtils.flushOrLog(newInstance, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close begin session file.");
            } catch (Throwable th2) {
                str = th2;
                flushable = newInstance;
                CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close begin session file.");
                throw str;
            }
        } catch (Throwable th3) {
            str = th3;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(flushable, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close begin session file.");
            throw str;
        }
    }

    private void writeSessionApp(String str) throws Exception {
        Throwable th;
        Closeable clsFileOutputStream;
        try {
            File filesDir = getFilesDir();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(SESSION_APP_TAG);
            clsFileOutputStream = new ClsFileOutputStream(filesDir, stringBuilder.toString());
            try {
                str = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
            } catch (String str2) {
                th = str2;
                str2 = null;
                CommonUtils.flushOrLog(str2, "Failed to flush to session app file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session app file.");
                throw th;
            }
            try {
                String str3 = str2;
                SessionProtobufHelper.writeSessionApp(str3, this.idManager.getAppIdentifier(), this.appData.apiKey, this.appData.versionCode, this.appData.versionName, this.idManager.getAppInstallIdentifier(), DeliveryMechanism.determineFrom(this.appData.installerPackageName).getId(), this.unityVersion);
                CommonUtils.flushOrLog(str2, "Failed to flush to session app file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session app file.");
            } catch (Throwable th2) {
                th = th2;
                CommonUtils.flushOrLog(str2, "Failed to flush to session app file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session app file.");
                throw th;
            }
        } catch (String str22) {
            clsFileOutputStream = null;
            th = str22;
            str22 = clsFileOutputStream;
            CommonUtils.flushOrLog(str22, "Failed to flush to session app file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session app file.");
            throw th;
        }
    }

    private void writeSessionOS(String str) throws Exception {
        Closeable clsFileOutputStream;
        Flushable flushable = null;
        try {
            File filesDir = getFilesDir();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(SESSION_OS_TAG);
            clsFileOutputStream = new ClsFileOutputStream(filesDir, stringBuilder.toString());
            try {
                str = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
            } catch (Throwable th) {
                str = th;
                CommonUtils.flushOrLog(flushable, "Failed to flush to session OS file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session OS file.");
                throw str;
            }
            try {
                SessionProtobufHelper.writeSessionOS(str, CommonUtils.isRooted(this.crashlyticsCore.getContext()));
                CommonUtils.flushOrLog(str, "Failed to flush to session OS file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session OS file.");
            } catch (Throwable th2) {
                Throwable th3 = th2;
                flushable = str;
                str = th3;
                CommonUtils.flushOrLog(flushable, "Failed to flush to session OS file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session OS file.");
                throw str;
            }
        } catch (Throwable th4) {
            str = th4;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(flushable, "Failed to flush to session OS file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session OS file.");
            throw str;
        }
    }

    private void writeSessionDevice(String str) throws Exception {
        Flushable newInstance;
        Throwable th;
        Throwable th2;
        CrashlyticsController crashlyticsController = this;
        Closeable clsFileOutputStream;
        try {
            File filesDir = getFilesDir();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(SESSION_DEVICE_TAG);
            clsFileOutputStream = new ClsFileOutputStream(filesDir, stringBuilder.toString());
            try {
                newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
            } catch (Throwable th3) {
                th = th3;
                newInstance = null;
                th2 = th;
                CommonUtils.flushOrLog(newInstance, "Failed to flush session device info.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session device file.");
                throw th2;
            }
            try {
                Context context = crashlyticsController.crashlyticsCore.getContext();
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                Flushable flushable = newInstance;
                SessionProtobufHelper.writeSessionDevice(flushable, crashlyticsController.idManager.getDeviceUUID(), CommonUtils.getCpuArchitectureInt(), Build.MODEL, Runtime.getRuntime().availableProcessors(), CommonUtils.getTotalRamInBytes(), ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()), CommonUtils.isEmulator(context), crashlyticsController.idManager.getDeviceIdentifiers(), CommonUtils.getDeviceState(context), Build.MANUFACTURER, Build.PRODUCT);
                CommonUtils.flushOrLog(newInstance, "Failed to flush session device info.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session device file.");
            } catch (Throwable th4) {
                th = th4;
                th2 = th;
                CommonUtils.flushOrLog(newInstance, "Failed to flush session device info.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session device file.");
                throw th2;
            }
        } catch (Throwable th5) {
            th = th5;
            clsFileOutputStream = null;
            newInstance = clsFileOutputStream;
            th2 = th;
            CommonUtils.flushOrLog(newInstance, "Failed to flush session device info.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session device file.");
            throw th2;
        }
    }

    private void writeSessionUser(String str) throws Exception {
        Closeable clsFileOutputStream;
        Flushable flushable = null;
        try {
            Flushable newInstance;
            File filesDir = getFilesDir();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(SESSION_USER_TAG);
            clsFileOutputStream = new ClsFileOutputStream(filesDir, stringBuilder.toString());
            try {
                newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
            } catch (Throwable th) {
                str = th;
                CommonUtils.flushOrLog(flushable, "Failed to flush session user file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
                throw str;
            }
            try {
                str = getUserMetaData(str);
                if (str.isEmpty()) {
                    CommonUtils.flushOrLog(newInstance, "Failed to flush session user file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
                    return;
                }
                SessionProtobufHelper.writeSessionUser(newInstance, str.id, str.name, str.email);
                CommonUtils.flushOrLog(newInstance, "Failed to flush session user file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
            } catch (Throwable th2) {
                str = th2;
                flushable = newInstance;
                CommonUtils.flushOrLog(flushable, "Failed to flush session user file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
                throw str;
            }
        } catch (Throwable th3) {
            str = th3;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(flushable, "Failed to flush session user file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
            throw str;
        }
    }

    private void writeSessionEvent(CodedOutputStream codedOutputStream, Date date, Thread thread, Throwable th, String str, boolean z) throws Exception {
        boolean z2;
        Thread[] threadArr;
        TreeMap attributes;
        Map treeMap;
        TrimmedThrowableData trimmedThrowableData = new TrimmedThrowableData(th, this.stackTraceTrimmingStrategy);
        Context context = this.crashlyticsCore.getContext();
        long time = date.getTime() / 1000;
        Float batteryLevel = CommonUtils.getBatteryLevel(context);
        int batteryVelocity = CommonUtils.getBatteryVelocity(context, this.devicePowerStateListener.isPowerConnected());
        boolean proximitySensorEnabled = CommonUtils.getProximitySensorEnabled(context);
        int i = context.getResources().getConfiguration().orientation;
        long totalRamInBytes = CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(context);
        long calculateUsedDiskSpaceInBytes = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
        RunningAppProcessInfo appProcessInfo = CommonUtils.getAppProcessInfo(context.getPackageName(), context);
        List linkedList = new LinkedList();
        StackTraceElement[] stackTraceElementArr = trimmedThrowableData.stacktrace;
        String str2 = this.appData.buildId;
        String appIdentifier = this.idManager.getAppIdentifier();
        int i2 = 0;
        if (z) {
            Map allStackTraces = Thread.getAllStackTraces();
            Thread[] threadArr2 = new Thread[allStackTraces.size()];
            for (Entry entry : allStackTraces.entrySet()) {
                threadArr2[i2] = (Thread) entry.getKey();
                linkedList.add(r0.stackTraceTrimmingStrategy.getTrimmedStackTrace((StackTraceElement[]) entry.getValue()));
                i2++;
            }
            z2 = true;
            threadArr = threadArr2;
        } else {
            z2 = true;
            threadArr = new Thread[0];
        }
        if (CommonUtils.getBooleanResourceValue(context, COLLECT_CUSTOM_KEYS, z2)) {
            attributes = r0.crashlyticsCore.getAttributes();
            if (attributes != null && attributes.size() > z2) {
                treeMap = new TreeMap(attributes);
                SessionProtobufHelper.writeSessionEvent(codedOutputStream, time, str, trimmedThrowableData, thread, stackTraceElementArr, threadArr, linkedList, treeMap, r0.logFileManager, appProcessInfo, i, appIdentifier, str2, batteryLevel, batteryVelocity, proximitySensorEnabled, totalRamInBytes, calculateUsedDiskSpaceInBytes);
            }
        }
        attributes = new TreeMap();
        treeMap = attributes;
        SessionProtobufHelper.writeSessionEvent(codedOutputStream, time, str, trimmedThrowableData, thread, stackTraceElementArr, threadArr, linkedList, treeMap, r0.logFileManager, appProcessInfo, i, appIdentifier, str2, batteryLevel, batteryVelocity, proximitySensorEnabled, totalRamInBytes, calculateUsedDiskSpaceInBytes);
    }

    private void writeSessionPartsToSessionFile(File file, String str, int i) {
        StringBuilder stringBuilder;
        Logger logger = Fabric.getLogger();
        String str2 = CrashlyticsCore.TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Collecting session parts for ID ");
        stringBuilder2.append(str);
        logger.mo2494d(str2, stringBuilder2.toString());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str);
        stringBuilder3.append(SESSION_FATAL_TAG);
        File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(stringBuilder3.toString()));
        boolean z = listFilesMatching != null && listFilesMatching.length > 0;
        Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{str, Boolean.valueOf(z)}));
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append(str);
        stringBuilder4.append(SESSION_NON_FATAL_TAG);
        File[] listFilesMatching2 = listFilesMatching(new FileNameContainsFilter(stringBuilder4.toString()));
        boolean z2 = listFilesMatching2 != null && listFilesMatching2.length > 0;
        Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{str, Boolean.valueOf(z2)}));
        if (!z) {
            if (!z2) {
                file = Fabric.getLogger();
                i = CrashlyticsCore.TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("No events present for session ID ");
                stringBuilder.append(str);
                file.mo2494d(i, stringBuilder.toString());
                file = Fabric.getLogger();
                i = CrashlyticsCore.TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Removing session part files for ID ");
                stringBuilder.append(str);
                file.mo2494d(i, stringBuilder.toString());
                deleteSessionPartFilesFor(str);
            }
        }
        synthesizeSessionFile(file, str, getTrimmedNonFatalFiles(str, listFilesMatching2, i), z ? listFilesMatching[0] : null);
        file = Fabric.getLogger();
        i = CrashlyticsCore.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Removing session part files for ID ");
        stringBuilder.append(str);
        file.mo2494d(i, stringBuilder.toString());
        deleteSessionPartFilesFor(str);
    }

    private void synthesizeSessionFile(File file, String str, File[] fileArr, File file2) {
        StringBuilder stringBuilder;
        boolean z = file2 != null;
        File fatalSessionFilesDir = z ? getFatalSessionFilesDir() : getNonFatalSessionFilesDir();
        if (!fatalSessionFilesDir.exists()) {
            fatalSessionFilesDir.mkdirs();
        }
        Flushable flushable = null;
        Closeable clsFileOutputStream;
        Flushable newInstance;
        try {
            clsFileOutputStream = new ClsFileOutputStream(fatalSessionFilesDir, str);
            try {
                newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                try {
                    Logger logger = Fabric.getLogger();
                    String str2 = CrashlyticsCore.TAG;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Collecting SessionStart data for session ID ");
                    stringBuilder2.append(str);
                    logger.mo2494d(str2, stringBuilder2.toString());
                    writeToCosFromFile(newInstance, file);
                    newInstance.writeUInt64(4, new Date().getTime() / 1000);
                    newInstance.writeBool(5, z);
                    newInstance.writeUInt32(11, 1);
                    newInstance.writeEnum(12, 3);
                    writeInitialPartsTo(newInstance, str);
                    writeNonFatalEventsTo(newInstance, fileArr, str);
                    if (z) {
                        writeToCosFromFile(newInstance, file2);
                    }
                    CommonUtils.flushOrLog(newInstance, "Error flushing session file stream");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
                } catch (Exception e) {
                    file = e;
                    flushable = newInstance;
                    try {
                        fileArr = Fabric.getLogger();
                        file2 = CrashlyticsCore.TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to write session file for session ID: ");
                        stringBuilder.append(str);
                        fileArr.mo2497e(file2, stringBuilder.toString(), file);
                        CommonUtils.flushOrLog(flushable, "Error flushing session file stream");
                        closeWithoutRenamingOrLog(clsFileOutputStream);
                    } catch (Throwable th) {
                        file = th;
                        newInstance = flushable;
                        CommonUtils.flushOrLog(newInstance, "Error flushing session file stream");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
                        throw file;
                    }
                } catch (Throwable th2) {
                    file = th2;
                    CommonUtils.flushOrLog(newInstance, "Error flushing session file stream");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
                    throw file;
                }
            } catch (Exception e2) {
                file = e2;
                fileArr = Fabric.getLogger();
                file2 = CrashlyticsCore.TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to write session file for session ID: ");
                stringBuilder.append(str);
                fileArr.mo2497e(file2, stringBuilder.toString(), file);
                CommonUtils.flushOrLog(flushable, "Error flushing session file stream");
                closeWithoutRenamingOrLog(clsFileOutputStream);
            }
        } catch (Exception e3) {
            file = e3;
            clsFileOutputStream = null;
            fileArr = Fabric.getLogger();
            file2 = CrashlyticsCore.TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to write session file for session ID: ");
            stringBuilder.append(str);
            fileArr.mo2497e(file2, stringBuilder.toString(), file);
            CommonUtils.flushOrLog(flushable, "Error flushing session file stream");
            closeWithoutRenamingOrLog(clsFileOutputStream);
        } catch (Throwable th3) {
            file = th3;
            newInstance = null;
            clsFileOutputStream = newInstance;
            CommonUtils.flushOrLog(newInstance, "Error flushing session file stream");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
            throw file;
        }
    }

    private static void writeNonFatalEventsTo(CodedOutputStream codedOutputStream, File[] fileArr, String str) {
        Arrays.sort(fileArr, CommonUtils.FILE_MODIFIED_COMPARATOR);
        for (File name : fileArr) {
            try {
                Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{str, name.getName()}));
                writeToCosFromFile(codedOutputStream, name);
            } catch (Throwable e) {
                Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeInitialPartsTo(CodedOutputStream codedOutputStream, String str) throws IOException {
        for (String str2 : INITIAL_SESSION_PART_TAGS) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(stringBuilder.toString()));
            if (listFilesMatching.length == 0) {
                Logger logger = Fabric.getLogger();
                String str3 = CrashlyticsCore.TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Can't find ");
                stringBuilder2.append(str2);
                stringBuilder2.append(" data for session ID ");
                stringBuilder2.append(str);
                logger.mo2497e(str3, stringBuilder2.toString(), null);
            } else {
                Logger logger2 = Fabric.getLogger();
                String str4 = CrashlyticsCore.TAG;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Collecting ");
                stringBuilder3.append(str2);
                stringBuilder3.append(" data for session ID ");
                stringBuilder3.append(str);
                logger2.mo2494d(str4, stringBuilder3.toString());
                writeToCosFromFile(codedOutputStream, listFilesMatching[0]);
            }
        }
    }

    private static void writeToCosFromFile(CodedOutputStream codedOutputStream, File file) throws IOException {
        Closeable fileInputStream;
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    copyToCodedOutputStream(fileInputStream, codedOutputStream, (int) file.length());
                    CommonUtils.closeOrLog(fileInputStream, "Failed to close file input stream.");
                    return;
                } catch (Throwable th) {
                    codedOutputStream = th;
                    CommonUtils.closeOrLog(fileInputStream, "Failed to close file input stream.");
                    throw codedOutputStream;
                }
            } catch (Throwable th2) {
                codedOutputStream = th2;
                fileInputStream = null;
                CommonUtils.closeOrLog(fileInputStream, "Failed to close file input stream.");
                throw codedOutputStream;
            }
        }
        codedOutputStream = Fabric.getLogger();
        String str = CrashlyticsCore.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Tried to include a file that doesn't exist: ");
        stringBuilder.append(file.getName());
        codedOutputStream.mo2497e(str, stringBuilder.toString(), null);
    }

    private static void copyToCodedOutputStream(InputStream inputStream, CodedOutputStream codedOutputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < bArr.length) {
            int read = inputStream.read(bArr, i2, bArr.length - i2);
            if (read < 0) {
                break;
            }
            i2 += read;
        }
        codedOutputStream.writeRawBytes(bArr);
    }

    private UserMetaData getUserMetaData(String str) {
        if (isHandlingException()) {
            return new UserMetaData(this.crashlyticsCore.getUserIdentifier(), this.crashlyticsCore.getUserName(), this.crashlyticsCore.getUserEmail());
        }
        return new MetaDataStore(getFilesDir()).readUserData(str);
    }

    boolean isHandlingException() {
        return this.crashHandler != null && this.crashHandler.isHandlingException();
    }

    File getFilesDir() {
        return this.fileStore.getFilesDir();
    }

    File getFatalSessionFilesDir() {
        return new File(getFilesDir(), FATAL_SESSION_DIR);
    }

    File getNonFatalSessionFilesDir() {
        return new File(getFilesDir(), NONFATAL_SESSION_DIR);
    }

    File getInvalidFilesDir() {
        return new File(getFilesDir(), INVALID_CLS_CACHE_DIR);
    }

    private boolean shouldPromptUserBeforeSendingCrashReports(SettingsData settingsData) {
        boolean z = false;
        if (settingsData == null) {
            return false;
        }
        if (settingsData.featuresData.promptEnabled != null && this.preferenceManager.shouldAlwaysSendReports() == null) {
            z = true;
        }
        return z;
    }

    private CreateReportSpiCall getCreateReportSpiCall(String str) {
        return new DefaultCreateReportSpiCall(this.crashlyticsCore, CommonUtils.getStringsFileValue(this.crashlyticsCore.getContext(), CRASHLYTICS_API_ENDPOINT), str, this.httpRequestFactory);
    }

    private void sendSessionReports(SettingsData settingsData) {
        if (settingsData == null) {
            Fabric.getLogger().mo2507w(CrashlyticsCore.TAG, "Cannot send reports. Settings are unavailable.");
            return;
        }
        Context context = this.crashlyticsCore.getContext();
        ReportUploader reportUploader = new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(settingsData.appData.reportsUrl), this.reportFilesProvider, this.handlingExceptionCheck);
        for (File sessionReport : listCompleteSessionFiles()) {
            this.backgroundWorker.submit(new SendReportRunnable(context, new SessionReport(sessionReport, SEND_AT_CRASHTIME_HEADER), reportUploader));
        }
    }

    private static void recordLoggedExceptionAnswersEvent(String str, String str2) {
        Answers answers = (Answers) Fabric.getKit(Answers.class);
        if (answers == null) {
            Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Answers is not available");
        } else {
            answers.onException(new LoggedException(str, str2));
        }
    }

    private static void recordFatalExceptionAnswersEvent(String str, String str2) {
        Answers answers = (Answers) Fabric.getKit(Answers.class);
        if (answers == null) {
            Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Answers is not available");
        } else {
            answers.onException(new FatalException(str, str2));
        }
    }
}
