package com.mevo.app.tools;

import android.text.Editable;
import android.text.TextWatcher;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public class MaskWatcherZipCode implements TextWatcher {
    private boolean isDeleting = false;
    private final String mask;

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public MaskWatcherZipCode(String str) {
        this.mask = str;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.isDeleting = i2 > i3 ? true : null;
    }

    public void afterTextChanged(Editable editable) {
        if (editable.toString().contains(Operation.MINUS) || editable.length() <= 2) {
            if (this.isDeleting && editable.length() > 1 && editable.toString().charAt(editable.length() - 1) == '-') {
                editable.delete(editable.length() - 1, editable.length());
            }
            return;
        }
        editable.insert(2, Operation.MINUS);
    }
}
