package com.mevo.app.data.model;

import java.util.Comparator;

public interface StartEndItem {

    public static class StartComparator implements Comparator<StartEndItem> {
        public int compare(StartEndItem startEndItem, StartEndItem startEndItem2) {
            return -Long.compare(startEndItem.getStartTsSeconds(), startEndItem2.getStartTsSeconds());
        }
    }

    long getEndTsSeconds();

    long getStartTsSeconds();
}
