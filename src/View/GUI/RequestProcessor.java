package View.GUI;
 import Controller.GameController;
 import View.Command;

 import javax.swing.*;
 import java.util.LinkedList;

/**
 * @author: Jack Davey
 * @version: 03/12/14
 * a simple class that ensures no deadlocks can take place
 * it runs on its own thread so that the event thread is not clogged up
 */
public class RequestProcessor
{
    private LinkedList<Command> commands;

    private static RequestProcessor proc;

    /**
     *
     * @return the object in question
     */
     public static RequestProcessor getRequestProcessor()
     {
         if(proc == null)
         {
             proc = new RequestProcessor();
         }
         return  proc;
     }

    /**
     * constructor
     */
    private RequestProcessor()
    {
        commands =new LinkedList<>();
    }

    /**
     * add acommand to be processed
     * @param com the command in question
     */
    public  void AddCommand(Command com)
    {
        commands.add(com);
    }

    /**
     * the main loop that  processes commands
     */
    public  void mainLoop()
    {
        while (true)
        {
            Command com = commands.poll();
            if (com!= null)
            {
                try
                {

                     GameController.initView().dealWIthCommand(com);

                }
                catch (Exception error)
                {
                     System.out.println(error.getCause());
                    error.printStackTrace();
                }



            }
        }
    }

}
