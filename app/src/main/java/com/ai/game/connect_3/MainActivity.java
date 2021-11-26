package com.ai.game.connect_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button vsFriend;
    private Button vsAI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vsFriend = findViewById(R.id.playVsFriend);
        vsAI = findViewById(R.id.playVsAi);
    }

    public void loadGameVsFriend(View view){
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }
}