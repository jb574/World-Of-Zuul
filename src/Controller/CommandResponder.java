package Controller;

import Model.Player;
import View.Command;
import View.GuiView;
import View.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * class that is responsible for managing the overall
 * flow of game play
 * @author Jack Davey
 * @version  28th October 2014
 */
public class CommandResponder
{


     private static CommandResponder responder;


    /**
     * retrieve the single CommandResponder that can
     * exist in this application
     * @return as stated above
     */
    public  static CommandResponder getCommandResponder()
    {
        if(responder == null)
        {
            responder = new CommandResponder();

        }
        return responder;
    }




     private Map<String,Function<CommandBundle,String>> responses;

    /**
     * constructor of this class
     * it sets up all the commands we can respond to
     */
    private CommandResponder()
    {
        responses = new HashMap<>();
        responses.put(TextView.translateString("go"),CommandResponder::goRoom);
        responses.put(TextView.translateString("look"), (response) -> response.getUser().toString());
        responses.put(TextView.translateString("take"), CommandResponder::take);
        responses.put(TextView.translateString("drop"), CommandResponder::drop);
        responses.put(TextView.translateString("give"),CommandResponder::give);
        responses.put(TextView.translateString("help"),CommandResponder::printHelp);
        responses.put(TextView.translateString("quit"),CommandResponder::quit);
        responses.put(TextView.translateString("introduce"),CommandResponder::introduce);
    }

    /**
     * priavte class that collects to gether a command
     * and a player object so that it can be passed to one of our
     * processing methods
     * this is needed because there is no function interface
     * that accepts tow parameters.
     * @author Jack Davey
     * @version 28th October 2014
     */
    private class CommandBundle
    {
        private Command command;
        private Player user;


        private CommandBundle(Player user, Command command)
        {
            this.user = user;
            this.command = command;
        }

        public Command getCommand()
        {
            return command;
        }

        public Player getUser()
        {
            return user;
        }
    }



    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @param user the person who sent the command
     * @return true If the command ends the game, false otherwise.
     */
    public  String  processCommand(Command command,Player user)
    {
        String commandWord = command.getCommandWord(0);
       Function<CommandBundle,String> result = responses.get(commandWord);
       if(result != null)
       {
           return result.apply(new CommandBundle(user,command));
       }
       else
       {
           return TextView.translateString("noIdea");
       }
    }


    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @param bundle the bundle passed in to help this method carry out
     *               its work, in this case, it is not used for anything
     * @return  a message saying  if the attempt to quit worked or not
     */
    private static  String  quit(CommandBundle bundle)
    {
        Command command = bundle.getCommand();
        String quitString = command.getCommandWord(1);
        if (quitString != null)
        {
            return  TextView.translateString("quitWhat");
        }
        else
        {
            return TextView.translateString("ending");
        }
    }

    /**
     * produce some help information. Here we return some stupid, cryptic
     * message and a list of the command words.
     *   @param bundle the bundle passed in to help this method carry out
     *               its work, in this case, it is not used for anything
     *  @return the help message produced
     */
    private static  String printHelp(CommandBundle dataToWorkWith) {
        String newLine = System.getProperty("line.separator");
        String result = TextView.translateString("lostHelp");
        result = result + newLine;
        result = result + TextView.translateString("uniHelp");
        result = result + newLine;
        result = result + newLine;
        result = result + TextView.translateString("wordList");
        result = result + newLine;
        for(String key : responder.responses.keySet())
        {
            result = result + key + " ";
        }
        result = result + newLine;
        return  result;
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new room and return a
     * message stating this ,
     * otherwise return an error string
     *    @param bundle the bundle passed in to help this method carry out
     *               its work,
     *    @return as above
     */
    private static  String goRoom(CommandBundle dataToWorkWith) {
        Player user = dataToWorkWith.getUser();
        Command command = dataToWorkWith.getCommand();
        String direction = command.getCommandWord(1);
        if (direction == null)
        {
            return TextView.translateString("goWhere");
        }
        else
        {
            return user.moveToRoom(direction);
        }

    }


    /**
     * Try to take an item from the current room
     *  @param bundle the bundle passed in to help this method carry out
     *               its work,
     * @return a sucsess message if sucessful, an error message if not
     */
    private static  String take(CommandBundle dataToWorkWith)
    {
        Player user = dataToWorkWith.getUser();
        Command command = dataToWorkWith.getCommand();
        String item = command.getCommandWord(1);
        if(item != null)
        {
            return user.take(item);
        }
        else
        {
            // if there is no second word, we don't know what to take...
            return TextView.translateString("takeWhat");
        }
    }

    /**
     * Try to drop an item,
     *      @param bundle the bundle passed in to help this method carry out
     *               its work,
     * @return a sucsess message if sucessful, an error message if not
     */
    private static  String drop(CommandBundle dataToWorkWith)
    {

        Player user = dataToWorkWith.getUser();
        Command command = dataToWorkWith.getCommand();
        String item = command.getCommandWord(1);
        if(item != null)
        {
            return user.drop(item);
        }
        else
        {
            // if there is no second word, we don't know what to drop...
            return TextView.translateString("dropWhat");
        }
    }

    /**
     * allows the finding out of information
     * on another character in the same room as the player
     * @param dataToWorkWith the command bundle containing
     *                       the player and the data entered by
     *                       the player
     * @return  a string about the character in question
     *          or an error if that person is not in the room
     */
    private static  String introduce(CommandBundle dataToWorkWith)
    {

        Player user = dataToWorkWith.getUser();
        Command command = dataToWorkWith.getCommand();
        String player = command.getCommandWord(1);
        if(player != null)
        {
            GuiView.setBoxNeeded(true);
            return user.introduce(player);

        }
        else
        {
            // if there is no second word, we don't know what to drop...
            return TextView.translateString("intToWho");
        }
    }


    /**
     * Try to give an item to another character
      * @param bundle the bundle passed in to help this method carry out
      *               its work,
      * @return a sucsess message if sucessful, an error message if not
     */
    private static  String give(CommandBundle dataToWorkWith)
    {
        Player user = dataToWorkWith.getUser();
        Command command = dataToWorkWith.getCommand();
        String item = command.getCommandWord(1);
        String whom = command.getCommandWord(2);
        if (item == null)
        {
            // if there is no second word, we don't know what to give...
            return TextView.translateString("giveWhat");
        }
        if (whom == null)
        {
            // if there is no third word, we don't to whom to give it...
            return TextView.translateString("giveWho");

        }
        return  user.give(item,whom);
    }




}
