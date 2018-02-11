package adventOfCode.day18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import util.IOUtil;

public class Day18 {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		List<String> input = new ArrayList<>();
		IOUtil.read(input, "res/inputs/day18/input.txt");
		
		//System.out.println(part1(input));
		System.out.println(part2(input));
						
		long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds");

	}
	
	static long part1(List<String> input) {
		
		Map<String, Long> registers = new HashMap<>();
		registers.put("a", 0L);
		registers.put("b", 0L);
		registers.put("p", 0L);
		registers.put("i", 0L);
		registers.put("f", 0L);
		
		long lastSound = 0;
		
		for (int i = 0; i < input.size(); i++) {
			String s = input.get(i);
			String[] inst = s.split("\\s");		
			
			//System.out.println(s);
						
			if (s.startsWith("snd")) {
				lastSound = registers.get(inst[1]);
								
			} else if (s.startsWith("set")) {
				if (isNumeric(inst[2])) {
					registers.put(inst[1], Long.parseLong(inst[2]));					
				} else {
					registers.put(inst[1], registers.get(inst[2]));
				}
				
			} else if (s.startsWith("add")) {
				if (isNumeric(inst[2])) {
					registers.put(inst[1], Long.parseLong(inst[2]) + registers.get(inst[1]));					
				} else {
					registers.put(inst[1], registers.get(inst[2]) + registers.get(inst[1]));
				}
				
			} else if (s.startsWith("mul")) {
				if (isNumeric(inst[2])) {
					registers.put(inst[1], Long.parseLong(inst[2]) * registers.get(inst[1]));					
				} else {
					registers.put(inst[1], registers.get(inst[2]) * registers.get(inst[1]));
				}
				
			} else if (s.startsWith("mod")) {
				if (isNumeric(inst[2])) {
					registers.put(inst[1],  registers.get(inst[1]) % Long.parseLong(inst[2]));					
				} else {
					registers.put(inst[1], registers.get(inst[1]) % registers.get(inst[2]));
				}
				
			} else if (s.startsWith("rcv")) {
				if (registers.get(inst[1]) != 0) {
					return lastSound;
				}
				
			} else if (s.startsWith("jgz")) {
				if (registers.get(inst[1]) > 0 || (isNumeric(inst[1]))) {
					
					if (isNumeric(inst[2])) {
						i = i + Integer.parseInt(inst[2]) - 1;
					} else {
						i =  (int) (i + registers.get(inst[2]) - 1);
					}
				}				
			}			
			//System.out.println(registers);			
		}
		
		
		return -1;
	}
	
	
static long part2(List<String> input) {
		
		Map<String, Long> registersZERO = new HashMap<>();
		registersZERO.put("a", 0L);
		registersZERO.put("b", 0L);
		registersZERO.put("p", 0L);
		registersZERO.put("i", 0L);
		registersZERO.put("f", 0L);
		
		Map<String, Long> registersONE = new HashMap<>();
		registersONE.put("a", 0L);
		registersONE.put("b", 0L);
		registersONE.put("p", 1L);
		registersONE.put("i", 0L);
		registersONE.put("f", 0L);
		
		Queue<Long> queueZERO = new LinkedList<>();
		Queue<Long> queueONE = new LinkedList<>();
		
		boolean lockedZERO = false;
		boolean lockedONE = false;
		
		int posZERO = 0;
		int posONE = 0;
		int count = 0;
		
		
		while (!lockedZERO || !lockedONE) {
		
			while(!lockedZERO) {
			
				for (int i = posZERO; i < input.size(); i++) {
					String s = input.get(i);
					String[] inst = s.split("\\s");		
					
					//System.out.println(s);
								
					if (s.startsWith("snd")) {
						queueZERO.add(registersZERO.get(inst[1]));
						
						lockedONE = false;
																
					} else if (s.startsWith("set")) {
						if (isNumeric(inst[2])) {
							registersZERO.put(inst[1], Long.parseLong(inst[2]));					
						} else {
							registersZERO.put(inst[1], registersZERO.get(inst[2]));
						}
						
					} else if (s.startsWith("add")) {
						if (isNumeric(inst[2])) {
							registersZERO.put(inst[1], Long.parseLong(inst[2]) + registersZERO.get(inst[1]));					
						} else {
							registersZERO.put(inst[1], registersZERO.get(inst[2]) + registersZERO.get(inst[1]));
						}
						
					} else if (s.startsWith("mul")) {
						if (isNumeric(inst[2])) {
							registersZERO.put(inst[1], Long.parseLong(inst[2]) * registersZERO.get(inst[1]));					
						} else {
							registersZERO.put(inst[1], registersZERO.get(inst[2]) * registersZERO.get(inst[1]));
						}
						
					} else if (s.startsWith("mod")) {
						if (isNumeric(inst[2])) {
							registersZERO.put(inst[1],  registersZERO.get(inst[1]) % Long.parseLong(inst[2]));					
						} else {
							registersZERO.put(inst[1], registersZERO.get(inst[1]) % registersZERO.get(inst[2]));
						}
						
					} else if (s.startsWith("rcv")) {
						
						if (!queueONE.isEmpty()) {
							registersZERO.put(inst[1], queueONE.poll());
							
						} else {
							lockedZERO = true;
							posZERO = i;
							break;
							
						}
						
					} else if (s.startsWith("jgz")) {
						
					
						if ((isNumeric(inst[1])) || registersZERO.get(inst[1]) > 0) {
							
							if (isNumeric(inst[2])) {
								i = i + Integer.parseInt(inst[2]) - 1;
							} else {
								i =  (int) (i + registersZERO.get(inst[2]) - 1);
							}
						}				
					}			
				}
			}
					
			
			// OTHER PROGRAM			
			while(!lockedONE) {
				
				for (int i = posONE; i < input.size(); i++) {
					
					String s = input.get(i);
					
					String[] inst = s.split("\\s");		
					
					//System.out.println(s);
												
					if (s.startsWith("snd")) {
						queueONE.add(registersONE.get(inst[1]));
										
							
						lockedZERO = false;
						
						count++;
										
					} else if (s.startsWith("set")) {
						if (isNumeric(inst[2])) {
							registersONE.put(inst[1], Long.parseLong(inst[2]));					
						} else {
							registersONE.put(inst[1], registersONE.get(inst[2]));
						}
						
					} else if (s.startsWith("add")) {
						if (isNumeric(inst[2])) {
							
							
							
							registersONE.put(inst[1], Long.parseLong(inst[2]) + registersONE.get(inst[1]));					
						} else {
							registersONE.put(inst[1], registersONE.get(inst[2]) + registersONE.get(inst[1]));
						}
						
					} else if (s.startsWith("mul")) {
						if (isNumeric(inst[2])) {
							registersONE.put(inst[1], Long.parseLong(inst[2]) * registersONE.get(inst[1]));					
						} else {
							registersONE.put(inst[1], registersONE.get(inst[2]) * registersONE.get(inst[1]));
						}
						
					} else if (s.startsWith("mod")) {
						if (isNumeric(inst[2])) {
							registersONE.put(inst[1],  registersONE.get(inst[1]) % Long.parseLong(inst[2]));					
						} else {
							registersONE.put(inst[1], registersONE.get(inst[1]) % registersONE.get(inst[2]));
						}
						
					} else if (s.startsWith("rcv")) {
						
						
						if (!queueZERO.isEmpty()) {
							registersONE.put(inst[1], queueZERO.poll());
							
						} else {
							lockedONE = true;
							posONE = i;
							
							break;
							
						}
						
					} else if (s.startsWith("jgz")) {
						
						if  ((isNumeric(inst[1])) || registersONE.get(inst[1]) > 0) {
							
							if (isNumeric(inst[2])) {
								i = (int) (i + Integer.parseInt(inst[2]) - 1);
								
							} else {
								i =  (int) (i + registersONE.get(inst[2]) - 1);
								
							}
						}				
					}		
					
					
					
				}
			}		
		}
		
		return count;
	}
	

	public static boolean isNumeric(String str)  {  
	  try  
	  {  
	    Long d = Long.parseLong(str); 
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

}
