public class GuiltySimulation
{

    private int size;
    private int uniques = 0;
    private double chance = 1;
    private int numOfTries = 0;
    private int fish = 0;

    public GuiltySimulation(int newSize)
    {
        size = newSize;

        while(uniques != size)
        {
            if(fish>=10)
            {
                uniques++;
                fish = fish - 10;
            }
            else
            {
                if(Math.random()<chance)
                {
                    uniques++;
                }
                else
                {
                    fish++;
                }
            }
            chance = (double) (size-uniques)/size;
            numOfTries++;
        }

    }

    public int getResult()
    {
        return(numOfTries);
    }

    static double getMean(GuiltySimulation[] sims)
    {
        System.out.println("    Calculating Guilty Mean");

        double mean = 0;
        for(int i=0;i<sims.length;i++)
        {
            mean += (double) sims[i].getResult() / sims.length;
        }

        System.out.println("    Calculated Guilty Mean");
        return(mean);
    }

    static double getVariance(GuiltySimulation[] sims,double gMean)
    {
        System.out.println("    Calculating Guilty Variance");

        double variance = 0;
        double mean = gMean;
        for(int i=0;i<sims.length;i++)
        {
            variance += ((mean-sims[i].getResult())*(mean-sims[i].getResult())) / sims.length;
            double test = ((mean-sims[i].getResult())*(mean-sims[i].getResult())) / sims.length;
            //System.out.println("O "+test+"+=("+(mean-sims[i].getResult())+")^2/"+sims.length);
        }

        System.out.println("    Calculated Guilty Variance");
        return(variance);
    }
}
