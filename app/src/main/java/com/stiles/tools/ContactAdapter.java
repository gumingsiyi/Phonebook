package com.stiles.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;
import com.stiles.model.ContactBean;
import com.stiles.phonebook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stiles on 16/5/9.
 */
public class ContactAdapter extends BaseAdapter implements SectionIndexer {
    private List<ContactBean> data;
    private Context mContext;

    public ContactAdapter(List<ContactBean> data, Context context) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        mContext = context;
    }

    private class ViewHolder {
        TextView nickname;
        TextView tag;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ContactBean contactBean = data.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.contact_item, null);
            viewHolder.nickname = (TextView) view.findViewById(R.id.nickname);
            viewHolder.tag = (TextView) view.findViewById(R.id.tag);
            view.setTag(viewHolder);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "hello",Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            viewHolder.tag.setVisibility(View.VISIBLE);
            viewHolder.tag.setText(contactBean.getFirstPinYin());
        } else {
            viewHolder.tag.setVisibility(View.GONE);
        }
        viewHolder.nickname.setText(data.get(position).getName());

        return view;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getSectionForPosition(int position) {
        return data.get(position).getFirstPinYin().charAt(0);
    }

    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < data.size(); i++) {
            String Fpinyin = data.get(i).getFirstPinYin();
            char first = Fpinyin.toUpperCase().charAt(0);
            if (first == section) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public int getCount() {
        return data.size();
    }
}
