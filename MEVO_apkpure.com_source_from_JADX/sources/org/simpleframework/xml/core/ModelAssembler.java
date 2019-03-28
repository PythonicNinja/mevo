package org.simpleframework.xml.core;

import org.simpleframework.xml.Order;
import org.simpleframework.xml.stream.Format;

class ModelAssembler {
    private final ExpressionBuilder builder;
    private final Detail detail;
    private final Format format;

    public ModelAssembler(ExpressionBuilder expressionBuilder, Detail detail, Support support) throws Exception {
        this.format = support.getFormat();
        this.builder = expressionBuilder;
        this.detail = detail;
    }

    public void assemble(Model model, Order order) throws Exception {
        assembleElements(model, order);
        assembleAttributes(model, order);
    }

    private void assembleElements(Model model, Order order) throws Exception {
        order = order.elements();
        int length = order.length;
        int i = 0;
        while (i < length) {
            Expression build = this.builder.build(order[i]);
            if (build.isAttribute()) {
                throw new PathException("Ordered element '%s' references an attribute in %s", build, this.detail);
            } else {
                registerElements(model, build);
                i++;
            }
        }
    }

    private void assembleAttributes(Model model, Order order) throws Exception {
        order = order.attributes();
        int length = order.length;
        int i = 0;
        while (i < length) {
            String str = order[i];
            Expression build = this.builder.build(str);
            if (build.isAttribute() || !build.isPath()) {
                if (build.isPath()) {
                    registerAttributes(model, build);
                } else {
                    model.registerAttribute(this.format.getStyle().getAttribute(str));
                }
                i++;
            } else {
                throw new PathException("Ordered attribute '%s' references an element in %s", build, this.detail);
            }
        }
    }

    private void registerAttributes(Model model, Expression expression) throws Exception {
        String prefix = expression.getPrefix();
        String first = expression.getFirst();
        int index = expression.getIndex();
        if (expression.isPath()) {
            model = model.register(first, prefix, index);
            expression = expression.getPath(1);
            if (model == null) {
                throw new PathException("Element '%s' does not exist in %s", first, this.detail);
            } else {
                registerAttributes(model, expression);
                return;
            }
        }
        registerAttribute(model, expression);
    }

    private void registerAttribute(Model model, Expression expression) throws Exception {
        String first = expression.getFirst();
        if (first != null) {
            model.registerAttribute(first);
        }
    }

    private void registerElements(Model model, Expression expression) throws Exception {
        String prefix = expression.getPrefix();
        String first = expression.getFirst();
        int index = expression.getIndex();
        if (first != null) {
            Model register = model.register(first, prefix, index);
            Expression path = expression.getPath(1);
            if (expression.isPath()) {
                registerElements(register, path);
            }
        }
        registerElement(model, expression);
    }

    private void registerElement(Model model, Expression expression) throws Exception {
        String prefix = expression.getPrefix();
        String first = expression.getFirst();
        int index = expression.getIndex();
        if (index <= 1 || model.lookup(first, index - 1) != null) {
            model.register(first, prefix, index);
        } else {
            throw new PathException("Ordered element '%s' in path '%s' is out of sequence for %s", first, expression, this.detail);
        }
    }
}
