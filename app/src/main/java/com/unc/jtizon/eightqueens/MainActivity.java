package com.unc.jtizon.eightqueens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Boolean[][] board;
    Button [][] butBoard;
    //boolean BW = true;
    //boolean T = true;
    boolean OK=true;
    int numQueens;
    int queenArr[]= new int[8];
    ArrayList<Integer> queenL= new ArrayList<Integer>();
    Boolean WL;
    Boolean givenup=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout grid = (GridLayout)findViewById(R.id.board);

        butBoard=new Button[8][8];
        board= new Boolean[8][8];

        for (int i = 0;i<8;i++){
            for (int j=0;j<8;j++){
                ViewGroup.LayoutParams butParam= new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                DisplayMetrics totScreen = getResources().getDisplayMetrics();
                board[i][j]=false;
                butParam.width=totScreen.widthPixels/8;
                butParam.height=totScreen.widthPixels/8;
                ButSub neoBut=new ButSub(this, i, j);
                butBoard[i][j]=neoBut;
                butBoard[i][j].setLayoutParams(butParam);
                int sum=i+j;

                if (sum%2==1){
                    butBoard[i][j].setBackgroundResource(R.drawable.black);
                }
                else {
                    butBoard[i][j].setBackgroundResource(R.drawable.white);
                }
                grid.addView(neoBut);
                neoBut.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        ButSub butSub=(ButSub)v;
                        int sum=butSub.rIndex+butSub.cIndex;
                        //OK = true;
                        int x= butSub.rIndex;
                        int y= butSub.cIndex;
                        boolean indexBool=board[x][y];
                        //nuhuhMessage(insertQueen(x,y));
                        insertQueen(x,y);

                        Log.v("DEEEEBUG",""+ numQueens );
                        //Toast.makeText(MainActivity.this,"Winner winner chicken dinner!!!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
    public Boolean insertQueen(int x, int y){//, Boolean indexBool,ButSub butSub){//was void
        int sum=x+y;
        if(board[x][y]==false){
            OK = true;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (i == x)
                        if (board[i][j] == true) {
                            OK = false;
                            //return OK;
                        }
                    if (j == y)
                        if (board[i][j] == true)
                            OK = false;

                    if (sum == i + j)
                        if (board[i][j])
                            OK = false;
                    if (x - i == y - j)
                        if (board[i][j] == true)
                            OK = false;
                }
            }
            if (sum%2==1&&OK){
                butBoard[x][y].setBackgroundResource(R.drawable.crownb);
                board[x][y] = true;
                numQueens++;
            }
            else if(sum%2==0&&OK){
                butBoard[x][y].setBackgroundResource(R.drawable.crownw);
                board[x][y] = true;
                numQueens++;
            }
            else if (OK==false && givenup==false &&numQueens<8)
                Toast.makeText(MainActivity.this,"nuh uh",Toast.LENGTH_SHORT).show();

        }
        else{
            if(sum%2==1){
                butBoard[x][y].setBackgroundResource(R.drawable.black);
                board[x][y]=false;
                numQueens--;
            }
            else{
                butBoard[x][y].setBackgroundResource(R.drawable.white);
                board[x][y]=false;
                numQueens--;
            }
        }

        if(numQueens==8)
            Toast.makeText(MainActivity.this,"Winner Winner Chicken Dinner!!!",Toast.LENGTH_SHORT).show();
        return OK;
    }


    public void resetFunction (View v){
       if(v.getId()==R.id.resetButton) {// v.setOnClickListener(new View.OnClickListener(){})
           for (int i = 0; i < 8; i++) {
               for (int j = 0; j < 8; j++) {
                   int sum = i + j;
                   board[i][j] = false;
                   if (sum % 2 == 1)
                       butBoard[i][j].setBackgroundResource(R.drawable.black);
                   else
                       butBoard[i][j].setBackgroundResource(R.drawable.white);
                   numQueens = 0;

                   Toast.makeText(MainActivity.this, "Game Cleared", Toast.LENGTH_SHORT).show();
               }
           }
       }
    }

    public void giveUpFunction(View v){
        givenup=true;
        WL=false;
        //Toast.makeText(MainActivity.this, "not done yet",Toast.LENGTH_SHORT).show();
        for(int i=0;i<8;i++)
            queenArr[i]=-1;

        for (int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                if (board[i][j]==true)
                    queenArr[i]=j;
            }
        }
        for (int i=0;i<8;i++){
            if (queenArr[i]==-1)
                queenL.add(i);
        }
        DFS(queenL,0);
        for(int i=0;i<8;i++){
            queenArr[i]=-1;
        }
        if(WL==false)
            Toast.makeText(MainActivity.this,"No Solution",Toast.LENGTH_SHORT).show();
        queenL.clear();
        givenup=false;
    }
    public void DFS(ArrayList<Integer> aL, int x){
        if(x==aL.size()) {
            WL = true;
            return;
        }
        for (int i=0;i<8;i++){
            int a = aL.get(x);
            if(insertQueen(aL.get(x),i)==true){
                DFS(aL,x+1);
                if (WL)
                    break;
                insertQueen(aL.get(x),i);
            }
        }return;
    }
}

