package adventOfCode.day14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day14 {

	public static void main(String[] args) {
		
		//String input = "vbqugkhl";
		//String input = "a0c20170";
		String input = "flqrgnkx";

		
		System.out.println(part1(input));
	}
	
	
	static int part1(String input) {
		
		List<String[]> binaryGrid = new ArrayList<>();
		int sum = 0;
		
		for (int i = 0; i < 128; i++) {
			String binary = "";
			String hash = input + "-" + i;
			String knotHash = makeKnotHash(hash);
			
			char [] charArray = knotHash.toCharArray();
			
			for (char c : charArray) {
				int num = Character.getNumericValue(c);
				String temp = Integer.toBinaryString(num);
				temp = String.format("%4s", temp).replace(" ", "0");
			
				binary += temp;
				//System.out.println(temp);
			}
			
			//System.out.println(binary);
			String[] stringArr = binary.split("");
			binaryGrid.add(stringArr);
			binary = binary.replace("0", "");
			
			sum += binary.length();								
		}
		
		int groups = 0;
		
		for (int i = 0; i < binaryGrid.size(); i++) {
			String[] s = binaryGrid.get(i);
			
			for (int j = 0; j < s.length; j++) {
				
				if (Integer.parseInt(s[j]) == 1) {
					
					
					
				}
				
				
				
			}
			
		}
		
			
		
		return sum;
	}
	
	
	static String makeKnotHash(String input) {
		
		List<Integer> knot = new ArrayList<>();
		
		for (int i = 0; i <256; i++) {
			knot.add(i);
		}		
	
		
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
		
		//System.out.println(xorKnot);
		
		
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

}
