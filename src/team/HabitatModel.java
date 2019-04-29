package team;

import data.Developers;
import data.Manager;
import data.Worker;
import data.WorkerArrayList;

public class HabitatModel {

    private double probabilityDevelopers;
    private double percentageOfDevelopers;
    long time = 0;
    private int timeOfDevelopers;
    private int timeOfManagers;
    int numbersOfDevelopers = 0;
    int numbersOfManagers = 0;

    public HabitatModel(double probabilityDevelopers, double percentageOfDevelopers,
                        int timeOfDevelopers, int timeOfManagers) {
        this.probabilityDevelopers = probabilityDevelopers;
        this.percentageOfDevelopers = percentageOfDevelopers;
        this.timeOfDevelopers = timeOfDevelopers;
        this.timeOfManagers = timeOfManagers;
    }

    void update(long t) {
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

    public double getProbabilityDevelopers() {
        return probabilityDevelopers;
    }

    public void setProbabilityDevelopers(double probabilityDevelopers) {
        this.probabilityDevelopers = probabilityDevelopers;
    }

    public double getPercentageOfDevelopers() {
        return percentageOfDevelopers;
    }

    public void setPercentageOfDevelopers(double percentageOfDevelopers) {
        this.percentageOfDevelopers = percentageOfDevelopers;
    }

    public int getTimeOfDevelopers() {
        return timeOfDevelopers;
    }

    public void setTimeOfDevelopers(int timeOfDevelopers) {
        this.timeOfDevelopers = timeOfDevelopers;
    }

    public int getTimeOfManagers() {
        return timeOfManagers;
    }

    public void setTimeOfManagers(int timeOfManagers) {
        this.timeOfManagers = timeOfManagers;
    }
}
