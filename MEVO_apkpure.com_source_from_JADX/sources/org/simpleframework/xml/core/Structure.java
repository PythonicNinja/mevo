package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;

class Structure {
    private final Instantiator factory;
    private final Model model;
    private final boolean primitive;
    private final Label text;
    private final Label version;

    public Structure(Instantiator instantiator, Model model, Label label, Label label2, boolean z) {
        this.primitive = z;
        this.factory = instantiator;
        this.version = label;
        this.model = model;
        this.text = label2;
    }

    public Instantiator getInstantiator() {
        return this.factory;
    }

    public Section getSection() {
        return new ModelSection(this.model);
    }

    public boolean isPrimitive() {
        return this.primitive;
    }

    public Version getRevision() {
        return this.version != null ? (Version) this.version.getContact().getAnnotation(Version.class) : null;
    }

    public Label getVersion() {
        return this.version;
    }

    public Label getText() {
        return this.text;
    }
}
