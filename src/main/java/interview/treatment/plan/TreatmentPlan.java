package interview.treatment.plan;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by CH172585 on 9/8/2016.
 */
public class TreatmentPlan {
    private Integer ageYearPortion;
    private Integer ageMonthPortion;
    private List<Clinic> clinics;
    private Map<Medication, BigDecimal> medications;

    public TreatmentPlan() {}

    public TreatmentPlan(Integer ageYearPortion, Integer ageMonthPortion, List<Clinic> clinics, Map<Medication, BigDecimal> medications) {
        this.ageYearPortion = ageYearPortion;
        this.ageMonthPortion = ageMonthPortion;
        this.clinics = clinics;
        this.medications = medications;
    }

    /**
     * Get the YEAR PORTION of the patient's age AS OF SEPTEMBER 1ST, 2016. The "Year Portion" of a patient who is
     * 12 years, 6 months old is "12".
     */
    public Integer getAgeYearPortion() {
        return ageYearPortion;
    }

    public void setAgeYearPortion(Integer ageYearPortion) {
        this.ageYearPortion = ageYearPortion;
    }

    /**
     * Get the MONTH PORTION of the patient's age AS OF SEPTEMBER 1ST, 2016. The "Month Portion" of a patient who is
     * 12 years, 6 months old is "6".
     */
    public Integer getAgeMonthPortion() {
        return ageMonthPortion;
    }

    public void setAgeMonthPortion(Integer ageMonthPortion) {
        this.ageMonthPortion = ageMonthPortion;
    }

    /**
     * Get a list of Clinics that this patient should be treated by. A patient should only be treated
     * by clinics appropriate for their age. A patient should be treated by any and all clinics that
     * specialize in treating diseases a patient has.
     */
    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    /**
     * Get a list of medications and their dosages for this patient's Treatment Plan. A medication
     * should be included in the map if it treats a disease which a patient has greater than or equal
     * to a 70% likelihood of having. The dosages should take into account a patient's weight. The
     * final list of medications should yield the cheapest treatment plan. The list of medications
     * should never include a medication that the patient is allergic to.
     */
    public Map<Medication, BigDecimal> getMedications() {
        return medications;
    }

    public void setMedications(Map<Medication, BigDecimal> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return "TreatmentPlan{" +
                "ageYearPortion=" + ageYearPortion +
                ", ageMonthPortion=" + ageMonthPortion +
                ", clinics=" + clinics +
                ", medications=" + medications +
                '}';
    }
}
