package adventOfCode.day22;

import java.util.ArrayList;
import java.util.List;

import util.IOUtil;

public class Day22 {

	public static void main(String[] args) {

long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day22/input.txt");
		
		List<List<String>> map = new ArrayList<>();
		
		for (String row : input) {
			String[] split = row.split("");
			List<String> temp = new ArrayList<>();
			for (String s : split) {
				temp.add(s);
			}
			map.add(temp);
		}
		
		String row = ".........................";
		String[] splitRow = row.split("");
		String col = ".";
		
		for (int i = 0; i < 1000; i++) { // make more rows
			List<String> temp = new ArrayList<>();
			for (String s : splitRow) {
				temp.add(s);
			}			
			if (i < 500) {
				map.add(temp);			
			} else {
				map.add(0, temp);
			}
		}
		
		for (int i = 0; i < 1000; i++) { //make more columns
			for (int j = 0; j < map.size(); j++) {
				if (i < 500) {
					map.get(j).add(".");			
				} else {
					map.get(j).add(0, ".");
				}
			}
		}
		
		//System.out.println(map.get(0));		
		//System.out.println(part1(map));
		System.out.println(part2(map));
						
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}
	
	static int part1(List<List<String>> map) {
		int infected = 0;
		int x = map.get(0).size() / 2;
		int y = map.size() / 2;
		String curPos = map.get(y).get(x);
		boolean up = true;
		boolean down = false;
		boolean left = false;
		boolean right = false;
				
		for (int i = 0; i < 10000; i++) {
			
			if (curPos.equals(".")) { //turn left
				
				map.get(y).set(x, "#");
				infected++;
				//System.out.println(infected);
				if (up) {
					x--;
					left = true;
					up = false;
					
				} else if (down) {
					x++;
					right = true;
					down = false;
					
				} else if (left) {
					y++;
					down = true;
					left = false;
					
				} else if (right) {
					y--;
					up = true;
					right = false;
				}
				
				
				
			} else if (curPos.equals("#")) { //turn right
				
				map.get(y).set(x, ".");
				
				if (up) {
					x++;
					right = true;
					up = false;
					
				} else if (down) {
					x--;
					left = true;
					down = false;
					
				} else if (left) {
					y--;
					up = true;
					left = false;
					
				} else if (right) {
					y++;
					down = true;
					right = false;
				}
				
			}
			
			curPos = map.get(y).get(x);
			
		}
		
		return infected;
	}
	
	static int part2(List<List<String>> map) {
		
		int infected = 0;
		int x = map.get(0).size() / 2;
		int y = map.size() / 2;
		String curPos = map.get(y).get(x);
		boolean up = true;
		boolean down = false;
		boolean left = false;
		boolean right = false;
				
		for (int i = 0; i < 10000000; i++) {
			
			if (curPos.equals(".")) { //turn left
				
				map.get(y).set(x, "W");
								
				//System.out.println(infected);
				if (up) {
					x--;
					left = true;
					up = false;
					
				} else if (down) {
					x++;
					right = true;
					down = false;
					
				} else if (left) {
					y++;
					down = true;
					left = false;
					
				} else if (right) {
					y--;
					up = true;
					right = false;
				}
				
				
				
			} else if (curPos.equals("#")) { //turn right
				
				map.get(y).set(x, "F");
				
				if (up) {
					x++;
					right = true;
					up = false;
					
				} else if (down) {
					x--;
					left = true;
					down = false;
					
				} else if (left) {
					y--;
					up = true;
					left = false;
					
				} else if (right) {
					y++;
					down = true;
					right = false;
				}
				
			} else if (curPos.equals("W")) { //Do not move
				
				map.get(y).set(x, "#");
				infected++;
				
				if (up) {
					y--;				
					
				} else if (down) {
					y++;					
					
				} else if (left) {
					x--;
										
				} else if (right) {
					x++;					
				}
				
			} else if (curPos.equals("F")) { //Reverse
				
				map.get(y).set(x, ".");
				
				if (up) {
					y++;
					down = true;
					up = false;
					
				} else if (down) {
					y--;
					up = true;
					down = false;
					
				} else if (left) {
					x++;
					right = true;
					left = false;
					
				} else if (right) {
					x--;
					left = true;
					right = false;
				}
				
			}
			
			curPos = map.get(y).get(x);
			
		}
		
		return infected;
	}

}
