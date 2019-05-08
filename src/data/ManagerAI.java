package data;

public class ManagerAI extends BaseAI {

    public ManagerAI() {
        super("ManagerThread");
    }

    @Override
    void nextStep() {
        //System.out.println(super.threadName);
        synchronized (WorkerCollections.getInstance().arrayWorkerList) {
            for (Worker worker : WorkerCollections.getInstance().arrayWorkerList) {
                if (worker instanceof Manager) {
                    worker.move();
                }
            }
        }
    }
}
