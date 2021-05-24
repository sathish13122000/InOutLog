public class StaffInfo {
    private String staffname;

    // string variable for storing
    // employee contact number
    private String staffreason;

    // string variable for storing
    // employee address.
    private String staffleaving;

    private String staffreturning;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public StaffInfo() {

    }

    // created getter and setter methods
    // for all our variables.
    public String getStaffName() {
        return staffname;
    }

    public void setStaffName(String staffname) {
        this.staffname = staffname;
    }

    public String getStaffreason() {
        return staffreason;
    }

    public void setStaffreason(String staffreason) {
        this.staffreason = staffreason;
    }

    public String getStaffleaving() {
        return staffleaving;
    }

    public void setStaffleaving(String staffleaving) {
        this.staffleaving = staffleaving;
    }

    public String getStaffreturning() {
        return staffreturning;
    }

    public void setStaffreturning(String staffreturning) {
        this.staffreturning= staffreturning;
    }
}
