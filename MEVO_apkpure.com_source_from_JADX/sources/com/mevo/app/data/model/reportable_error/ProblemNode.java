package com.mevo.app.data.model.reportable_error;

import java.util.ArrayList;

public class ProblemNode {
    public static final String CATEGORY_BIKE = "bikes";
    public static final String CATEGORY_MESSAGE = "message";
    public static final String CATEGORY_STATION = "stations";
    public static final int TYPE_LEAF = 4;
    public static final int TYPE_MAIN = 1;
    public static final int TYPE_NODE = 3;
    public static final int TYPE_SUB = 2;
    public ArrayList<ProblemNode> children = new ArrayList();
    public String code;
    private String name;
    public ProblemNode parent;
    public int type;

    public ProblemNode(ProblemNode problemNode) {
        this.parent = problemNode;
    }

    public void setName(String str) {
        this.name = str.trim();
    }

    public String getName() {
        return this.name;
    }

    public void destroy() {
        for (int size = this.children.size() - 1; size >= 0; size--) {
            ((ProblemNode) this.children.get(size)).destroy();
        }
        if (this.children.size() == 0) {
            if (this.parent != null) {
                this.parent.children.remove(this);
            }
            this.parent = null;
        }
    }
}
