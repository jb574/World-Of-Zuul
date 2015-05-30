package View;

import View.GUI.DataTransferrer;

import java.util.*;

/**
 * view class that handled all of the IO
 * work for the game
 * @author Jack Davey
 * @version 31st October 2014
 */
public class TextView  extends View
{
     private static Locale loc =  new Locale("en","UK");
    private static ResourceBundle mesages = ResourceBundle.getBundle("View.zuul", TextView.loc);;
    private static Scanner reader = new Scanner(System.in);
    private  static TextView view;

    private TextView()
    {

    }

     public static  void initView()
    {
        view = new TextView();
    }

    /**
     * generic method to print data
     * to the text.
     * this is here so that the text could be reformatted at a later
     * date.
     * @param data the data to print
     * @param <data> type of the data.
     */
    @Override
    public  <data> void  printData(data data)
    {
        System.out.println(data);
    }


    /**
     * Print out the opening message for the player.
     */
    @Override
    public  void printWelcome()
    {
        System.out.println();
        System.out.println(TextView.translateString("welcome"));
        System.out.println(TextView.translateString("boring"));
        System.out.println(TextView.translateString("advice"));
        System.out.println();
    }

    @Override
    public void recieveNewModelInformation(DataTransferrer trans)
    {

    }

    /**
     * @return The next command from the user.
     */
    @Override
    public  Command getCommand()
    {
        String inputLine;
        System.out.print("> ");
        inputLine = reader.nextLine();
        return produceCommandFromInput(inputLine);
    }


}
