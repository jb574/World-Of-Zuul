package View.GUI.VisualRepresentations;

import View.GUI.Displayable;

import java.awt.*;


/**
 * class  that models an generic item
 * to be extended by subclasses
 * @author Jack Davey
 * @version 28th November 2014
 */
public class ItemDisplay extends Displayable
{
    public ItemDisplay(int x, int y)
    {
        super(x,y, "");
    }

    @Override
    public void draw(Graphics2D drawer)
    {

    }
}
