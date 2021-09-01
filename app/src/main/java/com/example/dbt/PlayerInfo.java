package com.example.dbt;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class PlayerInfo extends AppCompatActivity {


    private ImageView memGameIcon, triviaGameIcon, simonGameIcon, highScoresIcon,
    targetGameIcon, txtAdvIcon, idotTestIcon, HighScorestxt;
    EditText userName;
    MediaPlayer playerInfoMusic;
    Status status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_info);


        //Get score from memGame
        Intent getScore = getIntent();
        int memScore = getScore.getIntExtra("memScore", 0);
        System.out.println("HERE -- " + memScore);
        userName = findViewById(R.id.userName);

        //set view for username
        //Memory Game
        memGameIcon = findViewById(R.id.memGameIcon);
        memGameIcon.setOnClickListener(cardClicked -> {
            try {
                if(checkUserName() == true) {
                    sendUserName(); musicRelease();
                    Intent memGame = new Intent(getApplicationContext(), MemoryGame.class);
                    startActivity(memGame);
                }
            }
            catch (Exception e) { e.printStackTrace(); } });
        //Trivia Game
        triviaGameIcon = findViewById(R.id.triviaGameIcon);
        triviaGameIcon.setOnClickListener(cardClicked -> {
            try {
                if(checkUserName() == true) {
                    sendUserName(); musicRelease();
                    Intent triviaGame = new Intent(getApplicationContext(), RiddleScreen.class);
                    startActivity(triviaGame);
                }
            }
            catch (Exception e) { e.printStackTrace(); } });
        //Simon Game
        simonGameIcon = findViewById(R.id.simonGameIcon);
        simonGameIcon.setOnClickListener(cardClicked -> {
            try {
                if(checkUserName() == true) {
                    sendUserName(); musicRelease();
                    Intent SelectDiff = new Intent(getApplicationContext(), SelectDifficulty.class);
                    startActivity(SelectDiff);
                }
            }
            catch(Exception e) { e.printStackTrace(); } });
        //High Scores Screen
        highScoresIcon = findViewById(R.id.targetGameIcon);
        highScoresIcon.setOnClickListener(cardClicked -> {
            try {
                if(checkUserName() == true) {
                    sendUserName(); musicRelease();
                    Intent highScores= new Intent(getApplicationContext(), HighScores.class);
                    startActivity(highScores);
                }
            }
            catch(Exception e) { e.printStackTrace(); } });
        //Idiot Test Game
        idotTestIcon = findViewById(R.id.idiotTestIcon);
        idotTestIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View cardClicked) {
                try {
                    if(checkUserName() == true) {
                        sendUserName();
                        if(playerInfoMusic.isPlaying()) {
                            playerInfoMusic.stop();
                        }

                        Intent idiotGame = new Intent(getApplicationContext(), IdiotTestGame1.class);
                        startActivity(idiotGame);

                    }
                }
                catch(Exception e) { e.printStackTrace(); } }});
        //Target Game Icon
        targetGameIcon = findViewById(R.id.targetGameIcon);
        targetGameIcon.setOnClickListener(cardClicked -> {
            try {
                if(checkUserName() == true) {
                    sendUserName(); musicRelease();
                    Intent targetGame = new Intent(getApplicationContext(), TargetGame.class);
                    startActivity(targetGame);
                } }
            catch(Exception e) { e.printStackTrace(); } });
        //Settings
        ImageView settings = findViewById(R.id.settings1);
        playerInfoMusic = MediaPlayer.create(this.getApplicationContext(), R.raw.one_heroes_journey);
        try { playerInfoMusic.prepareAsync(); } catch (Exception prep) {prep.printStackTrace(); }
        playerInfoMusic.start();
        settings.setOnClickListener(v -> {
            SettingMenu set = new SettingMenu();
            set.showWindow(PlayerInfo.this, settings, playerInfoMusic);
        });
        //Text ADV Game
        txtAdvIcon = findViewById(R.id.textAdvIcon);
        txtAdvIcon.setOnClickListener((View.OnClickListener) cardClicked -> {
          try {
              Intent Isekai = new Intent(getApplicationContext(), IsekaiTitle.class);
              sendUserName(); musicRelease();
              startActivity(Isekai);
          } catch(Exception e) { e.printStackTrace(); } });



    }

    /*
    Get inputted name and send to database
    */
    private void sendUserName() {
       String playerUserName = String.valueOf(userName.getText());
       status.setPlayerName(playerUserName);
    }


    /*
    Check userName not empty or unchanged
    */
    private boolean checkUserName() {
        if(userName.getText().length() == 0) {
            userName.setHint("Please enter user name");
            return false;
        }
        else {
            return true;
        }
    }

    private Boolean isThereHighScore()
    {
        try {
            Intent toLDBD = new Intent(getApplicationContext(), HighScores.class);
            sendUserName(); musicRelease();
            startActivity(toLDBD);
            return true;
        } catch(Exception e) { return false;}
    }

    /*
    Stops and releases music before sending to next activity just in case
    (also saves resources)
     */
    private void musicRelease() { if(playerInfoMusic.isPlaying()) { playerInfoMusic.pause(); playerInfoMusic.release(); } }


}