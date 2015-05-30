package View.GUI.VisualRepresentations.rooms;

import View.GUI.VisualRepresentations.RoomDisplay;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author: main
 * @version: 30/11/14
 * insert comment here
 */
public class OfficeDisplay extends RoomDisplay
{
    public OfficeDisplay(int x, int y, int startX, int startY)
    {

        super(x,y,startX,startY);
        setBackground(Color.cyan);
    }

    @Override
    public void draw(Graphics2D drawer)
    {
        drawer.setBackground(Color.WHITE);
        drawer.setColor(Color.YELLOW);
        Rectangle2D.Double  desk = new Rectangle2D.Double(0 ,300,500,100);
        drawer.fill(desk);
        shapes.add(desk);
    }

}
