package com.mevo.app.presentation.custom_views;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.widget.EditText;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public class ZipCodeListener implements TextWatcher {
    private static final int ZIP_CODE_LENGTH = 6;
    private EditText editText;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public ZipCodeListener(EditText editText) {
        this.editText = editText;
        editText.setFilters(new InputFilter[]{new LengthFilter(6)});
    }

    public void afterTextChanged(Editable editable) {
        editable = this.editText.getText().toString();
        if (editable.length() == 3 && editable.charAt(2) != '-' && isNumeric(editable)) {
            performChange(editable);
        }
    }

    private static boolean isNumeric(java.lang.String r0) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        java.lang.Double.parseDouble(r0);	 Catch:{ NumberFormatException -> 0x0005 }
        r0 = 1;
        return r0;
    L_0x0005:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.presentation.custom_views.ZipCodeListener.isNumeric(java.lang.String):boolean");
    }

    private void performChange(String str) {
        this.editText.setText(str.substring(0, 2).concat(Operation.MINUS).concat(str.substring(2, str.length())));
        this.editText.setSelection(this.editText.length());
    }
}
