package View.GUI;
import java.util.List;


/**
 * simple class to transfer data between the model and the view of
 * the application for display on the gui
 * @author Jack Davey
 * @version 14th November 2014
 */
public class DataTransferrer
{
    private List<String> roomExits;
    private List<String> characters;
    private List<String> roomItems;
    private List<String> playerItems;
    private String currentRoomDescription;

    /**
     * constructor for this class
     * @param characters the list of the names of the characters in the current room
     * @param roomExits  the list of exits from the current room
     * @param roomItems   the current items in the room
     * @param playerItems  the current items held by the player
     */
    public DataTransferrer( String roomDescription,List<String> characters, List<String> roomExits, List<String> roomItems, List<String> playerItems)
    {
        this.characters = characters;
        this.roomExits = roomExits;
        this.roomItems = roomItems;
        this.playerItems = playerItems;
        currentRoomDescription = roomDescription;
    }


    public String getCurrentRoomDescription()
    {
        return currentRoomDescription;
    }

    /**
     * @return    the current items held by the player
     */
    public List<String> getPlayerItems()
    {
        return playerItems;
    }


    /**
     * @return  the current items in the room
     */
    public List<String> getRoomItems()
    {
        return roomItems;
    }


    /**
     * @return the list of the names of the characters in the current room
     */
    public List<String> getCharacters()
    {
        return characters;
    }


    /**
     * @return   the list of exits from the current room
     */
    public List<String> getRoomExits()
    {
        return roomExits;
    }


}
