package Model;

import java.util.Collection;
import java.util.Optional;

/**
 * class to represent an item
 * @author Jack Davey
 * @version 27th October 2014
 */
public class Item
{
    private int weight;
    private String description;

    /**
     * standard constructor for this class
     * @param weight the weight of the item
     * @param description a description of the item
     */
    public Item(int weight, String description)
    {
        this.weight = weight;
        this.description = description;
    }


    /**
     * method to find a matching item in a collection
     * of items
     * @param description the description of the item we hope to find
     * @param items the collection of items to search through
     * @return an optional thats empty if the item wasn't found
     *         but is full if it was
     */
    public static Optional<Item> findMatchingItem(String description, Collection<Item> items)
    {
        Optional<Item> result =
                items.stream().filter(
                        ((Item foundItem) -> foundItem.getDescription().equals(description)
                        )).findAny();
        return result;
    }

    /**
     * @return the weight of this item
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * @return the description of this item
     */
    public String getDescription()
    {
        return description;
    }


    /**
     *@return  returns a text description of this item
     * along with how much it weighs
     */
    @Override
    public String toString()
    {
        return  description + '(' + weight + ')';
    }
}
