
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientManager {

    private ArrayList<Patient> patientList;
    private final String username;
    String name;
    private Patient currentPatient;

    public PatientManager(String username, String name) {
        this.username = username;
        this.name = name;
        patientList = new ArrayList<>();
        loadPatients();
        sortPatientsByID();
        setCurrentPatientByUsername(username);
        viewPatientInfo(username);
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
        } catch (IOException e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
    }

    public void sortPatientsByID() {
        for (int i = 0; i < patientList.size() - 1; i++) {
            for (int j = 0; j < patientList.size() - i - 1; j++) {
                if (patientList.get(j).getID() > patientList.get(j + 1).getID()) {
                    // swap the two patients
                    Patient temp = patientList.get(j);
                    patientList.set(j, patientList.get(j + 1));
                    patientList.set(j + 1, temp);
                }
            }
        }
    }

    public String viewPatientInfo(String username) {
    Patient patient = getPatientByUsername(username);

    if (patient == null) {
        String message = "Patient not found.";
        System.out.println(message);  
        return message;               
    }

    String patientInfo = "\nYour Information:\n" + patient.toString();
    System.out.println(patientInfo);  
    return patientInfo;   
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

    public void searchPatientByIDAndSet() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Patient ID to search: ");
        int targetID;

        try {
            targetID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            return;
        }

        int low = 0, high = patientList.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int midID = patientList.get(mid).getID();  

            if (midID == targetID) {
                currentPatient = patientList.get(mid);
                System.out.println("Patient found: " + currentPatient.getName());
                return;  
            } else if (midID < targetID) {
                low = mid + 1;  // higher half
            } else {
                high = mid - 1;  // lower half
            }
        }

        System.out.println("Patient not found.");
    }

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentPatientByUsername(String username) {

        for (Patient p : patientList) {
            if (p.getUsername().equals(username)) {
                this.currentPatient = p;
                return;
            }
        }
    }

    public void editPatientInfo(String username) {
        Patient patient = getPatientByUsername(username);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        Scanner scnr = new Scanner(System.in);
        boolean editing = true;

        while (editing) {
            System.out.println("\nEdit Menu for " + patient.getName() + ":");
            System.out.println("1. Change Password");
            System.out.println("2. Change Name");
            System.out.println("3. Change Email");
            System.out.println("4. Edit Treatment Notes");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scnr.nextInt();
            scnr.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new password: ");
                    String newPass = scnr.nextLine();
                    patient.setPassword(newPass);
                    break;
                    
                case 2:
                    System.out.print("Enter new name: ");
                    String newName = scnr.nextLine();
                    patient.setName(newName);
                    break;

                case 3:
                    System.out.print("Enter new email: ");
                    String newEmail = scnr.nextLine();
                    patient.setEmail(newEmail);
                    break;

                case 4:
                    System.out.print("Enter new treatment notes: ");
                    String newNotes = scnr.nextLine();
                    patient.setTreatmentNotes(newNotes);
                    break;

                case 5:
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void editAnyPatientInfo() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter the username of the patient you want to edit: ");
        String targetUsername = scnr.nextLine();

        Patient patient = getPatientByUsername(targetUsername);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        boolean editing = true;
        while (editing) {
            System.out.println("\nEditing Info for: " + patient.getName() + " (" + patient.getUsername() + ")");
            System.out.println("1. Change Password");
            System.out.println("2. Change Name");
            System.out.println("3. Change Email");
            System.out.println("4. Edit Treatment Notes");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scnr.nextInt();
            scnr.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new password: ");
                    patient.setPassword(scnr.nextLine());
                    break;

                case 2:
                    System.out.print("Enter new name: ");
                    patient.setName(scnr.nextLine());
                    break;

                case 3:
                    System.out.print("Enter new email: ");
                    patient.setEmail(scnr.nextLine());
                    break;
                case 4:
                    System.out.print("Enter new treatment notes: ");
                    patient.setTreatmentNotes(scnr.nextLine());
                    break;
                case 5:
                    System.out.println(patient);
                    break;
                case 6:
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public ArrayList<Patient> getPatientList() {
        return patientList;
    }
}
