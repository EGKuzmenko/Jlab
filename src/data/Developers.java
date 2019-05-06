package data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Developers extends Worker {

    public int getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(int liveTime) {
        this.liveTime = liveTime;
    }

    public static int liveTime = 2;

    private static Image img;

    static {
        try {
            img = ImageIO.read(new File("resources/Image/developer.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Developers(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g) {
        if (img != null) {
            g.drawImage(img, this.getX(), this.getY(), img.getWidth(null), img.getHeight(null), null);
        }
    }

    @Override
    public void move() {

    }

}
