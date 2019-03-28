package com.inverce.mod.core.collections;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface TraversalMethod {
    public static final int ASC = 18;
    public static final int BFS = 9;
    public static final int DESC = 34;
    public static final int DFS = 5;
}
