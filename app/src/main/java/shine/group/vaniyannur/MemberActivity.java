package shine.group.vaniyannur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MemberActivity extends AppCompatActivity {

    String formattedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ListView listView = (ListView) findViewById(R.id.list);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        //open database
        databaseAccess.open();
        List<String> rawNames = databaseAccess.getName();
        List<String> nameList = new ArrayList<>();

        for (String list:rawNames){
            String l = list.toLowerCase();
            String[] q = l.split(" ");
            for (int i=0;i<q.length;i++){
                if (q[i].length()==2){
                    q[i] = q[i].substring(0, 2).toUpperCase() + q[i].substring(2);
                }else {
                    q[i] = q[i].substring(0, 1).toUpperCase() + q[i].substring(1);
                }
            }
            formattedName = TextUtils.join(" ",q);
            nameList.add(formattedName);
        }
        //close database
        databaseAccess.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        listView.setAdapter(adapter);

    }
}
