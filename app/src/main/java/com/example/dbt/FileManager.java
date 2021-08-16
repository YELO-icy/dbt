package com.example.dbt;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FileManager extends MainActivity {


    private String userName;
    private int userScore = 0;
    public int time;
    public  int click = 0;

    FileManager(){}
    FileManager(String userName, int userScore) {
        this.userName = userName;
        this.userScore = userScore;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String newName) {
        userName = newName;
    }


    public int getUserScore() { return userScore; }


    public void setUserScore(int newScore) { userScore = newScore; }


    public void checkCorrect(View v) {

    }


    public void displayImages() {}


    public void hideImages() {
        //coment

        //TODO
    }

    public void changeGUI(View v)
    {
        switch (click)
        {
            case  0:
                Intent action1 = new Intent(getApplicationContext(), PlayerInfo.class);
                startActivity(action1);
                click +=1;
                break;
            case 1:
                Intent action2 = new Intent(getApplicationContext(), RiddleScreen.class);
                startActivity(action2);
                click +=1;
                break;
            case 2:
                Intent action3 = new Intent(getApplicationContext(), MemoryGame.class);
                startActivity(action3);
                click +=1;
                break;
            case 3:
                Intent action4 = new Intent(getApplicationContext(), GameOver.class);
                startActivity(action4);
                click +=1;
                break;
        }

    }



    public void timer()  {
        //TODO
    }
}
