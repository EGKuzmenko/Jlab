package team;

import data.Developers;
import data.Manager;
import data.Worker;
import data.WorkerCollections;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class HabitatView extends JFrame {

    public static int mHeight;
    public static int mWidth;
    private int mCoordinateX;
    private int mCoordinateY;

    JMenu menuSimulation;
    JMenu menuUI;

    JMenuItem startSimulationItem;
    JMenuItem endSimulationItem;
    JCheckBoxMenuItem showInfoItem;
    JCheckBoxMenuItem showTimeItem;

    JPanel mainPanel;
    public static JPanel panelGen;
    JPanel showTimePanel;
    JPanel testPanel;
    JButton startButton;
    JButton endButton;
    JButton liveObjects;
    JButton managerAIButton;
    JButton developerAIButton;
    JCheckBox showInfoCheckBox;
    JTextArea infoArea;
    JRadioButton yesButton;
    JRadioButton noButton;
    JLabel showTimeLabel;
    JPanel parentPanel;
    JPanel developerPanel;
    JPanel managerPanel;

    JLabel timeDevelopersLabel;
    JLabel timeManagersLabel;
    TextField timeDevelopersArea;
    TextField timeManagersArea;

    JSlider developersSlider;
    JSlider managersSlider;
    JLabel liveDevelopersLabel;
    TextField liveDevelopersArea;
    JLabel liveManagersLabel;
    TextField liveManagersArea;

    JComboBox priorManagerAI;
    JComboBox priorDeveloperAI;

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
        menuUI.add(showInfoItem);
        startSimulationItem = new JMenuItem("Начать симуляцию");
        endSimulationItem = new JMenuItem("Остановать симуляцию");
        endSimulationItem.setEnabled(false);
        menuSimulation.add(startSimulationItem);
        menuSimulation.add(endSimulationItem);
        menuBar.add(menuSimulation);
        menuBar.add(menuUI);
        setJMenuBar(menuBar);
    }

    private void drawDeveloperPanel() {
        developerPanel = new JPanel();
        developerPanel.setLayout(null);
        developerPanel.setMaximumSize(new Dimension(300, 125));
        developerPanel.setBounds(0, 160, 300, 125);
        timeDevelopersLabel = new JLabel("Время появления разработчиков");
        timeDevelopersLabel.setBounds(0, 0, 230, 25);
        timeDevelopersArea = new TextField();
        timeDevelopersArea.setBounds(230, 0, 30, 25);

        liveDevelopersLabel = new JLabel("Время жизни разработчиков");
        liveDevelopersLabel.setBounds(0, 30, 230, 25);
        liveDevelopersArea = new TextField();
        liveDevelopersArea.setText(String.valueOf(Developers.liveTime));
        liveDevelopersArea.setBounds(230, 30, 30, 25);

        developersSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        developersSlider.setBounds(0, 60, 300, 50);
        developersSlider.setMajorTickSpacing(10);
        developersSlider.setPaintTicks(true);
        developersSlider.setPaintLabels(true);
        developersSlider.setSnapToTicks(true);

        developerPanel.add(timeDevelopersLabel);
        developerPanel.add(timeDevelopersArea);
        developerPanel.add(liveDevelopersLabel);
        developerPanel.add(liveDevelopersArea);
        developerPanel.add(developersSlider);
    }

    private void drawManagerPanel() {
        managerPanel = new JPanel();
        managerPanel.setLayout(null);
        managerPanel.setMaximumSize(new Dimension(300, 125));
        managerPanel.setBounds(0, 295, 300, 125);

        timeManagersLabel = new JLabel("Период появления менеджеров");
        timeManagersLabel.setBounds(0, 0, 230, 25);
        timeManagersArea = new TextField();
        timeManagersArea.setBounds(230, 0, 30, 25);

        liveManagersLabel = new JLabel("Время жизни менеджеров");
        liveManagersLabel.setBounds(0, 30, 230, 25);
        liveManagersArea = new TextField();
        liveManagersArea.setText(String.valueOf(Manager.liveTime));
        liveManagersArea.setBounds(230, 30, 30, 25);

        managersSlider = new JSlider();
        managersSlider.setBounds(0, 60, 300, 50);
        managersSlider.setMajorTickSpacing(10);
        managersSlider.setPaintTicks(true);
        managersSlider.setPaintLabels(true);
        managersSlider.setSnapToTicks(true);

        managerPanel.add(timeManagersLabel);
        managerPanel.add(timeManagersArea);
        managerPanel.add(liveManagersLabel);
        managerPanel.add(liveManagersArea);
        managerPanel.add(managersSlider);
    }

    private void drawUI() {

        Border blackline;

        blackline = BorderFactory.createLineBorder(Color.BLACK);

        parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        testPanel = new JPanel();
        testPanel.setLayout(null);
        testPanel.setBounds(0, 0, 400, 150);
        testPanel.setMaximumSize(new Dimension(600, 150));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 30, 100, 125);

        startButton = new JButton("Start");
        startButton.setBounds(0, 0, 100, 25);
        endButton = new JButton("End");
        endButton.setBounds(0, 30, 100, 25);
        endButton.setEnabled(false);

        liveObjects = new JButton("Live Obj");
        liveObjects.setBounds(0, 60, 100, 25);
        liveObjects.setEnabled(false);

        buttonPanel.add(startButton);
        buttonPanel.add(endButton);
        buttonPanel.add(liveObjects);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(110, 0, 300, 250);
        showInfoCheckBox = new JCheckBox("Показать информацию");
        showInfoCheckBox.setBounds(0, 0, 300, 25);
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setVisible(false);
        infoArea.setBounds(0, 30, 100, 70);
        showTimePanel = new JPanel();
        showTimePanel.setLayout(null);
        showTimePanel.setBounds(0, 100, 150, 70);
        showTimeLabel = new JLabel("Показать время?");
        showTimeLabel.setBounds(0, 100, 150, 25);
        yesButton = new JRadioButton("Да");
        yesButton.setBounds(5, 25, 45, 25);
        noButton = new JRadioButton("Нет");
        noButton.setBounds(60, 25, 45, 25);
        noButton.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(yesButton);
        group.add(noButton);
        showTimePanel.add(showTimeLabel);
        showTimePanel.add(yesButton);
        showTimePanel.add(noButton);
        showTimePanel.setVisible(false);

        infoPanel.add(showInfoCheckBox);
        infoPanel.add(infoArea);
        infoPanel.add(showTimePanel);
        testPanel.add(buttonPanel);
        testPanel.add(infoPanel);

        drawDeveloperPanel();
        drawManagerPanel();

        managerAIButton = new JButton("Manager AI");
        managerAIButton.setBounds(0, 450, 100, 25);
        developerAIButton = new JButton("Developer AI");
        developerAIButton.setBounds(105, 450, 100, 25);
        priorDeveloperAI = new JComboBox();
        priorDeveloperAI.setBounds(130, 480, 50, 25);

        for (int i = 1; i <= 10; i++) {
            priorDeveloperAI.addItem(i);
        }

        priorManagerAI = new JComboBox();
        priorManagerAI.setBounds(25, 480, 50, 25);

        for (int i = 1; i <= 10; i++) {
            priorManagerAI.addItem(i);
        }

        mainPanel.add(testPanel);
        mainPanel.add(developerPanel);
        mainPanel.add(managerPanel);
        mainPanel.add(managerAIButton);
        mainPanel.add(developerAIButton);
        mainPanel.add(priorDeveloperAI);
        mainPanel.add(managerAIButton);
        mainPanel.setPreferredSize(new Dimension(300, 500));

        panelGen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                synchronized (WorkerCollections.getInstance().arrayWorkerList) {
                    for (Worker worker : WorkerCollections.getInstance().arrayWorkerList) {
                        worker.paint(g);
                    }
                }
            }
        };

        panelGen.setBorder(blackline);
        add(parentPanel);

        parentPanel.add(panelGen, BorderLayout.CENTER);
        parentPanel.add(mainPanel, BorderLayout.EAST);
        setBounds(mCoordinateX, mCoordinateY, mWidth, mHeight);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    void startSimulation(String statisticWithTime, String statisticWithoutTime) {
        if (yesButton.isSelected()) {
            infoArea.setText(statisticWithTime);
        } else {
            infoArea.setText(statisticWithoutTime);
        }
        startButton.setEnabled(false);
        startSimulationItem.setEnabled(false);
        endSimulationItem.setEnabled(true);
        endButton.setEnabled(true);
        liveObjects.setEnabled(true);
        repaint();
    }

    void stopSimulation() {
        repaint();
        startButton.setEnabled(true);
        startSimulationItem.setEnabled(true);
        endSimulationItem.setEnabled(false);
        endButton.setEnabled(false);
        liveObjects.setEnabled(false);
    }

}
