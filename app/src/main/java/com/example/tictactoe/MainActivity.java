package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.tictactoe.R;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    
    private int player = 1;
    Button btn;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn = (Button) findViewById(R.id.btn);
        textView2 = (TextView) findViewById(R.id.textView2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player = switchPlayer(player);
                textView2.setText(Integer.toString(player));
            }
        });

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        TextView p1Text = (TextView)findViewById(R.id.player1Text);
        TextView p2Text = (TextView)findViewById(R.id.player2Text);

        Button proof = (Button)findViewById(R.id.conceptProofButton);

        proof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerDisplay(p1Text, p2Text);
            }
        }); // listener that calls next activity method


        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static int switchPlayer(int player)
    {
        if(player == 1)
            player = 2;
        else
            player = 1;
        return player;
    }//end methodxded

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        resetBoard();
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
    }

    private void player2Wins() {
        player2Points++;
        resetBoard();
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }


    public void playerDisplay (TextView player1Text, TextView player2Text)
    {
        Button proofOfConcept = (Button) findViewById(R.id.conceptProofButton);
        proofOfConcept.setOnClickListener(new View.OnClickListener() {
            int pTurn = 1;
            @Override
            public void onClick (View v)
            {
                if (pTurn % 2 == 0)
                {
                    Log.i("info", "Player " + 2 + "'s turn");
                    player1Text.setBackgroundColor(Color.WHITE);
                    player2Text.setBackgroundColor(Color.YELLOW);
                }
                else {
                    Log.i("info", "Player " + 1 + "'s turn");
                    player2Text.setBackgroundColor(Color.WHITE);
                    player1Text.setBackgroundColor(Color.YELLOW);
                }
                pTurn++;
            } // end onCLick
        });
    } // end next button handler

    private void resetBoard()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }

}
