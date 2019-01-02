package womensafety.com.pink;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class about extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvhead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvhead = (TextView) findViewById(R.id.tvhead);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.icon)
                .setDescription("Pink\n" +
                        "The Women Safety App\n" )
                .addGroup("Connect with us")
                .addEmail("mailto:@gmail.com")
                .addWebsite("http://jayeshlilani36.wixsite.com/index")
                .addFacebook("")
//                .addTwitter("medyo80")
                .addYoutube("")
                .addPlayStore("")
//                .addGitHub("medyo")
//                .addInstagram("medyo80")
                .addGroup("Developed by")
                .addItem(createCopyright())
                .create();

        setContentView(aboutPage);
    }

    private Element createCopyright() {
        Element copyright = new Element();
        final String copyrightString = "Kiran Khatri | Jayesh Lilani | Piyush Khanna";
        copyright.setTitle(copyrightString);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Developed by "+copyrightString, Toast.LENGTH_LONG).show();
            }
        });
        return copyright;
    }
}
