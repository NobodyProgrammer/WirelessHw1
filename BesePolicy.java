public class BesePolicy extends Policy {
    // handoff if there is a more powerful station
    public boolean Algorithm(Car car) {

        car.setPower();// update new power when move car
        int now_idx = car.getNowStation();
        double[] power = car.getAllStationPower();
        double max_p = power[0];
        int max_idx = now_idx;

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
        // there is a more powerful station
        if (now_idx != max_idx) {
            now_idx = max_idx;
            car.setNowStation(now_idx);
            return true;
        } else {
            // System.out.println("i am still strong!");
            return false;
        }

    }
}
