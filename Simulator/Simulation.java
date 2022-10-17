public class Simulation
{
    protected int crateSize;
    protected int numOfTries = 0;
    protected int uniques = 0;
    protected double chance = 1;
    protected SimulationMenu simMenu;

    public int getResult()
    {
        return(numOfTries);
    }

    public void setMenu(SimulationMenu nSimMenu)
    {
        simMenu = nSimMenu;
    }

    public static double getMean(Simulation[] sims)
    {
        double mean = 0;

        for(int i=0;i<sims.length;i++)
        {
            mean += (double) sims[i].getResult() / sims.length;
        }

        return(mean);
    }

    public static double getVariance(Simulation[] sims)
    {
        double mean = Simulation.getMean(sims);
        double variance = 0;

        for(int i=0;i<sims.length;i++)
        {
            variance += ((mean-sims[i].getResult())*(mean-sims[i].getResult())) / sims.length;
        }

        return(variance);
    }

    public static double getVariance(Simulation[] sims,double nMean)
    {
        double variance = 0;

        double mean = nMean;

        for(int i=0;i<sims.length;i++)
        {
            variance += ((mean-sims[i].getResult())*(mean-sims[i].getResult())) / sims.length;
        }

        return(variance);
    }
}