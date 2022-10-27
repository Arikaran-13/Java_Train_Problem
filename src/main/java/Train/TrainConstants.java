package Train;

public enum TrainConstants {
    JourneyEnded("JOURNEY_ENDED"),
    TrainA("TRAIN_A"),
    TrainB("TRAIN_B"),
    TrainAB("TRAIN_AB"),
    Arrival("ARRIVAL "),
    Departure("DEPARTURE "),
    trainMergerStation("HYB");
    public final String label;
    private TrainConstants(String label) {
        this.label = label;
    }
}