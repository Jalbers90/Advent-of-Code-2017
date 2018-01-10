package adventOfCode.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.IOUtil;

public class Day13 {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day13/input.txt");
		
		Map<Integer, Integer> firewall = new HashMap<>();
		List<Integer> depth = new ArrayList<>();
		
		for (String row : input) {
			
			row = row.replaceAll(":", "");			
			String[] strArray = row.split("\\s");	
			
			firewall.put(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]));					
		}
		
		
		//System.out.println(part1(firewall));
		System.out.println(part2(firewall));
											
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");

	}
	
	static int part1(Map<Integer, Integer> firewall) {
		int severity = 0;
								
			for (int i = 0; i < 95; i++) {
				if (i != 0) {
					if (firewall.containsKey(i)) {
						if (i % (2 * firewall.get(i) - 2) == 0) {
							System.out.println("Caught! at " + i);
							severity = severity + (i * firewall.get(i));
						}
					}				
				}				
			}						
		return severity;
	}
	
	// this is a much better implementation for looping through the map
	static int part2(Map<Integer, Integer> firewall) {
		int severity = 0;
		boolean neverCaught = false;
		int delay = 0;
		
		while (!neverCaught) {		
			severity = 0; 
			delay++;
			
			for (Map.Entry<Integer, Integer> layer : firewall.entrySet()) {
				if ((layer.getKey() + delay) % (2 * (layer.getValue() -1)) == 0) {
					severity = 1;
					break;											
				}				
			}	
			
			if (severity == 0) {
				neverCaught = true;
			}			
		}		
		return delay;
	}
	
	
	


}
