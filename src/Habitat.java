import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.TimerTask;

public class Habitat extends JFrame implements KeyListener {

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
    private JPanel panel = null;
    private boolean mas = true;
    private Timer timer;
    private boolean begin = false;

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
        percentageOfDevelopers = 0.5;
        timeOfDevelopers = 1;
        timeOfManagers = 1;
    }

    private void update(long t) {
        if (t % timeOfDevelopers == 0) {
            if (probabilityDevelopers > (float) Math.random()){
                numbersOfDevelopers++;
                Worker hm = new Developers();
                hm.paint(getGraphics());
                list.add(hm);
            }
        }

        if (t % timeOfManagers == 0) {
            if (percentageOfDevelopers > (float) Math.random()) {
                numbersOfManagers++;
                Worker hm = new Manager();
                hm.paint(getGraphics());
                list.add(hm);
            }
        }
    }

    public void drawUI() {
        list = new ArrayList<Worker>();
        panel = new JPanel();
        add(panel);
        mes = new JLabel("", JLabel.RIGHT);
        panel.add(mes);
        setBounds(mCoordinateX, mCoordinateY, mWidth, mHeight);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (begin) {
                    time++;
                    update(time);
                }
            }
        }, 0, 1000);
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_B:
                begin = true;
                numbersOfDevelopers = 0;
                numbersOfManagers = 0;
                time = 0;
                mes.setText("");
                break;
            case KeyEvent.VK_E:
                if (begin) {
                    mes.setText("<html> <br>" +
                            "Количество: " + (numbersOfDevelopers + numbersOfManagers) + "<br>" +
                            "Разработчики: " + numbersOfDevelopers + "<br>" +
                            "Менеджеры: " + numbersOfManagers + "<br>" +
                            "Время: " + time + "<br></html>");
                    list.clear();
                    repaint();
                    numbersOfDevelopers = 0;
                    numbersOfManagers = 0;
                    time = 0;
                }
                begin = false;
                break;
            case KeyEvent.VK_T:
                if (!mas) {
                    mes.setText("<html> <br>" +
                            "Количество: " + (numbersOfDevelopers + numbersOfManagers) + "<br>" +
                            "Разработчики: " + numbersOfDevelopers + "<br>" +
                            "Менеджеры: " + numbersOfManagers + "<br>" +
                            "Время: " + time + "<br></html>");
                } else {
                    mes.setText("");
                }
                mas = !mas;
                break;
        }
    }

}
