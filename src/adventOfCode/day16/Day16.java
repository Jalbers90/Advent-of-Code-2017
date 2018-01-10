package adventOfCode.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import util.IOUtil;

public class Day16 {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
/*		input.add("s1");
		input.add("x3/4");
		input.add("pe/b");*/
		
		IOUtil.read(input, "res/inputs/day16/input.txt");
		
		String str = input.get(0);
		String[] strArr = str.split(",");
		input.clear();	
		
		for (String s : strArr) {
				input.add(s);
		}
		
		//String letters = "abcdefghijklmnop";
		String part2Letters = "ociedpjbmfnkhlga";
		//String letters = "abcde";
		//String[] letterArray = letters.split("");
		String[] part2LetterArray = part2Letters.split("");
		//List<String> programs = Arrays.asList(letterArray);
		
/*		List<String> programs = new LinkedList<>();
		for (int i = 0; i < letterArray.length; i++) {
			programs.add(letterArray[i]);
		}*/
		
		List<String> programs = new LinkedList<>();
		for (int i = 0; i < part2LetterArray.length; i++) {
			programs.add(part2LetterArray[i]);
		}
		
		System.out.println(part1(input, programs));
		
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}
	
	
	static List<String> part1(List<String> input, List<String> programs) {
		
		String startSequence = "ociedpjbmfnkhlga";
		
		
		for (long i = 1; i < 1000000000; i++) {
			
			for (String s : input) {
				
				String sub = s.substring(1);
							
				if(s.startsWith("s")) {
					
					for (int j = 0; j < Integer.parseInt(sub); j++) {	
						String temp = programs.get(programs.size()-1);
						programs.add(0, temp);
						programs.remove(programs.size()-1);
					}
					
				} else if (s.startsWith("x")) {
					
					
					String[] values = sub.split("/");
					int num1 = Integer.parseInt((values[0]));
					int num2 = Integer.parseInt((values[1]));
					
					Collections.swap(programs, num1, num2);
					
				} else if (s.startsWith("p")) {
					
					String[] values = sub.split("/");
					Collections.swap(programs, programs.indexOf(values[0]), programs.indexOf(values[1]));
					
				}
				
				//System.out.println(programs);
				

			}
			
			StringBuilder sb = new StringBuilder();
			for (String str : programs) {
				sb.append(str);
			}
			
			String progs = sb.toString();
			//System.out.println(progs);
			
			if (progs.equals(startSequence)) {
				System.out.println(i);
				
				i = 1000000000 - (1000000000 % i);
				System.out.println(i);
				
			}
			
			
			
			
		}
		
		
		
		return programs;
	}

}
