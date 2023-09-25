package controllers;

import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.cell.CheckBoxTableCell;
import java.util.Comparator;
import java.util.List;

import application.Database;
import application.LifeguardRoster;
import application.Person;
import application.Schedule;
import application.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class MainController {

    @FXML
    private TableView<Person> TableViewSchedule;
    @FXML
    private ListView<String> TotalLifeguards;
    @FXML
    private ListView<Person> DaysOffList;
    @FXML
    private Button SubtractYearButton;
    @FXML
    private Button AddYearButton;
    @FXML
    private Button RemoveGuardButton;
    @FXML
    private Button AddGuardButton;
    @FXML
    private Button LoadButton;
    @FXML
    private Button SaveButton;
    @FXML
    private Button ClearButton;
    @FXML
    private Button RegenButton;
    @FXML
    private Button PopulateButton;
    @FXML
    private Button ClearCacheButton;
    @FXML
    private Label SliderLabelOne;
    @FXML
    private Label SliderLabelTwo;
    @FXML
    private Label FontSizeLabel;
    @FXML
    private Label LifeguardNameLabel;
    @FXML
    private TextField LifeguardNameField;
    @FXML
    private TextField LifeguardYearField;
    @FXML
    private TableColumn<String[], String> CupsogueColumn;
    @FXML
    private TableColumn<String[], String> SmithPointColumn;
    @FXML
    private TableColumn<String[], String> OffColumn;
    @FXML
    private Slider SliderOne;
    @FXML
    private Slider SliderTwo;
    @FXML 
    private Slider FontSizeSlider;
    @FXML
    private Tab TabOne;
    @FXML
    private Tab TabTwo;
    @FXML
    private TextArea TextAreaSched;
    @FXML
    private TextArea LifeguardScheduleTextArea;
    @FXML
    private GridPane ScheduleGrid;
    @FXML
    private CheckBox AllBox;
    @FXML
    private CheckBox MondayBox;
    @FXML
    private CheckBox TuesdayBox;
    @FXML
    private CheckBox WednesdayBox;
    @FXML
    private CheckBox ThursdayBox;
    @FXML
    private CheckBox FridayBox;
    @FXML
    private CheckBox SaturdayBox;
    @FXML
    private CheckBox SundayBox;
    
    private ObservableList<Person> lifeguards = FXCollections.observableArrayList();
    
    private LifeguardRoster tester;
    
    private Schedule helpMePlease;
   
    

    public void initialize() {
    	Database.loadLifeguardList();
        tester = new LifeguardRoster(); // Initialize the LifeguardRoster object
        
        tester.setList(Database.getLifeguardList()); // Set the list from the database

        Schedule testSched = new Schedule();
        testSched.addRosterToSchedule(tester);
        helpMePlease = testSched;
        TextAreaSched.setText(testSched.getFormattedSchedule());
        populateLifeguardsListView();
       
        TotalLifeguards.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String lifeguardName = newValue.split(" \\(")[0]; // Extract lifeguard name
                LifeguardNameLabel.setText(lifeguardName);
                String scheduleString = Utils.convertSched2String(getPersonByName(lifeguardName)); // Assuming you have a method to get a Person by name
                LifeguardScheduleTextArea.setText(scheduleString);
            } else {
                LifeguardNameLabel.setText(""); // Clear the label if nothing is selected
                LifeguardScheduleTextArea.clear(); // Clear the text area if nothing is selected
            }
        });
        
        SliderOne.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            if (value == 0) {
                SliderLabelOne.setText("Auto");
            } else {
                SliderLabelOne.setText(String.valueOf(value));
            }
        });

        SliderTwo.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            if (value == 0) {
                SliderLabelTwo.setText("Auto");
            } else {
                SliderLabelTwo.setText(String.valueOf(value));
            }
        });
        
        FontSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int fontSize = newValue.intValue(); 
            FontSizeLabel.setText(String.valueOf(fontSize));
        });
        
    }
    
    public Person getPersonByName(String lifeguardName) {
	    for (Person lifeguard : tester.getList()) { // Replace with the actual name of your list
	        if (lifeguard.getFullName().equals(lifeguardName)) {
	            return lifeguard;
	        }
	    }
	    return null; // Return null if no matching lifeguard is found
	}
    
    @FXML
    private void updateUIForSelectedLifeguard() {
        String selectedLifeguardName = TotalLifeguards.getSelectionModel().getSelectedItem();
        if (selectedLifeguardName != null) {
            // Set the lifeguard's name in the label
            LifeguardNameLabel.setText(selectedLifeguardName);

            // Find the corresponding Person object in your data
            Person selectedPerson = null;
            for (Person lifeguard : tester.getList()) {
                if (lifeguard.getFullName().equals(selectedLifeguardName)) {
                    selectedPerson = lifeguard;
                    break; // Stop searching after finding the lifeguard
                }
            }

            // Check if the selectedPerson is not null (handle this accordingly)
            if (selectedPerson != null) {
                // Populate the TextArea with the converted schedule
                String convertedSchedule = Utils.convertSched2String(selectedPerson);
                LifeguardScheduleTextArea.setText(convertedSchedule);
            }
        } else {
            // Handle the case when no lifeguard is selected (clear the label and TextArea)
            LifeguardNameLabel.setText("");
            LifeguardScheduleTextArea.clear();
        }
    }




	public void regenerateSched() {
    	clearSched();
    	Schedule testSched = new Schedule();
    	System.out.println((int)SliderOne.getValue());
    	System.out.println((int)SliderTwo.getValue());
        tester.generateSchedule((int)SliderOne.getValue(),(int)SliderTwo.getValue());
        
        testSched.addRosterToSchedule(tester);
        Database.saveLifeguardList(tester.getList());
    	TextAreaSched.setText(testSched.getFormattedSchedule());  
    	populateScheduleGrid();
    }
    
    public void clearSched() {
    	TextAreaSched.clear();
    }
    
    public void loadSched() {
    	Schedule testSched = new Schedule();
        testSched.addRosterToSchedule(tester);
    	TextAreaSched.setText(testSched.getFormattedSchedule());
    }
    
    public void subtractYear() {
    	for(int i = 0; i < tester.getList().size();i++) {
    		tester.getList().get(i).decrementYear();
    	}	
    	Database.saveLifeguardList(tester.getList());
    	TotalLifeguards.getItems().clear();
    	populateLifeguardsListView();
    	loadSched();
    }
    
    public void addYear() {
    	for(int i = 0; i < tester.getList().size();i++) {
    		tester.getList().get(i).incrementYear();
    	}
    	Database.saveLifeguardList(tester.getList());
    	TotalLifeguards.getItems().clear();
    	populateLifeguardsListView();
    	loadSched();
    }
    
    public void addLifeguard() {
    	int year = 1;
    	String textRetrieved = LifeguardNameField.getText();
    	if(!LifeguardYearField.getText().isBlank()) {
    		String yearRetrieved = LifeguardYearField.getText();
        	year = Integer.parseInt(yearRetrieved);		
    	}   	
    	Person person2Add = new Person(textRetrieved,year);
    	tester.addPerson(person2Add);
    	TotalLifeguards.getItems().clear();
    	Database.saveLifeguardList(tester.getList());
    	populateLifeguardsListView();
    	loadSched();  	
    }
    
    public void setDaysOff() {
        String selectedLifeguardName = TotalLifeguards.getSelectionModel().getSelectedItem();
        
        if (selectedLifeguardName != null) {
            // Find the Person object corresponding to the selected lifeguard
            Person selectedLifeguard = null;
            for (Person lifeguard : tester.getList()) {
                if (lifeguard.getFullName().equals(selectedLifeguardName.split(" \\(")[0])) {
                    selectedLifeguard = lifeguard;
                    break;
                }
            }
            
            selectedLifeguard.resetDays();
            
            if (selectedLifeguard != null) {
            	
            	if(AllBox.isSelected()) {
            		for(int i = 0; i < 7; i++) {
            			selectedLifeguard.setScheduleDay(i, 0);
            		}
            		
            	}
            	if(MondayBox.isSelected()) {
            		selectedLifeguard.setScheduleDay(0, 0);
            	}
            	if(TuesdayBox.isSelected()) {
            		selectedLifeguard.setScheduleDay(1, 0);
            	}
            	if(WednesdayBox.isSelected()) {
            		selectedLifeguard.setScheduleDay(2, 0);
            	}
            	if(ThursdayBox.isSelected()) {
            		selectedLifeguard.setScheduleDay(3, 0);
            	}
            	if(FridayBox.isSelected()) {
            		selectedLifeguard.setScheduleDay(4, 0);
            	}
            	if(SaturdayBox.isSelected()) {
            		selectedLifeguard.setScheduleDay(5, 0);
            	}
            	if(SundayBox.isSelected()) {
            		selectedLifeguard.setScheduleDay(6, 0);
            	}
            	Utils.printArray(selectedLifeguard.getScheduleArray());
            }
            AllBox.setSelected(false);
            MondayBox.setSelected(false);
            TuesdayBox.setSelected(false);
            WednesdayBox.setSelected(false);
            ThursdayBox.setSelected(false);
            FridayBox.setSelected(false);
            SaturdayBox.setSelected(false);
            SundayBox.setSelected(false);
        }
    }

     
    public void removeLifeguard() {
        // Get the selected lifeguard from the list view
        String selectedLifeguard = TotalLifeguards.getSelectionModel().getSelectedItem();

        if (selectedLifeguard != null) {
            // Extract the lifeguard's name from the selected item (assuming it's formatted as "Name (Years)")
            String lifeguardName = selectedLifeguard.split(" \\(")[0];

            // Find the lifeguard in the list and remove them
            for (Person lifeguard : tester.getList()) {
                if (lifeguard.getFullName().equals(lifeguardName)) {
                    tester.removePerson(lifeguard);
                    break; // Stop searching after finding and removing the lifeguard
                }
            }

            // Remove the selected lifeguard from the list view
            TotalLifeguards.getItems().remove(selectedLifeguard);

            // Remove the lifeguard from the database (assuming you have a method to do this)
            Database.removeLifeguardByName(lifeguardName);

            // Refresh the schedule and list view
            loadSched();
        }
    }

    
    private void populateLifeguardsListView() {
        // Assuming lifeguardList contains the list of lifeguards
    	if(!Database.lifeguardList.isEmpty()) {
    		List<Person> sortedLifeguards = new ArrayList<>(tester.getList());

            // Sort the lifeguards by seniority (YearsOnBeach)
            sortedLifeguards.sort(Comparator.comparingInt(Person::getYearsOnBeach).reversed());

            // Populate the ListView with lifeguard names and years
            for (Person lifeguard : sortedLifeguards) {
                String fullNameAndYears = lifeguard.getFullName() + " (" + lifeguard.getYearsOnBeach() + " years)";
                TotalLifeguards.getItems().add(fullNameAndYears);
            }	
    	}
    	else {
    		TotalLifeguards.getItems().clear();
    	}
        
    }
    
    public void populateScheduleGrid() {
        
    	tester.setList(Database.getLifeguardList()); // Set the list from the database

        Schedule testSched = new Schedule();
        testSched.addRosterToSchedule(tester);
        helpMePlease = testSched;

        System.out.println("Button clicked: Populating ScheduleGrid");
        String[][][] scheduleData = helpMePlease.getSchedule();

        // Clear the ScheduleGrid before repopulating
        ScheduleGrid.getChildren().clear();

        // Print the contents of scheduleData
        	
        
        	for (int i = 0; i < scheduleData.length; i++) {
        	
            	for (int j = 0; j < scheduleData[i].length; j++) {
            	
            	String[] tempArr = scheduleData[i][j];
            	String[] tempArrTwo = scheduleData[i][j];
            	
            	for(int h = 0; h < tempArr.length; h++) {
            		String tempArrThree[] = tempArr[h].split(" ");
            		tempArrTwo[h] = tempArrThree[0];
            	}
            	String formattedText = "";
            	
            	if(Utils.isEven(tempArrTwo.length)) {
            		
            	}
            	
            	int splitAmount = 7;
                
            	for (int k = 0; k < tempArrTwo.length; k += splitAmount) {
            	    for (int p = 0; p < splitAmount && (k + p) < tempArrTwo.length; p++) {
            	        formattedText += tempArrTwo[k + p] + " ";
            	    }
            	    formattedText += "\n";
            	}

            	
                //String text = String.join("\n", scheduleData[i][j]);
                //Label testLabel = new Label(text);
            	Label testLabel = new Label(formattedText);
                testLabel.setStyle("-fx-padding: 1px; -fx-border-color: black; -fx-border-width: .3px; -fx-font-size: 12;");
                FontSizeLabel.setText("12");
                FontSizeSlider.setValue(12);
                // Set a maximum width to allow text wrapping without truncation
                testLabel.setMaxWidth(Double.MAX_VALUE);
                testLabel.setPrefWidth(958);
                testLabel.setPrefHeight(588);
                
                // Set the label to wrap text if it's too long
                testLabel.setWrapText(true);
                
                ScheduleGrid.add(testLabel, i, j);
            }
        }

        // Force a GUI refresh to ensure changes are visible
        ScheduleGrid.getParent().layout();
    }
    
    public void popScheduleWithFont() {
      
    	int fontSize = (int) FontSizeSlider.getValue();
    	tester.setList(Database.getLifeguardList()); // Set the list from the database

        Schedule testSched = new Schedule();
        testSched.addRosterToSchedule(tester);
        helpMePlease = testSched;

        System.out.println("Button clicked: Populating ScheduleGrid");
        String[][][] scheduleData = helpMePlease.getSchedule();

        // Clear the ScheduleGrid before repopulating
        ScheduleGrid.getChildren().clear();

        // Print the contents of scheduleData

        for (int i = 0; i < scheduleData.length; i++) {

            for (int j = 0; j < scheduleData[i].length; j++) {

                String[] tempArr = scheduleData[i][j];
                String[] tempArrTwo = scheduleData[i][j];

                for (int h = 0; h < tempArr.length; h++) {
                    String tempArrThree[] = tempArr[h].split(" ");
                    tempArrTwo[h] = tempArrThree[0];
                }
                String formattedText = "";

                if (Utils.isEven(tempArrTwo.length)) {

                }
                int splitAmount = 7;
               
                if(fontSize <= 10) {
                	splitAmount = 15;
                }
                
                if(fontSize > 10 && fontSize <= 15) {
                	splitAmount = 10;
                }
                
                if(fontSize > 15) {
                	splitAmount = 5;
                }

                

                for (int k = 0; k < tempArrTwo.length; k += splitAmount) {
                    for (int p = 0; p < splitAmount && (k + p) < tempArrTwo.length; p++) {
                        formattedText += tempArrTwo[k + p] + " ";
                    }
                    formattedText += "\n";
                }

                // Create a label with the specified font size
                Label testLabel = new Label(formattedText);
                testLabel.setStyle("-fx-padding: 1px; -fx-border-color: black; -fx-border-width: .3px;" + "-fx-font-size: " + fontSize + "px;");
               // testLabel.setStyle("-fx-font-size: " + fontSize + "px;"); // Set the font size

                // Set a maximum width to allow text wrapping without truncation
                testLabel.setMaxWidth(Double.MAX_VALUE);
                testLabel.setPrefWidth(958);
                testLabel.setPrefHeight(588);

                // Set the label to wrap text if it's too long
                testLabel.setWrapText(true);

                ScheduleGrid.add(testLabel, i, j);
            }
        }

        // Force a GUI refresh to ensure changes are visible
        ScheduleGrid.getParent().layout();
    }

}
