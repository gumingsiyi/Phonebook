package com.stiles.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import com.stiles.model.ContactBean;
import com.stiles.service.ContactService;
import com.stiles.tools.ContactAdapter;
import com.stiles.tools.PinyinComparator;
import com.stiles.tools.PinyinUtils;
import com.stiles.view.QuickViewIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int ADD_CONTACT_CODE = 1;
    private ListView mListView;
    private TextView dialog;
    private QuickViewIndex slideBar;
    private List<ContactBean> data;
    private ContactAdapter mContactAdapter;
    ContactService contactService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactService = new ContactService(getBaseContext());
        initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.exit) {
            finish();
            return true;
        } else if (id == R.id.add) {
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), AddContactActivity.class);
            startActivityForResult(intent, ADD_CONTACT_CODE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onResume() {
        super.onResume();
        data = getData(contactService.findAll());
        Collections.sort(data, new PinyinComparator());
        mContactAdapter = new ContactAdapter(data, this);
        mListView.setAdapter(mContactAdapter);
    }

    //初始化activity中的内容
    private void initView() {
        mListView = (ListView)findViewById(R.id.listView);
        data = getData(contactService.findAll());
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

    private List<ContactBean> getData(List<ContactBean> data) {
        List<ContactBean> list = new ArrayList<>();
        for (ContactBean aData : data) {
            String pinyin = PinyinUtils.getPinyin(aData.getName());
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();
            aData.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                aData.setFirstPinYin(Fpinyin);
            } else {
                aData.setFirstPinYin("#");
            }

            list.add(aData);
        }

        return list;
    }
}
