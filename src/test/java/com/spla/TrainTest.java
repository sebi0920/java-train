package com.spla;

import com.spla.enums.CoachType;
import com.spla.enums.EngineType;

import com.spla.exceptions.CoachNotAvailableException;
import com.spla.exceptions.UnableToRemovePrimaryLocomotiveException;
import com.spla.models.Coach;
import com.spla.models.Locomotive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class TrainTest {
    private Train train;

    TrainTest() throws CoachNotAvailableException {

        Locomotive primaryLocomotive = new Locomotive(12330.0, 5.3, 0, 1000, "someLoc", "SomeLocInc", 2019, "111111", 500000 , EngineType.Steam);
        train = new Train(primaryLocomotive);
        Coach firstCoach = new Coach(1000.0, 6, 30, 10000, "someCoach", "SomeCoachInc", 2019, "111112", CoachType.Carriage);
        train.addWagon(firstCoach);
    }


    @Test
    void testEmptyWeight(){
        Assertions.assertEquals(13330.0, train.getEmptyWeight());
    }

    @Test
    void testTrainPassengers(){
        Assertions.assertEquals(30, train.getMaxPassengers());
    }

    @Test
    void testMaxPayloadWeight(){
        Assertions.assertEquals(11000, train.getMaxPayloadWeight());
    }

    @Test
    void testGetMaxPayload(){
        Assertions.assertEquals(13250, train.getMaxPayload());
    }

    @Test
    void testGetMaxTotalWeight(){
        Assertions.assertEquals(26580, train.getMaxTotalWeight());
    }

    @Test
    void testGetTotalLength(){
        Assertions.assertEquals(11.3, train.getTotalLength());
    }

    @Test
    void testTrainCanPull(){
        Assertions.assertTrue(train.hasTrainEnoughPull());
    }

    @Test
    void testIsConductorNeeded(){
        Assertions.assertTrue(train.isConductorNeeded());
    }

    @Test
    void testNumConductorsNeeded(){
        Assertions.assertEquals(1, train.getNumConductorsNeeded());
    }

    @Test
    void testAddWagon(){
        Coach firstCoach = new Coach(1000.0, 6, 30, 10000, "someCoach", "SomeCoachInc", 2019, "111112", CoachType.Carriage);

        Assertions.assertThrows(CoachNotAvailableException.class, () -> {
            train.addWagon(firstCoach);
        });
    }

    @Test
    void testRemovePrimaryLocomotive(){
        Assertions.assertThrows(UnableToRemovePrimaryLocomotiveException.class, () -> {
            train.removeWagon(0);
        });
    }

    @Test
    void testReplaceOutOfBoundCoach(){
        Coach firstCoach = new Coach(1000.0, 6, 30, 10000, "someCoach", "SomeCoachInc", 2019, "111112", CoachType.Carriage);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            train.replaceWagon(3, firstCoach);
        });
    }



}
