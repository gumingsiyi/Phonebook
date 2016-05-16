package com.stiles.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.stiles.database.DatabaseHelper;
import com.stiles.model.ContactBean;

import java.util.ArrayList;

/**
 * Created by stiles on 16/5/15.
 */
public class ContactService {
    private SQLiteDatabase Rdb;
    private SQLiteDatabase Wdb;
    public ContactService(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        Rdb = helper.getReadableDatabase();
        Wdb = helper.getWritableDatabase();
    }

    //保存联系人
    public void save(ContactBean bean) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.NAME, bean.getName());
        values.put(DatabaseHelper.PHONENUM, bean.getPhoneNum());
        values.put(DatabaseHelper.EMAIL, bean.getEmail());
        Wdb.insert(DatabaseHelper.TABLENAME, "id", values);
    }

    //查找所有联系人
    public ArrayList<ContactBean> findAll() {
        ArrayList<ContactBean> list = new ArrayList<>();
        Cursor cursor = Rdb.query(DatabaseHelper.TABLENAME, null, null, null, null, null, null);
        int idIndex = cursor.getColumnIndex(DatabaseHelper.ID);
        int nameIndex = cursor.getColumnIndex(DatabaseHelper.NAME);
        int phoneNumIndex = cursor.getColumnIndex(DatabaseHelper.PHONENUM);
        int emailIndex = cursor.getColumnIndex(DatabaseHelper.EMAIL);

        int id;
        String name;
        String phoneNum;
        String email;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id = cursor.getInt(idIndex);
            name = cursor.getString(nameIndex);
            phoneNum = cursor.getString(phoneNumIndex);
            email = cursor.getString(emailIndex);

            ContactBean bean = new ContactBean(id, name, phoneNum, email);
            list.add(bean);
        }

        cursor.close();

        return list;
    }
}
