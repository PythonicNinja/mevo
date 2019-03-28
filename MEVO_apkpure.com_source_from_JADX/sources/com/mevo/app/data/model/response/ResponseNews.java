package com.mevo.app.data.model.response;

import com.mevo.app.data.model.News;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class ResponseNews {
    @Element(name = "channel")
    public Channel channel;

    @Root(name = "channel")
    public static class Channel {
        @Element(name = "description", required = false)
        public String description;
        @Element(name = "language", required = false)
        public String language;
        @Element(name = "link", required = false)
        public String link;
        @ElementList(inline = true, name = "item")
        public List<News> newsList;
        @Element(name = "title", required = false)
        public String title;
    }
}
