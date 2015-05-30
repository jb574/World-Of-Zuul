package View.GUI.VisualRepresentations.rooms;

import View.GUI.VisualRepresentations.RoomDisplay;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @author: main
 * @version: 29/11/14
 * class to model the uni pub
 */
public class PubDisplay extends RoomDisplay
{
    public PubDisplay(int x, int y,int startX,int startY)
    {
        super(x,y,startX,startY);
        setBackground(Color.ORANGE);
    }

    @Override
    public void draw(Graphics2D drawer)
    {
        drawer.setBackground(Color.BLACK);
        drawer.setColor(Color.GRAY);
        Rectangle2D.Double  desk = new Rectangle2D.Double(0,0,500,100);
        shapes.add(desk);
        drawer.fill(desk);
        drawer.setColor(Color.yellow);
        for(int ypos = 100; ypos <= 500; ypos = ypos + 50)
        {
            for (int xpos = 0; xpos <= 500; xpos = xpos + 70)
            {
                Ellipse2D.Double table = new Ellipse2D.Double(xpos,ypos,50,50);
                drawer.fill(table);
                shapes.add(table);
            }
        }
    }
}
