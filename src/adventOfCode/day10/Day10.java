package adventOfCode.day10;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import util.IOUtil;

public class Day10 {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day10/input.txt");
		
		String s = input.get(0);
		String[] strArray = s.split(",");
		
		List<Integer> knotInstructions = new ArrayList<>();
				
		for (String string : strArray) {
			knotInstructions.add(Integer.parseInt(string));			
		}
		
		List<Integer> knot = new ArrayList<>();
		
		for (int i = 0; i <256; i++) {
			knot.add(i);
		}		
		
											
		//System.out.println(part1(knotInstructions, knot));
		//s = "1,2,3";
		//s = "AoC 2017";
		//s = "";
		System.out.println(part2(s, knot));
		
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}
	
	static int part1(List<Integer> lengths, List<Integer> knot) {
		
		int currentPos = 0;
		int skip = 0;
		
		for (int i = 0; i < lengths.size(); i++) {
			List<Integer> subList = new ArrayList<>();
						
			if (currentPos < ((currentPos + lengths.get(i)) % knot.size()) || currentPos + lengths.get(i) == knot.size()) {
				
				subList = knot.subList(currentPos, currentPos + lengths.get(i));
				if (lengths.get(i) != 0) {
					Collections.reverse(subList);
				}
			
			} else {
				if (lengths.get(i) != 0) {
					List<Integer> frontHalf = knot.subList(0, (currentPos + lengths.get(i)) % knot.size());
					subList = knot.subList(currentPos, knot.size()); //back half
									
					Collections.reverse(subList);
					Collections.reverse(frontHalf);
					
					List<Integer> tempList = new ArrayList<>(); //okay apparently sublists effect other sublists
					for (int j = 0; j < subList.size(); j++) {
						tempList.add(subList.get(j));
					}
									
					for (int j = 0; j < frontHalf.size(); j++) {					
						knot.set((currentPos + j) % knot.size(), frontHalf.get(j));
					}
					
					for (int j = 0; j < tempList.size(); j++) {					
						knot.set((currentPos + frontHalf.size() + j) % knot.size(), tempList.get(j));	
					}					
				}				
			}
			
			currentPos = (currentPos + lengths.get(i) + skip) % knot.size();			
			skip++;
		}		
		return knot.get(0) * knot.get(1);
	}
	
	
	static String part2(String input, List<Integer> knot) {
		
		//input += ",17,31,73,47,23";
		char[] charArray = input.toCharArray();
		List<Integer> lengths = new ArrayList<>();
		
		for (int i = 0; i < charArray.length; i++) {
			lengths.add((int) charArray[i]);
		}
		lengths.add(17);
		lengths.add(31);
		lengths.add(73);
		lengths.add(47);
		lengths.add(23);
		
		//System.out.println(lengths);
		
		int currentPos = 0;
		int skip = 0;
		
		for (int k = 0; k < 64; k++) {
			
			for (int i = 0; i < lengths.size(); i++) {
				List<Integer> subList = new ArrayList<>();
							
				if (currentPos < ((currentPos + lengths.get(i)) % knot.size()) || currentPos + lengths.get(i) == knot.size()) {
					
					subList = knot.subList(currentPos, currentPos + lengths.get(i));
					if (lengths.get(i) != 0) {
						Collections.reverse(subList);
					}
				
				} else {
					if (lengths.get(i) != 0) {
						List<Integer> frontHalf = knot.subList(0, (currentPos + lengths.get(i)) % knot.size());
						subList = knot.subList(currentPos, knot.size()); //back half
										
						Collections.reverse(subList);
						Collections.reverse(frontHalf);
						
						List<Integer> tempList = new ArrayList<>(); //okay apparently sublists effect other sublists
						for (int j = 0; j < subList.size(); j++) {
							tempList.add(subList.get(j));
						}
										
						for (int j = 0; j < frontHalf.size(); j++) {					
							knot.set((currentPos + j) % knot.size(), frontHalf.get(j));
						}
						
						for (int j = 0; j < tempList.size(); j++) {					
							knot.set((currentPos + frontHalf.size() + j) % knot.size(), tempList.get(j));	
						}					
					}				
				}				
				currentPos = (currentPos + lengths.get(i) + skip) % knot.size();			
				skip++;
			}
		}
		
		List<Integer> xorKnot = new ArrayList<>();
		
		for (int i = 0; i < 16; i++) {
			int xor = knot.get(i * 16);
			
			for (int j = 1; j < 16; j++) {
				xor ^= knot.get(j + (i * 16));
			}
			
			xorKnot.add(xor);
		}
		
		System.out.println(xorKnot);
		
		
		String hex = "";
		//List<String> hex = new ArrayList<>();
		
		for (int i = 0; i < xorKnot.size(); i++) {
			String temp = Integer.toHexString(xorKnot.get(i));
			if (temp.length() == 1) {
				temp = "0" + temp;
			}
			
			hex += temp;
			//hex.add(Integer.toHexString(xorKnot.get(i)));			
		}		
		return hex;
	}
	
	static String toHex(String arg) {
		  return String.format("%x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
		}

}
