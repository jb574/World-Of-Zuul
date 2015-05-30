package Controller;

import Model.*;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

import Setup.Init;
import Model.Character;
import View.Command;
import View.GUI.DataTransferrer;
import View.GuiView;
import View.TextView;

import javax.swing.*;

/**
 * class that  controls
 * the overall flow of the zuul
 * game
 * @author Jack Davey
 * @version 1st November 2014
 */
public class GameController
{

    private List<Actor> participants;

    CommandResponder responder;


    /**
     * this retreives the
     * GameController object in memory
     * @return as above
     */
     public static GameController initView()
     {
         if(view == null)
         {
             view = new GameController();
         }
         return view;

     }


    private static GameController view;

    /**
     * constructor for this class
     * it sets up the data needed to play the game
     */
    private GameController()
    {
        participants = Init.createRooms();
        responder = CommandResponder.getCommandResponder();
        printStartingInformation();
        TextView.getView().printWelcome();
        completedMovements = new ArrayList<>();
        play();
    }


    /**
     * method called on each actor
     * when it is thier turn to move
     * @param person the actor in question
     * @return  true if the user has signalled that
     *          they want to quit
     */
    private  boolean move(Actor person)
    {
        if(person instanceof  Character)
        {
            return  moveCharacter((Character) person);
        }
        else
        {
            return movePlayer((Player)person);
        }
    }


    /**
     * this method is called by moveActor
     * to move computer controlled characters
     * @param person  the person to move
     * @return false, as a non player charcter will
     *          never send a quit signal
     */
    private  boolean  moveCharacter(Character person)
    {
        person.act();
        return false;
    }


    /**
     * this method is called by moveActor
     * to move human controlled characters
     * @param person  the person to move
     * @return true if the user wants to quit, false otherwise
     */
    private  boolean  movePlayer(Player person)
    {
        TextView.getView().printData("");
        TextView.getView().printData(person.getCurrentRoomInfo());


        Command com = TextView.getView().getCommand();
        if(com != null)
        {
            return dealWIthCommand(person, com);

        }
        return false;
    }



    public boolean dealWIthCommand( Command com)
    {
        String result = responder.processCommand(com,currentActor);

        if(result.equals(TextView.translateString("ending")))
        {
            System.exit(0);
        }
        updateDisplay(currentActor,result);

        currentActor = null;

        play();


        return result.equals(TextView.translateString("ending"));
    }

    public boolean dealWIthCommand(Player person, Command com)
    {
        String result = responder.processCommand(com,person);
        if(result.equals(TextView.translateString("ending")))
        {
            System.exit(0);
        }
        currentActor = null;
        play();
        updateDisplay(person, result);
        GuiView.setBoxNeeded(false);
        return result.equals(TextView.translateString("ending"));
    }

    private void updateDisplay(Player person, String result)
    {
        TextView.getView().printData(result);
        DataTransferrer trans = person.getGuiInformation();
        TextView.getView().recieveNewModelInformation(trans);
    }

    /**
     * main loop for playing the game
     */
    private void play()
    {
        GuiView.setBoxNeeded(false);
         while (currentActor == null)
         {
             if(participants.isEmpty())
             {
                 participants.addAll(completedMovements);
                 completedMovements.clear();;
             }
             Actor currentActorToProcess = participants.get(0);
             if(currentActorToProcess instanceof  Player)
             {
                 currentActor =  (Player) currentActorToProcess;
                 TextView.getView().printData("");
                 TextView.getView().printData(currentActor.getCurrentRoomInfo());
             }
             else
             {
                 move( currentActorToProcess);
             }
             participants.remove(currentActorToProcess);
             completedMovements.add(currentActorToProcess);
         }
    }


    private Player currentActor;
    private ArrayList<Actor> completedMovements;




    private void printStartingInformation()
    {
        TextView.getView().printData("printing starting location");
        participants.stream().filter(
                (person) -> person instanceof  Player).forEach(
                (person) -> TextView.getView().printData(person.toString()));
    }


}
