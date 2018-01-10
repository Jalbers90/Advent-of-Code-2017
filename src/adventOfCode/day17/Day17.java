package adventOfCode.day17;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import util.IOUtil;

public class Day17 {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		
		
		
		
		
		//System.out.println(part1());
		System.out.println(part2());
		
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}
	
	
	
	static int part1() {
		int steps = 377;
		//int steps = 3;
		int index = 0; 
		
		List<Integer> hurricane = new LinkedList<>();
		hurricane.add(0);
				
		for (int i = 1; i <= 2017; i++) {
			
			index = ((index + steps) % hurricane.size()) + 1;
			
			if (index == 1) {
				hurricane.add(index, i);
			}			
//			System.out.println(index);
//			System.out.println(hurricane);						
		}	
		
		index = hurricane.indexOf(2017);						
		return hurricane.get(index + 1);
	}
	
	static int part2() {
		int steps = 377;
		//int steps = 3;
		int index = 0;
		int value = 0;
						
		for (int i = 1; i <= 50000000; i++) {
			
			index = ((index + steps) % i) + 1;
			
			if (index == 1) {
				value = i;
			}			
		}		
					
		return value;		
	}

}
