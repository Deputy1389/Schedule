/**
 * Represents a calender with a collection of events
 * 
 * @author Sean Gallagher
 */

import java.util.ArrayList;
import java.util.Collections;


public class Calendar {
	static ArrayList<Event> events = new ArrayList<Event>();
	
	/*
	 * Adds an event to the event collection
	 * 
	 * O(1) because add works by appending the object to the end 
	 * so it is a constant time operation
	 * 
	 * @param event the event to be added to the calendar
	 */
	public void addEvent(Event event){
		events.add(event);
		System.out.println("Event added: \n"+event);
	}
	/*
	 * 
	 * Remove an event from the event collection
	 * 
	 * O(n) because it iterates through all n elements of the arraylist which takes linear time
	 * and then compares which is constant. After an element is removed the arraylist needs to shift which would be linear time. 
	 * the total would be 2n but that's still O(n)
	 * 
	 * @param event	the event to be removed from the calendar
	 */
	public void removeEvent(Event event){
		for(int i=0; i<events.size(); i++){
			if(true == events.get(i).checkClone(event))
				events.remove(i);
		}
	}
	
	/*
	 * 
	 * Prints every event in order
	 * 
	 * O(n) because it iterates through all n elements of the arraylist and takes constant time to print each
	 */
	public void printRemainingAgendaForTheWeek(){
		Collections.sort(events);
		
		for(int i=0; i<events.size(); i++){
			if(events.get(i).curWeek()){
				System.out.println(events.get(i).toString());
			}
		}
	}
}
