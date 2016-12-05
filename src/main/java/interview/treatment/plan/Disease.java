package interview.treatment.plan;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by CH172585 on 9/8/2016.
 */
public class Disease {
    private final String name;
    private final List<String> symptoms;
    private final List<Map<String, BigDecimal>> medicationCombinations;

    public Disease(String name, List<String> symptoms, List<Map<String, BigDecimal>> medicationCombinations) {
        this.name = name;
        this.symptoms = symptoms;
        this.medicationCombinations = medicationCombinations;
    }

    /**
     * Get the name of this disease
     */
    public String getName() {
        return name;
    }

    /**
     * Get a list of symptoms for this disease.
     */
    public List<String> getSymptoms() {
        return symptoms;
    }

    /**
     * Get a list of treatment options for this disease. A treatment option
     * is a Map of Medication Name to Dosage per kg. Only one treatment
     * option is required to treat a disease. The Medication Names will match
     * names in the ALL_MEDICATIONS list exactly.
     */
    public List<Map<String, BigDecimal>> getMedicationCombinations() {
        return medicationCombinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disease disease = (Disease) o;

        return name != null ? name.equals(disease.name) : disease.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "name='" + name +
                "'}";
    }
}
