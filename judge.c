#include<stdio.h>
#include<stdlib.h>
#include<math.h>
int main(){
    double r=0.02;
    double temp=0;
    int order=1;
    for(int i=0;i<=order;++i){
        int j=1;
        int t=i;
        while(t>0){
            j*=t;
            --t;
        }
        printf("%d\n",j);
        temp+=pow(r,i)/j;
    }
    printf("%f\n",temp);
    double p=(double)pow(r,order)/ (double) (order*temp);
    printf("%f\n",p);
}