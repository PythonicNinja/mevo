package com.inverce.mod.core.interfaces;

public interface LogListener {
    boolean handleExc(int i, String str, String str2, Throwable th);

    boolean handleMsg(int i, String str, String str2);
}
