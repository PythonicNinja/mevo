package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

class PullProvider implements Provider {
    private final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

    public PullProvider() throws Exception {
        this.factory.setNamespaceAware(true);
    }

    public EventReader provide(InputStream inputStream) throws Exception {
        XmlPullParser newPullParser = this.factory.newPullParser();
        if (inputStream != null) {
            newPullParser.setInput(inputStream, null);
        }
        return new PullReader(newPullParser);
    }

    public EventReader provide(Reader reader) throws Exception {
        XmlPullParser newPullParser = this.factory.newPullParser();
        if (reader != null) {
            newPullParser.setInput(reader);
        }
        return new PullReader(newPullParser);
    }
}
