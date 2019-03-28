package com.mevo.app.data.model.response;

import com.mevo.app.data.model.User;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseRecoverPin {
    @Element(name = "user")
    public User user;
    @Attribute(name = "version", required = false)
    public float version;

    public static class UserRecoverPin {
        @Attribute(name = "mobile")
        public String phoneNumber;
    }
}
