import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class PatientManager {
    private ArrayList<Patient> patientList;
    private final String username;
    String name;

    public PatientManager (String username, String name) {
        this.username = username;
        this.name = name;
        patientList = new ArrayList<>();
        loadPatients(); 
    }


    private void loadPatients() {
        String fileName = "patient.csv";  //file name

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
 
                if (data.length >= 6) { 
                    int ID = Integer.parseInt(data[0]); 
                    String username = data[1];
                    String password = data[2];
                    String name = data[3];
                    String email = data[4];
                    String treatmentNotes = data[5];

                    // create patient object and add to list
                    patientList.add(new Patient(ID, username, password, name, email, treatmentNotes));
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }

            System.out.println("Loaded " + patientList.size() + " patients.");

        } catch (IOException e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
    }

    public void viewPatientInfo(String username) {
        Patient patient = getPatientByUsername(username);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        // display current info 
        System.out.println("\nYour Information:");
        System.out.println(patient);
    }

    public Patient getPatientByUsername(String username) {
        for (Patient p : patientList) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    public String getUsername() {
        return username;
    }
}

