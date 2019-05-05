package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class WorkerArrayList {

    private static WorkerArrayList instance;

    public ArrayList <Worker> arrayWorkerList;
    public TreeSet <Worker> idTreeset;
    public HashMap <Long, Worker> bornHashMap;

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
