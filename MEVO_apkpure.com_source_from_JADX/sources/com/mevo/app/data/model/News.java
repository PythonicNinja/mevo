package com.mevo.app.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class News {
    @Element(name = "encoded", required = false)
    @Root(name = "content")
    public String content;
    @Element(name = "description", required = false)
    public String description;
    @Element(name = "link", required = false)
    public String link;
    @Element(name = "title", required = false)
    public String title;
}
