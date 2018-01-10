package adventOfCode.day20;

import java.util.ArrayList;
import java.util.List;

import util.IOUtil;

public class Day20 {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day20/input.txt");
		
		//System.out.println(part1(input));
		//System.out.println(part2(input));
						
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}

}
