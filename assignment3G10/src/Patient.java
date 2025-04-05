class Patient extends User {
    String treatmentNotes;

    public Patient(int ID, String username, String password, String name, String email, String treatmentNotes) {
        super(ID, username, password, name, email);
        this.treatmentNotes = treatmentNotes;
    }

    public String toString() {
        return super.toString() + "\nTreatment Notes: " + treatmentNotes;
    }

    public void setTreatmentNotes(String treatmentNotes) {
        this.treatmentNotes = treatmentNotes;
    }

    public String getTreatmentNotes() {
        return treatmentNotes; 
    }
}
