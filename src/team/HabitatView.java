package team;

import data.Worker;
import data.WorkerArrayList;

import javax.swing.*;
import java.awt.*;


public class HabitatView extends JFrame {

    private int mHeight;
    private int mWidth;
    private int mCoordinateX;
    private int mCoordinateY;

    JMenu menuSimulation;
    JMenu menuUI;

    JMenuItem startSimulationItem;
    JMenuItem endSimulationItem;
    JCheckBoxMenuItem showInfoItem;
    JCheckBoxMenuItem showTimeItem;

    JPanel mainPanel;
    JPanel panelGen;
    JPanel showTimePanel;
    JButton startButton;
    JButton endButton;
    JCheckBox showInfoCheckBox;
    JTextArea infoArea;
    JRadioButton yesButton;
    JRadioButton noButton;
    JLabel showTimeLabel;
    JPanel parentPanel;

    JLabel timeDevelopersLabel;
    JLabel timeManagersLabel;
    JLabel pTimeDevelopersLabel;
    JLabel pTimeManagersLabel;
    TextField timeDevelopersArea;
    TextField timeManagersArea;

    JSlider developersSlider;
    JSlider managersSlider;

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

    private void drawUI() {

        parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        startButton = new JButton("start");
        startButton.setMaximumSize(new Dimension(100, 25));

        endButton = new JButton("end");
        endButton.setMaximumSize(new Dimension(100, 25));
        endButton.setEnabled(false);

        showInfoCheckBox = new JCheckBox("Показать информацию");
        showInfoCheckBox.setFocusable(false);

        showTimePanel = new JPanel();
        showTimePanel.setLayout(new BoxLayout(showTimePanel, BoxLayout.LINE_AXIS));

        showTimeLabel = new JLabel("Показать время?");

        yesButton = new JRadioButton("Да");
        yesButton.setFocusable(false);

        noButton = new JRadioButton("Нет");
        noButton.setFocusable(false);
        noButton.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(yesButton);
        group.add(noButton);
        showTimePanel.add(showTimeLabel);
        showTimePanel.add(yesButton);
        showTimePanel.add(noButton);
        showTimePanel.setVisible(false);

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setVisible(false);
        infoArea.setFocusable(false);
        infoArea.setMaximumSize(new Dimension(300, 75));

        JPanel timeDevelopersPanel= new JPanel();
        timeDevelopersPanel.setLayout(new BoxLayout(timeDevelopersPanel, BoxLayout.LINE_AXIS));
        timeDevelopersLabel = new JLabel("Период появления разработчиков");
        timeDevelopersArea = new TextField();
        timeDevelopersArea.setMaximumSize(new Dimension(50, 25));

        timeDevelopersPanel.add(timeDevelopersLabel);
        timeDevelopersPanel.add(timeDevelopersArea);

        timeManagersLabel = new JLabel("Период поялвения менеджеров");
        timeManagersArea = new TextField();
        timeManagersArea.setMaximumSize(new Dimension(50, 25));

        pTimeDevelopersLabel = new JLabel("Вероятность полявения разработчиков");
        developersSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        developersSlider.setMajorTickSpacing(10);
        developersSlider.setPaintTicks(true);
        developersSlider.setPaintLabels(true);
        developersSlider.setSnapToTicks(true);

        pTimeManagersLabel = new JLabel("Процент числа разработчиков");
        managersSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        managersSlider.setMajorTickSpacing(10);
        managersSlider.setPaintTicks(true);
        managersSlider.setPaintLabels(true);
        managersSlider.setSnapToTicks(true);



        mainPanel.add(startButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(endButton);
        mainPanel.add(showInfoCheckBox);
        mainPanel.add(infoArea);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(showTimePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(timeDevelopersPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(pTimeDevelopersLabel);
        mainPanel.add(developersSlider);
        mainPanel.add(timeManagersLabel);
        mainPanel.add(timeManagersArea);
        mainPanel.add(pTimeManagersLabel);
        mainPanel.add(managersSlider);

        panelGen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawRect(10, 10, getWidth() - 40, getHeight() - 40);
                for (Worker worker : WorkerArrayList.getInstance().arrayWorkerList) {
                    worker.paint(g);
                }
            }
        };

        panelGen.setFocusable(true);
        add(parentPanel);

        parentPanel.add(panelGen, BorderLayout.CENTER);
        parentPanel.add(mainPanel, BorderLayout.LINE_END);
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
        repaint();
    }

    void stopSimulation() {
        repaint();
        startButton.setEnabled(true);
        startSimulationItem.setEnabled(true);
        endSimulationItem.setEnabled(false);
        endButton.setEnabled(false);
    }

}
