package com.spla.models;

import com.spla.Train;

/**
 * Abstract-Super-Class for all members of a train.
 * Contains all the needed fields of a train member (coach or engine)
 * */
public abstract class AbstractCoach {

    private double emptyWeight;
    private double length;

    private int numPassengers;
    private double payloadCapability;

    private String typeValue;
    private String maker;
    private int yearOfConstruction;

    private String serialNumber;
    private Train assignedTrain;

    AbstractCoach(double emptyWeight, double length, int numPassengers, double payloadCapability, String typeValue, String maker, int yearOfConstruction, String serialNumber) {
        this.emptyWeight = emptyWeight;
        this.length = length;
        this.numPassengers = numPassengers;
        this.payloadCapability = payloadCapability;
        this.typeValue = typeValue;
        this.maker = maker;
        this.yearOfConstruction = yearOfConstruction;
        this.serialNumber = serialNumber;
    }

    /**
     * Getter for emptyWeight
     * @return the emptyWeight
     * */
    public double getEmptyWeight() {
        return emptyWeight;
    }

    /**
     * Getter for the length
     * @return the length
     * */
    public double getLength() {
        return length;
    }

    /**
     * Getter for the number of passengers
     * @return the number of passengers
     * */
    public int getNumPassengers() {
        return numPassengers;
    }

    /**
     * Getter for the payload capability
     * @return the payload capability
     * */
    public double getPayloadCapability() {
        return payloadCapability;
    }

    /**
     * Getter for the Type of the Coach
     * @return the type of the train
     * */
    public String getTypeValue() {
        return typeValue;
    }

    /**
     * Getter for the maker of the coach
     * @return the name of the maker
     * */
    public String getMaker() {
        return maker;
    }

    /**
     * Getter for the year of construction of the coach
     * @return the year of construction
     * */
    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    /**
     * Getter for the serial number of the coach
     * @return the serialnumber
     * */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Getter for the assigned train. Returns the instance of the train to which this coach is assigned to
     * or null if the coach is unassigned
     * @return null or the instance of the train
     * */
    public Train getAssignedTrain() {
        return assignedTrain;
    }

    /**
     * Setter for the train. This must be called if the coach is assigned to the train.
     * @param assignedTrain the instance of the assigned train
     * */
    public void setAssignedTrain(Train assignedTrain) {
        this.assignedTrain = assignedTrain;
    }


}
