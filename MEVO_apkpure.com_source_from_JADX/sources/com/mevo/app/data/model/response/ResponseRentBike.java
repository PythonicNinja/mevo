package com.mevo.app.data.model.response;

import com.mevo.app.data.model.RentalItem;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseRentBike {
    @Element(name = "rental")
    public RentalItem rental;
}
