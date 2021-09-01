package com.example.dbt;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;

public class Simon extends AppCompatActivity {
    private int counter = 0;
    ArrayList<Integer>CorrectAnswers = new ArrayList<>();
    ArrayList<Integer>UsersAnswers = new ArrayList<>();

    ImageView g1; //grabbing green images
    ImageView g2;
    ImageView g3;
    ImageView g4;

    ImageView r1;  //grabbing red images
    ImageView r2;
    ImageView r3;
    ImageView r4;
    ImageView badSimon;
    Button returnBtnSimon;
    MediaPlayer simonMusic;
    TextView simonmsg;

    Status status = new Status();
    int score = 0;
    static boolean NextPattern = false;
    static int LetsGo = 0;
    Button startSimon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simon);
        simonmsg = findViewById(R.id.simonMsg);

        //grabbing green images
        g1 = findViewById(R.id.g1);
        g1.setEnabled(false);
        g2 = findViewById(R.id.g2);
        g2.setEnabled(false);
        g3 = findViewById(R.id.g3);
        g3.setEnabled(false);
        g4 = findViewById(R.id.g4);
        g4.setEnabled(false);
        //grabbing red images
        r1 = findViewById(R.id.r1);
        r1.setEnabled(false);
        r2 = findViewById(R.id.r2);
        r2.setEnabled(false);
        r3 = findViewById(R.id.r3);
        r3.setEnabled(false);
        r4 = findViewById(R.id.r4);
        r4.setEnabled(false);
        badSimon = findViewById(R.id.evilSimon);
         startSimon = findViewById(R.id.startSimon);
        //set all the red squares to invisible
        r1.setVisibility(View.INVISIBLE);
        r2.setVisibility(View.INVISIBLE);
        r3.setVisibility(View.INVISIBLE);
        r4.setVisibility(View.INVISIBLE);
        //Return Button
        returnBtnSimon = findViewById(R.id.returnBtnSimon);
        returnBtnSimon.setOnClickListener(cardClicked -> {
            LetsGo = 0;
            NextPattern = false;
            Status.isItEasy = false;
            Status.isItMedium = false;
            Status.isItHard =false;
            if(simonMusic.isPlaying()) { simonMusic.pause(); simonMusic.release(); }
            Intent returnToPlayerInfo = new Intent(getApplicationContext(), PlayerInfo.class);
            startActivity(returnToPlayerInfo);
        });
        //Settings
        ImageView settings = findViewById(R.id.simonSettings);
        simonMusic = MediaPlayer.create(this.getApplicationContext(), R.raw.riddle);
        try { simonMusic.prepareAsync(); } catch (Exception prep) {prep.printStackTrace(); }
        settings.setOnClickListener(v -> {
            SettingMenu set = new SettingMenu();
            set.showWindow(Simon.this, settings, simonMusic);
        });
    }

    public void SimonGo(View v){
       simonmsg.setText("");
       int CorrectAnswersSize = CorrectAnswers.size();
       switch(LetsGo)
       {
           case 0:
               disableViews();
               if(Status.isItEasy == true){ Pattern(6,1000);}
               if(Status.isItMedium == true){ Pattern2(6,500); }
               if(Status.isItHard == true){ Pattern3(6,100); }
               if(NextPattern == true){ LetsGo+=1; NextPattern = false;}
               if(LetsGo == 0 && NextPattern == false){ LetsGo = 0;}
               System.out.println(CorrectAnswersSize);
               startSimon.setEnabled(false);
           break;
           case 1:
               disableViews();
               if(Status.isItEasy == true) { Pattern2(6,1000); }
               if(Status.isItMedium == true){ Pattern3(6,500); }
               if(Status.isItHard == true){ Pattern4(6,100); }
               if(NextPattern == true){ LetsGo +=1; NextPattern = false;}
               if(LetsGo == 1 && NextPattern == false){ LetsGo = 1;}
               startSimon.setEnabled(false);
               break;
           case 2:
               disableViews();
               if(Status.isItEasy == true) { Pattern3(6,1000); }
               if(Status.isItMedium == true){ Pattern4(6,500); }
               if(Status.isItHard == true){ Pattern5(6,100); }
               if(NextPattern == true){ LetsGo +=1; NextPattern = false;}
               if(LetsGo == 2 && NextPattern == false){ LetsGo = 2;}
               startSimon.setEnabled(false);
               break;
           case 3:
               disableViews();
               if(Status.isItEasy == true) { Pattern4(7,1000); }
               if(Status.isItMedium == true){ Pattern5(7,500); }
               if(Status.isItHard == true){ Pattern(7,100); }
               if(NextPattern == true){ LetsGo +=1;NextPattern = false; startSimon.setEnabled(true);}
               if(LetsGo == 3 && NextPattern == false){ LetsGo = 3;}
               startSimon.setEnabled(false);

               break;
           case 4:
               disableViews();
               if(Status.isItEasy == true) { Pattern5(6,1000); }
               if(Status.isItMedium == true){ Pattern(6,500); }
               if(Status.isItHard == true){ Pattern2(6,100); }
               if(NextPattern == true){ LetsGo +=1;NextPattern = false; }
               if(LetsGo == 4 && NextPattern == false){ LetsGo = 4;}
               startSimon.setEnabled(false);

               break;
           case 5:
               disableViews();
               if(NextPattern == true)
               {
                   Status.isItEasy = false;
                   Status.isItMedium = false;
                   Status.isItHard = false;
                   Status.simonEnd = true;
                   Status.fromSimonGO = true;
                   Intent toGameOver = new Intent(getApplicationContext(), GeneralGamover.class);
                   startActivity(toGameOver);
               }
           break;
       }
   }
    public void check(View v){
       switch (v.getId())
       {
           case R.id.g1:
               patternSet(g1,r1,3);
               UsersAnswers.add(1);
               if(UsersAnswers.size() == CorrectAnswers.size())
               {
                   if(UsersAnswers.equals(CorrectAnswers)){ checkCorrectConditional();}
                   else{ checkWrongConditional(); }
               }
           break;
           case R.id.g2:
               patternSet(g2,r2,3);
               UsersAnswers.add(2);
               if(UsersAnswers.size() == CorrectAnswers.size())
               {
                   if(UsersAnswers.equals(CorrectAnswers)){ checkCorrectConditional();}
                   else{ checkWrongConditional(); }
               }
               break;
           case R.id.g3:
               patternSet(g3,r3,3);
               UsersAnswers.add(3);

               if(UsersAnswers.size() == CorrectAnswers.size())
               {
                   if(UsersAnswers.equals(CorrectAnswers)){ checkCorrectConditional();}
                   else{ checkWrongConditional(); }
               }
               break;
           case R.id.g4:
               patternSet(g4,r4,3);
               UsersAnswers.add(4);
               if(UsersAnswers.size() == CorrectAnswers.size())
               {
                   if(UsersAnswers.equals(CorrectAnswers)){ checkCorrectConditional();}
                   else{ checkWrongConditional(); }
               }
               break;
       }
   }
    public void Pattern(int time, int interval) {
        int milli = time * 1000;
        final int[] p = {0};
        new CountDownTimer(milli, interval) {
            public void onTick(long millisUntilFinished) {
                p[0] = p[0] + 1;
                switch (p[0]) {
                    case 2:
                        patternSet(g1,r1,2);
                        CorrectAnswers.add(1);
                    break;
                    case 3:
                        patternSet(g2,r2,2);
                        CorrectAnswers.add(2);
                    break;
                    case 4:
                        patternSet(g3,r3,2);
                        CorrectAnswers.add(3);
                    break;
                    case 5:
                        patternSet(g4,r4,2);
                        CorrectAnswers.add(4);
                    break;
                }
            }
            public void onFinish() {
                enableViews();
            }
        }.start();
}
    public void Pattern2(int time, int interval) {
        int milli = time * 1000;
        final int[] p = {0};
        int t;
        new CountDownTimer(milli, interval) {
            public void onTick(long millisUntilFinished) {
                p[0] = p[0] + 1;
                switch (p[0]) {
                    case 2:
                        patternSet(g2,r2,3);
                        CorrectAnswers.add(2);
                    break;
                    case 3:
                        patternSet(g4,r4,3);
                        CorrectAnswers.add(4);
                    break;
                    case 4:
                        patternSet(g1,r1,3);
                        CorrectAnswers.add(1);
                    break;
                    case 5:
                        patternSet(g3,r3,3);
                        CorrectAnswers.add(3);
                    break;
                }
            }
            public void onFinish() {
                enableViews();
            }
        }.start();

}
    public void Pattern3(int time, int interval) {
        int milli = time * 1000;
        final int[] p = {0};
        int t;
        new CountDownTimer(milli, interval) {
            public void onTick(long millisUntilFinished) {
                p[0] = p[0] + 1;
                switch (p[0]) {
                    case 2:
                        patternSet(g4,r4,3);
                        CorrectAnswers.add(4);
                        break;
                    case 3:
                        patternSet(g3,r3,3);
                        CorrectAnswers.add(3);
                        break;
                    case 4:
                        patternSet(g2,r2,3);
                        CorrectAnswers.add(2);
                        break;
                    case 5:
                        patternSet(g1,r1,3);
                        CorrectAnswers.add(1);
                        break;
                }
            }
            public void onFinish(){ enableViews(); }
        }.start();

    }
    public void Pattern4(int time, int interval) {
        int milli = time * 1000;
        final int[] p = {0};
        int t;
        new CountDownTimer(milli, interval) {
            public void onTick(long millisUntilFinished) {
                p[0] = p[0] + 1;
                switch (p[0]) {
                    case 2:
                        patternSet(g2,r2,3);
                        CorrectAnswers.add(2);
                        break;
                    case 3:
                        patternSet(g3,r3,3);
                        CorrectAnswers.add(3);
                        break;
                    case 4:
                        patternSet(g1,r1,3);
                        CorrectAnswers.add(1);
                        break;
                    case 5:
                        patternSet(g4,r4,3);
                        CorrectAnswers.add(4);
                        break;
                }
            }
            public void onFinish() {
                enableViews();
            }
        }.start();

    }
    public void Pattern5(int time, int interval) {
        int milli = time * 1000;
        final int[] p = {0};
        int t;
        new CountDownTimer(milli, interval) {
            public void onTick(long millisUntilFinished) {
                p[0] = p[0] + 1;
                switch (p[0]) {
                    case 2:
                        patternSet(g1,r1,3);
                        CorrectAnswers.add(1);
                        break;
                    case 3:
                        patternSet(g4,r4,3);
                        CorrectAnswers.add(4);
                        break;
                    case 4:
                        patternSet(g2,r2,3);
                        CorrectAnswers.add(2);
                        break;
                    case 5:
                        patternSet(g3,r3,3);
                        CorrectAnswers.add(3);
                        break;
                }
            }
            public void onFinish() {
                enableViews();
            }
        }.start();

    }
    public void displaySimonScore(int score) {
        TextView scoreDisplay = findViewById(R.id.simonScore);
        String scoreBeingSet = String.valueOf(status.getSimonScore());
        scoreDisplay.setText("Score: " + scoreBeingSet);
        simonmsg.setText("Congratulations You got the Pattern Correct Click Lets go To get next pattern");
    }
    public void checkCorrectConditional(){
        status.setSimonScore(score += 100);
        UsersAnswers.clear();
        CorrectAnswers.clear();
        NextPattern = true;
        startSimon.setEnabled(true);
        displaySimonScore(status.getSimonScore());
    }
    public void checkWrongConditional() {
        status.setSimonScore(score -= 100);
        UsersAnswers.clear();
        displaySimonScore(score);
        badSimon(10);
        simonmsg.setText("Oh NO!! You got the Pattern Wrong Simon didn't like that Click Lets Go to see pattern again");
        ShowPatternAgainWrong();

    }
    public void patternSet(ImageView g, ImageView r,int time) {
        g.setVisibility(View.INVISIBLE);
        r.setVisibility(View.VISIBLE);
        int milli = time * 1000;
        final int[] p = {0};
        int t;
        new CountDownTimer(milli, 500) {
            public void onTick(long millisUntilFinished) {
                p[0] = p[0] + 1;
                switch (p[0]) {
                    case 2:
                        g.setVisibility(View.VISIBLE);
                        r.setVisibility(View.INVISIBLE);
                        break;
                }
            }
            public void onFinish() {
            }
        }.start();
    }

    /*
    Enables and disables cards until they are ready to be clicked
     */
    private void disableViews() {
        g1.setEnabled(false); g2.setEnabled(false); g3.setEnabled(false); g4.setEnabled(false);
        r1.setEnabled(false); r2.setEnabled(false); r3.setEnabled(false); r4.setEnabled(false);
    }
    private void enableViews() {
        g1.setEnabled(true); g2.setEnabled(true); g3.setEnabled(true); g4.setEnabled(true);
        r1.setEnabled(true); r2.setEnabled(true); r3.setEnabled(true); r4.setEnabled(true);
    }

    private void badSimon(int time) {
        int milli = time * 1000;
        final int[] p = {0};
        int t;
        new CountDownTimer(milli, 500) {
            public void onTick(long millisUntilFinished) {
                p[0] = p[0] + 1;
                switch (p[0]) {
                    case 1:
                        badSimon.setVisibility(View.VISIBLE);
                    break;
                    case 2:
                        badSimon.setVisibility(View.VISIBLE);
                    break;
                    case 3:
                        badSimon.setVisibility(View.INVISIBLE);
                    break;
                }
            }
            public void onFinish() {
            }
        }.start();
    }

    private void ShowPatternAgainWrong()
    {
        switch(LetsGo)
        {
            case 0:
                if(Status.isItEasy){ Pattern(6,1000); }
                if(Status.isItMedium){ Pattern2(6,500); }
                if(Status.isItHard){ Pattern3(6,100); }

                break;
            case 1:
                if(Status.isItEasy){ Pattern2(6,1000); }
                if(Status.isItMedium){ Pattern3(6,500);  }
                if(Status.isItHard){ Pattern4(6,100);  }

                break;
            case 2:
                if(Status.isItEasy){ Pattern3(6,1000); }
                if(Status.isItMedium){ Pattern4(6,500);  }
                if(Status.isItHard){ Pattern5(6,100);  }

                break;
            case 3:
                if(Status.isItEasy){ Pattern4(7,1000); }
                if(Status.isItMedium){ Pattern5(7,500);  }
                if(Status.isItHard){ Pattern(7,100); }
                break;
            case 4:
                if(Status.isItEasy){ Pattern5(6,1000); }
                if(Status.isItMedium){ Pattern(6,500); }
                if(Status.isItHard){ Pattern2(6,100);  }
            break;
        }
    }

}
