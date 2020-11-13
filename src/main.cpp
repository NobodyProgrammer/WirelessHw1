#include <iostream>
#include <vector>
#include <time.h>
#include <math.h>
#include <random>
using namespace std;
bool possion(int reciprocal, double delta)
{
    double rate = 1 / reciprocal;
    double num = (-rate) * delta;
    double prob = 1 - exp(num);
    double random = ((double)rand() / RAND_MAX);

    if (prob > random)
        return true;
    else
        return false;
}

int main()
{
    cout << "fuck";
    srand(time(NULL));
    double Pt = 100;
    double P_min = 10;
    int car_time[3] = {2, 3, 5};
    int now_time = 0;
    int volecity = 10;
    int handoff = 0;
    std::vector<int> loc_x;
    std::vector<int> loc_y;
    for (int i = 0; i < 3; ++i)
    {
        while (now_time <= 86400000)
        {
            //car move
            for (int j = 0; j < loc_x.size(); ++j)
            {
                int direction = rand() % 5;
                switch (direction)
                {
                //right
                case 0:
                    loc_y[j] -= 100;
                    break;
                //left
                case 1:
                    loc_y[j] += 100;
                    break;
                //straight
                default:
                    loc_x[j] += 100;
                    break;
                }
                //car leave
                if (loc_x[j] > 1000 || loc_y[j] < 0 || loc_y[j] > 1000)
                {
                }
            }

            //car arrive
            if (now_time % car_time[i] == 0)
            {
                //car can enter
                if (possion(car_time[i], 0.001))
                {
                    int init_y = (rand() % 10) * 100;
                    loc_x.push_back(0);
                    loc_y.push_back(init_y);
                }
            }
            ++now_time;
        }
    }
}
