package Train;

import java.io.IOException;
import java.util.LinkedList;

import static Train.Train.*;
import static Train.TrainConstants.Arrival;
import static Train.TrainConstants.Departure;

public class GeekTrust {
    public static void main(String[] args) throws IOException {

        trainBogiesInput(args[0]);
      LinkedList<String> trainAtoHyb = printTrainABogiesToHyb();
      LinkedList<String> trainBtoHyb = printTrainBBogiesToHyb();
      LinkedList<String> departedTrainABfromHyb =trainDepartedFromHyb();
        
      System.out.print(Arrival.label);
        printList(trainAtoHyb);
        System.out.print(Arrival.label);
        printList(trainBtoHyb);
        System.out.print(Departure.label);
        printList(departedTrainABfromHyb);
    }
}
