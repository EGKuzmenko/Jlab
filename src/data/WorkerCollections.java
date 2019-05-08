package data;

import java.util.*;

public class WorkerCollections {

    private static WorkerCollections instance;

    public final LinkedList<Worker> arrayWorkerList;
    public TreeSet <UUID> idTreeSet;
    public HashMap <UUID, Long> bornHashMap;

    private WorkerCollections() {
        arrayWorkerList = new LinkedList<>();
        idTreeSet = new TreeSet<>();
        bornHashMap = new HashMap<>();
    }

    public static WorkerCollections getInstance() {
        if (null == instance) {
            instance = new WorkerCollections();
        }
        return instance;
    }

    public synchronized void cleanCollections(Long time) {
        for (int i = 0; i < WorkerCollections.getInstance().arrayWorkerList.size(); i++) {
            Worker curWorker = WorkerCollections.getInstance().arrayWorkerList.get(i);
            Long curBornTime = WorkerCollections.getInstance().bornHashMap.get(curWorker.getId());
            if (curWorker instanceof Developers) {
                if (time - WorkerCollections.getInstance().bornHashMap.get(curWorker.getId()) >= Developers.liveTime) {
                    WorkerCollections.getInstance().bornHashMap.remove(curWorker.getId());
                    WorkerCollections.getInstance().idTreeSet.remove(curWorker.getId());
                    WorkerCollections.getInstance().arrayWorkerList.remove(i);
                }
            } else {
                if (time - WorkerCollections.getInstance().bornHashMap.get(curWorker.getId()) >= Manager.liveTime) {
                    WorkerCollections.getInstance().bornHashMap.remove(curWorker.getId());
                    WorkerCollections.getInstance().idTreeSet.remove(curWorker.getId());
                    WorkerCollections.getInstance().arrayWorkerList.remove(i);
                }
            }
        }
    }

    public StringBuilder liveObjString() {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < WorkerCollections.getInstance().arrayWorkerList.size(); i++) {
            Worker curWorker = WorkerCollections.getInstance().arrayWorkerList.get(i);
            if (curWorker instanceof Developers) {
                stringBuilder.append("Developers ");
            } else {
                stringBuilder.append("Managers ");
            }
            stringBuilder.append(curWorker.getId() + " ");
            stringBuilder.append(WorkerCollections.getInstance().bornHashMap.get(curWorker.getId()));
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

}
