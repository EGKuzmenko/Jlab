import java.awt.*;

public abstract class Worker implements IBehaviour {

    int coordinateX;
    int coordinateY;

    public abstract Worker paint(Graphics g);

    Worker(int x, int y) {
        this.coordinateX = x;
        this.coordinateY = y;
    }

    @Override
    public int getX() {
        return coordinateX;
    }

    @Override
    public int getY() {
        return coordinateY;
    }

    @Override
    public void setX(int x) {
        this.coordinateX = x;
    }

    @Override
    public void setY(int y) {
        this.coordinateY = y;
    }
}
