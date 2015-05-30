package View.GUI.VisualRepresentations;

import View.GUI.Displayable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author: main
 * @version: 01/12/14
 * insert comment here
 */
public class NoteBookDisplayer extends Displayable
{

    public NoteBookDisplayer(int x, int y)
    {
        super(x,y, "take notebook");
    }

    @Override
    public void draw(Graphics2D drawer)
    {

        drawer.setColor(Color.YELLOW);

        Rectangle2D.Double  desk = new Rectangle2D.Double(x,y,50,50);
        drawer.setColor(Color.RED);
        drawer.fill(desk);
        drawer.drawString("notebook", x-10, y);
        shapes.add(desk);
    }
}
