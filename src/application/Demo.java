package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

	public class Demo extends Application {
		@Override
		public void start(Stage primaryStage) {
			try {
				 // Load the FXML file from a different package
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controllers/Main.fxml"));
	            Parent root = loader.load();

	            // Create the scene
	            Scene scene = new Scene(root);

	            // Set the scene on the stage
	            primaryStage.setScene(scene);
	            primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	

	public static void main(String[] args) {
//		
//		String line = "*****************************************************";
//		
//		Database database = new Database();
//		LifeguardRoster roster = new LifeguardRoster();
//		Schedule testSched = new Schedule();
//		
//		roster.setList(database.getLifeguardList());
//		roster.printSchedules();	
//		testSched.addRosterToSchedule(roster);
//		testSched.printSchedule();
//		System.out.println(line);
//		
//		roster.generateSchedule();
//		roster.printSchedules();	
//		testSched.addRosterToSchedule(roster);
//		testSched.printSchedule();
//		System.out.println(line);
//		
//		database.saveLifeguardList(roster.getList());
//		
	
		//roster.printSchedules();
		
//       
//		Person[] people = {
//                new Person("John", "Smith", 1),
//                new Person("Emily", "Johnson", 2),
//                new Person("Michael", "Williams", 3),
//                new Person("Sarah", "Brown", 4),
//                new Person("David", "Jones", 5),
//                new Person("Jessica", "Davis", 6),
//                new Person("Daniel", "Miller", 7),
//                new Person("Megan", "Wilson", 8),
//                new Person("William", "Moore", 9),
//                new Person("Sophia", "Taylor", 10),
//                new Person("Joseph", "Anderson", 1),
//                new Person("Olivia", "Clark", 2),
//                new Person("Christopher", "Thomas", 3),
//                new Person("Ava", "White", 4),
//                new Person("Matthew", "Hall", 5),
//                new Person("Emma", "Walker", 6),
//                new Person("Nicholas", "Lewis", 7),
//                new Person("Grace", "Young", 8),
//                new Person("Andrew", "Harris", 9),
//                new Person("Lily", "Martin", 10),
//                new Person("Ryan", "Jackson", 1),
//                new Person("Chloe", "Adams", 2),
//                new Person("James", "Thomas", 3),
//                new Person("Sofia", "Roberts", 4),
//                new Person("Ethan", "Turner", 5),
//                new Person("Isabella", "Baker", 6),
//                new Person("Benjamin", "Johnson", 7),
//                new Person("Avery", "Scott", 8),
//                new Person("Lucas", "Davis", 9),
//                new Person("Aria", "Nelson", 10),
//                new Person("Alexander", "Walker", 1),
//                new Person("Mia", "Garcia", 2),
//                new Person("Henry", "Carter", 3),
//                new Person("Madison", "Parker", 4),
//                new Person("Jackson", "Wright", 5),
//                new Person("Scarlett", "Miller", 6),
//                new Person("Liam", "Brown", 7),
//                new Person("Grace", "Williams", 8),
//                new Person("William", "Harris", 9),
//                new Person("Sophia", "Clark", 10)
//            };
//            
//            for (int i = 0; i < people.length; i++) {
//                int day = i % 7; // Assigning days cyclically (0 to 6)
//                int assignment = i % 5; // Assigning assignments cyclically (0 to 4)
//                roster.addPerson(people[i]);
//            }
//		
		//System.out.println(p1.toString());
		
		//test for days off
		
		//Utils.assignDaysOff(testDaysOff, p1);
		//System.out.println(p1.toString());
		
		
		//roster.generateSchedule();
		
		Database.loadLifeguardList();
		launch(args);
		
	}
}
