package com.mevo.app.data.model.response;

import com.mevo.app.data.model.Country;
import com.mevo.app.data.model.Tariff;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseTariffs {
    @Attribute(name = "country", required = false)
    public List<Country> country;
    @ElementList(entry = "tariff", inline = true, required = false)
    public List<Tariff> tarrifs;
}
