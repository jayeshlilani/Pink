package womensafety.com.pink;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText et_name,et_contact,et_e1,et_e2;
    Button btn_login;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp=getSharedPreferences("event",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        final String get1=sp.getString("nam","");
        if(get1.equals("")) {
            et_name = (EditText) findViewById(R.id.et_name);
            et_contact = (EditText) findViewById(R.id.et_contact);
            et_e1 = (EditText) findViewById(R.id.et_e1);
            et_e2 = (EditText) findViewById(R.id.et_e2);
            btn_login = (Button) findViewById(R.id.btn_login);

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = et_name.getText().toString();
                    String contact = et_contact.getText().toString();
                    String e1 = et_e1.getText().toString();
                    String e2 = et_e2.getText().toString();
                    if(contact.length()>=10 && e1.length()>=10 && e2.length()>=10)
                    {
                        sp = getSharedPreferences("event", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("contact", contact);
                        editor.putString("no1", e1);
                        editor.putString("no2", e2);
                        editor.putString("name", name);
                        editor.putString("nam", "done");
                        editor.commit();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Fields should not be empty!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else
        {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}