package app.svc.com.scarnes_dice;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView userScore,computerScore,currentPlayer,moveRoll;
    Button roll,hold,reset;
    ImageView dice;
    boolean isuserturn=true;
    int diceValue,turnscore=0,userscore=0,compscore=0,i=0;
   // final int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        roll.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                isuserturn=true;
                rollfn();

            }

        });

        hold.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                holdfn();
            }
        });
        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                userscore=0;
                compscore=0;
                setscoreView();
                moveRoll.setText(null);
                currentPlayer.setText(null);
            }
        });

    }
    public void initialize() {
        userScore = (TextView) findViewById(R.id.userScore);
        computerScore = (TextView) findViewById(R.id.computerScore);
        currentPlayer = (TextView) findViewById(R.id.currentPlayer);
        moveRoll = (TextView) findViewById(R.id.moveRoll);
        moveRoll.setMovementMethod(new ScrollingMovementMethod());
        roll = (Button) findViewById(R.id.roll);
        hold = (Button) findViewById(R.id.hold);
        reset = (Button) findViewById(R.id.reset);
        dice = (ImageView) findViewById(R.id.dice);
    }
    public void rollfn()
    {
        cur_Player();
        Random random=new Random();
        diceValue=random.nextInt(6)+1;
        setDiceImage(diceValue);
        if(isuserturn) {

            if (diceValue == 1) {
                moveRoll.append("User dicevalue: "+diceValue+"\n");
                turnscore = 0;
                holdfn();
            }
            else
            {
                moveRoll.append("User dicevalue: "+diceValue+"\n");
                turnscore=turnscore+diceValue;

            }
        }
        else
        {
            if (diceValue == 1) {
                moveRoll.append("Computer dicevalue: "+diceValue+"\n");
                turnscore = 0;
                holdfn();
            }
            else
            {
                moveRoll.append("Computer dicevalue: "+diceValue+"\n");
                turnscore=turnscore+diceValue;


            }
        }
   //     return turnscore;


    }
    public void setDiceImage(int n)
    {
        switch(n)
        {
            case 1:
                dice.setImageResource(R.drawable.dice1);
                break;
            case 2:
                dice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                dice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                dice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                dice.setImageResource(R.drawable.dice5);
                break;
            case 6:
                dice.setImageResource(R.drawable.dice6);
                break;

        }
    }
    public void holdfn()
    {
        if(isuserturn)
        {
            userscore=userscore+turnscore;
            setscoreView();
            turnscore=0;
            checkWinner();
        }
        else
        {

            compscore=compscore+turnscore;
            setscoreView();
            turnscore=0;
            checkWinner();
        }
    }
    public void setscoreView()
    {
        userScore.setText("user score is "+userscore);
        computerScore.setText("computer score is "+compscore);

    }
    public void checkWinner()
    {
        int MAX_SCORE=100;
        if(isuserturn)
        {
            if(userscore>=MAX_SCORE)
            {
                userScore.setText("USER IS WINNER....");
                roll.setEnabled(false);
                hold.setEnabled(false);
                reset.setEnabled(false);
            }
            else
            {
                roll.setEnabled(false);
                hold.setEnabled(false);
                // reset.setEnabled(false);
                isuserturn=false;
                computerPlays();
                reset.setEnabled(false);
            }
        }
        else
        {

            if(compscore>=MAX_SCORE)
            {
                computerScore.setText("COMP IS WINNER....");
                roll.setEnabled(false);
                hold.setEnabled(false);
                reset.setEnabled(false);
            }
            else
            {

                roll.setEnabled(true);
                hold.setEnabled(true);
                reset.setEnabled(true);
                isuserturn=true;


            }
        }
    }
    public void computerPlays()
    {
        if(!isuserturn)
        {
            if(turnscore<=20)
            {


                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        rollfn();
                        computerPlays();
                    }
                },1000);

            }
            else
            {
                holdfn();
            }
        }

    }

    public void cur_Player()
    {
        if(isuserturn)
        {
            currentPlayer.setText("Current Player is USER.");
        }
        else
        {
            currentPlayer.setText("Current Player is COMPUTER.");
        }
    }

}