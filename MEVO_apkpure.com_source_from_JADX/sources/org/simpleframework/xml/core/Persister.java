package org.simpleframework.xml.core;

import com.mevo.app.tools.UtilsWebView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.filter.Filter;
import org.simpleframework.xml.filter.PlatformFilter;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeBuilder;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.transform.Matcher;

public class Persister implements Serializer {
    private final Format format;
    private final SessionManager manager;
    private final Strategy strategy;
    private final Support support;

    public Persister() {
        this(new HashMap());
    }

    public Persister(Format format) {
        this(new TreeStrategy(), format);
    }

    public Persister(Map map) {
        this(new PlatformFilter(map));
    }

    public Persister(Map map, Format format) {
        this(new PlatformFilter(map));
    }

    public Persister(Filter filter) {
        this(new TreeStrategy(), filter);
    }

    public Persister(Filter filter, Format format) {
        this(new TreeStrategy(), filter, format);
    }

    public Persister(Matcher matcher) {
        this(new TreeStrategy(), matcher);
    }

    public Persister(Matcher matcher, Format format) {
        this(new TreeStrategy(), matcher, format);
    }

    public Persister(Strategy strategy) {
        this(strategy, new HashMap());
    }

    public Persister(Strategy strategy, Format format) {
        this(strategy, new HashMap(), format);
    }

    public Persister(Filter filter, Matcher matcher) {
        this(new TreeStrategy(), filter, matcher);
    }

    public Persister(Filter filter, Matcher matcher, Format format) {
        this(new TreeStrategy(), filter, matcher, format);
    }

    public Persister(Strategy strategy, Map map) {
        this(strategy, new PlatformFilter(map));
    }

    public Persister(Strategy strategy, Map map, Format format) {
        this(strategy, new PlatformFilter(map), format);
    }

    public Persister(Strategy strategy, Filter filter) {
        this(strategy, filter, new Format());
    }

    public Persister(Strategy strategy, Filter filter, Format format) {
        this(strategy, filter, new EmptyMatcher(), format);
    }

    public Persister(Strategy strategy, Matcher matcher) {
        this(strategy, new PlatformFilter(), matcher);
    }

    public Persister(Strategy strategy, Matcher matcher, Format format) {
        this(strategy, new PlatformFilter(), matcher, format);
    }

    public Persister(Strategy strategy, Filter filter, Matcher matcher) {
        this(strategy, filter, matcher, new Format());
    }

    public Persister(Strategy strategy, Filter filter, Matcher matcher, Format format) {
        this.support = new Support(filter, matcher, format);
        this.manager = new SessionManager();
        this.strategy = strategy;
        this.format = format;
    }

    public <T> T read(Class<? extends T> cls, String str) throws Exception {
        return read((Class) cls, str, true);
    }

    public <T> T read(Class<? extends T> cls, File file) throws Exception {
        return read((Class) cls, file, true);
    }

    public <T> T read(Class<? extends T> cls, InputStream inputStream) throws Exception {
        return read((Class) cls, inputStream, true);
    }

    public <T> T read(Class<? extends T> cls, Reader reader) throws Exception {
        return read((Class) cls, reader, true);
    }

    public <T> T read(Class<? extends T> cls, InputNode inputNode) throws Exception {
        return read((Class) cls, inputNode, true);
    }

    public <T> T read(Class<? extends T> cls, String str, boolean z) throws Exception {
        return read((Class) cls, new StringReader(str), z);
    }

    public <T> T read(Class<? extends T> cls, File file, boolean z) throws Exception {
        InputStream fileInputStream = new FileInputStream(file);
        try {
            cls = read((Class) cls, fileInputStream, z);
            return cls;
        } finally {
            fileInputStream.close();
        }
    }

    public <T> T read(Class<? extends T> cls, InputStream inputStream, boolean z) throws Exception {
        return read((Class) cls, NodeBuilder.read(inputStream), z);
    }

    public <T> T read(Class<? extends T> cls, Reader reader, boolean z) throws Exception {
        return read((Class) cls, NodeBuilder.read(reader), z);
    }

    public <T> T read(Class<? extends T> cls, InputNode inputNode, boolean z) throws Exception {
        try {
            cls = read((Class) cls, inputNode, this.manager.open(z));
            return cls;
        } finally {
            this.manager.close();
        }
    }

    private <T> T read(Class<? extends T> cls, InputNode inputNode, Session session) throws Exception {
        return read((Class) cls, inputNode, new Source(this.strategy, this.support, session));
    }

    private <T> T read(Class<? extends T> cls, InputNode inputNode, Context context) throws Exception {
        return new Traverser(context).read(inputNode, (Class) cls);
    }

    public <T> T read(T t, String str) throws Exception {
        return read((Object) t, str, true);
    }

    public <T> T read(T t, File file) throws Exception {
        return read((Object) t, file, true);
    }

    public <T> T read(T t, InputStream inputStream) throws Exception {
        return read((Object) t, inputStream, true);
    }

    public <T> T read(T t, Reader reader) throws Exception {
        return read((Object) t, reader, true);
    }

    public <T> T read(T t, InputNode inputNode) throws Exception {
        return read((Object) t, inputNode, true);
    }

    public <T> T read(T t, String str, boolean z) throws Exception {
        return read((Object) t, new StringReader(str), z);
    }

    public <T> T read(T t, File file, boolean z) throws Exception {
        InputStream fileInputStream = new FileInputStream(file);
        try {
            t = read((Object) t, fileInputStream, z);
            return t;
        } finally {
            fileInputStream.close();
        }
    }

    public <T> T read(T t, InputStream inputStream, boolean z) throws Exception {
        return read((Object) t, NodeBuilder.read(inputStream), z);
    }

    public <T> T read(T t, Reader reader, boolean z) throws Exception {
        return read((Object) t, NodeBuilder.read(reader), z);
    }

    public <T> T read(T t, InputNode inputNode, boolean z) throws Exception {
        try {
            t = read((Object) t, inputNode, this.manager.open(z));
            return t;
        } finally {
            this.manager.close();
        }
    }

    private <T> T read(T t, InputNode inputNode, Session session) throws Exception {
        return read((Object) t, inputNode, new Source(this.strategy, this.support, session));
    }

    private <T> T read(T t, InputNode inputNode, Context context) throws Exception {
        return new Traverser(context).read(inputNode, (Object) t);
    }

    public boolean validate(Class cls, String str) throws Exception {
        return validate(cls, str, true);
    }

    public boolean validate(Class cls, File file) throws Exception {
        return validate(cls, file, true);
    }

    public boolean validate(Class cls, InputStream inputStream) throws Exception {
        return validate(cls, inputStream, true);
    }

    public boolean validate(Class cls, Reader reader) throws Exception {
        return validate(cls, reader, true);
    }

    public boolean validate(Class cls, InputNode inputNode) throws Exception {
        return validate(cls, inputNode, true);
    }

    public boolean validate(Class cls, String str, boolean z) throws Exception {
        return validate(cls, new StringReader(str), z);
    }

    public boolean validate(Class cls, File file, boolean z) throws Exception {
        InputStream fileInputStream = new FileInputStream(file);
        try {
            cls = validate(cls, fileInputStream, z);
            return cls;
        } finally {
            fileInputStream.close();
        }
    }

    public boolean validate(Class cls, InputStream inputStream, boolean z) throws Exception {
        return validate(cls, NodeBuilder.read(inputStream), z);
    }

    public boolean validate(Class cls, Reader reader, boolean z) throws Exception {
        return validate(cls, NodeBuilder.read(reader), z);
    }

    public boolean validate(Class cls, InputNode inputNode, boolean z) throws Exception {
        try {
            cls = validate(cls, inputNode, this.manager.open(z));
            return cls;
        } finally {
            this.manager.close();
        }
    }

    private boolean validate(Class cls, InputNode inputNode, Session session) throws Exception {
        return validate(cls, inputNode, new Source(this.strategy, this.support, session));
    }

    private boolean validate(Class cls, InputNode inputNode, Context context) throws Exception {
        return new Traverser(context).validate(inputNode, cls);
    }

    public void write(Object obj, OutputNode outputNode) throws Exception {
        try {
            write(obj, outputNode, this.manager.open());
        } finally {
            this.manager.close();
        }
    }

    private void write(Object obj, OutputNode outputNode, Session session) throws Exception {
        write(obj, outputNode, new Source(this.strategy, this.support, session));
    }

    private void write(Object obj, OutputNode outputNode, Context context) throws Exception {
        new Traverser(context).write(outputNode, obj);
    }

    public void write(Object obj, File file) throws Exception {
        OutputStream fileOutputStream = new FileOutputStream(file);
        try {
            write(obj, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    public void write(Object obj, OutputStream outputStream) throws Exception {
        write(obj, outputStream, UtilsWebView.ENCODING);
    }

    public void write(Object obj, OutputStream outputStream, String str) throws Exception {
        write(obj, new OutputStreamWriter(outputStream, str));
    }

    public void write(Object obj, Writer writer) throws Exception {
        write(obj, NodeBuilder.write(writer, this.format));
    }
}
