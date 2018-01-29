package shine.group.vaniyannur;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kpajm on 15-05-2017.
 */

public class Members {
    private int id;
    private String name;
    private String family;
    private String guardian;
    private String phoneNumber;
    private String aadharNumber;
    private Date dob;
    private String bloodGroup;

    public Members(int id,String name, String family, String guardian, String phoneNumber, String aadharNumber, String dob, String bloodGroup) {
        this.id = id;
        setName(name);
        setFamily(family);
        setGuardian(guardian);
        setPhoneNumber(phoneNumber);
        setAadharNumber(aadharNumber);
        setBloodGroup(bloodGroup);
        setDob(dob);
    }

    //empty constructor
    public Members() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDob(String dob) {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.dob = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getGuardian() {
        return guardian;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public Date getDob() {
        return dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
}
