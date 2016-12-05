package interview.treatment.plan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by CH166521 on 9/8/2016.
 */
public class TreatmentPlanServiceTest {

    private TreatmentPlanService treatmentPlanService;
    private Patient testPatient;

    @Before
    public void init(){
        treatmentPlanService = new TreatmentPlanServiceImpl();
        treatmentPlanService.init(TestHelper.diseaseList(), TestHelper.clinics(), TestHelper.medications());

        testPatient = new Patient("Butler", "Christopher", LocalDate.of(1993, 5, 10), "5478693", BigDecimal.valueOf(68),
                Arrays.asList("Fever", "Cough", "Sneezing", "Sore Throat", "Headache", "Fatigue", "Chills", "Chest Pain", "Congestion"),
                Arrays.asList("Clarithromycin", "Gabapentin"));
    }

    @Test
    public void ageInYears() throws Exception {
        Integer ageYears = treatmentPlanService.ageInYears(testPatient);
        Assert.assertEquals(23, ageYears.intValue());
    }

    @Test
    public void ageInMonths() throws Exception {
        Integer ageMonths = treatmentPlanService.ageInMonths(testPatient);
        Assert.assertEquals(279, ageMonths.intValue());
    }

    @Test
    public void clinicsBasedOnAgeAndDiseases() throws Exception {
        List<Clinic> clinicList = treatmentPlanService.clinicsBasedOnAgeAndDiseases(testPatient);
        Assert.assertTrue(clinicList.size() == 1);
        Assert.assertEquals("Adult General", clinicList.get(0).getName());
    }

    @Test
    public void diseaseLikelihoods() throws Exception {
        Map<Disease, BigDecimal> result = treatmentPlanService.diseaseLikelihoods(testPatient);

        Map <String, BigDecimal> expected = new HashMap<>();
        expected.put("Bacterial Meningitis", BigDecimal.valueOf(.6));
        expected.put("Congestive Heart Failure", BigDecimal.valueOf(.29));
        expected.put("Hypertrophic Cardiomyopathy", BigDecimal.valueOf(.33));
        expected.put("Peripheral Neuropathy", BigDecimal.valueOf(0));
        expected.put("Dural Arteriovenous Fistulae", BigDecimal.valueOf(.14));
        expected.put("Common Cold", BigDecimal.valueOf(.71));
        expected.put("Pneumonia", BigDecimal.valueOf(.75));
        expected.put("Lyme Disease", BigDecimal.valueOf(.67));

        Assert.assertTrue(expected.size() == result.size());
        for (String expectedDisease : expected.keySet()){
            Assert.assertTrue(
                String.format("Expected [%s] but not found in result set.", expectedDisease),
                result.keySet().stream()
                    .map(Disease::getName)
                    .collect(Collectors.toSet()).contains(expectedDisease)
            );
        }

        for(Map.Entry<Disease, BigDecimal> entry : result.entrySet()){
            Assert.assertEquals(expected.get(entry.getKey().getName()).doubleValue(), entry.getValue().doubleValue(), 0.001);
        }
    }

    @Test
    public void medicationsForDisease() throws Exception {
        for (Disease d : TestHelper.diseaseList()) {
            Map<Medication, BigDecimal> result = treatmentPlanService.medicationsForDisease(testPatient, d);
            Map<Medication, BigDecimal> expected = new HashMap<>();

            boolean check = true;
            switch (d.getName()) {
                case "Bacterial Meningitis":
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Ibuprofen")).findFirst().get(), BigDecimal.valueOf(6.8));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Ampicillin")).findFirst().get(), BigDecimal.valueOf(34));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Cefotaxime")).findFirst().get(), BigDecimal.valueOf(13.6));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Ceftriaxone")).findFirst().get(), BigDecimal.valueOf(34));
                    break;
                case "Congestive Heart Failure":
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Captopril")).findFirst().get(), BigDecimal.valueOf(68));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Candesartan")).findFirst().get(), BigDecimal.valueOf(27.2));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Eplerenone")).findFirst().get(), BigDecimal.valueOf(6.8));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Torsemide")).findFirst().get(), BigDecimal.valueOf(34));
                    break;
                case "Hypertrophic Cardiomyopathy":
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Metoprolol")).findFirst().get(), BigDecimal.valueOf(68));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Propranolol")).findFirst().get(), BigDecimal.valueOf(136));
                    break;
                case "Peripheral Neuropathy":
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Ibuprofen")).findFirst().get(), BigDecimal.valueOf(340));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Conzip")).findFirst().get(), BigDecimal.valueOf(6.8));
                    break;
                case "Dural Arteriovenous Fistulae":
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Ibuprofen")).findFirst().get(), BigDecimal.valueOf(408));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Epitol")).findFirst().get(), BigDecimal.valueOf(3.4));
                    break;
                case "Common Cold":
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Acetaminophen")).findFirst().get(), BigDecimal.valueOf(68));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Ibuprofen")).findFirst().get(), BigDecimal.valueOf(408));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Cough Syrup")).findFirst().get(), BigDecimal.valueOf(1360));
                    break;
                case "Pneumonia":
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Azithromycin")).findFirst().get(), BigDecimal.valueOf(40.8));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Ibuprofen")).findFirst().get(), BigDecimal.valueOf(340));
                    break;
                case "Lyme Disease":
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Acetaminophen")).findFirst().get(), BigDecimal.valueOf(272));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Ampicillin")).findFirst().get(), BigDecimal.valueOf(34));
                    expected.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Cefotaxime")).findFirst().get(), BigDecimal.valueOf(13.6));
                    break;
                default:
                    check = false;
                    break;
            }

            if (check) {
                Assert.assertTrue(expected.size() == result.size());
                for (Medication expectedMedication : expected.keySet()){
                    Assert.assertTrue(
                            String.format("Expected [%s] but not found in result set.", expectedMedication),
                            result.keySet().stream().collect(Collectors.toSet()).contains(expectedMedication)
                    );
                }

                for(Map.Entry<Medication, BigDecimal> entry : result.entrySet()){
                    Assert.assertEquals(expected.get(entry.getKey()).doubleValue(), entry.getValue().doubleValue(), 0.001);
                }
            }
        }
    }

    @Test
    public void treatmentPlanForPatient() throws Exception {
        TreatmentPlan result = treatmentPlanService.treatmentPlanForPatient(testPatient);
        TreatmentPlan expected = new TreatmentPlan();
        expected.setClinics(Collections.singletonList(TestHelper.clinics().stream().filter(c -> c.getName().equals("Adult General")).findFirst().get()));
        expected.setAgeYearPortion(23);
        expected.setAgeMonthPortion(3);

        Map<Medication, BigDecimal> expectedMedications = new HashMap<>();
        expectedMedications.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Acetaminophen")).findFirst().get(), BigDecimal.valueOf(68));
        expectedMedications.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Ibuprofen")).findFirst().get(), BigDecimal.valueOf(748));
        expectedMedications.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Cough Syrup")).findFirst().get(), BigDecimal.valueOf(1360));
        expectedMedications.put(TestHelper.medications().stream().filter(m -> m.getName().equals("Azithromycin")).findFirst().get(), BigDecimal.valueOf(40.8));
        expected.setMedications(expectedMedications);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getAgeMonthPortion(), expected.getAgeMonthPortion());
        Assert.assertEquals(result.getAgeYearPortion(), expected.getAgeYearPortion());
        Assert.assertEquals(result.getClinics().size(), expected.getClinics().size());
        Assert.assertEquals(result.getClinics().get(0).getName(), expected.getClinics().get(0).getName());

        Assert.assertTrue(expected.getMedications().size() == result.getMedications().size());
        for (Medication expectedMedication : expected.getMedications().keySet()){
            Assert.assertTrue(
                    String.format("Expected [%s] but not found in result set.", expectedMedication),
                    result.getMedications().keySet().stream().collect(Collectors.toSet()).contains(expectedMedication)
            );
        }

        for(Map.Entry<Medication, BigDecimal> entry : result.getMedications().entrySet()){
            Assert.assertEquals(expected.getMedications().get(entry.getKey()).doubleValue(), entry.getValue().doubleValue(), 0.001);
        }
    }
}