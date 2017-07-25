package com.example.alexfanning.tictactoe;

/**
 * Created by alex.fanning on 25-Jul-17.
 */

public class User {


    private boolean isComputer;
    private boolean isPlayer1;
    private boolean isPlayer2;

    public User(){

    }


    public boolean getIsComputer() {
        return isComputer;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
        isPlayer1 = false;
        isPlayer2 = false;
    }

    public boolean getIsPlayer1() {
        return isPlayer1;

    }

    public void setPlayer1(boolean player1) {
        isPlayer1 = player1;
        isPlayer2 = false;
        isComputer = false;
    }

    public boolean getIsPlayer2() {
        return isPlayer2;
    }

    public void setPlayer2(boolean player2) {
        isPlayer2 = player2;
        isPlayer1 = false;
        isComputer = false;
    }




}
