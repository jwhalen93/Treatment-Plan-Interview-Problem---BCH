package interview.treatment.plan;

import java.math.BigDecimal;

/**
 * Created by CH172585 on 9/8/2016.
 */
public class Medication {
    private final String name;
    private final BigDecimal costPerMg;

    public Medication(String name, BigDecimal costPerMg) {
        this.name = name;
        this.costPerMg = costPerMg;
    }

    /**
     * Get the name of this medication
     */
    public String getName() {
        return name;
    }

    /**
     * Get the cost per mg for this medication
     */
    public BigDecimal getCostPerMg() {
        return costPerMg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medication that = (Medication) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "name='" + name +
                "'}";
    }
}
