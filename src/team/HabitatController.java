package team;



import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class HabitatController {

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

        view.timeDevelopersArea.addTextListener(timeDevelopersTextFieldList);
        view.timeDevelopersArea.addActionListener(timeDevelopersTextFieldListener);
        view.timeDevelopersArea.setText(String.valueOf(model.getTimeOfDevelopers()));

        view.timeManagersArea.addTextListener(timeManagersTextFieldList);
        view.timeManagersArea.addActionListener(timeManagersTextFieldListener);
        view.timeManagersArea.setText(String.valueOf(model.getTimeOfManagers()));
    }

    private ActionListener radioListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            switch (((JRadioButton) ae.getSource()).getText()) {
                case  "Да":
                    view.infoArea.setText(
                            "Количество: " + (model.numbersOfManagers + model.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + model.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + model.numbersOfManagers + "\n" +
                                    "Время: " + model.time);
                    break;
                case "Нет":
                    view.infoArea.setText(
                            "Количество: " + (model.numbersOfManagers + model.numbersOfDevelopers) + "\n" +
                                    "Разработчики: " + model.numbersOfDevelopers + "\n" +
                                    "Менеджеры: " + model.numbersOfManagers);
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
                view.showTimePanel.setVisible(true);
            } else {
                view.infoArea.setVisible(false);
                view.showInfoItem.setState(false);
                view.showTimePanel.setVisible(false);
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
                        model.startSimulation(true);
                    }
                    break;
                case KeyEvent.VK_E:
                    model.stopSimulation(view.showInfoCheckBox.isSelected());
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

    private ActionListener endListener = e -> model.stopSimulation(view.showInfoCheckBox.isSelected());

    private ActionListener beginListener = e -> model.startSimulation(true);

    private ChangeListener developersChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            double value = slider.getValue();
            model.setProbabilityDevelopers(value / 100);
            view.panelGen.requestFocus();
        }
    };

    private ChangeListener managersChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            double value = slider.getValue();
            model.setPercentageOfDevelopers(value);
            view.panelGen.requestFocus();
        }
    };

    private void timeDevelopersValidation() {
        try {
            Integer value;
            value = Integer.parseInt(view.timeDevelopersArea.getText());
            if (value > 0) {
                model.setTimeOfDevelopers(value);
            } else {
                throw new Exception();
            }
        } catch (Exception ignored) {

        }
    }

    private void timeManagersValidation() {
        try {
            Integer value;
            value = Integer.parseInt(view.timeManagersArea.getText());
            if (value > 0) {
                model.setTimeOfManagers(value);
            } else {
                throw new Exception();
            }
        } catch (Exception ignored) {

        }
    }

    private ActionListener timeDevelopersTextFieldListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeDevelopersValidation();
            view.panelGen.requestFocus();
        }
    };

    private ActionListener timeManagersTextFieldListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeManagersValidation();
            view.panelGen.requestFocus();
        }
    };

    private TextListener timeDevelopersTextFieldList = e -> timeDevelopersValidation();
    private TextListener timeManagersTextFieldList = e ->  timeManagersValidation();

}
