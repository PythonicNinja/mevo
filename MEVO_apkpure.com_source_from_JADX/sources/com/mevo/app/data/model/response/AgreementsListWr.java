package com.mevo.app.data.model.response;

import com.mevo.app.data.model.Agreement;
import java.util.List;
import org.simpleframework.xml.ElementList;

public class AgreementsListWr {
    @ElementList(inline = true, name = "agreement")
    public List<Agreement> agreements;
}
