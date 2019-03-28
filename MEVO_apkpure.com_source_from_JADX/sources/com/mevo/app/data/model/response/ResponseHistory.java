package com.mevo.app.data.model.response;

import com.mevo.app.data.model.AccountHistory;
import com.mevo.app.data.model.User;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseHistory {
    @Element(name = "account", required = false)
    public AccountHistory accountHistory;
    @Element(name = "user", required = false)
    public User user;
    @Attribute(name = "version", required = false)
    public float version;
}
