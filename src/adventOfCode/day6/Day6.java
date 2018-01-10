package adventOfCode.day6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.IOUtil;

public class Day6 {
	
	public static void main(String[] args) {
	
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day6/input.txt");
		
		List<Integer> intList = new ArrayList<>();
//		intList.add(0);
//		intList.add(2);
//		intList.add(7);
//		intList.add(0);
					
		for (String row : input) {
			
			String[] strArray = row.split("\\s+");
			for (int i = 0; i < strArray.length; i++) {
				intList.add(Integer.parseInt(strArray[i]));
			}
		}
		
		System.out.println(part1(intList));
		//System.out.println(part2(intList));
		
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}
	
	
	static int part1(List<Integer> input) {
		
		List<List<Integer>>  comparer= new ArrayList<>();
		
		int redistributionCount = 0; 
		boolean running = true;
		
		while(running) {
			
			List<Integer> tempList = new ArrayList<>(input);			
			comparer.add(tempList);
						
			int max = Collections.max(input);
			int maxIndex = input.indexOf(Collections.max(input));
			input.set(maxIndex, 0);
						
			for (int i = maxIndex + 1; i < max + maxIndex + 1; i++) {
				
				input.set(i%input.size(), input.get(i%input.size()) + 1);
			}
			
//			System.out.println(input);
//			System.out.println(comparer.get(0));
			
			//redistributionCount++;
			
			for (int i = 0; i < comparer.size(); i++) {
				
				if (input.equals(comparer.get(i))) {
					running = false;
				}
			}
		}		
		return redistributionCount;
	}
	
	
	static int part2(List<Integer> input) {
		
		List<List<Integer>>  comparer= new ArrayList<>();
		
		int redistributionCount = 0; 
		int sameStateAgain = 0;
		boolean running = true;
		
		while(running) {
			
			List<Integer> tempList = new ArrayList<>(input);
			
			comparer.add(tempList);
						
			int max = Collections.max(input);
			int maxIndex = input.indexOf(Collections.max(input));
			input.set(maxIndex, 0);
						
			for (int i = maxIndex + 1; i < max + maxIndex + 1; i++) {
				
				input.set(i%input.size(), input.get(i%input.size()) + 1);
			}
			
//			System.out.println(input);
//			System.out.println(comparer.get(0));
			
			redistributionCount++;
			
			for (int i = 0; i < comparer.size(); i++) {
				
				if (input.equals(comparer.get(i))) {
					
					sameStateAgain = part1(input);
					running = false;
				}
			}
		}		
		return sameStateAgain;
	}

}
