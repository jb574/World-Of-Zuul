package View.GUI.VisualRepresentations;

import View.GUI.Displayable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author: main
 * @version: 01/12/14
 * class to model the box
 */
public class BoxDisplay extends Displayable
{
    public BoxDisplay(int x, int y, String command)
    {
        super(x,y, "take box");
    }

    @Override
    public void draw(Graphics2D drawer)
    {

        drawer.setColor(Color.PINK);
        Rectangle2D.Double  desk = new Rectangle2D.Double(x,y,100,100);
        drawer.fill(desk);
        drawer.setColor(Color.black);
        drawer.drawString("box",x-10,y);
        shapes.add(desk);

    }

}
