package com.mevo.app.presentation.main.report_problem;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.reportable_error.BikeProblem;
import com.mevo.app.data.repository.UserRepository;
import com.mevo.app.presentation.custom_views.CustomCheckBox;
import com.mevo.app.presentation.custom_views.CustomInput;
import com.mevo.app.presentation.custom_views.ProgressOverlayView;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;
import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.tools.Lifecycle;
import com.mevo.app.tools.ProblemReportHelper;
import com.mevo.app.tools.ProblemReportHelper.ProblemReportListener;
import java.util.ArrayList;
import java.util.List;

public class ReportProblemFragment extends MainFragment {
    public static String reports = "{\"1\":{\"1\":\"battery\"}}";
    private CustomCheckBox brake;
    private CustomCheckBox chain;
    List<CustomCheckBox> checkBoxList;
    ConstraintLayout constraintLayout;
    private CustomInput description;
    private TextInputEditText descriptionInput;
    private CustomCheckBox engine;
    private CustomCheckBox frontWheel;
    private CustomCheckBox handBars;
    private CustomCheckBox pedals;
    private ProblemReportHelper problemReportHelper;
    private ProgressOverlayView progressOverlayView;
    private CustomCheckBox rearWheel;
    private AppCompatButton reportActionButton;
    private CustomCheckBox seat;
    private CustomInput stationNumber;
    private TextInputEditText stationNumberInput;
    private UserRepository userRepository;

    /* renamed from: com.mevo.app.presentation.main.report_problem.ReportProblemFragment$1 */
    class C08261 implements ProblemReportListener {
        public void onImageSent(boolean z) {
        }

        C08261() {
        }

        public void onProblemReported(int i) {
            new Builder().setText((int) C0434R.string.problem_report_success_info).setPositiveButton((int) C0434R.string.close, ReportProblemFragment$1$$Lambda$0.$instance).build().show();
            ReportProblemFragment.this.progressOverlayView.hide();
        }

        static final /* synthetic */ void lambda$onProblemReported$246$ReportProblemFragment$1(View view) {
            view = Lifecycle.getActivity();
            if (view != null && (view instanceof MainActivityInterface)) {
                ((MainActivityInterface) view).goToHome();
            }
        }

        public void onProblemReportFailed(int i) {
            ReportProblemFragment.this.progressOverlayView.hide();
            switch (i) {
                case 0:
                    TopToast.show(C0434R.string.error_server_report_problem, 0, TopToast.DURATION_SHORT);
                    return;
                case 1:
                    TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_SHORT);
                    return;
                default:
                    TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_SHORT);
                    return;
            }
        }
    }

    public static ReportProblemFragment newInstance() {
        return new ReportProblemFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_report_problem, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        if (IM.resources().getDisplayMetrics().widthPixels < 600) {
            setupCheckboxes(0.3f, true);
        } else if (IM.resources().getDisplayMetrics().widthPixels < 600 || IM.resources().getDisplayMetrics().widthPixels >= 1440) {
            setupCheckboxes(0.45f, false);
        } else {
            setupCheckboxes(0.4f, false);
        }
        return layoutInflater;
    }

    private void findViews(View view) {
        this.frontWheel = (CustomCheckBox) view.findViewById(C0434R.id.fragment_report_front);
        this.seat = (CustomCheckBox) view.findViewById(C0434R.id.fragment_report_seat);
        this.handBars = (CustomCheckBox) view.findViewById(C0434R.id.fragment_report_handbars);
        this.pedals = (CustomCheckBox) view.findViewById(C0434R.id.fragment_report_pedals);
        this.chain = (CustomCheckBox) view.findViewById(C0434R.id.fragment_report_chain);
        this.rearWheel = (CustomCheckBox) view.findViewById(C0434R.id.fragment_report_rear);
        this.engine = (CustomCheckBox) view.findViewById(C0434R.id.fragment_report_engine);
        this.brake = (CustomCheckBox) view.findViewById(C0434R.id.fragment_report_brake);
        this.stationNumber = (CustomInput) view.findViewById(C0434R.id.fragment_report_number);
        this.stationNumberInput = (TextInputEditText) view.findViewById(C0434R.id.fragment_report_number_edit);
        this.description = (CustomInput) view.findViewById(C0434R.id.fragment_report_description);
        this.descriptionInput = (TextInputEditText) view.findViewById(C0434R.id.fragment_report_description_edit);
        this.reportActionButton = (AppCompatButton) view.findViewById(C0434R.id.fragment_report_action);
        this.progressOverlayView = (ProgressOverlayView) view.findViewById(C0434R.id.progress_bar);
        this.constraintLayout = (ConstraintLayout) view.findViewById(C0434R.id.fragment_report_bike_container);
    }

    private void setupCheckboxes(float f, boolean z) {
        this.checkBoxList = new ArrayList();
        this.checkBoxList.add(this.engine);
        this.checkBoxList.add(this.seat);
        this.checkBoxList.add(this.handBars);
        this.checkBoxList.add(this.frontWheel);
        this.checkBoxList.add(this.pedals);
        this.checkBoxList.add(this.chain);
        this.checkBoxList.add(this.brake);
        this.checkBoxList.add(this.rearWheel);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.constraintLayout);
        for (CustomCheckBox customCheckBox : this.checkBoxList) {
            constraintSet.constrainPercentWidth(customCheckBox.getId(), f);
            if (z) {
                customCheckBox.setTextSizeForSmallLayout();
            }
        }
        constraintSet.applyTo(this.constraintLayout);
    }

    private void setListeners() {
        this.stationNumber.setValidator(null, ReportProblemFragment$$Lambda$0.$instance);
        this.description.setValidator(null, ReportProblemFragment$$Lambda$1.$instance);
        this.reportActionButton.setOnClickListener(new ReportProblemFragment$$Lambda$2(this));
        this.problemReportHelper = new ProblemReportHelper(new C08261());
    }

    static final /* synthetic */ Pair lambda$setListeners$243$ReportProblemFragment(String str) {
        if (str.length() > null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), IM.context().getString(C0434R.string.wrong_station_number));
    }

    final /* synthetic */ void lambda$setListeners$245$ReportProblemFragment(View view) {
        reportProblem();
    }

    private String setMessage(String str) {
        String str2 = "";
        if (this.engine.isChecked()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(BikeProblem.ENGINE.getProblemName());
            str2 = stringBuilder.toString();
        }
        if (this.seat.isChecked()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            if (str2.length() > 0) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", ");
                stringBuilder2.append(BikeProblem.SADDLE.getProblemName());
                str2 = stringBuilder2.toString();
            } else {
                str2 = BikeProblem.SADDLE.getProblemName();
            }
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (this.handBars.isChecked()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            if (str2.length() > 0) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", ");
                stringBuilder2.append(BikeProblem.HANDLEBARS.getProblemName());
                str2 = stringBuilder2.toString();
            } else {
                str2 = BikeProblem.HANDLEBARS.getProblemName();
            }
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (this.frontWheel.isChecked()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            if (str2.length() > 0) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", ");
                stringBuilder2.append(BikeProblem.FRONT_WHEEL.getProblemName());
                str2 = stringBuilder2.toString();
            } else {
                str2 = BikeProblem.FRONT_WHEEL.getProblemName();
            }
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (this.pedals.isChecked()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            if (str2.length() > 0) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", ");
                stringBuilder2.append(BikeProblem.PEDALS.getProblemName());
                str2 = stringBuilder2.toString();
            } else {
                str2 = BikeProblem.PEDALS.getProblemName();
            }
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (this.chain.isChecked()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            if (str2.length() > 0) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", ");
                stringBuilder2.append(BikeProblem.CHAIN.getProblemName());
                str2 = stringBuilder2.toString();
            } else {
                str2 = BikeProblem.CHAIN.getProblemName();
            }
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (this.brake.isChecked()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            if (str2.length() > 0) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", ");
                stringBuilder2.append(BikeProblem.BRAKE.getProblemName());
                str2 = stringBuilder2.toString();
            } else {
                str2 = BikeProblem.BRAKE.getProblemName();
            }
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (this.rearWheel.isChecked()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            if (str2.length() > 0) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", ");
                stringBuilder2.append(BikeProblem.BACK_WHEEL.getProblemName());
                str2 = stringBuilder2.toString();
            } else {
                str2 = BikeProblem.BACK_WHEEL.getProblemName();
            }
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (str2.length() > 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(IM.resources().getString(C0434R.string.fault));
            stringBuilder.append(": ");
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        } else {
            str2 = "";
        }
        if (str.length() <= 0) {
            return str2;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("\n");
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    private void reportProblem() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r10 = this;
        r0 = r10.stationNumber;
        r0.checkValid();
        r0 = r10.description;
        r0.checkValid();
        r0 = r10.checkBoxValid();
        r1 = r10.stationNumber;
        r1 = r1.isValid();
        r1 = r1.booleanValue();
        if (r1 == 0) goto L_0x009d;
    L_0x001a:
        r1 = r10.description;
        r1 = r1.isValid();
        r1 = r1.booleanValue();
        if (r1 == 0) goto L_0x009d;
    L_0x0026:
        if (r0 != 0) goto L_0x002a;
    L_0x0028:
        goto L_0x009d;
    L_0x002a:
        r0 = r10.stationNumberInput;
        r0 = r0.getVisibility();
        r1 = 0;
        if (r0 != 0) goto L_0x0047;
    L_0x0033:
        r0 = r10.stationNumberInput;	 Catch:{ Exception -> 0x0047 }
        r0 = r0.getText();	 Catch:{ Exception -> 0x0047 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0047 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0047 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x0047 }
        r5 = r0;
        goto L_0x0048;
    L_0x0047:
        r5 = r1;
    L_0x0048:
        r0 = r10.descriptionInput;
        r0 = r0.getText();
        r0 = r0.toString();
        r6 = r10.setMessage(r0);
        r0 = "bikes";
        r2 = com.mevo.app.tools.location.LocationTool.get();
        r2 = r2.getLastKnownLocation();
        if (r2 == 0) goto L_0x0075;
    L_0x0062:
        r3 = r2.getLatitude();
        r1 = java.lang.Double.toString(r3);
        r2 = r2.getLongitude();
        r2 = java.lang.Double.toString(r2);
        r7 = r1;
        r8 = r2;
        goto L_0x0077;
    L_0x0075:
        r7 = r1;
        r8 = r7;
    L_0x0077:
        r1 = r10.progressOverlayView;
        r1.show();
        r1 = r10.engine;
        r1 = r1.isChecked();
        if (r1 == 0) goto L_0x0095;
    L_0x0084:
        r1 = new com.mevo.app.data.repository.UserRepository;
        r1.<init>();
        r9 = new com.mevo.app.presentation.main.report_problem.ReportProblemFragment$$Lambda$3;
        r2 = r9;
        r3 = r10;
        r4 = r0;
        r2.<init>(r3, r4, r5, r6, r7, r8);
        r1.canUserSendBatteryReport(r9);
        goto L_0x009c;
    L_0x0095:
        r2 = r10.problemReportHelper;
        r4 = 0;
        r3 = r0;
        r2.reportProblem(r3, r4, r5, r6, r7, r8);
    L_0x009c:
        return;
    L_0x009d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.presentation.main.report_problem.ReportProblemFragment.reportProblem():void");
    }

    final /* synthetic */ void lambda$reportProblem$247$ReportProblemFragment(String str, Integer num, String str2, String str3, String str4, Boolean bool, Boolean bool2, Exception exception) {
        ReportProblemFragment reportProblemFragment = this;
        if (!bool.booleanValue()) {
            TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_SHORT);
            reportProblemFragment.progressOverlayView.hide();
        } else if (bool2.booleanValue()) {
            reportProblemFragment.problemReportHelper.reportProblem(str, reports, num, str2, str3, str4);
        } else {
            TopToast.show(C0434R.string.toast_report_battery_limit, 0, TopToast.DURATION_SHORT);
            reportProblemFragment.progressOverlayView.hide();
        }
    }

    private boolean checkBoxValid() {
        boolean z;
        if (!(this.engine.isChecked() || this.seat.isChecked() || this.handBars.isChecked() || this.frontWheel.isChecked() || this.pedals.isChecked() || this.chain.isChecked())) {
            if (!this.rearWheel.isChecked()) {
                z = false;
                if (!z) {
                    TopToast.show(C0434R.string.check_box_error, 0, TopToast.DURATION_SHORT);
                }
                return z;
            }
        }
        z = true;
        if (z) {
            TopToast.show(C0434R.string.check_box_error, 0, TopToast.DURATION_SHORT);
        }
        return z;
    }
}
