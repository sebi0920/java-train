package com.spla;


import com.spla.exceptions.CoachNotAvailableException;
import com.spla.exceptions.UnableToRemovePrimaryLocomotiveException;
import com.spla.models.AbstractCoach;
import com.spla.models.Coach;
import com.spla.models.Locomotive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for the train. The main data structure is a list.
 * List to keep the train elements in a correct order and keep the complexity down.
 * */

public class Train {


    private List<AbstractCoach> trainAssembly = new ArrayList<AbstractCoach>();

    /**
     * Constructor used for creating an instance of train.
     * @param primaryLocomotive: the necessary primary locomotive used for the train
     * @throws CoachNotAvailableException : if the given locomotive is already used in another train
     * */
    public Train(Locomotive primaryLocomotive) throws CoachNotAvailableException {
        if(primaryLocomotive.getAssignedTrain() != null){
            throw new CoachNotAvailableException(primaryLocomotive);
        }

        trainAssembly.add(primaryLocomotive);
    }

    /**
     * Adds a wagon or an engine to the end of the train
     * @param coach: the wagon to add
     * @throws CoachNotAvailableException if the coach is already in a train
     * */
    public boolean addWagon(AbstractCoach coach) throws CoachNotAvailableException {
        if(coach.getAssignedTrain() != null){
            throw new CoachNotAvailableException(coach);
        }

        if(trainAssembly.contains(coach)){
            throw new CoachNotAvailableException(coach);
        }
        coach.setAssignedTrain(this);

        return trainAssembly.add(coach);
    }


    /**
     * Calculates the empty weight of the train by using Java Streams
     * @return the total weight as a double
     * */
    public double getEmptyWeight(){
        return trainAssembly.stream().mapToDouble(AbstractCoach::getEmptyWeight).sum();
    }

    /**
     * Calculate the maximum number of passengers using by Java Streams
     * @return the total number of passengers as an integer
     * */
    public int getMaxPassengers(){
        return trainAssembly.stream().mapToInt(AbstractCoach::getNumPassengers).sum();
    }

    /**
     * Calculate the maximum payload weight by using Java Streams
     * @return the maximum payload as a double
     * */
    public double getMaxPayloadWeight(){
        return trainAssembly.stream().mapToDouble(AbstractCoach::getPayloadCapability).sum();
    }

    /**
     * Calculates the maximum payload including the weight of the passengers
     * @return the maximum payload as a double
     */
    public double getMaxPayload(){
        return getMaxPassengers()*75 + getMaxPayloadWeight();
    }

    /**
     * Calculates the maximum total weight of the train by using Java Streams
     * @return the total maximum weight of the train as a double
     * */
    public double getMaxTotalWeight(){
        return getMaxPayload() + trainAssembly.stream().mapToDouble(AbstractCoach::getEmptyWeight).sum();
    }

    /**
     * Calculates the total length of the train
     * @return returns the length of the train as a double
     * */
    public double getTotalLength(){
        return trainAssembly.stream().mapToDouble(AbstractCoach::getLength).sum();
    }

    /**
     * Calculates the maximum pull strength in kg
     * @return the maximum pull strength as a double
     * */
    public double getMaxPull(){
        return trainAssembly.stream().filter(abstractCoach -> abstractCoach instanceof Locomotive).mapToDouble(value -> ((Locomotive)value).getPull()).sum();
    }

    /**
     * Calculates if the train has enough pull strength to pull the train
     *
     * Therefore we calculate the weight of the locomotive, the maximum pull strength of the train
     * and the total weight of the train
     * This is necessary because the pull strength of the locomotive already excludes the weight of itself
     * Therefore we subtract the weight of all locomotives from the total weight and compare it to the maximum pull force
     *
     * @return true if the locomotive(s) is/are strong enough
     * */
    public boolean hasTrainEnoughPull(){
        double locomotiveWeight = trainAssembly.stream().filter(abstractCoach -> abstractCoach instanceof Locomotive).mapToDouble(AbstractCoach::getEmptyWeight).sum();

        return !((getMaxTotalWeight() - locomotiveWeight) > getMaxPull());
    }

    /**
     * Checks if a conductor is needed
     * @return true if the number of passengers is greater than 0
     * */
    public boolean isConductorNeeded(){
        return getMaxPassengers() > 0;
    }

    /**
     * Calculates the number of conductors needed. For 50 possible passengers there must be one conductor
     * @return the number of conductors needed
     * */
    public int getNumConductorsNeeded(){
        if(!isConductorNeeded())
            return 0;
        else{

            if(getMaxPassengers()%50 == 0){
                return getMaxPassengers()/50;
            }
            return getMaxPassengers()/50 + 1;
        }
    }

    /**
     * Removes the wagon at the given index and returns it. It also removes the context of the train
     * Therefore the wagon can be reused in this or another train
     * @param index the index of the wagon to be removed
     * @return the removed wagon
     * @throws UnableToRemovePrimaryLocomotiveException when tried to remove the primary locomotive
     * @throws IndexOutOfBoundsException if the index is greater than the train
     * */
    public AbstractCoach removeWagon(int index) throws UnableToRemovePrimaryLocomotiveException {
        if(index == 0){
            throw new UnableToRemovePrimaryLocomotiveException();
        }else if(index >= trainAssembly.size()){
            throw new IndexOutOfBoundsException();
        }
        AbstractCoach removedCoach = trainAssembly.remove(index);
        removedCoach.setAssignedTrain(null);
        return removedCoach;
    }

    /**
     * Replaces a wagon at the given index
     * @param index the index for the wagon to be replaced
     * @param coach the coach to replace
     * @throws IndexOutOfBoundsException if the index is greater than the train
     * @throws UnableToRemovePrimaryLocomotiveException if the primary locomotive is tried to be removed
     * */
    public void replaceWagon(int index, AbstractCoach coach) throws UnableToRemovePrimaryLocomotiveException {
        if(index >= trainAssembly.size()){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0 && coach instanceof Coach){
            throw new UnableToRemovePrimaryLocomotiveException();
        }
        coach.setAssignedTrain(this);
        trainAssembly.set(index, coach);
    }

    @Override
    public String toString() {
        return "Train{" + Arrays.toString(trainAssembly.toArray()) + '}';
    }
}
