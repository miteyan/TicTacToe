package com.miteyan.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MinimaxActivity extends AppCompatActivity {

    private MyGame myGame;

    private Button mBoardButtons[][];
    private Button newGame;

    TextView winnerLabel;
    TextView winsLabel;

    public int HUMAN = 1;
    public int COMPUTER = 2;
    Random random;

    private int First = 0;
    private int Counter = 0;
    private boolean isGameOver = false;

    private int wins = 0;
    private int draws = 0;
    private int loses = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mBoardButtons = new Button[3][3];
        mBoardButtons[0][0] = (Button) findViewById(R.id.NW);
        mBoardButtons[0][1] = (Button) findViewById(R.id.N);
        mBoardButtons[0][2] = (Button) findViewById(R.id.NE);
        mBoardButtons[1][0] = (Button) findViewById(R.id.W);
        mBoardButtons[1][1] = (Button) findViewById(R.id.C);
        mBoardButtons[1][2] = (Button) findViewById(R.id.E);
        mBoardButtons[2][0] = (Button) findViewById(R.id.SW);
        mBoardButtons[2][1] = (Button) findViewById(R.id.S);
        mBoardButtons[2][2] = (Button) findViewById(R.id.SE);

        newGame = (Button) findViewById(R.id.btnStartAgain);

        winnerLabel = (TextView) findViewById(R.id.winnerLabel);
        winsLabel = (TextView) findViewById(R.id.wins);

        random = new Random();

        final CharSequence[] items = {"Computer", "Player"};

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Who goes first?");
        alertDialog.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item] == "Computer") {
                    First = 1; // Computer
                } else if (items[item] == "Player") {
                    First = 2; // Player
                }
                dialog.dismiss();

                myGame = new MyGame(MinimaxActivity.this);

                if (First == 1) {
                    startNewGame(true); // True For Computer
                }
                if (First == 2) {
                    startNewGame(false); // False For Player
                }

            }
        });
        alertDialog.show();

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Counter % 2 == 0) {
                    startNewGame(false);
                    Counter++;
                } else {
                    startNewGame(true);
                    Counter++;
                }
            }
        });

    }

    private void startNewGame(boolean GoesFirst) {

        MyResetBoard(); // Look at board reset

        if (GoesFirst) {
            // Computer Goes First
            setMove(random.nextInt(3), random.nextInt(3), COMPUTER);
        }
        isGameOver = false;
    }

    private void MyResetBoard() {
        myGame.resetBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mBoardButtons[i][j].setText("");
                mBoardButtons[i][j].setOnClickListener(new ButtonClickListener(i, j));
            }
        }
    }

    private class ButtonClickListener implements View.OnClickListener {

        int x, y;

        public ButtonClickListener(int i, int j) {
            this.x = i;
            this.y = j;
        }

        @Override
        public void onClick(View v) {//button onClick
            Toast computer = Toast.makeText(getApplicationContext(),"Computer wins!",Toast.LENGTH_SHORT);
            Toast draw = Toast.makeText(getApplicationContext(),"Draw!",Toast.LENGTH_SHORT);
            computer.setGravity(Gravity.CENTER_VERTICAL,0,0);
            draw.setGravity(Gravity.CENTER_VERTICAL,0,0);
            if (!isGameOver &&mBoardButtons[x][y].getText()=="") { // If the game is not over
                if (mBoardButtons[x][y].isEnabled()) {
                    setMove(x, y, HUMAN); // Human makes a move CROSS


                    int winner = myGame.CheckGameState();

                    if (winner == myGame.PLAYING) { // If still playing
                        int[] result = myGame.move();
                        setMove(result[0], result[1], COMPUTER);
                        winner = myGame.CheckGameState();
                    }

                    winner = myGame.CheckGameState();

                    if (winner == myGame.PLAYING) {
                    } else if (winner == myGame.DRAW) { // If draw
                        isGameOver = true;
                        draw.show();
                        draws++;
                        winsLabel.setText("Wins:" + wins + "    Draws: "+draws+ "   Loses:" + loses);


                    } else if (winner == myGame.CROSS_WON) { // X Won
                        isGameOver = true;
                        //human cant win

                    } else if (winner == myGame.NOUGHT_WON) { // O Won
                        isGameOver = true;
                        winnerLabel.setText("Computer won!");
                        computer.show();
                        loses++;
                        winsLabel.setText("Wins:" + wins + "    Draws: "+draws+ "   Loses:" + loses);

                    }
                }
            }
        }
    }

    public void setMove(int x, int y, int player) {
        myGame.placeAMove(x, y, player);
        if (player == 1) {
            mBoardButtons[x][y].setText("x");
        } else {
            mBoardButtons[x][y].setText("o");
        }
    }
}