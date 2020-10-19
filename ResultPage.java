import java.util.ArrayList;

import javax.swing.*;


import java.awt.*;

public class ResultPage extends JFrame{
    private JTextField[]table=new JTextField[225];
    private JTextField[]table2=new JTextField[225];
    ResultPage(){
        System.out.println("fuck");
        this.setSize(900,900);
        this.setLayout(null);
        this.setVisible(true);
        for(int i=0;i<225;++i){
            this.table[i]=new JTextField();
            this.table2[i]=new JTextField();
        }
        JLabel table1Lable=new JLabel("Tabke for Q=0");
        table1Lable.setSize(500,30);
        table1Lable.setLocation(50,50);
        table1Lable.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        this.add(table1Lable);
        JLabel table2Lable=new JLabel("Tabke for Q=S");
        table2Lable.setSize(500,30);
        table2Lable.setLocation(50,450);
        table2Lable.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        this.add(table2Lable);
        initTable();
    }
    private void initTable(){
        for(int i=0;i<9;++i){
            for(int j=0;j<25;++j){
                if(j==0)
                {

                    if(i%3==0){
                        switch(i){
                            case 0:
                                this.table[i*25+j].setText("1");
                                this.table2[i*25+j].setText("1");
                                break;
                            case 3:
                                this.table[i*25+j].setText("5");
                                this.table2[i*25+j].setText("5");
                                break;
                            case 6:
                                this.table[i*25+j].setText("10");
                                this.table2[i*25+j].setText("5");
                                break;
                        }
                    }


                }
                else{
                    this.table[i*25+j].setText("BP_"+Integer.toString(j));
                    this.table2[i*25+j].setText("BP_"+Integer.toString(j));
                    
                }
                this.table[i*25+j].setSize(50,20);
                this.table2[i*25+j].setSize(50,20);
                this.table[i*25+j].setLocation(50+50*j,100+20*i);
                this.table2[i*25+j].setLocation(50+50*j,500+20*i);
                this.add(this.table[i*25+j]);
                this.add(this.table2[i*25+j]);


            
            }
        }
    }
    public void writeTable(double []block,int queue_flag){
        double []rate=new double[24];
        int count=0;
        for(double lambda=0.01;lambda<=10;lambda*=10){
            for(double mu=0.01;mu<=10.24;mu*=4){
                rate[count++]=lambda/mu;
            }
        }
        
        for(int i=0;i<9;++i){
            if(i%3==1){
                for(int j=1;j<25;++j){
                    if(queue_flag==0)
                        this.table[i*25+j].setText(Double.toString(rate[j-1]));
                    else
                        this.table2[i*25+j].setText(Double.toString(rate[j-1]));
                }
            }
            else if(i%3==2){
                for(int j=1;j<25;++j){
                    if(queue_flag==0)
                        this.table[i*25+j].setText(Double.toString(block[i/3+j-1]));
                    else
                        this.table2[i*25+j].setText(Double.toString(block[i/3+j-1]));
                }
            }
        }

    }
}
