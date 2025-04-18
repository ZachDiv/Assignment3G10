import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    private String username;
    private String password;
    private PatientManager patientManager;
    boolean patient = false;
    boolean staff = false;

    public PatientManager login() throws IOException{
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome! Please log in.");
        do {
            System.out.print("Username: ");
            username = scnr.nextLine();
            System.out.print("Password: ");
            password = scnr.nextLine();
        } while (!loginValidCheck(username, password));

        return patientManager; 
    }

    public boolean loginValidCheck(String username, String password) throws IOException {
        BufferedReader inputPatient = null, inputStaff = null;
        try {
            inputPatient = new BufferedReader(new FileReader("patient.csv"));
            inputStaff = new BufferedReader(new FileReader("medicalstaff.csv"));

            String lp = inputPatient.readLine();
            while(lp != null) {
                String[] line = lp.split(",");
                if (line[1].equals(username) && line[2].equals(password)) {
                    //need to make patient manager object here
                    System.out.println("Logged in as " + line[3] + " (Patient)");
                    patient = true;
                    patientManager = new PatientManager(username, line[3]);
                }
                lp = inputPatient.readLine();
            }

            String ls = inputStaff.readLine();
            while(ls != null) {
                String[] line = ls.split(",");
                if (line[1].equals(username) && line[2].equals(password)) {
                    //need to make patient manager object here
                    System.out.println("Logged in as " + line[3] + " (Staff)");
                    staff = true;
                    patientManager = new PatientManager(username, line[3]);
                }
                ls = inputPatient.readLine();
            }

        } finally {
            if (inputPatient != null) {inputPatient.close();}
            if (inputStaff != null) {inputStaff.close();}
        }

        if (patient || staff) {return true; }
        else {
            System.out.println("Invalid username or password");
            return false;
        }
    }

    public boolean isPatient() {return patient;}   
    public boolean isStaff() {return staff;}

}
