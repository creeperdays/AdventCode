import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day8 {

    public static int number2(String input) {
        char[][] grid = parseInput(input);

        Set<Coord> set = new HashSet<Coord>();

        ArrayList<Coord> antennas = antennas(grid);

        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Coord c = new Coord(i, j);

                for (Coord antenna : antennas) {
                    if (isAnti2(grid, c, antenna)) {
                        set.add(c);
                    }
                }


            }
        }

        return set.size();
    }



    public static boolean isAnti2(char[][] grid, Coord anti, Coord antenna) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                Coord c = new Coord(i, j);

                if (grid[i][j] == grid[antenna.getX()][antenna.getY()] && !c.equals(antenna)) {
                    if (colinear(anti, antenna, c)) {
                        return true;

                    }
                }


            }
        }

        return false;

    }


    // Below is Part 1 of my code

    public static int number(String input) {
        char[][] grid = parseInput(input);

        Set<Coord> set = new HashSet<Coord>();

        ArrayList<Coord> antennas = antennas(grid);

        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Coord c = new Coord(i, j);

                for (Coord antenna : antennas) {
                    if (isAnti(grid, c, antenna)) {
                        set.add(c);
                    }
                }


            }
        }

        return set.size();
    }

    public static boolean isAnti(char[][] grid, Coord anti, Coord antenna) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                Coord c = new Coord(i, j);

                if (grid[i][j] == grid[antenna.getX()][antenna.getY()] && !c.equals(antenna)) {
                    if (colinear(anti, antenna, c)) {
                        double d1 = distance(anti, antenna);
                        double d2 = distance(anti, c);

                        if (d2 == 2*d1 || d1 == 2*d2) {
                            return true;
                        }

                    }
                }


            }
        }

        return false;

    }

    public static ArrayList<Coord> antennas(char[][] grid) {
        ArrayList<Coord> a = new ArrayList<Coord>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '.') {
                    Coord c = new Coord(i, j);
                    a.add(c);
                }
            }
        }

        return a;
    }

    public static double distance(Coord c1, Coord c2) {
        return Math.sqrt(Math.pow(c1.getY() - c2.getY(), 2) + Math.pow(c1.getX() - c2.getX(), 2));
    }

    public static boolean colinear(Coord c1, Coord c2, Coord c3) {
        int l = (c2.getY() - c1.getY())*(c3.getX() - c2.getX());
        int r = (c3.getY() - c2.getY())*(c2.getX() - c1.getX());

        return r == l;
    }

    public static char[][] parseInput(String input) {
        String[] s = input.split("\n");

        char[][] grid = new char[s.length][s[0].length()];

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[0].length(); j++) {
                String s1 = s[i];

                char c = s1.charAt(j);

                grid[i][j] = c;
            }
        }

        return grid;
    }

    public static class Coord {
        private int x;

        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            try {
                Coord c = (Coord) obj;

                return c.getX() == this.getX() && c.getY() == this.getY();

            } catch (ClassCastException e) {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        String input = "...............................s..................\n" +
                "..................s..............q.............p..\n" +
                ".....a............................................\n" +
                "........c......Y.......Q..........................\n" +
                "............................................4.....\n" +
                "........Y.........y............m..........4.......\n" +
                "......................Y...s..........S............\n" +
                ".........................................S........\n" +
                "...............N.............y....................\n" +
                "...........a.......y..................1...........\n" +
                "................................................S.\n" +
                "...c........k.............q....t............S.....\n" +
                ".............................qM...................\n" +
                "........a.........................................\n" +
                "..................................................\n" +
                "..................................................\n" +
                "..c..........k...Q..q....P........................\n" +
                "5.................Q...................8...........\n" +
                "......yc..........................................\n" +
                "........................E............4............\n" +
                ".........6........................u..p.....4......\n" +
                ".........5.............P..n......1.........N......\n" +
                "6..............................1.........J.t......\n" +
                "..6..................................3.u..t.....p.\n" +
                "....5...k..........................u..............\n" +
                ".......................E..................u....x..\n" +
                "..................E.................x.............\n" +
                "...k..................P.............3.............\n" +
                "...........0.....9.5...........E.........31e....N.\n" +
                "......0.................................N.........\n" +
                ".................CU.....................t....x....\n" +
                "......7....................e......................\n" +
                "....0..........K......C...........................\n" +
                ".....6....j......M............................J...\n" +
                "......K.................................p.........\n" +
                ".....9........................U...................\n" +
                "............................3....n................\n" +
                ".............K.........2.....C..................x.\n" +
                "....................P........UJ...................\n" +
                ".....0......X...C.........T..............U........\n" +
                ".......M.....8j....7.............2........Q.......\n" +
                "9...............K.................................\n" +
                "....e.....8.........................2.A.m.........\n" +
                "..e......8.........s...n..........................\n" +
                ".....................................T..nm........\n" +
                "...................X............2.........m......A\n" +
                "......................X..j....................T...\n" +
                ".........7..M......j.............T................\n" +
                "....9...7....................................A....\n" +
                "..........................................A.......";

        System.out.println(number(input));
        System.out.println(number2(input));
    }
}
