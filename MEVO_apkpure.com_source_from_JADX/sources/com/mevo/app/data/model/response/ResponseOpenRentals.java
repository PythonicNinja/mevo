package com.mevo.app.data.model.response;

import com.mevo.app.data.model.RentalItem;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseOpenRentals {
    @ElementList(name = "rentalCollection", required = true)
    public List<RentalItem> openRentals;
}
