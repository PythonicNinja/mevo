package com.mevo.app.presentation.main.transactions;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.TransactionInfo;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;

final /* synthetic */ class TransactionsAdapter$$Lambda$0 implements OnClickListener {
    private final TransactionInfo arg$1;
    private final Context arg$2;

    TransactionsAdapter$$Lambda$0(TransactionInfo transactionInfo, Context context) {
        this.arg$1 = transactionInfo;
        this.arg$2 = context;
    }

    public void onClick(View view) {
        new Builder().setText(this.arg$1.getServiceFeeInfo(this.arg$2)).setPositiveButton((int) C0434R.string.close, null).build().show();
    }
}
