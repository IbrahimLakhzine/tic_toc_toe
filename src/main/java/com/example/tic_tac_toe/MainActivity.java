package com.example.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public boolean flag=true;
    public int index=1;
    public int playerScore1=0;
    public int playerScore2=0;
    public ArrayList<Button> btnArray=new ArrayList<Button>();
    public int[][] matrix={{0,0,0},{0,0,0},{0,0,0}} ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        for(int i=0;i<3;i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,1.0f
            ));
            tableLayout.addView(tableRow);
            for(int j=0;j<3;j++){
                final int FINAL_I=i;
                final int FINAL_J=j;
                final Button btn  = new Button(this);

                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,1.0f
                ));
                tableRow.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnArray.add(btn);
                        if(flag==true){
                            btn.setText("X");
                            matrix[FINAL_I][FINAL_J]=1;
                            solver(matrix);
                            if(solver(matrix)==1){
                                erase(btnArray,matrix);
                                //scoreplayer1
                                playerScore1+=10;
                                TextView scorePlayer1= (TextView) findViewById(R.id.scorePlayer1);
                                scorePlayer1.setText("your score is:"+playerScore1);
                            }
                            else if(solver(matrix)==2){
                                erase(btnArray,matrix);
                            }

                            flag=false;
                        }
                        else if(flag==false){
                            btn.setText("O");
                            matrix[FINAL_I][FINAL_J]=2;
                            solver(matrix);
                            if(solver(matrix)==1){

                                erase(btnArray,matrix);
                                //scoreplayer2
                                playerScore2+=10;
                                TextView scorePlayer2= (TextView) findViewById(R.id.scorePlayer2);
                                scorePlayer2.setText("your score is:"+playerScore2);
                            }

                            else if(solver(matrix)==2){


                                erase(btnArray,matrix);
                            }

                            flag=true;
                        }
                    }
                });

            }

        }
        Button resetBtn =(Button) findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erase(btnArray,matrix);
            }
        });

    }

    public void erase(ArrayList<Button> btnArray,int [][]matrix){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                matrix[i][j]=0;
            }
        }

        for(Button btn:btnArray){
            btn.setText("");
        }
    }


    public int solver(int [][] matrix) {
        int s=0,s1=0;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(matrix[i][j]==1){
                    s++;
                }
                else if(matrix[i][j]==2){
                    s--;
                }
            }
            if(s==-3) {
                //cercle you win
                Toast.makeText(MainActivity.this,"player2 you win",Toast.LENGTH_SHORT).show();
                return 1;
            }

            else if(s==3) {
                //croix you win
                Toast.makeText(MainActivity.this,"player1 you win",Toast.LENGTH_SHORT).show();
                return 1;
            }
            s=0;

        }

        s=0;

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(matrix[j][i]==1){
                    s++;
                }
                else if(matrix[j][i]==2){
                    s--;
                }
            }
            if(s==-3) {
                //cercle you win
                Toast.makeText(MainActivity.this,"player2 you win",Toast.LENGTH_SHORT).show();
                return 1;
            }

            else if(s==3) {
                //croix you win
                Toast.makeText(MainActivity.this,"player1 you win",Toast.LENGTH_SHORT).show();
                return 1;
            }
            s=0;

        }

        if(matrix[0][0]==1 && matrix[2][2]==1 && matrix[1][1]==1) {
            //croix you win
            Toast.makeText(MainActivity.this,"player1 you win",Toast.LENGTH_SHORT).show();
            return 1;

        }

        else if(matrix[0][0]==2 && matrix[2][2]==2 && matrix[1][1]==2) {
            //cercle you win
            Toast.makeText(MainActivity.this,"player2 you win",Toast.LENGTH_SHORT).show();
            return 1;
        }

        else if(matrix[0][2]==1 && matrix[1][1]==1 && matrix[2][0]==1) {
            //croix you win
            Toast.makeText(MainActivity.this,"player1 you win",Toast.LENGTH_SHORT).show();
            return 1;
        }
        else if(matrix[0][2]==2 && matrix[1][1]==2 && matrix[2][0]==2) {
            //cercle you win
            Toast.makeText(MainActivity.this,"player2 you win",Toast.LENGTH_SHORT).show();
            return 1;
        }

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(matrix[i][j]!=0) {
                    s1++;
                }
            }

        }

        if(s1==9) {
            Toast.makeText(MainActivity.this,"Draw",Toast.LENGTH_SHORT).show();
            return 2 ;
        }

        return 0;
    }
}
