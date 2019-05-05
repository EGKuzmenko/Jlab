package team;

import data.Developers;
import data.Manager;
import data.Worker;
import data.WorkerArrayList;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class HabitatModel {

    private Timer timer;
    private double probabilityDevelopers;
    private double percentageOfDevelopers;
    long time = 0;
    private int timeOfDevelopers;
    private int timeOfManagers;
    int numbersOfDevelopers = 0;
    int numbersOfManagers = 0;
    HabitatView view;

    public HabitatModel(double probabilityDevelopers, double percentageOfDevelopers,
                        int timeOfDevelopers, int timeOfManagers, HabitatView view) {
        this.probabilityDevelopers = probabilityDevelopers;
        this.percentageOfDevelopers = percentageOfDevelopers;
        this.timeOfDevelopers = timeOfDevelopers;
        this.timeOfManagers = timeOfManagers;
        this.view = view;
    }

    void update(long t) {
        if (t % timeOfDevelopers == 0) {
            if (probabilityDevelopers > (float) Math.random()) {
                numbersOfDevelopers++;
                Worker hm = new Developers(10 + (int) (Math.random() * view.panelGen.getWidth() - 100),
                        10 + (int) (Math.random() * view.panelGen.getHeight() - 100));
                WorkerArrayList.getInstance().arrayWorkerList.add(hm);
            }
        }

        if (t % timeOfManagers == 0) {
            if (((float) numbersOfManagers) < ((float) numbersOfDevelopers / 100) * (float) percentageOfDevelopers) {
                numbersOfManagers++;
                Worker hm = new Manager(10 + (int) (Math.random() * view.panelGen.getWidth() - 100),
                        10 + (int) (Math.random() * view.panelGen.getWidth() - 100));
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

    void startSimulation(boolean firstStart) {
        timer = new Timer();
        if (firstStart) {
            numbersOfDevelopers = 0;
            numbersOfManagers = 0;
            time = 0;
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time++;
                update(time);
                view.startSimulation(generateStatisticString(true), generateStatisticString(false));
            }
        }, 0, 1000);
    }

    String generateStatisticString(boolean withTime) {
        String statistic;
        if (withTime) {
            statistic = "Количество: " + (numbersOfDevelopers + numbersOfManagers) + "\n" +
                    "Разработчики: " + numbersOfDevelopers + "\n" +
                    "Менеджеры: "+ numbersOfManagers + "\n" +
                    "Время: " + time;
        } else {
            statistic ="Количество: " + (numbersOfDevelopers + numbersOfManagers) + "\n" +
                    "Разработчики: " + numbersOfDevelopers + "\n" +
                    "Менеджеры: "+ numbersOfManagers + "\n";
        }
        return statistic;
    }

    public void stopSimulation(boolean selected) {
        timer.cancel();
        timer.purge();
        if (selected) {
            Object[] options = {"Resume",
                    "Stop"};
            int n = JOptionPane.showOptionDialog(new JFrame(),
                    generateStatisticString(true),
                    "StopDialog",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 0) {
                startSimulation(false);
            } else {
                WorkerArrayList.getInstance().arrayWorkerList.clear();
                view.stopSimulation();
            }
        } else {
            WorkerArrayList.getInstance().arrayWorkerList.clear();
            view.stopSimulation();
        }

    }
}
