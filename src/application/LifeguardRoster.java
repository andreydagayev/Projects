package application;


import java.util.ArrayList;
import java.util.Collections;

public class LifeguardRoster {
	
	private ArrayList<Person> lifeguardList = new ArrayList<>();
	
	public LifeguardRoster() {
		lifeguardList = new ArrayList<>();
	}
	
	public void clearList() {
		for(int i = 0; i < lifeguardList.size(); i++) {
			lifeguardList.set(i, null);
		}
	}
	
	public ArrayList<Person> getList(){
		return lifeguardList;
	}
	
	public void setList(ArrayList<Person> list2Set) {
		this.lifeguardList = list2Set;
	}
	
	public void printSchedules() {
		for(int i = 0; i < lifeguardList.size(); i++) {
			System.out.println(lifeguardList.get(i).toString());
		}
	}
	
	public void addPerson(Person person2Add) {		
		lifeguardList.add(person2Add);
	}
	
	public void removePerson(Person person2Remove) {
		lifeguardList.remove(person2Remove);
	}
	
	public void shuffleList() {
		Collections.shuffle(lifeguardList);
	}
	
	

	
	public void generateSchedule(double weekdayCount, double weekendCount) {
		
		int off = 0;
		int smithPoint = 1;
		int cupsogue = 2;
		int unnasigned = 3;
		
		int tcCupRequirementWeekend = 3;
		int tcCupRequirementWeekday = 2;
		int lgCupRequirementWeekend = 9;
		int lgCupRequirementWeekday = 6;
		
		if(weekdayCount != 0 && weekendCount != 0) {
			tcCupRequirementWeekend = (int) weekendCount;
			tcCupRequirementWeekday = (int) weekdayCount;
			lgCupRequirementWeekend = (int) (weekendCount * 3);
			lgCupRequirementWeekday = (int) (weekdayCount * 3);
		}
		
		System.out.println("\n"+tcCupRequirementWeekend + "\n" + tcCupRequirementWeekday + "\n" + lgCupRequirementWeekend+"\n"+lgCupRequirementWeekday);
		
		for(int i = 0; i < 7 ; i++) { //days of week
			
			boolean isWeekend = i > 4; 
			int cupTowerCaptainCount = 0;
			int cupLifeguardCount = 0;
			
			Collections.shuffle(lifeguardList);
			
			for(int j = 0; j < lifeguardList.size(); j++) { //lifeguard list
				
				if(lifeguardList.get(j).getScheduleDay(i)!=0) {
							
					if(lifeguardList.get(j).isTowerCaptain()) {
						//is tc
						if(!isWeekend && cupTowerCaptainCount < tcCupRequirementWeekday ) {
							lifeguardList.get(j).setScheduleDay(i, cupsogue);
							cupTowerCaptainCount++;
						}
						else if(isWeekend && cupTowerCaptainCount < tcCupRequirementWeekend ) {
							lifeguardList.get(j).setScheduleDay(i, cupsogue);
							cupTowerCaptainCount++;
						}
						else {
							lifeguardList.get(j).setScheduleDay(i, smithPoint);
						}		
					}				
					else if(!lifeguardList.get(j).isTowerCaptain()){
						//not tc
						if(!isWeekend && cupLifeguardCount < lgCupRequirementWeekday) {
							lifeguardList.get(j).setScheduleDay(i, cupsogue);
							cupLifeguardCount++;
						}
						else if(isWeekend && cupLifeguardCount < lgCupRequirementWeekend) {
							lifeguardList.get(j).setScheduleDay(i, cupsogue);
							cupLifeguardCount++;
						}
						else {
							lifeguardList.get(j).setScheduleDay(i, smithPoint);
						}
					}	
					else {
						System.out.println("edge case discovered");
					}
				}
			}				
		}	
	}	
}
