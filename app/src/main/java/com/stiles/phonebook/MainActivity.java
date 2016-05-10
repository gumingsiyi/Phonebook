package com.stiles.phonebook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.stiles.model.ContactBean;
import com.stiles.tools.ContactAdapter;
import com.stiles.tools.PinyinComparator;
import com.stiles.tools.PinyinUtils;
import com.stiles.view.QuickViewIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
    ListView mListView;
    private TextView dialog;
    private QuickViewIndex slideBar;
    private List<ContactBean> data;
    ContactAdapter mContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    //初始化activity中的内容
    private void initView() {
        mListView = (ListView)findViewById(R.id.listView);
        data = getData(getResources().getStringArray(R.array.listPersons));
        Collections.sort(data, new PinyinComparator());
        mContactAdapter = new ContactAdapter(data, this);
        mListView.setAdapter(mContactAdapter);

        dialog = (TextView)findViewById(R.id.dialog);
        slideBar = (QuickViewIndex)findViewById(R.id.slideBar);
        slideBar.setTextView(dialog);
        slideBar.setOnTouchingLetterChangedListener(new QuickViewIndex.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = mContactAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mListView.setSelection(position);
                }
            }
        });

    }

    private List<ContactBean> getData(String[] data) {
        List<ContactBean> listarray = new ArrayList<ContactBean>();
        for (String aData : data) {
            String pinyin = PinyinUtils.getPinyin(aData);
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            ContactBean person = new ContactBean();
            person.setName(aData);
            person.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                person.setFirstPinYin(Fpinyin);
            } else {
                person.setFirstPinYin("#");
            }

            listarray.add(person);
        }

        return listarray;

    }
}
