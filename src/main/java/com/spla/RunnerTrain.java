package com.spla;

import com.spla.enums.CoachType;
import com.spla.enums.EngineType;
import com.spla.exceptions.CoachNotAvailableException;
import com.spla.exceptions.UnableToRemovePrimaryLocomotiveException;
import com.spla.models.Coach;
import com.spla.models.Locomotive;

/**
 * Just a simple runner class to see the train in action
 * */
public class RunnerTrain {


    public static void main(String[] args) throws CoachNotAvailableException, UnableToRemovePrimaryLocomotiveException {

        Locomotive primaryLocomotive = new Locomotive(12330.0, 5.3, 0, 1000, "someLoc", "SomeLocInc", 2019, "111111", 500000 , EngineType.Steam);

        Train someTrain = new Train(primaryLocomotive);

        Coach firstCoach = new Coach(1000.0, 6, 30, 10000, "someCoach", "SomeCoachInc", 2019, "111112", CoachType.Carriage);

        Coach dinerCoach = new Coach(1000.0, 6, 30, 10000, "someCoach", "SomeCoachInc", 2019, "111113", CoachType.Diner);

        someTrain.addWagon(firstCoach);
        someTrain.addWagon(dinerCoach);

        System.out.println(someTrain);

        Coach betterDinerCoach = new Coach(1000.0, 8, 40, 15000, "someCoach", "SomeCoachInc", 2019, "111114", CoachType.Diner);

        someTrain.replaceWagon(2, betterDinerCoach);

        System.out.println(someTrain);

        Locomotive additionalEngine = new Locomotive(15000.0, 9, 0, 1000, "someLoc", "SomeLocInc", 2019, "111115", 1000000 , EngineType.Diesel);

        someTrain.addWagon(additionalEngine);

        System.out.println(someTrain);

        System.out.println("MaxPayload: "+someTrain.getMaxPayload());
        System.out.println("Number Conductors: "+someTrain.getNumConductorsNeeded());

    }
}
