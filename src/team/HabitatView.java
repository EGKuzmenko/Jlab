package team;

import data.Worker;
import data.WorkerArrayList;
import sun.tools.jstat.Jstat;

import javax.swing.*;
import java.awt.*;


public class HabitatView extends JFrame {

    private int mHeight;
    private int mWidth;
    private int mCoordinateX;
    private int mCoordinateY;

    JMenu menuSimulation = null;
    JMenu menuUI = null;

    JMenuItem startSimulationItem = null;
    JMenuItem endSimulationItem = null;
    JCheckBoxMenuItem showInfoItem = null;
    JCheckBoxMenuItem showTimeItem = null;

    JPanel mainPanel = null;
    JPanel panelGen = null;
    JButton startButton = null;
    JButton endButton = null;
    JCheckBox showInfoCheckBox = null;
    JTextArea infoArea = null;
    JRadioButton yesButton = null;
    JRadioButton noButton = null;
    JLabel showTimeLabel = null;
    JLabel timeDevelopersLabel = null;
    JLabel timeManagersLabel = null;
    JLabel pTimeDevelopersLabel = null;
    JLabel pTimeManagersLabel = null;
    JTextField timeDevelopersArea = null;
    JTextField timeManagersArea = null;
    JSlider developersSlider = null;
    JSlider managersSlider = null;

    public HabitatView(int mWidth, int mHeight, int mCoordinateX, int mCoordinateY) {
        this.mHeight = mHeight;
        this.mWidth = mWidth;
        this.mCoordinateX = mCoordinateX;
        this.mCoordinateY = mCoordinateY;
        drawMenu();
        drawUI();
    }

    private void drawMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuSimulation = new JMenu("Симуляция");
        menuUI = new JMenu("Вид");

        showInfoItem = new JCheckBoxMenuItem("Показать информацию");
       // showTimeItem = new JCheckBoxMenuItem("Показывать время");

        menuUI.add(showInfoItem);
        //menuUI.add(showTimeItem);

        startSimulationItem = new JMenuItem("Начать симуляцию");
        endSimulationItem = new JMenuItem("Остановать симуляцию");
        endSimulationItem.setEnabled(false);
        menuSimulation.add(startSimulationItem);
        menuSimulation.add(endSimulationItem);
        menuBar.add(menuSimulation);
        menuBar.add(menuUI);
        setJMenuBar(menuBar);
    }

    private void drawUI() {
        setLayout(null);
        Font font = new Font("Verdana", Font.PLAIN, 11);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        startButton = new JButton("start");
        startButton.setSize(100, 25);

        endButton = new JButton("end");
        endButton.setSize(100, 25);
        endButton.setLocation(0, 30);
        endButton.setEnabled(false);

        showInfoCheckBox = new JCheckBox("Показать информацию");
        showInfoCheckBox.setBounds(0, 70, 200, 25);
        showInfoCheckBox.setFocusable(false);

        showTimeLabel = new JLabel("Показать время?");
        showTimeLabel.setBounds(20, 170, 100, 20);

        yesButton = new JRadioButton("Да");
        yesButton.setFocusable(false);
        yesButton.setBounds(20, 190, 50, 25);

        noButton = new JRadioButton("Нет");
        noButton.setFocusable(false);
        noButton.setBounds(70, 190, 50, 25);
        noButton.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(yesButton);
        group.add(noButton);

        infoArea = new JTextArea();
        infoArea.setBounds(0, 100, 150, 65);
        infoArea.setEditable(false);
        infoArea.setVisible(false);
        infoArea.setFocusable(false);

        timeDevelopersLabel = new JLabel("Период появления разработчиков");
        timeDevelopersLabel.setBounds(10, 240, 240, 25);
        timeDevelopersArea = new JTextField();
        timeDevelopersArea.setBounds(240, 240, 20, 20);

        timeManagersLabel = new JLabel("Период поялвения менеджеров");
        timeManagersLabel.setBounds(10, 340, 240, 20);
        timeManagersArea = new JTextField();
        timeManagersArea.setBounds(240, 340, 20, 20);

        pTimeDevelopersLabel = new JLabel("Вероятность полявения разработчиков");
        pTimeDevelopersLabel.setBounds(10, 260, 260, 20);
        developersSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        developersSlider.setMajorTickSpacing(10);
        developersSlider.setPaintTicks(true);
        developersSlider.setPaintLabels(true);
        developersSlider.setSnapToTicks(true);
        developersSlider.setBounds(10, 280, 250, 50);

        pTimeManagersLabel = new JLabel("Процент числа разработчиков");
        pTimeManagersLabel.setBounds(10, 360, 260, 20);
        managersSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        managersSlider.setMajorTickSpacing(10);
        managersSlider.setPaintTicks(true);
        managersSlider.setPaintLabels(true);
        managersSlider.setSnapToTicks(true);
        managersSlider.setBounds(10, 380, 250, 50);

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
        mainPanel.add(timeDevelopersLabel);
        mainPanel.add(timeManagersLabel);
        mainPanel.add(pTimeDevelopersLabel);
        mainPanel.add(pTimeManagersLabel);

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
        add(panelGen);
        add(mainPanel);
        setBounds(mCoordinateX, mCoordinateY, mWidth, mHeight);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
