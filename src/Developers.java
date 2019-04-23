import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Developers extends Worker {

    static Image img;

    static {
        try {
            img = ImageIO.read(new File("resources/Image/developer.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Developers() {
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
