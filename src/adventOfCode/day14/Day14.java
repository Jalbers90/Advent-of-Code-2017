package adventOfCode.day14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adventOfCode.day14.Day14.Node;

public class Day14 {
	
	static class Node {
		
		int data;
		boolean visited;
		private List<Node> neighbors; 
		
		Node (int data) {
			this.data = data;
			this.neighbors = new ArrayList<>();
		}
		
		public void addNeighbor (Node neighbor) {
			this.neighbors.add(neighbor);
		}
		
		public List<Node> getNeighbors() {
			return neighbors;
		}
		
		public void setNeighbors (List<Node> neighbors) {
			this.neighbors = neighbors;
		}
		
	}

	public static void main(String[] args) {
		
		String input = "vbqugkhl";
		//String input = "a0c20170";
		//String input = "flqrgnkx";

		
		
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
			//System.out.println(stringArr.length);
			binaryGrid.add(stringArr);
			binary = binary.replace("0", "");
			
			sum += binary.length();								
		}

		//System.out.println(binaryGrid.size());
		System.out.println(part2(binaryGrid));
		
		return sum;
	}
	
	
	static int part2(List<String[]> binaryGrid) {
		List<List<Node>> grid = new ArrayList<>();
		
		for (String[] row : binaryGrid) {	// make node grid
			List<Node> nodeList = new ArrayList<>();
			
			for (int i = 0; i < row.length; i++) {
				Node n = new Node(Integer.parseInt(row[i]));				
				nodeList.add(n);				
			}
			grid.add(nodeList);
		}
		
		for (int i = 0; i < grid.size(); i++) { //set neighbors
			List<Node> nodeRow = grid.get(i);
			
			for (int j = 0 ; j < nodeRow.size(); j++) {
				Node n = nodeRow.get(j);
				
				if (i == 0 && j == 0) { //top left corner
					n.addNeighbor(grid.get(i).get(j+1));
					n.addNeighbor(grid.get(i+1).get(j));
				}
				
				if (i == 0 && j == nodeRow.size()-1) { // top right corner
					n.addNeighbor(grid.get(i).get(j-1));
					n.addNeighbor(grid.get(i+1).get(j));
				}
				
				if (i == 0 && (j != 0 && j != nodeRow.size()-1) ) { //top row
					n.addNeighbor(grid.get(i).get(j+1));
					n.addNeighbor(grid.get(i).get(j-1));
					n.addNeighbor(grid.get(i+1).get(j));				
				}
				
				if ( (i !=0 && i != grid.size()-1) && j == 0) { //first node in most rows
					n.addNeighbor(grid.get(i).get(j+1));
					n.addNeighbor(grid.get(i+1).get(j));
					n.addNeighbor(grid.get(i-1).get(j));
				}
				
				if ( (i !=0 && i != grid.size()-1) && j == nodeRow.size()-1) { //last node in most rows
					n.addNeighbor(grid.get(i).get(j-1));
					n.addNeighbor(grid.get(i+1).get(j));
					n.addNeighbor(grid.get(i-1).get(j));
				}
				
				if ( (i !=0 && i != grid.size()-1) && (j != 0 && j != nodeRow.size()-1) ) { //everything in between
					n.addNeighbor(grid.get(i).get(j+1));
					n.addNeighbor(grid.get(i).get(j-1));
					n.addNeighbor(grid.get(i+1).get(j));
					n.addNeighbor(grid.get(i-1).get(j));
				}
				
				if (i == grid.size()-1 && j == 0 ) { //bottom left corner
					n.addNeighbor(grid.get(i).get(j+1));
					n.addNeighbor(grid.get(i-1).get(j));				
				}
				
				if (i == grid.size()-1 && j == nodeRow.size()-1 ) { //bottom right corner
					n.addNeighbor(grid.get(i).get(j-1));
					n.addNeighbor(grid.get(i-1).get(j));				
				}
				
				if (i == grid.size()-1 && (j != 0 && j != nodeRow.size()-1) ) { //bottom row
					n.addNeighbor(grid.get(i).get(j-1));
					n.addNeighbor(grid.get(i).get(j+1));
					n.addNeighbor(grid.get(i-1).get(j));				
				}
					
			}
		}
		
		for (List<Node> row : grid) {
			for (int i = 0; i < row.size(); i++) {
				System.out.print(row.get(i).data);
			}
			System.out.println("");
		}
		
		System.out.println("");
		
		int groups = 0;
		
		for (List<Node> row : grid) {
			
			for (int j = 0 ; j < row.size(); j++) {
							
				if (row.get(j).data == 1 && row.get(j).visited == false) {
					
					dfs(row.get(j));
					groups++;
					System.out.println("\nnumber of groups is " + groups);
					
				} else if (row.get(j).data == 0) row.get(j).visited = true;
									
			}
		}
		
		
		
		System.out.println(grid.get(0).get(grid.get(0).size()-1).getNeighbors().get(1).data);
		
		return groups;
	}
	
	static void dfs (Node node) {
		System.out.print(node.data);
		List<Node> neighbors = node.getNeighbors();
		node.visited = true;
		
		for (int i = 0; i < neighbors.size(); i++) {
			Node n = neighbors.get(i);
			
			
			if (n != null && n.visited == false && n.data == 1) {
				
				dfs(n);
			}
		}		
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



