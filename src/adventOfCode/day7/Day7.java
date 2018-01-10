package adventOfCode.day7;

import java.util.ArrayList;
import java.util.List;

import util.IOUtil;

public class Day7 {

	public static void main(String[] args) {

long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day7/input.txt");
				
		//System.out.println(part1(input));
		System.out.println(part2(input));
		
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");
	}
	
	
	static String part1(List<String> input) {
		
		List<String> supportingPrograms = new ArrayList<>();
		
		for (String row : input) {	
			
			row = removeWeight(row);
			row = row.replaceAll(",", "");			
			String[] strArray = row.split("\\s");
			for (String string : strArray) {
				supportingPrograms.add(string);
			}				
		}		
		outer:
		for (int i = 0; i < supportingPrograms.size(); i++) {
			
			String compare = supportingPrograms.get(i);
			
			
			for (int j = 0; j < supportingPrograms.size(); j++) {
				
				if (i != j) {
					if(compare.equals(supportingPrograms.get(j))) {						
						continue outer;							
					} 					
				} else {
					continue;
				} 
			}			
			return compare;			
		}			
		return "something went wrong";
	}
	
	
	
	static int part2(List<String> input) {
		String trueBase = "gynfwly";
		String[] truBaseArray = {"lynvd", "dxhcxko", "xaatl", "leulsg", "zworz", "fkbrmim", "jjjks"};
		List<Integer> weightSums = new ArrayList<>();
		
		for(int i = 0; i < truBaseArray.length; i++) {
			int weight = 0;
			weightSums.add(traverseTree(truBaseArray[i], input, weight));				
		}
		
		
		compareSums("jjjks", input);
						
		return -1;
	}
	
	static int compareSums (String base, List<String> input) {
		List<String> children = new ArrayList<>();
		for (String row : input) {				
			if (row.startsWith(base)) {
				System.out.println(row);
				if (row.contains("->")) {
					
				}
					row = makeChildren(row);
					row = row.replaceAll(",", "");					
					String[] temp = row.split("\\s");
					
					for (int i = 0; i < temp.length; i++) {
						children.add(temp[i]);
					}
			}				
		}
		
		List<Integer> weightSums = new ArrayList<>();
		
		for(int i = 0; i < children.size(); i++) {
			int weight = 0;
			weightSums.add(traverseTree(children.get(i), input, weight));				
		}
		
		System.out.println(weightSums);
		String name = findUnique(weightSums, children);
		
		if(name.startsWith("Problem node is: ")) {
			System.out.println(name);
			
		} else {
			compareSums(name, input);
		}
		
		
	
		
		return -1;
	}
	
	static String findUnique(List<Integer> list, List<String> nameArray) {
		String name = "";
		outer:
		for (int i = 0; i < list.size(); i++) {
			
			int compare = list.get(i);
			name = nameArray.get(i);
			
			for (int j = 0; j < list.size(); j++) {
				
				if (i != j) {
					if(compare == list.get(j)) {						
						continue outer;							
					} 					
				} else {
					continue;
				} 
			}			
			return name;			
		}
		
		
		return "Problem node is parent of " + name;		
	}
		
	static int traverseTree(String base, List<String> input, int weight) {
						
			for (String row : input) {				
				if (row.startsWith(base)) {
					//System.out.println(row);
					if (row.contains("->")) {
						
						weight += extractWeight(row);
						//System.out.println(weight);
										
						row = makeChildren(row);
						row = row.replaceAll(",", "");
						
						String[] children = row.split("\\s");
											
						for (int i = 0; i < children.length; i++) {
							int temp;
							temp = traverseTree(children[i], input, weight);
							temp = temp - weight;
							weight += temp;
						}	
						
					} else {
						weight += extractWeight(row);
						//System.out.println(weight);
					}					
				}
			}
				
		return weight;
	}
	
	
	///below are some utility methods
	static String removeWeight(String s) {
		int startIndex = s.indexOf("(");
		int endIndex = s.indexOf(")");
		String toRemove = s.substring(startIndex, endIndex+1);
		s = s.replace(toRemove, "");
		
		return s;
	}
	
	static String makeChildren(String s) {
		//int startIndex = s.indexOf("(");
		int endIndex = s.indexOf("-> ");
		String toRemove = s.substring(0, endIndex + 3);
		s = s.replace(toRemove, "");
		//System.out.println(s);
		return s;
	}
	
	static int extractWeight(String s) {
		int weight = 0;		
		int startIndex = s.indexOf("(");
		int endIndex = s.indexOf(")");
		weight = Integer.parseInt(s.substring(startIndex+1, endIndex));
		//System.out.println(weight);
		return weight;
	}
}
