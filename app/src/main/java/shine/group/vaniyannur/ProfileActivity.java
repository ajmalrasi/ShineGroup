package shine.group.vaniyannur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView nameView = (TextView) findViewById(R.id.nameView);
        TextView mobileNumberView = (TextView) findViewById(R.id.mobileNumberView);
        TextView addressView = (TextView) findViewById(R.id.addressView);
        TextView dobView = (TextView) findViewById(R.id.dobView);
        TextView familyView = (TextView) findViewById(R.id.familyView);
        TextView guardianView = (TextView) findViewById(R.id.guardianView);
        TextView aadharView = (TextView) findViewById(R.id.aadharView);
        TextView bloodGroupView = (TextView) findViewById(R.id.bloodGroupView);

        if(getIntent().hasExtra("id")){
            id = getIntent().getStringExtra("id");
        }
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        //open database
        databaseAccess.open();
        Members members = databaseAccess.getInfo(id);
        nameView.setText(members.getName());
        mobileNumberView.setText(members.getPhoneNumber());
        familyView.setText(members.getFamily());
        guardianView.setText(members.getGuardian());
        aadharView.setText(members.getAadharNumber());
        bloodGroupView.setText(members.getBloodGroup());
        //close database
        databaseAccess.close();
    }

}
