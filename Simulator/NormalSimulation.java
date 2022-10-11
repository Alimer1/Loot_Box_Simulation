public class NormalSimulation
{

    private int crateSize;
    private int uniques = 0;
    private double chance = 1;
    private int numOfTries = 0;

    public NormalSimulation(int newCrateSize)
    {
        crateSize = newCrateSize;
    }

    public void start()
    {
        while(uniques < crateSize)
        {
            if(Math.random()<chance)
            {
                uniques++;
            }
            chance = (double) (crateSize-uniques)/crateSize;
            numOfTries++;
        }
    }

    public int getResult()
    {
        return(numOfTries);
    }

    static double getMean(NormalSimulation[] sims)
    {

        double mean = 0;
        for(int i=0;i<sims.length;i++)
        {
            mean += (double) sims[i].getResult() / sims.length;
        }

        return(mean);
    }

    static double getVariance(NormalSimulation[] sims,double nMean)
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
