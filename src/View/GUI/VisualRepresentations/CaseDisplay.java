package View.GUI.VisualRepresentations;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import View.GUI.Displayable;

/**
 * @author: main
 * @version: 01/12/14
 *  class to model a case
 */
public class CaseDisplay   extends  Displayable
{
    public CaseDisplay(int x, int y)
    {
        super(x,y, "take case ");
    }

    @Override
    public void draw(Graphics2D drawer)
    {
        drawer.setColor(Color.GREEN);
        Rectangle2D.Double  desk = new Rectangle2D.Double(x,y,50,50);
        drawer.setColor(Color.black);
        drawer.drawString("box",x-10,y);
        drawer.draw(desk);
        shapes.add(desk);
    }

}
