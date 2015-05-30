package View.GUI.VisualRepresentations;

import View.GUI.Displayable;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * class that is responsible for drawing a
 * non player Character onto the screen
 * @author Jack Davey
 * @version 3oth November
 */
public class CharacterDisplay extends Displayable
{
    private String label;
    public CharacterDisplay(int x, int y, String label, String name)
    {
        super(x,y, "introduce " + name);
        this.label = label;
    }

    @Override
    public void draw(Graphics2D drawer)
    {

        Ellipse2D.Double head = new Ellipse2D.Double(x,y,60,60);
        Ellipse2D.Double eyeLeft = new Ellipse2D.Double(x+25,y+25,5,5);
        Ellipse2D.Double eyeRight = new Ellipse2D.Double(x+45,y+25,5,5);
        Rectangle2D.Double body = new Rectangle2D.Double(x,y+60,60,80);
        Line2D.Double leftArm =new Line2D.Double(x -50,y+100,x,y+100);
        Line2D.Double rightArm =new Line2D.Double(x+60,y+100,x+110,y+100);
        Line2D.Double leftLeg = new Line2D.Double(x,y+140,x,y+190);
        Line2D.Double rightLeg = new Line2D.Double(x+60,y+140,x+60,y+190);

        drawer.setColor(Color.BLUE);
        drawer.fill(head);
        drawer.fill(body);
        drawer.draw(leftArm);
        drawer.draw(rightArm);
        drawer.draw(leftLeg);
        drawer.draw(rightLeg);
        shapes.add(head);
        shapes.add(body);
        shapes.add(leftArm);
        shapes.add(rightArm);
        shapes.add(leftLeg);
        shapes.add(rightLeg);
        drawer.setColor(Color.YELLOW);

        drawer.fill(eyeLeft);
        drawer.fill(eyeRight);
        drawer.drawArc(x + 25, y + 45, 20, 10, 0, -190);
        drawer.setColor(Color.black);
        drawer.drawString(label, x, y+70);

    }
}
