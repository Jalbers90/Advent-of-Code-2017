package adventOfCode.day3;

public class Day3 {

	public static void main(String[] args) {
		
		double input = 347991;
		//int input = 1024;
		
		System.out.println(Math.ceil(Math.sqrt(347991)));

	}
	
	
	
	
	public static int part1 (int input) {
		int answer = 0;
		double closestSquare = 1;
		int count = 1;
		int row = 0;
			
		while(input > closestSquare) {
			
			closestSquare = Math.pow(count, 2);
			count += 2;
			row++;
		}
		
		
		
		answer = count;
		
		return answer;
	}
	
	
	public static int part2 (int input) {
		int answer = 0;
		
		
		return answer;
	}

}
