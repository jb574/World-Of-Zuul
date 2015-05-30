package Setup;

import Model.*;
import Model.Character;
import View.GUI.DataTransferrer;
import View.GUI.SceneManager;
import View.GUI.VisualRepresentations.*;
import View.GUI.VisualRepresentations.rooms.*;
import View.GuiView;
import View.TextView;

import java.util.LinkedList;

import java.util.List;

/**
 * class that contains a couple
 * of static methods to take the setup
 * out of the GameController class
 * @author Jack Davey
 * @version 1st November 2014
 */
public class Init
{
    public static final String newline = System.getProperty("line.separator");

    /**
     * Create all the rooms and link their exits together.
     * @return  the list of actors in this game
     */
    public static List<Actor> createRooms()
    {
        List<Actor> participants = new LinkedList<>();

        Room outside, theatre, pub, lab, office;

        // create the rooms
        outside = new Room(TextView.translateString("outside"));
        SceneManager.getSceneMananger().addBackgroundOverlay(TextView.translateString("outside"),new OutsideDisplay(0,0,0,0));
        SceneManager.getSceneMananger().addBackgroundOverlay(TextView.translateString("lec"), new TheatreDisplay(0,0,400,0));
        theatre = new Room(TextView.translateString("lec"));
        pub = new Room(TextView.translateString("pub"));
        SceneManager.getSceneMananger().addBackgroundOverlay(TextView.translateString("pub"),new PubDisplay(0,0,400,0));
        SceneManager.getSceneMananger().addBackgroundOverlay(TextView.translateString("lab"), new LabDisplay(0,0,400,0));
        SceneManager.getSceneMananger().addBackgroundOverlay(TextView.translateString("office"),new OfficeDisplay(0,0,400,0));
        lab = new Room(TextView.translateString("lab"));
        office = new Room(TextView.translateString("office"));
        initExits(outside, theatre, pub, lab, office);
        Player user = new Player(outside, GuiView.getPlayerName());
        SceneManager.getSceneMananger().addSceneElement(GuiView.getPlayerName(), new CharacterDisplay(260,0, "user " + GuiView.getPlayerName(),GuiView.getPlayerName()));
        SceneManager.getSceneMananger().addSceneElement("joe",new CharacterDisplay(110,0, "coder joe","joe"));
        Character joeBloggs = new Programmer(outside,"joe");
        participants.add(joeBloggs);
        participants.add(user);
        DataTransferrer trans = user.getGuiInformation();
        TextView.getView().recieveNewModelInformation(trans);
        return participants;
    }

    /**
     * link all the rooms to thier exits
     * @param outside the first room
     * @param theatre the second room
     * @param pub     the third room
     * @param lab     the fourth room
     * @param office  the fith room
     */
    private static void initExits(Room outside, Room theatre, Room pub, Room lab, Room office)
    {
        // initialise room exits
        // north east south west
        outside.addExit(TextView.translateString("east"), theatre);
        outside.addExit(TextView.translateString("south"),lab);
        outside.addExit(TextView.translateString("west"),pub);
        SceneManager.getSceneMananger().addSceneElement(TextView.translateString("notebook"), new NoteBookDisplayer(100, 220));
        SceneManager.getSceneMananger().addSceneElement(TextView.translateString("box"),new BoxDisplay(210,200, ""));
        SceneManager.getSceneMananger().addBackgroundOverlay(TextView.translateString("case"),new CaseDisplay(310,200));
        outside.addItem(TextView.translateString("notebook"), 2);
        outside.addItem(TextView.translateString("case"), 10);
        outside.addItem(TextView.translateString("box"), 11);
        theatre.addExit(TextView.translateString("west"), outside);
        pub.addExit(TextView.translateString("east"), outside);
        lab.addExit(TextView.translateString("north"),outside);
        lab.addExit(TextView.translateString("east"),office);
        office.addExit(TextView.translateString("west"),lab);
        SceneManager man = SceneManager.getSceneMananger();
        man.addSceneElement(TextView.translateString("north"),
                new ExitDisplay(10,450, "go north", "north"));
        man.addSceneElement(TextView.translateString("south"),
                new ExitDisplay(50,450, " go south", "south"));
        man.addSceneElement(TextView.translateString("east"),
                new ExitDisplay(100,450, "go east", "east"));
        man.addSceneElement(TextView.translateString("west"),
                new ExitDisplay(150,450, "go west", "west"));
    }
}
