import java.util.ArrayList;
import java.util.Random;

public class project2 {

    public static void main(String[] args) {
        ArrayList<Policy> policy = new ArrayList<Policy>();
        Policy best = new BesePolicy();
        Policy threshold = new Threshold();
        Policy entropy = new Entropy();
        policy.add(best);
        policy.add(threshold);
        policy.add(entropy);
        for (int i = 0; i < 3; ++i) {
            Running run = new Running(policy.get(i), i);
        }

    }

}
