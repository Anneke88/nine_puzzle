import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.lang.IllegalStateException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.Path;
//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import java.io.PrintWriter;

public class NinePuzzle {	
  public static void main(String[] args) throws FileNotFoundException {	
    //Scanner input = new Scanner(Paths.get("begin_waardes.txt"));
    Scanner input = new Scanner(args[0]);//lees uit text leer
    Scanner gebruiker = new Scanner(System.in);//gebruiker se skuiwe
	
    boolean solved = false;
    int count = 0;
	
		int[] current = new int[9];
		int[] finaal = new int[9];
		
		setUpArray(current, finaal, input.nextLine());
		
	    while(!solved) {
		  puzzle(current); // print current puzzle
		  int lees = 0;
		  System.out.println("wat is u volgende skuif");
		  lees = gebruiker.nextInt(); //probleem
		  if(lees != 0) {
		    move_index(lees, current);
		    count++;
		    solved=compare_solution(finaal, current);
		  } else {
		    //stoor na txt file as begin waardes
		    String filename = "begin_waardes.txt"; // wat is the file name's name 
		    try {
			  PrintWriter outputStream = new PrintWriter(filename);
			  for ( int k = 0; k < 9; k++ ) {
				if ( k != 8 ) {
			      outputStream.print(current[k] + ","); //print write the current puzzle
				} else {
				  outputStream.print(current[k] + "\n");
				}
			  }
			  for(int h = 0; h < 9; h++ ) {
				if ( h != 8 ) {
			      outputStream.print(finaal[h] + ","); //print write the current puzzle
				} else {
				  outputStream.print(finaal[h] + "\n");
				}			  
			  }
			  outputStream.println(count); //write how many moves are made
			  outputStream.close();
			  System.out.println("your puzzle has been stored");
		    } //end try
		    catch (FileNotFoundException e) {
			  e.printStackTrace();
			}//end catch
		    solved = true;
		    }//end if
		
	    //  System.out.println(compare_solution(finaal, current));		
		}//end while	
		System.out.println("Geluk jy het die puzzle gesolve");
		System.out.println("jy het" + " " + count + " " + "skuiwe gemaak om die puzzle klaar te maak");
	}//end main
	
	
	
//read csv file into an array
	public static void setUpArray(int [] current, int [] finaal, String filename) throws FileNotFoundException {
		File File = new File(filename);	
		try
		{
			Scanner	inputStream = new Scanner(File);
			int lines_read = 0;
			
			while(inputStream.hasNext())
			{
				String data = inputStream.next();//gets a whole line
				String [] values = data.split(",");
				if(lines_read == 0)
				{
					for(int b = 0; b < 9;b++)
					{
						current[b] = Integer.parseInt(values[b]);
					}
				}
				else if(lines_read==1)
				{
					for(int b = 0; b < 9;b++)
					{
						finaal[b] = Integer.parseInt(values[b]);
					}
				}
				lines_read++;
				System.out.println(data);	
			}	
			inputStream.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
			/*
			  try
			 {
			 while(scanIn.hasNextLine())
			{
				String InputLine = scanIn.nextLine();//read data from csv file
				String [] inarray = InputLine.split(",");//read data into an array
				String [] outarray = InputLine.split(",");
				
				for(int g = 0; g < 9;g++)
				{
					inarray[g] = 
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}*/
		
	//end setuparray
	//print puzzle
	public static int puzzle(int [] current)
	{
		for(int k = 0; k<9;k++)
		{
			System.out.print(current[k] +" ");
			if(((k+1)%3)==0)
			{
				 System.out.print("\n");
			}//end if
		}//end for
		return 0;
	}//end puzzle
	
//swap the index of the tile that has been moved
public static boolean move_index(int lees, int [] current)
{
	int[][] lookup = {{1,3,-1,-1},
	 		   {0,2,4,-1},
	 		   {1,5,-1,-1},
	 		   {0,4,6,-1},
	 		   {1,3,5,7},
	 		   {2,4,8,-1},
	 		   {3,7,-1,-1},
	 		   {4,6,8,-1},
	 		   {5,7,-1,-1}};
	
      int zero_position = get_index(0, current);
	  int value_position = get_index(lees, current);
  for ( int i = 0; i < 4; i++) {
    if ( lookup[zero_position][i] == value_position ) {
	  current[value_position] = 0;
	  current[zero_position] = lees;
	  return true;
	}
  }
  return false;
}//end move_index

//get the index of the blank space and the number that the user want to move
public static int get_index(int lees, int [] current) {
  for ( int i = 0; i < 9; i++ ) {
    if ( lees == current[i] ) {
	  return i;
	}
  }
  return 0;
}

//compare wether the current puzzle has the same values of the final puzzle
public static boolean compare_solution(int [] finaal, int [] current)
{
  for ( int i = 0; i < 9; i++) {
    if ( finaal[i] != current[i] ) {
      return false;
	}
  }
  return true;
}//end compare
}//end class
