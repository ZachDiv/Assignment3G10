
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Report {

    private ArrayList<Patient> patientList;

    public Report(ArrayList<Patient> patientList) {
        this.patientList = patientList;
    }

    public void sortIDA() {
        for (Patient p : patientList) {
            System.out.println(p.getID() + " " + p.getName() + " " + p.getEmail());
        }
    }

    public void sortNameA() {
        int n = patientList.size();

        // bubble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (patientList.get(j).getName().compareToIgnoreCase(patientList.get(j + 1).getName()) > 0) {
                    // swap
                    Patient temp = patientList.get(j);
                    patientList.set(j, patientList.get(j + 1));
                    patientList.set(j + 1, temp);
                }
            }
        }

        // print list
        for (Patient p : patientList) {
            System.out.println(p.getID() + " " + p.getName() + " " + p.getEmail());
        }
    }

    public void sortEmail() {
        int n = patientList.size();

        //sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (patientList.get(j).getEmail().compareToIgnoreCase(patientList.get(j + 1).getEmail()) > 0) {
                    Patient temp = patientList.get(j);
                    patientList.set(j, patientList.get(j + 1));
                    patientList.set(j + 1, temp);
                }
            }
        }

        for (Patient p : patientList) {
            System.out.println(p.getEmail());
        }
    }

    public String displayUserInfo(Login loginSystem, PatientManager patientManager) {
        StringBuilder output = new StringBuilder();

        if (loginSystem.isPatient()) {
            output.append("Logged in as a Patient.\n");

            output.append(patientManager.viewPatientInfo(patientManager.getUsername())).append("\n");
        } else if (loginSystem.isStaff()) {
            output.append("Logged in as Staff.\n");

            output.append(patientManager.viewPatientInfo(patientManager.getUsername())).append("\n");
        } else {
            output.append("User type not recognized.\n");
        }

        return output.toString();
    }

    public void generateReport(Login loginSystem, PatientManager patientManager) {
        Scanner scanner = new Scanner(System.in);
        String userInfo = "";

        // report type
        System.out.println("Select a report to generate:");
        System.out.println("1. List Patients by ID");
        System.out.println("2. List Patients by Name");
        System.out.println("3. List of Emails");
        System.out.println("4. Display User Info");
        System.out.print("Enter the number for the report: ");
        int reportChoice = scanner.nextInt();
        scanner.nextLine();

        //filename
        System.out.print("Enter the filename to save the report: ");
        String filename = scanner.nextLine();

        switch (reportChoice) {
            case 1:
                sortIDA();
                saveToFile(filename);
                break;
            case 2:
                sortNameA();
                saveToFile(filename);
                break;
            case 3:
                sortEmail();
                saveEmailList(filename);
                break;
            case 4:
                userInfo = displayUserInfo(loginSystem, patientManager);
                saveUserInfoToFile(userInfo, filename);
                break;
            default:
                System.out.println("Invalid choice! No report generated.");
        }
    }

    private void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            // attribute group goes on new line
            for (Patient p : patientList) {
                writer.write(p.getID() + ", " + p.getName() + ", " + p.getEmail() + "\n");
            }

            System.out.println("Report has been saved to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the report: " + e.getMessage());
        }
    }

    private void saveUserInfoToFile(String userInfo, String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(userInfo);
            writer.write("\n");
            System.out.println("User Info has been saved to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the user info: " + e.getMessage());
        }
    }

    private void saveEmailList(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Patient p : patientList) {
                writer.write(p.getEmail() + "\n");  
            }

            System.out.println("Report has been saved to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the report: " + e.getMessage());
        }
    }
}
