package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.reotyranny.semeru.Model.*;
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
                AccountType a = AccountType.DONOR;
                i.putExtra("type", a);
                startActivity(i);
            }
        });

        Button employeeButton =  findViewById(R.id.button_Employee);
//        employeeButton.setOnClickListener( new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(AccountTypeActivity.this, EmployeeRegistrationScreenActivity.class);
//                AccountType a = AccountType.EMPLOYEE;
//                i.putExtra("type", a);
//                i.putExtra("test", Employee.class);
//                startActivity(i);
//            }
//        });
        Button managerButton =  findViewById(R.id.button_Manager);
        managerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountTypeActivity.this, RegistrationScreenActivity.class);
                AccountType a = AccountType.MANAGER;
                i.putExtra("type", a);
                startActivity(i);
            }
        });
        Button adminButton =  findViewById(R.id.button_Admin);
        adminButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountTypeActivity.this, RegistrationScreenActivity.class);
                AccountType a = AccountType.ADMINISTRATOR;
                i.putExtra("type", a);
                startActivity(i);
            }
        });

        Button cancelButton =  findViewById(R.id.button_Cancel);
        cancelButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountTypeActivity.this, WelcomeScreenActivity.class));
            }
        });
    }
}
