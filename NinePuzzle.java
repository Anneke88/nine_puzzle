import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class NinePuzzle 
{	
	public static void main(String[] args)
	{	
		Scanner input = new Scanner(Paths.get("begin_waardes.txt"));//lees uit text leer
		Scanner gebruiker = new Scanner(System.in);//gebruiker se skuiwe
		
		boolean solved = false;
		int size = input.nextInt();
		int[] current =new int[9];
		
		for(int i = 0; i< 9;i++)
		{
			current[i] = input.nextInt();
		}
		/* int[] current = {0,1,2,
                          3,4,5,
                          6,7,8};*/

		
		 int [] finaal = {1,0,2,
				 		  3,4,5,
				 		  6,7,8};	
		 
		int lees =0 ;
		while(!solved) {
		  puzzle(current);
			
		  System.out.println("wat is u volgende skuif");
		  lees = gebruiker.nextInt();
		  move_index(lees, current);
	
	
          solved=compare_solution(finaal, current);
			
	      System.out.println(compare_solution(finaal, current));
			
		}//end while		
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
	
	
//
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

public static int get_index(int lees, int [] current) {
  for ( int i = 0; i < 9; i++ ) {
    if ( lees == current[i] ) {
	  return i;
	}
  }
  return 0;
}

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
