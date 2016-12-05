package interview.treatment.plan;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Service for handling various calculations based on a given patient
 */
public interface TreatmentPlanService {

    void init(List<Disease> diseases, List <Clinic> clinics, List <Medication> medications);

    /**
     * Given a Patient, return the age in years of the patient <b>AS OF SEPTEMBER 1ST, 2016</b>. This method should
     * return "12" for a patient who is 12 years, 6 months old.
     *
     * @param patient The patient who's age should be calculated.
     * @return The number of years old that a patient is. (<b>nullable</b>)
     */
    Integer ageInYears(Patient patient);

    /**
     * Given a Patient, return the age in months of the patient <b>AS OF SEPTEMBER 1ST, 2016</b>. This method should
     * return "150" or a patient who is 12 years, 6 months old.
     *
     * @param patient The patient who's age should be calculated.
     * @return The number of months old that a patient is. (<b>nullable</b>)
     */
    Integer ageInMonths(Patient patient);

    /**
     * Given a Patient, return a list of clinics that the patient should visit for treatment.
     * A patient should visit a clinic if:
     *
     * 1. Their age falls into the range of ages for the clinic, AND
     * 2. The patient is "likely" to have at least one disease that the clinic specializes in treating.
     *
     * <b>A patient is "likely" to have a disease if there is greater than or
     * equal to an 70% likelihood that the patient has the disease based on their symptoms.</b>
     *
     * @param patient Patient who is being seen.
     * @return A list of clinics that the patient could be seen at for the given disease. (<b>never null</b>)
     */
    List<Clinic> clinicsBasedOnAgeAndDiseases(Patient patient);


    /**
     * Given a patient, return a map of diseases to the likelihoods that the patient has a
     * disease (as a percent, rounded to two decimal places (e.g. .856732 = 85.67% chance)). The likelihood
     * that a patient has a disease is calculated based on their symptoms and the known symptoms
     * of the disease. If a disease has 3 symptoms (e.g. Headache, Stiff Neck, Fever), patients
     * have a .33 (33%) likelihood of having the disease if they have 1 of the symptoms, a .67 (67%)
     * likelihood if they have 2 of the symptoms, and a 1 (100%) likelihood if they have 3 of the symptoms.
     *
     * @param patient Patient who is being seen.
     * @return Map of disease to percentage changes that the patient has the disease. (<b>never null</b>)
     */
    Map<Disease, BigDecimal> diseaseLikelihoods(Patient patient);

    /**
     * Given a patient and disease, return a map of medications to dosages to treat the disease.
     *
     * Criteria to consider:
     * 1. If there are multiple treatment options for a disease, the LOWEST COST treatment option should be used.
     * (We guarantee that no two treatment options will ever have the SAME cost).
     * 2. The medication dosage values (in kg) in the result map should take into account the patient's weight (given in kg).
     * 3. The dosage required to treat a disease is given in mg/kg in the Disease Object
     * 4. A medication that a patient is allergic to should never be included in the result map.
     * 5. It is OK if there are no viable treatment options given a Patient's allergies; in this case, return an empty map.
     *
     * @param patient Patient who is being seen.
     * @param disease Disease which the patient has.
     * @return Map of diseases to medication amounts. (<b>never null</b>)
     */
    Map<Medication, BigDecimal> medicationsForDisease(Patient patient, Disease disease);


    /**
     * Given a Patient, return their Treatment Plan. A valid Treatment Plan has:
     * 1. The <b>year portion</b> of the patient's age <b>AS OF SEPTEMBER 1ST, 2016</b>
     * 2. The <b>month portion</b> of the patient's age <b>AS OF SEPTEMBER 1ST, 2016</b>
     * 3. A list of Clinics that the patient should visit, given the above specifications in "clinicsBasedOnAgeAndDiseases"
     * 4. A map of Medications to Dosages that should be used to treat the diseases a patient is likely to have. Once again,
     * a patient is "likely to have" a disease if he or she has greater than or equal to a 70% chance of having the disease
     * based on his or her symptoms. The dosages in the treatment plan should take into account the patient's weight. A
     * medication that the patient is allergic to should never be included in the Treatment Plan.
     *
     * @param patient Patient who is being seen.
     * @return The treatment plan to use for the patient. (<b>never null</b>)
     */
    TreatmentPlan treatmentPlanForPatient(Patient patient);

}
