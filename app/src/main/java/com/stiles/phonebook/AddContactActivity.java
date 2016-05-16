package com.stiles.phonebook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.stiles.model.ContactBean;
import com.stiles.service.ContactService;

/**
 * Created by stiles on 16/5/16.
 */
public class AddContactActivity extends Activity {
    private EditText nameText;
    private EditText phoneText;
    private EditText emailText;
    private ContactService contactService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactService = new ContactService(getBaseContext());
        setContentView(R.layout.add_contact_activity);
        nameText = (EditText) findViewById(R.id.name);
        phoneText = (EditText) findViewById(R.id.phoneNum);
        emailText = (EditText) findViewById(R.id.email);

        Button button = (Button) findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(nameText.getText().toString())) {
                    Toast.makeText(AddContactActivity.this, "请输入信息!", Toast.LENGTH_SHORT).show();
                } else {
                    ContactBean bean = new ContactBean();
                    bean.setName(nameText.getText().toString());
                    bean.setPhoneNum(phoneText.getText().toString());
                    bean.setEmail(emailText.getText().toString());
                    contactService.save(bean);
                    finish();
                }
            }
        });
    }
}
