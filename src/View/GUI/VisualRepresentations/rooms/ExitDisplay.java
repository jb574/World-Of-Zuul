package View.GUI.VisualRepresentations.rooms;

import View.GUI.Displayable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * class to model an exit
 * @author Jack Davey
 * @version 21st November 2014
 */
public class ExitDisplay extends Displayable
{

    private String label;
    public  ExitDisplay(int x, int y, String command, String label)
    {
        super(x,y, command);
        this.label = label;
    }

    public  void draw(Graphics2D drawer)
    {
        drawer.setColor(Color.DARK_GRAY);
        Rectangle2D.Double door = new Rectangle2D.Double(x,y,20,50);
        shapes.add(door);
        drawer.setColor(Color.black);
        drawer.drawString(label, x - 10, y);
        drawer.fill(door);
    }

}


