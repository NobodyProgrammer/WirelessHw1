import java.util.ArrayList;

public class Project2 {

    public static void main(String[] args) {
        ArrayList<Policy> policy = new ArrayList<Policy>();
        Policy best = new BestPolicy();
        Policy min = new Threshold(10);
        Policy threshold = new Threshold(14.5);
        Policy entropy = new Entropy();
        Policy Dentropy = new DynamicEntropy();
        policy.add(best);
        policy.add(min);
        policy.add(threshold);
        policy.add(entropy);
        policy.add(Dentropy);
        // Running run = new Running(policy.get(0), 0);
        for (int i = 0; i < 5; ++i) {
            Running run = new Running();
            run.runPolicy(policy.get(i), i);
        }

    }

}
