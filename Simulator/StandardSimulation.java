public class StandardSimulation extends Simulation implements Runnable
{

    public StandardSimulation(int newCrateSize,SimulationMenu nSimMenu)
    {
        crateSize = newCrateSize;
        simMenu = nSimMenu;
    }

    public void run()
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
        simMenu.markCompletion();
    }
    
}
