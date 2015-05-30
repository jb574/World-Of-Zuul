package View.GUI.VisualRepresentations.rooms;

import View.GUI.VisualRepresentations.RoomDisplay;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author: main
 * @version: 30/11/14
 * class to model the lecture theatrue
 */
public class TheatreDisplay extends RoomDisplay
{
    public TheatreDisplay(int x, int y,int startX,int startY)

    {
        super(x,y,startX,startY);
        setBackground(Color.GREEN);
    }



    @Override
    public void draw(Graphics2D drawer)
    {
        drawer.setBackground(Color.GREEN);
        drawer.setColor(Color.RED);
        Rectangle2D.Double  desk = new Rectangle2D.Double(0,0,500,100);
        drawer.fill(desk);
        shapes.add(desk);
        for(int ypos = 100; ypos <= 500; ypos = ypos + 70)
        {
            drawSeatHalf(drawer,250,0, ypos);
            drawSeatHalf(drawer,500,270,ypos);
        }
    }

    public void drawSeatHalf(Graphics2D drawer,int rowEnd, int xStart, int ypos)
    {
        for (int xpos = xStart; xpos <= rowEnd; xpos = xpos + 70)
        {
            Rectangle2D.Double seat = new Rectangle2D.Double(xpos,ypos,50,50);
            drawer.fill(seat);
            shapes.add(seat);
        }
    }
}


