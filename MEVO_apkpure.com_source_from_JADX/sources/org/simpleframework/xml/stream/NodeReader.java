package org.simpleframework.xml.stream;

class NodeReader {
    private final EventReader reader;
    private final InputStack stack = new InputStack();
    private final StringBuilder text = new StringBuilder();

    public NodeReader(EventReader eventReader) {
        this.reader = eventReader;
    }

    public boolean isRoot(InputNode inputNode) {
        return this.stack.bottom() == inputNode ? true : null;
    }

    public InputNode readRoot() throws Exception {
        if (!this.stack.isEmpty()) {
            return null;
        }
        InputNode readElement = readElement(null);
        if (readElement != null) {
            return readElement;
        }
        throw new NodeException("Document has no root element");
    }

    public InputNode readElement(InputNode inputNode) throws Exception {
        if (!this.stack.isRelevant(inputNode)) {
            return null;
        }
        EventNode next = this.reader.next();
        while (next != null) {
            if (next.isEnd()) {
                if (this.stack.pop() == inputNode) {
                    return null;
                }
            } else if (next.isStart()) {
                return readStart(inputNode, next);
            }
            next = this.reader.next();
        }
        return null;
    }

    public InputNode readElement(InputNode inputNode, String str) throws Exception {
        if (!this.stack.isRelevant(inputNode)) {
            return null;
        }
        EventNode peek = this.reader.peek();
        while (peek != null) {
            if (peek.isText()) {
                fillText(inputNode);
            } else if (peek.isEnd()) {
                if (this.stack.top() == inputNode) {
                    return null;
                }
                this.stack.pop();
            } else if (peek.isStart()) {
                if (isName(peek, str) != null) {
                    return readElement(inputNode);
                }
                return null;
            }
            this.reader.next();
            peek = this.reader.peek();
        }
        return null;
    }

    private InputNode readStart(InputNode inputNode, EventNode eventNode) throws Exception {
        InputNode inputElement = new InputElement(inputNode, this, eventNode);
        if (this.text.length() > null) {
            this.text.setLength(0);
        }
        return eventNode.isStart() != null ? (InputNode) this.stack.push(inputElement) : inputElement;
    }

    private boolean isName(EventNode eventNode, String str) {
        eventNode = eventNode.getName();
        if (eventNode == null) {
            return null;
        }
        return eventNode.equals(str);
    }

    public String readValue(InputNode inputNode) throws Exception {
        if (!this.stack.isRelevant(inputNode)) {
            return null;
        }
        if (this.text.length() <= 0 && this.reader.peek().isEnd()) {
            if (this.stack.top() == inputNode) {
                return null;
            }
            this.stack.pop();
            this.reader.next();
        }
        return readText(inputNode);
    }

    private String readText(InputNode inputNode) throws Exception {
        EventNode peek = this.reader.peek();
        while (this.stack.top() == inputNode && r0.isText()) {
            fillText(inputNode);
            this.reader.next();
            peek = this.reader.peek();
        }
        return readBuffer(inputNode);
    }

    private String readBuffer(InputNode inputNode) throws Exception {
        if (this.text.length() <= null) {
            return null;
        }
        inputNode = this.text.toString();
        this.text.setLength(0);
        return inputNode;
    }

    private void fillText(InputNode inputNode) throws Exception {
        inputNode = this.reader.peek();
        if (inputNode.isText()) {
            this.text.append(inputNode.getValue());
        }
    }

    public boolean isEmpty(InputNode inputNode) throws Exception {
        return (this.stack.top() != inputNode || this.reader.peek().isEnd() == null) ? null : true;
    }

    public void skipElement(InputNode inputNode) throws Exception {
        while (readElement(inputNode) != null) {
        }
    }
}
