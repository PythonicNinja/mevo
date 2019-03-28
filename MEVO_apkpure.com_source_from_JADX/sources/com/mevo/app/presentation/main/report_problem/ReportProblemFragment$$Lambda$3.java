package com.mevo.app.presentation.main.report_problem;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseBatteryReport;

final /* synthetic */ class ReportProblemFragment$$Lambda$3 implements ResponseBatteryReport {
    private final ReportProblemFragment arg$1;
    private final String arg$2;
    private final Integer arg$3;
    private final String arg$4;
    private final String arg$5;
    private final String arg$6;

    ReportProblemFragment$$Lambda$3(ReportProblemFragment reportProblemFragment, String str, Integer num, String str2, String str3, String str4) {
        this.arg$1 = reportProblemFragment;
        this.arg$2 = str;
        this.arg$3 = num;
        this.arg$4 = str2;
        this.arg$5 = str3;
        this.arg$6 = str4;
    }

    public void onResponse(Boolean bool, Boolean bool2, Exception exception) {
        this.arg$1.lambda$reportProblem$247$ReportProblemFragment(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, bool, bool2, exception);
    }
}
