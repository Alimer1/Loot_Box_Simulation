import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Framework of the system ...?
* 
**/
public class SimulationController implements ActionListener
{
    private JFrame mainFrame = new JFrame("Loot Box Simulator");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel simulationPanel = new JPanel(new GridLayout(1,2,10,10));
    private JPanel controlPanel = new JPanel(new BorderLayout());

    private StandardSimulation[] sims1; //Standard Simulation Panel
    private GuiltySimulation[] sims2; //Guilty Gear Simulation Panel

    private Thread[] sim1Threads;
    private Thread[] sim2Threads;
    private Thread[] progressChecker;

    private JProgressBar simProgressBar1 = new JProgressBar(0,100);
    private JProgressBar simProgressBar2 = new JProgressBar(0,100);

    private SimulationMenu simMenu1 = new SimulationMenu("Standard Simulation",simProgressBar1);
    private SimulationMenu simMenu2 = new SimulationMenu("Guilty Gear Simulation",simProgressBar2);

    private JPanel settingsPanel = new JPanel(new GridLayout(3,1));
    private JPanel settingsStartTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel settingsInput1Panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel settingsInput2Panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    private JButton startButton = new JButton("Start");
    private JLabel input1Label = new JLabel("Simulation Count:");
    private JLabel input2Label = new JLabel("Crate Size:");
    private JTextField input1 = new JTextField(10);
    private JTextField input2 = new JTextField(10);

    private int simCount;
    private int crateSize;

    private String errorString;

    public SimulationController()
    {
        displaySetup();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == startButton)
        {
            if(inputCheck())
            {
                startButton.setEnabled(false);
                simCount = Integer.parseInt(input1.getText());
                crateSize = Integer.parseInt(input2.getText());
                startSimulation();
                displayResults();
                startButton.setEnabled(true);
            }
            else
            {
                System.out.println(errorString);
            }
        }
    }

    private boolean inputCheck()
    {
        errorString = "empty";
        return(true);   //Come back and actually make sure the input are valid values
    }

    private void startSimulation()
    {
        sims1 = new StandardSimulation[simCount];
        sims2 = new GuiltySimulation[simCount];
        sim1Threads = new Thread[simCount];
        sim2Threads = new Thread[simCount];
        progressChecker = new Thread[2];
        progressChecker[0] = new Thread(simMenu1);
        progressChecker[1] = new Thread(simMenu2);

        for(int i=0;i<simCount;i++)
        {
            sims1[i] = new StandardSimulation(crateSize,simMenu1);
            sims2[i] = new GuiltySimulation(crateSize,simMenu2);
            sim1Threads[i] = new Thread(sims1[i]);
            sim2Threads[i] = new Thread(sims2[i]);
        }

        simMenu1.clearValues();
        simMenu2.clearValues();
        simMenu1.setSimCount(simCount);
        simMenu2.setSimCount(simCount);

        progressChecker[0].start();
        progressChecker[1].start();
        for(int i=0;i<simCount;i++)
        {
            sim1Threads[i].start();
            sim2Threads[i].start();
        }

        for(int i=0;i<simCount;i++)
        {
            try
            {
                sim1Threads[i].join();  // I SHOULDN'T JOIN WHILE IN THE MAIN METHOD THAT CONTROLS THE UI SINCE THIS CAUSES THE WHOLE PROGRAM TO FREEZE WHILE WORKING I MUST SEPERATE THE CONTROLL PART OF THE PROGRAM AND THE UI PART OF IT.
                sim2Threads[i].join();
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }

        simMenu1.stop();
        simMenu2.stop();

        simMenu1.completeProgress();
        simMenu2.completeProgress();
    }

    private void displayResults()
    {
        simMenu1.setValues((int)Simulation.getMean(sims1),(int)Simulation.getVariance(sims1),(int)Math.sqrt(Simulation.getVariance(sims1)));
        simMenu2.setValues((int)Simulation.getMean(sims2),(int)Simulation.getVariance(sims2),(int)Math.sqrt(Simulation.getVariance(sims2)));
    }
    
    private void displaySetup()
    {

        mainPanel.add(controlPanel,BorderLayout.WEST);
        mainPanel.add(simulationPanel,BorderLayout.CENTER);

        controlPanel.add(settingsPanel,BorderLayout.NORTH);

        settingsPanel.add(settingsStartTopPanel);
        settingsPanel.add(settingsInput1Panel);
        settingsPanel.add(settingsInput2Panel);

        settingsStartTopPanel.add(startButton);
        startButton.addActionListener(this);

        settingsInput1Panel.add(input1Label);
        settingsInput1Panel.add(input1);
        settingsInput2Panel.add(input2Label);
        settingsInput2Panel.add(input2);

        simulationPanel.add(simMenu1.getSimPanel());
        simulationPanel.add(simMenu2.getSimPanel());

        mainFrame.setContentPane(mainPanel);
        mainFrame.setSize(640,480);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
