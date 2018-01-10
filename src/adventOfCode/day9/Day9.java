package adventOfCode.day9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.IOUtil;

public class Day9 {
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day9/input.txt");
		
		char[] river = input.get(0).toCharArray();
		//char[] river = "{{<!!>},{<!!>},{<!!>},{<!!>}}".toCharArray();
											
		System.out.println(part1(river));
		//System.out.println(part2(intList));
		
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}


	static int part1 (char[] river) {
		boolean inGarbage = false;
		int groupCount = 0;
		int score = 0;
		int garbageCount = 0; //for part 2
		
		for (int i = 0; i < river.length; i++) {
			
			if(!inGarbage) {
							
				switch(river[i]) {			
				case '{':
					groupCount ++;
					score += groupCount;
					break;
					
				case '}':
					groupCount --;
					break;
					
				case '<':
					inGarbage = true;
					break;
					
				case '!':
					i++;
					continue;
					
				default: 
					break;
				}
				
			} else {
				
				switch(river[i]) {			
				case '!':
					i++;
					continue;
					
				case '>':
					inGarbage = false;
					break;
				default:
					garbageCount++; //for part 2;
					break;
				}				
			}				
		}		
		return score; //return for part 1
		//return garbageCount; //return for part2
	}

}
