package data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Manager extends Worker {

    private static Image img;

    static {
        try {
            img = ImageIO.read(new File("resources/Image/manager.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Manager(int x, int y) {
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
