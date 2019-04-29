
public class Habitat {

    private static double probabilityDevelopers;
    private static double percentageOfDevelopers;
    static long time = 0;
    private static int timeOfDevelopers;
    private static int timeOfManagers;
    static int numbersOfDevelopers = 0;
    static int numbersOfManagers = 0;

    static {
        probabilityDevelopers = 0.5;
        percentageOfDevelopers = 25;
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
