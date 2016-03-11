import java.io.IOException;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadTextFile
{

	public static void main(String[] args)
	{
		openFile(); /*om die file oop te maak*/
		readRecord();
		closeFile();
	}
	
	//open file nine.txt
	public static void openFile()
	{
		try
		{
			Scanner input = new Scanner(Paths.get("ninepuzzle.txt")); //open the file
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

public class ReadTextFile {

}
