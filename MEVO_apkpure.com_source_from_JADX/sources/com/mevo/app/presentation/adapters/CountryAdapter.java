package com.mevo.app.presentation.adapters;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.CountryCode;
import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends BaseAdapter {
    private List<CountryCode> data;

    private static class ViewHolder {
        TextView countryName;

        private ViewHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public CountryAdapter(List<CountryCode> list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.data = list;
    }

    public void setData(List<CountryCode> list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.data = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.data.size();
    }

    public CountryCode getItem(int i) {
        return (CountryCode) this.data.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        i = getItem(i);
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C0434R.layout.spinner_item_valid, viewGroup, false);
            viewGroup = new ViewHolder();
            viewGroup.countryName = (TextView) view.findViewById(C0434R.id.spinner_item_valid_text);
            view.setTag(viewGroup);
        } else {
            viewGroup = (ViewHolder) view.getTag();
        }
        if (i.getCode().equals("1")) {
            viewGroup.countryName.setTextColor(ContextCompat.getColor(IM.context(), C0434R.color.hintColor));
            viewGroup.countryName.setTextSize(18.0f);
        } else {
            viewGroup.countryName.setTextColor(ContextCompat.getColor(IM.context(), C0434R.color.textInput));
        }
        viewGroup.countryName.setText(i.getName());
        return view;
    }

    public int getCountryIndex(String str) {
        for (int i = 0; i < this.data.size(); i++) {
            if (((CountryCode) this.data.get(i)).getCode().equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        i = getItem(i);
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C0434R.layout.spinner_item_valid, viewGroup, false);
            viewGroup = new ViewHolder();
            viewGroup.countryName = (TextView) view.findViewById(C0434R.id.spinner_item_valid_text);
            view.setTag(viewGroup);
        } else {
            viewGroup = (ViewHolder) view.getTag();
        }
        if (i.getCode().equals("1")) {
            viewGroup.countryName.setVisibility(8);
        } else {
            viewGroup.countryName.setVisibility(0);
        }
        viewGroup.countryName.setText(i.getName());
        return view;
    }
}
