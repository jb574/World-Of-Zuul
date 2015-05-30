package Model;

/**
 * abstract class that all our AI controlled subclasses
 * will inherit from
 * @author Jack Davey
 * @version 31st October 2014
 */
public abstract class Character extends Actor
{


    /**
     * constructor for this class
     * @param currentRoom the first room of the character
     * @param name the name of this character
     */
    public  Character(Room currentRoom, String name)
    {
        super(currentRoom,name);
    }

    /**
     * method overridden by the artificial intellignence
     * in subclasss, if we want a class to be intelligent
     * then it needs to act
     */
    public abstract void act();
}
