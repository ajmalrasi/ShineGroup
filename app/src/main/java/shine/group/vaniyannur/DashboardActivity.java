package shine.group.vaniyannur;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    ImageView members, dashboard, bloodGroup, profile;
    TextView memberName,groupTitle;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        } else {
            ActionBar actionBar = getSupportActionBar();
            try {
                actionBar.hide();
            } catch (NullPointerException e) {
                this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
        }
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/marilou.ttf");

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            id = getIntent().getStringExtra("id");
        } else {
            id = extras.getString("id");
        }

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        //open database
        databaseAccess.open();
        String rawName = databaseAccess.getName(id);
        //close database
        databaseAccess.close();
        String formattedName = GeneralUtils.formatName(rawName);

        setContentView(R.layout.activity_dashboard);
        members = (ImageView) findViewById(R.id.memberIcon);
        dashboard = (ImageView) findViewById(R.id.dashboardIcon);
        bloodGroup = (ImageView) findViewById(R.id.bloodGroupIcon);
        profile = (ImageView) findViewById(R.id.profileIcon);
        groupTitle = (TextView) findViewById(R.id.groupName);
        groupTitle.setTypeface(custom_font);
        

        Resources res = getResources();
        String text = res.getString(R.string.member_name, formattedName);
        memberName = (TextView) findViewById(R.id.memberName);
        memberName.setText(text);

        members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MemberActivity.class);
                startActivity(i);
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar mySnackbar = Snackbar.make(v, R.string.no_permission, Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }
        });

        bloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), BloodgroupActivity.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ProfileActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

//        System.out.println(GeneralUtils.getScreenDpi(this));


    }
}
