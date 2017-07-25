package com.example.alexfanning.tictactoe;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private BoardComponent[][] mBoardComps;
    private TextView mTvPlayer;
    private boolean mIsPlayer1sGo;
    private User mPlayer1;
    private User mPlayer2;
    private int numClicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseBoard();
        create2PlayerGame();
    }

    private void initialiseBoard(){
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
        BoardComponent bcClicked = (BoardComponent)view;
        if (bcClicked.isSelected()){return;}
        if (mIsPlayer1sGo){
            bcClicked.setBackground(getDrawable(R.drawable.ic_x));
//            bcClicked.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPlayer1));
            bcClicked.setSelected(true);
            setLabelGoText(mPlayer2);
            mIsPlayer1sGo=false;
        }else if (!mIsPlayer1sGo){
//            bcClicked.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPlayer2));
            bcClicked.setBackground(getDrawable(R.drawable.ic_y));
            setLabelGoText(mPlayer1);
            bcClicked.setSelected(true);
            mIsPlayer1sGo=true;
        }
        ++numClicks;

        if (numClicks > 4){
            boolean checkIsYAxis = true;
           checkDiagonals();
            for (int i = 0;i<2;i++){
                checkIsWinner(checkIsYAxis);
                checkIsYAxis = false;
            }

        }
    }

    private void checkDiagonals(){
        if (!mBoardComps[1][1].isSelected()){return ;}
        Bitmap bitmapDrawableX = ((BitmapDrawable) getDrawable(R.drawable.ic_x)).getBitmap();
        Bitmap bitmapDrawableY = ((BitmapDrawable) getDrawable(R.drawable.ic_y)).getBitmap();
        Bitmap bitmapToCheck = null;

        if (BoardComponent.getBitmap(mBoardComps[1][1]) == bitmapDrawableX) {
            bitmapToCheck = bitmapDrawableX;
        }else if(BoardComponent.getBitmap(mBoardComps[1][1]) == bitmapDrawableX){
            bitmapToCheck = bitmapDrawableY;
        }
        if (BoardComponent.getBitmap(mBoardComps[0][2]) == bitmapToCheck && BoardComponent.getBitmap(mBoardComps[2][0]) == bitmapToCheck){
                gameOver(mPlayer1);
        }else if (BoardComponent.getBitmap(mBoardComps[0][0]) == bitmapToCheck && BoardComponent.getBitmap(mBoardComps[2][2]) == bitmapToCheck){
            gameOver(mPlayer1);
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

                if (selectedBc.isSelected()){

                    Bitmap bitmapBc = BoardComponent.getBitmap(selectedBc) ; //selectedBc.getBitmap(); //((BitmapDrawable) selectedBc.getBackground()).getBitmap();
                    Bitmap bitmapDrawableX = ((BitmapDrawable) getDrawable(R.drawable.ic_x)).getBitmap();
                    Bitmap bitmapDrawableY = ((BitmapDrawable) getDrawable(R.drawable.ic_y)).getBitmap();
                    if (bitmapBc == bitmapDrawableX){
                        numSelectedPlayer1++;
                    }else if (bitmapBc == bitmapDrawableY){
                        numSelectedPlayer2++;
                    }
                }
            }
            if (numSelectedPlayer1 == 3){
                isWinner = true;
//                Player 1 is the winner
            }else if (numSelectedPlayer2 == 3){
                isWinner = true;
//                Player 2 is the winner
            }
        }

        if (isWinner){
            gameOver(mPlayer1);
            return;
        }
    }
//    WInner Checks
//    private boolean xAxisCheck(){
//        for (int i = 0; i < 3; ++i){
//            int numSelected = 0;
//            for (int j = 0; i < 3; ++i){
//                if (mBoardComps[i][j].isSelected())
//                    numSelected++;
//            }
//            if (numSelected == 3){
//                return true;
//            }
//        }
//        return false;
//    }
//    private boolean xAxisCheck(){

    private void gameOver(User user){
        Toast.makeText(this,"WINNER",Toast.LENGTH_LONG).show();

    }



}
