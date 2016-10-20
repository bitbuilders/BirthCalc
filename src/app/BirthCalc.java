package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BirthCalc {
	static String[] current;
	static int cYear;
	static int cMonth;
	static int cDay;
	static int bYear;
	static int bMonth;
	static int bDay;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the best Birthday Calculator on the planet!");
		currentDay(reader);
	}

	public static void currentDay(BufferedReader reader) throws IOException {

		boolean isValid = false;
		do {
			System.out.print("Enter in today's NUMERIC year, month, and day. Example: 1994,3,21 ");
			String input = reader.readLine();
			if (input.contains(" ")) {
				input = input.replace(" ", "");
			}
			if (input.length() >= 8 && input.contains(",") && !input.endsWith(",") && (input.lastIndexOf(",") == 6 || input.lastIndexOf(",") == 7)) {
				if (!input.contains(".")) {
				current = input.split(",");
				cYear = Integer.parseInt(current[0]);
				cMonth = Integer.parseInt(current[1]);
				cDay = Integer.parseInt(current[2]);
				isValid = true;
				}
				else {
					isValid = false;
					System.out.println("ERROR: No decimals!");
				}
			}
			else {
				System.out.println("ERROR: You need to seperate all three numbers with commas.");
				isValid = false;
			}
			if (cYear < 1000 || cYear > 3000) {
				System.out.println("ERROR: The year has to be greater than 1000 and less than 3000.");
				isValid = false;
			}
			if (cMonth < 1 || cMonth > 12) {
				System.out.println("ERROR: You have to enter a numeric month between 1 and 12.");
				isValid = false;
			}
			if (!validDay(cMonth, cDay)) {
				System.out.println("ERROR: You need to enter a valid day from that month, or your month number is invalid.");
				isValid = false;
			}
		}while(!isValid);
		birthDay(reader);
	}

	public static void birthDay(BufferedReader reader) throws IOException {

		boolean isValid = false;
		do {
			System.out.print("Enter in your NUMERIC BIRTHDAY year, month, and day. Example: 1994,3,21 ");
			String input = reader.readLine();
			if (input.contains(" ")) {
				input = input.replace(" ", "");
			}
			if (input.length() >= 8 && input.contains(",") && !input.endsWith(",") && (input.lastIndexOf(",") == 6 || input.lastIndexOf(",") == 7)) {
				if (!input.contains(".")) {
				current = input.split(",");
				bYear = Integer.parseInt(current[0]);
				bMonth = Integer.parseInt(current[1]);
				bDay = Integer.parseInt(current[2]);
				isValid = true;
				}
				else {
					isValid = false;
					System.out.println("ERROR: No decimals!");
				}
			}
			else {
				System.out.println("ERROR: You need to seperate all three numbers with commas.");
				isValid = false;
			}
			if (bYear < 100 || bYear > cYear) {
				System.out.println("ERROR: The year has to be greater than 1000 and less than the current year.");
				isValid = false;
			}
			else {
				if ((bMonth > cMonth || bDay > cDay) && bYear >= cYear) {
					System.out.println("ERROR: Your birth month and day have to be less than the current month and day.");
					isValid = false;
				}
			}
			if (bMonth < 1 || bMonth > 12) {
				System.out.println("ERROR: You have to enter a numeric month between 1 and 12.");
				isValid = false;
			}
			if (!validDay(bMonth, bDay)) {
				System.out.println("ERROR: You need to enter a valid day from that month, or your month number is invalid.");
				isValid = false;
			}
		}while(!isValid);

		System.out.println("Today is '" + getMonth(cMonth) + " " + cDay + ", " + cYear + "', and your birthday is '" + getMonth(bMonth) + " "
				+ bDay + ", " + bYear + "'.");
		
		if (cMonth > bMonth) {
			System.out.println("Your are " + (cYear - bYear) + " years old.");
			System.out.println(isOlder());
		}
		else if (cMonth < bMonth) {
			System.out.println("You are " + ((cYear - bYear) - 1) + " year old");
			System.out.println(isOlder());
		}
		else {
			if (cDay > bDay && cMonth == bMonth) {
				System.out.println("You are " + (cYear - bYear) + " years old");
				System.out.println(isOlder());
			}
			else if (cDay < bDay && cMonth == bMonth) {
				System.out.println("You are " + ((cYear - bYear) - 1) + " year old");
				System.out.println(isOlder());
			}
			else if (cDay == bDay) {
				System.out.println("Happy birthday! You are " + (cYear - bYear) + " years old");
				System.out.println(isOlder());
			}
		}

		boolean valid = false;
		do {
			System.out.print("Are you finished with this birthday calculator? (yes, or no) ");
			String repeat = reader.readLine();
			repeat = repeat.replace(" ", "");
			if (repeat.equalsIgnoreCase("yes")) {
				valid = true;
				System.out.print("Closing application...");
			}
			else if (repeat.equalsIgnoreCase("no")) {
				valid = true;
				currentDay(reader);
			}
			else {
				System.out.println("ERROR: You need to enter yes or no. ");
			}
		}while(!valid);
	}
	
	public static String isOlder() {
		if (bYear == 1998) {
			if (cMonth >= bMonth) {
				if (cMonth == bMonth && cDay >= bDay) {
					if (cMonth <= 6) {
						if (cDay < 3 && cMonth == 6) {
							return "You are older than me";
						}
						else if (cMonth < 6) {
							return "You are older than me";
						}
						else{
							return "You are the same age as me";
						}
					}
				}
					else if (cMonth > 6) {
						if (cMonth < bMonth) {
							return "You are younger than me";
						}
						else {
							if (cMonth == bMonth) {
								if (cDay >= bDay) {
									return "You are the same age as me";
								}
								else {
									return "You are younger than me";
								}
							}
							else {
								return "You are younger than me";
							}
						}
					}
				}
			else {
				if (cMonth < 6) {
					return "You are the same age as me";
				}
				else {
					return "You are younger than me";
				}
			}
		}
		else if (bYear < 1998) {
			if (cMonth <= bMonth) {
				if (cDay < bDay && cMonth == bMonth) {
					if (cMonth >= 6) {
						return "You are the same age as me";
					}
					else {
						return "You are older than me";
					}
				}
				else if (cMonth < bMonth) {
					if (cMonth >= 6) {
						return "You are the same age as me";
					}
					else {
						return "You are older than me";
					}
				}
			}
			else {
				return "You are older than me";
			}
		}
		else {
			if (cMonth >= bMonth) {
				if (cDay >= bDay && cMonth == bMonth && cMonth < 6) {
					return "You are the same age as me";
				}
				else if (cMonth > bMonth) {
					if (cMonth < 6) {
						return "You are the same age as me";
					}
					else {
						return "You are younger than me";
					}
				}
			}
			else {
				return "You are younger than me";
			}
		}
		return "";
	}

	public static boolean validDay(int month, int day) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if (day > 0 && day < 32)
				return true;
			else
				return false;
		case 4:
		case 6:
		case 9:
		case 11:
			if (day > 0 && day < 31)
				return true;
			else
				return false;
		case 2:
			if (day > 0 && day < 29)
				return true;
			else 
				return false;
		default:
			return false;
		}
	}

	public static String getMonth(int month) {
		switch (month) {
		case 1:
			return "January";
		case 3:
			return "March";
		case 5:
			return "May";
		case 7:
			return "July";
		case 8:
			return "August";
		case 10:
			return "October";
		case 12:
			return "December";
		case 4:
			return "April";
		case 6:
			return "June";
		case 9:
			return "September";
		case 11:
			return "November";
		case 2:
			return "Febuary";
		default:
			return "";
		}
	}
}
