package View.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class that represents a
 * object that we can draw on screen
 * @author Jack Davey
 * @version 10th November 2014
 */
public abstract class Displayable
{
     protected int x;
    protected  int y;
   protected List<Shape> shapes;

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public Displayable(int x, int y, String newcommand)
    {
        changePosition(x, y);
        command = newcommand;
        shapes = new ArrayList<>();
    }


    public  boolean contains(int newX, int newY)
    {
        for(Shape currentShape : shapes)
        {
            if(currentShape.contains(newX,newY))
            {
                return  true;
            }
        }
        return false;
    }

    public void changePosition(int x, int y)
    {
        this.x = x;
        this.y = y;

    }

    protected   String command;

    public String getCommand()
    {
        return command;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }


    public abstract void draw(Graphics2D drawer);
}
