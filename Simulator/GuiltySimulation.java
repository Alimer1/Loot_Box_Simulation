public class GuiltySimulation extends Simulation implements Runnable
{

    private int fish = 0;

    public GuiltySimulation(int newCrateSize,SimulationMenu nSimMenu)
    {
        crateSize = newCrateSize;
        simMenu = nSimMenu;
    }

    public void run()
    {
        while(uniques < crateSize)
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
            chance = (double) (crateSize-uniques)/crateSize;
            numOfTries++;
        }
        simMenu.markCompletion();
    }
}
