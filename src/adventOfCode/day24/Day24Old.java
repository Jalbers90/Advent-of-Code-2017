package adventOfCode.day24;

import java.util.ArrayList;
import java.util.List;

public class Day24Old { 
	
	static int part1(List<String> input) {
		int max = 0;
		int maxIndex = 0;
		int strength = 0;
		int totalStrength = 0;
		boolean bridgeBuilding = true;
		int trueStrength = 0;
		String current = "";
		
		
		for (int i = 0; i < input.size(); i++) { //find strongest component
			
			String string = input.get(i);
			String[] strArr = string.split("/");			
			totalStrength = Integer.parseInt(strArr[0]) + Integer.parseInt(strArr[1]);
			maxIndex = i;
			List<String> temp = new ArrayList<>();
			temp.addAll(input);
				
			while (bridgeBuilding) {			
							
				max = 0;				
				current = temp.get(maxIndex);				
				//System.out.println(current);
				String[] split = current.split("/");
				temp.remove(maxIndex);
				
				for (int j = 0; j < temp.size(); j++) {
					if (temp.get(j).contains(split[0]) || temp.get(j).contains(split[1])) {
						
						String s = temp.get(j);
						String[] nComp = s.split("/");
						
						strength = Integer.parseInt(nComp[0]) + Integer.parseInt(nComp[1]);
						
						if (strength > max) {
							max = strength;							
							maxIndex = j;
						}
						
					}
					
				}
				
				if (temp.get(maxIndex).startsWith("0")) {
					bridgeBuilding = false;
					
				} else {
					totalStrength += max;
				}
								
			}
			
			bridgeBuilding = true;
			System.out.println(totalStrength);
			if (totalStrength > trueStrength) {
				trueStrength = totalStrength;
			}
		}
		
		
		return trueStrength;
	}
	
}
		


