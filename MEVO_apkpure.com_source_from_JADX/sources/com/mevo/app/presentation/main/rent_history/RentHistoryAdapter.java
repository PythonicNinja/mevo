package com.mevo.app.presentation.main.rent_history;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.constants.BikeTypes;
import com.mevo.app.data.model.RentalDistance;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.tools.Tools;
import com.mevo.app.tools.formatters.Formatter;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.util.ArrayList;
import java.util.List;

public class RentHistoryAdapter extends Adapter<ViewHolder> {
    private List<HistoryRentalItemViewModel> data;
    private ItemClickListener listener;

    public static class HistoryRentalItemViewModel {
        String bikeNumber;
        String bikeType;
        String cost;
        String distance;
        String rentDate;
        String rentTime;
        RentalDistance rentalDistance;
        RentalItem rentalItem;
        String serviceFee;
        int serviceFeeCents;
        String serviceFeeInfo;

        public static HistoryRentalItemViewModel create(RentalItem rentalItem, @Nullable RentalDistance rentalDistance, String str, Context context) {
            long j;
            String str2;
            String bikesType;
            String formatDurationRent;
            String str3;
            String formatDurationStartEndDates;
            int serviceFee;
            String serviceFeeInfo;
            Context context2;
            Object[] objArr;
            RentalItem rentalItem2 = rentalItem;
            String str4 = str;
            if (rentalItem2.endTimestampSeconds > 0) {
                j = rentalItem2.endTimestampSeconds;
            } else {
                j = System.currentTimeMillis() / 1000;
            }
            String formatMoneyFromCents = rentalItem.getServiceFee() != 0 ? Formatter.formatMoneyFromCents((double) Math.abs(rentalItem.getServiceFee())) : null;
            String formatMoney = Formatter.formatMoney(rentalItem);
            if (!TextUtils.isEmpty(str)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(formatMoney);
                stringBuilder.append(" ");
                stringBuilder.append(str4);
                formatMoney = stringBuilder.toString();
                if (!TextUtils.isEmpty(formatMoneyFromCents)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(formatMoneyFromCents);
                    stringBuilder.append(" ");
                    stringBuilder.append(str4);
                    formatMoneyFromCents = formatMoney;
                    formatMoney = stringBuilder.toString();
                    str2 = rentalItem2.bikeNumber;
                    bikesType = Tools.getBikesType(BikeTypes.getBikeGroupByBikeType(rentalItem2.bikeType));
                    formatDurationRent = Formatter.formatDurationRent(j - rentalItem2.startTimestampSeconds, IM.context());
                    str3 = formatMoneyFromCents;
                    formatDurationStartEndDates = Formatter.formatDurationStartEndDates(rentalItem2.startTimestampSeconds * 1000, rentalItem2.endTimestampSeconds * 1000);
                    serviceFee = rentalItem.getServiceFee();
                    serviceFeeInfo = rentalItem2.getServiceFeeInfo(context);
                    context2 = IM.context();
                    objArr = new Object[1];
                    objArr[0] = rentalDistance == null ? Formatter.formatDistance(rentalDistance.getDistanceMeters()) : Operation.MINUS;
                    return new HistoryRentalItemViewModel(str2, bikesType, formatDurationRent, formatDurationStartEndDates, str3, formatMoney, serviceFee, serviceFeeInfo, context2.getString(C0434R.string.rent_history_distance, objArr), rentalItem2, rentalDistance);
                }
            }
            String str5 = formatMoney;
            formatMoney = formatMoneyFromCents;
            formatMoneyFromCents = str5;
            str2 = rentalItem2.bikeNumber;
            bikesType = Tools.getBikesType(BikeTypes.getBikeGroupByBikeType(rentalItem2.bikeType));
            formatDurationRent = Formatter.formatDurationRent(j - rentalItem2.startTimestampSeconds, IM.context());
            str3 = formatMoneyFromCents;
            formatDurationStartEndDates = Formatter.formatDurationStartEndDates(rentalItem2.startTimestampSeconds * 1000, rentalItem2.endTimestampSeconds * 1000);
            serviceFee = rentalItem.getServiceFee();
            serviceFeeInfo = rentalItem2.getServiceFeeInfo(context);
            context2 = IM.context();
            objArr = new Object[1];
            if (rentalDistance == null) {
            }
            objArr[0] = rentalDistance == null ? Formatter.formatDistance(rentalDistance.getDistanceMeters()) : Operation.MINUS;
            return new HistoryRentalItemViewModel(str2, bikesType, formatDurationRent, formatDurationStartEndDates, str3, formatMoney, serviceFee, serviceFeeInfo, context2.getString(C0434R.string.rent_history_distance, objArr), rentalItem2, rentalDistance);
        }

        public HistoryRentalItemViewModel(String str, String str2, String str3, String str4, String str5, String str6, int i, String str7, String str8, RentalItem rentalItem, RentalDistance rentalDistance) {
            this.bikeNumber = str;
            this.bikeType = str2;
            this.rentTime = str3;
            this.rentDate = str4;
            this.cost = str5;
            this.serviceFee = str6;
            this.serviceFeeCents = i;
            this.serviceFeeInfo = str7;
            this.distance = str8;
            this.rentalItem = rentalItem;
            this.rentalDistance = rentalDistance;
        }
    }

    public interface ItemClickListener {
        void onRentHistoryItemClick(RentalItem rentalItem, RentalDistance rentalDistance);
    }

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private TextView bikeNumber;
        private TextView bikeType;
        private View container;
        private TextView costA;
        private TextView costB;
        private ImageView costInfoBtn;
        private TextView date;
        private TextView distance;
        private TextView time;

        public ViewHolder(View view) {
            super(view);
            this.date = (TextView) view.findViewById(C0434R.id.item_rent_history_date);
            this.bikeType = (TextView) view.findViewById(C0434R.id.item_rent_history_bike_type);
            this.bikeNumber = (TextView) view.findViewById(C0434R.id.item_rent_history_bike_number);
            this.time = (TextView) view.findViewById(C0434R.id.item_rent_history_time);
            this.costA = (TextView) view.findViewById(C0434R.id.item_rent_history_cost_a);
            this.costB = (TextView) view.findViewById(C0434R.id.item_rent_history_cost_b);
            this.costInfoBtn = (ImageView) view.findViewById(C0434R.id.item_rent_history_cost_info_btn);
            this.distance = (TextView) view.findViewById(C0434R.id.item_rent_history_distance);
            this.container = view.findViewById(C0434R.id.list_item_main_rent_history_root);
        }

        public void bindData(HistoryRentalItemViewModel historyRentalItemViewModel) {
            Context context = this.bikeNumber.getContext();
            this.bikeNumber.setText(historyRentalItemViewModel.bikeNumber);
            this.date.setText(historyRentalItemViewModel.rentDate);
            this.bikeType.setText(historyRentalItemViewModel.bikeType);
            this.time.setText(historyRentalItemViewModel.rentTime);
            CharSequence string;
            if (Math.abs(historyRentalItemViewModel.serviceFeeCents) == 200) {
                this.costA.setVisibility(0);
                CharSequence string2 = context.getString(C0434R.string.item_rent_history_cost, new Object[]{historyRentalItemViewModel.cost});
                string = context.getString(C0434R.string.rent_official_station_premium_return, new Object[]{historyRentalItemViewModel.serviceFee});
                this.costA.setText(string2);
                this.costB.setText(string);
            } else {
                this.costA.setVisibility(8);
                if (historyRentalItemViewModel.serviceFee == null) {
                    string = context.getString(C0434R.string.item_rent_history_cost, new Object[]{historyRentalItemViewModel.cost});
                } else {
                    string = context.getString(C0434R.string.item_rent_hisotry_cost_with_service_fee, new Object[]{historyRentalItemViewModel.cost, historyRentalItemViewModel.serviceFee});
                }
                this.costB.setText(string);
            }
            if (TextUtils.isEmpty(historyRentalItemViewModel.serviceFeeInfo)) {
                this.costInfoBtn.setVisibility(8);
                this.costInfoBtn.setOnClickListener(null);
            } else {
                this.costInfoBtn.setVisibility(0);
                this.costInfoBtn.setOnClickListener(new RentHistoryAdapter$ViewHolder$$Lambda$0(historyRentalItemViewModel));
            }
            this.distance.setText(historyRentalItemViewModel.distance);
            this.container.setOnClickListener(new RentHistoryAdapter$ViewHolder$$Lambda$1(this, historyRentalItemViewModel));
        }

        final /* synthetic */ void lambda$bindData$236$RentHistoryAdapter$ViewHolder(HistoryRentalItemViewModel historyRentalItemViewModel, View view) {
            if (RentHistoryAdapter.this.listener != null) {
                RentHistoryAdapter.this.listener.onRentHistoryItemClick(historyRentalItemViewModel.rentalItem, historyRentalItemViewModel.rentalDistance);
            }
        }
    }

    public RentHistoryAdapter(List<HistoryRentalItemViewModel> list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.data = list;
    }

    public void setData(List<HistoryRentalItemViewModel> list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.data = list;
        notifyDataSetChanged();
    }

    public RentHistoryAdapter setListener(ItemClickListener itemClickListener) {
        this.listener = itemClickListener;
        return this;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0434R.layout.item_rent_history, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData((HistoryRentalItemViewModel) this.data.get(i));
    }

    public int getItemCount() {
        return this.data.size();
    }
}
