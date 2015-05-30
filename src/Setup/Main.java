package Setup;

import Controller.GameController;
import View.GUI.MainScreen;
import View.GUI.RequestProcessor;
import View.GuiView;

import javax.swing.*;

/**
 *
 * @author rej
 */
public class Main {

    public static boolean  allReady = false;

    public static void sendReadySignal()
    {
        allReady = true;
    }

    public  static  Thread th;


    /**
     * the method that is called to start the application
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        GuiView.setPlayerName(JOptionPane.showInputDialog(null,"enter your name"));
         GuiView.initView();

          GameController.initView();
        RequestProcessor proc = RequestProcessor.getRequestProcessor();
        Runnable thing = () -> proc.mainLoop();
         th  = new Thread(thing);
        th.setPriority(Thread.MAX_PRIORITY);
    }

    public  static  void prepare()
    {
        th.setPriority(Thread.MAX_PRIORITY);
    }


}
