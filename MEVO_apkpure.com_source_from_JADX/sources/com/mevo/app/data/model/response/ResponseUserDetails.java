package com.mevo.app.data.model.response;

import com.mevo.app.data.model.UserDetails;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseUserDetails {
    @Attribute(name = "server_time", required = false)
    public long serverTime;
    @Element(name = "user", required = false)
    public UserDetails userDetails;
}
