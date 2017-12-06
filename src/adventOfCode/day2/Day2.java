package adventOfCode.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.IOUtil;


public class Day2 {

	public static void main(String[] args) {
		
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "res/inputs/day2/input.txt");
		
		//System.out.println(data);
		
		List<List<Integer>> numberData = new ArrayList<>();
		
		for (String row : data) {
			
			String[] strArray = row.split("\\s+");
			//System.out.println(java.util.Arrays.toString(strArray));
			List<Integer> intList = new ArrayList<>();
			
			for (int i = 0; i < strArray.length; i++) {
				intList.add(Integer.parseInt(strArray[i]));
			}
			
			numberData.add(intList);
		}
		
		System.out.println(part2(numberData));
		
			
	}
	
	
	public static int part1 (List<List<Integer>> numberData) {
		
		int checkSum = 0;
		for (List<Integer> list : numberData) {
			int difference = Collections.max(list) - Collections.min(list);
			
			checkSum += difference;
		}
		
		return checkSum;
	}
	
	public static int part2 (List<List<Integer>> input) {
		
		int answer = 0; 
		
		for (List<Integer> row : input) {
			
			for (int i : row) {
				for (int j = 0; j < row.size(); j++) {
					
					if (i != row.get(j)) {
						if (i % row.get(j) == 0) {
							
							int division = i / row.get(j);
							answer += division;
						}
					}
					
				}
			}
		}
		
		return answer;
	}

}
