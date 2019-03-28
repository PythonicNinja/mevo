package com.mevo.app.data.model.response;

import com.mevo.app.data.model.CanceledBooking;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseCancelBooking {
    @Element(name = "booking", required = false)
    private CanceledBooking canceledBooking;

    public CanceledBooking getCanceledBooking() {
        return this.canceledBooking;
    }
}
