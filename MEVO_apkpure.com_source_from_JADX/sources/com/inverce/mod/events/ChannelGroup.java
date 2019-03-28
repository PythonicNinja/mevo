package com.inverce.mod.events;

import android.util.SparseArray;

public class ChannelGroup {
    protected SparseArray<Channel> channels;
    protected final boolean useWeekEvents;

    public ChannelGroup() {
        this(true);
    }

    public ChannelGroup(boolean z) {
        this.useWeekEvents = z;
        this.channels = new SparseArray();
    }

    public Channel on(int i) {
        Channel channel = (Channel) this.channels.get(i);
        if (channel != null) {
            return channel;
        }
        SparseArray sparseArray = this.channels;
        Channel channel2 = new Channel(this.useWeekEvents);
        sparseArray.put(i, channel2);
        return channel2;
    }
}
