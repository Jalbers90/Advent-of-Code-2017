package adventOfCode.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.IOUtil;

public class Day11 {

	public static void main(String[] args) {
		
long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day11/input.txt");
		
		//System.out.println(input.get(1));
		
		String s = input.get(0);
		String[] strArray = s.split(",");
		List<String> childSteps = new ArrayList<>();
				
		for (String string : strArray) {
			childSteps.add(string);			
		}
													
		System.out.println(part1(childSteps));
		
		
		//System.out.println(part2(intList));
		
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");

	}
	
	
	static int part1 (List<String> childSteps) {
		int x = 0;
		int y = 0;
		int z = 0;
		int fewestSteps = 0;
		
		// furthest and coords are for part2 only
		int furthest = 0;
		
		for(String step : childSteps) {
					
			switch(step) {
			
			case "n": 
				y++;
				z--;
				break;
			case "s": 
				y--;
				z++;
				break;
			case "ne": 
				x++;
				z--;
				break;
			case "se": 
				y--;
				x++;
				break;
			case "nw": 
				y++;
				x--;
				break;
			case "sw": 
				z++;
				x--;
				break;
			}
			
			List<Integer> coords = new ArrayList<>();
			coords.add(x);
			coords.add(y);
			coords.add(z);			
			for (int i = 0; i < coords.size(); i++) {
				//System.out.println(Math.abs(coords.get(i)));
				if ( furthest < Math.abs(coords.get(i))) {
					furthest =  Math.abs(coords.get(i));
				}
			}			
			
		}
		
		fewestSteps = (Math.abs(x) + Math.abs(y) + Math.abs(z)) / 2;
		
		
		//return fewestSteps; //return for part 1
		return furthest; //return for part2
	}

}
