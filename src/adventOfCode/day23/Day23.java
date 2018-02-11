package adventOfCode.day23;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.IOUtil;

public class Day23 {
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day23/input.txt");
		
		
		//System.out.println(part1(input));
		System.out.println(part2());
						
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");

	}
	
	static long part1(List<String> input) {
		
		Map<String, Long> registers = new HashMap<>();
		registers.put("a", 0L);
		registers.put("b", 0L);
		registers.put("c", 0L);
		registers.put("d", 0L);
		registers.put("e", 0L);
		registers.put("f", 0L);
		registers.put("g", 0L);
		registers.put("h", 0L);
		
		int count = 0;
		
		for (int i = 0; i < input.size(); i++) {
			String s = input.get(i);
			String[] inst = s.split("\\s");		
			
			//System.out.println(s);
						
			if (s.startsWith("set")) {
				if (isNumeric(inst[2])) {
					registers.put(inst[1], Long.parseLong(inst[2]));					
				} else {
					registers.put(inst[1], registers.get(inst[2]));
				}
				
			} else if (s.startsWith("sub")) {
				if (isNumeric(inst[2])) {
					registers.put(inst[1], registers.get(inst[1]) - Long.parseLong(inst[2]));					
				} else {
					registers.put(inst[1], registers.get(inst[1]) - registers.get(inst[2]));
				}
				
			} else if (s.startsWith("mul")) {
				count++;
				if (isNumeric(inst[2])) {
					registers.put(inst[1], Long.parseLong(inst[2]) * registers.get(inst[1]));					
				} else {
					registers.put(inst[1], registers.get(inst[2]) * registers.get(inst[1]));
				}
				
			} else if (s.startsWith("jnz")) {
				if ((isNumeric(inst[1])) || (registers.get(inst[1]) != 0) ) {
					
					if (isNumeric(inst[2])) {
						i = i + Integer.parseInt(inst[2]) - 1;
					} else {
						i =  (int) (i + registers.get(inst[2]) - 1);
					}
				}				
			}			
			System.out.println(registers);			
		}
		
		
		return count;
	}
	
	
	
	static int part2() { //This was a bit too much for me, solution from reddit/r/adventofcode...tailored to my input
		
		   int counter = 0;
		   final int original = 93 * 100 + 100000;

		    for (int n = 0; n <= 1000; ++n) {
		        int number = original + 17 * n;
		        if (!BigInteger.valueOf(number).isProbablePrime(100000)) counter++;
		    }

		    return counter;
				
	}
	
	 
	   
	
	
	public static boolean isNumeric(String str)  {  
		  try  
		  {  
		    Long d = Long.parseLong(str); 
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true;  
		}

}
