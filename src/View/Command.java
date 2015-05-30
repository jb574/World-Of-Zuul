package View;

import java.util.List;
import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * it holds information of a command
 *  a command  is made up of a list of words
 * @author  Michael Kolling and David J. Barnes
 * @author Jack Davey
 * @version  27th October 2014
 */

public class Command
{


    private List<String> words;




    public  Command(List<String> words)
    {
        this.words = words;
    }


    /**
     * retreives one of the words that
     * make up this command
     * @param wordToGet  the index of the word to get
     * @return  the command word at that index
     */
    public String getCommandWord(int wordToGet)
    {
        if(wordToGet >= 0 && wordToGet < words.size())
        {
            return words.get(wordToGet);
        }
        else
        {
            return null;
        }
    }
}

