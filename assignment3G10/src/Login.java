import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;

public class Login {
    String username;
    String password;
    boolean patient = false;
    boolean staff = false;

    public void login() throws IOException{
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome! Please log in.");
        do {
            System.out.print("Username: ");
            username = scnr.nextLine();
            System.out.print("Password: ");
            password = scnr.nextLine();
        } while (!loginValidCheck(username, password));
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
                    System.out.println("Successfully logged in as patient " + line[3]);
                    patient = true;
                }
                lp = inputPatient.readLine();
            }

            String ls = inputStaff.readLine();
            while(ls != null) {
                String[] line = ls.split(",");
                if (line[1].equals(username) && line[2].equals(password)) {
                    //need to make patient manager object here
                    System.out.println("Successfully logged in as staff member " + line[3]);
                    staff = true;
                }
                ls = inputPatient.readLine();
            }


        } finally {
            if (inputPatient != null) {
                inputPatient.close();
            }
            if (inputStaff != null) {
                inputStaff.close();
            }
        }
        if (patient || staff) {
            return true;

        }
        else {
            System.out.println("Invalid username or password");
            return false;
        }
    }
}
