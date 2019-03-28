package com.mevo.app.data.model.response;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseReportProblem {
    @Element(name = "errorReport")
    public ErrorReport errorReport;
    @Attribute(name = "server_time", required = false)
    public float serverTimeSeconds;
    @Attribute(name = "version", required = false)
    public float version;

    public static class ErrorReport {
        @Attribute(name = "uid", required = true)
        public int id;
    }
}
