package com.mevo.app.data.model.response;

import com.mevo.app.data.model.BikeXml;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseBikeState {
    @Element(name = "bike")
    public BikeXml bike;
}
