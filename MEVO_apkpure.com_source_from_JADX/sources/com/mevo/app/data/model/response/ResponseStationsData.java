package com.mevo.app.data.model.response;

import com.mevo.app.data.model.Country;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "markers")
public class ResponseStationsData {
    @ElementList(inline = true, name = "country", required = false)
    public List<Country> countries;
}
