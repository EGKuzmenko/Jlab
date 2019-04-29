package team;

import data.Developers;
import data.Manager;
import data.Worker;
import data.WorkerArrayList;

public class Habitat {

    static double probabilityDevelopers;
    static double percentageOfDevelopers;
    static long time = 0;
    static int timeOfDevelopers;
    static int timeOfManagers;
    static int numbersOfDevelopers = 0;
    static int numbersOfManagers = 0;

    static {
        probabilityDevelopers = 0.5;
        percentageOfDevelopers = 5;
        timeOfDevelopers = 1;
        timeOfManagers = 1;
    }

    static void update(long t) {
        if (t % timeOfDevelopers == 0) {
            if (probabilityDevelopers > (float) Math.random()) {
                numbersOfDevelopers++;
                Worker hm = new Developers(10 + (int) (Math.random() * 410), 10 + (int) (Math.random() * 410));
                WorkerArrayList.getInstance().arrayWorkerList.add(hm);
            }
        }

        if (t % timeOfManagers == 0) {
            if (((float) numbersOfManagers) < ((float) numbersOfDevelopers / 100) * (float) percentageOfDevelopers) {
                numbersOfManagers++;
                Worker hm = new Manager(10 + (int) (Math.random() * 410), 10 + (int) (Math.random() * 410));
                WorkerArrayList.getInstance().arrayWorkerList.add(hm);
            }
        }
    }

}
