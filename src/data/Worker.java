package data;

import java.awt.*;
import java.util.UUID;

public abstract class Worker implements IBehaviour {

    private int coordinateX;
    private int coordinateY;
    private UUID id = UUID.randomUUID();

    public UUID getId() {
        return id;
    }

    public abstract void paint(Graphics g);

    void checkId() {
        for (UUID i : WorkerCollections.getInstance().idTreeSet) {
            if (this.id == i) {
                this.id = UUID.randomUUID();
                checkId();
            }
        }
    }

    Worker(int x, int y) {
        this.coordinateX = x;
        this.coordinateY = y;
        checkId();
        WorkerCollections.getInstance().idTreeSet.add(this.id);
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
