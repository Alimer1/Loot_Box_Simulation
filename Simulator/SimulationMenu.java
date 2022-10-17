import javax.swing.*;
import java.awt.*;

public class SimulationMenu implements Runnable
{
    private int completeThreadCount;
    private int progress;

    private int simCount;
    private String name;
    private boolean exit;

    private JPanel simPanel = new JPanel(new BorderLayout());
    private JPanel titlePanel = new JPanel(new BorderLayout());
    private JPanel infoPanel = new JPanel(new BorderLayout());

    private JLabel simName;
    private JProgressBar progressBar = new JProgressBar(0,100);
    private JPanel outputPanel = new JPanel(new GridLayout(3,1));
    private JPanel outputPanelSegment1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel outputPanelSegment2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel outputPanelSegment3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JLabel output1Label = new JLabel("Mean:");
    private JLabel output1ValueLabel = new JLabel("0");

    private JLabel output2Label = new JLabel("Variance:");
    private JLabel output2ValueLabel = new JLabel("0");

    private JLabel output3Label = new JLabel("Standard Deviation:");
    private JLabel output3ValueLabel = new JLabel("0");

    public SimulationMenu(String nName)
    {
        name = nName;
        displaySetup();
    }

    public void setSimCount(int nSimCount)
    {
        simCount = nSimCount;
    }

    public JPanel getSimPanel()
    {
        return(simPanel);
    }

    public void run()
    {
        completeThreadCount = 0;
        progress = 0;
        exit = false;
        while(!exit)
        {
            try
            {
                progress = (int) ((completeThreadCount*100)/simCount);
                progressBar.setValue(progress);
                System.out.println("Progress in "+name+" is: "+progress);
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    public void completeProgress()
    {
        progressBar.setValue(100);
        completeThreadCount = simCount;
        progress = 100;
    }
    

    public void resetProgress()
    {
        progressBar.setValue(0);
        completeThreadCount = 0;
        progress = 0;
    }

    public synchronized void markCompletion()
    {
        ++completeThreadCount;
    }

    public void stop()
    {
        exit = true;
    }

    public void setValues(int mean,int var,int sd)
    {
        output1ValueLabel.setText(Integer.toString(mean));
        output2ValueLabel.setText(Integer.toString(var));
        output3ValueLabel.setText(Integer.toString(sd));
    }

    public void clearValues()
    {
        output1ValueLabel.setText("0");
        output2ValueLabel.setText("0");
        output3ValueLabel.setText("0");
    }

    private void displaySetup()
    {
        simPanel.add(titlePanel,BorderLayout.NORTH);
        simName = new JLabel(name);
        titlePanel.add(simName,BorderLayout.NORTH);
        titlePanel.add(progressBar,BorderLayout.SOUTH);

        simPanel.add(infoPanel,BorderLayout.CENTER);
        infoPanel.add(outputPanel,BorderLayout.NORTH);

        outputPanel.add(outputPanelSegment1);
        outputPanel.add(outputPanelSegment2);
        outputPanel.add(outputPanelSegment3);

        outputPanelSegment1.add(output1Label);
        outputPanelSegment1.add(output1ValueLabel);
        outputPanelSegment2.add(output2Label);
        outputPanelSegment2.add(output2ValueLabel);
        outputPanelSegment3.add(output3Label);
        outputPanelSegment3.add(output3ValueLabel);
    }
}
