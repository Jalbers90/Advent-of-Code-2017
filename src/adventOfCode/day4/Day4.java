package adventOfCode.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.IOUtil;

public class Day4 {

	public static void main(String[] args) {

		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day4/input.txt");
		
		//System.out.println(data);
		
		List<String[]> stringData = new ArrayList<>();
		
		for (String row : input) {
			
			String[] strArray = row.split("\\s+");
			//System.out.println(java.util.Arrays.toString(strArray));
			//List<String> stringList = new ArrayList<>();
			
//			for (int i = 0; i < strArray.length; i++) {
//				stringList.add((strArray[i]));
//			}
			
			stringData.add(strArray);
		}
		
		//System.out.println(stringData.size());
		//System.out.println(part1(stringData));
		System.out.println(part2(stringData));
		
	}
	
	
	public static int part1(List<String[]> input) {
		
		int notValid = 0;
		
		for(String[] strArray : input) {
			
			outer:
			for (int i = 0; i < strArray.length; i++) {
				for (int j = 0; j < strArray.length; j++) {
					
					if (i != j) {
						if (strArray[i].equals(strArray[j])) {
							
							notValid++;
							break outer;
						}	
					}	
				}	
			}	
		}		
		int valid = input.size() - notValid;
		return valid;	
	}
	
	
	public static int part2(List<String[]> input) {
		
		int notValid = 0;
		
		for(String[] strArray : input) {
			outer:
			for (int i = 0; i < strArray.length; i++) {
				for (int j = 0; j < strArray.length; j++) {
					
					if (i != j) {
						if (isAnagram(strArray[i], strArray[j])) {
							
							notValid++;
							break outer;
						}	
					}	
				}	
			}	
		}
		
		int valid = input.size() - notValid;
		return valid;
	}
	
	
	
	public static boolean isAnagram(String firstWord, String secondWord) {
	     char[] word1 = firstWord.replaceAll("[\\s]", "").toCharArray();
	     char[] word2 = secondWord.replaceAll("[\\s]", "").toCharArray();
	     Arrays.sort(word1);
	     Arrays.sort(word2);
	     return Arrays.equals(word1, word2);
	}

}
