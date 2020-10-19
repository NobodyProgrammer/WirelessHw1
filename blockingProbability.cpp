#include<iostream>
#include<cstdlib>
#include<ctime>
#include<math.h>
using namespace std;
bool possion(double rate,int num,int time){
    double inside=(-rate)*num*time;
    double prob=exp(inside);
    double random = (double) rand() / (RAND_MAX+1.0);
    //cout<<random<<endl;
    bool match=false;
    prob > random ? match = true : match = false;
    
    //cout<<prob<<" "<<random<<endl;
    return match;
}
int main(){
    srand(time(NULL));
    int time_unit=100000;
    int count=0;
    int server[3]={1,5,10};
    int queue=0;
    double lambda;
    double mu;
    int block_num=0;
    int now_server=0;
    int now_queue=0;
    double blockingProbability[72];
    int block_count=0;
    
    for(int i=0;i<3;++i){
        cout<<endl<<endl;
        for(lambda=0.01;lambda<=10;lambda*=10){
            for(mu=0.01;mu<=10.24;mu*=4){
                while (count<time_unit)
                {

                    //for queue
                    bool isArrive=possion(lambda,1,count);
                    if(isArrive){
                        //cout<<now_server<<" ";
                        if(now_server>=server[i]){
                            //cout<<"full server!"<<endl;
                            if(now_queue>=queue)
                                ++block_num;
                            else
                                ++now_queue;
                        }
                        else{
                            ++now_server;
                        }
                    }
                    bool isDeparture=possion(mu,now_server,count);
                    if(isDeparture){
                        if(now_server>0){
                            //由queue補進來
                            if(now_queue>0)
                                --queue;
                            //queue empty 有 server閒置
                            else
                                --now_server;
                        }
                    }
                    ++count;
                }

                
                blockingProbability[block_count]=((double)block_num/(double)time_unit);
                //cout<<"blocking probability is "<<blockingProbability[block_count]<<endl;
                block_count++;
                now_server=0;
                now_queue=0;
                block_num=0;
                count=0;
                
            }
        }
    }

    
    
}