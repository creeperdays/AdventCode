import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day10 {

    //Part 2 code. Part one used an ArrayList instead of a HashSet, and included a contains check in the
    //search function
    public static int score(String input) {
        int[][] grid = parseInput(input);

        int sum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    Coord current = new Coord(i, j);
                    Set<Coord> found = new HashSet<Coord>();

                    search(grid, found, current);

                    sum = sum + found.size();
                }
            }
        }

        return sum;
    }

    public static void search(int[][] grid, Set<Coord> found, Coord current) {

        if (grid[current.getX()][current.getY()] == 9) {
            found.add(current);
        }

        ArrayList<Coord> poss = possible(grid, current);

        for (Coord c : poss) {
            search(grid, found, c);
        }


    }

    /**
     * The possible moves the hiker can make from his or her current position
     * @param grid the hiking grid
     * @param current the current position of the hiker
     * @return Returns the ArrayList of possible moves the hiker can make
     */
    public static ArrayList<Coord> possible(int[][] grid, Coord current) {
        int i = grid[current.getX()][current.getY()];

        ArrayList<Coord> possible1 = new ArrayList<Coord>();

        //Checking the up coordinate
        Coord c1 = new Coord(current.getX() - 1, current.getY());

        if (valid(grid, c1)) {
            if (grid[c1.getX()][c1.getY()] == i + 1) {
                possible1.add(c1);
            }
        }

        //Checking the right coordinate
        Coord c2 = new Coord(current.getX(), current.getY() + 1);

        if (valid(grid, c2)) {
            if (grid[c2.getX()][c2.getY()] == i + 1) {
                possible1.add(c2);
            }
        }

        //Checking the down coordinate
        Coord c3 = new Coord(current.getX() + 1, current.getY());

        if (valid(grid, c3)) {
            if (grid[c3.getX()][c3.getY()] == i+1) {
                possible1.add(c3);
            }
        }

        //Checking the left coordinate
        Coord c4 = new Coord(current.getX(), current.getY() - 1);

        if (valid(grid, c4)) {
            if (grid[c4.getX()][c4.getY()] == i + 1) {
                possible1.add(c4);
            }
        }


        return possible1;
    }

    //Returns whether something is a valid coordinate or not
    public static boolean valid(int[][] grid, Coord c) {

        return c.getX() >= 0 && c.getY() >= 0 && c.getX() < grid.length && c.getY() < grid[0].length;

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
            String s = "(" + x + "," + y + ")";
            return s;
        }
    }



    public static int[][] parseInput(String input) {
        String[] s = input.split("\n");

        int[][] grid = new int[s.length][s[0].length()];

        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            for (int j = 0; j < s[0].length(); j++) {
                int num = Integer.parseInt(String.valueOf(s1.charAt(j)));

                grid[i][j] = num;

            }
        }

        return grid;
    }

    public static void main(String[] args) {
        String input = "6543298761232134567654321234365430109789210765\n" +
                "7630165450545023698743430143278121218654307876\n" +
                "8781074305656712587412945056189054327610128965\n" +
                "9692181212789803654307876567098769016501201234\n" +
                "8543290325654908743216521098778978039432343210\n" +
                "1012107812343219210127432101065432128765464569\n" +
                "8703456901432101021858921098123498012346678978\n" +
                "9698767876545832210961012567898567109656541066\n" +
                "0543678989836943367878983456547010478765432127\n" +
                "1234569834787894450123672561032123567345345698\n" +
                "8705450125690765645674501870129854438936216787\n" +
                "9612325678501234734983465985438763520127303456\n" +
                "0549814789456789821567874376579654011298012367\n" +
                "1236703210321098980432989210189503567885410198\n" +
                "0145610123010123671301065123679012456956328765\n" +
                "2125898016134564547219874034578654320167019954\n" +
                "1034567667821078998765723410165743210298907823\n" +
                "2101215454910899789054010567234894109347876910\n" +
                "3432109323856789670123120198654301078456585410\n" +
                "4569218012541008765434103296785212562345694321\n" +
                "5678107873432210894120254589698943421034783210\n" +
                "6760256976501345703091345678899874014345115432\n" +
                "5801345488989856912782987010710762321236006781\n" +
                "4912352397430767824653456123601051430347112390\n" +
                "3983401256521956543241089234512340554398203454\n" +
                "2876510342107867852130176541329429663294345965\n" +
                "1089459656236238901021287430458218776187654874\n" +
                "2012368789845121652012396521567309889076567876\n" +
                "3001871656903030743189465432456456776985678945\n" +
                "2100980347812349876676576321078944567034569432\n" +
                "3231291210101656734589689234561323498123253211\n" +
                "4945387667892109825678710105430212234510104500\n" +
                "5876456108743276710265431016521001107601067601\n" +
                "6700123219654985610176721037815410098432188932\n" +
                "5217634508989414323789872348906321016501098543\n" +
                "4398549607678501234567961059817892107890107676\n" +
                "3210498714565430104498854367876765226981238985\n" +
                "4782107123645621223421343458987014312876543214\n" +
                "5693898034564789014530012121898123103987454103\n" +
                "8764796543673298987645430010987035654036567812\n" +
                "9855687234982187656786521027876546789145658901\n" +
                "8544590127651012565497890038985589690237849878\n" +
                "1234210098545671434387912147104676521056932569\n" +
                "0110367653234780301275403456203465432345691410\n" +
                "3223458910125691215676323569312394341210780323\n" +
                "4334389871210101234981019878456787430101671410";

        String input1 = "89010123\n" +
                "78121874\n" +
                "87430965\n" +
                "96549874\n" +
                "45678903\n" +
                "32019012\n" +
                "01329801\n" +
                "10456732";

        System.out.println(score(input));


    }
}
