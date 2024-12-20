import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day15 {

    public static long calculate1(Grid[][] grid) {
        long sum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == Grid.LEFT) {
                    int x = Math.min(j, grid[0].length - 1 - (j+1));

                    int y = Math.min(i, grid.length - 1 - (i + 1));

                    long gps = 100L*i + j;
                    sum = sum + gps;
                }
            }
        }

        return sum;

    }

    public static void followDirections(Grid[][] grid, ArrayList<Direction> directions) {

        for (Direction d : directions) {

            Coord intial = findRobot(grid);
            if (d == Direction.UP) {
                moveUp(grid, intial);
            } else if (d == Direction.RIGHT) {
                moveRight(grid, intial);
            } else if (d == Direction.DOWN) {
                moveDown(grid, intial);
            } else if (d == Direction.LEFT) {
                moveLeft(grid, intial);
            }


        }
    }

    public static void moveDown(Grid[][] grid, Coord current) {
        ArrayList<Coord> coords = new ArrayList<Coord>();

        if (bfs(grid, Direction.DOWN, current, coords)) {
            HashMap<Coord, Grid> map = new HashMap<Coord, Grid>();

            for (Coord c : coords) {
                map.put(c, grid[c.getX()][c.getY()]);
            }

            for (Coord c : coords) {
                grid[c.getX()][c.getY()] = Grid.EMPTY;
            }

            for (Coord c : coords) {
                grid[c.getX() + 1][c.getY()] = map.get(c);
            }
        }
    }

    public static void moveRight(Grid[][] grid, Coord current) {
        ArrayList<Coord> coords = new ArrayList<Coord>();

        if (bfs(grid, Direction.RIGHT, current, coords)) {

            HashMap<Coord, Grid> map = new HashMap<Coord, Grid>();

            for (Coord c : coords) {
                map.put(c, grid[c.getX()][c.getY()]);
            }

            for (Coord c : coords) {
                grid[c.getX()][c.getY()] = Grid.EMPTY;
            }

            for (Coord c : coords) {
                grid[c.getX()][c.getY() + 1] = map.get(c);
            }
        }
    }

    public static void moveLeft(Grid[][] grid, Coord current) {
        ArrayList<Coord> coords = new ArrayList<Coord>();

        if (bfs(grid, Direction.LEFT, current, coords)) {
            HashMap<Coord, Grid> map = new HashMap<Coord, Grid>();

            for (Coord c : coords) {
                map.put(c, grid[c.getX()][c.getY()]);
            }

            for (Coord c : coords) {
                grid[c.getX()][c.getY()] = Grid.EMPTY;
            }

            for (Coord c : coords) {
                grid[c.getX()][c.getY() - 1] = map.get(c);
            }
        }


    }

    public static void moveUp(Grid[][] grid, Coord current) {
        ArrayList<Coord> coords = new ArrayList<Coord>();
        if (bfs(grid, Direction.UP, current, coords)) {

            HashMap<Coord, Grid> map = new HashMap<Coord, Grid>();

            for (Coord c : coords) {
                map.put(c, grid[c.getX()][c.getY()]);
            }

            for (Coord c : coords) {
                grid[c.getX()][c.getY()] = Grid.EMPTY;
            }

            for (Coord c : coords) {
                grid[c.getX() - 1][c.getY()] = map.get(c);
            }

        }
    }

    public static Coord findRobot(Grid[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == Grid.ROBOT) {
                    Coord c = new Coord(i, j);
                    return c;
                }
            }
        }

        return null;
    }


    public static boolean bfs(Grid[][] grid, Direction d, Coord current, ArrayList<Coord> coords) {

        ArrayDeque<Coord> q = new ArrayDeque<Coord>();
        ArrayList<Coord> visited = new ArrayList<Coord>();

        if (d == Direction.UP) {
            q.addLast(current);
            visited.add(current);
            coords.add(current);

            while (!q.isEmpty()) {
                Coord w = q.removeFirst();

                ArrayList<Coord> adjacent = new ArrayList<Coord>();

                if (grid[w.getX()][w.getY()] == Grid.LEFT) {
                    Coord l = new Coord(w.getX(), w.getY() + 1);
                    adjacent.add(l);
                }

                if (grid[w.getX()][w.getY()] == Grid.RIGHT) {
                    Coord r = new Coord(w.getX(), w.getY() - 1);
                    adjacent.add(r);
                }

                if (grid[w.getX() - 1][w.getY()] == Grid.WALL) {
                    return false;
                }

                if (grid[w.getX() - 1][w.getY()] != Grid.EMPTY) {
                    Coord up = new Coord(w.getX() - 1, w.getY());
                    adjacent.add(up);
                }

                for (Coord c : adjacent) {
                    if (!visited.contains(c)) {
                        visited.add(c);
                        coords.add(c);
                        q.addLast(c);
                    }
                }



            }
        } else if (d == Direction.RIGHT) {
            q.addLast(current);
            visited.add(current);
            coords.add(current);

            while (!q.isEmpty()) {
                Coord w = q.removeFirst();


                ArrayList<Coord> adjacent = new ArrayList<Coord>();

                if (grid[w.getX()][w.getY() + 1] == Grid.WALL) {
                    return false;
                }

                if (grid[w.getX()][w.getY() + 1] != Grid.EMPTY) {
                    Coord r = new Coord(w.getX(), w.getY() + 1);
                    adjacent.add(r);
                }

                for (Coord c : adjacent) {
                    if (!visited.contains(c)) {
                        visited.add(c);
                        coords.add(c);
                        q.addLast(c);
                    }
                }
            }

        } else if (d == Direction.DOWN) {
            q.addLast(current);
            visited.add(current);
            coords.add(current);

            while (!q.isEmpty()) {
                Coord w = q.removeFirst();

                ArrayList<Coord> adjacent = new ArrayList<Coord>();

                if (grid[w.getX()][w.getY()] == Grid.LEFT) {
                    Coord r = new Coord(w.getX(), w.getY() + 1);
                    adjacent.add(r);
                }

                if (grid[w.getX()][w.getY()] == Grid.RIGHT) {
                    Coord l = new Coord(w.getX(), w.getY() - 1);
                    adjacent.add(l);
                }

                if (grid[w.getX() + 1][w.getY()] == Grid.WALL) {
                    return false;
                }

                if (grid[w.getX() + 1][w.getY()] != Grid.EMPTY) {
                    Coord down = new Coord(w.getX() + 1, w.getY());
                    adjacent.add(down);
                }

                for (Coord c : adjacent) {
                    if (!visited.contains(c)) {
                        visited.add(c);
                        coords.add(c);
                        q.addLast(c);
                    }
                }

            }

        } else if (d == Direction.LEFT) {
            q.addLast(current);
            visited.add(current);
            coords.add(current);

            while (!q.isEmpty()) {
                Coord w = q.removeFirst();

                ArrayList<Coord> adjacent = new ArrayList<Coord>();

                if (grid[w.getX()][w.getY() - 1] == Grid.WALL) {
                    return false;
                }

                if (grid[w.getX()][w.getY() - 1] != Grid.EMPTY) {
                    Coord l = new Coord(w.getX(), w.getY() - 1);
                    adjacent.add(l);
                }

                for (Coord c : adjacent) {
                    if (!visited.contains(c)) {
                        visited.add(c);
                        coords.add(c);
                        q.addLast(c);
                    }
                }

            }
        }

        return true;

    }







    public static boolean canMove2(Grid[][] grid, Coord current, Direction d, ArrayList<Coord> coords) {
        coords.add(current);
        if (d == Direction.UP) {
            Coord up = new Coord(current.getX() -  1, current.getY());
            if (grid[current.getX()][current.getY()] == Grid.LEFT) {
                if (grid[up.getX()][up.getY()] == Grid.EMPTY) {
                    if (grid[up.getX()][up.getY() + 1] == Grid.EMPTY) {
                        Coord r = new Coord(current.getX(), current.getY() + 1);
                        coords.add(r);
                        return true;
                    } else if (grid[up.getX()][up.getY()] == Grid.WALL) {
                        return false;
                    } else {
                        Coord r = new Coord(current.getX(), current.getY() + 1);
                        coords.add(r);
                        return canMove2(grid, up, d, coords);
                    }
                } else if (grid[up.getX()][up.getY()] == Grid.WALL) {
                    return false;
                } else {
                    return canMove2(grid, up, d, coords);
                }
            } else if (grid[current.getX()][current.getY()] == Grid.RIGHT) {
                if (grid[up.getX()][up.getY()] == Grid.EMPTY) {
                    if (grid[up.getX()][up.getY() - 1] == Grid.EMPTY) {
                        return true;
                    } else if (grid[up.getX()][up.getY() - 1] == Grid.WALL) {
                        return false;
                    } else {
                        return canMove2(grid, up, d, coords);
                    }
                } else if (grid[up.getX()][up.getY()] == Grid.WALL) {
                    return false;
                } else {
                    return canMove2(grid, up, d, coords);
                }
            } else {
                if (grid[up.getX()][up.getY()] == Grid.EMPTY) {
                    return true;
                } else if (grid[up.getX()][up.getY()] == Grid.WALL) {
                    return false;
                } else {
                    return canMove2(grid, up, d, coords);
                }
            }
        } else if (d == Direction.RIGHT) {
            Coord right = new Coord(current.getX(), current.getY() + 1);

            if (grid[right.getX()][right.getY()] == Grid.EMPTY) {
                return true;
            } else if (grid[right.getX()][right.getY()] == Grid.WALL) {
                return false;
            } else {
                return canMove2(grid, right, d, coords);
            }
        } else if (d == Direction.DOWN) {
            Coord down = new Coord(current.getX() + 1, current.getY());

            if (grid[current.getX()][current.getY()] == Grid.LEFT) {
                if (grid[down.getX()][down.getY()] == Grid.EMPTY) {
                    if(grid[down.getX()][down.getY() + 1] == Grid.EMPTY) {
                        return true;
                    } else if (grid[down.getX()][down.getY() + 1] == Grid.WALL) {
                        return false;
                    } else {
                        return canMove2(grid, down, d, coords);
                    }
                } else if (grid[down.getX()][down.getY() + 1] == Grid.WALL) {
                    return false;
                } else {
                    return canMove2(grid, down, d, coords);
                }
            } else if (grid[current.getX()][current.getY()] == Grid.RIGHT) {
                if (grid[down.getX()][down.getY()] == Grid.EMPTY) {
                    if (grid[down.getX()][down.getY() - 1] == Grid.EMPTY) {
                        return true;
                    } else if (grid[down.getX()][down.getY() - 1] == Grid.WALL) {
                        return false;
                    } else {
                        return canMove2(grid, down, d, coords);
                    }
                } else if (grid[down.getX()][down.getY()] == Grid.WALL) {
                    return false;
                } else {
                    return canMove2(grid, down, d, coords);
                }
            } else {
                if (grid[down.getX()][down.getY()] == Grid.EMPTY) {
                    return true;
                } else if (grid[down.getX()][down.getY()] == Grid.WALL) {
                    return false;
                } else {
                    return canMove2(grid, down, d, coords);
                }
            }

        } else { //Direction is left
            Coord left = new Coord(current.getX(), current.getY() - 1);

            if (grid[left.getX()][left.getY()] == Grid.EMPTY) {
                return true;
            } else if (grid[left.getX()][left.getY()] == Grid.WALL) {
                return false;
            } else {
                return canMove2(grid, left, d, coords);
            }
        }

    }

    public static void visual(Grid[][] grid) {
        char[][] c = new char[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == Grid.EMPTY) {
                    c[i][j] = '.';
                } else if (grid[i][j] == Grid.WALL) {
                    c[i][j] = '#';
                } else if (grid[i][j] == Grid.BOX) {
                    c[i][j] = 'O';
                } else if (grid[i][j] == Grid.ROBOT) {
                    c[i][j] = '@';
                } else if (grid[i][j] == Grid.LEFT) {
                    c[i][j] = '[';
                } else {
                    c[i][j] = ']';
                }
            }
        }

        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }

    }



    public static Grid[][] wideGrid(Grid[][] grid) {

        Grid[][] newGrid = new Grid[grid.length][grid[0].length*2];

        int k = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length ; j++) {
                if (grid[i][j] == Grid.BOX) {
                    newGrid[i][k] = Grid.LEFT;
                    newGrid[i][k+1] = Grid.RIGHT;

                    k = k+2;
                } else if (grid[i][j] == Grid.WALL) {
                    newGrid[i][k] = Grid.WALL;
                    newGrid[i][k+1] = Grid.WALL;

                    k = k+2;
                } else if (grid[i][j] == Grid.EMPTY) {
                    newGrid[i][k] = Grid.EMPTY;
                    newGrid[i][k + 1] = Grid.EMPTY;

                    k = k+2;
                } else if (grid[i][j] == Grid.ROBOT) {
                    newGrid[i][k] = Grid.ROBOT;

                    newGrid[i][k+1] = Grid.EMPTY;

                    k = k+2;
                }
            }

            k = 0;
        }

        return newGrid;

    }

    //Below is part 1 of the code

    public static long calculate(Grid[][] grid) {
        long sum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == Grid.BOX) {
                   long gps = 100L*i +j;

                   sum = sum + gps;

                }
            }
        }

        return sum;

    }

    public static void follow(Grid[][] grid, Coord intial,  ArrayList<Direction> directions) {

        Coord current = intial;

        for (Direction d : directions) {
            if (canMove(grid, current, d)) {
                System.out.println(d);
                move(grid, current, d, Grid.EMPTY);

                for (int i = 0; i < grid.length; i++) {
                    System.out.println(Arrays.toString(grid[i]));
                }
                System.out.println(" ");

                if (d == Direction.UP) {
                    current = new Coord(current.getX() - 1, current.getY());
                } else if (d == Direction.RIGHT) {
                    current = new Coord(current.getX(), current.getY() + 1);
                } else if (d == Direction.DOWN) {
                    current = new Coord(current.getX() + 1, current.getY());
                } else {
                    current = new Coord(current.getX(), current.getY() - 1);
                }
            }

        }


    }




    /**
     * Moves all items and the robot by one point
     * @param grid the grid
     * @param point the current point which is going to be moved
     * @param d the direction in which the point is going to be moved in
     */
    public static void move(Grid[][] grid, Coord point, Direction d, Grid previous) {

        if (d == Direction.UP) {
            Coord up  =  new Coord(point.getX() - 1, point.getY());

            if (grid[up.getX()][up.getY()] == Grid.EMPTY) {
                if (previous == Grid.EMPTY) {
                    Grid temp = grid[point.getX()][point.getY()];
                    grid[point.getX()][point.getY()] = Grid.EMPTY;

                    grid[up.getX()][up.getY()] = temp;

                } else {
                    grid[up.getX()][up.getY()] = previous;

                }
            } else { // means that there is a box above
                if (previous == Grid.EMPTY) {
                    Grid temp = grid[up.getX()][up.getY()];

                    Grid current = grid[point.getX()][point.getY()];

                    grid[point.getX()][point.getY()] = Grid.EMPTY;

                    grid[up.getX()][up.getY()] = current;

                    move(grid, up, d, temp);
                } else {
                    Grid p = grid[point.getX()][point.getY()];
                    move(grid, up, d, previous);
                }
            }
        } else if (d == Direction.RIGHT) {
            Coord right = new Coord (point.getX(), point.getY() + 1);

            if (grid[right.getX()][right.getY()] == Grid.EMPTY) {
                if (previous == Grid.EMPTY) {
                    Grid temp = grid[point.getX()][point.getY()];

                    grid[point.getX()][point.getY()] = Grid.EMPTY;

                    grid[right.getX()][right.getY()] = temp;
                } else {
                    grid[right.getX()][right.getY()] = previous;
                }
            } else { // means that there is a box to the right
                if (previous == Grid.EMPTY) {
                    Grid temp = grid[right.getX()][right.getY()];

                    Grid current = grid[point.getX()][point.getY()];

                    grid[point.getX()][point.getY()] = Grid.EMPTY;

                    grid[right.getX()][right.getY()] = current;

                    move(grid, right, d, temp);
                } else {
                    Grid p = grid[point.getX()][point.getY()];
                    move(grid, right, d, previous);
                }
            }
        } else if (d == Direction.DOWN) {
            Coord down = new Coord(point.getX() + 1, point.getY());
            if (grid[down.getX()][down.getY()] == Grid.EMPTY) {
                if (previous == Grid.EMPTY) {
                    Grid temp = grid[point.getX()][point.getY()];

                    grid[point.getX()][point.getY()] = Grid.EMPTY;

                    grid[down.getX()][down.getY()] = temp;
                } else {
                    grid[down.getX()][down.getY()] = previous;
                }
            } else {
                if (previous == Grid.EMPTY) {
                    Grid temp = grid[down.getX()][down.getY()];

                    Grid current = grid[point.getX()][point.getY()];

                    grid[point.getX()][point.getY()] = Grid.EMPTY;

                    grid[down.getX()][down.getY()] = current;

                    move(grid, down, d, temp);
                } else {
                    Grid p = grid[point.getX()][point.getY()];
                    move(grid, down, d, previous);
                }
            }
        } else if (d == Direction.LEFT) {
            Coord left = new Coord(point.getX(), point.getY() - 1);
            if (grid[left.getX()][left.getY()] == Grid.EMPTY) {
                if (previous == Grid.EMPTY) {
                    Grid temp = grid[point.getX()][point.getY()];

                    grid[point.getX()][point.getY()] = Grid.EMPTY;

                    grid[left.getX()][left.getY()] = temp;
                } else {
                    grid[left.getX()][left.getY()] = previous;
                }
            } else {
                if (previous == Grid.EMPTY) {
                    Grid temp = grid[left.getX()][left.getY()];

                    Grid current = grid[point.getX()][point.getY()];

                    grid[point.getX()][point.getY()] = Grid.EMPTY;

                    grid[left.getX()][left.getY()] = current;

                    move(grid, left, d, temp);
                } else {
                    Grid p = grid[point.getX()][point.getY()];
                    move(grid, left, d, previous);
                }
            }
        }

    }

    /**
     * Returns whether a point can be moved or not in a certain direction
     * @param grid the grid
     * @param point the current point
     * @param d the direction that wants to be moved in
     * @return a boolean value of whether the point can be moved in the direction or not
     */
    public static boolean canMove(Grid[][] grid, Coord point, Direction d) {
        if (d == Direction.UP) {
            Coord up  =  new Coord(point.getX() - 1, point.getY());
            if (grid[up.getX()][up.getY()] == Grid.EMPTY) {
                return true;
            } else if (grid[up.getX()][up.getY()] == Grid.WALL) {
                return false;
            } else { // Must be a box
                return canMove(grid, up, d);
            }
        } else if (d == Direction.RIGHT) {
            Coord right = new Coord (point.getX(), point.getY() + 1);

            if (grid[right.getX()][right.getY()] == Grid.EMPTY) {
                return true;
            } else if (grid[right.getX()][right.getY()] == Grid.WALL) {
                return false;
            } else {
                return canMove(grid, right, d);
            }
        } else if (d == Direction.DOWN) {
            Coord down = new Coord(point.getX() + 1, point.getY() );

            if (grid[down.getX()][down.getY()] == Grid.EMPTY) {
                return true;
            } else if (grid[down.getX()][down.getY()] == Grid.WALL) {
                return false;
            } else {
                return canMove(grid, down, d);
            }
        } else { //The direction is left
            Coord left = new Coord(point.getX(), point.getY() - 1);

            if (grid[left.getX()][left.getY()] == Grid.EMPTY) {
                return true;
            } else if (grid[left.getX()][left.getY()] == Grid.WALL) {
                return false;
            } else {
                return canMove(grid, left, d);
            }
        }

    }

    public static ArrayList<Direction> parseInstructions(String input) {
        String[] s = input.split("\n");

        ArrayList<Direction> directions = new ArrayList<Direction>();

        for (String s1 : s) {
            for (int i = 0; i < s1.length(); i++) {
                char c = s1.charAt(i);

                if (c == '^') {
                    directions.add(Direction.UP);
                } else if (c == 'v') {
                    directions.add(Direction.DOWN);
                } else if (c == '>') {
                    directions.add(Direction.RIGHT);
                } else {
                    directions.add(Direction.LEFT);
                }



            }
        }

        return directions;

    }

    // Finds the initial coord that the robot is on;
    public static Coord findInitial(String input) {

        String[] s = input.split("\n");

        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];

            for (int j = 0; j < s1.length(); j++) {
                char c = s1.charAt(j);

                if (c == '@') {
                    Coord c1 = new Coord(i, j);
                    return c1;
                }
            }

        }

        return null;

    }

    public static Grid[][] parseGrid(String input) {
        String[] s = input.split("\n");

        Grid[][] grid = new Grid[s.length][s[0].length()];

        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            for (int j = 0; j < s1.length(); j++) {
                char c = s1.charAt(j);
                if (c == '#') {
                    grid[i][j] = Grid.WALL;
                } else if (c == '.') {
                    grid[i][j] = Grid.EMPTY;
                } else if (c == 'O') {
                    grid[i][j] = Grid.BOX;
                } else {
                    grid[i][j] = Grid.ROBOT;
                }
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

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
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

        @Override
        public String toString() {
            return "(" + x + "," +  y + ")";
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public enum Grid {
        EMPTY,
        ROBOT,
        BOX,
        LEFT,
        RIGHT,
        WALL;
    }

    public static void main(String[] args) {
        String grid = "##################################################\n" +
                "#.#..OO.OO...O..OO..#.....O....#OO.#....#..OO....#\n" +
                "#O..O......O#...O.....#..OO..O....O...OO##O..O#..#\n" +
                "##.O.O...O.#.O...O#O...O.#O..#..#....#O.O.OOO...##\n" +
                "###...O...O.OO..OO....O.........OO..OO..#..O...#O#\n" +
                "#OOOOO...O.OO.O..OO.O.....O.O...OO..O#......O.O.##\n" +
                "#..#..#..##.OOO.#O..O....................O#.O..#.#\n" +
                "#.....O.O.O.OO..#.#.O...OOO.O.#.........O.#......#\n" +
                "#........#....O..O.OOO.OOO..O...O.O.OOO.O...O.O.O#\n" +
                "#..O..O........O..O#...O.O....#..O......O..O.#O..#\n" +
                "#O....O.O...O.........#...#.....OO....O......OOOO#\n" +
                "#..O.....OOO#O..O.O.#.............O.#OO....OO.O.O#\n" +
                "#...OO.....#....OO......O#..OOO....O.O..#OO......#\n" +
                "#....O#.......O....#.#.O..#.OO#O..O...OO..#O#O..O#\n" +
                "#..O..O.........O..#.OO.#O......OO.OO......O...#.#\n" +
                "#...............#...#.#...O#............O.......O#\n" +
                "#OO...O.OOOO#O.....O........OO.OOO.OO....OO#.#...#\n" +
                "#.O.#O..OO.OO..O..#..#..#..O...OOOO...........O#.#\n" +
                "#...OO......O...O..O...O.O.O#..OOO......O#..O....#\n" +
                "##..OO....OO.....#.O...O..OO.O.O..O##..O....#....#\n" +
                "#......O...O....#OO..#.OO.O.O.O.#.OO...O....OO.#.#\n" +
                "##.....#.OO.O.............#O......OOO#..........O#\n" +
                "#.........O...#OO.....#.....O..#.....OO.O.#O.....#\n" +
                "#.........#O...OO..O...#.OOO......O....O...O.O.#.#\n" +
                "#....O..#...OO.OOO......@..#..O.O..O.O.......#O.O#\n" +
                "#.OO.#O.O..#..O.......OO.....#.O.O...##O.......#.#\n" +
                "#..O....O.O.....#.O.OO..O...#OOO.O...OO.O..#.....#\n" +
                "#.#.O.OO.#...O.....#...O#.O..#O#O.OO............##\n" +
                "#....#.O....O..O...OO...........O.O.OO.O.OO..#.O.#\n" +
                "#O..O.O.O.OOO.O..O...#O.O..OOO.O#O...#.O...OO.O.O#\n" +
                "#OO....#.....O.O..#.#.O.O..........OO.#..O#......#\n" +
                "#..O......O#..O...##O.O..O.O.#...O.OO.O....O..O..#\n" +
                "#.#..O.O.....#O.O...OOOO....O#O.OO#....O......OOO#\n" +
                "##..#...OO###.O..O.OOO.O.O.........#..O..O.O..O..#\n" +
                "##...OOOOO.OOOO#..O##...O.....O.O..OO....OOO#....#\n" +
                "#....O.O..OO..#O..#...O........OOO.O.##.O.#.##..O#\n" +
                "#.....OO..OO..O...#.....O.......#..........O.#.#O#\n" +
                "##..#.....#.O...O#..O.....#.....#..#...OO....O...#\n" +
                "#.OO...O.OO..#O#...#...O.O.##..O..#O#.........O..#\n" +
                "#......O.O..O.OO.#.O.O..O...OOO....#.O.OO.O..O...#\n" +
                "#..#...O...O..OOO..............O.#...O..O....O.###\n" +
                "#........OOO...O..#.....OO...OO....O......#.OOO#O#\n" +
                "#.##.OO..O.....##.OO.OO.#.O.##..#..........OO....#\n" +
                "#..O.O..O.O.#.OOO...#....O..#..#.O.O....#.O..O..##\n" +
                "#O..O..O..O.O.............OOO.O..O..O..O..O.OO##.#\n" +
                "#...O#OO..OO...O.....O..#O.O...O...O.OO...OO.....#\n" +
                "#..O...#..#.O....#.....#.OOO#........O.....#.O...#\n" +
                "#.OO...OOOO.....#O......#.....O..O.O.O.#.........#\n" +
                "#.....O.O#.O..OO......O..O.O.O....##O#..O.#...#..#\n" +
                "##################################################";

        String moves = "vvv>>><v^v<v>^<v>^^v<>v>^>^^>v<v^^^<^<<>v<^vvv<^<v^><><^^<v>^^^^^><<>v<vvv^>^^<^vv<>v^v<^<<^<<v^vvv>>><>v<>vv>^<><^^v^>^>^^^<^^^>^^<<<><v>vv^^<>><v^^<vv>^vv^v^<v^>>>>^v<^><vv<^<<<<>><^^^v^v<>v>^v^<^^>>^^>^<<^v<^<>v<>v<vv><^<^<^><>>><<<>>v>>>>v>v^<><<<><>^<>^<^v>><v^v>v<^^<<>vv^v^<v<<^v<v<^^vvv<^<^v<^<^^>v^>v^>vv<v>>>vv>>^>^v<v<>>><v<<^<>v<>v^^^v<>>v^<><<vv>vv^<^v^^>^vv<>^<^vvv<<<v<>^^><vv<<<>>^><>>>>^^^^^vv>><>^vv<>v><v>v^^^>>v<<><v^v<^<^^vv<<^v>>v^<v<^<<^><>vv<vvv^><><^v^v<^><>>>^^>^>^^<v<<^>>vv<<v^^><^<vv<vv<<vv<<><^^^<v<<^<<^>>>><<<>^<<vv>^<>vv^<<^>^<^<v^v>v^<<><>>v^<>vvv>^^<>^<v^<v>v^><v<^v>^vv<>^<^<>^<^><<<v^><v^>^>^^<^<v^^><<^v^^>>>v<>vv<<v<<<^><vv<>><>>^><vvv^vv>^<^vv>v<^^>^>>^>v^<<<>><^^vv>^^<^^>>^<<v>><>v^><^^v^^v>^<<>>v<v^v^><<><^>^>^<^<>^<<v>vvv^^^<<>>><v^<>^^><^v^>^<v>^>v><>><^>v^><^<><vv^<<<^><<>vv><^^><v<^v>v><<<><<>^><v^<<>^><v>^^v<>v>^^^<><><^v>>v><<^<v>>v^<^<>^^v>v>^>>>v><>^^>^^v>v^^v<v^>>vvvv^v^<^vv^>>^^<v<v><^^^><><v<>>v>v<v^>^vv<>>^^>^vvv>v^^v>>^<^>>^>v^>>v><vvvv^>v\n" +
                "v<^<><v^<v<vv<<v>v^<>^><vv<<<<v^>^>v<^^>v<v>v<>>^>>v<^^^v^^>v^><v>^^>>>^>v>>>v^><><<><v^v^vv^^^^v<<^>>v<^><<v^>v^>^^<v<<><^v<><v^^<vv^^<v^>^>>^v<v^v^<v^v<<^<^>v^<><<v><vvv^<^^^^<><><^^^vv^^>vv^>v<v^^<<^^<<<<<>^v^vv^vv^>>>vv<^<vv<^v<><^^>>v<<<<^^<v>>>v^><>^>>vv>>v<<><v>>v>>><<v^<v>>^<>v><^<<^<<<<v^^^vv<^>v<v<^><><<>^v^vv><v>^vv><<^v^v>vv^v<v>>v>^>v>>><<><><><v^<v>^<>^<>^v^v<>vv>>^<>><>^v^v<<^>v<<v<^>><>>^^v>v^vv><vv>^^>^v^>v>><<v><vvvv><>>>^<^v>v<<>>v^<<v>^<><v^v<><^^v<>^vv>^>v<^vvv<><>>vv>^^<v>^v<<v^^^<><>>>^<<>vv<><v><<>v<v>v>>^v>>v<vvv><<^>^^<^<>>>>v<^v^^^<<<<<^>^>>>^<^<^><vv<>^>^^v^><v^>>^<<^>^<v^^vv>v<>v><v^><>>>vvv><>v<vv^>>v<<><^<>>v>>><vv<>v>><vvvvv^><<<^><>>^^><v^<^^^v<^<^>^<v^<v^^^><^<v>v>^<<^<>^>v^<v>vvvv>v>>>v^vvvv>><>^>>><>vvv^<^<v>^^^>>^vv>>^v>>^>^^>^>>vv^^>><^<>>^^^^<^v><>^>^v>>><^vvv^vvvv<>^><^vv>>^^v<>^<<^v<^^<v>>v^<v>^><^><vv<<^<<<v^vv<<>v<v^^<><>^^>^v<<^<><v^v^><v>^><<v>^vv>^v^^v^><<vv>v><vvv><>^<>v>v>^<<^<<>vvv>>>><<>vv<^<><>^<^v><^<vvv^<>^^>>vv>v<>v>^^^vv<><<^>v<v^v\n" +
                "<>^<^^v<vv>^>>^<^>^<v^vv<v<<v<^>>v^^v>^^v>v<<<<^<<v^<v>>v>v^v<^^<><<^v>^^^<v^^><v>vv<^^^<>>^<><^v><>><><^>^<<<v<<v>v<<v^vv^v<v>^^>v<v>><>v<v<<^>v>^<<v><<>^^^>^v<vvv<^<>><<>>v<^<>^v<>v>v>v<<<><^<^<>^<^<v>^<v>>>^<v>>>v<^v<^^^^>^<v>^><^><<><v>^>vv^>^v<<>>>v^v<>^<v<><><><<><<>^>v<>><<>^v<^<<<^v^>v^^^^>>><<^v>>>^><^v^^<<^^v><>>v^^^>>v<>^>vv<vvv^>>>>v<v<v^<^<>>^v<vv>^>^v^vvv<<^^><v<^v^v<vv^v>><^^<^>vv<v^^v><>>v^>>v<<>v^^<<^>>><<><><<<>^v>><<vvv<v^v<^<>>>>><^<<^<^><v>v>v^>v^v><v>v<^<^>v>><>v^<<v<<><^>v<^>v<^<v^v<>v^v^v>vvv<<><<>><^>vv^<^^^^<>>^v>>v^v>>v<v<<<v<><v^<>>v>>^><^>^^><^^v<<<><^>^v^>><><^>v><<v^>>><>^^v<>^<^v>v><<><>^<><<<^v>^^><>>^>><^v^v>>^><>v>v<^>><<><v>v^>^>v^vv>vv<^v^^v><<vv>v>vvv>^><v>v<>v>^^^v^>>^>>v^^>^>vv>v><^<>><^v>v>>>^<v<^vvv<vv>^>>^<^>^<<>>v<<^^<v><<^<^<<vv>^<>>^<>v<>>^v>vv<>^v<v>^>>v>^v^>^^<><><vv<>^v<^><<vvv^<><><<>^<<^>>>vvvv^v^v^<^<v<vvv<v>^vv<<>v><>^><>^v>v>>v>^v>v>^^^v<v<^vv<><><vvvv>^><^v><>>>v^<^v^>^>>>>><><<<>vv><v^>^v^>v<>>>v^>vvv<>v^v<v<^><v<<v^vv^^><v^<v^<^v\n" +
                ">>>v<v^<<><^>>v^<>v<<^>>>^>><<<<>><v>><v<>>>v<>><vv^<>vvv>v<v>v^^<<^>^<<^>^v^^^<<>v>^<><^^<vv<>v<^>^<^^>^^^vv>>^<v<v>v^vv>^><v<^^v^v><<<^^v<v<v^><v^v>>v>^>v>vv>>>^^vv>^vv<^>^><v^v<>vv^<v^<<<><>vv>>vv>v>v^>>^vv^>v^<^>^><<>v>>><<>><v>^>>>^<<vvv>>^v<<<><>v^v^<vvv>vv<>^<<>^v><vv><v^>v>v^>^<<v<^<^<<v<<^^^<^v<vvv>^>v<<<v>vvv>^><<^<v<v>^>vv^<>v>^^^^<><^>^<<<<v<<^vv<<^v<>v<<<v^<^vv^><<vv<>vv^v>v><<>vv^><v>^vv<^v<^><^^v^^^^vvv<>><^<<<>>v>^^>^<^^^><<>><<^^^^vv^<<><<<v>^v^^v>vv><>v<>^<><^<<><><>^<v>v<>^vv^^<><<^<<<v<<<<>v><><>v<>>^v^^<^<><vv<>>><v<^v<^v<v^v<^^^^^<vv<<v^<<^<<vv<<vvv><^v^^<>^v<^<><><<v>v^^^v^>^<<v>>v>v^vvv^>>^>^<>>vvv><v<v><^^><v^<<v^^<<>v><<<v^<><^><^v^<<^<v<<<<v^<^^v^<>v>v><<v>^v<>>v<v^v^v^>^<v^^^>><^^^^v^<^^<<^<>^<v^><vvv><v<><><^^^v>v>><^<v<<>^^vv<vvv>>>>v<vv^<v>vv^^><<>^vv<^^<^<v>>vvvvv^v^>>vv<v>v<v<^v<>v^^<v^vv<v^>^^<<v^<<><^<<>v>v>^^<v>^^>v<>v<vvv<>v>v<v^>^<vv^<>>v^v<<^^v^>^<vv^v<<^vvv^^<>^v<><v<^<vv<>vvvv<v<^v>^>>><<v^v^<<^vv^v>^>vv^^>>>^>^<^>^v^^<><>^<<v<<^v<^<^v^<^^vv^^vv\n" +
                ">>v<^><<v^>>^<<^^v>^<<^<<v<vvvv<<^^<^v>^^<<<vv><v>^<^^^^v>v>^<^><^^<>^vv<v<>>v><^<vv>v<>^^^><>vvv<>vv>>vvv^>^><<<<<>^>v^>v^^<^<^><><<^<<><<^<>v>^>>v<^<^^<<^^<>>>>^v><><v^^^v^v>>>vv>v<^^v<^^>>>>^^^^<>^v>^^<^^>^^vvvv^v>v<<^v>^v<>^>^^v>^><>v>vv>^v>^^v^><<<^^>^v^v^v^v>^>^>v<^<^>>v^<<><v><^^^^>^>>>v<<v^^^<><^v<>v^>>>><^v<>>v><vv^>>>vv^<>>v>vv>^>^v>>^><>v<v<>v<^>>v<>^>^<<^v^>v<>><^^<^vv><^v<<<v^v^<^^^^v<^><<vv<<>^vvv<^v^v^^>^>^v^^v>^<><<vvv>v>^^^^><v<<vv>v^<v<^^vv<<v<^<<<<<^v<vv<v>^<<>>vv^v^><^^^^<v<v>^^^v^v^^vv<v^v^<^>>^^<<<^v>vvvvv<><<>^<v^<^v>vv^v^<v<v^>^^<v>>>>>v^<<>^><v<^^><<v^><>^^>>v>v^<>v^^>>>>><>v^v>^^v<<^<<<<><v^<<vv^>>v<<^<<v<v^>>^^<><v>v>>^vv<<<v<v><v^vvvv>vv^<v<<^vvv^^^^v>^<^>^<<><v<<^>>^><>^v<v>^<v><><<v<<<^<v^>^^v^v><v<<><v<vv^^<v<<^^>^<<^<vv<>v>^>^<<<^^^^>^<^^vv<<v<<vv>>>>v><<>>><vv><<>>>v><<v>^>><>v<v>v^^^<>^><^v><>v<><>v>>^^<<<>><^^<><^><>>v>vv^<v^>>>v<<<>v<^^<^^<v^><><>v^<v>^^>>v^>^>vv>^^^<vv>^v<<<v<^^^>v<^<<^^v^vv>^>^v<^v>^<vv>v^v^^^v<^v>^>v<<vv^<<^^^vv<<^>vv^<v^vv><<<v>>\n" +
                "<^>v^>>><<^v<^>>v^v>vv<^v^>>>^>^>v<>v>v<^^><<>^>^v<><^^v^>^>><^>^>v^<>^><<>>>^>>v><><<^^v<<^^>^>^v>vvv>>>vv>v<^^v^^v<vv<^>^<vv><v<vv^^<<><^<>v^vv>>^^vvv^<vv^<<><<<v^^<>v^^v<^<v^>>>^>^^v^^^^^<<vv><^<<v^v<v>>v>v<><^<<v<>>^>v><v>v<>vv<>vv<<^<v><>^^<>v><v<^>^^>^^vv<v^^<<<>>^^v<>^^>^vv<>v<>^v^v^v<>v<>^v^^^v<^<><v^v^>>^v<<<^>v>^^^v<>v<v^><^v>v<<>v^vv<>>><^<v^>v^<v^^v>^v^<<^<v><v^<^^>^>v<^>><>^^^vv<>^v<v^<^^<<>vv^><v>v<v<vv^v>>v<><<>^>^^>^<>v>>^^>v>>><<<^v^v<^^^v>>v>v<<>>^>><v^v<>^v<>v<><<<^>>v>vv^<v>^>><^v^><><>v>>^v>^>^<<v^v>v<vv^vvv^<^>v<^>^<v<vvv<^^<<<><v^><<>^>v^vv^^^v<vv<v^<>v^<^^<<><<v<<v^>^><>^<>>>^v<vvv<v<<<>vv<v>^>>v>>v>>^<v^vv<^^^<>>v<^^^vv<^>v<<><<<v^<^^^^<^>v<<>>>^<^vv>>>><>^<<<v<>v^<>^><v<<>>^>^^^<^vv<<<v<v^^>v>>v>^^>^<<<^<>^>><v^v<^<^>>^vv^<^^vv<v<vvv<^^^>^vv>>>^^v<v<^<<<^vv<><^><>><<<^<<>^<<>v<<^v><>^^^<<>v><^>^><><^^^<^vv<^>^<<^>>>>^^^<>v<v<v>><><>>^^vvv><^v<>^<v^^<^v<v<vvv^<<vv<<v<^v^v<v>^^vv>>>v<<<>vv<vv<v^>>^vv^v^><^<^<^<v><v><^<v<<v^^>><><<v><^><vv^^<<v><vv<><<^vvvv<v^vv>\n" +
                "><<<<>^>>>^><><<vv^<<<vv<v^<v<<><<^<v<^>>^><>^><<>><>^<v<><^<^^^^vv^>v^><>v^^<vv>>v<^<v^^^^^^>v>^>v<v>><><^vv>v>>>>v^v^^v<^^<v>v>>v^v><<>v>v^^><^<v<<><><<^>>^vv><<^v^vv^vv<<^vv>vv<^^v<^^vv<v^<<>v<>v^<^^v^<vvvv^v><vvv<vv^><vv<v>v>v<>^>>><v>v>>vv^^>vv<>>^<>^>^><v<>>>>><>>v><<>>^<<>vv^<>^^>><<^^^<><^<v<v>^<<<>v<v^v>v>^>vv>>^^vv^>><>><>v^v>><^^<>>^^>^^>^^^^vvv^>v>^>v><>>vv^><^^v<>v<v<vv><>>^^>>vv^^^v^^v^^<>v<^<v<v^^vv<vv^><^>v>>^<<^^><^<^vv<^><^^>^<^><v<^^^>>><^^<^^v<v>^>^vvv<<vv>^<^<>^v<<>^^v<^^^<><>>v^v^><>>vv^^^^^^<<^^^v^^^v><>v<vv>vv<^^^^<^v<v>^^^><v^<^v^v>^>><vv><v>vv>>^<>^^><<>>>^v<>>v^><^<^><^^><<v<v^>v<<<^^<v>>>vv<v^v^^^^^^^>>^^><^v^<vv<<<>^^v>v>v<vv^^<v^^^>^^^v<v>v^^>^v><>^>v>v^^v<><<>>>v>^^<v^>>vv^<^^^^<^^<>^^<^v>^><>v<>^^^<vv><<vvv^^<<^v>>vv<>vv<v^^>>^<v>><^<v>>^v^^<<v<>>^^>><v^^>vvv^v<^v<^^^v^^>v^><v^vv<>vvv<<^v>v<^>v><vvvv<^v>v<>>>^<><>^^^>^<<vv>^v>v<<<^<^>>v<<vv^>>^^<<>^^^>>>v>>^<^^^<<>v>vv<^v><<v>>v^^<<^<vv<<><^<<^^>^v><><>v^v>>^^>vv<v<vv<v<v>v<>vv>v^><^v<^>^^^><><<>^^v<v<v^\n" +
                "><^<<v^><<<<<<^>>^^<^^<^^^>^vvv>^<>^vv<><^vvv><vv^v<<v>>v^v>v>>v^vv^^^^^vv<><v>v<>v^vv>><^v^>^<>v<><v<<v^v<<^v<<<^<^vv>>>><^><^>>^>^<vvvv><<>>v<^<v>>^<>vv<>vvv^>>v^^>vvv<vvv^>>><v^<>^<><>v^v<^<v><>^^<^^vv<<>>^><>v<<>^<<>^^>>^>><^<v^>^<<>v^><vv^v<^^>^<>^>>^v^<<>>^<<<<^>><<^^^<^^<v^>v^^vv><<^^<>v<<v<^<^><^>><>v^>^v<^v>>>^>vvv<v^>vv^<><<>>v<^v>v<><v>>><<<^^<^<v^<v<<><vv<><v<^<vv>v^>^v^^<^^<>^<v<<<v^vv^^>v>^>^v<>^>v^<^^<>^^>vv^v^v<^v<v<<^>^>v<<>v^>v>><^v<>^<>><<^^v><>^^>>v^v><<<vv<^^<>v>^>>^^vv>^^>>v<<v<vvvv^^><v>^>>v>v^^^v><<v^vv><>><^<>^<>>v^v<^^v<vv<<^<<>v<>v>vv<>^>vv^>^v<<v<vv^<><>>^vv<^<v>>v<vv^^^^vv^^<^>>>>><^^<<^<<v>v>vv>><^v^v<^<vv>><>^^>v><^><^>>v^^>^^>>^^<v<<v>>v<<<><>>v^>vv>><<^<^v<^v<^<v<<>vv>v<<><v<^^^>^^<<v<v^>>><^v^>^>v<<>>>>^>>><v^^>v^>><>><v>>vv<>^>vv>v>>>^<v<^vv^><vv<^^vv^><>^<v><^>v<>><<v<<v^<v>v><^>v>>><<v^^<<^>>>^vv^>><^>>vv^v>^>^v>^<^v^>^<>v^>><<v<>^v^^><vvv>^><>>>^v<^<>v<>^<<><^>^>^v<^<v>v>>^<^^^v<<<^^<^v>^>^>^>^>^v^<^>^>^<v^><>^<v<^><>>v>^>v<<><<<v<^v>^<<^v<<<<^<<><\n" +
                "^<>^v^<^<><>^>><v<^<>^><<<>^v>v^^v><<>>^^^^v^^^>^^v>v^v^^>><<^<^v>^<>>^><v^>vv^vv^^v<vv<^>v>v<v>^>v>>v<>><v^>>v><^^<>^^<^^v<^>vvv^>^<>^^v>>^v>>>>v>vv<^^^^<>vv^><>^^vv<^<^>^<>^v>^vv><^^<^v><<>v^^v<vvvv<>v>>v<^^>^<<>^<>>><<>v>^><<^>^^v^<v<vv<><v><>>^<^v><v<>>><^>vvvvv^^v^>^^^>v<<<><<^^^<v>^<vv>>>^<<^<<<^<vv><^>^^><<<v<>v^^>vvv^v^^vv^>>><v<^vvvv>^<<<<>><v>v<v^<<^<^<<^^<^vv>^>>>^v^^>v^^vv>^^<^v^<v>^<vv>v^>^>><<v>^vvv>v<v<<>^<v>>><>^^<v^>^>^v>v<^>>^^>^>v^vv^>^<>^<^v^<^<v<v<>>><<><><>v^<>v^v^v><^^v^v^<vvv>^<>>v^<><<v^v^^v>v^^<><v^>v>^><^v<<<v<^v>v<^<>v><<><>v^<<v^<^v^v<<^><^<>v<v><^<^<vv^>^^>^vv^<^^<><v><^>^v>^><^v<>v<<<<<<^<<^v<^^><^<^<<>><<>^<v<<<<^>vvv>v^v<<>v<^<>><<><<^^<<v<><<vvv^<^^v>vv<v<vv<vv^<<><vv<<<><vvv>>v^^<vvv<v>vv^><><^>^>^<<v^vv>^>>vv^<v>><^<>>>^>^v^v^^^<^v<^^v^<^><v<v^^>v>>^^>vv>><^<>v><vv^^^^^^^><v^vv>v^v><^v<><v<<v>v<^^>vv<v<v>v^^^^^<>vv^v^<^>>v^^^vvv^>>v^<v<<<>v<>v<<^>>>v>v^v><>>^v^^^v<>>^><>><>vv<>^>>>v<>><>^<<>v<v<v^vv>v^^<><>v><<^<^vv<^v^>^><v^^^<>v<>^^>><<>><v>^v>vv^v\n" +
                "><v^v<v<^>>vvv<><>><^^vv>vv>><>><><>><>^vvv>>>><v>^^>^^^v>>><^<<v^v>><^^><^v<><v<^<<<>><<v<^v^v<^<v^<vv<^^^^^v<<vv><^>^>v>>>^<v^<<^<<^^^>>^v<>><^vv>v^^>v>^^<v^^><>v>>^v^><>>^>v<<>>^vv><v<^^v<<v<v><v^<><^>>v^v>^<<>>^v^^vv>>^<<<v>^>>vv><vv>>^>^^v^vvvvv>>>vv><>^<<v^^v^<>v^v>>^<v<<v>v<^>vv^>^>v>><^v^v>>^<v><>v>^><^<^<v^v>^^v^<><^<<<^v^^<><v<v<vv^v>v^^<^<v<v<^<><^v<<<>v^>v^<<>vv>>v^><^<^^vv^<^<<v^v<<^>^<^<^>v^^<v<><>><^^v>^<<<^v<>vvv^>vv>>>>v^vvvv^^<^^^v^<<v<v<><<v<vv^<v<v<>v<v<v^^v^vv><<^<<>>v<>^<>vv>^^vvv^>>>v>>>v^v>vvv<>v<<^^v><<<><<>>v>vv<<v<^^vvv<<<v^v<vv<vv>>vvv>>^v<>>^vvv><v^<>^<v><<^<v<>v<<v<^>>v<>v^^^<>vv^>><^><^>v<^^<>^>v>>>><vv^<>^^^v<^^<^<>v<v>v<v^<v<^v<^v<><><v<><<><>^>>>>^>v<^^vv^><v<v^>^<^^>>>^<v^^vv<<<^^>^^^>>^<^<>^v<<><vv^<^^^^v^<v<><^<v<vv<>^v><<^<<^v><>><^<>v><^<>^<^v^<>^^>^v^^^^>^<<vvv<^<<v>^^<^>^<<>v<<v<v>>>>^>v>><v<v<>^<<^v><<>v^^v^>v^^<^v^^><v<<v<vv^<v^<<<^v>>^<vv>>v^v^v^^<^v>><><><>v>>vvv>^<<><^>vv<^v>^<>><><<^>^>><<<<^^^vv><v<>v>>^<vvv>>^v><>^^^v>v><<>v<v>v>^v^^v>^v\n" +
                ">v<^^>>^^<v<><><<>^<>>>v^^^<><^v<v^v>><>>^<^<><v^^vv^<<<>>><>>vv^>^vv^<^vv><v^>><^^v^vvvv^v<^v^^<^>>v^<vvv><>^v<v^<<<>v<<<>^v<v>v^>^^v>><<>^v^<vv>><<<vv^v>^^v<<^<<^<>^vvvv<>v^>^>vv<v>^vvv<<^><<^><<v^^^v^<>><^>>><^^><v>^v^v<^<>v^^<^vv<^^>vvv<v<^^v^<>v<>v<<^>>>><v^vvv>^>v<<<v<<>v>>v<^<<>^v<v^<vv<^v><v>>^>>><v>><<<>>^<<<>vv<<<>^>><>^<^>><<>^<vv>^><>>v<^<^<>^><<>^v^<^vv<<>v^>>>^>>^>^v>^<^>v>>^^v>v>>>^v^^^><^<^v^>vvv>v<v^<vvv><<^>^>><<>^^^>v>^<>v<><v<v<^<^^v>><vv>v^^v^>>^v<^^vv><><v^<v<<^v>vv><<><<<v^<v<^><v<^vv>v^>vv^<v^<^<vv^^<v^><^>^^><<v><<^^<>v^<<v^>v^<^><v^v>>><>v><v>>>v><<vv^v>v^>^<>^<vv<>vv>v>v><v^>>^>v^<vv^<>^><<>>>>>><vvv><><v<^>><<<^<^^<<v^><>^vv^>>vv>vv<>>><<^^<><<^<^>vv<><^>vv^^v>v^^v^^v<^^<v<vv^<v>v^^>><<^<v><<<>><<>v<>>^>>>^^^>v<<>v><>><^>>v>vvv>v<>><>>v>^<><>>v^>><>>vv<^>^>>>^>^><vv^<^>v<^vvv><><v<<<>>^<<^>^^^^vv><^><^>><>^<^<>><>^^v^^<v>>>>^vv^vv>^<^vv<<v><>>^<<>^^^v<><<^^>>v>vvv><^>>^^<<>>v<>v^<^>>><v<v^^<<><><>^v><<<>v^vv<>^<v^><v>^><^^>^^<>^>^<^^v><^v<v<<>^<<^>^^v<<<v<<v\n" +
                "v<<<>v^<<v><v^v^^<vv<v>^v<<^<^v><^v<>^^v><v^^>^<vv<^v<v>>>>^v<<vv><>><^^vv^><<<^<<^v>v><><>vv>^<^vvv<^v>^<v<v<<<>>><>>^vvv<v^^<>v^<>v^><<<>><<<<^^><<v^vv^v^v<vv^^v<><<v<<^^<v^^><><^v^>>vv>vv>v^^^vv<>>v<vv>v<>><<^^^<vv^>v^><v<<v<<vv^<<>>>vv<><^^>^>vv^^><vvv^<><^<>>^><v<<<^>v^<><^^>vv<<vv<vv<^^^vvv^>><><<v>^vv>v<<^>vv^^>^v>v>v>v>v<>>vv<<>>^><<v>><<>^<vv^<>vvv<>^v>^^v<^v<v><<v^<^>v>^^v<<>^v<>>vv<^^^<^<><<v^<^>vv^^v^>>vv>^^>vv>^>vv^>^^v^>v<<^>vv>v>^<^<v><<v>>>v><^<<^^<>^v^><^<<v<v<vv^v^^^>v^v<<<v<v>^^<><^>^>>>v<v<^<><<v>^v^><<^<v>^<v>^v^<<^><v>v^<>^vv><vv^^^<^><<<vvv<>v>v<<><v>^vv<v<^<>>>^^v^^v<<^v>v>>^>^<v^>vv^v>v>^<>>v><v>>>><^v<v^>v<<vv^<v>>><>^<<>><<<>><>>>^^>^<vv>>v<>v^v^^<<><^^><v^vv>v<vvv^<v^>^v>v>v>^>^v><^<v<><>>v<^>^>>>v^>v><v^<>v>^><v<^^>^<^>>>>^^^^<>^<>>^v^<>v<v^v<<^v^^^v<v<><^^><<^^vv<>^vv<vv<>><><^<^^><^><v<<^>>><>^^>v<<<>^vv<>^>v>>^^><v>^<^>>v<<<vv><v^<^^<<><^<>^v^<vv^<<^^<^v^>^<^^^<^><>><>>^<>^<^vv<^vv^^>v><^>v><^>><^<^v><v<<^^^^v<vv<^>^v><<^<>>^<vv<vvv^v^vv^<vv>>>v<<<>v<^v>\n" +
                "^^<<v<^>^v^>>v>v>><v<>^><>vv>v<<^^^<>^><vv^v>>v><<^>^<<^^>vvv<v>^>v>^^vv<^v<v<v^<<<<vvv>v^^<<^><v><^v><vv^vv<^^^>>>v^^vv^><<>>v^<vvv<<<^^>^<>>>^<>^<><>><<><^v^vv^^><^^<>>^<vvv<^^>>vv^>^^<vvv^<^><^^^vv>>>>v^<^<^<>^<^>>^v^^>v^^^v<>><^>^<>^>v^v>vvv^>^<^^>v>^>>v^<^<<>v>vv<v^^>v<^^vvv<>vv^^v^<<>^v>>^<^<v<>^^><>^^v><>v^^^<^>vv<<>vvvv^^><><v>^<>^>>^<v^v<>^>^v>>^^v<><>v^<<><<<v<vv^<>v>>^v><>>v^v<^v<^v<v<>vv^v<^vvvv<^><^v^<>^>^^>v><^^^vv><<^^>v^<^>><>v>v>v^^vv<^<^^<^<v<v<<vvvv^<><<<<<^<>^><>^^><^^v^v>v^><^v^>^<^><^vv><>^vv<><^^^^v^>^><><><><><^v^v<<<>^<<^v>><<<>>>^^<<vv^v><v^<<^>v^v>^<^^<<>vvv<>><<>v><<^><vv<^><v>^>>>^^<>v>v>^>><^>v>v<^<>v<v<><vvv^v>><^^^<>v<>^vv><v><<v^vv<^<v<v^<<vv<^v^v<<^v<v>v<>^^v>v>^^<<<^<><v><vvvvv<>vv>><v<v^<>^>^^>><>^^^<^>^<>^>^<<>v>v>^^vvv^<>v<^><^^^>v<<^>>^^<<^^<v><vv<v>vvvv>v<<<<>v>^^^^<<<v^>v^^>><^><^^>^v^>v<<^<v^><><>>^^^^>^^^^v^^<<^^><<><v<<v<v^v^><><^<^^<>^<v<^<^^^v<>>^><v^vv>><^v<<<>^>^vv^>v>><^v>^<>^^^^<v<>v>>^<vv<^>v<v>v<>>><>^<<<<<<^v<>^>><^^>><v^>>><<v<^^><<\n" +
                "^^v>>v^<^<vv^^^><^>v^>><<<<>^^^vv<>v<^v^v<<<v>>>vv^<^vv<^v^^<<v>^^<^v^><>v<>^^vv><^<^v>>vvv<^>v>^><<v>^<>^^>v>>>>^>v^v<v<>>v^v<v^^<<><><>v<>v<^^<<^v>v^<v><vvvv>>v<^v^v<<v^<<><<><^><^vv^v<>>v^>v^^v><^v^>^<<v>^>>vv<vv^<><^^^v^>^^^v^^^<<v<>v>^>^<>^v^><^v^>^^<<>^v<v<^v^v^>>><vvv<^v^>^^^v^<<^^v>^v^^^>>v^<v^vv><>v<v^>^v<<<<v^<^<v>vv>><^^v^v<<^^v<vv><>>v<^<>vv>>^<<v<v^><v^^v>>>^>^>v^>vvv>^^<^>>^<^v<<><v^<v^>^>^^vvvv>^v^<vv>>v>v<^v^<^<^vvv^<>v>^vvvv>>^>^<>v<<<^^^<^>v>^^<^>>v<v<<v<^>^><v<v^<^^v<v<^^<>vv>v<<<vv^vv<^><<><<^^v<^<>>v<<^^<<<><v>v>><^<>>^>>v^><vv<v<>v^v^>^<^vv<^<^>v^^>^^vv<>^vvv>><<^v>>>>vvvvv^^>>^^><vv>>>v^v<><^<>v<vv>^>vvv<<<<>>>^<<>vvvv<v<^<vv^>vv<>vv<v>>v^>^^>^v<>>>><>^v<v<>^<v<^^<>><><<vv<<<<^><<>><v^vv<<v<v><<<v^<<^^>^^^<<<>v^v><v<<<>vv<v>^<v<v<<v>><^<^vv>>^^<vv^^<>v<^^<>>vv<>^^^v>v^<<<><vv>v>vv<<^<<^<><v<^v^><>vv^<<^>^^^<^^<v^^>v><>^<<v^<^<<<<<<v><^>>v<<v>>^^^>>^<<v<vvvv^<v<v>v>v>vv<<v<<v^v>vv>v><><^^^v<^^>^vv<<v^<^<vvv<^<>v>^v>>vvv><>>>v<<>>>>^^<vv^>>>^v^>>v^^>><<^^v^>^<><^<v\n" +
                "v<>^v^<^>^>v<v>v>>>v^<v>>>v><vvv^><^>^vv^>>v<^<v><v^>>>>v^v^>^^^<>^>^^vvv>v<<^vv>^<>vv<<><><^>>^^^<^^^v<^>^v^^^^>v<><<<>>>vv^^<v>^<^^^>vv<<vv<><>v^<><v^>^<v<>^<<<^<v^^v<<^<vv<><v>v^<<><^^vv<<<<<^<>^>>v<<<vv^<^^v>v^<<v<^v>><^^^>^<^^<<v>><^^vv^^v>^>>>vvvv>vv^^^<>^^v>>vvvvv>><><v<>^v^>vvv>^><^><^^v>v>v<<v>v^<<^v<v<<>>v>><v>^v^<<^vvv^<><>v^><v^<v>^>^vvv^v^^^vv^>^<<<v<v^^^>><^^^<^><<><^>^^v<v<vvv^^v^v>^><v>>><>^^v<<vv><v>>>>>>v>>>^<><v<>v>^vv<vv><^^v>vv>>^><<^^v>^>vv<>v^^<^>>v><<>>^<><><>v>^<v<>>v>>v<<^v>>>><v<>>>v><<><v><^<<><><v^^v^>^v>^>^^v^>v<v>^^>^><^vv^<v<><<v>^<<^^>>^><^^<<v^<^^^<>>>v^^v^^v<vvv<<<^^v^>^>>^<<^^vv<<^<v<<^^v>v<v<<^<<vv>>>vv><^vvv<>^>><>>><>v<vv>>v>><>vvv<^><vvv<v<>^v^>^^vv>^<<<^^vv>v<v<v^<^v^<^v>v^>vv><v^<^^><^^<>^v^<^<><<^>>v>vv<<^^^v^^v>v^v<>^v>>>>>^^^><v^^^^^^vv^vv<<>^<<<<<>^^<v<^v<>^vvv^>v^<<>^<>^v^>^<v^^v<^v<<^<>v><vv<<>>v>>v<vv>><v^v^<><<vv<>>>>>v^>^<<>>><^v^>>>v><>v^>^>^^^>vv><v<v^^v^<><><^v>>^^<vv<<>><>>vv<<>v<>v^^>^<v^<<v<<^^^v>>^^^<><^v<<^><^>><>^v^<<>^<<><vv^\n" +
                "vv<<^v<>vv^>v^<<<v<v<><<^vv^>v>^<<<>>>vv^><<<v^^^vv><^^><^^<^<v>>>>v<^v<>^<>v^v^vv<^vv^^^<<v<<^v^^^>vv>>v<^>v>^v^>v^^v^^v><><^<<^^^>vv>^<v>^v<vvv^v>v^^<vv<^v>>v^v<>v>vvv^v<>><^<^^<>vv<>>^^<^^^^<>>^>vv>^vv>vv<v^v^v>v>>^>^v^>v<^^<<^>><<<v<v^<^^v><<vv<>^>^<v^^<v<^<>v^v<^>>vv>^>^vv^^vv^<^v<v><v^^<^<>^<>v>>>^v><v><v<v<^<^>^<vv^><^^>>>>>vv^>v^<>><v<>><><^^>>^>^^v^<>><>vvvvv<^><>^vv>>^>>v^v^^<^<v>>^vvv><<<^<<>v>>v<<<<<^>v^^^v<>^v<v>>><v<<^vv<^>>>>>^>>^^^<^<<<v<<^>>vvv>v<v>^^^^><^^^v>vv<v^v^^<v>^^<>^>v<>v<><^v>^^<^<<^^>>>vvv^>v^<v^^<<v^v>^v>><v^<>^v<^^>^<>^<>^v<<>^^v>^v^^v^v>^>>vvv^>><v<^>^^><><^^>>^<^<^vv>>vv><^<<vvvv^^^vv^>><v^^v<^>^v<>v><vvvv^<^^<<>v>><v>><<<v<v>>^>v<>>v^<<<^v<v>v<^v>>>v>v>>>v^v>^^v>^^v^<<<<<>v^<v>>^>vv><^>v<^>>>>>^>v>><vvv<v>>>>v<v^^<^^^><v<v^^v<v^v<^<>^>^^>>v>^^v>>>^<v<<>^<>><v>^<^v<><>^v<^<><<<^v^v^^<<^<>>>><>>^<^v<><vv>>>^>vv>^^^v^>>v>><<<>^vvv^v^v>vv<<<^<<^>><>^v>>^v>^>vv^^v><^^vv^^<>>^v>^^<<^v<v^<><v<><^><<v>><<v<v<^<<<<v^<<v>>v>^<^^<^><^^vv^<<<><>v>>^v^<>^>vvv>>vv<<v\n" +
                "><v<vv>>><<<^v^>>v>vv><><^><v^^>v^<<<vvv^<>^v^^^^<^<>vvv^<vv>^^>^^v^v^>^>^v<v<>><<>^v^^v><v<><vv<v^>v>>>v^^>^<>v>v>^vv<<>vvvv^v>^<>^<^^vvvvv>>>v>^^^<>>v<^>vvv<<v><v>^v^vvv<>v<>^v^v>v^^<v^v^v^<^v>vvv^vv^^^<>>^^<^>vvvvv<<^^v>^>v<^^vv^>>>^v^^<v^<>^>>>>>^<vv<<<><v><v>>^v>^vv^<<<<>v>vv<<^<^>>>vv><v^<v^v<<<^>v>^<<><v^vvv><<^vv>^^>>>v>><^^><v<v>v>v>^>v^>>v^v>^<><<^<<<>>^>v<^<v<<^><<v<v>><vv^<>>vv><^>^v<v^<<>>v<vv>^><<^<>v><^<>^<<^^<<<^v^>^^><vv^^^>^><^^^^<vv>v^^v><<v>>vv^>><v^v^^>v^<v^^>^^>^^^^<<^>^vvv<^>v^^>>>>><<>v>v^v><^>><v<vvv^<^>^^^>v^<<^<^^<>><^^v<><<<>^><<>v^<<^^>^v>v<<<<<>><^^^^>v>><^v>v>>^>^v^v>^vv>^vv<<<^><v<^v>^v<^<><^><^>^><<vv>>>^^^^vv><<v><^^v<v^v^>^<>v>^^^<v<v>><^^^>v^^<>><^<<>>^>>v>^v>>vv^^v>v<v><v^^>><vv>^^<<^vvv^v<^vvv><v><v<><<><<<^<v^^<v<^>vvv>v<<<<^>>^^<vv<>>^v^vv<^v><<>><vv^^><vv>vv><v^v^vvv>^v^v>><vv^<>v^vv>v><v>^^v<^^^<^<vv^vv<vv>v<>v<>v^><vv^^vv>^v>v<^^>>>v><>><^^>^^><^^<>>><^v^>v<vv><<v<<^v<^v><^^^>^v<>v>^v^><vvvv^v<>>><<<v>vv^^<^v>>^<<>v>>>>^>v<^<^^^vvvv>^<<<<^>^<>\n" +
                "v<^vvv>vv<^v<v^^v<vvv>^^>^<v<^vv><>v<>vv^>^^><^^<<<^<>v><v<>><^<>^v^<><><<<><<v<<>vv><<<vv^vv^>><v^<^<^>><v><<<v<<v^<^<vv<<v^^v^vv>^v<^^v<<^>>^<>^<<^>v>>v^>><<^>vv^v<vvv>v^<^^^^<<^^^>><>v^^v><vv^^v<^>^v^v>v^^^v>v^>>^>v^^^>^>vv^vv^<><^vv^^<^v>v>v>^^^v<v^>vv><>v>v<<v^^>^<v^<>>v>>><><^<<v^v>^^<><v^v<<v<^<<^<<vv<^^v<v<vv>>>^v^^^v<^^>^>>>^v^<v^>>>>^>>v<>^^<<<vvvv^><<^^^<><<<><><>>^v<>^>>^^><<><<v<<<^v^v^^^<<<<>v>v>><<><v<v>>^vv^>v<<<<^><v>^<<v>v<<v<^>^<^^><<>^v^>>vvv<<>><<vv>^v^^^^<^^><>v^<^>>>^v^<^<v<vvvv^>v<v^v<v>^v^v>v<<^^v^<<<v^><v>>v><^<vv>>v<>^vvv<<<<v^^>vv>^>>^<<^<<>v>^^vv<^<vvv^^<^v<v^<>>v<>><<>>><<v^<<^^v>><^<<^v><^<^^^<^>>>>v>>^<<<vv><v<vv^<><<>^vvvv>v>v>>v><^v^>v>v>^v>vv^<<vvvvv^<>^^<v><^vv<v>^>v><<v>>^>^vv<<<^<^>vv^<<><><>v^><vv><<>v^v>v>>v^^^<<v<^>v>v^^<<v<>>^>>^<v<v^v<>>>>>^<>^v<<><v<<^>^^^^<^v^^^<>>^^><v>>>v<><<<^<^<v<^>^vvv<^^><^v>^^vv<<^<<<^v^^>v<<vv^<v>v>><^v>vvv<v>>^>v<>>><<><<><<>v><v^^><><<<^<^<>>v^>>^v<>vvvv^<vv>>v<vv^v^v^^><<v^<<>^>v^v<<>v^v<v<<^<>><<>>><<^vv<<v>v^<^v\n" +
                "^<^^^<><^^v<v><<^<<^<<>>v^<<vv<<^^><>^^^<>><>>>^<vvvvv^<>v<v^^>><vvv^<>v^>^<><v^^>>^><>>^v>^vvv^^vv<v>>v^<v<vvv>v<>^>>vv^^>v<vv<<<>v^^^<<>>>>v<<^v^^^>^<^^><>><<vv<<><>>v^vv>^><<><^^>><vv^>>>^<^v^<<<>^^v<>vv>>><^^^<v>v<>><<vv<>v^>v>>v<>^^><>^>v<^>^vvv^>^>^vv<^>^>^v^>v<<v<<^^^>>>>v>>v<<<<^<^<v><><vv^>>><>vv<<v^v><<^^v<^<>^v^v^>vv^>v^vvv><<v^<<^^v<<^^<<><vvv^<>vv>v<<vvv>^<v^v<>v>^^vvv><^v<<v^>><^vv^^^^v>>>>v<<>>>v^>^^<<>^^v^<^<^vv^>>><v^>>>^v^>v^^<^>vvv<>^^v^<v<<v^><>^^<><>^^>^^vv<><^>v^<><^vv^v>^>v<>v^^v<^>v><<v<<<<v^^^><vvvv^<v<>^v>^^v<<^<vv^v^<<<vv>v^v<^v^^<<^>>v^v>^v^>^<<^>v<<><^<^^vv>^v<vvv>v^^<^>^<^<^vv^^v>^<^^v<><^<^^vv<v^v<<vvvvv^v><v<<^^vvv^v^^>><>vv><<<><^>^v<>vv>vv^>^v><v<v<v<><v<<^<>^>>vv^v><^>>v><>^^v>^<<><vv>^<^v><>>>^<vv>^^vv^>><>>v<<><>vvv>^^v><^<>>>^>v><^>><^v>^<<v<^vv>^v^<^v<<v<v^>>v^v<^^v<^<v>>>>^v^>v^>^>>v<^^><<><><^>^>>^>^^^^^v^^<vv^>v^<>vvv^><v>^<><vv><>v<<><vv>v^<>v<^^^vv<v^<>>>>^vv>v<<>v<>^>>>v>^v>>^<<<><^vv^>>>>v<^v<^>v>><<v<<vv<<>^<><<>^v^<>vvv>vv<><v>>v^><<<<v^>\n" +
                ">vv>v<^vv^^v>v>^^^<>vvv<^<>vv^^vvv>>v^<><^v<^v>>>>>^<<v<^^^><vv>^<^>v^^>v<>>>v^v>^^v^>^v<v>v<><>^^<^^^^>^^<<><^<>^^>v><^v>v^>vvv>>^>v><^<<>^v<v<>^^vv><^^<v<^v>>>^v>>>^<>v^^><v^<^<<<<<v^<^vvvv<v<v<<v><v>^<vv<<<v>>v^^<>v>^<>>^>v^<^<^^v^^<<v<^>^vvv>^<^v^<^<<vv<v^>>v<<vvv>>><v<v<vv^v><><>>>v><v><v<<^^>^<><><><>^<^<^^<<<v<v>>^vv^>>>v^>^vv^>^>^<v>><^v<><><>>>>v<^v^>vvv>v^<^^^>v^<^<^v<v^v<^<v<v>>>>v^v>>>^>vv^><^^>vv<^^<v>>^>^^<vv<^<vvv^v>vv^<><>>vvv><vv^<^vvv><^^>>v^^^<>v^^<<>v>>^<^v<>^>>vv<^vv>^^>>v^v^<<^^>^<<^^^<>v^^^^^<^^v<>^^><<><^^<^^>><^>>>>vv<v<v^<><>>^<vv><<vv>vvv^^vv^<><v<>^<v^vv><^vv>v<>>^>^<v<>v<>^<>^><^<^>vvv^^^<>v<>^v^v<^^^vv<><^<v>v^<^^<v>^<<<<>>^><<^v>vv<<^v<v<>v^v>vv<^v>^<<^^>vvv<>v^><^^>^^<<<<^vv<v<<^^<^<<v^>>v<^v>vv<><^v^vv^<<>^<>>>^>>>^><<v><^<><><^<^<>>v<^>v<^><<v<v^><><v^v^<v^vv^v<v><<<>^^><vv^^v>^vv>^>v^<v>><<<<^>^^<<^v<<<^>vv><<<>^^<^^^<v<>>v^>^>v<>v<^><<<v>^>^^>v^^<vvv<>^>vv>v<>^>>^v<^<><<>>>v^v<><<vv^><^<>>><vvv<vv<v>vv^><^<^>>^<>><<<^<^vv^<<^>v^<<><>>^>>v><<^>><^v<>^";

        String input1 = "##########\n" +
                "#..O..O.O#\n" +
                "#......O.#\n" +
                "#.OO..O.O#\n" +
                "#..O@..O.#\n" +
                "#O#..O...#\n" +
                "#O..O..O.#\n" +
                "#.OO.O.OO#\n" +
                "#....O...#\n" +
                "##########";
        String input2 = "<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^\n" +
                "vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v\n" +
                "><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<\n" +
                "<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^\n" +
                "^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><\n" +
                "^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^\n" +
                ">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^\n" +
                "<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>\n" +
                "^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>\n" +
                "v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^";

        Grid[][] g = parseGrid(grid);

        Grid[][] g1 = wideGrid(g);

        Coord c = findRobot(g1);

        ArrayList<Direction> directions = parseInstructions(moves);

        followDirections(g1, directions);

        visual(g1);

        System.out.println(calculate1(g1));
























    }

}
