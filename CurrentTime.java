/**
 * @author Victor Bieniek
 * CSC 201
 * Professor Tanes Kanchanawanchai
 * Exercise 2.8
 * This class asks for the time offset from GMT that you want and gives you that time
 * 
 * The main of this class will give you the command line version of the Project
*/
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CurrentTime
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String input;

		System.out.println("\n\n##################################");
		System.out.println("#                                #");
		System.out.println("#     CurrentTime Java Class     #");
		System.out.println("#                                #");
		System.out.println("##################################\n\n");

		while(true)
		{
			System.out.print("Enter the timezone offset to GMT: ");
			input = scan.next();
			if(isInt(input))
			{
				break;
			}
			System.out.println("Invalid Input\nPlease enter a valid number");
		}
		
		System.out.println(offsetTime(getUTCTime(), Integer.parseInt(input)));
		System.out.println();
	}

	public static String getUTCTime()
	{
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		return utc.format(DateTimeFormatter.ofPattern("hh:mm:ss"));

	}

	public static String offsetTime(String time, int offset)
	{
		int hour = Integer.parseInt(time.substring(0,2));
		hour += offset;

		if(hour > 12)
		{
			hour -= 12;
		}
		else if(hour < 1)
		{
			hour += 12;
		}

		return hour + time.substring(2);
	}

	public static boolean isInt(String str)
	{
		try{
			Integer.parseInt(str);
			return true;
		} catch(Exception e){
			return false;
		}
	}
}//end of class