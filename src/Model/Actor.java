package Model;

import Setup.Init;
import View.GuiView;
import View.TextView;

import java.util.Optional;

/**
 * class to represent an actor
 * in the world of zuul
 * @author Jack Davey
 * @version 28th October
 */
public abstract class Actor
{
    private Room currentRoom;
    private Bag heldItems;
    private String name;



    protected Bag getBag()
    {
        return heldItems;
    }

    protected Room getCurrentRoom()
    {
        return currentRoom;
    }

    public Actor(Room currentRoom, String name)
    {
        heldItems = new Bag();
        this.currentRoom = currentRoom;
        this.name =name;

        currentRoom.enterRoom(this);
    }

    public String getName()
    {
        return name;
    }

    /**
     * method to make this player move to a new room
     * @param direction  the direction in which to move
     * @return  a message indicating sucess or failure
     */
    public String moveToRoom(String direction)
    {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null)
        {
           return TextView.translateString("noDoor");
        }
        else
        {
            currentRoom.leaveRoom(this);
            currentRoom = nextRoom;
            currentRoom.enterRoom(this);
            return getName() + " " + TextView.translateString("nowIn") + " " + currentRoom.getDescription();
        }
    }


    /**
     * @return a string representation of this actor
     */
    @Override
    public String toString()
    {
        return  name + ": " +
                TextView.translateString("Items") + Init.newline +
         " "  +       heldItems.toString() + Init.newline  +
                TextView.translateString("currentRoomInfo") +
        Init.newline  + currentRoom.toString() ;
    }

    /**
     * method to return as a string information about the current room.
     * @return  as above
     */
     public String getCurrentRoomInfo()
     {
         return TextView.translateString("currentRoomInfo") +
                 Init.newline   + currentRoom.toString();
     }


    /**
     * method to take an item
     * from the current room
     * @param item the string description
     *             of the item we think is in the room
     * @return  a string indicating sucsess or failure
     */
    public String take(String item)
    {
        int w = currentRoom.containsItem(item);
        if (w == 0)
        {
            // The item is not in the room
            return TextView.translateString("No") + " " + item + " " +
                    TextView.translateString("noRoom");
        }
        return recieveItem(item, w);
    }


    /**
     * method to recieve an item and add it
     * to the bag
     * @param item the description of the item to recieve
     * @param w    the weight of thhe item
     * @return     a string  to say whether the operation
     *             was a sucess or not
     */
    private String recieveItem(String item, int w)
    {
        if(heldItems.take(new Item(w,item)))
        {
            currentRoom.removeItem(item);
              return  name + " " +
                      TextView.translateString("youTookThe") + " " + item;
        }
        else
        {
            GuiView.setBoxNeeded(true);
            return item + " " +  TextView.translateString("tooHeavy");
        }
    }


    /**
     * make this actor drop an item
     * @param item  the description of the item to drop
     * @return  a message indicating sucsess or failure
     */
    public  String drop(String item)
    {
        Item oldItem = heldItems.drop(item);
        if(oldItem != null)
        {
            currentRoom.addItem(oldItem.getDescription(),oldItem.getWeight());
            return TextView.translateString("dropped") + " " + item + " "
                    + " " + currentRoom.getDescription();
        }
        else
        {
            return TextView.translateString("noItem");
        }

    }

    /**
     * allows the finding out of information
     * on another character in the same room as the player
     * @param characterName the name of the person to look for
     * @return  a string about the character in question
     *          or an error if that person is not in the room
     */
  public String introduce(String characterName)
  {
      Optional<Actor> result = currentRoom.findCharacter(characterName);
      if(result.isPresent())
      {
         return result.get().toString();
      }
      else
      {
          return characterName + " " + TextView.translateString("notRoom");
      }
  }


    /**
     * make this actor give an item to another player
     * @param item  the description of the item to give
     * @param whom  the person to give to
     * @return  an error indicating sucsess or failiure
     */
    public String give(String item, String whom)
    {
        Optional<Actor> possibllity = currentRoom.findCharacter(whom);
        if(possibllity.isPresent())
        {
            Item oldItem = heldItems.drop(item);
            if(oldItem != null)
            {
               return possibllity.get().recieveItem(oldItem.getDescription(),oldItem.getWeight());
            }
            else
            {
                GuiView.setBoxNeeded(true);
                return TextView.translateString("noItem");
            }
        }
        else
        {
            return whom +  " " + TextView.translateString("notRoom");
        }
    }
}
