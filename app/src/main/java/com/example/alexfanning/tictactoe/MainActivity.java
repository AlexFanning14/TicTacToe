package com.example.alexfanning.tictactoe;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private BoardComponent[][] mBoardComps = null;
    private TextView mTvPlayer;
    private boolean mIsPlayer1sGo;
    private User mPlayer1;
    private User mPlayer2;
    private int numClicks = 0;
    private static boolean sIsOnePlayerGame;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static boolean isOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            generateMenuDialog(false,"");

    }

    private void startGame(){
        initialiseBoard();
        if (sIsOnePlayerGame){
            create1PlayerGame();
        }else{
            create2PlayerGame();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_1_player){
            sIsOnePlayerGame = true;
        }else if(id == R.id.menu_2_player){
            sIsOnePlayerGame = false;
        }
        startGame();
        return super.onOptionsItemSelected(item);
    }


    private void initialiseBoard(){
        if (mBoardComps != null){
            clearBoard();
        }
        isOver = false;
        numClicks = 0;
        mBoardComps = new BoardComponent[3][3];

        mTvPlayer = (TextView) findViewById(R.id.tvPlayer);
        BoardComponent arr00 = (BoardComponent) findViewById(R.id.boardComp00);
        arr00.setCoors(0,0);
        BoardComponent arr01 = (BoardComponent) findViewById(R.id.boardComp01);
        arr01.setCoors(0,1);
        BoardComponent arr02 = (BoardComponent) findViewById(R.id.boardComp02);
        arr02.setCoors(0,2);
        BoardComponent arr10 = (BoardComponent) findViewById(R.id.boardComp10);
        arr10.setCoors(1,0);
        BoardComponent arr11 = (BoardComponent) findViewById(R.id.boardComp11);
        arr11.setCoors(1,1);
        BoardComponent arr12 = (BoardComponent) findViewById(R.id.boardComp12);
        arr12.setCoors(1,2);
        BoardComponent arr20 = (BoardComponent) findViewById(R.id.boardComp20);
        arr20.setCoors(2,0);
        BoardComponent arr21 = (BoardComponent) findViewById(R.id.boardComp21);
        arr21.setCoors(2,1);
        BoardComponent arr22 = (BoardComponent) findViewById(R.id.boardComp22);
        arr22.setCoors(2,2);

        mBoardComps[0] = new BoardComponent[]{arr00,arr01,arr02};
        mBoardComps[1] = new BoardComponent[]{arr10,arr11,arr12};
        mBoardComps[2] = new BoardComponent[]{arr20,arr21,arr22};

    }

    private void clearBoard(){
        for (int i = 0;i < 3;i++){
            for (int j = 0;j<3;j++){
                mBoardComps[i][j].clear(this);
            }
        }
    }


    private void create1PlayerGame(){
        mPlayer1 = new User();
        mPlayer1.setPlayer1(true);

        mPlayer2 = new User();
        mPlayer2.setComputer(true);

        mIsPlayer1sGo = true;
        setLabelGoText(mPlayer1);
    }

    private void create2PlayerGame(){
        mPlayer1 = new User();
        mPlayer1.setPlayer1(true);

        mPlayer2 = new User();
        mPlayer2.setPlayer2(true);

        mIsPlayer1sGo = true;
        setLabelGoText(mPlayer1);
    }

    private void setLabelGoText(User u){
        if (u.getIsPlayer1()){
            mTvPlayer.setText("Player 1s turn");
        }else if (u.getIsPlayer2()){
            mTvPlayer.setText("Player 2s turn");
        }else if (u.getIsComputer()){
            mTvPlayer.setText(("Computers Go"));
        }
    }

    public void boardCompCLicked(View view) {
        if (sIsOnePlayerGame && !mIsPlayer1sGo){return;}
        BoardComponent bcClicked = (BoardComponent)view;
        if (bcClicked.checkIsSelected()){return;}
        if (mIsPlayer1sGo){
            bcClicked.setIsX(true,this);
            setLabelGoText(mPlayer2);
            mIsPlayer1sGo=false;
        }else if (!mIsPlayer1sGo){
            bcClicked.setIsO(true,this);
            setLabelGoText(mPlayer1);
            mIsPlayer1sGo=true;
        }
        ++numClicks;
        checkClicks();
        if (sIsOnePlayerGame && !isOver){
            computerMove();

            checkClicks();
        }

    }

    private void checkClicks(){
        if (numClicks > 4){
            if (numClicks == 9){
                isOver = true;
                generateMenuDialog(true,"Draw game!");
                return;
            }
            boolean checkIsYAxis = true;
            checkDiagonals();
            for (int i = 0;i<2;i++){
                checkIsWinner(checkIsYAxis);
                if (isOver){return;}
                checkIsYAxis = false;
            }

        }
    }

    private void computerMove() {
//        Handler handler = new Handler();
//        Runnable r=new Runnable() {
//            public void run() {
                smartComputerMove();
                ++numClicks;
//                boolean isSelected = false;
//
//                while (!isSelected){
//                    int x = getRandom(0,2);
//                    int y = getRandom(0,2);
//                    BoardComponent bc = mBoardComps[x][y];
//                    if (!bc.checkIsSelected()){
//                        bc.setIsO(true,getApplicationContext());
                        setLabelGoText(mPlayer1);
                        //isSelected = true;


                        mIsPlayer1sGo=true;
//                    }
//                }

//        };
//        handler.postDelayed(r, 1000);

    }

    private void smartComputerMove() {
        if (numClicks == 1) {
            selectFirstCompMove();

        } else if (numClicks > 1) {
            if (!checkForTwoInRow()){
                selectRandom();
            }

        }
    }


    private void selectFirstCompMove(){
        if (mBoardComps[1][1].checkIsSelected()){
            int x = getRandom(1,4);
            switch(x){
                case 1:
                    mBoardComps[0][0].setIsO(true,this);
                case 2:
                    mBoardComps[2][0].setIsO(true,this);
                case 3:
                    mBoardComps[2][0].setIsO(true,this);
                case 4:
                    mBoardComps[2][0].setIsO(true,this);
            }
        }else{
            mBoardComps[1][1].setIsO(true,this);
        }
    }

        private boolean checkForTwoInRow(){
            boolean checkIsYAxis = false;
            for (int k = 0; k < 2; ++k) {

            for(int i = 0; i < 3; ++i){
                int numSelectedPlayer1 = 0;
                ArrayList<BoardComponent> row = new ArrayList<>();
                for (int j = 0; j < 3; ++j) {

                    BoardComponent selectedBc;
                    if (checkIsYAxis) {
                        selectedBc = mBoardComps[i][j];
                    } else {
                        selectedBc = mBoardComps[j][i];
                    }

                    if (selectedBc.checkIsSelected()) {
                        if (selectedBc.isX()) {
                            numSelectedPlayer1++;
                        }
                    }else{
                        row.add(selectedBc);
                    }
                }
                if (numSelectedPlayer1 == 2) {
                    if (row.size() > 0){
                        row.get(0).setIsO(true,this);
                        return true;
                    }
                }
            }
            checkIsYAxis = true;
            }
            return false;

    }






    private void selectRandom() {
        boolean isSelected = false;

        while (!isSelected) {
            int x = getRandom(0, 2);
            int y = getRandom(0, 2);
            BoardComponent bc = mBoardComps[x][y];
            if (!bc.checkIsSelected()) {
                bc.setIsO(true, getApplicationContext());
                setLabelGoText(mPlayer1);
                isSelected = true;
                mIsPlayer1sGo = true;
            }
        }
    }


    private int getRandom(int min, int max){
        Random rn = new Random();
        int range = max - min + 1;
        int randomNum =  rn.nextInt(range) + min;
        return randomNum;
    }

    private void checkDiagonals(){
        if (!mBoardComps[1][1].checkIsSelected()){return ;}

        if (mBoardComps[1][1].isX()) {
            if ((mBoardComps[0][2].isX()&& mBoardComps[2][0].isX()) || mBoardComps[0][0].isX()&& mBoardComps[2][2].isX()){
                gameOver(mPlayer1);
            }
        }else if(mBoardComps[1][1].isO()){
            if ((mBoardComps[0][2].isO()&& mBoardComps[2][0].isO()) || mBoardComps[0][0].isO()&& mBoardComps[2][2].isO()){
                gameOver(mPlayer2);
            }
        }

    }



    private void checkIsWinner(boolean checkIsYAxis){
        boolean isWinner = false;

        for (int i = 0; i < 3; ++i){
            int numSelectedPlayer1 = 0;
            int numSelectedPlayer2 = 0;
            for (int j = 0; j < 3; ++j){
                BoardComponent selectedBc = null;
                if (checkIsYAxis){
                    selectedBc = mBoardComps[i][j];
                }else{
                    selectedBc = mBoardComps[j][i];
                }

                if (selectedBc.checkIsSelected()){
                    if (selectedBc.isX()){
                        numSelectedPlayer1++;
                    }else if (selectedBc.isO()){
                        numSelectedPlayer2++;
                    }
                }
            }
            if (numSelectedPlayer1 == 3){
                isWinner = true;
            }else if (numSelectedPlayer2 == 3){
                isWinner = true;
            }
        }

        if (isWinner){
            gameOver(mPlayer1);
        }
    }


    private void gameOver(User user){
        String winner = "";
        if (user.getIsPlayer1()){
            winner = "Player 1 has won!";
        }else if (user.getIsPlayer2()){
            winner = "Player 2 has won!";
        }else if (user.getIsComputer()){
            winner = "Computer has won!";
        }
        isOver = true;
        generateMenuDialog(true,winner);

    }

    private void generateMenuDialog(boolean wasWinner, String whoWOn){
        final CharSequence menus[] = new CharSequence[]{"One Player Game", "Two Player Game"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String title = "";
        if (wasWinner){
            title = whoWOn + "\nChoose Game Mode";
        }else{
            title = whoWOn + "Choose Game Mode";
        }
        builder.setTitle(title);

        builder.setItems(menus, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    sIsOnePlayerGame = true;
                }else{
                    sIsOnePlayerGame = false;
                }
                startGame();
            }
        });
        builder.show();
    }



}
