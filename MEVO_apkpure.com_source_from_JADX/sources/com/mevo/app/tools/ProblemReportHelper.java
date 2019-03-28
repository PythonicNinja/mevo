package com.mevo.app.tools;

import android.text.format.DateFormat;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mevo.app.App;
import com.mevo.app.Cfg;
import com.mevo.app.constants.Servers;
import com.mevo.app.data.model.MevoErrorReport;
import com.mevo.app.data.model.response.ResponseReportProblem;
import com.mevo.app.data.model.response.ResponseReportResult;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.UserRepository;
import com.mevo.app.modules.firebase_cloud_messaging.MyFirebaseMessagingService.PushData;
import com.mevo.app.presentation.main.report_problem.ReportProblemFragment;
import java.util.Date;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProblemReportHelper {
    public static final int ERROR_CONNECTION_BROKEN = 1;
    public static final int ERROR_NONE = -1;
    public static final int ERROR_SERVER = 0;
    private int firstErrorCode;
    private ProblemReportListener listener;
    private int nextbikeProblemId;

    public interface ProblemReportListener {
        void onImageSent(boolean z);

        void onProblemReportFailed(int i);

        void onProblemReported(int i);
    }

    /* renamed from: com.mevo.app.tools.ProblemReportHelper$2 */
    class C08392 implements Callback<ResponseReportResult> {
        public void onFailure(Call<ResponseReportResult> call, Throwable th) {
        }

        public void onResponse(Call<ResponseReportResult> call, Response<ResponseReportResult> response) {
        }

        C08392() {
        }
    }

    public ProblemReportHelper(ProblemReportListener problemReportListener) {
        this.listener = problemReportListener;
    }

    public void reportProblem(String str, String str2, Integer num, String str3, String str4, String str5) {
        this.firstErrorCode = -1;
        reportProblemToNextBike(str, str2, num, str3, str4, str5);
    }

    private void reportProblemToNextBike(String str, String str2, Integer num, String str3, String str4, String str5) {
        String charSequence = DateFormat.format("yyyy-MM-dd", new Date(System.currentTimeMillis())).toString();
        final String str6 = str;
        final String str7 = str2;
        final Integer num2 = num;
        final String str8 = str3;
        final String str9 = charSequence;
        final String str10 = str4;
        final String str11 = str5;
        Rest.getApiNextbike().sendErrorReport(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), str, str2, num, str3, charSequence, str4, str5).enqueue(new Callback<ResponseReportProblem>() {
            public void onResponse(Call<ResponseReportProblem> call, Response<ResponseReportProblem> response) {
                C08381 c08381 = this;
                if (response.body() == null || ((ResponseReportProblem) response.body()).errorReport == null) {
                    ProblemReportHelper problemReportHelper = ProblemReportHelper.this;
                    String str = str6;
                    String str2 = str7;
                    Integer num = num2;
                    String str3 = str8;
                    String str4 = str9;
                    String str5 = str10;
                    problemReportHelper.onReportProblemFinished(false, 0, str, str2, num, str3, str4, str5, str11, ProblemReportHelper.this.nextbikeProblemId);
                    return;
                }
                ProblemReportHelper.this.nextbikeProblemId = ((ResponseReportProblem) response.body()).errorReport.id;
                ProblemReportHelper.this.onReportProblemFinished(true, -1, str6, str7, num2, str8, str9, str10, str11, ProblemReportHelper.this.nextbikeProblemId);
            }

            public void onFailure(Call<ResponseReportProblem> call, Throwable th) {
                if (call.isCanceled() == null) {
                    ProblemReportHelper.this.onReportProblemFinished(false, 1, str6, str7, num2, str8, str9, str10, str11, ProblemReportHelper.this.nextbikeProblemId);
                }
            }
        });
    }

    private void onReportProblemFinished(boolean z, int i, String str, String str2, Integer num, String str3, String str4, String str5, String str6, int i2) {
        String str7 = str2;
        if (this.firstErrorCode == -1) {
            r0.firstErrorCode = i;
        }
        if (z) {
            if (str7 != null && str7.equals(ReportProblemFragment.reports)) {
                new UserRepository().updateBatteryReports();
            }
            int i3 = i2;
            Rest.getApiExtended().sendErrorReport(Servers.MEVO_REPORTS_KEY, new MevoErrorReport(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), UserManager.getUser().getPhoneNumber(), str, str7, num, str3, str4, str5, str6, i3, createPushUrl(i3))).enqueue(new C08392());
            if (r0.listener != null) {
                r0.listener.onProblemReported(r0.nextbikeProblemId);
            }
        } else if (r0.listener != null) {
            r0.listener.onProblemReportFailed(r0.firstErrorCode);
        }
    }

    private String createPushUrl(int i) {
        String token = FirebaseInstanceId.getInstance().getToken();
        if (token == null) {
            return 0;
        }
        Builder newBuilder = HttpUrl.parse("http://apiapp.veturilo.net.pl/v1/send-push").newBuilder();
        newBuilder.setQueryParameter(PushData.PARAM_TOKEN, token).setQueryParameter(PushData.PARAM_ACTION, PushData.ACTION_PROBLEM_REPORT).setQueryParameter(PushData.PARAM_PROBLEM_ID, Integer.toString(i)).setQueryParameter(PushData.PARAM_LANGUAGE, LocaleHelper.getLanguage(App.getAppContext())).setQueryParameter(PushData.PARAM_OS, "android").setQueryParameter(PushData.PARAM_MOBILE_NUMBER, UserManager.getUser().getPhoneNumber()).setQueryParameter(PushData.PARAM_BIKES_SYSTEM_ID, Servers.EXTENDED_API_SYSTEM_ID);
        return newBuilder.build().toString();
    }
}
