package com.ai.game.connect_3;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameScreen extends AppCompatActivity {
    private ArrayList<Integer> gameState = new ArrayList<>();
    private int currPlayer = 0;
    private boolean gameOver = false;
    private TextView currentChance;
    private GridLayout gridLayout;
    private Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        currentChance = findViewById(R.id.textView2);
        restart = findViewById(R.id.button);

        for(int i = 0; i < 9; i++){
            gameState.add(2);
        }
    }
    public void dropPiece(View view){
        ImageView img = (ImageView) view;
        int tappedCounter = Integer.parseInt(view.getTag().toString());

        if(gameState.get(tappedCounter) == 2 && !gameOver){
            gameState.set(tappedCounter, currPlayer);
            img.setTranslationY(-1000);
            if(currPlayer == 0){
                img.setImageResource(R.drawable.yellow);
                currentChance.setText("RED'S TURN");
                currentChance.setTextColor(Color.parseColor("#ff030b"));
                currPlayer = 1;
            }
            else {
                img.setImageResource(R.drawable.red);
                currentChance.setText("YELLOW'S TURN");
                currentChance.setTextColor(Color.parseColor("#eac203"));
                currPlayer = 0;
            }
            img.animate().translationYBy(1000).setDuration(500);
        }
        else{
            img.animate().rotationYBy(360).rotationXBy(360).setDuration(500);
        }

        if(checkWin() == null){
            currentChance.setText("DRAW");
            gameOver = true;
            restart.setVisibility(View.VISIBLE);
        }
        else if (checkWin()){
            if(currPlayer == 1){
                currentChance.setText("YELLOW WINS!");
                currentChance.setTextColor(Color.parseColor("#eac203"));
            }
            else {
                currentChance.setText("RED WINS!");
                currentChance.setTextColor(Color.parseColor("#ff030b"));
            }
            gameOver = true;
            restart.setVisibility(View.VISIBLE);
        }
    }

    public void reset(View view){
        Log.i("Array prints : ", gameState.toString());
        for(int i = 0; i < 9; i++){
            gameState.add(2);
        }
        GameScreen.this.recreate();
    }

    private Boolean checkWin(){
        for(int i = 0; i < 3; i++){
            if(gameState.get(i + (2*i)).equals(gameState.get(i + (2*i) + 1)) && gameState.get(i + (2*i) + 1).equals(gameState.get(i + (2*i)+2)) && !gameState.get(i + (2*i)).equals(2)) {
                Log.i("CHECK WIN : ", "HORIZONTALLY");
                return true;
            }
            if(gameState.get(i).equals(gameState.get(i+3)) && gameState.get(i + 3).equals(gameState.get(i+6)) && !gameState.get(i).equals(2)) {
                Log.i("CHECK WIN : ", "VERTICALLY");
                return true;
            }
        }
        if(gameState.get(0).equals(gameState.get(4)) && gameState.get(4).equals(gameState.get(8)) && !gameState.get(4).equals(2)) {
            Log.i("CHECK WIN : ", "DIAGONALLY");
            return true;
        }
        if(gameState.get(2).equals(gameState.get(4)) && gameState.get(4).equals(gameState.get(6)) && !gameState.get(4).equals(2)) {
            Log.i("CHECK WIN : ", "DIAGONALLY");
            return true;
        }
        boolean draw = false;
        for(int i = 0; i < 9; i++){
            if(gameState.get(i).equals(2)){
                draw = true;
                break;
            }
        }
        if(!draw) return null;
        return false;
    }
}