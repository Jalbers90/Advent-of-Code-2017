package adventOfCode.day8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.IOUtil;

public class Day8 {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day8/input.txt");
		
		List<String[]> registerInstructions = new ArrayList<>();
		Map<String, Integer> registerValues = new HashMap<>();
							
		for (String row : input) {
			
			String[] strArray = row.split("\\s+");
			registerInstructions.add(strArray);
			registerValues.put(strArray[0], 0);
			
		}
		
		System.out.println(part1(registerInstructions, registerValues));
		//System.out.println(part2(intList));
		
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");

	}
	
	static int part1(List<String[]> registerInstructions, Map<String, Integer> registerValues) {
		int highestEver = 0;
		
		for (String[] strArray : registerInstructions) {
			
			int compareReg = registerValues.get(strArray[4]);
			int compareConst = Integer.parseInt(strArray[6]);
			int currentReg = registerValues.get(strArray[0]);
			int incrementValue = Integer.parseInt(strArray[2]);
			
			switch(strArray[5]) {
			case ">":
				if (compareReg > compareConst) {
					currentReg = decOrInc(strArray[1], currentReg, incrementValue);
				} else {}
				break;			
			case "<":
				if (compareReg < compareConst) {
					currentReg = decOrInc(strArray[1], currentReg, incrementValue);
				} else {}
				break;			
			      
			case ">=":
				if (compareReg >= compareConst) {
					currentReg = decOrInc(strArray[1], currentReg, incrementValue);
				} else {}
				break;			
			      
			case "<=":
				if (compareReg <= compareConst) {
					currentReg = decOrInc(strArray[1], currentReg, incrementValue);
				} else {}
				break;			
			       
			case "==":
				if (compareReg == compareConst) {
					currentReg = decOrInc(strArray[1], currentReg, incrementValue);
				} else {}
				break;			
			     
			case "!=":
				if (compareReg != compareConst) {
					currentReg = decOrInc(strArray[1], currentReg, incrementValue);
				} else {}
				break;			
			       
			 default:
				 System.out.println("forgot an operator");
				 break;			
			}
			
			registerValues.put(strArray[0], currentReg);									
			highestEver = part2(registerValues, highestEver);
			
			
		}
		
		
		//return Collections.max(registerValues.values()); //Part 1 return
		return highestEver; 			
	}
	
	static int decOrInc (String s, int currentReg, int incrementValue) {
		if (s.equals("inc")) {
			currentReg += incrementValue;
			
		} else {
			currentReg -= incrementValue;
		}
		return currentReg;
	}
	
	static int part2 (Map<String, Integer> registerValues, int highestEver) {
		if(highestEver < Collections.max(registerValues.values())) {
			highestEver = Collections.max(registerValues.values());
		}
		
		return highestEver;
	}
	


}
