package data;

public class DeveloperAI extends BaseAI {

    public DeveloperAI() {
        super("DeveloperThread");
    }

    @Override
    void nextStep() {
       // System.out.println(super.threadName);
        synchronized (WorkerCollections.getInstance().arrayWorkerList) {
            for (Worker worker : WorkerCollections.getInstance().arrayWorkerList) {
                if (worker instanceof Developers) {
                    worker.move();
                }
            }
        }
    }
}
