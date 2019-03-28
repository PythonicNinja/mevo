package org.simpleframework.xml.stream;

class InputStack extends Stack<InputNode> {
    public InputStack() {
        super(6);
    }

    public boolean isRelevant(InputNode inputNode) {
        if (contains(inputNode) == null) {
            if (isEmpty() == null) {
                return null;
            }
        }
        return true;
    }
}
