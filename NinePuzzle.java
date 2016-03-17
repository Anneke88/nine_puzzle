import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class NinePuzzle {  
  public static void main(String[] args) throws FileNotFoundException {
    // scanner to read out of txt file
    Scanner input = new Scanner(args[0]);
    // gebruiker se skuiwe
    Scanner gebruiker = new Scanner(System.in);
    boolean solved = false;
    boolean save = false;
    int count = 0;
    // create arrays
    int[] current = new int[9];
    int[] finaal = new int[9];
    // array for the string read in csv file
    String[] userInputs = new String[1];
    String filename = input.nextLine();
    // call method that reads the csv file
    setUpArray(current, finaal, userInputs, filename);  
    // display how the game is played
    String message = "How to play: \n" +
	                 "Type in the number that you want to \n" +
                     "move to the blank space (b).  If you \n"+
					 "want to save your puzzle and exit the \n"+
					 "game type '0' then the program will \n"+
					 "store your current nine-puzzle \n" +
                     "then you can continue at another time \n"+
					 "If you want to view the finale puzzle type '99'";
    JOptionPane.showMessageDialog(null, message);
	System.out.println("new player? y or n");
	String newUser = gebruiker.nextLine();
	String yn = newUser.substring(0, 1);
	if (yn.equals("y") ||  yn.equals("Y")) {
	  userInputs[0] = "";
      writeArrayToFile(current, finaal, userInputs[0], filename);
	}
	count = userInputs.length;
    while ( !solved && !save) {
      System.out.println("Current Puzzle");
      puzzle(current); // print current puzzle
      int lees = 0;
      // ask user what his next move is going to be
      System.out.println("What is your next move?");
      lees = gebruiker.nextInt();
      // test if the input from user is 0 or any other number between 1 - 8
      if ( lees != 0 ) {
        if(lees == 99) {
          System.out.println("Goal Puzzle");
          puzzle(finaal);
          System.out.println("");
          } else {
          // move the index of the tiles
            if (move_index(lees, current)) {
              count++;
              solved = compare_solution(finaal, current);
              userInputs[0] += Integer.toString(lees) + ",";
		      writeArrayToFile(current,finaal,userInputs[0],filename);
		    }
          }  
      } else { //if the user enters 0
        //write current puzzle to csv file
        writeArrayToFile(current, finaal, userInputs[0], filename);
        //stoor na txt file as begin waardes
        save = true;
      }//end if
      //show the final puzzle to the user        
    }// end while  
    // show message that say he has solved  the puzzle
    if ( solved ) {
		count = count-1;
      String message1 ="Congradulations you have solved the puzzle in" +" "+count +" "+ "moves";
      JOptionPane.showMessageDialog(null, message1);
    }
	if(save){
	  String message2 = "Your puzzle has been saved";
      JOptionPane.showMessageDialog(null, message2);
	}
}//end main
  
  public static void writeArrayToFile(int [] current,
                                       int [] finaal,
                                    String userinputs,
                                    String filename) 
                  throws FileNotFoundException {
    try {
      PrintWriter outputStream = new PrintWriter(filename);
      for ( int k = 0; k < 9; k++ ) {
        if ( k != 8 ) {
       // write the current puzzle to file
          if(current[k] == 0) {
            outputStream.print("b,");
          } else {  
            outputStream.print(current[k] + ","); 
          }
        } else {
            outputStream.print(current[k] + "\r\n");
        }
      }
    for(int h = 0; h < 9; h++ ) {
      if (h != 8) {
        if (finaal[h] == 0) {
          outputStream.print("b,"); 
        } else {
            outputStream.print(finaal[h] + ",");
        }
      } else {
        outputStream.print(finaal[h] + "\r\n");
      }//end else
    }    
    if (userinputs.length() > 0) {
      String user_inputs = userinputs.substring(0, userinputs.length() - 1) + "\r\n";
      outputStream.println(user_inputs);
    }
    outputStream.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }//end catch
  }
  //read csv file into an array
  public static void setUpArray(int [] current, int [] finaal, 
                                String [] userinputs, String filename) 
								throws FileNotFoundException {
    File File = new File(filename);  
    try {
      Scanner inputStream = new Scanner(File);
      int lines_read = 0;
      while (inputStream.hasNext() ) {
        String data = inputStream.next();//gets a whole line
        String [] values = data.split(",");
        if ( lines_read == 0 ) {
          for ( int v = 0; v < 9; v++ ) {
            if (values[v].equals("b")) {
              current[v] = 0;
            } else {
              current[v] = Integer.parseInt(values[v]); 
            }
          }
        } else if ( lines_read == 1 ) {
                 for ( int w = 0; w < 9; w++ ) {
                   if (values[w].equals("b")) {
                     finaal[w] = 0;
                   } else {
                       finaal[w] = Integer.parseInt(values[w]);
                   }
                 } 
        } else if ( lines_read == 2 ) {
		         if (!data.equals(null) || !data.equals("")) {
                   userinputs[0] = data + ",";
		         } else {
		             userinputs[0] = "";
		         }
        }
      lines_read++;  
    }
      inputStream.close();
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    }      
  }//end setuparray
  //print puzzle
  public static int puzzle(int [] current) {
    for(int k = 0; k < 9; k++) {   
      if (current[k] == 0) {
        System.out.print("b ");
      } else {
          System.out.print(current[k] +" ");  
      }
      if ( ((k + 1) % 3) == 0 ) {
        System.out.print("\n");
      }
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