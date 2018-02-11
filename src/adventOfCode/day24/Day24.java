package adventOfCode.day24;

import java.util.ArrayList;
import java.util.List;

import util.IOUtil;

public class Day24 {
	
	static List<Component> components = new ArrayList<>();
	static int maxStrength = 0;

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		//IOUtil.read(input, "res/inputs/day24/input.txt");
		
		input.add("0/2");
		input.add("9/10");
		input.add("2/2");
		input.add("0/1");
		input.add("5/10");
		input.add("2/3");
		input.add("3/4");
		input.add("3/5");		
		input.add("10/1");
		
		
		
		for (String row : input) { //create bridge components
			String[] splitted = row.split("/");
			Component comp = new Component(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]));
			components.add(comp);
		}
		
		for (Component c : components) { //add possible connections for each comp
			for (int i = 0 ; i < components.size(); i++) {
				
				if (c.equals(components.get(i))) {
					//skip itself
				} else if (c.a == components.get(i).a || c.a == components.get(i).b || c.b == components.get(i).a || c.b == components.get(i).b) {
					c.addConnection(components.get(i));
				}
			}			
		}
		
	/*	for (Component comp : components) {
			
			System.out.println(comp.a + "/" + comp.b);
		}*/
								
		//System.out.println(components.get(0).getConnections().get(2).a);
		System.out.println(part1(components));
		//System.out.println(part2());
						
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");

	}
	
	
	static int part1(List<Component> components) {
		
		List<Component> dummy = new ArrayList<>();
		dummy.addAll(components);
		Component comp = dummy.get(3);	
		buildBridge(comp, 0, dummy);
		
		
		return maxStrength;
	}
	
	static void buildBridge(Component comp, int bridgeStrength, List<Component> list) {
		System.out.println(comp.a + "/" + comp.b);
		bridgeStrength += comp.strength;
		//comp.visited = true;		
		
		if (comp.a == 0) {
			comp.usedA = true;
		}
		
		//List<Component> connections = comp.getConnections();		
		
		for (int i = 0; i < list.size(); i++) {
			
			Component c = list.get(i);
			
			if (comp.equals(c)) {
				//skip itself
				
			} else if (comp.a == c.a && !comp.usedA) {
				
				comp.usedA = true;
				c.usedA = true;
				list.remove(comp);
				buildBridge(c, bridgeStrength, list);
				
			} else if (comp.b == c.b && !comp.usedB) {
				
				comp.usedB = true;
				c.usedB = true;
				list.remove(comp);
				buildBridge(c, bridgeStrength, list);
				
			} else if (comp.a == c.b && !comp.usedA) {
				
				comp.usedA = true;
				c.usedB = true;
				list.remove(comp);
				buildBridge(c, bridgeStrength, list);
				
			} else if (comp.b == c.a && !comp.usedB) {
				
				comp.usedB = true;
				c.usedA = true;
				list.remove(comp);
				buildBridge(c, bridgeStrength, list);
								
			}
									
		}		
		
		// bridge complete			
		System.out.println("Bridge Strength is: " + bridgeStrength);
		if (bridgeStrength > maxStrength) {
			maxStrength = bridgeStrength;
		}
			
			components.remove(comp);
			List<Component> dummy = new ArrayList<>();
			dummy.addAll(components);
			
			for (Component c : dummy) {
				c.usedA = false;
				c.usedB = false;
			}
			
/*			System.out.println("starting list print: ...");
			for (Component comp1 : dummy) {				
				System.out.println(comp1.a + "/" + comp1.b);
			}
			System.out.println("Ending list print: ...\n");*/
			
			if (!components.isEmpty()) {
				Component newComp = dummy.get(0);
				
				for (int i = 0; i < dummy.size(); i++) {
					Component c = dummy.get(i);
					if (c.a == 0 && c.b == 1) {
						newComp = c;
						break;
					}
				}
				if (newComp.a == 0) {	
					buildBridge(newComp, 0, dummy);
				}
			}
		
		
		//return bridgeStrength;
	}
	
static class Component {
		
		int a;
		int b;
		int strength;
		boolean visited;
		boolean usedA;
		boolean usedB;
		private List<Component> connections;
		 		
		Component (int a, int b) {
			this.a = a;
			this.b = b;
			this.strength = a + b;
			connections = new ArrayList<>();
		}	
		
		public void addConnection(Component comp) {
			this.connections.add(comp);
		}
		
		public List<Component> getConnections () {
			return connections;
		}
		
		public void setConnections (List<Component> connections) {
			this.connections = connections;
		}
		
		public int otherSide (int port) {
			if (a == port) {
				return b;				
			} else return a;
		}
		
		
	}

}
