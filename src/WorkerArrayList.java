import java.util.ArrayList;

public class WorkerArrayList {

    private static WorkerArrayList instance;

    ArrayList <Worker> arrayWorkerList;

    private WorkerArrayList() {
        arrayWorkerList = new ArrayList<>();
    }

    public static WorkerArrayList getInstance() {
        if (null == instance) {
            instance = new WorkerArrayList();
        }
        return instance;
    }

}
