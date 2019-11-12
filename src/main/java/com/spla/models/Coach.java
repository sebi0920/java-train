package com.spla.models;

import com.spla.enums.CoachType;

/**
 * Class for the coach of the train.
 * Contains a field for setting the type of coach
 * */

public class Coach extends AbstractCoach {

    private CoachType coachType;


    public Coach(double emptyWeight, double length, int num_passengers, double payload_capability, String typeValue, String maker, int yearOfConstruction, String serialNumber, CoachType coachType) {
        super(emptyWeight, length, num_passengers, payload_capability, typeValue, maker, yearOfConstruction, serialNumber);

        this.coachType = coachType;
    }

    /**
     * Getter for the coach type
     * @return the type of the coach
     * */
    public CoachType getCoachType() {
        return coachType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach)) return false;

        Coach coach = (Coach) o;

        return this.getSerialNumber().equals(coach.getSerialNumber());
    }


    @Override
    public int hashCode() {
        return this.getSerialNumber().hashCode();
    }

    @Override
    public String toString() {
        return "Coach: coachType="+this.getCoachType() + " serialNumber="+this.getSerialNumber();
    }
}
