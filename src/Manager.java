import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Manager extends Worker {

    static Image img;

    static {
        try {
            img = ImageIO.read(new File("resources/Image/manager.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Manager() {
        super();
    }

    @Override
    public Worker paint(Graphics g) {
        if (img != null) {
            g.drawImage(img, this.getX(), this.getY(), img.getWidth(null), img.getHeight(null), null);
        }
        return null;
    }

    @Override
    public void move() {

    }

}
