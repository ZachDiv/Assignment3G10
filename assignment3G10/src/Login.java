import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Login {
    String username;
    String password;
    boolean patient = false;
    boolean staff = false;

    public Login (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void loginCheck() throws IOException {
        BufferedReader inputPatient = null, inputStaff = null;
        BufferedWriter outputPatient = null, outputStaff = null;
        try {
            inputPatient = new BufferedReader(new FileReader("patient.csv"));
            inputStaff = new BufferedReader(new FileReader("medicalstaff.csv"));

            String lp = inputPatient.readLine();
            while(lp != null) {
                String[] line = lp.split(",");
                if (line[1].equals(username) && line[2].equals(password)) {
                    //need to make patient manager object here
                    System.out.println("patient login successful");
                    patient = true;
                }
                lp = inputPatient.readLine();
            }

            String ls = inputStaff.readLine();
            while(ls != null) {
                String[] line = ls.split(",");
                if (line[1].equals(username) && line[2].equals(password)) {
                    //need to make patient manager object here
                    System.out.println("staff login successful");
                    staff = true;
                }
                ls = inputPatient.readLine();
            }

            if (!patient && !staff) {
                System.out.println("invalid username or password, try again");
            }
        } finally {
            if (inputPatient != null) {
                inputPatient.close();
            }
            if (inputStaff != null) {
                inputStaff.close();
            }
        }
    }
}
