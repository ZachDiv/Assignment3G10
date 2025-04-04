
import java.io.IOException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);
        Login loginSystem = new Login();
        int choice;
        PatientManager patientManager = loginSystem.login(); //gets instance of patientManager

        System.out.println("Welcome to the dashboard, choose an option:");
        while (true) {
            System.out.println("\n1 - View Patient Info, 2 - View/Lookup Patient, 3 - Edit Patient Info, 4 - Exit");
            choice = scnr.nextInt();

            switch (choice) {
                case 1:
                    if (patientManager != null) {
                        patientManager.viewPatientInfo(patientManager.getUsername());
                    }
                    break;

                case 2:
                    if (loginSystem.isPatient()) {
                        try {
                            throw new IllegalAccessException("Access Denied: Patients are not allowed to use this feature.");
                        } catch (IllegalAccessException ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else if (loginSystem.isStaff() && patientManager != null) {
                        patientManager.searchPatientByIDAndSet();
                    }

                    break;

                case 3:
                    if (loginSystem.isPatient() && patientManager != null) {
                        patientManager.editPatientInfo(patientManager.getUsername());
                    }

                    if (loginSystem.isStaff() && patientManager != null) {
                        patientManager.editAnyPatientInfo();
                    }
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    return;

                default:
                    throw new AssertionError();
            }
        }
    }
}
