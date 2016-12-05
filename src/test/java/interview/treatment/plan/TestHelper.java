package interview.treatment.plan;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by CH166521 on 9/8/2016.
 */
public class TestHelper {

    private TestHelper(){}

    public static List<Disease> diseaseList(){
        List <Disease> diseases = new LinkedList<>();

        Disease d1 = new Disease("Bacterial Meningitis",
                Arrays.asList("Headache", "Sore Neck", "Fever", "Loss of Limb Sensation", "Cough"),
                Arrays.asList(new HashMap<String, BigDecimal>() {{
                                  put("Ibuprofen", BigDecimal.valueOf(.1));
                                  put("Ampicillin", BigDecimal.valueOf(.5));
                                  put("Cefotaxime", BigDecimal.valueOf(.2));
                                  put("Ceftriaxone", BigDecimal.valueOf(.5));
                              }},
                        new HashMap<String, BigDecimal>() {{
                            put("Ampicillin", BigDecimal.valueOf(2));
                        }},
                        new HashMap<String, BigDecimal>() {{
                            put("Gentamicin", BigDecimal.valueOf(3));
                            put("Penicillin", BigDecimal.valueOf(2));
                        }}));

        Disease d2 = new Disease("Congestive Heart Failure",
                Arrays.asList("Congested Lungs", "Dizziness", "Fatigue", "Weakness", "Irregular Heartbeat", "Chest Pain", "Shortness of Breath"),
                Arrays.asList(new HashMap<String, BigDecimal>() {{
                                  put("Captopril", BigDecimal.valueOf(1));
                                  put("Candesartan", BigDecimal.valueOf(.4));
                                  put("Eplerenone", BigDecimal.valueOf(.1));
                                  put("Torsemide", BigDecimal.valueOf(.5));
                              }},
                        new HashMap<String, BigDecimal>() {{
                            put("Fosinopril", BigDecimal.valueOf(2));
                            put("Losartan", BigDecimal.valueOf(1));
                            put("Spironolactone", BigDecimal.valueOf(.6));
                            put("Torsemide", BigDecimal.valueOf(.5));
                        }}));

        Disease d3 = new Disease("Hypertrophic Cardiomyopathy",
                Arrays.asList("Fainting", "Fatigue", "Chest Pain", "Shortness of Breath", "Palpitations", "Burning Pain"),
                Arrays.asList(new HashMap<String, BigDecimal>() {{
                                  put("Metoprolol", BigDecimal.valueOf(1));
                                  put("Propranolol", BigDecimal.valueOf(2));
                              }},
                        new HashMap<String, BigDecimal>(){{
                            put("Ibuprofen", BigDecimal.valueOf(.1));
                            put("Sotalol", BigDecimal.valueOf(4));
                        }}));

        Disease d4 = new Disease("Peripheral Neuropathy",
                Arrays.asList("Sensitivity to Touch", "Paralysis", "Numbness", "Tingling", "Burning Pain"),
                Arrays.asList(new HashMap<String, BigDecimal>() {{
                                  put("Ibuprofen", BigDecimal.valueOf(5));
                                  put("Conzip", BigDecimal.valueOf(.1));
                              }},
                        new HashMap<String, BigDecimal>() {{
                            put("Gabapentin", BigDecimal.valueOf(5));
                        }},
                        new HashMap<String, BigDecimal>() {{
                            put("Capsaicin Cream", BigDecimal.valueOf(.5));
                            put("Lidocaine Patches", BigDecimal.valueOf(2));
                        }}));

        Disease d5 = new Disease("Dural Arteriovenous Fistulae",
                Arrays.asList("Headache", "Tinnitus", "Seizure", "Hemorrhage", "Loss of Limb Sensation", "Glaucoma", "Proptosis"),
                Collections.singletonList(new HashMap<String, BigDecimal>() {{
                    put("Ibuprofen", BigDecimal.valueOf(6));
                    put("Epitol", BigDecimal.valueOf(.05));
                }}));

        Disease d6 = new Disease("Common Cold",
                Arrays.asList("Cough", "Congestion", "Sneezing", "Fever", "Sore Throat", "Runny Nose", "Malaise"),
                Arrays.asList(new HashMap<String, BigDecimal>() {{
                                  put("Acetaminophen", BigDecimal.valueOf(1));
                                  put("Ibuprofen", BigDecimal.valueOf(6));
                                  put("Cough Syrup", BigDecimal.valueOf(20));
                              }},
                        new HashMap<String, BigDecimal>() {{
                            put("Echinacea", BigDecimal.valueOf(4));
                            put("Acetaminophen", BigDecimal.valueOf(2));
                        }}));

        Disease d7 = new Disease("Pneumonia",
                Arrays.asList("Cough", "Fever", "Chills", "Headache", "Chest Pain", "Congestion", "Confusion", "Shortness of Breath"),
                Arrays.asList(new HashMap<String, BigDecimal>() {{
                                  put("Azithromycin", BigDecimal.valueOf(.2));
                                  put("Clarithromycin", BigDecimal.valueOf(.1));
                                  put("Ibuprofen", BigDecimal.valueOf(5));
                              }},
                        new HashMap<String, BigDecimal>() {{
                            put("Azithromycin", BigDecimal.valueOf(.6));
                            put("Ibuprofen", BigDecimal.valueOf(5));
                        }}));

        Disease d8 = new Disease("Lyme Disease",
                Arrays.asList("Rash", "Fever", "Chills"),
                Arrays.asList(new HashMap<String, BigDecimal>() {{
                                  put("Acetaminophen", BigDecimal.valueOf(4));
                                  put("Ampicillin", BigDecimal.valueOf(.5));
                                  put("Cefotaxime", BigDecimal.valueOf(.2));
                              }},
                        new HashMap<String, BigDecimal>() {{
                            put("Gentamicin", BigDecimal.valueOf(2));
                            put("Acetaminophen", BigDecimal.valueOf(4));
                            put("Penicillin", BigDecimal.valueOf(1));
                        }},
                        new HashMap<String, BigDecimal>() {{
                            put("Ampicillin", BigDecimal.valueOf(2));
                            put("Acetaminophen", BigDecimal.valueOf(4));
                            put("Gentamicin", BigDecimal.valueOf(3));
                        }}));

        diseases.addAll(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8));

        return diseases;

    }

    public static List <Clinic> clinics(){
        List <Clinic> clinics = new LinkedList<>();
        Clinic c1 = new Clinic("Neonatal Cardiology", 0, 1, Arrays.asList("Congestive Heart Failure", "Hypertrophic Cardiomyopathy"));
        Clinic c2 = new Clinic("Neonatal Neurology", 0, 1, Arrays.asList("Peripheral Neuropathy", "Dural Arteriovenous Fistulae"));
        Clinic c3 = new Clinic("Neonatal General", 0, 1, Arrays.asList("Bacterial Meningitis", "Common Cold", "Pneumonia", "Lyme Disease"));
        Clinic c4 = new Clinic("Newborn Cardiology", 2, 12, Arrays.asList("Congestive Heart Failure", "Hypertrophic Cardiomyopathy"));
        Clinic c5 = new Clinic("Newborn Neurology", 2, 12, Arrays.asList("Peripheral Neuropathy", "Dural Arteriovenous Fistulae"));
        Clinic c6 = new Clinic("Newborn General", 2, 12, Arrays.asList("Bacterial Meningitis", "Common Cold", "Pneumonia", "Lyme Disease"));
        Clinic c7 = new Clinic("Child Cardiology", 13, 120, Arrays.asList("Congestive Heart Failure", "Hypertrophic Cardiomyopathy"));
        Clinic c8 = new Clinic("Child Neurology", 13, 120, Arrays.asList("Peripheral Neuropathy", "Dural Arteriovenous Fistulae"));
        Clinic c9 = new Clinic("Child General", 13, 120, Arrays.asList("Bacterial Meningitis", "Common Cold", "Pneumonia", "Lyme Disease"));
        Clinic c10 = new Clinic("Adolescent Cardiology", 121, 216, Arrays.asList("Congestive Heart Failure", "Hypertrophic Cardiomyopathy"));
        Clinic c11 = new Clinic("Adolescent Neurology", 121, 216, Arrays.asList("Peripheral Neuropathy", "Dural Arteriovenous Fistulae"));
        Clinic c12 = new Clinic("Adolescent General", 121, 216, Arrays.asList("Bacterial Meningitis", "Common Cold", "Pneumonia", "Lyme Disease"));
        Clinic c13 = new Clinic("Adult Cardiology", 217, null, Arrays.asList("Congestive Heart Failure", "Hypertrophic Cardiomyopathy"));
        Clinic c14 = new Clinic("Adult Neurology", 217, null, Arrays.asList("Peripheral Neuropathy", "Dural Arteriovenous Fistulae"));
        Clinic c15 = new Clinic("Adult General", 217, null, Arrays.asList("Bacterial Meningitis", "Common Cold", "Pneumonia", "Lyme Disease"));
        clinics.addAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15));
        return clinics;
    }

    public static List <Medication> medications(){
        List <Medication> medications = new LinkedList<>();

        Medication m1 = new Medication("Penicillin", BigDecimal.valueOf(10));
        Medication m2 = new Medication("Gentamicin", BigDecimal.valueOf(6));
        Medication m3 = new Medication("Ampicillin", BigDecimal.valueOf(1));
        Medication m4 = new Medication("Ceftriaxone", BigDecimal.valueOf(.5));
        Medication m5 = new Medication("Cefotaxime", BigDecimal.valueOf(.5));
        Medication m6 = new Medication("Captopril", BigDecimal.valueOf(1));
        Medication m7 = new Medication("Candesartan", BigDecimal.valueOf(2));
        Medication m8 = new Medication("Eplerenone", BigDecimal.valueOf(4));
        Medication m9 = new Medication("Torsemide", BigDecimal.valueOf(.5));
        Medication m10 = new Medication("Fosinopril", BigDecimal.valueOf(2));
        Medication m11 = new Medication("Losartan", BigDecimal.valueOf(3));
        Medication m12 = new Medication("Spironolactone", BigDecimal.valueOf(10));
        Medication m13 = new Medication("Metoprolol", BigDecimal.valueOf(.3));
        Medication m14 = new Medication("Propranolol", BigDecimal.valueOf(.6));
        Medication m15 = new Medication("Sotalol", BigDecimal.valueOf(6));
        Medication m16 = new Medication("Ibuprofen" , BigDecimal.valueOf(.1));
        Medication m17 = new Medication("Conzip", BigDecimal.valueOf(2));
        Medication m18 = new Medication("Gabapentin", BigDecimal.valueOf(.6));
        Medication m19 = new Medication("Capsaicin Cream", BigDecimal.valueOf(4));
        Medication m20 = new Medication("Lidocaine Patches", BigDecimal.valueOf(2));
        Medication m21 = new Medication("Epitol", BigDecimal.valueOf(1));
        Medication m22 = new Medication("Echinacea", BigDecimal.valueOf(5));
        Medication m23 = new Medication("Acetaminophen", BigDecimal.valueOf(.2));
        Medication m24 = new Medication("Cough Syrup", BigDecimal.valueOf(.01));
        Medication m25 = new Medication("Azithromycin", BigDecimal.valueOf(4));
        Medication m26 = new Medication("Clarithromycin", BigDecimal.valueOf(1));

        medications.addAll(Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12,
                m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23, m24, m25, m26));

        return medications;
    }
}
