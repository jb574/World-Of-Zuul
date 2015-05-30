package View.GUI;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import View.GuiView;


/**
 * class that is the main screen of
 * the gui
 * @author Jack Davey
 * @version 7th November 2014
 */
public class MainScreen  extends JPanel
{

    private  final JTextArea area;

    private List<String> roomCharacters;

    private final JPanel locPanel;
    private final JPanel charaPanel;
    private final JPanel roomItemPanel;
    private final JPanel playerItemPanel;
    private final DrawingPanel graphicsDisplay;

    /**
     * add some text to the text area
     * @param text the text to add
     */
    public void addText(String text)
    {
        area.append(text);
       area.setCaretPosition(area.getText().length());
    }

    private GuiView view;

    /**
     * methdo to call when we send a command from a menu
     * @param command  the command to send
     */
    private void sendMenuCommand(String command)
    {
        GuiView.setBoxNeeded(true);
        view.interpretCommand(command);
    }


    public MainScreen(GuiView view)
    {
        this.view = view;
        setOpaque(true);
        setLookAndFeel();
        // Creates a menubar for a JFrame
        JMenuBar menuBar = new JMenuBar();

        // Add the menubar to the frame

        JFrame window = new JFrame("World Of Zuul");
        window.setJMenuBar(menuBar);
        JMenu help = new JMenu("help");
        menuBar.add(help);
        final JMenuItem lookItem = new JMenuItem("look");
        lookItem.addActionListener( (event) -> sendMenuCommand("look"));
        help.add(lookItem);
        final JMenuItem helpItem = new JMenuItem("help");
        helpItem.addActionListener( (event) -> sendMenuCommand("help"));

        help.add(helpItem);
        setPreferredSize(new Dimension(600, 700));
        setMaximumSize(new Dimension(600,700));
        setMinimumSize(new Dimension(600,700));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = window.getContentPane();
        contentPane.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        contentPane.add(this);
        this.setOpaque(true);
        locPanel = new JPanel();
        //add(locPanel, BorderLayout.EAST);
        locPanel.setLayout(new BoxLayout(locPanel,BoxLayout.Y_AXIS));
        charaPanel = new JPanel();
        charaPanel.setLayout(new BoxLayout(charaPanel,BoxLayout.Y_AXIS));
       // add(charaPanel, BorderLayout.WEST);
        roomItemPanel = new JPanel();
        roomItemPanel.setLayout(new FlowLayout());
        //add(roomItemPanel, BorderLayout.NORTH);
        playerItemPanel =new JPanel();
        playerItemPanel.setLayout(new BoxLayout(playerItemPanel, BoxLayout.Y_AXIS));

        playerItemPanel.add(new JLabel("held items"));
        playerItemPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        add(playerItemPanel, BorderLayout.WEST);

        JPanel ActionPanel = new JPanel();
        area = new JTextArea();

        area.setEditable(false);

         JPanel areaPanel = new JPanel();
       JScrollPane Scroll = new JScrollPane(area);
        Scroll.setPreferredSize(new Dimension(600, 170));
        Scroll.setMaximumSize(new Dimension(600, 170));
        areaPanel.add(Scroll);
        areaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(areaPanel,BorderLayout.SOUTH);
        playerItemPanel.setPreferredSize(new Dimension(100, 70));
        playerItemPanel.setMaximumSize(new Dimension(100,70));
        graphicsDisplay = new DrawingPanel(view);
        ActionPanel.add(graphicsDisplay);
        ActionPanel.setPreferredSize(new Dimension(500, 500));
        ActionPanel.setMaximumSize(new Dimension(500,500));
        add(ActionPanel,BorderLayout.CENTER);
        window.setVisible(true);
        window.pack();
    }


    public void preformAction(String input)
    {
        SwingUtilities.invokeLater(() -> view.interpretCommand(input));
    }

    public void UpdateDisplay(String Description, List<String> directions, List<String> charas,
                              List<String> playerItems, List<String> roomItems)
    {


        roomCharacters = charas;

        playerItemPanel.removeAll();
        for(String item : playerItems)
        {
            final JButton itemButton = new JButton(item);
            final JPopupMenu itemMenu = new JPopupMenu();
            final JMenuItem dropItem = new JMenuItem("drop");
            dropItem.addActionListener(
                    (event) -> view.interpretCommand("drop "+ item)
            );
            itemMenu.add(dropItem);
            for(String name : charas)
            {
                if(!name.equals(GuiView.getPlayerName()))
                {
                    final JMenuItem givemen = new JMenuItem("give" + name);
                    givemen.addActionListener(
                            (event) -> view.interpretCommand("give " + item + " " + name)
                    );
                    itemMenu.add(givemen);
                }

            }

            itemButton.addActionListener(
                    (evnet) -> itemMenu.show(itemButton,itemButton.getX(),itemButton.getY())

            );
            playerItemPanel.add(itemButton);
        }

       List<String> sceneDescriptors = BuildSceneList(directions, charas, playerItems, roomItems);
        SceneManager manager = SceneManager.getSceneMananger();
        List<Displayable> scene = manager.buildScene(Description, sceneDescriptors);
        ;
        graphicsDisplay.clear();
        graphicsDisplay.setReps(scene);
        this.invalidate();
        this.repaint();
        this.revalidate();



    }

    private List<String> BuildSceneList(List<String> directions, List<String> charas, List<String> playerItems, List<String> roomItems)
    {
        List<String> sceneDescriptors = new ArrayList<>();
        sceneDescriptors.addAll(roomItems);
        sceneDescriptors.addAll(directions);

        sceneDescriptors.addAll(charas);

        return sceneDescriptors;
    }


    public  void  addSceneList(List<String> words)
    {
        for(String word : words)
        {

        }
    }


    private void updatePanel(JPanel panel, List<String> words, String commandWord)
    {
        panel.removeAll();
        for(String word : words)
        {
            final JButton button = new JButton(word);
            button.addActionListener(
                    (event) -> view.interpretCommand(commandWord + " " + word)
            );
            panel.add(button);
        }
    }




    /**
     * method to set the look and feel
     * of the application
     */
    private void setLookAndFeel()
    {
        try
        {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception error)
        {

        }
    }

}
