package DataStructure;
import ModelClasses.Criminal;
public class CriminalArray {
    private Criminal[] criminals;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public CriminalArray() {
        criminals = new Criminal[INITIAL_CAPACITY];
        size = 0;
    }

    public void insert(Criminal criminal) {
        if (size == criminals.length) {
            resize();
        }
        criminals[size++] = criminal;
    }

    private void resize() {
        Criminal[] newArray = new Criminal[criminals.length * 2];
        System.arraycopy(criminals, 0, newArray, 0, criminals.length);
        criminals = newArray;
    }

    public Criminal search(String id) {
        for (int i = 0; i < size; i++) {
            if (criminals[i].getId().equals(id)) {
                return criminals[i];
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Criminal getCriminal(int index) {
        if (index >= 0 && index < size) {
            return criminals[index];
        }
        return null;
    }

    public void sortCriminals(String sortBy) {
        if (size <= 1) {
            return;  // No need to sort if array is empty or has one element
        }

        if (sortBy.equalsIgnoreCase("id")) {
            CriminalSorter.sortByID(criminals, size);
        } else if (sortBy.equalsIgnoreCase("name")) {
            CriminalSorter.sortByName(criminals, size);
        } else if (sortBy.equalsIgnoreCase("crime")) {
            CriminalSorter.sortByCrimePriority(criminals, size);
        }
    }

    public boolean delete(String id) {
        for (int i = 0; i < size; i++) {
            if (criminals[i].getId().equals(id)) {
                // Shift remaining elements to the left
                for (int j = i; j < size - 1; j++) {
                    criminals[j] = criminals[j + 1];
                }
                criminals[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    public void displayAllCriminals() {
        if (size == 0) {
            System.out.println("No criminals in the system.");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println(criminals[i]);
            System.out.println("------------------------");
        }
    }
}
