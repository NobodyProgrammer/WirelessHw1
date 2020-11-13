public class Threshold extends Policy {
    public boolean Algorithm(Car car) {
        car.setPower();
        if (car.getNowStationPower() > 10) {
            // System.out.println("I am not die!");
            return false;
        } else {
            // System.out.println("i am die");
            double[] power = car.getAllStationPower();
            double max_p = power[0];
            int max_idx = 0;

            // here check there whether there is no station alive
            boolean isAllMinimum = true;
            for (int i = 0; i < 4; ++i) {
                if (power[i] > 10)
                    isAllMinimum = false;

            }
            // all die no change, just wait the car remove from board
            if (isAllMinimum)
                return false;

            for (int i = 0; i < 4; ++i) {
                if (power[i] > max_p) {
                    power[i] = max_p;
                    max_idx = i;
                }
            }
            // the power is die!
            // change power station
            car.setNowStation(max_idx);

            return true;
        }

    }
}
