package application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Schedule {
	// array[col][row]
	
	private String[][][] scheduleMatrix = new String[3][7][];
    private int maxNameLength = 20; // Maximum length of each name in the output

    public Schedule() {
    	 initializeScheduleMatrix();
    }
    
    private void initializeScheduleMatrix() {
        scheduleMatrix = new String[3][7][];
        for (int location = 0; location < 3; location++) {
            for (int day = 0; day < 7; day++) {
                scheduleMatrix[location][day] = new String[0]; // Initialize with an empty array
            }
        }
    }
    
    public void setSchedule(String[][][] matrix2Set) {
    	this.scheduleMatrix = matrix2Set;
    }
    
    public String[][][] getSchedule() {
    	return scheduleMatrix;
    }

    public void addPersonToSchedule(Person person) {
        int[] tempArr = person.getScheduleArray();
        String fullName = person.getFullName();
        if (person.isTowerCaptain()) {
            fullName += " (Tower Captain)";
        }
        
        int numRows = scheduleMatrix.length;
        int numCols = scheduleMatrix[0].length;

        for (int i = 0; i < tempArr.length; i++) {
            // Check if the index is within the valid range
            if (tempArr[i] >= 0 && tempArr[i] < numRows && i < numCols) {
                if (scheduleMatrix[tempArr[i]][i] == null) {
                    scheduleMatrix[tempArr[i]][i] = new String[1];
                    scheduleMatrix[tempArr[i]][i][0] = fullName;
                } else {
                    int length = scheduleMatrix[tempArr[i]][i].length;
                    String[] newArray = new String[length + 1];
                    System.arraycopy(scheduleMatrix[tempArr[i]][i], 0, newArray, 0, length);
                    newArray[length] = fullName;
                    scheduleMatrix[tempArr[i]][i] = newArray;
                }
            }
        }
    }



    public void addRosterToSchedule(LifeguardRoster roster) {

        for (int i = 0; i < roster.getList().size(); i++) {
            addPersonToSchedule(roster.getList().get(i));
        }
    }
    
    
    
    public String getFormattedSchedule() {
        StringBuilder scheduleString = new StringBuilder();
        String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        String[] beachLocations = { "Off", "Smith Point", "Cupsogue" };

        for (int row = 0; row < 7; row++) {
            scheduleString.append(daysOfWeek[row]).append("\n"); // Add the day of the week to the string

            for (int col = 0; col < 3; col++) {
                String location = beachLocations[col];
                scheduleString.append("Location: ").append(location); // Add beach location to the string
                String[] names = scheduleMatrix[col][row];

                if (names != null) {
                    int totalPeople = names.length;
                    scheduleString.append(" (").append(totalPeople).append(" people)\n"); // Add total number of people
                    for (String name : names) {
                        scheduleString.append(String.format("%-" + maxNameLength + "s\n", name)); // Add names with formatting
                    }
                } else {
                    scheduleString.append(" (0 people)\n"); // Add "0 people" for empty locations
                }
                scheduleString.append("\n"); // Add newline between locations
            }

            scheduleString.append("\n"); // Add newline between days
        }

        return scheduleString.toString();
    }


    public void printSchedule() {
        String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        String[] beachLocations = { "Off", "Smith Point", "Cupsogue" };

        for (int row = 0; row < 7; row++) {
            System.out.println(daysOfWeek[row]); // Print the day of the week for each day

            for (int col = 0; col < 3; col++) {
                String location = beachLocations[col];
                System.out.print("Location: " + location); // Print beach location
                String[] names = scheduleMatrix[col][row];

                if (names != null) {
                    int totalPeople = names.length;
                    System.out.print(" (" + totalPeople + " people)"); // Print total number of people
                    System.out.println(); // Newline before names
                    for (String name : names) {
                        System.out.printf("%-" + maxNameLength + "s\n", name); // Adjust the width as needed
                    }
                } else {
                    System.out.print(" (0 people)\n"); // Print "0 people" for empty locations
                }
                System.out.println(); // Newline between locations
            }

            System.out.println(); // Newline between days
        }
    }

	
    




	
	
	
	
	
	
	
	
	
//	
//	private String[][] scheduleMatrix = new String[3][7];
//	
//	public Schedule() {
//		
//	}
//	
//	public void addPersonToSchedule(Person person) {
//		int[] tempArr = person.getScheduleArray();
//		for(int i = 0; i < tempArr.length; i++) {
//			scheduleMatrix[tempArr[i]][i] = person.getFullName();
//		}
//		
//	}
//	
//	public void addRosterToSchedule(LifeguardRoster roster) {
//		
//		for(int i = 0; i < roster.getList().size(); i++) {
//			addPersonToSchedule(roster.getList().get(i));
//		}
//	}
//	
//	public void printSchedule() {
//	    for (int row = 0; row < 7; row++) {
//	        for (int col = 0; col < 3; col++) {
//	            String name = scheduleMatrix[col][row];
//	            if (name != null) {
//	                System.out.printf("%-20s", name); // Adjust the width as needed
//	            } else {
//	                System.out.printf("%-20s", "Empty"); // Replace "Empty" with any desired placeholder
//	            }
//	        }
//	        System.out.println(); // Move to the next row
//	    }
//	}
//
//	
	
	
}
