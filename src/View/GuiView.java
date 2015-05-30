package View;

import Controller.GameController;
import Setup.Init;
import Setup.Main;
import View.GUI.DataTransferrer;
import View.GUI.MainScreen;
import View.GUI.RequestProcessor;

import javax.swing.*;
import java.util.Stack;

/**
 * the view we interact with when we have a graphical
 * application
 * @authr Jack Davey
 * @verson 7th November
 */
public class GuiView extends  View
{
    private MainScreen screen;

    private static GuiView currentView;

    private Stack<Command> commands;

    /**
     * retrieves the current view
     * @return as above
     */
    public static GuiView getCurrentView()
    {
        if(currentView == null)
        {
            currentView = new GuiView();
        }
        return currentView;
    }


    /**
     * method to interpet a command sent fromt he gui
     * @param command the commmand inquestion
     */
    public void interpretCommand(String command)
    {
         Command com = produceCommandFromInput(command);
       // RequestProcessor.getRequestProcessor().AddCommand(com);
        GameController.initView().dealWIthCommand(com);
        Main.prepare();
        Thread.yield();

    }

    /**
     *
     * method to get the next command to be processed
     * kept for compatiblity withte text baed game
     * @return as above
     */
    @Override
    public Command getCommand()
    {
        if(commands.isEmpty())
        {
           return null;
        }
        else
        {
            return  commands.pop();
        }


    }

    private  static boolean boxNeeded  = false;

    public  static void setBoxNeeded(boolean isNeeded)
    {
        boxNeeded = isNeeded;
    }

    private static String playerName;

    public static String getPlayerName()
    {
        return playerName;
    }

    public static void setPlayerName(String playerName)
    {
        GuiView.playerName = playerName;
    }

    @Override
    public <data> void printData(data thing)
    {
        if(boxNeeded)
        {
            DisplayMoreProminentText(thing);
        }
        else
        {
            screen.addText(Init.newline + thing.toString());
        }

    }

    /**
     * method called when we want ot display data in a
     * popup box
     * @param thing the data we want to dispaly
     */
    private <data> void DisplayMoreProminentText(data thing)
    {
        screen.addText(thing.toString() + Init.newline );
        if(thing != null && thing.toString() != "")
        {
            JOptionPane.showMessageDialog(null, thing.toString());
        }
    }

    /**
     * method to take info from the gui and dipplay it on screen
     * @param trans  the model info
     */
    @Override
    public void recieveNewModelInformation(DataTransferrer trans)
    {

                screen.UpdateDisplay(trans.getCurrentRoomDescription(),trans.getRoomExits(),trans.getCharacters(),trans.getPlayerItems(),trans.getRoomItems());
    }

    public static  void initView()
    {
        setView(new GuiView());
    }

    /**
     * constructor
     */
    private GuiView()
    {
        screen = new  MainScreen(this);
        commands = new Stack<>();
    }
}
