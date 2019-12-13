package com.example.xo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GameBoard extends AppCompatActivity {
    Button btn_playagain;
    ImageView img;
    public static final int o = 0;
    public static final int x = 1;
    public static final int NOT_PLAYED = 2;
    int turn = x;
    int winner = -1;
    int [] cell =   {NOT_PLAYED,NOT_PLAYED,NOT_PLAYED,
            NOT_PLAYED,NOT_PLAYED,NOT_PLAYED,
            NOT_PLAYED,NOT_PLAYED,NOT_PLAYED};

    int[][] winningModes = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        btn_playagain = findViewById(R.id.btn_playagain);


    }



    public void dropin(View view){


        int tag= Integer.parseInt((String) view.getTag());
        img = (ImageView) view;
        if(cell[tag]!=NOT_PLAYED || winner!=-1 || fillCompleted()){
            return;
        }
        if(turn==x) {
            img.setImageResource(R.drawable.x);
            turn=o;
            cell[tag]=x;
        }else if (turn==o){
            img.setImageResource(R.drawable.o);
            turn=x;
            cell[tag]=o;
        }
        //Check Who IS Winner
        winner = whoIsWinner();
        if (winner==0)
            Toast.makeText(this, "Winner IS O! Player", Toast.LENGTH_SHORT).show();
        else if (winner==1)
            Toast.makeText(this, "Winner IS X! Player", Toast.LENGTH_SHORT).show();
        else if (fillCompleted() && winner==-1)
            Toast.makeText(this, "No Winner", Toast.LENGTH_SHORT).show();

    }

    private int whoIsWinner() {
        for(int[] position : winningModes)
            if(cell[position[0]]==cell[position[1]]&&
                    cell[position[1]]==cell[position[2]]&&
                    cell[position[0]]!=NOT_PLAYED){
                return cell[position[0]];
            }

        return -1;
    }
    private  boolean fillCompleted(){
        for (int i = 0 ;i<cell.length;i++)
            if(cell[i]==NOT_PLAYED)
                return false;
        return true;
    }
    public void onclick_again(View view){
        Intent intent = new Intent(GameBoard.this,GameBoard.class);
        startActivity(intent);

    }


}
