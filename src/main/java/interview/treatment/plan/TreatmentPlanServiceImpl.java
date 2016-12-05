package interview.treatment.plan;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Your task is to implement the below service to solve the following problem:
 * given a Patient, what is the appropriate TreatmentPlan?
 *
 * A Patient has a name, date of birth, weight, list of symptoms, list of
 * medication allergies, and MRN (Medical Record Number). We have also provided
 * a list of Diseases, Medications, and Clinics for use in this problem in our
 * test suite.
 *
 * A Disease has a name, list of symptoms (which suggest a patient has the
 * disease if a patient has the symptoms in the list), and a list of possible
 * treatments for the disease. Each possible treatment for a disease is a
 * combination of medications with dosage amounts given in mg/kg.
 *
 * A Medication has a name and a cost per mg.
 *
 * A Clinic has a name, a range of ages (in months) that the clinic is open to,
 * and a list of diseases the clinic specializes in treating.
 *
 * Using this information and the provided classes and interface, implement the
 * TreatmentPlanServiceImpl class. Each method in the interface includes exact
 * specifications for what it should return. You can validate that you are
 * returning the correct information using the provided JUnit Test Suite. We
 * will test your answers against additional tests upon your submission of your
 * code.
 *
 * The "Init" method will be called before each test to set up the lists of
 * Disease, Medications, and Clinics. We may test your solution against
 * different lists of Diseases, Medications, and Clinics.
 */
public class TreatmentPlanServiceImpl implements TreatmentPlanService {

	// Do not modify the lists below.
	private List<Disease> diseases = new ArrayList<>();
	private List<Medication> medications = new ArrayList<>();
	private List<Clinic> clinics = new ArrayList<>();

	// TODO Optionally Implement any additional data structures here....

	// TODO .... to here.

	@Override
	public void init(List<Disease> diseases, List<Clinic> clinics, List<Medication> medications) {

		this.diseases = diseases;
		this.clinics = clinics;
		this.medications = medications;

		// TODO Optionally implement any additional init items below here ....

		// TODO ... to here.
	}

	@Override
	public Integer ageInYears(Patient patient) {
		return Period.between(patient.getDateOfBirth(), LocalDate.now()).getYears();
	}

	@Override
	public Integer ageInMonths(Patient patient) {
		return (int) Period.between(patient.getDateOfBirth(), LocalDate.now()).toTotalMonths();

	}

	@Override
	public List<Clinic> clinicsBasedOnAgeAndDiseases(Patient patient) {
		int pMonths = ageInMonths(patient);
		List<String> pLikelyDiseases = new ArrayList<>();

		// Get map of likely diseases
		Map<Disease, BigDecimal> pLikelyDiseasesMap = diseaseLikelihoods(patient);
		for (Map.Entry<Disease, BigDecimal> entry : pLikelyDiseasesMap.entrySet()) {
			if ((entry.getValue().compareTo(BigDecimal.valueOf(.7)) >= 0)) {
				pLikelyDiseases.add(entry.getKey().getName());
			}
		}

		List<Clinic> ageClinics = new ArrayList<>();
		List<Clinic> diseaseClinics = new ArrayList<>();
		// Get two Lists of clinics based off age and disease and merge for a
		// final list
		for (Clinic c : clinics) {
			if (c.getMinAgeInMonths() <= pMonths && ((c.getMaxAgeInMonths() != null) ? pMonths <= c.getMaxAgeInMonths() : true)) {
				ageClinics.add(c);
			}
			if (!Collections.disjoint(c.getDiseases(), pLikelyDiseases)) {
				diseaseClinics.add(c);
			}
		}
		ageClinics.retainAll(diseaseClinics);
		return ageClinics;

	}

	@Override
	public Map<Disease, BigDecimal> diseaseLikelihoods(Patient patient) {
		DecimalFormat twoDecimal = new DecimalFormat("#.##");
		List<String> pSymptoms = new ArrayList<>(patient.getSymptoms());
		Map<Disease, BigDecimal> diseaseLikelihoods = new HashMap<>();
		
		// Compute disease likelihood based on ratio of how many symptoms match
		// what the patient has vs total amount of symptoms based on disease
		// list
		for (Disease d : diseases) {
			List<String> dSymptoms = new ArrayList<>(d.getSymptoms());
			float symptomAmount = dSymptoms.size();
			dSymptoms.retainAll(pSymptoms);
			float matchingSymptoms = dSymptoms.size();
			float likelihood = Float.valueOf(twoDecimal.format(matchingSymptoms / symptomAmount));
			diseaseLikelihoods.put(d, BigDecimal.valueOf(likelihood));
		}

		return diseaseLikelihoods;
	}

	@Override
	public Map<Medication, BigDecimal> medicationsForDisease(Patient patient, Disease disease) {

		BigDecimal pWeight = patient.getWeight();
		List<String> allergies = new ArrayList<>(patient.medicationAllergies());
		Map<Medication, BigDecimal> patientMedicationsMap = new HashMap<>();
		List<Map<String, BigDecimal>> medicationCombinations = new ArrayList<>(disease.getMedicationCombinations());
		List<Map<String, BigDecimal>> medicationPossibilities = new ArrayList<>();
		Iterator<Map<String, BigDecimal>> itr = medicationCombinations.iterator();

		// Filter out medications patient is allergic to and create map of
		// medications with dosage in mg based on patient weight
		while (itr.hasNext()) {
			Map<String, BigDecimal> map = itr.next();
			Set<String> medications = map.keySet();
			if (!Collections.disjoint(medications, allergies)) {
				itr.remove();
				continue;
			}
			Map<String, BigDecimal> weighedMap = map.entrySet().stream()
					.collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue().multiply(pWeight)));
			medicationPossibilities.add(weighedMap);
		}
		if (medicationPossibilities.size() == 0) {
			return patientMedicationsMap;
		}
		
		Iterator<Map<String, BigDecimal>> itr2 = medicationPossibilities.iterator();
		float max = 0;
		Map<Medication, BigDecimal> tempMap = new HashMap<>();
		// From previously generated list, find lowest cost combination of
		// medications based on dosage amounts for the patient and cost per mg
		while (itr2.hasNext()) {
			float currentTotal = 0;

			Map<String, BigDecimal> map = itr2.next();
			List<Medication> medList = medications.stream().filter(m -> map.keySet().contains(m.getName()))
					.collect(Collectors.toList());
			Iterator<Medication> medItr = medList.iterator();
			while (medItr.hasNext()) {
				Medication med = medItr.next();
				BigDecimal medicationCost = med.getCostPerMg().multiply(map.get(med.getName()));
				currentTotal += medicationCost.floatValue();
			}
			if (max > currentTotal || max == 0) {
				max = currentTotal;
				tempMap.clear();
				for (Medication med : medList) {
					tempMap.put(med, map.get(med.getName()));
				}
			}

		}
		patientMedicationsMap = tempMap;
		return patientMedicationsMap;

	}

	@Override
	public TreatmentPlan treatmentPlanForPatient(Patient patient) {
		Map<Disease, BigDecimal> diseasesMap = diseaseLikelihoods(patient);
		Map<Medication, BigDecimal> medicationsMap = new HashMap<>();
		// Create list of diseases where likeihood of having the disease is >=
		// 70%
		List<Disease> likelyDiseases = diseasesMap.entrySet().stream()
				.filter(d -> d.getValue().compareTo(BigDecimal.valueOf(.7)) >= 0).map(Map.Entry::getKey)
				.collect(Collectors.toList());

		// Create map of all medications + dosages based on likely diseases,
		// with extra care taken to make sure duplicate keys have their values
		// handled properly
		for (Disease d : likelyDiseases) {
			Map<Medication, BigDecimal> tMedicationsMap = medicationsForDisease(patient, d);
			for (Entry<Medication, BigDecimal> entry : tMedicationsMap.entrySet()) {
				if (medicationsMap.containsKey(entry.getKey())) {
					BigDecimal oldValue = medicationsMap.get(entry.getKey());
					BigDecimal newValue = oldValue.add(entry.getValue());
					medicationsMap.put(entry.getKey(), newValue);
				} else {
					medicationsMap.put(entry.getKey(), entry.getValue());
				}
			}
		}
	
		int month = ageInMonths(patient) - (ageInYears(patient) * 12);
		List<Clinic> patientClinics = clinicsBasedOnAgeAndDiseases(patient);
		int year = ageInYears(patient);
		return new TreatmentPlan(year, month, patientClinics, medicationsMap);
	}

}
