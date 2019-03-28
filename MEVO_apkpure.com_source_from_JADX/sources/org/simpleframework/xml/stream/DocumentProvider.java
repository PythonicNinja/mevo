package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;

class DocumentProvider implements Provider {
    private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public DocumentProvider() {
        this.factory.setNamespaceAware(true);
    }

    public EventReader provide(InputStream inputStream) throws Exception {
        return provide(new InputSource(inputStream));
    }

    public EventReader provide(Reader reader) throws Exception {
        return provide(new InputSource(reader));
    }

    private EventReader provide(InputSource inputSource) throws Exception {
        return new DocumentReader(this.factory.newDocumentBuilder().parse(inputSource));
    }
}
