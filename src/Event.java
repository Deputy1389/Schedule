/**
 * Represents an event with a date and two times, a start and end
 * 
 * @author Sean Gallagher
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Event implements Comparable<Event>{
	private LocalTime startTime, endTime;
	private LocalDate date;

	/*
	 * Event constructor
	 * 
	 * @param: day		the day of the event
	 * @param: start	the time the event starts
	 * @param: end		the time the event ends
	 */
	public Event(LocalDate day, LocalTime start, LocalTime end){
		date = day;
		startTime = start;
		endTime = end;
	}

	/*
	 * Formats the date and converts to a string
	 * 
	 * @return formatted date as string
	 */
	public String getDay(){
		DateTimeFormatter ft = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
		return date.format(ft);
	}	

	/*
	 * Checks if the date falls within this week
	 * 
	 * @return true if the date is this week
	 */
	public boolean curWeek(){
		LocalDate weekStart = LocalDate.now();
		LocalDate weekEnd = LocalDate.now();
		LocalDate cur = LocalDate.now();
		LocalTime curTime = LocalTime.now();
		weekStart = weekStart.with(ChronoField.DAY_OF_WEEK, 1);
		weekEnd = weekEnd.with(ChronoField.DAY_OF_WEEK, 7);
		
		if(startTime.isAfter(curTime) && date.isEqual(cur)){
			return true;
		}
		if(date.isAfter(cur) && (date.isBefore(weekEnd) || date.isEqual(weekEnd))){
				return true;
		}
		return false;
	}

	/*
	 * Getter for endTime
	 * 
	 * @return formatted time as string
	 */
	public String getEndStr(){
		return formatString(endTime);
	}

	/*
	 * Getter for startTime
	 * 
	 * @return formatted time as string
	 */
	public String getStartStr(){
		return formatString(startTime);
	}

	/*
	 * Formats the time and converts to a string
	 * 
	 * @return formatted time as string
	 */
	private String formatString(LocalTime time){
		DateTimeFormatter ft = DateTimeFormatter.ofPattern("hh:mm a");
		return time.format(ft);
	}

	/*
	 * Getter for description
	 * 
	 * @return description of event
	 */
	public String getDesc(){
		return countdown();
	}

	/*
	 * Creates a description string that tells the user how long until the event
	 * 
	 * @return description of event
	 */
	private String countdown(){
		long cmp = LocalDate.now().until(date, ChronoUnit.DAYS);
		String desc = null;
		if(0 == cmp){
			desc = "Today but later";
		}else if(cmp < 0){
			desc = "You missed the event";
		}
		else{
			if(1 == cmp)
				desc = "In " + cmp + " day";
			else
				desc = "In " + cmp + " days";
		}
		return desc;
	}

	/*
	 * Creates a string with the events information including the date, start, end and description
	 * 
	 * @return event information
	 */
	public String toString(){
		String day = "Date: " + getDay();
		String start = "Start Time: " + getStartStr();
		String end = "End Time: " + getEndStr();
		String desc = "Description: " + getDesc();
		String ret = day+ "\n" +start + "\n" + end + "\n" + desc + "\n";
		return ret;
	}

	/*
	 * Checks to see if this event is a clone of another
	 * 
	 * @return true if it is a clone, false otherwise
	 */
	public boolean checkClone(Event e){
		if(0 == this.date.compareTo(e.date)){
			if((0 == this.startTime.compareTo(e.startTime)) && (0 == this.endTime.compareTo(e.endTime))){
				return true;
			}
		}
		return false;
	}

	/*
	 * compareTo used by Collections.sort
	 * 
	 * @return the order of the objects
	 */
	@Override
	public int compareTo(Event e){
		int cmp = this.date.compareTo(e.date);
		if(0 == cmp){
			return this.startTime.compareTo(e.startTime);
		}
		return cmp;
	}
}
