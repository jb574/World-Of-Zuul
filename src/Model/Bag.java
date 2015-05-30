package Model;

import View.TextView;

import java.util.*;

/**
 * class that stores a bag of items held by a character
 * @author Jack Davey
 * @version 26th October 2014
 */
public class Bag
{
    private Set<Item> items;
    private int maxCapacity;
    private  int currentCapacity;

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
     * @return a string representation of this bag
     */
    @Override
    public String toString()
    {
        String result =  "";
        for(Item currentItem : items)
        {
            result = result + currentItem.getDescription() + "(" +
                    currentItem.getWeight() +") ";
        }
        result = result  + TextView.translateString("currentBagWeight") +" "+ currentCapacity;
        result = result  + " " + TextView.translateString("maxBagWeight")+ " " + maxCapacity + " ";
        return result;
    }


    /**
     * standard constructor for this class
     */
    public Bag()
    {
        maxCapacity = 10;
        items = new HashSet<>();
    }

    /**
     * This is a second constructor that
     * allows the user to set the maximum
     * capacity for this bag
     * @param maxCapacity  the maximum capacty that
     *                     this bag can hold
     */
    public Bag(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
        items = new HashSet<>();
    }





    /**
     * check if this bag is too full
     * @param newWeight  the new weight
     * @return  a yes or no answer
     */
    private boolean isFull(int newWeight)
    {
        return ((currentCapacity + newWeight) >= maxCapacity);
    }


    /**
     * add an item to this bag
     * @param newItem the item to add
     * @return a boolean indicating whether the
     *         operation was a sucsess or not
     */
    public boolean take(Item newItem)
    {
        if(isFull(newItem.getWeight()))
        {
            return false;
        }
       items.add(newItem);
       currentCapacity = currentCapacity + newItem.getWeight();
       return true;
    }


    /**
     * remove an item from the bag
     * @param description the description of the item to remove
     * @return the removed item  or null if it did not exist
     */
    public Item drop(String description)
    {
        Optional<Item> result = Item.findMatchingItem(description,items);
        if(result.isPresent())
        {
            Item itemToGo = result.get();
            items.remove(itemToGo);
            currentCapacity = currentCapacity - itemToGo.getWeight();
            return itemToGo;
        }
        else
        {
           return null;
        }
    }











}
