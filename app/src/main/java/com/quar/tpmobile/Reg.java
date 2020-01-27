package com.quar.tpmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.santalu.maskedittext.MaskEditText;

import org.w3c.dom.Comment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.sql.CommonDataSource;

import static android.text.TextUtils.isEmpty;

public class Reg extends AppCompatActivity {

    EditText UserFirstName, UserLastName, UserFatherName;
    MaskEditText UserBrithday, UserPhoneNumber;
    Button RegButton;

    ImageView userAvatar;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        UserFirstName = findViewById(R.id.FirstName);
        UserLastName = findViewById(R.id.LastName);
        UserFatherName = findViewById(R.id.FatherName);
        UserBrithday = findViewById(R.id.Brithday);
        UserPhoneNumber = findViewById(R.id.PhoneNumber);

        RegButton = findViewById(R.id.regButton);

        userAvatar = findViewById(R.id.userAvatar);

        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Reg.this, "It`s test ", Toast.LENGTH_SHORT).show();
                chooseFile();
            }
        });

        UserBrithday.setOnTouchListener(new View.OnTouchListener() {
                                            @Override
                                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                                final int DRAWABLE_RIGHT = 2;
                                                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                                    if (motionEvent.getRawX() >= (UserBrithday.getRight() - UserBrithday.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                                        getDateText(UserBrithday);
                                                    }
                                                    return true;
                                                }

                                                return false;
                                            }
                                        });

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmpty(UserFirstName.getText()) || isEmpty(UserLastName.getText()) ||
                        isEmpty(UserFatherName.getText()) || isEmpty(UserBrithday.getText()) ||
                        isEmpty(UserPhoneNumber.getText())) {

                    if (isEmpty(UserFirstName.getText()))
                        UserFirstName.setError("Iltimos ismingizni kiriting ");
                    if (isEmpty(UserLastName.getText()))
                        UserLastName.setError("Iltimos familyangizni kiriting ");
                    if (isEmpty(UserFatherName.getText()))
                        UserFatherName.setError("Iltimos otangizni ismini kiriting ");
                    if (isEmpty(UserBrithday.getText()))
                        UserBrithday.setError("Iltimos tug`ulgan sanangizni kiriting ");
                    if (isEmpty(UserPhoneNumber.getText()))
                        UserPhoneNumber.setError("Iltimos telefon raqamingizni kiriting ");
                }
                else {
                    Toast.makeText(Reg.this, "Good", Toast.LENGTH_SHORT).show();

                    Toast.makeText(Reg.this, UserBrithday.getRawText(), Toast.LENGTH_LONG).show();
                    Toast.makeText(Reg.this, UserPhoneNumber.getRawText(), Toast.LENGTH_LONG).show();
                }

            }
        });




    }



    //It's other
    private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
          Uri filePath = data.getData();
          try {
              bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
              userAvatar.setImageBitmap(bitmap);
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }

       //  UploadPicture("1", getStringImage(bitmap));
        }
    }

    private void UploadPicture(final String id, final String photo){
        final ProgressDialog progressDialog = new ProgressDialog(Reg.this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        progressDialog.dismiss();
    }

    public String getStringImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }

    public void getDateText(final EditText editText){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(Reg.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String mDay = String.valueOf(day);
                        String mMonth = String.valueOf(month);
                        String mYear = String.valueOf(year);

                        if (mDay.length() == 1) mDay = "0" + mDay;
                        if (mMonth.length() == 1) mMonth = "0" + mMonth;

                        String date = mDay + "-" + mMonth + "-" + mYear;
                        editText.setText(date);
                    }
                }, year, month, day );

        datePickerDialog.show();

    }
}
