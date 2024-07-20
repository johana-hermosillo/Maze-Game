//Johana Hermosillo
/*[CS1101] Comprehensive Lab 2
This work is to be done individually. It is not permitted to
share, reproduce, or alter any part of this assignment for any
purpose. Students are not permitted to share code, upload
this assignment online in any form, or view/receive/receive
nodifying code written by anyone else. This assignmentis part
of an academic course at The University of Texas at El Paso and 
a grade will be assigned for the work produced individually by 
the student.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CL2_Hermosillo{

	//global the variables
	static char[][] arr = new char [0][0];
	static int [] startPosition = new int[0];
	static int [] finishPosition = new int [0];
	static int playerRow = 0;
	static int playerCol = 0;

	public static void main(String[] args){

		//Scanner that reads the user input
		Scanner user = new Scanner(System.in);

		//Ask the user for maze option
		System.out.println("Which maze do you want to escape from?");
		System.out.println("Choose between maze1.txt, maze2.txt or maze3.txt");
		String whichMaze = user.next();

		//call all the methods
		createSquareMaze(whichMaze);
		loadMaze(whichMaze);
		findStartPosition();

		while(true){
			displayMaze();
			System.out.println("Enter your move (W/A/S/D): ");
			char direction = user.next().charAt(0);
			movePlayer(direction);

			if(arr[playerRow][playerCol] == 'F'){
				System.out.println("Congratulations you win!");
				break;
			}
		}

	}

	//Read the maze from the provided file and get its size.
	public static void createSquareMaze(String whichMaze){
		try{
			File myFile = new File(whichMaze);
			Scanner scannerCreate = new Scanner(myFile);

			int size = 0;
			while(scannerCreate.hasNextLine()){
				size++;
				scannerCreate.nextLine();
			}
			arr = new char[size][size];			

		}catch(FileNotFoundException e){
			System.out.println("an error occurred reading the file");
		}

	}

	//Read the maze from the maze file provided by the user
	public static void loadMaze(String whichMaze){
		try{
			File myFile = new File(whichMaze);
			Scanner scannerLoad = new Scanner(myFile);

			//traverse through the array
			while(scannerLoad.hasNextLine()){
				for(int size1 = 0; size1 < arr.length; size1++){
					String line = scannerLoad.nextLine();
					for(int size2 = 0; size2 < arr[size1].length; size2++){
						arr[size1][size2] = line.charAt(size2);
					}
				}
			}

		}catch(FileNotFoundException e){
			System.out.println("an error occurred reading the file");
		}

	}

	//Traverse the array to find the 'S' which indicates the starting position
	//The user position should be updated to reflect this position
	public static void findStartPosition(){
		//initialize where the Row and the Column is going to start
		int startRow = 0;
		int startColumn = 0;

		//Find the 'S' 
		char start = 'S';
		for(int size1 = 0; size1 < arr.length; size1++){
			for(int size2 = 0; size2 < arr[size1].length; size2++){
				if(arr[size1][size2] == start){
					playerRow = size1;
					playerCol = size2;
					startRow = size1;
					startColumn = size2;
					return;
				}
			}
		}	
		startPosition[0] = startRow;
		startPosition[1] = startColumn;
	}

	//Traverse the array to find the 'F' which indicates the finishing position
	//The user position should be updated to reflect this position
	public static void findFinishPosition(){
		//initialize where the Row and the Column is going to finish
		int finishRow = 0;
		int finishColumn = 0;

		//Find the 'F'
		char finish = 'F';
		for(int size1 = 0; size1 < arr.length; size1++){
			for(int size2 = 0; size2 < arr[size1].length; size2++){
				if(arr[size1][size2] == finish){
					playerRow = size1;
					playerCol = size2;
					finishRow = size1;
					finishColumn = size2;
					return;
				}
			}
		}
		finishPosition[0] = finishRow;
		finishPosition[1] = finishColumn;
	}

	
	public static void displayMaze(){
		for(int size1 = 0; size1 < arr.length; size1++){
			for(int size2 = 0; size2 < arr[size1].length; size2++){
				if(size1 == playerRow && size2 == playerCol){
					System.out.print('P');
				}else {
					System.out.print(arr[size1][size2]);
				}

			}
			System.out.println();
		}
	}

	public static boolean movePlayer(char direction){
			int newSize1 = playerRow;
			int newSize2 = playerCol;

			switch(direction){
				case 'W':
					newSize1--;
					break;
				case 'A':
					newSize2--;
					break;
				case 'S':
					newSize1++;
					break;
				case 'D':
					newSize2++;
					break;
				default:
					System.out.println("Invalid direction.");
					return false;
			}

			if(newSize1 < 0 || newSize1 >= arr.length || newSize2 >= arr.length || arr[newSize1][newSize2] == '#'){
				System.out.println("You cannot go through here.");
				return false;
			}
			playerRow = newSize1;
			playerCol = newSize2;
			return true;

	}

	public static boolean isValidMove(int size1, int size2){
		return size1 >= 0 && size1 < arr.length && size2 >= 0 && size2 < arr[0].length && arr[size1][size2] != '#';
 	}

}