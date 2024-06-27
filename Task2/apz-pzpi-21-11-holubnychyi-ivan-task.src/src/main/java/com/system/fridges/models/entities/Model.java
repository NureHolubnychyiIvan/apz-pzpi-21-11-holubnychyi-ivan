package com.system.fridges.models.entities;


import jakarta.persistence.*;


@Entity
@Table(name = "model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private int modelId;

    @Column(name = "name_model", nullable = false, unique = true)
    private String nameModel;

    @Column(name = "brand", nullable = false)
    private String  brand;

    @Column(name = "number_boxes", nullable = false)
    private int numberBoxes;

    @Column(name = "energy_per_year", nullable = false)
    private float  energyPerYear;

    public Model(String nameModel, String brand, int numberBoxes, float energyPerYear) {
        this.nameModel = nameModel;
        this.brand = brand;
        this.numberBoxes = numberBoxes;
        this.energyPerYear = energyPerYear;
    }

    public Model() {}

    public int getModelId() {
        return modelId;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumberBoxes() {
        return numberBoxes;
    }

    public void setNumberBoxes(int numberBoxes) {
        this.numberBoxes = numberBoxes;
    }

    public float getEnergyPerYear() {
        return energyPerYear;
    }

    public void setEnergyPerYear(float energyPerYear) {
        this.energyPerYear = energyPerYear;
    }
}
