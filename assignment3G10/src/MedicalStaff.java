public class MedicalStaff extends User {
    String department;

    public MedicalStaff(int ID, String username, String password, String name, String email, String department) {
        super(ID, username, password, name, email);
        this.department = department;
    }

    public String toString() {
        return super.toString() + "\nDepartment: " + department;
    }

    public void setTreatmentNotes(String department) {
        this.department = department;
    }
}
