package View.GUI.VisualRepresentations.rooms;

import View.GUI.VisualRepresentations.RoomDisplay;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author: Jack Davey
 * @version: 27/11/14
 * class to model the outside of the uni
 */
public class OutsideDisplay  extends RoomDisplay
{

    public OutsideDisplay(int x , int y, int startX,int startY)
    {
        super(x,y,startX,startY);
    }

    @Override
    public void draw(Graphics2D drawer)
    {

        // test
        Rectangle2D.Double  lab = new Rectangle2D.Double(0,375,437,62);
        Rectangle2D.Double pub = new Rectangle2D.Double(0,0,62,375);
        Rectangle2D.Double theatre = new Rectangle2D.Double(375,0,62,375);
        drawer.fill(lab);
        drawer.fill(pub);
        drawer.fill(theatre);
        shapes.add(lab);
        shapes.add(pub);
        shapes.add(theatre);
    }
}
