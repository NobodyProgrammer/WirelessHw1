
public class Entropy extends Policy {
    private int myEntropy = 9;

    public String getName() {
        return "Entropy";
    }

    public boolean Algorithm(Car car) {
        car.setPower();
        int now_idx = car.getNowStation();
        int new_idx = now_idx;
        int max_idx = now_idx;
        double[] allPower = car.getAllStationPower();
        double now_p = car.getNowStationPower();
        double max_p = now_p;

        for (int i = 0; i < allPower.length; ++i) {
            if (allPower[i] > max_p) {
                max_p = allPower[i];
                max_idx = i;
            }

        }
        if (max_p > now_p + myEntropy) {
            new_idx = max_idx;
        }
        // no change
        if (now_idx == new_idx)
            return false;
        else {
            car.setNowStation(new_idx);
            return true;
        }
    }

    public void adjustEntropy(int handoff) {
    }
}