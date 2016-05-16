package com.stiles.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.stiles.model.ContactBean;
import com.stiles.service.ContactService;

/**
 * Created by stiles on 16/5/16.
 */
public class ShowContactActivity extends Activity {
    private EditText nameText;
    private EditText phoneText;
    private EditText emailText;
    private ContactService contactService;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_contact_activity);
        contactService = new ContactService(getBaseContext());
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        nameText = (EditText) findViewById(R.id.show_name);
        phoneText = (EditText) findViewById(R.id.show_phoneNum);
        emailText = (EditText) findViewById(R.id.show_email);
        ContactBean bean = contactService.findById(id);

        if (bean == null) {
            Toast.makeText(ShowContactActivity.this, "参数错误!", Toast.LENGTH_SHORT).show();
            finish();
        }

        assert bean != null;
        nameText.setText(bean.getName());
        phoneText.setText(bean.getPhoneNum());
        emailText.setText(bean.getEmail());


    }
}
