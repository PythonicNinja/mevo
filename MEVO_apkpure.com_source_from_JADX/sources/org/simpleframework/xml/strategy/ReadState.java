package org.simpleframework.xml.strategy;

import org.simpleframework.xml.util.WeakCache;

class ReadState extends WeakCache<ReadGraph> {
    private final Contract contract;
    private final Loader loader = new Loader();

    public ReadState(Contract contract) {
        this.contract = contract;
    }

    public ReadGraph find(Object obj) throws Exception {
        ReadGraph readGraph = (ReadGraph) fetch(obj);
        if (readGraph != null) {
            return readGraph;
        }
        return create(obj);
    }

    private ReadGraph create(Object obj) throws Exception {
        ReadGraph readGraph = (ReadGraph) fetch(obj);
        if (readGraph != null) {
            return readGraph;
        }
        readGraph = new ReadGraph(this.contract, this.loader);
        cache(obj, readGraph);
        return readGraph;
    }
}
