import java.awt.*;

public abstract class Worker implements IBehaviour {

    int coordinateX;
    int coordinateY;

    public abstract Worker paint(Graphics g);

    Worker() {
        coordinateX = (int) (Math.random() * 560);
        coordinateY = (int) (Math.random() * 560);
    }

    @Override
    public int getX() {
        return coordinateX;
    }

    @Override
    public int getY() {
        return coordinateY;
    }
}
