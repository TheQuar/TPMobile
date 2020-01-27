//   __      __         .__
//  /  \    /  \  ____  |  |    ____    ____    _____    ____
//  \   \/\/   /_/ __ \ |  |  _/ ___\  /  _ \  /     \ _/ __ \
//   \        / \  ___/ |  |__\  \___ (  <_> )|  Y Y  \\  ___/
//    \__/\  /   \___  >|____/ \___  > \____/ |__|_|  / \___  >
//         \/        \/            \/               \/      \/
//              ________
//              \_____  \   __ __ _____   _______
//               /  / \  \ |  |  \\__  \  \_  __ \
//              /   \_/.  \|  |  / / __ \_ |  | \/
//              \_____\ \_/|____/ (____  / |__|
//                     \__>            \/

package com.quar.tpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText LogEdit;
    EditText PasswordEdit;
    Button LoginBtn;
    Button RegBtn;
    Button testbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogEdit = findViewById(R.id.editText);
        PasswordEdit = findViewById(R.id.editText2);

        LoginBtn = findViewById(R.id.button);
        RegBtn = findViewById(R.id.button2);
        testbtn = findViewById(R.id.testbtn);


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        RegBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, Reg.class);
               startActivity(intent);

           }
       });

       testbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, TabMenu.class);
               startActivity(intent);


           }
       });


    }




}

