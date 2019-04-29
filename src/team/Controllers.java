package team;

import data.WorkerArrayList;

import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Controllers {

    private Timer timer;

    View v;

    public Controllers(View v) {
        this.v = v;
        init();
    }

    void init() {
        v.startButton.addActionListener(beginListener);
        v.endButton.addActionListener(endListener);
        v.yesButton.addActionListener(radioListener);
        v.noButton.addActionListener(radioListener);
        v.showInfoCheckBox.addItemListener(showInfoCheckBoxListener);
        v.panelGen.addKeyListener(keyAdapter);
    }

    private void startSimulation(long t, int _numbersOfDevelopers, int _numbersOfMangers) {
        timer = new Timer();
        Habitat.numbersOfDevelopers = _numbersOfDevelopers;
        Habitat.numbersOfManagers = _numbersOfMangers;
        Habitat.time = t;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Habitat.time++;
                Habitat.update(Habitat.time);
                if (v.yesButton.isSelected()) {
                    v.infoArea.setText(
                            "Количество: " + (Habitat.numbersOfManagers + Habitat.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + Habitat.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + Habitat.numbersOfManagers + "\n" +
                                    "Время: " + Habitat.time);
                } else {
                    v.infoArea.setText(
                            "Количество: " + (Habitat.numbersOfManagers + Habitat.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + Habitat.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + Habitat.numbersOfManagers);
                }
                v.startButton.setEnabled(false);
                v.endButton.setEnabled(true);
                v.repaint();
            }
        }, 0, 1000);
    }

    private void endSimulation() {
        timer.cancel();
        timer.purge();
        if (v.showInfoCheckBox.isSelected()) {
            Object[] options = {"Resume", "Stop"};
            int n = JOptionPane.showOptionDialog(new JFrame(),
                    "Количество: " + (Habitat.numbersOfManagers + Habitat.numbersOfDevelopers) + "\n" +
                            "Разработчики: " + Habitat.numbersOfDevelopers + "\n" +
                            "Менеджеры: " + Habitat.numbersOfManagers + "\n" +
                            "Время: " + Habitat.time,
                    "StopDialog",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 0) {
                startSimulation(Habitat.time, Habitat.numbersOfDevelopers, Habitat.numbersOfManagers);
            } else {
                WorkerArrayList.getInstance().arrayWorkerList.clear();
                v.repaint();
                v.startButton.setEnabled(true);
                v.endButton.setEnabled(false);
            }
        } else {
            WorkerArrayList.getInstance().arrayWorkerList.clear();
            v.repaint();
            v.startButton.setEnabled(true);
            v.endButton.setEnabled(false);
        }
    }

    ActionListener radioListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            switch ( ((JRadioButton)ae.getSource()).getText() ) {
                case  "Да":
                    v.infoArea.setText(
                            "Количество: " + (Habitat.numbersOfManagers + Habitat.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + Habitat.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + Habitat.numbersOfManagers + "\n" +
                                    "Время: " + Habitat.time);
                    break;
                case "Нет":
                    v.infoArea.setText(
                            "Количество: " + (Habitat.numbersOfManagers + Habitat.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + Habitat.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + Habitat.numbersOfManagers);
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
                v.infoArea.setVisible(true);
            } else {
                v.infoArea.setVisible(false);
            }
        }
    };

    KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_B:
                    if (v.startButton.isEnabled()) {
                        startSimulation(0, 0, 0);
                    }
                    break;
                case KeyEvent.VK_E:
                    endSimulation();
                    break;
                case KeyEvent.VK_T:
                    if (v.showInfoCheckBox.isSelected()) {
                        v.infoArea.setVisible(false);
                        v.showInfoCheckBox.setSelected(false);
                    } else {
                        v.infoArea.setVisible(false);
                        v.showInfoCheckBox.setSelected(true);
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
