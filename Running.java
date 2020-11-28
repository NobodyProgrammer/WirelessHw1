import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.nio.Buffer;

public class Running {
    private static Random rand = new Random();
    private int car_time[] = { 2, 3, 5 };
    private int now_time = 0;
    private int distance = 10;
    private int total_handoff = 0;
    private double totalPower = 0;
    private int totalCar = 0;
    private ArrayList<Car> myCars = new ArrayList<Car>();

    public Running() {

    }

    public void runPolicy(Policy policy, int policy_idx) {
        System.out.println(policy.getName());

        for (int i = 0; i < 3; ++i) {

            try {
                File outFile = new File(policy.getName() + "_" + car_time[i] + ".csv");
                // outFile.createNewFile();
                BufferedWriter wr = new BufferedWriter(new FileWriter(outFile));
                wr.write("time,handoff,total,avgPower\n");
                int handoff_sec = 0;
                while (now_time <= 86400000) {
                    powerAdd(now_time, policy_idx);
                    // car move
                    int handoff = carMove(policy);
                    handoff_sec += handoff;
                    if (now_time % 1000 == 0) {
                        wr.write(now_time + "," + handoff_sec + "\n");
                        handoff_sec = 0;

                    }

                    total_handoff += handoff;
                    // car arrive
                    if (now_time % car_time[i] == 0) {
                        // car can enter(every direction should be take possion distribution)
                        initCarLocation(car_time[i]);
                    }
                    ++now_time;
                }

                double p_avg = totalPower / totalCar;
                System.out.println("lamda=" + (double) 1 / car_time[i]);
                System.out.println("handoff=" + total_handoff);
                // System.out.println("total car=" + totalCar);
                // System.out.println("total power=" + totalPower);
                System.out.println("average power=" + p_avg + "\n");
                wr.write(" , ," + total_handoff + "," + p_avg);
                total_handoff = 0;
                totalCar = 0;
                totalPower = 0;
                now_time = 0;
                myCars.clear();
                wr.close();

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("error!");
            }

        }
        System.out.println("-----------------------------------");

    }

    private void powerAdd(int now_time, int idx) {
        if (now_time % 1000 == 0) {
            totalCar += myCars.size();
            for (int i = 0; i < myCars.size(); ++i) {

                totalPower += myCars.get(i).getNowStationPower();

            }
        }
    }

    private int carMove(Policy policy) {
        int handoff = 0;
        for (int j = 0; j < myCars.size(); ++j) {
            int turn = rand.nextInt(5);
            switch (turn) {
                // right
                case 0:
                    myCars.get(j).turnRight(turn, distance);
                    break;
                // left
                case 1:
                    myCars.get(j).turnLeft(turn, distance);
                    break;
                // straight
                default:
                    myCars.get(j).goStraight(turn, distance);
                    break;
            }
            double now_x = myCars.get(j).getX();
            double now_y = myCars.get(j).getY();
            // car is leave?
            if (now_y > 1000 || now_y < 0 || now_x > 1000) {
                myCars.remove(j);
                --j;
            }
            // need to handoff station?
            else {

                // there is a handoff station occur!!
                if (policy.Algorithm(myCars.get(j))) {
                    handoff++;
                    // handoff_persecond++;
                }

            }
        }
        policy.adjustEntropy(handoff);
        // System.out.println(handoff);

        return handoff;
    }

    private void initCarLocation(int lamda_reverse) {
        for (int dir = 0; dir < 4; ++dir) {
            for (int loc = 100; loc < 1000; loc += 100) {
                if (possion(lamda_reverse, 0.001)) {
                    Car car = null;
                    switch (dir) {
                        case 0:
                            car = new Car(0, loc, dir);
                            break;
                        case 1:
                            car = new Car(loc, 0, dir);
                            break;
                        case 2:
                            car = new Car(1000, loc, dir);
                            break;
                        case 3:
                            car = new Car(loc, 1000, dir);
                            break;
                    }
                    myCars.add(car);

                }
            }
        }
    }

    private boolean possion(int reverse, double delta) {
        double lamda = 1 / (double) reverse;
        // System.out.println(rate);
        double num = (-lamda) * delta;
        double prob = 1 - Math.exp(num);
        double random = Math.random();
        // System.out.println(prob + " " + random);
        if (prob > random)
            return true;
        else
            return false;
    }
}
