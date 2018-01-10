package adventOfCode.day19;

import java.util.ArrayList;
import java.util.List;

import util.IOUtil;

public class Day19 {
	

	
	public static void main(String[] args) {
		
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day19/input.txt");
		
		List<List<String>> maze = new ArrayList<>();
		
		for (String s : input) {
			
			List<String> row = new ArrayList<>();
			String[] strArr = s.split("");
			
			for (String str : strArr) {
				row.add(str);
			}
			maze.add(row);
		}
		
		System.out.println(part1(maze));
		//System.out.println(part2(input));
						
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");

	}
	
	static int part1(List<List<String>> maze) {
		
		int x = 0;
		int y = 0;
		int steps = 1;
		boolean inMaze = true;
		boolean UP = false;
		boolean DOWN = true;
		boolean LEFT = false;
		boolean RIGHT = false;
		String pos;
		String letters = "";
		
		//find start position
		List<String> firstRow = maze.get(0);
		x = firstRow.indexOf("|");
			
		
		while (inMaze) {
			
			//System.out.println(maze.get(y).get(x));
			
			
			if (DOWN) {
				y++;
				
				pos = maze.get(y).get(x);	
							
				if (pos.equals("+")) { //at corner										
					DOWN = false;
					
					if (maze.get(y).get(x + 1).equals(" ")) {	
						LEFT = true;		
					} else {		
						RIGHT = true;
					}
											
				} else if (pos.equals("|") || pos.equals("-")) {
					
					
				} else {
					letters += (maze.get(y).get(x));
				}	
				
				if (maze.get(y + 1).get(x).equals(" ") && maze.get(y).get(x + 1).equals(" ") && maze.get(y).get(x - 1).equals(" ")) {
					inMaze = false;
				}
				
			} else if (UP) {
				y--;
				
				pos = maze.get(y).get(x);	
				
				if (pos.equals("+")) { //at corner										
					UP = false;					
					if (maze.get(y).get(x + 1).equals(" ")) {	
						LEFT= true;					
					} else {
						RIGHT = true;
					}
											
				} else if (pos.equals("|") || pos.equals("-")) {
					
					
				} else {
					letters += (maze.get(y).get(x));
				}
				
				//System.out.println(y);
				//System.out.println(x);
				if (maze.get(y - 1).get(x).equals(" ") && maze.get(y).get(x + 1).equals(" ") && maze.get(y).get(x - 1).equals(" ")) {
					inMaze = false;
				}
				
			} else if (LEFT) {
				x--;
				
				pos = maze.get(y).get(x);	
				
				if (pos.equals("+")) { //at corner										
					LEFT = false;					
					if (maze.get(y + 1).get(x).equals(" ")) {	
						UP = true;					
					} else {
						DOWN = true;
					}
											
				} else if (pos.equals("|") || pos.equals("-")) {
					
					
				} else {
					letters += (maze.get(y).get(x));
				}	
				
				if (maze.get(y + 1).get(x).equals(" ") && maze.get(y - 1).get(x).equals(" ") && maze.get(y).get(x - 1).equals(" ")) {
					inMaze = false;
				}
				
			} else if (RIGHT) {
				x++;
				
				pos = maze.get(y).get(x);	
				
				if (pos.equals("+")) { //at corner										
					RIGHT = false;					
					if (maze.get(y + 1).get(x).equals(" ")) {	
						UP = true;					
					} else {
						DOWN = true;
					}
											
				} else if (pos.equals("|") || pos.equals("-")) {
					
					
				} else {
					letters += (maze.get(y).get(x));
				}	
				
				if (maze.get(y + 1).get(x).equals(" ") && maze.get(y - 1).get(x).equals(" ") && maze.get(y).get(x + 1).equals(" ")) {
					inMaze = false;
				}
			}	
			
			steps++;	
		}
	
		//return letters; //return for part 1
		return steps; //return for part 2
		
	}
	


}
