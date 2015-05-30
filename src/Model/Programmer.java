package Model;

/**
 * class to represent a dmo ai controlled character
 * @author Jack Davey
 * @version 31st October 2014
 */
public class Programmer extends Character
{

    /**
     * constructor for this class
     * @param currentRoom  the starting room of the programmer
     * @param name         the name of the programmer
     */
    public  Programmer(Room currentRoom, String name)
    {
        super(currentRoom,name);
    }

    /**
     * we overide this method
     * to  implement the behaviour of this person
     */
    public void act()
    {
    }


}
