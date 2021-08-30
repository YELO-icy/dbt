package com.example.dbt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class IsekaiPlayerName extends AppCompatActivity {

    JourneyText[] m3s = new JourneyText[1];

    int c1Tracker =0;
    int c1Id;
    int c2Id;
    int c3Id;
    int c4Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.isekai_player_name);

        Intent action2 = getIntent();
        c1Id = action2.getIntExtra("c1",R.drawable.b);
        c2Id = action2.getIntExtra("c2",R.drawable.b_attack);
        c3Id = action2.getIntExtra("c3",R.drawable.b_die);
        c4Id = action2.getIntExtra("c4",R.drawable.b_win);
        Button sure = findViewById(R.id.button3);
        Button nah = findViewById(R.id.button4);
        sure.setVisibility(View.INVISIBLE);
        nah.setVisibility(View.INVISIBLE);

        ImageView sprite = findViewById(R.id.cImage);
        sprite.setImageResource(c1Id);
    }

    public void SetName(View v){
        EditText txtName = findViewById(R.id.txtName);
        TextView textview4 = findViewById(R.id.textView4);

        if(txtName.length() ==0){
            textview4.setText("Enter a name to Continue.");
            return;
        }
        else{
            Button sure = findViewById(R.id.button3);
            Button nah = findViewById(R.id.button4);
            sure.setVisibility(View.VISIBLE);
            nah.setVisibility(View.VISIBLE);
            m3s[c1Tracker] = new JourneyText();
            m3s[c1Tracker].name = txtName.getText().toString();

            textview4.setText(m3s[c1Tracker].toString());
        }


    }

    public void NoGame(View v){
        Intent action2 = new Intent(getApplicationContext(),IsekaiTitle.class);
        startActivity(action2);

    }

    public void GotoGAME(View v){
        Intent action3 = new Intent(getApplicationContext(),IsekaiFirstScene.class);
        EditText txtName = findViewById(R.id.txtName);
        if(txtName.length() ==0){
            TextView textView4 = findViewById(R.id.textView4);
            textView4.setText("Enter a name to Continue.");
            return;
        }

        action3.putExtra("c1",c1Id);
        action3.putExtra("c2",c2Id);
        action3.putExtra("c3",c3Id);
        action3.putExtra("c4",c4Id);
        action3.putExtra("c5",m3s[c1Tracker].name);

        startActivity(action3);
    }
}