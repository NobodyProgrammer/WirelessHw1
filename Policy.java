public abstract class Policy {
    public abstract boolean Algorithm(Car car);

    public abstract void adjustEntropy(int handoff);

    public abstract String getName();

}
