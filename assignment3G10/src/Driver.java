import java.io.IOException;
import java.util.Scanner;
public class Driver {
    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);
        Login loginSystem = new Login();
        int choice;
        PatientManager patientManager = loginSystem.login(); //gets instance of patientManager

        System.out.println("Welcome to the dashboard, choose an option");
        System.out.println("1 - View Patient Info, 2 - View/Lookup Patient, 3 - Edit Patient Info");
        choice = scnr.nextInt();

        switch (choice) {
            case 1 :
            if (patientManager != null) {
            patientManager.viewPatientInfo(patientManager.getUsername());
            }
                break;
            default:
                throw new AssertionError();
        }
    }
}
