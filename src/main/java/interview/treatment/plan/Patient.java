package interview.treatment.plan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by CH172585 on 9/8/2016.
 */
public class Patient {
    private final String lastName;
    private final String firstName;
    private final LocalDate dateOfBirth;
    private final String mrn;
    private final BigDecimal weight;
    private final List<String> symptoms;
    private final List<String> medicationAllergies;

    public Patient(String lastName, String firstName, LocalDate dateOfBirth, String mrn, BigDecimal weight, List<String> symptoms, List<String> medicationAllergies) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.mrn = mrn;
        this.weight = weight;
        this.symptoms = symptoms;
        this.medicationAllergies = medicationAllergies;
    }

    /**
     * Get the last name of this patient
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the first name of this patient
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the date of birth of this patient
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Get the MRN for this patient
     */
    public String getMrn() {
        return mrn;
    }

    /**
     * Get the weight of this patient, in kg
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Get the list of symptoms this patient has
     */
    public List<String> getSymptoms() {
        return symptoms;
    }

    /**
     * Get a list of medication allergies for this patient. Medication
     * allergies are represented as a Strings which match a Medication
     * Name in ALL_MEDICATIONS exactly.
     */
    public List<String> medicationAllergies() {
        return medicationAllergies;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", mrn='" + mrn + '\'' +
                ", weight=" + weight +
                ", symptoms=" + symptoms +
                ", medicationAllergies=" + medicationAllergies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        return mrn != null ? mrn.equals(patient.mrn) : patient.mrn == null;

    }

    @Override
    public int hashCode() {
        return mrn != null ? mrn.hashCode() : 0;
    }
}
