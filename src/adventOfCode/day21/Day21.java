package adventOfCode.day21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.IOUtil;

public class Day21 {
	
	static String[][] art = { {".", "#", "."}, {".", ".", "#"}, {"#", "#", "#"} };
	
	public static void main(String[] args) {
	
	
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day21/input.txt");
		
		List<String[][]> rules = new ArrayList<>();
		List<String[][]> enhancements = new ArrayList<>();		
		
		
		
		for (String string : input) { // parse puzzle input into rule and enhancement lists
			
			String[] strArray = string.split(" => ");
			
			for (int i = 0; i < strArray.length; i++) {
				
				String[] sa = strArray[i].split("/");
				
				int size = sa.length;	
				String[][] temp = new String[size][size];
											
				for (int j = 0; j < size; j++) {
					String[] split = sa[j].split("");
					
					for (int k = 0; k < size; k++) {
						temp[j][k] = split[k];
					}
				}
				
				if (i == 0) {
					rules.add(temp);					
				} else if (i == 1) {
					enhancements.add(temp);
				}				
			}
		}
		
/*		for (int i = 0; i < enhancements.size(); i++) {
			String[][] temp = enhancements.get(i);
			for (int j = 0; j <temp.length; j++) {
				System.out.println("");
				for (int k = 0; k < temp[j].length; k++) {
					System.out.print(temp[j][k]);
				}
			}
			System.out.println("");
		}*/
		
/*		art = transpose(art);
		for (int j = 0; j <art.length; j++) {
			System.out.println("");
			for (int k = 0; k < art[j].length; k++) {
				System.out.print(art[j][k]);
			}
		}*/
		
		System.out.println(part1(rules, enhancements));
								
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");

	}

	static int part1(List<String[][]> rules, List<String[][]> enhancements) {
		
		// part 1 = 5 iterations
		// part 2 = 18 iterations...this takes a minute
		
		for (int k = 0; k < 18; k++) {
			System.out.println("hi");
			int size = art.length;
			int div = size % 2 == 0 ? 2 : 3;
			int squares = (size / div) * (size / div);
			int newSize = (size / div) * (div + 1);
			
			String[][] temp = copy(art);
			
			art = new String[newSize][newSize];
					
				for (int i = 0; i < squares; i++) {					
					String[][] sub = makeSub(temp, i);
					
/*					for (int m = 0; m <sub.length; m++) { // printing array code
						System.out.println("");
						for (int n = 0; n < sub[m].length; n++) {
							System.out.print(sub[m][n]);
						}
					}*/
					
					String[][] enhanced = getEnhanced(sub, enhancements, rules);
					putSub(enhanced, i);
									
				}					
		}
		
		return count();
	}
	
	static String[][] getEnhanced(String[][] sub, List<String[][]> enhancements, List<String[][]> rules) {
		
		for (int i = 0; i < rules.size(); i++) {
			
			String[][] rule = rules.get(i);			
			if(match(sub, rule) != null) {
				return enhancements.get(i);
			}			
		}		
		return null;
	}
	
	static String[][] makeSub (String[][] temp, int index) {
		int length = temp.length % 2 == 0 ? 2 : 3;	
		int divs = temp.length / length;
		
		String[][] sub = new String[length][length];
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				sub[i][j] = temp[i + (index % divs) * length][j + (index / divs) * length]; 
			}
		}		
		return sub;
	}
	
	static void putSub (String[][] sub, int index) {
		int divs = art.length / sub.length;
        for (int i = 0; i < sub.length; i++) {
            for (int j = 0; j < sub.length; j++) {
                int y = i + (index % divs) * sub.length;
                int x = j + (index / divs) * sub.length;
                art[y][x] = sub[j][i];
            }
        }
	}
	
	static String[][] match (String[][] sub, String[][] rule) {
		
		for (int i = 0; i < 2; i++) {
			if (Arrays.deepEquals(sub, rule)) {
				return rule;
			}
			sub = flipX(sub);
			if (Arrays.deepEquals(sub, rule)) {
				return rule;
			}
			sub = transpose(sub);
			if (Arrays.deepEquals(sub, rule)) {
				return rule;
			}
			sub = flipY(sub);
			if (Arrays.deepEquals(sub, rule)) {
				return rule;
			}
			sub = flipX(sub);
			if (Arrays.deepEquals(sub, rule)) {
				return rule;
			}
			sub = flipY(sub);
			if (Arrays.deepEquals(sub, rule)) {
				return rule;
			}
			sub = transpose(sub);
			if (Arrays.deepEquals(sub, rule)) {
				return rule;
			}
			sub = flipX(sub);
			if (Arrays.deepEquals(sub, rule)) {
				return rule;
			}
		}
		
		return null;
	}
	
	
	static String[][] transpose (String [][] sub) {
		
		String[][] temp = new String[sub[0].length][sub.length];
		
        for (int i = 0; i < sub.length; i++)
            for (int j = 0; j < sub[0].length; j++)
                temp[j][i] = sub[i][j];
		
		return temp;
	}
	
	static String[][] flipY (String[][]sub) {
		String[][] temp = new String[sub.length][sub[0].length];
		
		for (int i = 0; i < sub.length; i++) {
			temp[i] = sub[(sub.length - 1) - i];
		}
				
		return temp;
	}
	
	static String[][] flipX (String[][] sub) {
		String[][] temp = new String[sub.length][sub[0].length];
		
		for (int i = 0; i < sub.length; i++) {
			for (int j = 0; j < sub[i].length; j++) {
				temp[i][j] = sub[i][(sub.length - 1) - j];
			}
		}
		
		return temp;
	}
	
	static String[][] copy(String[][] temp) {
	        String[][] copy = new String[temp.length][temp.length];
	        for (int i = 0; i < temp.length; i++) {
	            for (int j = 0; j < temp.length; j++) {
	                copy[i][j] = temp[i][j];
	            }
	        }
	        return copy;
	    }
	 
	 static int count() {
		 int count = 0;
		 
		 for (int i = 0; i < art.length; i++) {
			 for (int j = 0; j < art.length; j++) {
				 if (art[i][j].equals("#")) {
					 count++;
				 }
			 }
		 }
		 return count;
	 }

}
