package com.mevo.app.data.model.response;

import com.mevo.app.data.model.BookingItem;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseActiveBookings {
    @ElementList(entry = "booking", inline = true, required = false)
    public List<BookingItem> bookingItems;

    public List<BookingItem> getBookingItems() {
        return this.bookingItems;
    }
}
