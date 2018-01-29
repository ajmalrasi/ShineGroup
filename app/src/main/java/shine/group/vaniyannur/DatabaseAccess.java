package shine.group.vaniyannur;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kpajm on 15-05-2017.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private Members members;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<String> getName() {
        List<String> list = new ArrayList<>();
//        String sql="SELECT members.name FROM members INNER JOIN family ON members.family_id=family.id WHERE family.address='CHATHERY';";
        String sql="SELECT members.name FROM members ORDER BY members.name;";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public List<String> getMobileNumber(){
        List<String> list = new ArrayList<>();
        String sql="SELECT members.mobile_number FROM members;";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public String getName(String mobileNumber){
        String name = "Lorem Ipsum";
        String sql="select members.name from members where members.mobile_number = '"+mobileNumber+"';";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        name = cursor.getString(0);
        cursor.close();
        return name;
    }

    public Members getInfo(String mobileNumber){
        String sql="select * from members inner join family on members.family_id = family.id inner join blood_group on members.blood_group_id = blood_group.id where members.mobile_number = '"+mobileNumber+"';";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        members = new Members();
        members.setId(cursor.getInt(0));
        members.setName(cursor.getString(1));
        members.setGuardian(cursor.getString(2));
        members.setDob(cursor.getString(3));
        members.setPhoneNumber(cursor.getString(4));
        members.setAadharNumber(cursor.getString(5));
        members.setFamily(cursor.getString(8));
        members.setBloodGroup(cursor.getString(10));
        cursor.close();
        return members;
    }

}
