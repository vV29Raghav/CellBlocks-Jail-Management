package DataStructure;
import java.util.*;
import ModelClasses.Officer;

public class OfficerQueue {
    private Queue<Officer> officers;

    public OfficerQueue() {
        this.officers = new LinkedList<>();
    }

    public void addOfficer(Officer officer) {
        officers.offer(officer);
    }

    public Officer getNextOfficer() {
        return officers.poll();
    }

    public boolean isEmpty() {
        return officers.isEmpty();
    }

    public int size() {
        return officers.size();
    }

    public boolean deleteOfficer(int id) {
        if (isEmpty()) {
            return false;
        }

        // Create a temporary queue
        OfficerQueue tempQueue = new OfficerQueue();
        boolean found = false;

        // Copy all officers except the one to delete
        while (!isEmpty()) {
            Officer officer = getNextOfficer();
            if (officer.getId() != id) {
                tempQueue.addOfficer(officer);
            } else {
                found = true;
            }
        }

        // Restore the queue
        while (!tempQueue.isEmpty()) {
            addOfficer(tempQueue.getNextOfficer());
        }

        return found;
    }
}
