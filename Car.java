public class Car {
    private double loc_x;
    private double loc_y;
    private double stationPower[] = new double[4];
    private int stationIdx;// now station car listening to
    int station_loc[][] = { { 330, 350 }, { 360, 680 }, { 640, 310 }, { 660, 658 } };

    public Car(double init_x, double init_y) {
        this.loc_x = init_x;
        this.loc_y = init_y;
        setPower();
        initStationIdx();
        for (int i = 0; i < 4; ++i) {
            // System.out.println("Station " + i + "=" + stationPower[i]);
        }

    }

    private void initStationIdx() {
        double max_p = stationPower[0];
        int idx = 0;
        for (int i = 0; i < 4; ++i) {
            if (stationPower[i] > max_p)
                idx = i;
        }
        this.stationIdx = idx;
    }

    public void setPower() {
        for (int i = 0; i < 4; ++i) {
            double distance = (double) Math
                    .sqrt(Math.pow(station_loc[i][0] - loc_x, 2) + Math.pow(station_loc[i][1] - loc_y, 2));
            double p_loss = 32.45 + 20 * Math.log10(distance);
            stationPower[i] = 100 - p_loss;
        }
    }

    public void updateLocation(double x, double y) {
        this.loc_x = x;
        this.loc_y = y;
    }

    public double getLocY() {
        return this.loc_y;
    }

    public double getLocX() {
        return this.loc_x;
    }

    public int getNowStation() {
        return this.stationIdx;
    }

    public void setNowStation(int idx) {
        this.stationIdx = idx;
    }

    public double[] getAllStationPower() {
        return this.stationPower;
    }

    public double getNowStationPower() {
        return this.stationPower[this.stationIdx];
    }
}
