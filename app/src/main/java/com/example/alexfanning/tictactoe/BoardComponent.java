package com.example.alexfanning.tictactoe;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by alex.fanning on 25-Jul-17.
 */

public class BoardComponent extends ImageButton {



    private int xAxis;
    private int yAxis;
    private boolean isX = false;
    private boolean isY = false;
    private boolean isSelected = false;


    public static Bitmap getBitmap(BoardComponent bc){
        try {
        Bitmap bitmapBc = ((BitmapDrawable) bc.getBackground()).getBitmap();
            return bitmapBc;
        }catch (Exception e){
            return null;
        }

    }


//    Default Image Button constructors
    public BoardComponent(Context c){
        super(c);
        setBackground(c.getDrawable(R.drawable.ic_empty));
    }

    public BoardComponent(Context c, AttributeSet atts)
    {
        super(c, atts);
        setBackground(c.getDrawable(R.drawable.ic_empty));
    }

    public BoardComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackground(context.getDrawable(R.drawable.ic_empty));
    }

    public BoardComponent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setBackground(context.getDrawable(R.drawable.ic_empty));
    }

    public void setCoors(int x, int y){
        this.xAxis = x;
        this.yAxis = y;
    }

    public void clear(Context c){
        setBackground(c.getDrawable(R.drawable.ic_empty));
        isSelected = false;
    }



    public int getxAxis() {
        return xAxis;
    }

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public boolean isX() {
        return isX;
    }

    public void setIsX(boolean x,Context c) {
        isY = x;
        setBackground(c.getDrawable(R.drawable.ic_x));
        isSelected = true;
    }

    public boolean isY() {
        return isX;
    }

    public void setIsY(boolean y, Context c) {
        isY = y;
        setBackground(c.getDrawable(R.drawable.ic_y));
        isSelected = false;
    }


    public boolean checkIsSelected() {
        return isSelected;
    }


    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }


}
