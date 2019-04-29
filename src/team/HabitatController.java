package team;


import data.WorkerArrayList;

import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class HabitatController {

    private Timer timer;
    HabitatView view;
    HabitatModel habitat;

    public HabitatController(HabitatView view, HabitatModel habitat) {
        this.view = view;
        this.habitat = habitat;
        init();
    }

    void init() {
        view.startButton.addActionListener(beginListener);
        view.endButton.addActionListener(endListener);
        view.yesButton.addActionListener(radioListener);
        view.noButton.addActionListener(radioListener);
        view.showInfoCheckBox.addItemListener(showInfoCheckBoxListener);
        view.panelGen.addKeyListener(keyAdapter);
        view.timeDevelopersArea.setText(String.valueOf(habitat.getTimeOfDevelopers()));
        view.timeManagersArea.setText(String.valueOf(habitat.getTimeOfManagers()));
    }

    public void startSimulation(long t, int _numbersOfDevelopers, int _numbersOfMangers) {
        timer = new java.util.Timer();
        habitat.numbersOfDevelopers = _numbersOfDevelopers;
        habitat.numbersOfManagers = _numbersOfMangers;
        habitat.time = t;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                habitat.time++;
                habitat.update(habitat.time);
                if (view.yesButton.isSelected()) {
                    view.infoArea.setText(
                            "Количество: " + (habitat.numbersOfManagers + habitat.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + habitat.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + habitat.numbersOfManagers + "\n" +
                                    "Время: " + habitat.time);
                } else {
                    view.infoArea.setText(
                            "Количество: " + (habitat.numbersOfManagers + habitat.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + habitat.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + habitat.numbersOfManagers);
                }
                view.startButton.setEnabled(false);
                view.endButton.setEnabled(true);
                view.repaint();
            }
        }, 0, 1000);
    }

    public void endSimulation() {
        timer.cancel();
        timer.purge();
        if (view.showInfoCheckBox.isSelected()) {
            Object[] options = {"Resume", "Stop"};
            int n = JOptionPane.showOptionDialog(new JFrame(),
                    "Количество: " + (habitat.numbersOfManagers + habitat.numbersOfDevelopers) + "\n" +
                            "Разработчики: " + habitat.numbersOfDevelopers + "\n" +
                            "Менеджеры: " + habitat.numbersOfManagers + "\n" +
                            "Время: " + habitat.time,
                    "StopDialog",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 0) {
                startSimulation(habitat.time, habitat.numbersOfDevelopers, habitat.numbersOfManagers);
            } else {
                WorkerArrayList.getInstance().arrayWorkerList.clear();
                view.repaint();
                view.startButton.setEnabled(true);
                view.endButton.setEnabled(false);
            }
        } else {
            WorkerArrayList.getInstance().arrayWorkerList.clear();
            view.repaint();
            view.startButton.setEnabled(true);
            view.endButton.setEnabled(false);
        }
    }

    ActionListener radioListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            switch ( ((JRadioButton)ae.getSource()).getText() ) {
                case  "Да":
                    view.infoArea.setText(
                            "Количество: " + (habitat.numbersOfManagers + habitat.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + habitat.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + habitat.numbersOfManagers + "\n" +
                                    "Время: " + habitat.time);
                    break;
                case "Нет":
                    view.infoArea.setText(
                            "Количество: " + (habitat.numbersOfManagers + habitat.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + habitat.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + habitat.numbersOfManagers);
                    break;
                default:
                    break;
            }
        }
    };

    ItemListener showInfoCheckBoxListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                view.infoArea.setVisible(true);
            } else {
                view.infoArea.setVisible(false);
            }
        }
    };

    KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_B:
                    if (view.startButton.isEnabled()) {
                        startSimulation(0, 0, 0);
                    }
                    break;
                case KeyEvent.VK_E:
                    endSimulation();
                    break;
                case KeyEvent.VK_T:
                    if (view.showInfoCheckBox.isSelected()) {
                        view.infoArea.setVisible(false);
                        view.showInfoCheckBox.setSelected(false);
                    } else {
                        view.infoArea.setVisible(true);
                        view.showInfoCheckBox.setSelected(true);
                    }
                    break;
            }
        }
    };

    ActionListener endListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            endSimulation();
        }
    };

    ActionListener beginListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            startSimulation(0, 0, 0);
        }
    };

}
