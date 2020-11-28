public class DynamicEntropy extends Policy {
    private double myEntropy = 9.0;

    public String getName() {
        return "DynamicEntropy";
    }

    public boolean Algorithm(Car car) {

        car.setPower();
        int now_idx = car.getNowStation();
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
            car.setNowStation(max_idx);
            return true;
        } else
            return false;
    }

    // dynamic adjust the entropy base on the handoff
    public void adjustEntropy(int handoff) {

        double rate = ((double) handoff / 9);
        // System.out.println(rate);
        if (rate > 0 && rate < 1)
            this.myEntropy = rate * 9 + 9;
        else if (rate > 1)
            rate = 18;
        else
            this.myEntropy = 9;
        // System.out.println(handoff + " " + this.myEntropy);
    }
}
