package View;

import View.GUI.DataTransferrer;
import View.GUI.Viewable;

import java.util.*;
import Setup.Main;
/**
 * interface that specifies what all views need to do
 * @author Jack Davey
 * @version 7th November 2014
 */
public abstract  class View
{

    public  View()
    {
        possibleSceneActors = new HashMap<>();
    }

    private static Locale loc =  new Locale("en","UK");
    private static ResourceBundle mesages = ResourceBundle.getBundle("View.zuul", loc);

    public abstract <data> void  printData(data thing);
    private  static View view;

    protected static void setView(View view)
    {
        View.view = view;
        Main.sendReadySignal();
    }

    public   static View getView()
    {
        return view;
    }

    public static String translateString(String wordToTranslate)
    {
        return mesages.getString(wordToTranslate);
    }

    private  Map<String, Viewable> possibleSceneActors;

    public  void AddSceneRepresentation(String newKey, Viewable newValue)
    {
        possibleSceneActors.put(newKey,newValue);
    }

    public  List<Viewable> getSceneToDraw(List<String> gameTexts)
    {
         List<Viewable> scene = new ArrayList<>();
         for(String currentItem : gameTexts)
         {
             Viewable sceneElement = possibleSceneActors.get(currentItem);
             if (sceneElement == null)
             {
                 throw new IllegalArgumentException(" no such object exits");
             }
             else
             {
                 scene.add(sceneElement);
             }
         }
        return scene;
    }

    public Command produceCommandFromInput(String inputLine)
    {
        Scanner tokenizer = new Scanner(inputLine);
        List<String> words = new ArrayList<>();
        while (tokenizer.hasNext())
        {
            String word = tokenizer.next();
            words.add(word);
        }

        return new Command(words);
    }

    /**
     * @return The next command from the user.
     */
    public  abstract Command getCommand();

   public abstract void recieveNewModelInformation(DataTransferrer trans);


    /**
     * Print out the opening message for the player.
     */

    public  void printWelcome()
    {
        printData("");
        printData(TextView.translateString("welcome"));
        printData((TextView.translateString("boring")));
        printData(TextView.translateString("advice"));
        printData("");
    }


}
