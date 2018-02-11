package adventOfCode.day20;

import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.HashSet;

public class Day20Test {

public static void main(String[] args) throws IOException {
    String input = new String(Files.readAllBytes(Paths.get("res/inputs/day20/input.txt")));
    String[] inputArray = input.split("\n");
    //[][x] Px = 0, Py = 1, Pz = 2, Vx = 3, Vy = 4, Vz = 5, Ax = 6. Ay = 7, Az = 8
    int[][] particles = new int[inputArray.length][9];
    double[] particleAcceleration = new double[particles.length];
    HashSet<Integer> particleNumbers = new HashSet<>();
    HashSet<Integer> toRemove = new HashSet<>();


    for(int i = 0; i < inputArray.length; i++){
        String[] tempArray = inputArray[i].split(", ");

        //Velocity as a string
        String[] velocity = tempArray[1].split(",");
        velocity[0] = velocity[0].substring(3, velocity[0].length());
        velocity[2] = velocity[2].substring(0, velocity[2].length() - 1);

        //Acceleration as a string
        String[] acceleration = tempArray[2].split(",");
        acceleration[0] = acceleration[0].substring(3, acceleration[0].length());
        acceleration[2] = acceleration[2].substring(0, acceleration[2].length() - 1);

        //position as a string
        String[] position = tempArray[0].split(",");
        position[0] = position[0].substring(3, position[0].length());
        position[2] = position[2].substring(0, position[2].length() - 1);

        for(int j = 0; j < 3; j++){
            particles[i][j + 3] = Integer.parseInt(velocity[j]);
            particles[i][j] = Integer.parseInt(position[j]);
            particles[i][j + 6] = Integer.parseInt(acceleration[j]);
        }

        particleAcceleration[i] = Math.sqrt((Integer.parseInt(acceleration[0]) * Integer.parseInt(acceleration[0])) + (Integer.parseInt(acceleration[1]) * Integer.parseInt(acceleration[1])) + (Integer.parseInt(acceleration[2]) * Integer.parseInt(acceleration[2])));
        particleNumbers.add(i);
    }

    int particleNumber = 0;

    for(int i = 0; i < particleAcceleration.length; i++){
        if(particleAcceleration[i] < particleAcceleration[particleNumber]){
            particleNumber = i;
        }
    }

    boolean remove = false;
    int counter = 100;

    while (counter > 0){
        for(Integer i : particleNumbers){
            for(Integer z : particleNumbers){
                if(i != z && particles[i][0] == particles[z][0] && particles[i][1] == particles[z][1] && particles[i][2] == particles[z][2]){
                    if(!toRemove.contains(z)) {
                        toRemove.add(z);
                        remove = true;
                    }
                }
            }

            if(remove){
                if(!toRemove.contains(i)) {
                    toRemove.add(i);
                }
                remove = false;
            }
        }

        for(Integer i : toRemove){
            particleNumbers.remove(i);
        }

        toRemove.clear();

        for(Integer i : particleNumbers){
            particles[i][3] = particles[i][3] + particles[i][6];
            particles[i][4] = particles[i][4] + particles[i][7];
            particles[i][5] = particles[i][5] + particles[i][8];

            particles[i][0] = particles[i][3] + particles[i][0];
            particles[i][1] = particles[i][4] + particles[i][1];
            particles[i][2] = particles[i][5] + particles[i][2];

        }

        counter--;
    }

    System.out.println("Part 1: " + particleNumber);
    System.out.println("Part 2: " + particleNumbers.size());


}
}