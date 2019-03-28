package com.mevo.app.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.LanguageCode;
import java.util.ArrayList;
import java.util.List;

public class LanguageAdapter extends BaseAdapter {
    private List<LanguageCode> data;

    private static class ViewHolder {
        TextView countryName;

        private ViewHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public LanguageAdapter(List<LanguageCode> list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.data = list;
    }

    public void setData(List<LanguageCode> list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.data = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.data.size();
    }

    public LanguageCode getItem(int i) {
        return (LanguageCode) this.data.get(i);
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
        viewGroup.countryName.setText(i.getName());
        return view;
    }

    public int getLanguageIndex(String str) {
        for (int i = 0; i < this.data.size(); i++) {
            if (((LanguageCode) this.data.get(i)).getCode().equals(str)) {
                return i;
            }
        }
        return 0;
    }
}
