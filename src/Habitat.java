import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.ArrayList;
import java.util.TimerTask;

public class Habitat extends JFrame {

    private double probabilityDevelopers;
    private double percentageOfDevelopers;
    private int timeOfDevelopers;
    private int timeOfManagers;
    private int time = 0;

    private int mHeight;
    private int mWidth;
    private int mCoordinateX;
    private int mCoordinateY;

    private JLabel mes = null;
    private JPanel mainPanel = null;
    private JPanel panelGen = null;
    private JButton startButton = null;
    private JButton endButton = null;
    private JCheckBox showInfoCheckBox = null;
    private JTextArea infoArea = null;
    private boolean mas = true;
    private Timer timer;

    private int numbersOfDevelopers = 0;
    private int numbersOfManagers = 0;

    ArrayList<Worker> list;

    public Habitat() {
        this.mWidth = 500;
        this.mHeight = 500;
        this.mCoordinateX = 100;
        this.mCoordinateY = 100;
    }

    Habitat(int mWidth, int mHeight, int mCoordinateX, int mCoordinateY) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mCoordinateX = mCoordinateX;
        this.mCoordinateY = mCoordinateY;
        probabilityDevelopers = 0.5;
        percentageOfDevelopers = 25;
        timeOfDevelopers = 1;
        timeOfManagers = 1;
    }

    private void update(long t) {
        if (t % timeOfDevelopers == 0) {
            if (probabilityDevelopers > (float) Math.random()){
                numbersOfDevelopers++;
                Worker hm = new Developers(10 + (int) (Math.random() * 400), 10 + (int) (Math.random()  * 400));
                list.add(hm);
                panelGen.repaint();
            }
        }

        if (t % timeOfManagers == 0) {
            if (((float) numbersOfManagers) < ((float) numbersOfDevelopers / 100) * (float) percentageOfDevelopers) {
                numbersOfManagers++;
                Worker hm = new Manager(10 + (int) (Math.random() * 410), 10 + (int) (Math.random() * 400));
                list.add(hm);
                panelGen.repaint();
            }
        }
        infoArea.setText("Количество: " + (numbersOfDevelopers + numbersOfManagers) + "\n" +
                "Разработчики: " + numbersOfDevelopers + "\n" +
                "Менеджеры: " + numbersOfManagers + "\n" +
                "Время: " + time);
    }


    public void startSimulation() {
        numbersOfDevelopers = 0;
        numbersOfManagers = 0;
        time = 0;
        infoArea.setText("");
        startButton.setEnabled(false);
        endButton.setEnabled(true);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time++;
                update(time);
            }
        } , 0, 1000);
    }

    public void endSimulation() {
        list.clear();
        repaint();
        numbersOfDevelopers = 0;
        numbersOfManagers = 0;
        time = 0;
        showInfoCheckBox.setSelected(true);
        infoArea.setVisible(true);
        startButton.setEnabled(true);
        endButton.setEnabled(false);
    }

    public void drawUI() {
        setLayout(null);
        list = new ArrayList<Worker>();
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        startButton = new JButton("start");
        startButton.setSize(100, 25);
        startButton.addActionListener(e -> startSimulation());

        endButton = new JButton("end");
        endButton.setSize(100, 25);
        endButton.setLocation(0, 30);
        endButton.setEnabled(false);

        endButton.addActionListener(e -> endSimulation());

        infoArea = new JTextArea();
        infoArea.setBounds(0, 130, 150, 100);
        infoArea.setEditable(false);
        infoArea.setVisible(false);
        infoArea.setFocusable(false);

        showInfoCheckBox = new JCheckBox("Показать инофрмацию");
        showInfoCheckBox.setBounds(0, 100, 200, 25);
        showInfoCheckBox.setFocusable(false);
        showInfoCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                infoArea.setVisible(true);
            } else {
                infoArea.setVisible(false);
            }
        });

        mainPanel.add(startButton);
        mainPanel.add(endButton);
        mainPanel.add(showInfoCheckBox);
        mainPanel.add(infoArea);
        panelGen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawRect(10, 10, 500, 500);
                for (Worker worker:list) {
                    worker.paint(g);
                }
            }
        };
        panelGen.setFocusable(true);
        panelGen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent. VK_B:
                        startSimulation();
                        break;
                    case KeyEvent.VK_E:
                        endSimulation();
                        break;
                    case KeyEvent.VK_T:
                        if (showInfoCheckBox.isSelected()) {
                            infoArea.setVisible(false);
                            showInfoCheckBox.setSelected(false);
                        } else {
                            infoArea.setVisible(true);
                            showInfoCheckBox.setSelected(true);
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        panelGen.setBounds(10, 10, 520, 520);
        mainPanel.setBounds(530, 10, 200, 500);
        mes = new JLabel("", JLabel.RIGHT);
        panelGen.add(mes);
        add(panelGen);
        add(mainPanel);

        setBounds(mCoordinateX, mCoordinateY, mWidth, mHeight);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timer = new Timer();
    }

}
