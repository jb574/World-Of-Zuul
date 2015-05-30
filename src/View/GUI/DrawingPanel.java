package View.GUI;

import View.GUI.VisualRepresentations.RoomDisplay;
import View.GUI.VisualRepresentations.rooms.OutsideDisplay;
import View.GuiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

/**
 * class that we can draw on
 * @author Jack Davey
 * @version 9th November 2014
 */
public class DrawingPanel  extends JPanel
{

    private List<Displayable>  reps;
    private GuiView view;

    /**
     * constructor for this class
     * @param view the view we are linked with
     */
    public DrawingPanel(GuiView view)
    {
        super();
        setOpaque(true);
        this.view = view;
        reps = new LinkedList<>();
        reps = Collections.synchronizedList(reps);
        setPreferredSize(new Dimension(500,500));
        setMaximumSize(new Dimension(500,500));
        addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("test");
                handleUserCLlick(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });
    }


    /**
     * method to handle  a click
     * by the user
     * @param x   the x position fo the click
     * @param y   the y positon of the click
     */
    public void handleUserCLlick(int x, int y)
    {
        for(Displayable representation : reps)
        {
            if( representation.contains(x,y))
            {
                 view.interpretCommand(representation.getCommand());
                return;
            }
        }
    }

    /**
     * claears the things we need to draw
     */
    public void clear()
    {
        reps.clear();
    }

    /**
     * method that draws everything on screen
     * @param g the graphics object we use for drawing
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setOpaque(true);
          Graphics2D graphicsOb = (Graphics2D) g;
          Iterator<Displayable> it = reps.iterator();
        while (it.hasNext())
        {
            Displayable dis = it.next();


            if(dis instanceof RoomDisplay && !(dis instanceof OutsideDisplay))
            {
                Color color =graphicsOb.getColor();
                RoomDisplay thing = (RoomDisplay) dis;
                graphicsOb.setColor(thing.getBackgroundColour());
                graphicsOb.fill3DRect(0,0,500,500,false);
                graphicsOb.setColor(color);
            }

            dis.draw(graphicsOb);
        }

    }

    public void setReps(List<Displayable> reps)
    {
        this.reps.clear();
        this.reps.addAll(reps);

    }
}
