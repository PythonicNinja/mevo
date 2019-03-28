package com.mevo.app.presentation.register.register_step_two;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.AgreementWithConsent;
import java.util.List;

public class RegisterAgreementsAdapter extends Adapter<RegisterAgreementViewHolder> {
    private List<AgreementWithConsent> data;

    class RegisterAgreementViewHolder extends ViewHolder {
        CheckBox checkBox;
        TextView info;

        public RegisterAgreementViewHolder(View view) {
            super(view);
            this.checkBox = (CheckBox) view.findViewById(C0434R.id.item_register_agreement_checkbox);
            this.info = (TextView) view.findViewById(C0434R.id.item_register_agreement_info);
        }

        public void bind(AgreementWithConsent agreementWithConsent, int i) {
            this.checkBox.setChecked(agreementWithConsent.isConsent());
            this.info.setText(agreementWithConsent.getRegisterAgreement().agreementContentString);
            this.info.setMovementMethod(LinkMovementMethod.getInstance());
            this.checkBox.setOnCheckedChangeListener(new RegisterAgreementsAdapter$RegisterAgreementViewHolder$$Lambda$0(this, i));
        }

        /* renamed from: lambda$bind$325$RegisterAgreementsAdapter$RegisterAgreementViewHolder */
        final /* synthetic */ void m116xef9cf2bf(int i, CompoundButton compoundButton, boolean z) {
            ((AgreementWithConsent) RegisterAgreementsAdapter.this.data.get(i)).setConsent(z);
        }
    }

    public RegisterAgreementsAdapter(@NonNull List<AgreementWithConsent> list) {
        this.data = list;
    }

    public List<AgreementWithConsent> getData() {
        return this.data;
    }

    @NonNull
    public RegisterAgreementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RegisterAgreementViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0434R.layout.item_register_agreement, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull RegisterAgreementViewHolder registerAgreementViewHolder, int i) {
        registerAgreementViewHolder.bind((AgreementWithConsent) this.data.get(i), i);
    }

    public int getItemCount() {
        return this.data.size();
    }
}
