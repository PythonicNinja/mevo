package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ModelSection implements Section {
    private LabelMap attributes;
    private LabelMap elements;
    private Model model;
    private ModelMap models;

    public ModelSection(Model model) {
        this.model = model;
    }

    public String getName() {
        return this.model.getName();
    }

    public String getPrefix() {
        return this.model.getPrefix();
    }

    public String getPath(String str) throws Exception {
        Expression expression = this.model.getExpression();
        if (expression == null) {
            return str;
        }
        return expression.getElement(str);
    }

    public String getAttribute(String str) throws Exception {
        Expression expression = this.model.getExpression();
        if (expression == null) {
            return str;
        }
        return expression.getAttribute(str);
    }

    public Iterator<String> iterator() {
        List arrayList = new ArrayList();
        for (String add : this.model) {
            arrayList.add(add);
        }
        return arrayList.iterator();
    }

    public boolean isSection(String str) throws Exception {
        return getModels().get(str) != null ? true : null;
    }

    public ModelMap getModels() throws Exception {
        if (this.models == null) {
            this.models = this.model.getModels();
        }
        return this.models;
    }

    public Label getText() throws Exception {
        return this.model.getText();
    }

    public LabelMap getAttributes() throws Exception {
        if (this.attributes == null) {
            this.attributes = this.model.getAttributes();
        }
        return this.attributes;
    }

    public LabelMap getElements() throws Exception {
        if (this.elements == null) {
            this.elements = this.model.getElements();
        }
        return this.elements;
    }

    public Label getElement(String str) throws Exception {
        return getElements().getLabel(str);
    }

    public Section getSection(String str) throws Exception {
        ModelList modelList = (ModelList) getModels().get(str);
        if (modelList != null) {
            str = modelList.take();
            if (str != null) {
                return new ModelSection(str);
            }
        }
        return null;
    }
}
