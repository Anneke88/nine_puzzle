import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class NinePuzzle {	
  public static void main(String[] args) throws FileNotFoundException {
	//scanner to read out of txt file
    Scanner input = new Scanner(args[0]);
	//gebruiker se skuiwe
    Scanner gebruiker = new Scanner(System.in);
    boolean solved = false;
    int count = 0;
	//create arrays
	int[] current = new int[9];
	int[] finaal = new int[9];
	//array for the string read in csv file
	String[] userInputs = new String[1];
	String filename = input.nextLine();
	//call method that reads the csv file
	setUpArray(current, finaal, userInputs, filename);	
	//display how the game is played
	String message = "How to play: \n Type in the number that you want to \n move to the blank space (B).  If you \n want to exit the game type '0' then the \n program will store your current nine-puzzle \n and you can continue at another time";
	JOptionPane.showMessageDialog(null,message);
	//while loop to see if the user has finished playing
    while ( !solved ) {
	  puzzle(current); // print current puzzle
	  int lees = 0;
	  //ask user what his next move is going to be
	  System.out.println("wat is u volgende skuif");
      lees = gebruiker.nextInt();
	  //test if the input from user is 0 or any other number between 1 - 8
      if ( lees != 0 ) {
		//move the index of the tiles
        move_index(lees, current);
        count++;
		solved = compare_solution(finaal, current);
		userInputs[0] += Integer.toString(lees) + ",";
	  //if the user enters 0
      } else {
	    //write current puzzle to csv file
        writeArrayToFile(current, finaal, userInputs[0], filename);
        //stoor na txt file as begin waardes
	    solved = true;
      }//end if
      //show the final puzzle to the user	  
	 /* if(lees == 99)
	  {
		  String message3 = Integer.parseString(puzzle(finaal));
		  JOptionPane.showMessageDialog(null,message3);
	  }*/
	}//end while	
    //show message that say he has solved  the puzzle
	String message1 = "Congradulations you have solved the puzzle in   moves";
	JOptionPane.showMessageDialog(null,message1);
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
		// write the current puzzle to file
		if(outputStream.print(current[k]) == 0){
		 outputStream.print(current[k]) = b;
	  } else {	
	      outputStream.print(current[k] + ","); 
		}
	  } else {
	      outputStream.print(current[k] + "\r\n");
	  }
	}
	for(int h = 0; h < 9; h++ ) {
	  if ( h != 8 ) {
		//write the finale puzzle to file
	      if(finaal[h] == 0){
		    outputStream.print(finaal[h]) = b;
	      } else {
	    outputStream.print(finaal[h] + ","); 
	  } else {
	    outputStream.print(finaal[h] + "\r\n");
	  }//end else
	  }//end if		  
	}//end for
	if ( userinputs.length() > 0 ) {
	  String user_inputs = userinputs.substring(0, userinputs.length() - 1) 
	                       + "\r\n";
      outputStream.println(user_inputs);
	}
	outputStream.close();
	String message2 = "Your puzzle has been saved";
	JOptionPane.showMessageDialog(null,message2);
	
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
				  for ( int v = 0; v < 9; v++ ) {
					  if (values[v] == b){
						  current[v] = 0;
						  //end if
					  } else {
				          current[v] = Integer.parseInt(values[v]);
				      }//end else
					//end for  
				} else if ( lines_read == 1 ) {
				  for ( int w = 0; w < 9; w++ ) {
					  if (values[w] == b){
					    finaal[w] = 0;
						//end values[w] = b
					  } else {
				    finaal[w] = Integer.parseInt(values[w]);
				  }//end else
				  //end for
				} else if ( lines_read == 2 ) {
				  userinputs[0] = data + ",";
				}//end if lines_read 2
				lines_read++;	
			}	//end if lines_read =1
			inputStream.close();
			//end if
			}
			}//end while
		}//end try
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
			
}//end setuparray
	//print puzzle
	public static int puzzle(int [] current) {
      for(int k = 0; k < 9; k++) {
        System.out.print(current[k] +" ");
		if(current[k] == 0)
			System.out.print("b");
		else{
          if ( ((k+1)%3) == 0 ) {
           System.out.print("\n");
		  }
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