package Model;

import View.GUI.DataTransferrer;

/**
 * class tha represents a player
 * in the world of zuul
 * @author Jack Davey
 * @version 28th October 2014
 */
public class Player extends Actor
{


    /**
     * method to send back information about the Gui
     * to the controller
     * @return an object containing all the information the gui needs to update its
     *          display
     */
     public DataTransferrer getGuiInformation()
     {
          return new DataTransferrer(getCurrentRoom().getDescription(),getCurrentRoom().getCharacterNames(),
                  getCurrentRoom().getExitGUIinformation(),
                  getCurrentRoom().getItemNames(),getBag().getItemNames());
     }

    /**
     * main constructor for the player class
     * @param currentRoom  the room to start in
     * @param name the name of the player
     */
    public Player(Room currentRoom,String name)
    {
        super(currentRoom,name);
    }


}
