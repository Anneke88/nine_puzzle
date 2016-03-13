import java.util.Scanner;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.lang.IllegalStateException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.Path;
//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import java.io.PrintWriter;

public class NinePuzzle 
{	
	public static void main(String[] args)
	{	//Scanner input = new Scanner(Paths.get("begin_waardes.txt"));
		Scanner input = new Scanner(System.in);//lees uit text leer
		Scanner gebruiker = new Scanner(System.in);//gebruiker se skuiwe
		
		boolean solved = false;
		int count =0;
	
		int[] current = new int[9];
		int[] finaal = new int[9];
		
		for(int i = 0; i < 9;i++)
		{
			//current[i] = i;
			current[i] = input.nextInt();
			//puzzle(current);
		}
		for(int j = 0; j < 9;j++)
		{
			finaal[j] = input.nextInt();
		}
		for(int m = 0 ;m < 2;m++)
		{
			count = input.nextInt();
		}
		
		input.close();
		/* int[] current = {0,1,2,
                          3,4,5,
                          6,7,8};

		
		 int [] finaal = {1,0,2,
				 		  3,4,5,
				 		  6,7,8};	*/	
		
		//System.out.println("current" +puzzle(current));
		//System.out.println("finaal" +puzzle(finaal));
		while(!solved) {
		  puzzle(current);
			int lees ;
		  System.out.println("wat is u volgende skuif");
		    lees = gebruiker.nextInt(); //probleem
		    if(lees != 0)
		    {
		    	move_index(lees, current);
		    	count++;
		    	solved=compare_solution(finaal, current);
		    }
		    else
		    {
		    	 //stoor na txt file as begin waardes
		    	String filename = "begin_waardes.txt"; // wat is the file name's name 
		    	try {
					PrintWriter outputStream = new PrintWriter(filename);
					
					for(int k = 0;k < 9;k++)
					{
						outputStream.println(current[k]); //print write the current puzzle
					}
					for(int h = 0; h < 9;h++ )
					{
						outputStream.println(finaal[h]);	//write the final puzzle
					}
					for(int n = 0 ; n < 2; n++)
					{
						outputStream.println(count); //write how many moves are made
					}
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
		System.out.println("jy het"+ " "+count +" "+ "skuiwe gemaak om die puzzle klaar te maak");
	}//end main
	
	
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
