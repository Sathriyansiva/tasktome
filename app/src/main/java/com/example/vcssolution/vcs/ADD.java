package com.example.vcssolution.vcs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ADD extends AppCompatActivity {
    EditText name, father, phone, nationality, address, dob;
    RadioGroup sexradio;
    String SQLiteQuery;
    SQLiteDatabase SQLITEDATABASE;
    Boolean CheckEditTextEmpty;
    String Name, FatherName, PhoneNumber, Nationality, Addres, Dob;
    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setCurrentDateOnView();
    }
    public void setCurrentDateOnView() {
       dob = (EditText) findViewById(R.id.editText_dob);
        final java.util.Calendar c = java.util.Calendar.getInstance();
        year = c.get(java.util.Calendar.YEAR);
        month = c.get(java.util.Calendar.MONTH);
        day = c.get(java.util.Calendar.DAY_OF_MONTH);
        dob .setText(new StringBuilder()
                .append(day).append("-").append(month + 1).append("-")
                .append(year).append(" "));
    }
    public void date(View view)
    {
        showDialog(DATE_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener,year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            dob .setText(new StringBuilder().append(day)
                    .append("-").append(month + 1).append("-").append(year)
                    .append(" "));
        }
    };


    public void save(View view) {
        DBCreate();

        SubmitData2SQLiteDB();
    }

    public void cancel(View view) {

    }

    public void DBCreate() {

        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);

        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS demoTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, phone_number VARCHAR, subject VARCHAR);");
    }

    public void SubmitData2SQLiteDB() {


        Name = name.getText().toString();
        FatherName = father.getText().toString();
        PhoneNumber = phone.getText().toString();
        Nationality = nationality.getText().toString();
        Addres = address.getText().toString();
        Dob = dob.getText().toString();


//        Subject = GetSubject.getText().toString();

        CheckEditTextIsEmptyOrNot(Name, PhoneNumber, FatherName, Nationality, Addres, Dob);

        if (CheckEditTextEmpty == true) {

            SQLiteQuery = "INSERT INTO demoTable (name,fathername,phone_number,nationality,address,dob,sex) VALUES('" + Name + "','" + FatherName + "', '" + PhoneNumber + "','" + Nationality + "', '" + Addres + "','" + dob + "',);";

            SQLITEDATABASE.execSQL(SQLiteQuery);

            Toast.makeText(ADD.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            ClearEditTextAfterDoneTask();

        } else {

            Toast.makeText(ADD.this, "Please Fill All the Fields", Toast.LENGTH_LONG).show();
        }
    }

    private void ClearEditTextAfterDoneTask() {

        name.getText().clear();
        phone.getText().clear();
        father.getText().clear();
        nationality.getText().clear();
        address.getText().clear();
        dob.getText().clear();
    }

    public void CheckEditTextIsEmptyOrNot(String Name, String PhoneNumber,
                                          String FatherName, String Nationality,
                                          String Addres, String Dob) {

        if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber)
                || TextUtils.isEmpty(FatherName)
                || TextUtils.isEmpty(Nationality)
                || TextUtils.isEmpty(Dob)
                || TextUtils.isEmpty(Addres)) {

            CheckEditTextEmpty = false;

        } else {
            CheckEditTextEmpty = true;
        }
    }



}