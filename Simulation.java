public class Simulation
{

    private int size;
    private int uniques = 0;
    private double chance = 1;
    private int numOfTries = 0;

    public Simulation(int newSize)
    {
        size = newSize;

        while(uniques != size)
        {
            if(Math.random()<chance)
            {
                uniques++;
            }
            chance = (double) (size-uniques)/size;
            numOfTries++;
        }

    }

    public int getResult()
    {
        return(numOfTries);
    }

    static double getMean(Simulation[] sims)
    {
        System.out.println("    Calculating Normal Mean");

        double mean = 0;
        for(int i=0;i<sims.length;i++)
        {
            mean += (double) sims[i].getResult() / sims.length;
        }

        System.out.println("    Calculated Normal Mean");
        return(mean);
    }

    static double getVariance(Simulation[] sims,double nMean)
    {
        System.out.println("    Calculating Normal Variance");

        double variance = 0;
        double mean = nMean;
        for(int i=0;i<sims.length;i++)
        {
            variance += ((mean-sims[i].getResult())*(mean-sims[i].getResult())) / sims.length;
            double test = ((mean-sims[i].getResult())*(mean-sims[i].getResult())) / sims.length;
            //System.out.println("O "+test+"+=("+(mean-sims[i].getResult())+")^2/"+sims.length);
        }

        System.out.println("    Calculated Normal Variance");
        return(variance);
    }
}
