package adventOfCode.day25;

import java.util.ArrayList;
import java.util.List;

public class Day25 {
	
	static List<Integer> tape;
	static int cursor;
	static boolean stateA = true;
	static boolean stateB = false;
	static boolean stateC = false;
	static boolean stateD = false;
	static boolean stateE = false;
	static boolean stateF = false;
	
	
	static void stateA() {
		if (tape.get(cursor) == 0) {
			
			tape.set(cursor, 1);
			cursor++;
			stateB = true;
			stateA = false;
			
		} else if (tape.get(cursor) == 1) {
			tape.set(cursor, 0);
			cursor++;
			stateF = true;
			stateA = false;
		}
	}
		
	static void stateB() {
			if (tape.get(cursor) == 0) {
				cursor--;
				
			} else if (tape.get(cursor) == 1) {
							
				cursor--;
				stateC = true;
				stateB = false;
			}		
	}
	
	static void stateC() {
		if (tape.get(cursor) == 0) {
			
			tape.set(cursor, 1);
			cursor--;
			stateD = true;
			stateC = false;
			
		} else if (tape.get(cursor) == 1) {
			tape.set(cursor, 0);
			cursor++;
		}
	}
	
	static void stateD() {
		if (tape.get(cursor) == 0) {
			
			tape.set(cursor, 1);
			cursor--;
			stateE = true;
			stateD = false;
			
		} else if (tape.get(cursor) == 1) {
			
			cursor++;
			stateA = true;
			stateD = false;
		}
	}
		
	static void stateE() {
			if (tape.get(cursor) == 0) {
				
				tape.set(cursor, 1);
				cursor--;
				stateF = true;
				stateE = false;
				
			} else if (tape.get(cursor) == 1) {
				tape.set(cursor, 0);
				cursor--;
				stateD = true;
				stateE = false;
			}
	}
	
	static void stateF() {
		if (tape.get(cursor) == 0) {
			
			tape.set(cursor, 1);
			cursor++;
			stateA = true;
			stateF = false;
			
		} else if (tape.get(cursor) == 1) {
			tape.set(cursor, 0);
			cursor--;
			stateE = true;
			stateF = false;
		}
}
	
	static List<Integer> makeTape() {
		tape = new ArrayList<>();
		
		for (int i = 0; i < 5000000; i++) {
			tape.add(0);
		}		
		cursor = tape.size() /2;
		return tape;
	}
	
	static long part1() {
		long count = 0;
		long iterations = 12964419;
		
		//System.out.println(cursor);
		
		for (long i = 0; i < iterations; i++) {
			//System.out.println(cursor);
			if (stateA) {
				stateA();
			} else if (stateB) {
				stateB();
			} else if (stateC) {
				stateC();
			} else if (stateD) {
				stateD();
			} else if (stateE) {
				stateE();
			} else if (stateF) {
				stateF();
			}
		}
		
		for (int i : tape) {
			if (i == 1) {
				count++;
			}
		}
		
		return count;
	}
	
	

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();

		tape = makeTape();
		//System.out.println(cursor);
		System.out.println(part1());

		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}

}
