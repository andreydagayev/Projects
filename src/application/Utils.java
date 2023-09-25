package application;

public class Utils {
	
	public static void assignDaysOff(int[] dayOffArray, Person person2Assign) {
		
		int[] tempSchedule = person2Assign.getScheduleArray();
		int tempDay = 0;
		
		for(int i = 0; i < dayOffArray.length;i++) {
			tempDay = dayOffArray[i];
			tempSchedule[tempDay] = 0;
		}
		
	}
	
	public static void printArray(int[] array2Print) {
	    for (int i = 0; i < array2Print.length; i++) {
	        System.out.print(array2Print[i]);
	        // Print a space after each element except the last one
	        if (i < array2Print.length - 1) {
	            System.out.print(" ");
	        }
	    }
	    // Print a newline character to move to the next line
	    System.out.println();
	}

	public static boolean isEven(int number2Check) {
		if(number2Check % 2 == 0) {
			return true;
		}
		return false;
	}
	
	public static String convertSched2String(Person person) {
	    int[] tempArr = person.getScheduleArray();
	    StringBuilder stringBuilder = new StringBuilder();

	    for (int value : tempArr) {
	        switch (value) {
	            case 0:
	                stringBuilder.append("Off");
	                break;
	            case 1:
	                stringBuilder.append("Smith Point");
	                break;
	            case 2:
	                stringBuilder.append("Cupsogue");
	                break;
	            default:
	                // Handle any other values here, if needed
	                break;
	        }
	        stringBuilder.append(System.lineSeparator()); // Add a new line after each value
	    }

	    return stringBuilder.toString();
	}

	
	public static void print2dArray(String[][][] array2Print) {
	    for (int i = 0; i < array2Print.length; i++) {
	        for (int j = 0; j < array2Print[i].length; j++) {
	            for (int k = 0; k < array2Print[i][j].length; k++) {
	                System.out.println("array2Print[" + i + "][" + j + "][" + k + "] = " + array2Print[i][j][k]);
	            }
	        }
	    }
	}


}
