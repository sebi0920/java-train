package com.spla.exceptions;

import com.spla.models.AbstractCoach;

/**
 * Thrown if the coach is already in the train in order to prevent circles or using an unavailable coach
 * this exception will be thrown.
 * */
public class CoachNotAvailableException extends Throwable{

    public CoachNotAvailableException(AbstractCoach coach) {
        super("Coach with serial number: "+coach.getSerialNumber()+" is not available!");
    }


}
