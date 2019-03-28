package com.mevo.app.data.model.response;

import com.mevo.app.data.model.RentalItem;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseReturnBike {
    @Element(name = "error", required = false)
    public Error error;
    @Element(name = "rental", required = false)
    public RentalItem rental;
}
