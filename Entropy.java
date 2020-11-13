
public class Entropy extends Policy {
    private int myEntropy = 40;

    public boolean Algorithm(Car car) {
        car.setPower();
        int now_idx = car.getNowStation();
        int new_idx = now_idx;
        double[] allPower = car.getAllStationPower();
        double now_p = car.getNowStationPower();
        for (int i = 0; i < allPower.length; ++i) {
            if (allPower[i] > now_p + myEntropy) {
                new_idx = i;
                now_p = allPower[i];
            }

        }
        // no change
        if (now_idx == new_idx)
            return false;
        else {
            car.setNowStation(new_idx);
            return true;
        }
    }
}