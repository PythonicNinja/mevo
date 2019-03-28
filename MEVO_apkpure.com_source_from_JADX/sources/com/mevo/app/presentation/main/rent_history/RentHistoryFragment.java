package com.mevo.app.presentation.main.rent_history;

import android.os.Bundle;
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
import com.mevo.app.Cfg;
import com.mevo.app.data.model.RentalDistance;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.RentalItem.ReversedStartDateComparator;
import com.mevo.app.data.model.RentalItem.StartDateComparatorWithRentalRoute;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.response.ResponseHistory;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.DirectionsRepository;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.presentation.main.rent_history.RentHistoryAdapter.HistoryRentalItemViewModel;
import com.mevo.app.presentation.main.rent_history.RentHistoryAdapter.ItemClickListener;
import com.mevo.app.presentation.main.rent_summary.RentSummaryFragment;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.formatters.CurrencyFormatter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentHistoryFragment extends MainFragment implements Callback<ResponseHistory>, ItemClickListener {
    private static final String TAG = "RentHistoryFragment";
    private static final int VALID_RENTAL_MIN_DURATION_SECONDS = 10;
    private RentHistoryAdapter adapter;
    private CallsToken callsToken;
    private TextView noHistory;
    private ProgressBar progressView;
    private RecyclerView recyclerView;
    private View separator;

    public static RentHistoryFragment newInstance() {
        return new RentHistoryFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_rent_history, viewGroup, false);
        findViews(layoutInflater);
        this.callsToken = new CallsToken();
        setAdapters();
        return layoutInflater;
    }

    public void onStart() {
        super.onStart();
        fetchRentHistory();
        this.adapter.setListener(this);
    }

    public void onStop() {
        super.onStop();
        NetTools.cancelSavedOngoingCalls(this.callsToken);
        this.adapter.setListener(null);
    }

    private void findViews(View view) {
        this.recyclerView = (RecyclerView) view.findViewById(C0434R.id.fragment_rent_history_recycler);
        this.progressView = (ProgressBar) view.findViewById(C0434R.id.fragment_rent_history_progress);
        this.separator = view.findViewById(C0434R.id.fragment_rent_history_seperator);
        this.noHistory = (TextView) view.findViewById(C0434R.id.fragment_rent_history_no_history_text);
    }

    private void setAdapters() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new RentHistoryAdapter(null);
        this.recyclerView.setAdapter(this.adapter);
    }

    private void showDataInfo(boolean z) {
        if (z) {
            this.recyclerView.setVisibility(0);
            this.separator.setVisibility(0);
            this.noHistory.setVisibility(8);
            return;
        }
        this.recyclerView.setVisibility(8);
        this.separator.setVisibility(8);
        this.noHistory.setVisibility(0);
    }

    private void fetchRentHistory() {
        User user = UserManager.getUser();
        if (user != null) {
            this.progressView.setVisibility(0);
            NetTools.saveCall(this.callsToken, Rest.getApiNextbike().fetchHistory(Cfg.get().apikeyNextbike(), user.getLoginkey(), Integer.valueOf(1000), Integer.valueOf(0))).enqueue(this);
        }
    }

    public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
        if (response.isSuccessful() == null || response.body() == null) {
            this.progressView.setVisibility(8);
            onHistoryCallFailed();
            return;
        }
        onHistoryReceived((ResponseHistory) response.body());
    }

    public void onFailure(Call<ResponseHistory> call, Throwable th) {
        if (call.isCanceled() == null) {
            onHistoryCallFailed();
            showDataInfo(null);
        }
        this.progressView.setVisibility(8);
    }

    private void onHistoryReceived(ResponseHistory responseHistory) {
        if (responseHistory.accountHistory != null) {
            if (responseHistory.user != null) {
                responseHistory = Stream.of(responseHistory.accountHistory.getRentalItems()).filter(RentHistoryFragment$$Lambda$0.$instance).filter(RentHistoryFragment$$Lambda$1.$instance).toList();
                new DirectionsRepository().getDistancesForRentalItemsNew(responseHistory, new RentHistoryFragment$$Lambda$2(this, responseHistory));
                return;
            }
        }
        showDataInfo(null);
        this.progressView.setVisibility(8);
    }

    static final /* synthetic */ boolean lambda$onHistoryReceived$237$RentHistoryFragment(RentalItem rentalItem) {
        return rentalItem.getDurationSeconds() > 10 ? true : null;
    }

    final /* synthetic */ void lambda$onHistoryReceived$240$RentHistoryFragment(List list, List list2, boolean z, Exception exception) {
        this.progressView.setVisibility(8);
        exception = UserManager.getUser() != null ? CurrencyFormatter.formatCurrencyAbbreviation(UserManager.getUser().getCurrency()) : "";
        if (!z || list2.isEmpty()) {
            list = Stream.of((Iterable) list).sorted(new ReversedStartDateComparator()).map(new RentHistoryFragment$$Lambda$4(this, exception)).toList();
        } else {
            list = Stream.of((Iterable) list2).sorted(new StartDateComparatorWithRentalRoute()).map(new RentHistoryFragment$$Lambda$3(this, exception)).toList();
        }
        showDataInfo(list.isEmpty() ^ 1);
        this.adapter.setData(list);
    }

    final /* synthetic */ HistoryRentalItemViewModel lambda$null$238$RentHistoryFragment(String str, Pair pair) {
        return HistoryRentalItemViewModel.create((RentalItem) pair.first, (RentalDistance) pair.second, str, getContext());
    }

    final /* synthetic */ HistoryRentalItemViewModel lambda$null$239$RentHistoryFragment(String str, RentalItem rentalItem) {
        return HistoryRentalItemViewModel.create(rentalItem, null, str, getContext());
    }

    private void onHistoryCallFailed() {
        TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_SHORT);
    }

    public void onRentHistoryItemClick(RentalItem rentalItem, RentalDistance rentalDistance) {
        if (rentalItem.isReturned() != null) {
            getActivityInterface().changeFragment(RentSummaryFragment.newInstance(rentalItem));
        }
    }
}
