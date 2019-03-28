package org.simpleframework.xml.strategy;

import org.simpleframework.xml.util.WeakCache;

class WriteState extends WeakCache<WriteGraph> {
    private Contract contract;

    public WriteState(Contract contract) {
        this.contract = contract;
    }

    public WriteGraph find(Object obj) {
        WriteGraph writeGraph = (WriteGraph) fetch(obj);
        if (writeGraph != null) {
            return writeGraph;
        }
        writeGraph = new WriteGraph(this.contract);
        cache(obj, writeGraph);
        return writeGraph;
    }
}
