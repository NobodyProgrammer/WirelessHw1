public class Threshold extends Policy {
    private double threshold = 0;

    public Threshold(double t) {
        this.threshold = t;
    }

    public String getName() {
        if (this.threshold <= 10)
            return "Pmin";
        else
            return "Threshold";
    }

    public boolean Algorithm(Car car) {
        car.setPower();
        double[] power = car.getAllStationPower();
        double now_p = car.getNowStationPower();
        int now_idx = car.getNowStation();
        int new_idx = now_idx;
        if (now_p > threshold) {
            // System.out.println("I am not die!");
            return false;
        } else {
            // System.out.println("i am die");

            for (int i = 0; i < 4; ++i) {
                if (power[i] > now_p) {
                    now_p = power[i];
                    new_idx = i;
                }
            }
            // the power is die!
            // change power station
            if (new_idx != now_idx) {
                car.setNowStation(new_idx);
                return true;
            } else {
                return false;
            }
        }

    }

    public void adjustEntropy(int handoff) {
    }
}
