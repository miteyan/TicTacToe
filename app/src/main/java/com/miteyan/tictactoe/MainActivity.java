package com.miteyan.tictactoe;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private boolean firstPlayer = true;
    private boolean winner = false;
    private int moveCount = 0;

    private int wins1 = 0;
    private int wins2 = 0;
    private int draws = 0;

    private Button btnNW;
    private Button btnN;
    private Button btnNE;
    private Button btnW;
    private Button btnC;
    private Button btnE;
    private Button btnSW;
    private Button btnS;
    private Button btnSE;
    private TextView winnerLabel;
    private TextView wins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnNW = (Button) findViewById(R.id.NW);
        btnN = (Button) findViewById(R.id.N);
        btnNE = (Button) findViewById(R.id.NE);
        btnW = (Button) findViewById(R.id.W);
        btnC = (Button) findViewById(R.id.C);
        btnE = (Button) findViewById(R.id.E);
        btnSW = (Button) findViewById(R.id.SW);
        btnS = (Button) findViewById(R.id.S);
        btnSE = (Button) findViewById(R.id.SE);

        winnerLabel = (TextView) findViewById(R.id.winnerLabel);
        winnerLabel.setText("Player 1");
        wins = (TextView) findViewById(R.id.wins2p);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                WebView webview = (WebView) findViewById(R.id.webview);
                webview.loadUrl("https://en.wikipedia.org/wiki/Tic-tac-toe");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void update(Button btn) {
        Toast draw = Toast.makeText(getApplicationContext(),"Draw",Toast.LENGTH_SHORT);
        draw.setGravity(Gravity.CENTER_VERTICAL,0,0);

        if (btn.getText() == "") {
            if (firstPlayer) {
                btn.setText("X");
            }
            else{

                btn.setText( "O");
            }
            moveCount++;
            firstPlayer = !firstPlayer;
        }
        if (moveCount==9){
            draw.show();
        }
        hasWon();

    }

    private void hasWon() {
        Toast p1win = Toast.makeText(getApplicationContext(),"Player 1 wins!",Toast.LENGTH_SHORT);
        Toast p2win = Toast.makeText(getApplicationContext(),"Player 2 wins!",Toast.LENGTH_SHORT);
        p1win.setGravity(Gravity.CENTER_VERTICAL,0,0);
        p2win.setGravity(Gravity.CENTER_VERTICAL,0,0);

        //horizontal
        if (btnNW.getText()==btnN.getText() && btnN.getText()==btnNE.getText() && btnNE.getText() !="") {
            winner=true;
        }
        if (btnW.getText()==btnC.getText() && btnC.getText()==btnE.getText() && btnE.getText() !="") {
            winner=true;
        }
        if (btnSW.getText()==btnS.getText() && btnS.getText()==btnSE.getText() && btnSE.getText() !="") {
            winner=true;
        }
        //diagonal

        if (btnSW.getText()==btnC.getText() && btnC.getText()==btnNE.getText() && btnNE.getText() !="") {
            winner=true;
        }
        if (btnSE.getText()==btnC.getText() && btnC.getText()==btnNW.getText() && btnNW.getText() !="") {
            winner=true;
        }

        //verticle
        if (btnSW.getText()==btnW.getText() && btnW.getText()==btnNW.getText() && btnNW.getText() !="") {
            winner=true;
        }
        if (btnSE.getText()==btnE.getText() && btnE.getText()==btnNE.getText() && btnNE.getText() !="") {
            winner=true;
        }
        if (btnS.getText()==btnC.getText() && btnC.getText()==btnN.getText() && btnN.getText() !="") {
            winner=true;
        }
        if (winner){
            if (!firstPlayer) {
                p1win.show();
                wins1++;
                wins.setText("P1 wins:" + wins1 + "    Draws: "+draws+ "   P2 wins:" + wins2);

            }else{
                p2win.show();
                wins2++;
                wins.setText("P1 wins:" + wins1 + "    Draws: "+draws+ "   P2 wins:" + wins2);

            }
        }else{
            if (firstPlayer) {
                winnerLabel.setText("Player 1");
            }else{
                winnerLabel.setText("Player 2");
            }
        }
        if (!winner && moveCount==9){
            winnerLabel.setText("Draw!");
            draws++;
            wins.setText("P1 wins:" + wins1 + "    Draws: "+draws+ "   P2 wins:" + wins2);

        }
    }


    public void pushButton(View view) {
        if (!winner) {
            //which button pressed
            if (view.getId() == btnNW.getId()) {
                update(btnNW);
            }
            if (view.getId() == btnN.getId()) {
                update(btnN);
            }
            if (view.getId() == btnNE.getId()) {
                update(btnNE);
            }
            if (view.getId() == btnW.getId()) {
                update(btnW);
            }
            if (view.getId() == btnC.getId()) {
                update(btnC);
            }
            if (view.getId() == btnE.getId()) {
                update(btnE);
            }
            if (view.getId() == btnSW.getId()) {
                update(btnSW);
            }
            if (view.getId() == btnS.getId()) {
                update(btnS);
            }
            if (view.getId() == btnSE.getId()) {
                update(btnSE);
            }
        }
    }

    public void startAgain(View view) {
        btnN.setText("");
        btnNW.setText("");
        btnNE.setText("");
        btnC.setText("");
        btnW.setText("");
        btnE.setText("");
        btnSE.setText("");
        btnS.setText("");
        btnSW.setText("");
        moveCount=0;
        winner=false;
        firstPlayer=true;
        winnerLabel.setText("Player 1");
    }
}
