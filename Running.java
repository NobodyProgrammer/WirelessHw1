import java.util.ArrayList;
import java.util.Random;

public class Running {
    private static Random rand = new Random();
    private int car_time[] = { 2, 3, 5 };
    private int now_time = 0;
    private int velocity = 10;
    private int handoff = 0;
    private double totalPower = 0;
    private int totalCar = 0;
    private ArrayList<Car> myCars = new ArrayList<Car>();

    private void powerAvg(int now_time, int idx) {
        if (now_time % 1000 == 0) {
            totalCar += myCars.size();
            for (int i = 0; i < myCars.size(); ++i) {

                totalPower += myCars.get(i).getNowStationPower();

            }
        }
    }

    public Running(Policy policy, int policy_idx) {
        // System.out.println("init handoff=" + handoff);
        // Policy all_policy[] = new Policy[3];

        for (int i = 0; i < 3; ++i) {
            while (now_time <= 86400000 || myCars.size() != 0) {
                // count the power every second
                powerAvg(now_time, policy_idx);
                // if (policy_idx == 1)
                // System.out.println("car size=" + myCars.size());
                // car move
                for (int j = 0; j < myCars.size(); ++j) {
                    int direction = rand.nextInt(5);
                    double now_y = myCars.get(j).getLocY();
                    double now_x = myCars.get(j).getLocX();
                    switch (direction) {
                        // right
                        case 0:
                            now_y -= velocity;
                            myCars.get(j).updateLocation(now_x, now_y);
                            break;
                        // left
                        case 1:
                            now_y += velocity;
                            myCars.get(j).updateLocation(now_x, now_y);
                            break;
                        // straight
                        default:
                            now_x += velocity;
                            myCars.get(j).updateLocation(now_x, now_y);
                            break;
                    }
                    // car is leave?
                    if (now_y > 1000 || now_y < 0 || now_x > 1000) {
                        myCars.remove(j);
                        --j;
                    }
                    // need to handoff station?
                    else {

                        // there is a handoff station occur!!
                        if (policy.Algorithm(myCars.get(j)))
                            handoff++;

                    }

                }

                // car arrive
                if (now_time % car_time[0] == 0) {
                    // car can enter
                    if (possion(car_time[0], 0.001)) {
                        // System.out.println("successful");
                        int init_y = rand.nextInt(10) * 100;
                        // System.out.println(init_y);
                        Car car = new Car(0, init_y);
                        myCars.add(car);
                    }
                }

                ++now_time;
            }

            // change next lamda

            double p_avg = totalPower / totalCar;
            System.out.println("lamda=" + (double) 1 / car_time[i]);
            System.out.println("handoff=" + handoff);
            // System.out.println("total car=" + totalCar);
            // System.out.println("total power=" + totalPower);
            System.out.println("average power=" + p_avg + "\n");

            handoff = 0;
            totalCar = 0;
            totalPower = 0;
            now_time = 0;
        }
        // System.out.println("now car=" + myCars.size());
        // System.out.println("handoff=" + handoff);

    }

    public boolean possion(int reciprocal, double delta) {
        double rate = 1 / (double) reciprocal;
        // System.out.println(rate);
        double num = (-rate) * delta;
        double prob = 1 - Math.exp(num);
        double random = Math.random();
        // System.out.println(prob);
        if (prob > random)
            return true;
        else
            return false;
    }
}
