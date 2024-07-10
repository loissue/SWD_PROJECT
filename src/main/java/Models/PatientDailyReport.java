package Models;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PatientReportDaily")
public class PatientDailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    private String symtoms;
    private String rfe;
    private String testAndResult;
    private String treatmentProgress;
    private String diagnose;
    private Date time;
    private String note;

    @ManyToOne
    @JoinColumn(name = "PatientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "RecordId")
    private PatientRecord patientRecord;

    // Getters and setters
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getSymtoms() {
        return symtoms;
    }

    public void setSymtoms(String symtoms) {
        this.symtoms = symtoms;
    }

    public String getRfe() {
        return rfe;
    }

    public void setRfe(String rfe) {
        this.rfe = rfe;
    }

    public String getTestAndResult() {
        return testAndResult;
    }

    public void setTestAndResult(String testAndResult) {
        this.testAndResult = testAndResult;
    }

    public String getTreatmentProgress() {
        return treatmentProgress;
    }

    public void setTreatmentProgress(String treatmentProgress) {
        this.treatmentProgress = treatmentProgress;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientRecord getPatientRecord() {
        return patientRecord;
    }

    public void setPatientRecord(PatientRecord patientRecord) {
        this.patientRecord = patientRecord;
    }
}
