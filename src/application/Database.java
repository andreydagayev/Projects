package application;
import java.io.*;
import java.util.ArrayList;

public class Database {
    
    private static final String LIFEGUARD_FILE = "lifeguards.dat";
    public static ArrayList<Person> lifeguardList;

    public Database() {
        loadLifeguardList();
        
        if (lifeguardList == null) {
            lifeguardList = new ArrayList<>();
        }
    }

    public static void saveLifeguardList(ArrayList<Person> lifeguards) {
        lifeguardList = new ArrayList<>(lifeguards);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(LIFEGUARD_FILE))) {
            outputStream.writeObject(lifeguardList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Person> getLifeguardList() {
        return lifeguardList;
    }

    @SuppressWarnings("unchecked")
    public static void loadLifeguardList() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(LIFEGUARD_FILE))) {
            lifeguardList = (ArrayList<Person>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            lifeguardList = new ArrayList<>();
        }
    }

    public static void deletePerson(Person person) {
        if (lifeguardList != null) {
            lifeguardList.remove(person);
            saveLifeguardList(lifeguardList);
        }
    }

    public static void deleteAllPersons() {
        if (lifeguardList != null) {
            lifeguardList.clear();
            lifeguardList = new ArrayList<Person>();
            saveLifeguardList(lifeguardList);
        } else {
            lifeguardList = new ArrayList<>();
            saveLifeguardList(lifeguardList);
        }
    }

    public static void removeLifeguardByName(String lifeguardName) {
        if (lifeguardList != null) {
            // Iterate through the lifeguardList
            for (Person lifeguard : lifeguardList) {
                if (lifeguard.getFullName().equals(lifeguardName)) {
                    lifeguardList.remove(lifeguard); // Remove the lifeguard with the matching name
                    saveLifeguardList(lifeguardList); // Save the updated list
                    return; // Exit the loop after removing the lifeguard
                }
            }
        }
    }
    

}
