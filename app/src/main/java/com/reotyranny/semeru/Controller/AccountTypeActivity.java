package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.reotyranny.semeru.Model.AccountType;
import com.reotyranny.semeru.R;

public class AccountTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        Button userButton =  findViewById(R.id.button_User);
        userButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountTypeActivity.this, RegistrationScreenActivity.class);
                AccountType a = AccountType.user;
                i.putExtra("type", a);
                startActivity(i);
            }
        });

        Button employeeButton =  findViewById(R.id.button_Employee);
        employeeButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountTypeActivity.this, EmployeeRegistationScreenActivity.class);
                AccountType a = AccountType.employee;
                i.putExtra("type", a);
                startActivity(i);
            }
        });
        Button managerButton =  findViewById(R.id.button_Manager);
        managerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountTypeActivity.this, RegistrationScreenActivity.class);
                AccountType a = AccountType.manager;
                i.putExtra("type", a);
                startActivity(i);
            }
        });
        Button adminButton =  findViewById(R.id.button_Admin);
        adminButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountTypeActivity.this, RegistrationScreenActivity.class);
                AccountType a = AccountType.administrator;
                i.putExtra("type", a);
                startActivity(i);
            }
        });
    }
}
