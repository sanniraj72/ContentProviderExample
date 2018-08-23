package com.service.contentproviderexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(MyProvider.NAME,
                ((EditText) findViewById(R.id.editText2)).getText().toString());

        values.put(MyProvider.GRADE,
                ((EditText) findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(
                MyProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.service.contentproviderexample.MyProvider";

        Uri students = Uri.parse(URL);
        Cursor c = getContentResolver().query(students, null, null, null, "name");
        // Cursor c = managedQuery(students, null, null, null, "name");

        if (c != null && c.moveToFirst()) {
            do {
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(MyProvider._ID)) +
                                ", " + c.getString(c.getColumnIndex(MyProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex(MyProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
            c.close();
        }
    }
}
