package object_orienters;

public class Task extends Thread {

    private Runnable action;
    private int processID;

    public Task(int processID, Runnable action) {
        this.action = action;
        this.processID = processID;

    }

    public Task(int processID) {
        this.processID = processID;
    }

    public int getProcessID() {
        return processID;
    }

    public void setAction(Runnable action) {
        this.action = action;
    }

    @Override
    public void run() {
        if (this.action != null) {
            this.action.run();
        }
    }
}
