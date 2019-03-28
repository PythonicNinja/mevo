package org.simpleframework.xml.core;

import java.util.Arrays;
import java.util.List;

class LabelGroup {
    private final List<Label> list;
    private final int size;

    public LabelGroup(Label label) {
        this(Arrays.asList(new Label[]{label}));
    }

    public LabelGroup(List<Label> list) {
        this.size = list.size();
        this.list = list;
    }

    public List<Label> getList() {
        return this.list;
    }

    public Label getPrimary() {
        return this.size > 0 ? (Label) this.list.get(0) : null;
    }
}
