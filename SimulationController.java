import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.io.FileWriter;
import java.io.IOException;

public class SimulationController
{
    private Simulation[] aNormal;       //Array Normal Simulation
    private GuiltySimulation[] aGuilty; //Array Guilty Simulation

    private double mNormal;             //Normal Mean
    private double mGuilty;             //Guilty Mean

    private double vNormal;             //Normal Variance
    private double vGuilty;             //Guilty Variance

    private double sdNormal;            //Normal Standard Deviation
    private double sdGuilty;            //Guilty Standard Deviation



    public SimulationController(int size,int NumOfSimulations)
    {

        aNormal = new Simulation[NumOfSimulations];
        aGuilty = new GuiltySimulation[NumOfSimulations];

        System.out.println("Calculating Values");

        for(int i=0;i<NumOfSimulations;i++)
        {
            aNormal[i] = new Simulation(size);
            aGuilty[i] = new GuiltySimulation(size);
        }

        System.out.println("Calculated Values");

        mNormal = Simulation.getMean(aNormal);

        vNormal = Simulation.getVariance(aNormal,mNormal);

        sdNormal = Math.sqrt(vNormal);

        mGuilty = GuiltySimulation.getMean(aGuilty);

        vGuilty = GuiltySimulation.getVariance(aGuilty,mGuilty);

        sdGuilty = Math.sqrt(vGuilty);


        System.out.print
        (

        "Normal Mean : "+mNormal+"\n"+
        "Guilty Mean : "+mGuilty+"\n"+
        "Normal Variance : "+vNormal+"\n"+
        "Guilty Variance : "+vGuilty+"\n"+
        "Normal Standard Deviation : "+sdNormal+"\n"+
        "Guilty Standard Deviation : "+sdGuilty+"\n"
        
        );

        System.out.println("Printing Normal Results To A.txt");

        try
        {
            FileWriter output = new FileWriter("A.txt");
            for(int i=0;i<NumOfSimulations;i++)
            {
                output.write(aNormal[i].getResult()+"\n");
            }
            output.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Printing Complete");

        System.out.println("Printing Guilty Results To B.txt");

        try
        {
            FileWriter output = new FileWriter("B.txt");
            for(int i=0;i<NumOfSimulations;i++)
            {
                output.write(aGuilty[i].getResult()+"\n");
            }
            output.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Printing Complete");

    }
}
