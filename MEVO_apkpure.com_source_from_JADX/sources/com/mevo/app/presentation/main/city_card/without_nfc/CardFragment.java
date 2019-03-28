package com.mevo.app.presentation.main.city_card.without_nfc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.data.repository.UserRepository;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.tools.SpanUtils;

public class CardFragment extends MainFragment {
    private static final String KEY_NUMBER = "KEY_NUMBER";
    private Button addCardBt;
    private TextView cardNumberTv;
    private Button removeCardBt;

    public static CardFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_NUMBER, str);
        str = new CardFragment();
        str.setArguments(bundle);
        return str;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_cart, viewGroup, false);
        findViews(layoutInflater);
        setCardNumber();
        this.removeCardBt.setOnClickListener(new CardFragment$$Lambda$0(this));
        this.addCardBt.setOnClickListener(new CardFragment$$Lambda$1(this));
        return layoutInflater;
    }

    final /* synthetic */ void lambda$onCreateView$182$CardFragment(View view) {
        new Builder().setText((int) C0434R.string.do_you_want_to_remove).setNegativeButton((int) C0434R.string.no, null).setPositiveButton((int) C0434R.string.yes, new CardFragment$$Lambda$3(this)).build().show();
    }

    final /* synthetic */ void lambda$null$181$CardFragment(View view) {
        onCardRemove();
    }

    final /* synthetic */ void lambda$onCreateView$183$CardFragment(View view) {
        if (this.activityInterface != null) {
            this.activityInterface.changeFragment(CityCardAddFragment.newInstance());
        }
    }

    private void setCardNumber() {
        String str = "";
        if (getArguments() != null) {
            str = getArguments().getString(KEY_NUMBER, "");
        }
        SpanUtils.on(this.cardNumberTv).boldText(str).done();
    }

    private void onCardRemove() {
        if (getActivityInterface() != null) {
            getActivityInterface().setProgressViewVisibility(true);
        }
        new UserRepository().removeCardNumber(new CardFragment$$Lambda$2(this));
    }

    final /* synthetic */ void lambda$onCardRemove$184$CardFragment(boolean z, Exception exception) {
        if (getActivityInterface()) {
            getActivityInterface().setProgressViewVisibility(null);
            getActivityInterface().popFragment();
            getActivityInterface().changeFragment(CityCardAddFragment.newInstance());
        }
    }

    private void findViews(View view) {
        this.addCardBt = (Button) view.findViewById(C0434R.id.add_card_button);
        this.removeCardBt = (Button) view.findViewById(C0434R.id.remove_card_button);
        this.cardNumberTv = (TextView) view.findViewById(C0434R.id.card_number);
    }
}
