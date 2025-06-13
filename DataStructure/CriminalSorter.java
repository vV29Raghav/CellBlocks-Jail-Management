package DataStructure;
import ModelClasses.Criminal;
public class CriminalSorter {
    public static void sortByID(Criminal[] criminals, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (criminals[j].getId().compareTo(criminals[j + 1].getId()) > 0) {
                    Criminal temp = criminals[j];
                    criminals[j] = criminals[j + 1];
                    criminals[j + 1] = temp;
                }
            }
        }
    }


    public static void sortByName(Criminal[] criminals, int size) {
        for (int i = 1; i < size; i++) {
            Criminal key = criminals[i];
            int j = i - 1;
            while (j >= 0 && criminals[j].getName().compareTo(key.getName()) > 0) {
                criminals[j + 1] = criminals[j];
                j--;
            }
            criminals[j + 1] = key;
        }
    }


    // Sort criminals by crime priority
    public static void sortByCrimePriority(Criminal[] criminals, int size) {
        if (size <= 1) return;
        mergeSort(criminals, 0, size - 1);
    }
    private static void mergeSort(Criminal[] criminals, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(criminals, left, mid);
            mergeSort(criminals, mid + 1, right);

            merge(criminals, left, mid, right);
        }
    }
    private static void merge(Criminal[] criminals, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Criminal[] L = new Criminal[n1];
        Criminal[] R = new Criminal[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = criminals[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = criminals[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (getCrimePriority(L[i].getCrimeType()) >= getCrimePriority(R[j].getCrimeType())) {
                criminals[k] = L[i];
                i++;
            } else {
                criminals[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            criminals[k++] = L[i++];
        }

        while (j < n2) {
            criminals[k++] = R[j++];
        }
    }

    // Helper method to get crime priority
    private static int getCrimePriority(String crimeType) {
        // Convert to lowercase for case-insensitive comparison
        String crime = crimeType.toLowerCase();

        // High priority crimes
        if (crime.contains("murder") || crime.contains("rape")) {
            return 3;
        }
        // Medium priority crimes
        else if (crime.contains("assault") || crime.contains("robbery") ||
                crime.contains("kidnapping")) {
            return 2;
        }
        // Low priority crimes
        else if (crime.contains("theft") || crime.contains("fraud") ||
                crime.contains("vandalism")) {
            return 1;
        }
        // Default priority
        return 0;
    }
}
