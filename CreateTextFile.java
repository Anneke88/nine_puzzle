import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateTextFile
{
	private static Formatter output;//output text to a file
	
	public static void main(String[] args)
	{
		openFile(); /*om die file oop te maak*/
		addRecords();
		closeFile();
				
	}
	
	//open file nine.txt
	public static void openFile()
	{
		try
		{
			output = new Formatter("ninepuzzle.txt"); //open the file
		}
		catch(SecurityException securityException)
		{
			System.err.println("Write permission denied. Teminating.");
			System.exit(1); //terminate the program
		}
		catch(FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file. Teminating.");
			System.exit(1); //terminate the program
		}
	}
	
	//add records to file
	public static void addRecords()
	{
		Scanner input = new Scanner(System.in);
		
		while(input.hasNext())
		{
			try
			{
				//output new record to file; assumes valid input
				output.format("%d",input.nextInt());
			}
			catch (FormatterClosedException formatterClosedException)
			{
				System.err.println("Error writing to file. terminating.");
				break;
			}
			catch(NoSuchElementException elementException)
			{
				System.err.println("Invalid input. Please try again");
				input.nextLine(); // discard input so user can try again
			}
			
			System.out.print("?");
		}//end while
	}//end method addrecords
		
	//close file
	public static void closeFile()
	{
		if(output != null)
			output.close();
	}
}
