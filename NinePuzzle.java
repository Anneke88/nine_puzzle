import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class NinePuzzle {	
  public static void main(String[] args) throws FileNotFoundException {
	//lees uit text leer
    Scanner input = new Scanner(args[0]);
    Scanner gebruiker = new Scanner(System.in);//gebruiker se skuiwe
    boolean solved = false;
    int count = 0;
	int[] current = new int[9];
	int[] finaal = new int[9];
	String[] userInputs = new String[1];
	String filename = input.nextLine();
	setUpArray(current, finaal, userInputs, filename);
		
	String message = "Hallo ruan";
	JOptionPane.showMessageDialog(null,message);
    while ( !solved ) {
	  puzzle(current); // print current puzzle
	  int lees = 0;
	  System.out.println("wat is u volgende skuif");
      lees = gebruiker.nextInt(); //probleem
      if ( lees != 0 ) {
        move_index(lees, current);
        count++;
		solved = compare_solution(finaal, current);
		userInputs[0] += Integer.toString(lees) + ",";
      } else {
        writeArrayToFile(current, finaal, userInputs[0], filename);
        //stoor na txt file as begin waardes
	    solved = true;
      }//end if
	  //  System.out.println(compare_solution(finaal, current));		
	}//end while	
    System.out.println("Geluk jy het die ` gesolve");
    System.out.println("jy het" + " " + count + " " 
	+ "skuiwe gemaak om die puzzle klaar te maak");
}//end main
	
public static void writeArrayToFile(int [] current,
                                    int [] finaal,
 									String userinputs,
									String filename) 
									throws FileNotFoundException {
  //File File = new File(filename);	
  try {
    PrintWriter outputStream = new PrintWriter(filename);
	for ( int k = 0; k < 9; k++ ) {
	  if ( k != 8 ) {
		//print write the current puzzle
	    outputStream.print(current[k] + ","); 
	  } else {
	    outputStream.print(current[k] + "\r\n");
	  }
	}
	for(int h = 0; h < 9; h++ ) {
	  if ( h != 8 ) {
	    outputStream.print(finaal[h] + ","); //print write the current puzzle
	  } else {
	    outputStream.print(finaal[h] + "\r\n");
	  }			  
	}
	if ( userinputs.length() > 0 ) {
	  String user_inputs = userinputs.substring(0, userinputs.length() - 1) 
	                       + "\r\n";
      outputStream.println(user_inputs);
	}
	outputStream.close();
	System.out.println("your puzzle has been stored");
    //end try
  } catch (FileNotFoundException e) {
    e.printStackTrace();
  }//end catch
}	
//read csv file into an array
public static void setUpArray(int [] current, int [] finaal, String [] userinputs, String filename) throws FileNotFoundException {
  File File = new File(filename);	
		try {
		  Scanner inputStream = new Scanner(File);
		  int lines_read = 0;
			
			while (inputStream.hasNext() ) {
				String data = inputStream.next();//gets a whole line
				String [] values = data.split(",");
				if ( lines_read == 0 ) {
				  for ( int b = 0; b < 9; b++ ) {
				    current[b] = Integer.parseInt(values[b]);
				  }
				} else if ( lines_read == 1 ) {
				  for ( int b = 0; b < 9; b++ ) {
				    finaal[b] = Integer.parseInt(values[b]);
				  }
				} else if ( lines_read == 2 ) {
				  userinputs[0] = data + ",";
				}
				lines_read++;	
			}	
			inputStream.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}		
	//end setuparray
	//print puzzle
	public static int puzzle(int [] current) {
      for(int k = 0; k < 9; k++) {
        System.out.print(current[k] +" ");
          if ( ((k+1)%3) == 0 ) {
           System.out.print("\n");
		  }//end if
        }//end for
      return 0;
	}//end puzzle
	
//swap the index of the tile that has been moved
public static boolean move_index(int lees, int [] current) {
  int[][] lookup = {{1, 3, -1, -1},
	 		   {0, 2, 4, -1},
	 		   {1, 5, -1, -1},
	 		   {0, 4, 6, -1},
	 		   {1, 3, 5, 7},
	 		   {2, 4, 8, -1},
	 		   {3, 7, -1, -1},
	 		   {4, 6, 8, -1},
	 		   {5, 7, -1, -1}};
	
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
public static boolean compare_solution(int [] finaal, int [] current) {
  for ( int i = 0; i < 9; i++) {
    if ( finaal[i] != current[i] ) {
      return false;
	}
  }
  return true;
}//end compare
}//end class