package com.mevo.app.presentation.main.transactions;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.annimon.stream.Stream;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.AccountHistory;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.presentation.main.MainFragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TransactionsFragment extends MainFragment {
    private TransactionsAdapter adapter;
    private TextView noDataTxt;
    private ProgressBar progressBar;
    private View separator;
    private RecyclerView settlementRv;

    public static TransactionsFragment newInstance() {
        return new TransactionsFragment();
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_transactions, viewGroup, false);
        findViews(layoutInflater);
        setupRecycler();
        fetchData();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.settlementRv = (RecyclerView) view.findViewById(C0434R.id.fragmet_transactions_recycler);
        this.progressBar = (ProgressBar) view.findViewById(C0434R.id.fragment_transactions_progress);
        this.noDataTxt = (TextView) view.findViewById(C0434R.id.fragment_transactions_no_data_txt);
        this.separator = view.findViewById(C0434R.id.fragment_transactions_separator);
    }

    private void setupRecycler() {
        this.adapter = new TransactionsAdapter();
        this.settlementRv.setLayoutManager(new LinearLayoutManager(getContext()));
        this.settlementRv.setAdapter(this.adapter);
    }

    private void fetchData() {
        this.progressBar.setVisibility(0);
        new NextbikeApiRepository().getTransactionsHistory(new TransactionsFragment$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$fetchData$284$TransactionsFragment(Pair pair, boolean z, Exception exception) {
        exception = new ArrayList();
        Collection toList = Stream.of(pair.first != null ? ((AccountHistory) pair.first).getBookingItems() : new ArrayList()).filter(TransactionsFragment$$Lambda$1.$instance).toList();
        if (z) {
            exception.addAll(((AccountHistory) pair.first).getPaymentItems());
            exception.addAll(((AccountHistory) pair.first).getRentalItems());
            exception.addAll(toList);
            exception.addAll(((AccountHistory) pair.first).getTransactionHistoryItems());
            exception.addAll(((AccountHistory) pair.first).getVoucherHistoryItems());
            Collections.sort(exception, TransactionsFragment$$Lambda$2.$instance);
        }
        int i = 8;
        this.progressBar.setVisibility(8);
        this.adapter.setSubscriptionTimeRanges((List) pair.second);
        this.adapter.setData(exception);
        this.noDataTxt.setVisibility(exception.isEmpty() ? false : true);
        this.settlementRv.setVisibility(!exception.isEmpty() ? false : true);
        pair = this.separator;
        if (!exception.isEmpty()) {
            i = 0;
        }
        pair.setVisibility(i);
    }

    static final /* synthetic */ boolean lambda$null$282$TransactionsFragment(BookingItem bookingItem) {
        if (bookingItem.getPrice() <= 0) {
            if (bookingItem.getUsedTariffSeconds() <= 0) {
                return null;
            }
        }
        return true;
    }
}
