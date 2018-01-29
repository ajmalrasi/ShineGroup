package shine.group.vaniyannur;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mobileNumberEdit;
    TextView statusText,shineTitle,subTitle;
    private static final String TAG = "MainActivity";
    List<String> mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16) {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }else{
            ActionBar actionBar = getSupportActionBar();
            try {
                actionBar.hide();
            }catch (NullPointerException e){
                this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
        }
        setContentView(R.layout.activity_main);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/marilou.ttf");

        statusText= (TextView) findViewById(R.id.statusText);
        shineTitle= (TextView) findViewById(R.id.groupTitle);
        subTitle= (TextView) findViewById(R.id.subTitle);
        shineTitle.setTypeface(custom_font);
        subTitle.setTypeface(custom_font);
        mobileNumberEdit = (EditText) findViewById(R.id.mobileNumberInput);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        //open database
        databaseAccess.open();
        mobileNumber = databaseAccess.getMobileNumber();
        //close database
        databaseAccess.close();
    }

    public void logIn(View view){
        if (mobileNumber.contains(mobileNumberEdit.getText().toString())){
            statusText.setVisibility(View.INVISIBLE);
            Intent i = new Intent(this,DashboardActivity.class);
            i.putExtra("id",mobileNumberEdit.getText().toString());
            startActivity(i);
        }else{
            statusText.setVisibility(View.VISIBLE);
            statusText.setText(R.string.invalid_number);
        }
    }

}
