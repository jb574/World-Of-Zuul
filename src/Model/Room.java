package Model;

import Setup.Init;
import View.TextView;

import java.util.*;

/**
 * Class Room - a room in an adventure game.
 *
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author Jack Davey
 * @version 28th October 2014
 */
public class Room 
{
    public String description;
    
    // Exits from the room
    private Map<String,Room> exits;

    private Set<Item> items;

    private List<Actor> inhabitants;




    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashSet<>();
        inhabitants = new ArrayList<>();
    }

    /**
     * @return a string containing all the exit information of this
     *         room
     */
 public String getExitInformation()
 {
     String result = TextView.translateString("exits") + " ";
     return exits.keySet().stream().reduce( result ,
             (firstString, secondString) -> firstString + " " + secondString );
 }

    public List<String> getExitGUIinformation()
    {
         return new ArrayList<>(exits.keySet());
    }

    public List<String> getCharacterNames()
    {
        List<String> result = new ArrayList<>();
        for(Actor inhab : inhabitants)
        {
            result.add(inhab.getName());
        }
        return result;
    }

    public List<String> getItemNames()
    {
        List<String> result = new ArrayList<>();
        for(Item currentItem : items)
        {
            result.add(currentItem.getDescription());
        }
        return result;
    }



    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * @return a string containing information  on all the items
     *         stored in this room
     */
    public String retrieveItemInformation()
    {
        String result = TextView.translateString("Items") + " " ;
        for(Item currentItem : items)
        {
           result = result + currentItem.toString();
        }
        return result;
    }

    /**
     * @return a string containing information on all the characters in
     *         this room
     */
    public  String retrieveCharacterInformation()
    {
        String result = TextView.translateString("characters") + " ";
        for(Actor person : inhabitants)
        {
            result = result + person.getName() + " ";
        }
        return result;
    }


    /**
     * @return a string representation of this room
     */
    @Override
    public String toString()
    {

        String result  = TextView.translateString("youAre") + " " + getDescription();
        result = result + Init.newline + getExitInformation();
        result = result + Init.newline + retrieveCharacterInformation();
        result = result + Init.newline + retrieveItemInformation();
        return result;
    }



    /**
     * Add an item to the Room
     * @param description The description of the item
     * @param weight The item's weight
     */
    public void addItem(String description, int weight)
    {
        Item newItem = new Item(weight,description);
        items.add(newItem);
    }

    /**
     * This method lets
     * an actor enter a room
     * @param inhabitant the new person in the room
     */
    public void enterRoom(Actor inhabitant)
    {
        inhabitants.add(inhabitant);
    }

    /**
     * called by an actor to signal that they are leaving
     * a room
     * @param personLeaving the person who is leaving the room
     */
    public void leaveRoom(Actor personLeaving)
    {
        if(inhabitants.contains(personLeaving))
        {
            inhabitants.remove(personLeaving);
        }
    }

    /**
     * method to find a character in this room
     * @param characterDescription  the description of the character
     * @return an optional containing the character if they are there or an
     *         empty one if not
     */
    public Optional<Actor> findCharacter(String characterDescription)
    {
         return inhabitants.stream().filter(
                (Actor person ) -> person.getName().equals(characterDescription)
        ).findAny();
    }



    /**
     * method to check if the room contains
     * an item that matches a text description
     * @param description the text description in question
     * @return the item's weight or 0 if none
     */
    public int containsItem(String description)
    {
        Optional<Item> result = Item.findMatchingItem(description, items);
        if(result.isPresent())
        {
            return result.get().getWeight();
        }
        else
        {
            return 0;
        }
    }

    /**
     * returns  the exit assosicated with the string parameter
     * @param direction the key of the exit to  look for
     * @return as above
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * method to add an exit to this room
     * @param direction  the direction from which the new room can
     *                   be reached
     * @param neighbour  the room iteslf
     */
   public void addExit(String direction,Room neighbour)
   {
       exits.put(direction,neighbour);
   }



    /**
     * Remove an item from the Room
     * @param description a description of the item to remove
     *  @return  the item description if found or null otherwise
     */
    public String removeItem(String description) {
        Optional<Item> result =  Item.findMatchingItem(description, items);
        if(result.isPresent())
        {
            Item ultimateResult = result.get();
            items.remove(ultimateResult);
            return ultimateResult.getDescription();
        }
        else
        {
            return  null;
        }
    }

}
