package org.simpleframework.xml.core;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class ScannerFactory {
    private final Cache<Scanner> cache = new ConcurrentCache();
    private final Support support;

    public ScannerFactory(Support support) {
        this.support = support;
    }

    public Scanner getInstance(Class cls) throws Exception {
        Scanner scanner = (Scanner) this.cache.fetch(cls);
        if (scanner == null) {
            PrimitiveScanner primitiveScanner;
            Detail detail = this.support.getDetail(cls);
            if (this.support.isPrimitive(cls)) {
                primitiveScanner = new PrimitiveScanner(detail);
            } else {
                primitiveScanner = new ObjectScanner(detail, this.support);
                if (primitiveScanner.isPrimitive() && !this.support.isContainer(cls)) {
                    primitiveScanner = new DefaultScanner(detail, this.support);
                }
            }
            scanner = primitiveScanner;
            this.cache.cache(cls, scanner);
        }
        return scanner;
    }
}
