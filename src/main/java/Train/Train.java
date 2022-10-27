package Train;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static Train.TrainConstants.*;

public class Train{  // "D:\\Arikaran\\train.txt"
    private static Map<String, Integer> trainARoutes;
    private static Map<String, Integer> trainBRoutes;
    static List<String> trainABogies;
    static List<String> trainBBogies;
    private static LinkedList<String> mergedTrain;
    private static LinkedList<String> trainMergedFromHyb;

    static {
        trainARoutes = new LinkedHashMap<>();
        trainBRoutes = new LinkedHashMap<>();
        trainABogies = new LinkedList<>();
        trainBBogies = new LinkedList<>();
        mergedTrain = new LinkedList<>();
        trainARoutes.put("CHN", 0);
        trainARoutes.put("SLM", 350);
        trainARoutes.put("BRN", 550);
        trainARoutes.put("KRN", 900);
        trainARoutes.put("HYB", 1200);
        trainARoutes.put("NGP", 1600);
        trainARoutes.put("ITJ", 1900);
        trainARoutes.put("BPL", 2000);
        trainARoutes.put("AGA", 2500);
        trainARoutes.put("NDL", 2700);

        trainBRoutes.put("TVC", 0);
        trainBRoutes.put("SRR", 300);
        trainBRoutes.put("MAQ", 600);
        trainBRoutes.put("MAO", 1000);
        trainBRoutes.put("PNE", 1400);
        trainBRoutes.put("HYB", 2000);
        trainBRoutes.put("NGP", 2400);
        trainBRoutes.put("ITJ", 2700);
        trainBRoutes.put("BPL", 2800);
        trainBRoutes.put("PTA", 3800);
        trainBRoutes.put("NJP", 4200);
        trainBRoutes.put("GHY", 4700);

    }

    private static int trainADistanceFromSource = trainARoutes.get(TrainConstants.trainMergerStation.label);
    private static int trainBDistanceFromSource = trainBRoutes.get(TrainConstants.trainMergerStation.label);

    public static void trainBogiesInput(String s) throws IOException {
        List<String> trainsIpt = Files.readAllLines(Paths.get(s));

        String stationNamesTrainA = trainsIpt.get(0);
        String stationNamesTrainB = trainsIpt.get(1);

        trainABogies = Arrays.stream(stationNamesTrainA.split(" "))
                .collect(Collectors.toList());
        trainBBogies = Arrays.stream(stationNamesTrainB.split(" "))
                .collect(Collectors.toList());

    }

    public static void printList(List<String> list) {
        list.forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    public static LinkedList<String> printTrainABogiesToHyb() {

        LinkedList<String> trainAtoHyb = trainABogies.stream().skip(2)
                .filter(i -> trainARoutes.containsKey(i) && trainARoutes.get(i) >= trainADistanceFromSource ||
                        trainBRoutes.containsKey(i) && trainBRoutes.get(i) >= trainBDistanceFromSource)
                .collect(Collectors.toCollection(LinkedList::new));

        if (trainAtoHyb.isEmpty()) {
            System.out.println(JourneyEnded.label);
        }

     
        trainAtoHyb.addFirst("ENGINE");
        trainAtoHyb.addFirst(TrainA.label);
        mergedTrain.addAll(trainAtoHyb);
        
        return trainAtoHyb;

    }

    public static  LinkedList<String> printTrainBBogiesToHyb() {

        LinkedList<String> trainBtoHyb = trainBBogies.stream().skip(2)
                .filter(i -> trainARoutes.containsKey(i) && trainARoutes.get(i) >= trainADistanceFromSource ||
                        trainBRoutes.containsKey(i) && trainBRoutes.get(i) >= trainBDistanceFromSource)
                .collect(Collectors.toCollection(LinkedList::new));

        if (trainBtoHyb.isEmpty()) {
            System.out.println(JourneyEnded.label);
        }
       
        trainBtoHyb.addFirst("ENGINE");
        trainBtoHyb.addFirst(TrainB.label);

       
        mergedTrain.addAll(trainBtoHyb);
        return trainBtoHyb;
    }

    public static  LinkedList<String>  trainDepartedFromHyb() {
        Comparator<String> comp = (String s1, String s2) -> {
            int value1 = 0, value2 = 0;
            if (trainARoutes.containsKey(s1)) {
                value1 = trainARoutes.get(s1);
            } else if (trainBRoutes.containsKey(s1)) {
                value1 = trainBRoutes.get(s1);
            }
            if (trainARoutes.containsKey(s2)) {
                value2 = trainARoutes.get(s2);
            } else if (trainBRoutes.containsKey(s2)) {
                value2 = trainBRoutes.get(s2);
            }

            if (value1 < value2) {
                return 1;
            } else if (value1 > value2) {
                return -1;
            }
            return 0;
        };
        trainMergedFromHyb = mergedTrain.stream()
                .filter(i -> trainARoutes.containsKey(i) && trainARoutes.get(i) > trainADistanceFromSource ||
                        trainBRoutes.containsKey(i) && trainBRoutes.get(i) > trainBDistanceFromSource)
                .sorted(comp).collect(Collectors.toCollection(LinkedList::new));

        trainMergedFromHyb.addFirst(trainABogies.get(1));
        trainMergedFromHyb.addFirst(trainBBogies.get(1));
        trainMergedFromHyb.addFirst(TrainAB.label);
      
     
        return trainMergedFromHyb;
    }

}
