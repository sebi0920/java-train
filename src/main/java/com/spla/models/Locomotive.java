package com.spla.models;

import com.spla.enums.EngineType;

/**
 * Class for the locomotive. Extends the SuperClass AbstractCoach
 * Extends the SuperClass with the needed fields
 * */
public class Locomotive extends AbstractCoach {

    private int pull;
    private EngineType engineType;

    public Locomotive(double emptyWeight, double length, int num_passengers, double payload_capability, String typeValue, String maker,
                      int yearOfConstruction, String serialNumber, int pull, EngineType engineType) {

        super(emptyWeight, length, num_passengers, payload_capability, typeValue, maker, yearOfConstruction, serialNumber);
        this.pull = pull;
        this.engineType = engineType;
    }


    /**
     * Getter for the engine type of the locomotive
     * @return the type of engine
     * */
    public EngineType getEngineType() {
        return engineType;
    }

    /**
     * Getter for the pull of the locomotive. The pull of the locomotive is the weight it can pull beside its own weight
     * @return the max pull
     * */
    public int getPull() {
        return pull;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Locomotive)) return false;

        Locomotive that = (Locomotive) o;

        return this.getSerialNumber().equals(that.getSerialNumber());
    }

    @Override
    public int hashCode() {
        String serialNumber = this.getSerialNumber();
        return serialNumber.hashCode();
    }

    @Override
    public String toString() {
        return "Locomotive: " +
                "pull=" + pull +
                " engineType=" + engineType +
                " serialNumber=" + this.getSerialNumber();
    }
}
