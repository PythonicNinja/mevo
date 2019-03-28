package com.mevo.app.data.model.reportable_error;

public enum BikeProblem {
    FRAME("RAMA"),
    HANDLEBARS("KIEROWNICA"),
    CHAIN("ŁAŃCUCH"),
    FRONT_WHEEL("PRZEDNIE KOŁO"),
    BACK_WHEEL("TYLNE KOŁO"),
    SADDLE("SIODEŁKO"),
    PEDALS("PEDAŁY"),
    BRAKE("HAMULEC"),
    ENGINE("SILNIK");
    
    String problemName;

    private BikeProblem(String str) {
        this.problemName = str;
    }

    public String getProblemName() {
        return this.problemName;
    }
}
