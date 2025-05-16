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
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (criminals[j].getName().compareTo(criminals[j + 1].getName()) > 0) {

                    Criminal temp = criminals[j];
                    criminals[j] = criminals[j + 1];
                    criminals[j + 1] = temp;
                }
            }
        }
    }

    // Sort criminals by crime priority
    public static void sortByCrimePriority(Criminal[] criminals, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (getCrimePriority(criminals[j].getCrimeType()) <
                        getCrimePriority(criminals[j + 1].getCrimeType())) {
                    // Swap criminals
                    Criminal temp = criminals[j];
                    criminals[j] = criminals[j + 1];
                    criminals[j + 1] = temp;
                }
            }
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
