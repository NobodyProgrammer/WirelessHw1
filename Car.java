public class Car {
    private double loc_x;
    private double loc_y;
    private int direction;
    private double stationPower[] = new double[4];
    private int stationIdx;// now station car listening to
    private int station_loc[][] = { { 330, 350 }, { 360, 680 }, { 640, 310 }, { 660, 658 } };

    public Car(double init_x, double init_y, int dir) {
        this.loc_x = init_x;
        this.loc_y = init_y;
        this.direction = dir;
        setPower();
        initStationIdx();

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
            double p_loss;
            double distance = (double) Math
                    .sqrt(Math.pow(station_loc[i][0] - loc_x, 2) + Math.pow(station_loc[i][1] - loc_y, 2));

            // System.out.println(loc_x + " " + loc_y);
            if (distance <= 0)// car location is at the basic station
                p_loss = 32.45;
            else
                p_loss = 32.45 + 20 * Math.log10(distance);

            stationPower[i] = 100 - p_loss;
            if (stationPower[i] > 100)
                System.out.println(stationPower[i]);

        }
    }

    public void turnRight(int turn, int distance) {
        switch (this.direction) {
            case 0:
                this.loc_y -= distance;
                break;
            case 1:
                this.loc_x += distance;
                break;
            case 2:
                this.loc_y += distance;
                break;
            case 3:
                this.loc_x -= distance;
                break;
        }
    }

    public void turnLeft(int turn, int distance) {
        switch (this.direction) {
            case 0:
                this.loc_y += distance;
                break;
            case 1:
                this.loc_x -= distance;
                break;
            case 2:
                this.loc_y += distance;
                break;
            case 3:
                this.loc_x -= distance;
                break;
        }
    }

    public void goStraight(int turn, int distance) {
        switch (this.direction) {
            case 0:
                this.loc_x += distance;
                break;
            case 1:
                this.loc_y += distance;
                break;
            case 2:
                this.loc_x -= distance;
                break;
            case 3:
                this.loc_y -= distance;
                break;
        }
    }

    public double getX() {
        return this.loc_x;
    }

    public double getY() {
        return this.loc_y;
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
