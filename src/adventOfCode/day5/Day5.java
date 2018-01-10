package adventOfCode.day5;

import java.util.ArrayList;
import java.util.List;

import util.IOUtil;

public class Day5 {
	
	public static void main(String[] args) {

		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day5/input.txt");
//		List<String> input = new ArrayList<>();
//		input.add("0");
//		input.add("3");
//		input.add("0");
//		input.add("1");
//		input.add("-3");
		
		List<Integer> intList= new ArrayList<>();
		
		for (String row : input) {
			
			intList.add(Integer.valueOf(row));		
		}
		
		//System.out.println(part1(intList));	
		System.out.println(part2(intList));		
	}
	
	public static int part1(List<Integer> input) {
		
		int currentPos = 0; //this is an index
		int nextPos = 0; //this is an index
		int currentValue;
		
		int totalMoves = 0;
		
		boolean moving = true;
		
		while (moving) {
				
			currentValue = input.get(currentPos);			
			nextPos = currentPos + currentValue; 			
			input.set(currentPos, currentValue + 1);			
			currentPos = nextPos;			
			totalMoves++;
			
			if(currentPos >= input.size()) {
				moving = false;
			}			
		}
		
		return totalMoves;
	}
	
	public static int part2(List<Integer> input) {
		
		int currentPos = 0; //this is an index
		int nextPos = 0; //this is an index
		int currentValue;
		
		int totalMoves = 0;		
		boolean moving = true;
		
		while (moving) {
				
			currentValue = input.get(currentPos);			
			nextPos = currentPos + currentValue; 		
			
			if (currentValue < 3) {
				input.set(currentPos, currentValue + 1);	
			
			} else {
				input.set(currentPos, currentValue - 1);
			}
			
			currentPos = nextPos;			
			totalMoves++;
			
			if(currentPos >= input.size()) {
				moving = false;
			}			
		}
		
		return totalMoves;
	}
	
	

}


