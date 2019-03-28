package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class TreeModel implements Model {
    private LabelMap attributes;
    private Detail detail;
    private LabelMap elements;
    private Expression expression;
    private int index;
    private Label list;
    private ModelMap models;
    private String name;
    private OrderList order;
    private Policy policy;
    private String prefix;
    private Label text;

    private static class OrderList extends ArrayList<String> {
    }

    public TreeModel(Policy policy, Detail detail) {
        this(policy, detail, null, null, 1);
    }

    public TreeModel(Policy policy, Detail detail, String str, String str2, int i) {
        this.attributes = new LabelMap(policy);
        this.elements = new LabelMap(policy);
        this.models = new ModelMap(detail);
        this.order = new OrderList();
        this.detail = detail;
        this.policy = policy;
        this.prefix = str2;
        this.index = i;
        this.name = str;
    }

    public Model lookup(Expression expression) {
        Model lookup = lookup(expression.getFirst(), expression.getIndex());
        if (expression.isPath()) {
            expression = expression.getPath(1, 0);
            if (lookup != null) {
                return lookup.lookup(expression);
            }
        }
        return lookup;
    }

    public void registerElement(String str) throws Exception {
        if (!this.order.contains(str)) {
            this.order.add(str);
        }
        this.elements.put(str, null);
    }

    public void registerAttribute(String str) throws Exception {
        this.attributes.put(str, null);
    }

    public void registerText(Label label) throws Exception {
        if (this.text != null) {
            throw new TextException("Duplicate text annotation on %s", label);
        } else {
            this.text = label;
        }
    }

    public void registerAttribute(Label label) throws Exception {
        String name = label.getName();
        if (this.attributes.get(name) != null) {
            throw new AttributeException("Duplicate annotation of name '%s' on %s", name, label);
        } else {
            this.attributes.put(name, label);
        }
    }

    public void registerElement(Label label) throws Exception {
        String name = label.getName();
        if (this.elements.get(name) != null) {
            throw new ElementException("Duplicate annotation of name '%s' on %s", name, label);
        }
        if (!this.order.contains(name)) {
            this.order.add(name);
        }
        if (label.isTextList()) {
            this.list = label;
        }
        this.elements.put(name, label);
    }

    public ModelMap getModels() throws Exception {
        return this.models.getModels();
    }

    public LabelMap getAttributes() throws Exception {
        return this.attributes.getLabels();
    }

    public LabelMap getElements() throws Exception {
        return this.elements.getLabels();
    }

    public boolean isModel(String str) {
        return this.models.containsKey(str);
    }

    public boolean isElement(String str) {
        return this.elements.containsKey(str);
    }

    public boolean isAttribute(String str) {
        return this.attributes.containsKey(str);
    }

    public Iterator<String> iterator() {
        List arrayList = new ArrayList();
        Iterator it = this.order.iterator();
        while (it.hasNext()) {
            arrayList.add((String) it.next());
        }
        return arrayList.iterator();
    }

    public void validate(Class cls) throws Exception {
        validateExpressions(cls);
        validateAttributes(cls);
        validateElements(cls);
        validateModels(cls);
        validateText(cls);
    }

    private void validateText(Class cls) throws Exception {
        if (this.text == null) {
            return;
        }
        if (!this.elements.isEmpty()) {
            throw new TextException("Text annotation %s used with elements in %s", this.text, cls);
        } else if (isComposite()) {
            throw new TextException("Text annotation %s can not be used with paths in %s", this.text, cls);
        }
    }

    private void validateExpressions(Class cls) throws Exception {
        cls = this.elements.iterator();
        while (cls.hasNext()) {
            Label label = (Label) cls.next();
            if (label != null) {
                validateExpression(label);
            }
        }
        cls = this.attributes.iterator();
        while (cls.hasNext()) {
            label = (Label) cls.next();
            if (label != null) {
                validateExpression(label);
            }
        }
        if (this.text != null) {
            validateExpression(this.text);
        }
    }

    private void validateExpression(Label label) throws Exception {
        label = label.getExpression();
        if (this.expression != null) {
            if (!this.expression.getPath().equals(label.getPath())) {
                throw new PathException("Path '%s' does not match '%s' in %s", this.expression.getPath(), label.getPath(), this.detail);
            }
            return;
        }
        this.expression = label;
    }

    private void validateModels(Class cls) throws Exception {
        Iterator it = this.models.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ModelList) it.next()).iterator();
            int i = 1;
            while (it2.hasNext()) {
                Model model = (Model) it2.next();
                if (model != null) {
                    String name = model.getName();
                    int i2 = i + 1;
                    if (model.getIndex() != i) {
                        throw new ElementException("Path section '%s[%s]' is out of sequence in %s", name, Integer.valueOf(model.getIndex()), cls);
                    } else {
                        model.validate(cls);
                        i = i2;
                    }
                }
            }
        }
    }

    private void validateAttributes(Class cls) throws Exception {
        for (String str : this.attributes.keySet()) {
            if (((Label) this.attributes.get(str)) == null) {
                throw new AttributeException("Ordered attribute '%s' does not exist in %s", str, cls);
            } else if (this.expression != null) {
                this.expression.getAttribute(str);
            }
        }
    }

    private void validateElements(Class cls) throws Exception {
        for (String str : this.elements.keySet()) {
            ModelList modelList = (ModelList) this.models.get(str);
            Label label = (Label) this.elements.get(str);
            if (modelList == null && label == null) {
                throw new ElementException("Ordered element '%s' does not exist in %s", str, cls);
            } else if (modelList != null && label != null && !modelList.isEmpty()) {
                throw new ElementException("Element '%s' is also a path name in %s", str, cls);
            } else if (this.expression != null) {
                this.expression.getElement(str);
            }
        }
    }

    public void register(Label label) throws Exception {
        if (label.isAttribute()) {
            registerAttribute(label);
        } else if (label.isText()) {
            registerText(label);
        } else {
            registerElement(label);
        }
    }

    public Model lookup(String str, int i) {
        return this.models.lookup(str, i);
    }

    public Model register(String str, String str2, int i) throws Exception {
        Model lookup = this.models.lookup(str, i);
        return lookup == null ? create(str, str2, i) : lookup;
    }

    private Model create(String str, String str2, int i) throws Exception {
        Model treeModel = new TreeModel(this.policy, this.detail, str, str2, i);
        if (str != null) {
            this.models.register(str, treeModel);
            this.order.add(str);
        }
        return treeModel;
    }

    public boolean isComposite() {
        Iterator it = this.models.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ModelList) it.next()).iterator();
            while (it2.hasNext()) {
                Model model = (Model) it2.next();
                if (model != null && !model.isEmpty()) {
                    return true;
                }
            }
        }
        return this.models.isEmpty() ^ true;
    }

    public boolean isEmpty() {
        if (this.text == null && this.elements.isEmpty() && this.attributes.isEmpty()) {
            return isComposite() ^ 1;
        }
        return false;
    }

    public Label getText() {
        if (this.list != null) {
            return this.list;
        }
        return this.text;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return this.index;
    }

    public String toString() {
        return String.format("model '%s[%s]'", new Object[]{this.name, Integer.valueOf(this.index)});
    }
}
