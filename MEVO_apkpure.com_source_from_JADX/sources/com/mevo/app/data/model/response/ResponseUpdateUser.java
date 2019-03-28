package com.mevo.app.data.model.response;

import com.mevo.app.data.model.UserDetails;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseUpdateUser {
    @Attribute(name = "server_time", required = false)
    public long serverTime;
    @Element(name = "user")
    public UserDetails userDetails;
    @Attribute(name = "version", required = false)
    public float version;
}
