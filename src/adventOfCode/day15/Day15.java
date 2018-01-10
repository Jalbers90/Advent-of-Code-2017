package adventOfCode.day15;

public class Day15 {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		
		System.out.println(part1());
		
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");

	}
	
	static int part1() {
		
		long valueA = 873;
		long valueB = 583;
//		long valueA = 65;
//		long valueB = 8921;
		long round = 0;
		
		long factorA = 16807;
		long factorB = 48271;
		long divider = 2147483647;
		int score = 0;
		
		// 40_000_000 loops for part 1
		for (int i = 0; i < 5000000; i++) {
			valueA = (factorA * valueA) % divider;
			valueB = (factorB * valueB) % divider;
			
			//while loops for part 2 only
			while (valueA % 4 != 0) {
				valueA = (factorA * valueA) % divider;
			}
			
			while (valueB % 8 != 0) {
				valueB = (factorB * valueB) % divider;
			}
			
			
			String binaryA = Long.toBinaryString(valueA);
			binaryA = String.format("%32s", binaryA).replace(" ", "0");
			
			String binaryB = Long.toBinaryString(valueB);
			binaryB = String.format("%32s", binaryB).replace(" ", "0");
			
			if (binaryA.substring(16).equals(binaryB.substring(16)) ) {
				score++;
			}
					
			//System.out.println(binaryA.substring(16).length());
			//System.out.println(valueA);
			
			//System.out.println(binaryB);
			//System.out.println(valueB);
			//System.out.println();
			round++;
			System.out.println(round);
		}
			
		return score;
	}

}
