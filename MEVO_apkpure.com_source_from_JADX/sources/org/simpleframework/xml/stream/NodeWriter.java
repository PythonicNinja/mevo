package org.simpleframework.xml.stream;

import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class NodeWriter {
    private final Set active;
    private final OutputStack stack;
    private final boolean verbose;
    private final Formatter writer;

    public NodeWriter(Writer writer) {
        this(writer, new Format());
    }

    public NodeWriter(Writer writer, Format format) {
        this(writer, format, false);
    }

    private NodeWriter(Writer writer, Format format, boolean z) {
        this.writer = new Formatter(writer, format);
        this.active = new HashSet();
        this.stack = new OutputStack(this.active);
        this.verbose = z;
    }

    public OutputNode writeRoot() throws Exception {
        OutputNode outputDocument = new OutputDocument(this, this.stack);
        if (this.stack.isEmpty()) {
            this.writer.writeProlog();
        }
        return outputDocument;
    }

    public boolean isRoot(OutputNode outputNode) {
        return this.stack.bottom() == outputNode ? true : null;
    }

    public boolean isCommitted(OutputNode outputNode) {
        return this.active.contains(outputNode) ^ 1;
    }

    public void commit(OutputNode outputNode) throws Exception {
        if (this.stack.contains(outputNode)) {
            OutputNode top = this.stack.top();
            if (!isCommitted(top)) {
                writeStart(top);
            }
            while (this.stack.top() != outputNode) {
                writeEnd(this.stack.pop());
            }
            writeEnd(outputNode);
            this.stack.pop();
        }
    }

    public void remove(OutputNode outputNode) throws Exception {
        if (this.stack.top() != outputNode) {
            throw new NodeException("Cannot remove node");
        }
        this.stack.pop();
    }

    public OutputNode writeElement(OutputNode outputNode, String str) throws Exception {
        if (this.stack.isEmpty()) {
            return writeStart(outputNode, str);
        }
        if (!this.stack.contains(outputNode)) {
            return null;
        }
        OutputNode top = this.stack.top();
        if (!isCommitted(top)) {
            writeStart(top);
        }
        while (this.stack.top() != outputNode) {
            writeEnd(this.stack.pop());
        }
        if (!this.stack.isEmpty()) {
            writeValue(outputNode);
        }
        return writeStart(outputNode, str);
    }

    private OutputNode writeStart(OutputNode outputNode, String str) throws Exception {
        OutputNode outputElement = new OutputElement(outputNode, this, str);
        if (str != null) {
            return this.stack.push(outputElement);
        }
        throw new NodeException("Can not have a null name");
    }

    private void writeStart(OutputNode outputNode) throws Exception {
        writeComment(outputNode);
        writeName(outputNode);
        writeAttributes(outputNode);
        writeNamespaces(outputNode);
    }

    private void writeComment(OutputNode outputNode) throws Exception {
        outputNode = outputNode.getComment();
        if (outputNode != null) {
            this.writer.writeComment(outputNode);
        }
    }

    private void writeName(OutputNode outputNode) throws Exception {
        String prefix = outputNode.getPrefix(this.verbose);
        outputNode = outputNode.getName();
        if (outputNode != null) {
            this.writer.writeStart(outputNode, prefix);
        }
    }

    private void writeValue(OutputNode outputNode) throws Exception {
        Mode mode = outputNode.getMode();
        String value = outputNode.getValue();
        if (value != null) {
            Iterator it = this.stack.iterator();
            while (it.hasNext()) {
                OutputNode outputNode2 = (OutputNode) it.next();
                if (mode != Mode.INHERIT) {
                    break;
                }
                mode = outputNode2.getMode();
            }
            this.writer.writeText(value, mode);
        }
        outputNode.setValue(null);
    }

    private void writeEnd(OutputNode outputNode) throws Exception {
        String name = outputNode.getName();
        String prefix = outputNode.getPrefix(this.verbose);
        if (outputNode.getValue() != null) {
            writeValue(outputNode);
        }
        if (name != null) {
            this.writer.writeEnd(name, prefix);
            this.writer.flush();
        }
    }

    private void writeAttributes(OutputNode outputNode) throws Exception {
        NodeMap<String> attributes = outputNode.getAttributes();
        for (String str : attributes) {
            OutputNode outputNode2 = (OutputNode) attributes.get(str);
            this.writer.writeAttribute(str, outputNode2.getValue(), outputNode2.getPrefix(this.verbose));
        }
        this.active.remove(outputNode);
    }

    private void writeNamespaces(OutputNode outputNode) throws Exception {
        OutputNode<String> namespaces = outputNode.getNamespaces();
        for (String str : namespaces) {
            this.writer.writeNamespace(str, namespaces.getPrefix(str));
        }
    }
}
