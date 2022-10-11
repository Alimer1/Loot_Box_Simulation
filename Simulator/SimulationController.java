import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

/**
* Framework of the system ...?
* 
**/
public class SimulationController
{
    private JFrame mainFrame = new JFrame("Loot Box Simulator");
    private JPanel mainPanel = new JPanel();
    private JPanel settingsPanel = new JPanel();
    private JPanel simulationPanel = new JPanel();
    private final Color bg = new Color(100,100,100);




    public SimulationController()
    {
        displaySetup();
    }
    
    private void displaySetup()
    {
        mainPanel.setLayout(new BorderLayout());
        settingsPanel.setLayout(new GridBagLayout());
        simulationPanel.setLayout(new GridLayout(1,2));

        mainPanel.setBackground(bg);
        settingsPanel.setBackground(bg);
        simulationPanel.setBackground(bg);

        mainFrame.setContentPane(mainPanel);
        mainFrame.setSize(640,480);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
