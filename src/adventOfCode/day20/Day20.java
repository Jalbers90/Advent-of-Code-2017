package adventOfCode.day20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.IOUtil;

public class Day20 {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day20/input.txt");
		
		List<List<Long>> position = new ArrayList<>();
		List<List<Long>> velocity = new ArrayList<>();
		List<List<Long>> acceleration = new ArrayList<>();
		
		for (String row : input) {
			String[] strArr = row.split(" ");
					
			for (int i = 0; i < strArr.length; i++) {
				
				String s = strArr[i].substring(strArr[i].indexOf("<") + 1, strArr[i].indexOf(">"));
				//System.out.println(s);
				String[] sa = s.split(",");
				List<Long> list = new ArrayList<>();
				
				for (int j = 0; j < sa.length; j++) {
					list.add(Long.parseLong(sa[j]));								
				}
				
				if(i == 0) {
					position.add(list);
				} else if (i == 1) {
					velocity.add(list);
				} else if (i == 2) {
					acceleration.add(list);
				}		
			}
						
		}
		
		//System.out.println(velocity);
		System.out.println(part1(position, velocity, acceleration, input));
		//System.out.println(part2(input));
						
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}
	
	static int part1(List<List<Long>> position, List<List<Long>> velocity, List<List<Long>> acceleration, List<String> input) {
		
		int closestParticle = 0;
		long distance = 0;
		long smallestAcc = 0; 
		
		
		for (int k = 0; k < 10000; k++) {
			//System.out.println("LOOP " + k);
			List<Long> distances = new ArrayList<>();
			
			for (int i = 0; i < input.size(); i++) {
				long check;
				
				for (int j = 0; j < position.get(i).size(); j++) {
					
					velocity.get(i).set(j, velocity.get(i).get(j) + acceleration.get(i).get(j));	
					position.get(i).set(j, position.get(i).get(j) + velocity.get(i).get(j));					
				}
						
				//System.out.println(position.get(i));
				//System.out.println(velocity.get(i));
				
				check = Math.abs(position.get(i).get(0)) + Math.abs(position.get(i).get(1)) + Math.abs(position.get(i).get(2));
				distances.add(check);

			}			
			
			closestParticle = distances.indexOf(Collections.min(distances));
			System.out.println(Collections.min(distances));
		}
		
		long smallAccParticle = 0;
		for (int i = 0; i < acceleration.size(); i++) {
			long temp = Math.abs(acceleration.get(i).get(0)) + Math.abs(acceleration.get(i).get(1)) + Math.abs(acceleration.get(i).get(2));
			
			if (i == 0) {
				smallestAcc = temp;
			}
			
			if (temp < smallestAcc) {
				smallestAcc = temp;
				smallAccParticle = i;
			}
		}
		
		//System.out.println(smallAccParticle);
		return closestParticle;
	}

}
