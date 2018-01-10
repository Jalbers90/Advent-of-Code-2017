package adventOfCode.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.IOUtil;

public class Day12 {
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day12/input.txt");
		
		Map<Integer, List<String>> pipes = new HashMap<>();
		
		for (String row : input) {
			row = removeConnector(row);
			row = row.replaceAll(",", "");
			
			String[] strArray = row.split("\\s");
			List<String> children = new ArrayList<>();
			
			pipes.put(Integer.parseInt(strArray[0]), children);
			
			for (int i = 2; i < strArray.length; i++) {
				children.add(strArray[i]);
			}
			
		}
				
		System.out.println(part1(pipes));
											
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");

	}
	
	static int part1 (Map<Integer, List<String>> pipes) {
		
		List<Integer> repeats = new ArrayList<>();
		int total = 0; 
		int key = 0;
		int groups = 0;
		
		//int forRealTotal = traversePipes(pipes, repeats, key, total); // part1
		
		for (int i = 0; i < pipes.size(); i++) {
			
			if (!repeats.contains(i)) {
				traversePipes(pipes, repeats, i, total);
				groups++;
				
			}
		}
		
	
		
		//return forRealTotal; //return for part1
		return groups; //return for part2
	}
	
	static int traversePipes (Map<Integer, List<String>> pipes, List<Integer> repeats, int key, int total) {
		
		List<String> children = new ArrayList<>();		
		children = pipes.get(key);
				
		repeats.add(key);		
		total = total + 1;
					
		for (int i = 0; i < children.size(); i++) {
			
			if (!repeats.contains(Integer.parseInt(children.get(i)))) {
				
				repeats.add(Integer.parseInt(children.get(i)));
				
				key = Integer.parseInt(children.get(i));
				int temp = traversePipes(pipes, repeats, key, total);
				temp = temp - total; 
				total += temp;
			}
			
		}
				
		return total;
	}
	
	
	
	/// Utility Methods
	static String removeConnector(String s) {
		int startIndex = s.indexOf("<");
		int endIndex = s.indexOf(">");
		String toRemove = s.substring(startIndex, endIndex+1);
		s = s.replace(toRemove, "");
		
		return s;
	}

}
