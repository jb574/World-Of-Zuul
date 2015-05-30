package View.GUI.VisualRepresentations.rooms;

import View.GUI.VisualRepresentations.RoomDisplay;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author Jack Davey
 * @version 30th November 2014
 * class to mobel the background of the lab
 */
public class LabDisplay   extends RoomDisplay
{
    public LabDisplay(int x, int y, int startX,int startY)
    {

        super(x,y,startX,startY);
        setBackground(Color.black);
    }

    @Override
    public void draw(Graphics2D drawer)
    {
        drawer.setBackground(Color.GRAY);
        drawer.setColor(Color.WHITE);
        for(int y = 0; y < 500; y = y + 120)
        {
            Rectangle2D.Double  desk = new Rectangle2D.Double(0,y,500,100);
            drawer.fill(desk);
            shapes.add(desk);
        }


    }
}

