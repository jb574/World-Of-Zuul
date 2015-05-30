package View.GUI;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.util.List;

/**
 * class tha manages all the things
 * that could possibly be on screen
 * @author Jack Davey
 * @version 8th November 2014
 */
public class SceneManager
{
    private Map<String, Displayable>  sceneParticipants;

    private Map<String, Displayable> backgroundOverlays;

    private static SceneManager manager;

    /**
     *
     * @return the scene manager
     */
    public static SceneManager getSceneMananger()
    {
        if(manager == null)
        {
            manager = new SceneManager();
        }
        return manager;
    }

    private SceneManager()
    {
        sceneParticipants = new HashMap<>();
        backgroundOverlays =new HashMap<>();
    }

    public void addSceneElement(String Description, Displayable rep)
    {
        sceneParticipants.put(Description,rep);
    }

    /**
     * adds a backgrond image that we can use
     * @param roomDescription  the description of the room we want his image to be used for
     * @param overlay  the image object iteslf
     */
    public void addBackgroundOverlay(String roomDescription, Displayable overlay)
    {
        backgroundOverlays.put(roomDescription,overlay);
    }

    /**
     * helpe rmethod that helps build up oru list to display to the user
     * @param target  the list that we will eentually draw
     * @param choices the map we are loking in
     * @param desc   the thing we want to find
     */
    private void addSceneElement(List<Displayable> target,
                                 Map<String, Displayable> choices, String desc)
    {
        Displayable obj = choices.get(desc);
        if(obj != null)
        {
            target.add(obj);
        }
    }

    /**
     * method to build the scne
     * @param roomDescription  the decription of the room we are looking for
     * @param descriptons the descriptons of the scene elements
     * @return  the completed scene
     */
     List<Displayable>  buildScene(String roomDescription,List<String> descriptons)
    {
        List<Displayable> result = new ArrayList<>();
        addSceneElement(result,backgroundOverlays,roomDescription);
        for(String currentItem : descriptons)
        {
           addSceneElement(result,sceneParticipants,currentItem);
        }
        return result;
    }








}
