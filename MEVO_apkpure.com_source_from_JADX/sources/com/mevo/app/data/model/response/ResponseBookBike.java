package com.mevo.app.data.model.response;

import com.mevo.app.data.model.BookingItem;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseBookBike {
    @Element(name = "booking", required = false)
    private BookingItem bookingItem;

    public BookingItem getBookingItem() {
        return this.bookingItem;
    }
}
