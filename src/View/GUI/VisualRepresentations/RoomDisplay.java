package View.GUI.VisualRepresentations;

import View.GUI.Displayable;

import java.awt.*;

/**
 * class that models
 * a generic room
 * @author Jack Davey
 * @version 13th November 2014
 */
public abstract class RoomDisplay  extends Displayable
{

    protected  void setBackground(Color col)
    {
        background = col;
    }
    public RoomDisplay(int x, int y, int charaStartX, int CharaStarty)
    {
        super(x,y, "");
        this.startx = charaStartX;
        this.endy   = charaStartX;
        background = Color.WHITE;
    }

    public  Color getBackgroundColour()
    {
        return background;
    }


    private Color background;
    private int startx;
    private int endy;

    public int getStartx()
    {
        return startx;
    }

    public void setStartx(int startx)
    {
        this.startx = startx;
    }

    public int getEndy()
    {
        return endy;
    }

    public void setEndy(int endy)
    {
        this.endy = endy;
    }

    @Override
    public  boolean contains(int newX, int newY)
    {
        return false;
    }

    @Override
     public abstract void draw(Graphics2D drawer);



}
