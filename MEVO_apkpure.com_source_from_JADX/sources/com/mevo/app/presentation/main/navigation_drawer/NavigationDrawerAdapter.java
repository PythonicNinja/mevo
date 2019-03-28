package com.mevo.app.presentation.main.navigation_drawer;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.main.navigation_drawer.NavigationDrawer.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerAdapter extends BaseAdapter {
    private List<MenuItem> menuItems;

    private static class ViewHolder {
        public TextView name;
        public ImageView picture;

        private ViewHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public NavigationDrawerAdapter(List<MenuItem> list) {
        setMenuItems(list);
    }

    public void setMenuItems(List<MenuItem> list) {
        if (list == null) {
            list = new ArrayList();
        }
        int i = 0;
        while (i < list.size()) {
            int i2 = 0;
            while (i2 < list.size()) {
                if (i == i2 || ((MenuItem) list.get(i2)).getId() != ((MenuItem) list.get(i)).getId()) {
                    i2++;
                } else {
                    throw new RuntimeException("Ids of menu items should be unique!");
                }
            }
            i++;
        }
        this.menuItems = list;
    }

    public int getCount() {
        return this.menuItems.size();
    }

    public MenuItem getItem(int i) {
        return (MenuItem) this.menuItems.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        i = getItem(i);
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C0434R.layout.list_item_navigation_drawer_menu, null);
            viewGroup = new ViewHolder();
            viewGroup.name = (TextView) view.findViewById(C0434R.id.list_item_navigation_drawer_menu_name);
            viewGroup.picture = (ImageView) view.findViewById(C0434R.id.list_item_navigation_drawer_menu_picture);
            view.setTag(viewGroup);
        } else {
            viewGroup = (ViewHolder) view.getTag();
        }
        viewGroup.name.setText(i.getStringRes());
        viewGroup.picture.setImageDrawable(ContextCompat.getDrawable(IM.context(), i.getDrawableRes()));
        return view;
    }
}
