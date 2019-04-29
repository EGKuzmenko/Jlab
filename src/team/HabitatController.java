package team;


import data.WorkerArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class HabitatController {

    private Timer timer;
    private HabitatView view;
    private HabitatModel model;

    public HabitatController(HabitatView view, HabitatModel model) {
        this.view = view;
        this.model = model;
        init();
    }

    private void init() {
        view.startSimulationItem.addActionListener(beginListener);
        view.endSimulationItem.addActionListener(endListener);
        view.showInfoItem.addActionListener(menuInfoListener);

        view.developersSlider.addChangeListener(developersChangeListener);
        view.managersSlider.addChangeListener(managersChangeListener);
        view.startButton.addActionListener(beginListener);
        view.endButton.addActionListener(endListener);
        view.yesButton.addActionListener(radioListener);
        view.noButton.addActionListener(radioListener);
        view.showInfoCheckBox.addItemListener(showInfoCheckBoxListener);
        view.panelGen.addKeyListener(keyAdapter);

        view.timeDevelopersArea.addActionListener(timeDevelopersTextFieldListener);
        view.timeDevelopersArea.setText(String.valueOf(model.getTimeOfDevelopers()));

        view.timeManagersArea.addActionListener(timeManagersTextFieldListener);
        view.timeManagersArea.setText(String.valueOf(model.getTimeOfManagers()));
    }

    private void startSimulation(long t, int _numbersOfDevelopers, int _numbersOfMangers) {
        timer = new java.util.Timer();
        model.numbersOfDevelopers = _numbersOfDevelopers;
        model.numbersOfManagers = _numbersOfMangers;
        model.time = t;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                model.time++;
                model.update(model.time);
                if (view.yesButton.isSelected()) {
                    view.infoArea.setText(
                            "Количество: " + (model.numbersOfManagers + model.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + model.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + model.numbersOfManagers + "\n" +
                                    "Время: " + model.time);
                } else {
                    view.infoArea.setText(
                            "Количество: " + (model.numbersOfManagers + model.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + model.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + model.numbersOfManagers);
                }
                view.startButton.setEnabled(false);
                view.startSimulationItem.setEnabled(false);
                view.endSimulationItem.setEnabled(true);
                view.endButton.setEnabled(true);
                view.repaint();
            }
        }, 0, 1000);
    }

    private void endSimulation() {
        timer.cancel();
        timer.purge();
        if (view.showInfoCheckBox.isSelected()) {
            Object[] options = {"Resume", "Stop"};
            int n = JOptionPane.showOptionDialog(new JFrame(),
                    "Количество: " + (model.numbersOfManagers + model.numbersOfDevelopers) + "\n" +
                            "Разработчики: " + model.numbersOfDevelopers + "\n" +
                            "Менеджеры: " + model.numbersOfManagers + "\n" +
                            "Время: " + model.time,
                    "StopDialog",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 0) {
                startSimulation(model.time, model.numbersOfDevelopers, model.numbersOfManagers);
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
            view.startSimulationItem.setEnabled(false);
            view.endSimulationItem.setEnabled(true);
            view.endButton.setEnabled(false);
        }
    }

    private ActionListener radioListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            switch ( ((JRadioButton)ae.getSource()).getText() ) {
                case  "Да":
                    view.infoArea.setText(
                            "Количество: " + (model.numbersOfManagers + model.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + model.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + model.numbersOfManagers + "\n" +
                                    "Время: " + model.time);
                    view.showTimeItem.setState(true);
                    break;
                case "Нет":
                    view.infoArea.setText(
                            "Количество: " + (model.numbersOfManagers + model.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + model.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + model.numbersOfManagers);
                    view.showTimeItem.setState(false);
                    break;
                default:
                    break;
            }
        }
    };

    private ItemListener showInfoCheckBoxListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                view.infoArea.setVisible(true);
                view.showInfoItem.setState(true);
            } else {
                view.infoArea.setVisible(false);
                view.showInfoItem.setState(false);
            }
        }
    };

    private ActionListener menuInfoListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.showInfoItem.getState()) {
                view.infoArea.setVisible(true);
                view.showInfoCheckBox.setSelected(true);
            } else {
                view.infoArea.setVisible(false);
                view.showInfoCheckBox.setSelected(false);
            }
        }
    };

    private KeyAdapter keyAdapter = new KeyAdapter() {
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

    private ActionListener endListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            endSimulation();
        }
    };

    private ActionListener beginListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            startSimulation(0, 0, 0);
        }
    };

    private ChangeListener developersChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider)e.getSource();
            double value = slider.getValue();
            model.setProbabilityDevelopers(value/100);
            view.panelGen.requestFocus();
        }
    };

    private ChangeListener managersChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider)e.getSource();
            double value = slider.getValue();
            model.setPercentageOfDevelopers(value);
            view.panelGen.requestFocus();
        }
    };

    private ActionListener timeDevelopersTextFieldListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String value;
            value = view.timeDevelopersArea.getText();
            view.panelGen.requestFocus();
            model.setTimeOfDevelopers(Integer.parseInt(value));
        }
    };

    private ActionListener timeManagersTextFieldListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String value;
            value = view.timeManagersArea.getText();
            view.panelGen.requestFocus();
            model.setTimeOfManagers(Integer.parseInt(value));
        }
    };

}
