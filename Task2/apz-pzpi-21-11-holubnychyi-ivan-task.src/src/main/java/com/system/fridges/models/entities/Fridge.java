package com.system.fridges.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeSpending;
import jakarta.persistence.*;

/** These annotations must be here. Annotations NamedNativeQuery, SqlResultSetMapping parse data
 from the custom query and put this data into the DTO object.
 */
@NamedNativeQuery(
        name = "FridgeSpendingQuery",
        query =
                "SELECT f.fridge_id as fridge_id, m.name_model as name_model, m.energy_per_year as energy_per_year, m.energy_per_year * :priceForElectricity AS spendingMoney \n" +
                        "            FROM fridge as f \n" +
                        "            LEFT JOIN model as m ON f.model_id = m.model_id WHERE :nameCompany IN (SELECT name_company FROM office)",
        resultSetMapping = "FridgeSpendingMapping"
)
@SqlResultSetMapping(
        name = "FridgeSpendingMapping",
        classes = @ConstructorResult(
                targetClass = FridgeSpending.class,
                columns = {
                        @ColumnResult(name = "fridge_id", type = Integer.class),
                        @ColumnResult(name = "name_model", type = String.class),
                        @ColumnResult(name = "energy_per_year", type = Double.class),
                        @ColumnResult(name = "spendingMoney", type = Double.class)
                }
        )
)

@Entity
@Table(name = "fridge")
public class Fridge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fridge_id")
    private int fridgeId;

    @Column(name = "temperature", nullable = false)
    private float temperature;

    @Column(name = "humidity", nullable = false)
    private float humidity;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    public Fridge(float temperature, float humidity, Office office, Model model) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.office = office;
        this.model = model;
    }

    public Fridge() {
    }

    public int getFridgeId() {
        return fridgeId;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
