/**
 * Runs a command line interface to interact with an event calendar
 * 
 * @author Sean Gallagher
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class RunCalender {
	static Calendar calend = new Calendar();
	static Scanner sc = new Scanner (System.in);

	/*
	 * Starts the program by displaying a menu and
	 * takes user input to navigate the menu
	 */
	public static void main(String[] args){
		printMenu();
		while (sc.hasNext() == true ) {

			String s1 = sc.next();
			switch(s1){
			case("1"):
				add();
				break;
			case("2"):
				remove();
				break;
			case("3"):
				print();
				break;
			case("4"):
				System.exit(0);
				break;
			default:
				continue;
			}
			printMenu();
		}
	}

	/*
	 * Prints the user menu string
	 */
	public static void printMenu() {
		System.out.println("Event Calender \nPlease choose an option \n1. Add an event \n"
				+ "2. Remove an event \n3. Display this weeks events\n4. Exit");
	}

	/*
	 * Asks the user for information about the event and creates an event object.
	 * After object creation, it is added to the calendar
	 */
	public static void add(){
		int year, month, day, hour1, min1, hour2, min2;
		String ampm;

		System.out.println("Please enter a date in the format yyyy m d \nex. 2018 3 1 for March 1, 2018");
		year = sc.nextInt();
		month = sc.nextInt();
		day = sc.nextInt();

		System.out.println("Please enter the hour, minute and indicate whether it is in the am or pm \nex 1 0 pm for 1:00 pm"
				+ " for the start time");
		hour1 = sc.nextInt();
		min1 = sc.nextInt();
		ampm = sc.next();
		if(ampm.compareTo("pm") == 0){
			hour1 += 12;
		}

		System.out.println("Please enter the end time following the same format");
		hour2 = sc.nextInt();
		min2 = sc.nextInt();
		ampm = sc.next();
		if(ampm.compareTo("pm") == 0){
			hour2 += 12;
		}

		LocalDate date = LocalDate.of(year, month, day);
		LocalTime start = LocalTime.of(hour1, min1);
		LocalTime end = LocalTime.of(hour2, min2);

		calend.addEvent(new Event(date, start, end));
	}

	/*
	 * Asks the user for the information about the event so it can be found and removed from the calendar
	 */
	public static void remove(){
		int year, month, day, hour1, min1, hour2, min2;
		String ampm;
		System.out.println("Please enter a date in the format yyyy m d \nex. 2018 3 1 for March 1, 2018");
		year = sc.nextInt();
		month = sc.nextInt();
		day = sc.nextInt();

		System.out.println("Please enter the hour, minute and indicate whether it is in the am or pm \nex 1 0 pm for 1:00 pm"
				+ " for the start time");
		hour1 = sc.nextInt();
		min1 = sc.nextInt();
		ampm = sc.next();

		if(ampm.compareTo("pm") == 0){
			hour1 += 12;
		}
		System.out.println("Please enter the end time following the same format");
		hour2 = sc.nextInt();
		min2 = sc.nextInt();
		ampm = sc.next();
		if(ampm.compareTo("pm") == 0){
			hour2 += 12;
		}

		LocalDate date = LocalDate.of(year, month, day);
		LocalTime start = LocalTime.of(hour1, min1);
		LocalTime end = LocalTime.of(hour2, min2);

		calend.removeEvent(new Event(date, start, end));

		System.out.println("Event removed\n");
	}

	/*
	 * Prints each event in order
	 */
	public static void print(){
		calend.printRemainingAgendaForTheWeek();
	}
}
