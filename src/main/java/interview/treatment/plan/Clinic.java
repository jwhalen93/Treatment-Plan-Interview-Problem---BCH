package interview.treatment.plan;

import java.util.List;

/**
 * Created by CH172585 on 9/8/2016.
 */
public class Clinic {
    private final String name;
    private final Integer minAgeInMonths;
    private final Integer maxAgeInMonths;
    private final List<String> diseases;

    public Clinic(String name, Integer minAgeInMonths, Integer maxAgeInMonths, List<String> diseases) {
        this.name = name;
        this.minAgeInMonths = minAgeInMonths;
        this.maxAgeInMonths = maxAgeInMonths;
        this.diseases = diseases;
    }

    /**
     * Get the name of this clinic
     */
    public String getName() {
        return name;
    }

    /**
     * Get the minimum age, in months, of a patient that this clinic
     * will treat. Assume that clinic age ranges will never overlap.
     */
    public Integer getMinAgeInMonths() {
        return minAgeInMonths;
    }

    /**
     * Get the maximum age, in months, of a patient that this clinic
     * will treat. Assume that clinic age ranges will never overlap.
     * If a clinic has a "null" maximum age, the upper limit is infinite.
     */
    public Integer getMaxAgeInMonths() {
        return maxAgeInMonths;
    }

    /**
     * Get a list of disease names that this clinic specializes in treating.
     * The names of the diseases a clinic treats will match the names of
     * diseases in the ALL_DISEASES list exactly.
     */
    public List<String> getDiseases() {
        return diseases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clinic clinic = (Clinic) o;

        return name != null ? name.equals(clinic.name) : clinic.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "name='" + name +
                '}';
    }
}
