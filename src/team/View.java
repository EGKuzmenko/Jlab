package team;

import data.Worker;
import data.WorkerArrayList;
import sun.tools.jstat.Jstat;

import javax.swing.*;
import java.awt.*;


public class View extends JFrame {

    private int mHeight;
    private int mWidth;
    private int mCoordinateX;
    private int mCoordinateY;

    Controllers controllers;
    JLabel mes = null;
    JPanel mainPanel = null;
    JPanel panelGen = null;
    JButton startButton = null;
    JButton endButton = null;
    JCheckBox showInfoCheckBox = null;
    JTextArea infoArea = null;
    JRadioButton yesButton = null;
    JRadioButton noButton = null;
    JLabel showTimeLabel = null;
    JTextField timeDevelopersArea = null;
    JTextField timeManagersArea = null;
    JSlider developersSlider = null;
    JSlider managersSlider = null;

    public View(int mWidth, int mHeight, int mCoordinateX, int mCoordinateY) {
        this.mHeight = mHeight;
        this.mWidth = mWidth;
        this.mCoordinateX = mCoordinateX;
        this.mCoordinateY = mCoordinateY;
        drawUI();
    }

    public void drawUI() {
        setLayout(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        startButton = new JButton("start");
        startButton.setSize(100, 25);

        endButton = new JButton("end");
        endButton.setSize(100, 25);
        endButton.setLocation(0, 30);
        endButton.setEnabled(false);

        showTimeLabel = new JLabel("Показать время?");
        showTimeLabel.setBounds(0, 125, 100, 20);

        yesButton = new JRadioButton("Да");
        yesButton.setFocusable(false);
        yesButton.setBounds(0, 145, 50, 25);

        noButton = new JRadioButton("Нет");
        noButton.setFocusable(false);
        noButton.setBounds(60, 145, 50, 25);
        noButton.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(yesButton);
        group.add(noButton);

        infoArea = new JTextArea();
        infoArea.setBounds(0, 180, 150, 65);
        infoArea.setEditable(false);
        infoArea.setVisible(false);
        infoArea.setFocusable(false);

        showInfoCheckBox = new JCheckBox("Показать информацию");
        showInfoCheckBox.setBounds(0, 100, 200, 25);
        showInfoCheckBox.setFocusable(false);

        timeDevelopersArea = new JTextField(String.valueOf(Habitat.timeOfDevelopers));
        timeDevelopersArea.setBounds(0, 250, 20, 20);

        timeManagersArea = new JTextField(String.valueOf(Habitat.timeOfManagers));
        timeManagersArea.setBounds(0, 280, 20, 20);

        developersSlider = new JSlider(JSlider.VERTICAL, 0, 100, (int) (Habitat.probabilityDevelopers * 100));
        developersSlider.setMajorTickSpacing(10);
        developersSlider.setPaintTicks(true);
        developersSlider.setPaintLabels(true);
        developersSlider.setSnapToTicks(true);
        developersSlider.setBounds(40, 250, 50, 200);

        managersSlider = new JSlider(JSlider.VERTICAL, 0, 100, (int) (Habitat.percentageOfDevelopers));
        managersSlider.setMajorTickSpacing(10);
        managersSlider.setPaintTicks(true);
        managersSlider.setPaintLabels(true);
        managersSlider.setSnapToTicks(true);
        managersSlider.setBounds(90, 250, 50, 200);

        mainPanel.add(yesButton);
        mainPanel.add(noButton);
        mainPanel.add(showTimeLabel);
        mainPanel.add(startButton);
        mainPanel.add(endButton);
        mainPanel.add(showInfoCheckBox);
        mainPanel.add(infoArea);
        mainPanel.add(timeDevelopersArea);
        mainPanel.add(timeManagersArea);
        mainPanel.add(developersSlider);
        mainPanel.add(managersSlider);

        panelGen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawRect(10, 10, 500, 500);
                for (Worker worker : WorkerArrayList.getInstance().arrayWorkerList) {
                    worker.paint(g);
                }
            }
        };

        panelGen.setFocusable(true);

        panelGen.setBounds(10, 10, 520, 520);
        mainPanel.setBounds(530, 10, 300, 500);
        mes = new JLabel("", JLabel.RIGHT);
        panelGen.add(mes);
        add(panelGen);
        add(mainPanel);
        setBounds(mCoordinateX, mCoordinateY, mWidth, mHeight);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
