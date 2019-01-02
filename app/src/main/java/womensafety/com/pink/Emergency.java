package womensafety.com.pink;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Emergency extends AppCompatActivity {
    Button btn_pick1, btn_pick2, btn_add, btnviewconts;
    EditText et_add1, et_add2;
    private final int REQUEST_CODE = 99;
    SharedPreferences sp;
    int i1 = 0;
    int i2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        btn_pick1 = (Button) findViewById(R.id.btn_pick1);
        btn_add = (Button) findViewById(R.id.btn_add);
        et_add1 = (EditText) findViewById(R.id.et_add1);
        btn_pick2 = (Button) findViewById(R.id.btn_pick2);
        et_add2 = (EditText) findViewById(R.id.et_add2);


//        btn_pick1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_CONTACTS)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(Emergency.this, new String[]{android.Manifest.permission.READ_CONTACTS}, 1);
//
//                }
//                else{
//
//                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                    i1=1;
//                    i2=0;
//                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
//                    startActivityForResult(intent, 100);                            // No explanation needed; erequest the permission
//                  }
//            }
//        });
//        btn_pick2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_CONTACTS)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(Emergency.this, new String[]{android.Manifest.permission.READ_CONTACTS}, 1);
//
//                }
//                else{
//
//                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                    i1=0;
//                    i2=1;
//
//                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
//                    startActivityForResult(intent, 100);                            // No explanation needed; erequest the permission
//                }
//            }
//        });
//
//
//    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK && requestCode == 100) {
//
//            try {
//                Uri returnUri = data.getData();
//                Cursor cursor = getContentResolver().query(returnUri, null, null, null, null);
//
//                if (cursor.moveToNext()) {
//                    int columnIndex_ID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
//                    String contactID = cursor.getString(columnIndex_ID);
//                    int columnIndex_HASPHONENUMBER = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
//                    String stringHasPhoneNumber = cursor.getString(columnIndex_HASPHONENUMBER);
//
//                    if (stringHasPhoneNumber.equals("1")) {
//                        Cursor pcursor = getContentResolver().query(
//                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                                null,
//                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                                new String[]{contactID}, null);
//                        String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        if(i1==1) {
//                            et_add1.setText(phone);
//                        }
//                        else
//                        {
//                            et_add2.setText(phone);
//                        }
//
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "Phone Number not Found.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            } catch (Exception e) {
//                Toast.makeText(this, "File not supported", Toast.LENGTH_SHORT).show();
//            }
//        }


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String no1 = et_add1.getText().toString();
                String no2 = et_add2.getText().toString();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("no1",no1);
                editor.putString("no2",no2);
                editor.commit();
                startActivity(i);

            }
        });
    }
}