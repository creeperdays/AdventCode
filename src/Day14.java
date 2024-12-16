import java.util.ArrayList;
import java.util.Arrays;

public class Day14 {

    public static boolean checkRow(int[] row) {
        return inARow(row) > 22;
    }

    public static int christMasTree(String input, int wide, int tall) {
        ArrayList<Robot> robots = parseInput(input);

        int seconds = 0;
        boolean found = true;

        while (found) {
            for (Robot r : robots) {
                second(r, wide, tall);
            }

            seconds = seconds + 1;

            int[][] grid = toGrid(robots, wide, tall);

            for (int i = 0; i < grid.length; i++) {
                if (checkRow(grid[i])) {
                    found = false;
                    break;
                }
            }
        }



        return seconds;
    }

    /**
     * Function which finds how many numbers 1 or more  are in a row in
     * the grid
     * @param row An array of integers
     * @return
     */
    public static int inARow(int[] row) {
        int max = 0;
        int current = 0;

        for (int num : row) {
            if (num > 0) {
                current = current + 1;
            } else {
                if (current > max) {
                    max = current;
                }

                current = 0;
            }
        }

        return max;
    }

    public static int[][] toGrid(ArrayList<Robot> robots, int wide, int tall) {
        int[][] grid = new int[tall][wide];

        for (Robot r : robots) {
            grid[r.getPosY()][r.getPosX()] = grid[r.getPosY()][r.getPosX()] + 1;
        }

        return grid;
    }



    public static int safetyScore(String input, int wide, int tall) {
        ArrayList<Robot> robots = parseInput(input);

        for (Robot r : robots) {

            for (int i = 1; i <= 100; i++) {
                second(r, wide, tall);
            }
        }

        int midX = ((wide + 1) / 2) -1;
        int midY = ((tall + 1) / 2) -1;

        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;

        for (Robot r : robots) {
            System.out.println(r);
            if (r.getPosX() < midX && r.getPosY() < midY) {
                q1 = q1 + 1;
            }

            if (r.getPosX() > midX && r.getPosY() < midY) {
                q2 = q2 + 1;
            }

            if (r.getPosX() < midX && r.getPosY() > midY) {
                q3 = q3 + 1;
            }

            if (r.getPosX() > midX && r.getPosY() > midY) {
                q4 = q4 + 1;
            }
        }

        System.out.println(q1);
        System.out.println(q2);
        System.out.println(q3);
        System.out.println(q4);

        return q1*q2*q3*q4;

    }

    /**
     * Changes the position of the robot after one second
     * @param robot the robot
     * @param wide how many tiles wide it is - the plane
     * @param tall how many tiles tall is the plane
     */
    public static void second(Robot robot, int wide, int tall) {

        if (robot.getPosX() + robot.getVelX() >= wide) {
            int diff = robot.getPosX() + robot.getVelX() - (wide - 1) - 1;
            robot.setPosX(diff);
        } else if (robot.getPosX() + robot.getVelX() < 0) {
            int pos =  wide + robot.getPosX() + robot.getVelX();
            robot.setPosX(pos);
        } else {
            robot.setPosX(robot.getPosX() + robot.getVelX());
        }

        if (robot.getPosY() + robot.getVelY() >= tall) {
            int diff = robot.getPosY() + robot.getVelY() - (tall - 1) - 1;
            robot.setPosY(diff);
        } else if (robot.getPosY() + robot.getVelY() < 0) {
            int pos = tall + robot.getPosY() + robot.getVelY();
            robot.setPosY(pos);
        } else {
            robot.setPosY(robot.getPosY() + robot.getVelY());
        }

    }

    public static ArrayList<Robot> parseInput(String input) {
        String[] s = input.split("\n");

        ArrayList<Robot> robots = new ArrayList<Robot>();

        for (String s1 : s) {
            String[] s2 = s1.split(" ");

            String[] p = s2[0].split("=");

            String[] v = s2[1].split("=");

            String[] p1 = p[1].split(",");
            String[] v1 = v[1].split(",");

            int posX = Integer.parseInt(p1[0]);
            int posY = Integer.parseInt(p1[1]);

            int velX = Integer.parseInt(v1[0]);
            int velY = Integer.parseInt(v1[1]);

            Robot r = new Robot(posX, posY, velX, velY);

            robots.add(r);
        }

        return robots;

    }

    public static class  Robot {

        //X position of the robot (how far left they are from the wall
        private int posX;

        //How far down they are
        private int posY;

        //Velocity in the X direction
        private int velX;

        //Velocity in the Y direction
        private int velY;

        public Robot (int posX, int posY, int velX, int velY) {
            this.posX = posX;
            this.posY = posY;
            this.velX = velX;
            this.velY = velY;
        }

        public int getPosX() {
            return posX;
        }

        public int getPosY() {
            return posY;
        }

        public int getVelX() {
            return velX;
        }

        public int getVelY() {
            return velY;
        }

        public void setPosX(int posX) {
            this.posX = posX;
        }

        public void setPosY(int posY) {
            this.posY = posY;
        }

        @Override
        public String toString() {
            return "(" + posX + "," + posY + "," + velX + "," + velY + ")";
        }
    }


    public static void main(String[] args) {
        String input = "p=50,78 v=89,45\n" +
                "p=65,96 v=88,-21\n" +
                "p=23,63 v=61,46\n" +
                "p=61,76 v=25,-67\n" +
                "p=23,90 v=6,91\n" +
                "p=1,47 v=-17,-9\n" +
                "p=84,56 v=-73,-24\n" +
                "p=78,66 v=-20,-76\n" +
                "p=49,59 v=-22,17\n" +
                "p=73,95 v=34,-64\n" +
                "p=99,76 v=67,-40\n" +
                "p=59,77 v=-49,-4\n" +
                "p=15,30 v=24,65\n" +
                "p=89,2 v=-19,-38\n" +
                "p=28,70 v=93,66\n" +
                "p=39,33 v=24,-42\n" +
                "p=14,42 v=-33,-49\n" +
                "p=68,85 v=-37,-47\n" +
                "p=91,0 v=-45,12\n" +
                "p=88,92 v=78,-24\n" +
                "p=19,63 v=-70,-10\n" +
                "p=69,4 v=-40,90\n" +
                "p=61,91 v=64,-11\n" +
                "p=47,95 v=-77,46\n" +
                "p=20,10 v=-23,-63\n" +
                "p=98,82 v=90,96\n" +
                "p=90,30 v=4,74\n" +
                "p=0,28 v=-73,8\n" +
                "p=88,102 v=51,-51\n" +
                "p=66,29 v=26,1\n" +
                "p=19,20 v=-61,-15\n" +
                "p=13,18 v=6,71\n" +
                "p=12,39 v=67,24\n" +
                "p=41,67 v=-24,-23\n" +
                "p=78,10 v=-59,-68\n" +
                "p=30,2 v=-35,85\n" +
                "p=86,47 v=-95,53\n" +
                "p=44,36 v=-69,-89\n" +
                "p=19,20 v=-87,-95\n" +
                "p=65,3 v=73,16\n" +
                "p=95,29 v=43,61\n" +
                "p=66,50 v=49,-69\n" +
                "p=28,92 v=-25,19\n" +
                "p=74,60 v=-6,-98\n" +
                "p=15,80 v=92,-27\n" +
                "p=80,12 v=18,-62\n" +
                "p=72,34 v=19,-65\n" +
                "p=46,75 v=33,-74\n" +
                "p=14,100 v=-79,99\n" +
                "p=94,97 v=74,49\n" +
                "p=46,13 v=-45,-61\n" +
                "p=93,39 v=-9,-64\n" +
                "p=100,45 v=45,-76\n" +
                "p=62,19 v=-76,85\n" +
                "p=18,85 v=30,-71\n" +
                "p=24,68 v=84,-73\n" +
                "p=31,20 v=-86,-35\n" +
                "p=76,46 v=-59,77\n" +
                "p=27,52 v=-95,77\n" +
                "p=52,81 v=1,59\n" +
                "p=43,60 v=-71,-38\n" +
                "p=59,38 v=-6,24\n" +
                "p=70,65 v=26,-23\n" +
                "p=11,40 v=60,-33\n" +
                "p=26,77 v=62,43\n" +
                "p=24,93 v=99,-51\n" +
                "p=46,84 v=24,76\n" +
                "p=48,30 v=58,-87\n" +
                "p=46,100 v=21,98\n" +
                "p=24,39 v=-23,84\n" +
                "p=25,6 v=47,-78\n" +
                "p=89,73 v=-73,23\n" +
                "p=4,49 v=-96,14\n" +
                "p=69,61 v=52,-10\n" +
                "p=96,68 v=-96,99\n" +
                "p=17,12 v=-30,75\n" +
                "p=91,47 v=-98,-87\n" +
                "p=50,38 v=71,67\n" +
                "p=68,58 v=-13,17\n" +
                "p=39,30 v=84,93\n" +
                "p=93,19 v=-66,98\n" +
                "p=31,7 v=-8,75\n" +
                "p=7,81 v=-95,-4\n" +
                "p=38,43 v=8,-89\n" +
                "p=24,21 v=-98,3\n" +
                "p=72,43 v=25,-13\n" +
                "p=4,98 v=-71,78\n" +
                "p=79,65 v=-91,-57\n" +
                "p=49,47 v=-97,23\n" +
                "p=37,7 v=-15,45\n" +
                "p=41,74 v=-15,36\n" +
                "p=58,30 v=-92,71\n" +
                "p=72,92 v=68,-7\n" +
                "p=1,79 v=-4,-75\n" +
                "p=82,99 v=42,6\n" +
                "p=62,51 v=57,63\n" +
                "p=10,43 v=48,-74\n" +
                "p=74,70 v=65,-40\n" +
                "p=25,35 v=-1,-39\n" +
                "p=50,71 v=37,68\n" +
                "p=51,84 v=67,55\n" +
                "p=71,25 v=-45,-98\n" +
                "p=47,14 v=-7,28\n" +
                "p=70,99 v=69,46\n" +
                "p=21,44 v=-94,-16\n" +
                "p=52,40 v=-83,-49\n" +
                "p=76,99 v=-71,63\n" +
                "p=21,92 v=-32,-34\n" +
                "p=78,27 v=-85,-55\n" +
                "p=42,92 v=-76,-61\n" +
                "p=50,29 v=95,77\n" +
                "p=72,10 v=65,58\n" +
                "p=92,48 v=20,-1\n" +
                "p=42,80 v=55,-98\n" +
                "p=69,96 v=41,62\n" +
                "p=68,35 v=-91,-45\n" +
                "p=18,56 v=-33,37\n" +
                "p=52,96 v=1,49\n" +
                "p=22,87 v=53,99\n" +
                "p=84,31 v=19,41\n" +
                "p=87,38 v=58,-13\n" +
                "p=9,13 v=-18,98\n" +
                "p=40,0 v=-94,-31\n" +
                "p=71,16 v=-62,76\n" +
                "p=41,88 v=-46,99\n" +
                "p=27,97 v=-15,-4\n" +
                "p=25,54 v=23,-36\n" +
                "p=61,0 v=-60,-21\n" +
                "p=28,75 v=41,-79\n" +
                "p=64,46 v=-8,34\n" +
                "p=97,26 v=37,74\n" +
                "p=32,67 v=-48,-93\n" +
                "p=18,81 v=53,-2\n" +
                "p=12,101 v=30,-1\n" +
                "p=80,40 v=-90,-16\n" +
                "p=48,16 v=-69,61\n" +
                "p=7,89 v=52,49\n" +
                "p=93,0 v=-35,-61\n" +
                "p=24,52 v=31,53\n" +
                "p=37,67 v=-98,36\n" +
                "p=92,77 v=28,46\n" +
                "p=76,59 v=-74,-21\n" +
                "p=96,63 v=58,57\n" +
                "p=62,2 v=-68,-68\n" +
                "p=39,20 v=-39,-25\n" +
                "p=5,12 v=60,88\n" +
                "p=75,35 v=-37,88\n" +
                "p=77,24 v=-52,-13\n" +
                "p=42,0 v=92,-72\n" +
                "p=33,61 v=-91,-6\n" +
                "p=10,74 v=23,-17\n" +
                "p=6,73 v=37,-54\n" +
                "p=76,99 v=2,-71\n" +
                "p=53,20 v=71,91\n" +
                "p=82,31 v=71,-82\n" +
                "p=20,87 v=-40,26\n" +
                "p=52,47 v=-45,-69\n" +
                "p=32,38 v=43,-55\n" +
                "p=8,85 v=-56,-77\n" +
                "p=11,0 v=68,2\n" +
                "p=40,30 v=-46,-72\n" +
                "p=26,53 v=27,-43\n" +
                "p=5,40 v=98,-90\n" +
                "p=46,4 v=39,-85\n" +
                "p=15,36 v=-95,87\n" +
                "p=0,4 v=-49,42\n" +
                "p=69,11 v=-86,-87\n" +
                "p=47,47 v=86,-17\n" +
                "p=95,88 v=-73,26\n" +
                "p=2,5 v=53,52\n" +
                "p=92,84 v=63,79\n" +
                "p=25,0 v=47,-35\n" +
                "p=83,55 v=-43,-46\n" +
                "p=95,43 v=-3,40\n" +
                "p=30,60 v=-79,-27\n" +
                "p=89,79 v=-39,-80\n" +
                "p=10,19 v=6,-95\n" +
                "p=70,64 v=87,10\n" +
                "p=51,93 v=79,-31\n" +
                "p=30,82 v=85,16\n" +
                "p=66,75 v=-51,40\n" +
                "p=79,70 v=-20,-20\n" +
                "p=59,60 v=25,63\n" +
                "p=86,17 v=75,-62\n" +
                "p=3,62 v=-18,-47\n" +
                "p=43,20 v=24,91\n" +
                "p=23,13 v=56,93\n" +
                "p=32,13 v=71,76\n" +
                "p=12,10 v=53,-28\n" +
                "p=20,86 v=-71,-74\n" +
                "p=91,90 v=-97,-75\n" +
                "p=76,66 v=-67,83\n" +
                "p=54,54 v=-84,64\n" +
                "p=66,70 v=-12,36\n" +
                "p=30,73 v=54,43\n" +
                "p=62,22 v=-21,31\n" +
                "p=11,92 v=-94,76\n" +
                "p=47,101 v=93,-25\n" +
                "p=37,21 v=96,19\n" +
                "p=93,51 v=20,97\n" +
                "p=46,31 v=-54,61\n" +
                "p=26,99 v=-64,-11\n" +
                "p=6,91 v=-55,-90\n" +
                "p=95,7 v=95,33\n" +
                "p=1,89 v=5,19\n" +
                "p=69,24 v=-83,81\n" +
                "p=82,37 v=-66,-92\n" +
                "p=20,66 v=-80,-77\n" +
                "p=45,87 v=-47,-11\n" +
                "p=19,77 v=8,1\n" +
                "p=61,12 v=-70,79\n" +
                "p=59,48 v=14,44\n" +
                "p=68,70 v=48,-37\n" +
                "p=77,94 v=42,-84\n" +
                "p=47,31 v=1,-32\n" +
                "p=22,1 v=-40,95\n" +
                "p=41,98 v=-53,16\n" +
                "p=38,49 v=48,77\n" +
                "p=21,98 v=-71,12\n" +
                "p=67,92 v=-13,69\n" +
                "p=99,99 v=-80,-84\n" +
                "p=50,18 v=-77,-45\n" +
                "p=71,35 v=-36,-22\n" +
                "p=61,65 v=-68,10\n" +
                "p=96,25 v=56,-21\n" +
                "p=77,65 v=-20,-40\n" +
                "p=56,81 v=56,29\n" +
                "p=81,19 v=-97,-15\n" +
                "p=31,12 v=-62,-38\n" +
                "p=92,83 v=51,-4\n" +
                "p=70,23 v=-44,45\n" +
                "p=55,61 v=-27,-33\n" +
                "p=9,48 v=99,40\n" +
                "p=20,36 v=-80,-52\n" +
                "p=83,46 v=-35,-59\n" +
                "p=52,43 v=58,-88\n" +
                "p=2,88 v=-49,-34\n" +
                "p=31,99 v=54,59\n" +
                "p=76,21 v=11,31\n" +
                "p=5,51 v=-27,35\n" +
                "p=57,88 v=2,19\n" +
                "p=86,41 v=-51,60\n" +
                "p=82,58 v=-98,-96\n" +
                "p=100,70 v=-38,-85\n" +
                "p=81,47 v=-10,-30\n" +
                "p=96,88 v=82,6\n" +
                "p=24,28 v=62,98\n" +
                "p=44,71 v=-69,-50\n" +
                "p=75,11 v=-99,-52\n" +
                "p=35,80 v=16,-37\n" +
                "p=2,21 v=-27,84\n" +
                "p=80,14 v=-43,98\n" +
                "p=49,76 v=80,21\n" +
                "p=84,96 v=-82,67\n" +
                "p=32,79 v=-95,74\n" +
                "p=35,81 v=-90,-68\n" +
                "p=47,45 v=-22,-6\n" +
                "p=69,45 v=41,-83\n" +
                "p=63,21 v=1,-92\n" +
                "p=57,1 v=-98,4\n" +
                "p=34,4 v=-93,-81\n" +
                "p=46,63 v=-99,47\n" +
                "p=8,99 v=5,29\n" +
                "p=32,27 v=54,-42\n" +
                "p=27,98 v=17,-98\n" +
                "p=63,22 v=81,98\n" +
                "p=75,36 v=-86,66\n" +
                "p=58,5 v=-67,-58\n" +
                "p=82,20 v=81,-57\n" +
                "p=82,67 v=81,83\n" +
                "p=58,58 v=17,-13\n" +
                "p=18,89 v=14,-21\n" +
                "p=76,63 v=24,65\n" +
                "p=38,48 v=-85,-99\n" +
                "p=17,34 v=45,21\n" +
                "p=77,70 v=3,-50\n" +
                "p=56,82 v=17,76\n" +
                "p=64,97 v=94,-71\n" +
                "p=16,40 v=37,84\n" +
                "p=29,96 v=-61,-7\n" +
                "p=12,79 v=-95,-34\n" +
                "p=1,72 v=63,38\n" +
                "p=0,20 v=60,68\n" +
                "p=43,60 v=-6,40\n" +
                "p=20,22 v=-17,58\n" +
                "p=47,49 v=-70,94\n" +
                "p=12,29 v=-25,-32\n" +
                "p=22,69 v=-44,-22\n" +
                "p=44,78 v=31,33\n" +
                "p=86,50 v=76,-19\n" +
                "p=0,92 v=35,16\n" +
                "p=42,8 v=-39,87\n" +
                "p=30,1 v=14,61\n" +
                "p=41,57 v=18,-84\n" +
                "p=83,82 v=-97,36\n" +
                "p=72,7 v=-78,62\n" +
                "p=17,4 v=-95,12\n" +
                "p=15,15 v=60,73\n" +
                "p=80,79 v=-59,56\n" +
                "p=49,76 v=-22,-47\n" +
                "p=58,82 v=26,62\n" +
                "p=59,101 v=-82,96\n" +
                "p=78,56 v=-97,-56\n" +
                "p=19,62 v=85,-44\n" +
                "p=21,4 v=61,-91\n" +
                "p=80,66 v=-12,-27\n" +
                "p=66,17 v=96,28\n" +
                "p=57,6 v=-45,-38\n" +
                "p=24,70 v=61,-90\n" +
                "p=4,12 v=68,-58\n" +
                "p=27,37 v=15,-59\n" +
                "p=10,66 v=-33,-50\n" +
                "p=22,64 v=95,-38\n" +
                "p=47,44 v=24,-39\n" +
                "p=96,28 v=75,71\n" +
                "p=95,20 v=32,30\n" +
                "p=38,52 v=-89,10\n" +
                "p=88,94 v=96,-6\n" +
                "p=93,31 v=-38,18\n" +
                "p=61,79 v=46,-3\n" +
                "p=22,27 v=-33,5\n" +
                "p=77,62 v=81,-60\n" +
                "p=63,13 v=-94,81\n" +
                "p=39,49 v=93,89\n" +
                "p=7,37 v=-2,-89\n" +
                "p=59,2 v=24,-58\n" +
                "p=82,32 v=-4,-45\n" +
                "p=28,55 v=-70,-13\n" +
                "p=36,49 v=-93,90\n" +
                "p=16,74 v=53,-20\n" +
                "p=56,69 v=-84,93\n" +
                "p=40,25 v=-93,-89\n" +
                "p=73,60 v=-75,90\n" +
                "p=28,82 v=-47,-27\n" +
                "p=35,67 v=-8,-73\n" +
                "p=76,17 v=68,63\n" +
                "p=33,43 v=1,37\n" +
                "p=5,7 v=-52,-34\n" +
                "p=79,49 v=89,60\n" +
                "p=78,59 v=20,57\n" +
                "p=96,31 v=-93,-50\n" +
                "p=24,88 v=78,-24\n" +
                "p=12,39 v=37,-16\n" +
                "p=60,15 v=-21,45\n" +
                "p=25,17 v=-40,-45\n" +
                "p=9,63 v=-42,13\n" +
                "p=46,48 v=8,1\n" +
                "p=16,85 v=18,48\n" +
                "p=36,45 v=-41,95\n" +
                "p=62,88 v=54,6\n" +
                "p=46,57 v=-99,23\n" +
                "p=57,67 v=79,-40\n" +
                "p=88,96 v=4,-51\n" +
                "p=82,19 v=66,-2\n" +
                "p=9,73 v=68,-54\n" +
                "p=66,38 v=9,74\n" +
                "p=40,68 v=-15,83\n" +
                "p=97,24 v=-64,-55\n" +
                "p=52,56 v=-61,7\n" +
                "p=55,15 v=-53,91\n" +
                "p=98,66 v=78,-47\n" +
                "p=27,46 v=-47,4\n" +
                "p=100,19 v=36,-78\n" +
                "p=5,9 v=6,82\n" +
                "p=63,35 v=49,21\n" +
                "p=40,101 v=8,-14\n" +
                "p=98,19 v=-74,-28\n" +
                "p=74,15 v=96,-65\n" +
                "p=53,47 v=-84,34\n" +
                "p=48,14 v=94,-98\n" +
                "p=69,2 v=3,-71\n" +
                "p=38,10 v=78,35\n" +
                "p=74,76 v=-90,-60\n" +
                "p=37,68 v=55,-70\n" +
                "p=52,78 v=-31,48\n" +
                "p=6,24 v=-73,24\n" +
                "p=77,51 v=38,94\n" +
                "p=73,45 v=-42,-72\n" +
                "p=52,57 v=67,-51\n" +
                "p=12,14 v=46,39\n" +
                "p=6,11 v=65,21\n" +
                "p=29,37 v=-54,-95\n" +
                "p=41,24 v=-85,68\n" +
                "p=78,102 v=96,-24\n" +
                "p=13,25 v=-16,66\n" +
                "p=8,53 v=6,90\n" +
                "p=77,6 v=26,-71\n" +
                "p=98,45 v=-95,84\n" +
                "p=56,15 v=-27,-83\n" +
                "p=82,98 v=-35,52\n" +
                "p=24,42 v=-8,-29\n" +
                "p=16,46 v=-78,-53\n" +
                "p=25,15 v=-34,-54\n" +
                "p=27,42 v=-11,-13\n" +
                "p=12,69 v=46,3\n" +
                "p=74,20 v=43,-9\n" +
                "p=25,85 v=9,-93\n" +
                "p=65,99 v=26,-1\n" +
                "p=90,78 v=74,36\n" +
                "p=41,6 v=77,-88\n" +
                "p=85,62 v=-43,93\n" +
                "p=34,61 v=33,2\n" +
                "p=0,65 v=75,53\n" +
                "p=40,30 v=48,-36\n" +
                "p=31,1 v=47,48\n" +
                "p=24,100 v=85,99\n" +
                "p=97,24 v=-76,-67\n" +
                "p=69,59 v=21,28\n" +
                "p=82,72 v=7,96\n" +
                "p=9,10 v=68,25\n" +
                "p=26,91 v=85,49\n" +
                "p=35,86 v=39,-24\n" +
                "p=38,35 v=48,-99\n" +
                "p=99,61 v=35,-61\n" +
                "p=79,86 v=-98,99\n" +
                "p=94,40 v=50,-22\n" +
                "p=47,101 v=16,72\n" +
                "p=53,61 v=41,-3\n" +
                "p=27,101 v=-48,89\n" +
                "p=28,82 v=15,29\n" +
                "p=83,58 v=58,-10\n" +
                "p=40,38 v=-14,-82\n" +
                "p=52,78 v=79,16\n" +
                "p=45,15 v=-47,78\n" +
                "p=72,0 v=81,64\n" +
                "p=72,51 v=-95,-32\n" +
                "p=88,67 v=34,70\n" +
                "p=89,72 v=12,-90\n" +
                "p=61,31 v=25,-92\n" +
                "p=68,53 v=48,70\n" +
                "p=47,53 v=94,10\n" +
                "p=15,53 v=-72,27\n" +
                "p=80,8 v=20,-37\n" +
                "p=28,66 v=-16,-17\n" +
                "p=77,17 v=99,-11\n" +
                "p=46,10 v=-9,-66\n" +
                "p=76,71 v=78,-78\n" +
                "p=26,62 v=-85,56\n" +
                "p=87,9 v=-80,-71\n" +
                "p=67,36 v=-67,18\n" +
                "p=99,32 v=-80,61\n" +
                "p=69,87 v=73,89\n" +
                "p=6,100 v=-72,-48\n" +
                "p=40,38 v=-93,-92\n" +
                "p=89,12 v=81,-92\n" +
                "p=85,97 v=-19,-88\n" +
                "p=22,100 v=37,-4\n" +
                "p=11,45 v=-33,34\n" +
                "p=43,32 v=6,79\n" +
                "p=47,102 v=-84,52\n" +
                "p=89,94 v=-84,65\n" +
                "p=75,14 v=-3,-11\n" +
                "p=11,65 v=2,46\n" +
                "p=76,59 v=65,35\n" +
                "p=65,60 v=-75,40\n" +
                "p=92,36 v=-32,-9\n" +
                "p=69,16 v=11,15\n" +
                "p=62,63 v=32,80\n" +
                "p=47,50 v=-30,60\n" +
                "p=13,98 v=-80,-98\n" +
                "p=32,82 v=23,-74\n" +
                "p=28,6 v=-92,35\n" +
                "p=72,89 v=4,3\n" +
                "p=72,57 v=-3,80\n" +
                "p=6,44 v=89,-1\n" +
                "p=33,19 v=31,-78\n" +
                "p=43,101 v=-39,78\n" +
                "p=46,51 v=-30,-63\n" +
                "p=24,37 v=-79,-82\n" +
                "p=51,46 v=64,44\n" +
                "p=0,36 v=13,-99\n" +
                "p=71,6 v=72,9\n" +
                "p=40,98 v=78,-81\n" +
                "p=36,33 v=96,-14\n" +
                "p=63,100 v=95,12\n" +
                "p=12,42 v=-41,87\n" +
                "p=66,48 v=-52,77\n" +
                "p=5,77 v=36,23\n" +
                "p=94,54 v=80,24\n" +
                "p=77,71 v=-50,1\n" +
                "p=81,20 v=89,-85\n" +
                "p=9,13 v=-95,42\n" +
                "p=13,78 v=71,20\n" +
                "p=36,43 v=-21,88\n" +
                "p=56,94 v=87,69\n" +
                "p=91,53 v=21,57\n" +
                "p=80,84 v=-74,-44\n" +
                "p=19,90 v=-87,96\n" +
                "p=13,83 v=-63,-51\n" +
                "p=14,91 v=5,56\n" +
                "p=23,1 v=-72,-15\n" +
                "p=50,44 v=71,-73\n" +
                "p=6,49 v=45,67\n" +
                "p=4,0 v=-9,-91\n" +
                "p=70,8 v=-98,45\n" +
                "p=30,42 v=-78,61\n" +
                "p=87,74 v=-97,26\n" +
                "p=35,89 v=92,-32\n" +
                "p=23,61 v=94,41\n" +
                "p=87,21 v=-82,28";

        String input1 = "p=0,4 v=3,-3\n" +
                "p=6,3 v=-1,-3\n" +
                "p=10,3 v=-1,2\n" +
                "p=2,0 v=2,-1\n" +
                "p=0,0 v=1,3\n" +
                "p=3,0 v=-2,-2\n" +
                "p=7,6 v=-1,-3\n" +
                "p=3,0 v=-1,-2\n" +
                "p=9,3 v=2,3\n" +
                "p=7,3 v=-1,2\n" +
                "p=2,4 v=2,-3\n" +
                "p=9,5 v=-3,-3";

        System.out.println(christMasTree(input, 101, 103));








    }
}
