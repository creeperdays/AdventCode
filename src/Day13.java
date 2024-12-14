import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.Arrays;

public class Day13 {

    public static long totalCost2(String input) {
        ArrayList<Machine> m = parseInput(input);

        for (Machine m1 : m) {
            long newX = 10000000000000L + m1.getPrizeX();
            long newY = 10000000000000L + m1.getPrizeY();

            m1.setPrizeX(newX);
            m1.setPrizeY(newY);

        }

        long tokens = 0;

        for (Machine m1 : m) {
            double[] solutions = solve2(m1.getAx(), m1.getBx(), m1.getPrizeX(), m1.getAy(), m1.getBy(), m1.getPrizeY());

            double A = solutions[0];
            double B = solutions[1];

            if (Math.round(A) == A && Math.round(B) == B) {
                tokens = (long) (tokens + 3*A + B);
            }
        }

        return tokens;


    }

    public static int totalCost(String input) {
        ArrayList<Machine> m = parseInput(input);

        int tokens = 0;

        for (Machine m1 : m) {

            double[] solutions = solve2(m1.getAx(), m1.getBx(), m1.getPrizeX(), m1.getAy(), m1.getBy(), m1.getPrizeY());

            double A = solutions[0];
            double B = solutions[1];

            if (Math.round(A) == A && Math.round(B) == B) {
                tokens = (int) (tokens + 3*A + B);
            }


        }

        return tokens;
    }

    //2 by 2 simultaneous linear equation solver
    public static double[] solve2(double a1, double b1, double c1, double a2, double b2, double c2) {
        double y = solve1(a1*b2-a2*b1, a1*c2-a2*c1 );
        double x = (c1 - b1*y)/a1;
        //System.out.println("x = " + x);
        //System.out.println("y = " + y);

        double[] d = new double[2];

        d[0] = x;
        d[1] = y;

        return d;
    }



    static double solve1(double a, double b) {
        return b/a;
    }



    public static ArrayList<Machine> parseInput(String input) {

        String[] s = input.split("\n");


        ArrayList<ArrayList<String>> strings = new ArrayList<ArrayList<String>>();

        ArrayList<String> m = new ArrayList<String>();

        for (String n : s) {
            if (!n.isEmpty()) {
                m.add(n);
            } else {
                ArrayList<String> s1 = new ArrayList<String>(m);
                strings.add(s1);
                m.clear();
            }
        }

        strings.add(m);


        ArrayList<Machine> machines = new ArrayList<Machine>();

        for (ArrayList<String> machine : strings) {
            int Ax = 0;
            int Ay = 0;

            int Bx = 0;
            int By = 0;

            int prizeX = 0;
            int prizeY = 0;

            for (int i = 0; i < machine.size(); i++) {
                String s1 = machine.get(i);

                if (i == 0) {
                    String[] x = s1.split("\\+");

                    Ay = Integer.parseInt(x[2]);
                    String[] x1 = x[1].split(", ");
                    Ax = Integer.parseInt(x1[0]);
                } else if (i == 1) {

                    String[] x = s1.split("\\+");

                    By = Integer.parseInt(x[2]);

                    String[] x1 = x[1].split(", ");
                    Bx = Integer.parseInt(x1[0]);
                } else {

                    String[] x = s1.split("=");

                    prizeY = Integer.parseInt(x[2]);

                    String[] x1 = x[1].split(", ");

                    prizeX = Integer.parseInt(x1[0]);
                }
            }

            Machine l = new Machine(Ax, Ay, Bx, By, prizeX, prizeY);

            machines.add(l);
        }

        return machines;
    }

    public static class Machine {
        //How much the machine will move in the X directions when Button A is pushed
        private int Ax;

        //How much the machine will move in the Y direction when Button A is pushed
        private int Ay;

        //How much the machine will move in the X direction when Button B is pushed
        private int Bx;

        //How much the machine will move in the Y direction when Button B is pushed
        private int By;

        //The x coordinate of the prize
        private long prizeX;

        //The y coordinate of the prize
        private long prizeY;

        public Machine(int Ax, int Ay, int Bx, int By, int prizeX, int prizeY) {
            this.Ax = Ax;
            this.Ay = Ay;

            this.Bx = Bx;
            this.By = By;

            this.prizeX = prizeX;
            this.prizeY = prizeY;
        }

        public int getAx() {
            return Ax;
        }

        public int getAy() {
            return Ay;
        }

        public int getBx() {
            return Bx;
        }

        public int getBy() {
            return By;
        }

        public long getPrizeX() {
            return prizeX;
        }

        public long getPrizeY() {
            return prizeY;
        }

        public void setPrizeX(long prizeX) {
            this.prizeX = prizeX;
        }

        public void setPrizeY(long prizeY) {
            this.prizeY = prizeY;
        }

        @Override
        public String toString() {
            return "(" + Ax + ", " + Ay + ", " + Bx + ", " + By + ", " + prizeX + ", " + prizeY + ")";
        }
    }

    public static void main(String[] args) {
        String input = "Button A: X+24, Y+90\n" +
                "Button B: X+85, Y+62\n" +
                "Prize: X=6844, Y=6152\n" +
                "\n" +
                "Button A: X+35, Y+12\n" +
                "Button B: X+17, Y+52\n" +
                "Prize: X=9516, Y=13408\n" +
                "\n" +
                "Button A: X+38, Y+79\n" +
                "Button B: X+28, Y+13\n" +
                "Prize: X=3648, Y=4148\n" +
                "\n" +
                "Button A: X+32, Y+60\n" +
                "Button B: X+27, Y+14\n" +
                "Prize: X=11335, Y=18922\n" +
                "\n" +
                "Button A: X+29, Y+13\n" +
                "Button B: X+52, Y+76\n" +
                "Prize: X=7167, Y=1431\n" +
                "\n" +
                "Button A: X+39, Y+11\n" +
                "Button B: X+18, Y+64\n" +
                "Prize: X=3723, Y=3407\n" +
                "\n" +
                "Button A: X+97, Y+16\n" +
                "Button B: X+48, Y+64\n" +
                "Prize: X=8392, Y=6656\n" +
                "\n" +
                "Button A: X+49, Y+64\n" +
                "Button B: X+62, Y+15\n" +
                "Prize: X=5228, Y=4915\n" +
                "\n" +
                "Button A: X+99, Y+80\n" +
                "Button B: X+19, Y+98\n" +
                "Prize: X=9267, Y=9472\n" +
                "\n" +
                "Button A: X+23, Y+11\n" +
                "Button B: X+42, Y+68\n" +
                "Prize: X=15419, Y=9479\n" +
                "\n" +
                "Button A: X+26, Y+59\n" +
                "Button B: X+93, Y+53\n" +
                "Prize: X=8355, Y=9003\n" +
                "\n" +
                "Button A: X+47, Y+27\n" +
                "Button B: X+29, Y+86\n" +
                "Prize: X=3760, Y=8678\n" +
                "\n" +
                "Button A: X+15, Y+61\n" +
                "Button B: X+72, Y+29\n" +
                "Prize: X=2831, Y=15765\n" +
                "\n" +
                "Button A: X+45, Y+57\n" +
                "Button B: X+79, Y+21\n" +
                "Prize: X=2983, Y=3225\n" +
                "\n" +
                "Button A: X+23, Y+73\n" +
                "Button B: X+64, Y+18\n" +
                "Prize: X=10496, Y=432\n" +
                "\n" +
                "Button A: X+42, Y+18\n" +
                "Button B: X+15, Y+23\n" +
                "Prize: X=4412, Y=13708\n" +
                "\n" +
                "Button A: X+13, Y+99\n" +
                "Button B: X+83, Y+87\n" +
                "Prize: X=2884, Y=9426\n" +
                "\n" +
                "Button A: X+38, Y+31\n" +
                "Button B: X+19, Y+87\n" +
                "Prize: X=2641, Y=4371\n" +
                "\n" +
                "Button A: X+30, Y+18\n" +
                "Button B: X+38, Y+89\n" +
                "Prize: X=3844, Y=5815\n" +
                "\n" +
                "Button A: X+46, Y+66\n" +
                "Button B: X+89, Y+40\n" +
                "Prize: X=5366, Y=4542\n" +
                "\n" +
                "Button A: X+23, Y+57\n" +
                "Button B: X+41, Y+18\n" +
                "Prize: X=5055, Y=11348\n" +
                "\n" +
                "Button A: X+28, Y+63\n" +
                "Button B: X+33, Y+14\n" +
                "Prize: X=3782, Y=6461\n" +
                "\n" +
                "Button A: X+27, Y+64\n" +
                "Button B: X+60, Y+12\n" +
                "Prize: X=6077, Y=11760\n" +
                "\n" +
                "Button A: X+12, Y+33\n" +
                "Button B: X+31, Y+20\n" +
                "Prize: X=2968, Y=3986\n" +
                "\n" +
                "Button A: X+86, Y+43\n" +
                "Button B: X+26, Y+99\n" +
                "Prize: X=6996, Y=5304\n" +
                "\n" +
                "Button A: X+17, Y+49\n" +
                "Button B: X+59, Y+16\n" +
                "Prize: X=18869, Y=6446\n" +
                "\n" +
                "Button A: X+14, Y+26\n" +
                "Button B: X+52, Y+15\n" +
                "Prize: X=19540, Y=11477\n" +
                "\n" +
                "Button A: X+30, Y+11\n" +
                "Button B: X+16, Y+56\n" +
                "Prize: X=9718, Y=17799\n" +
                "\n" +
                "Button A: X+58, Y+29\n" +
                "Button B: X+14, Y+30\n" +
                "Prize: X=5576, Y=16523\n" +
                "\n" +
                "Button A: X+76, Y+17\n" +
                "Button B: X+13, Y+48\n" +
                "Prize: X=17477, Y=5797\n" +
                "\n" +
                "Button A: X+39, Y+23\n" +
                "Button B: X+14, Y+46\n" +
                "Prize: X=768, Y=1664\n" +
                "\n" +
                "Button A: X+45, Y+24\n" +
                "Button B: X+20, Y+52\n" +
                "Prize: X=3365, Y=11324\n" +
                "\n" +
                "Button A: X+58, Y+30\n" +
                "Button B: X+12, Y+29\n" +
                "Prize: X=8988, Y=17006\n" +
                "\n" +
                "Button A: X+20, Y+50\n" +
                "Button B: X+54, Y+32\n" +
                "Prize: X=18628, Y=15584\n" +
                "\n" +
                "Button A: X+12, Y+51\n" +
                "Button B: X+23, Y+19\n" +
                "Prize: X=581, Y=1918\n" +
                "\n" +
                "Button A: X+36, Y+23\n" +
                "Button B: X+12, Y+33\n" +
                "Prize: X=18212, Y=435\n" +
                "\n" +
                "Button A: X+16, Y+91\n" +
                "Button B: X+82, Y+66\n" +
                "Prize: X=4870, Y=10482\n" +
                "\n" +
                "Button A: X+47, Y+17\n" +
                "Button B: X+31, Y+65\n" +
                "Prize: X=2105, Y=2375\n" +
                "\n" +
                "Button A: X+38, Y+12\n" +
                "Button B: X+21, Y+55\n" +
                "Prize: X=15306, Y=7946\n" +
                "\n" +
                "Button A: X+49, Y+12\n" +
                "Button B: X+15, Y+34\n" +
                "Prize: X=5845, Y=19480\n" +
                "\n" +
                "Button A: X+22, Y+56\n" +
                "Button B: X+61, Y+19\n" +
                "Prize: X=19689, Y=4311\n" +
                "\n" +
                "Button A: X+35, Y+11\n" +
                "Button B: X+55, Y+81\n" +
                "Prize: X=13845, Y=19665\n" +
                "\n" +
                "Button A: X+99, Y+56\n" +
                "Button B: X+35, Y+70\n" +
                "Prize: X=2477, Y=3108\n" +
                "\n" +
                "Button A: X+20, Y+46\n" +
                "Button B: X+55, Y+13\n" +
                "Prize: X=14990, Y=9952\n" +
                "\n" +
                "Button A: X+16, Y+57\n" +
                "Button B: X+88, Y+70\n" +
                "Prize: X=8008, Y=9292\n" +
                "\n" +
                "Button A: X+64, Y+35\n" +
                "Button B: X+25, Y+50\n" +
                "Prize: X=9072, Y=1630\n" +
                "\n" +
                "Button A: X+16, Y+52\n" +
                "Button B: X+85, Y+69\n" +
                "Prize: X=9222, Y=10490\n" +
                "\n" +
                "Button A: X+13, Y+28\n" +
                "Button B: X+22, Y+11\n" +
                "Prize: X=14631, Y=13080\n" +
                "\n" +
                "Button A: X+21, Y+57\n" +
                "Button B: X+56, Y+31\n" +
                "Prize: X=6202, Y=7154\n" +
                "\n" +
                "Button A: X+69, Y+39\n" +
                "Button B: X+20, Y+46\n" +
                "Prize: X=16373, Y=5333\n" +
                "\n" +
                "Button A: X+28, Y+89\n" +
                "Button B: X+38, Y+11\n" +
                "Prize: X=3346, Y=2182\n" +
                "\n" +
                "Button A: X+27, Y+13\n" +
                "Button B: X+12, Y+81\n" +
                "Prize: X=1839, Y=2766\n" +
                "\n" +
                "Button A: X+52, Y+16\n" +
                "Button B: X+13, Y+28\n" +
                "Prize: X=5255, Y=7124\n" +
                "\n" +
                "Button A: X+97, Y+19\n" +
                "Button B: X+33, Y+97\n" +
                "Prize: X=4966, Y=7944\n" +
                "\n" +
                "Button A: X+49, Y+16\n" +
                "Button B: X+37, Y+53\n" +
                "Prize: X=4406, Y=4794\n" +
                "\n" +
                "Button A: X+75, Y+30\n" +
                "Button B: X+34, Y+82\n" +
                "Prize: X=5247, Y=4356\n" +
                "\n" +
                "Button A: X+11, Y+29\n" +
                "Button B: X+42, Y+13\n" +
                "Prize: X=7934, Y=15651\n" +
                "\n" +
                "Button A: X+15, Y+84\n" +
                "Button B: X+94, Y+95\n" +
                "Prize: X=9602, Y=11494\n" +
                "\n" +
                "Button A: X+91, Y+14\n" +
                "Button B: X+17, Y+25\n" +
                "Prize: X=7393, Y=1406\n" +
                "\n" +
                "Button A: X+14, Y+40\n" +
                "Button B: X+52, Y+34\n" +
                "Prize: X=2492, Y=13796\n" +
                "\n" +
                "Button A: X+23, Y+82\n" +
                "Button B: X+63, Y+20\n" +
                "Prize: X=4609, Y=4974\n" +
                "\n" +
                "Button A: X+83, Y+90\n" +
                "Button B: X+76, Y+13\n" +
                "Prize: X=1528, Y=1171\n" +
                "\n" +
                "Button A: X+21, Y+78\n" +
                "Button B: X+57, Y+35\n" +
                "Prize: X=3840, Y=4897\n" +
                "\n" +
                "Button A: X+41, Y+64\n" +
                "Button B: X+46, Y+17\n" +
                "Prize: X=11922, Y=280\n" +
                "\n" +
                "Button A: X+12, Y+41\n" +
                "Button B: X+79, Y+41\n" +
                "Prize: X=1561, Y=984\n" +
                "\n" +
                "Button A: X+47, Y+14\n" +
                "Button B: X+18, Y+41\n" +
                "Prize: X=15870, Y=18265\n" +
                "\n" +
                "Button A: X+32, Y+90\n" +
                "Button B: X+79, Y+57\n" +
                "Prize: X=7507, Y=5751\n" +
                "\n" +
                "Button A: X+53, Y+41\n" +
                "Button B: X+20, Y+74\n" +
                "Prize: X=4057, Y=4309\n" +
                "\n" +
                "Button A: X+29, Y+55\n" +
                "Button B: X+36, Y+16\n" +
                "Prize: X=774, Y=19798\n" +
                "\n" +
                "Button A: X+50, Y+77\n" +
                "Button B: X+61, Y+22\n" +
                "Prize: X=8740, Y=6985\n" +
                "\n" +
                "Button A: X+36, Y+80\n" +
                "Button B: X+56, Y+17\n" +
                "Prize: X=13104, Y=8073\n" +
                "\n" +
                "Button A: X+43, Y+17\n" +
                "Button B: X+19, Y+47\n" +
                "Prize: X=2652, Y=11866\n" +
                "\n" +
                "Button A: X+31, Y+35\n" +
                "Button B: X+91, Y+21\n" +
                "Prize: X=5751, Y=2079\n" +
                "\n" +
                "Button A: X+20, Y+57\n" +
                "Button B: X+48, Y+19\n" +
                "Prize: X=9432, Y=5497\n" +
                "\n" +
                "Button A: X+55, Y+11\n" +
                "Button B: X+11, Y+47\n" +
                "Prize: X=13927, Y=1243\n" +
                "\n" +
                "Button A: X+52, Y+31\n" +
                "Button B: X+19, Y+36\n" +
                "Prize: X=6279, Y=12982\n" +
                "\n" +
                "Button A: X+94, Y+27\n" +
                "Button B: X+32, Y+59\n" +
                "Prize: X=7116, Y=4634\n" +
                "\n" +
                "Button A: X+80, Y+93\n" +
                "Button B: X+88, Y+19\n" +
                "Prize: X=9592, Y=9568\n" +
                "\n" +
                "Button A: X+78, Y+66\n" +
                "Button B: X+86, Y+14\n" +
                "Prize: X=2110, Y=610\n" +
                "\n" +
                "Button A: X+11, Y+34\n" +
                "Button B: X+37, Y+26\n" +
                "Prize: X=3050, Y=15020\n" +
                "\n" +
                "Button A: X+45, Y+21\n" +
                "Button B: X+33, Y+65\n" +
                "Prize: X=18344, Y=10176\n" +
                "\n" +
                "Button A: X+95, Y+18\n" +
                "Button B: X+19, Y+80\n" +
                "Prize: X=10431, Y=8394\n" +
                "\n" +
                "Button A: X+11, Y+56\n" +
                "Button B: X+23, Y+12\n" +
                "Prize: X=1687, Y=3544\n" +
                "\n" +
                "Button A: X+98, Y+12\n" +
                "Button B: X+19, Y+22\n" +
                "Prize: X=6174, Y=2684\n" +
                "\n" +
                "Button A: X+44, Y+23\n" +
                "Button B: X+22, Y+55\n" +
                "Prize: X=990, Y=2127\n" +
                "\n" +
                "Button A: X+98, Y+79\n" +
                "Button B: X+25, Y+90\n" +
                "Prize: X=8715, Y=9470\n" +
                "\n" +
                "Button A: X+52, Y+78\n" +
                "Button B: X+93, Y+43\n" +
                "Prize: X=5880, Y=6118\n" +
                "\n" +
                "Button A: X+39, Y+24\n" +
                "Button B: X+11, Y+35\n" +
                "Prize: X=695, Y=7532\n" +
                "\n" +
                "Button A: X+74, Y+19\n" +
                "Button B: X+15, Y+73\n" +
                "Prize: X=2032, Y=18163\n" +
                "\n" +
                "Button A: X+48, Y+84\n" +
                "Button B: X+49, Y+13\n" +
                "Prize: X=8411, Y=9347\n" +
                "\n" +
                "Button A: X+83, Y+13\n" +
                "Button B: X+15, Y+80\n" +
                "Prize: X=8921, Y=9801\n" +
                "\n" +
                "Button A: X+20, Y+11\n" +
                "Button B: X+19, Y+46\n" +
                "Prize: X=6215, Y=10670\n" +
                "\n" +
                "Button A: X+66, Y+43\n" +
                "Button B: X+14, Y+34\n" +
                "Prize: X=19654, Y=3157\n" +
                "\n" +
                "Button A: X+34, Y+18\n" +
                "Button B: X+16, Y+47\n" +
                "Prize: X=1392, Y=2124\n" +
                "\n" +
                "Button A: X+99, Y+57\n" +
                "Button B: X+11, Y+73\n" +
                "Prize: X=7403, Y=10129\n" +
                "\n" +
                "Button A: X+67, Y+13\n" +
                "Button B: X+18, Y+49\n" +
                "Prize: X=4689, Y=15148\n" +
                "\n" +
                "Button A: X+16, Y+89\n" +
                "Button B: X+69, Y+68\n" +
                "Prize: X=3203, Y=5500\n" +
                "\n" +
                "Button A: X+11, Y+30\n" +
                "Button B: X+49, Y+17\n" +
                "Prize: X=2144, Y=11482\n" +
                "\n" +
                "Button A: X+41, Y+21\n" +
                "Button B: X+12, Y+20\n" +
                "Prize: X=7659, Y=247\n" +
                "\n" +
                "Button A: X+12, Y+56\n" +
                "Button B: X+84, Y+26\n" +
                "Prize: X=18800, Y=9584\n" +
                "\n" +
                "Button A: X+28, Y+22\n" +
                "Button B: X+28, Y+96\n" +
                "Prize: X=3192, Y=8206\n" +
                "\n" +
                "Button A: X+68, Y+32\n" +
                "Button B: X+34, Y+87\n" +
                "Prize: X=2448, Y=2856\n" +
                "\n" +
                "Button A: X+71, Y+38\n" +
                "Button B: X+36, Y+84\n" +
                "Prize: X=3119, Y=5618\n" +
                "\n" +
                "Button A: X+75, Y+37\n" +
                "Button B: X+13, Y+33\n" +
                "Prize: X=18380, Y=16766\n" +
                "\n" +
                "Button A: X+24, Y+94\n" +
                "Button B: X+63, Y+16\n" +
                "Prize: X=4752, Y=1998\n" +
                "\n" +
                "Button A: X+23, Y+26\n" +
                "Button B: X+14, Y+72\n" +
                "Prize: X=2231, Y=6398\n" +
                "\n" +
                "Button A: X+56, Y+23\n" +
                "Button B: X+16, Y+61\n" +
                "Prize: X=19656, Y=5328\n" +
                "\n" +
                "Button A: X+32, Y+11\n" +
                "Button B: X+34, Y+73\n" +
                "Prize: X=15646, Y=10729\n" +
                "\n" +
                "Button A: X+89, Y+59\n" +
                "Button B: X+22, Y+88\n" +
                "Prize: X=2144, Y=5606\n" +
                "\n" +
                "Button A: X+26, Y+54\n" +
                "Button B: X+69, Y+37\n" +
                "Prize: X=2969, Y=9101\n" +
                "\n" +
                "Button A: X+40, Y+17\n" +
                "Button B: X+17, Y+40\n" +
                "Prize: X=7098, Y=16873\n" +
                "\n" +
                "Button A: X+39, Y+25\n" +
                "Button B: X+11, Y+29\n" +
                "Prize: X=17680, Y=18112\n" +
                "\n" +
                "Button A: X+63, Y+18\n" +
                "Button B: X+47, Y+60\n" +
                "Prize: X=10214, Y=7296\n" +
                "\n" +
                "Button A: X+18, Y+93\n" +
                "Button B: X+63, Y+32\n" +
                "Prize: X=2880, Y=5488\n" +
                "\n" +
                "Button A: X+59, Y+18\n" +
                "Button B: X+19, Y+40\n" +
                "Prize: X=14410, Y=14486\n" +
                "\n" +
                "Button A: X+20, Y+11\n" +
                "Button B: X+37, Y+64\n" +
                "Prize: X=1673, Y=2186\n" +
                "\n" +
                "Button A: X+13, Y+58\n" +
                "Button B: X+60, Y+26\n" +
                "Prize: X=19672, Y=5444\n" +
                "\n" +
                "Button A: X+49, Y+67\n" +
                "Button B: X+54, Y+12\n" +
                "Prize: X=5885, Y=1925\n" +
                "\n" +
                "Button A: X+37, Y+11\n" +
                "Button B: X+38, Y+78\n" +
                "Prize: X=5683, Y=5625\n" +
                "\n" +
                "Button A: X+12, Y+62\n" +
                "Button B: X+79, Y+14\n" +
                "Prize: X=17225, Y=2330\n" +
                "\n" +
                "Button A: X+43, Y+14\n" +
                "Button B: X+24, Y+62\n" +
                "Prize: X=4130, Y=4200\n" +
                "\n" +
                "Button A: X+61, Y+20\n" +
                "Button B: X+28, Y+60\n" +
                "Prize: X=5033, Y=2660\n" +
                "\n" +
                "Button A: X+11, Y+83\n" +
                "Button B: X+73, Y+37\n" +
                "Prize: X=945, Y=5589\n" +
                "\n" +
                "Button A: X+13, Y+29\n" +
                "Button B: X+67, Y+29\n" +
                "Prize: X=1739, Y=16997\n" +
                "\n" +
                "Button A: X+54, Y+26\n" +
                "Button B: X+24, Y+54\n" +
                "Prize: X=5426, Y=7340\n" +
                "\n" +
                "Button A: X+39, Y+75\n" +
                "Button B: X+61, Y+18\n" +
                "Prize: X=4003, Y=2832\n" +
                "\n" +
                "Button A: X+12, Y+28\n" +
                "Button B: X+91, Y+54\n" +
                "Prize: X=8830, Y=5720\n" +
                "\n" +
                "Button A: X+91, Y+25\n" +
                "Button B: X+63, Y+91\n" +
                "Prize: X=6867, Y=3213\n" +
                "\n" +
                "Button A: X+68, Y+47\n" +
                "Button B: X+12, Y+27\n" +
                "Prize: X=9304, Y=14668\n" +
                "\n" +
                "Button A: X+27, Y+71\n" +
                "Button B: X+47, Y+12\n" +
                "Prize: X=16276, Y=10143\n" +
                "\n" +
                "Button A: X+61, Y+33\n" +
                "Button B: X+40, Y+73\n" +
                "Prize: X=1204, Y=1884\n" +
                "\n" +
                "Button A: X+86, Y+16\n" +
                "Button B: X+81, Y+87\n" +
                "Prize: X=9166, Y=7172\n" +
                "\n" +
                "Button A: X+71, Y+33\n" +
                "Button B: X+34, Y+51\n" +
                "Prize: X=5308, Y=3699\n" +
                "\n" +
                "Button A: X+43, Y+13\n" +
                "Button B: X+31, Y+62\n" +
                "Prize: X=12192, Y=12767\n" +
                "\n" +
                "Button A: X+24, Y+72\n" +
                "Button B: X+50, Y+16\n" +
                "Prize: X=6290, Y=10064\n" +
                "\n" +
                "Button A: X+27, Y+78\n" +
                "Button B: X+66, Y+16\n" +
                "Prize: X=18800, Y=19496\n" +
                "\n" +
                "Button A: X+78, Y+27\n" +
                "Button B: X+45, Y+80\n" +
                "Prize: X=6576, Y=2534\n" +
                "\n" +
                "Button A: X+98, Y+77\n" +
                "Button B: X+20, Y+50\n" +
                "Prize: X=6014, Y=6131\n" +
                "\n" +
                "Button A: X+96, Y+86\n" +
                "Button B: X+84, Y+13\n" +
                "Prize: X=9564, Y=4397\n" +
                "\n" +
                "Button A: X+82, Y+38\n" +
                "Button B: X+20, Y+56\n" +
                "Prize: X=5398, Y=3810\n" +
                "\n" +
                "Button A: X+75, Y+13\n" +
                "Button B: X+41, Y+54\n" +
                "Prize: X=6566, Y=4702\n" +
                "\n" +
                "Button A: X+19, Y+64\n" +
                "Button B: X+47, Y+16\n" +
                "Prize: X=7224, Y=18384\n" +
                "\n" +
                "Button A: X+11, Y+35\n" +
                "Button B: X+85, Y+60\n" +
                "Prize: X=19073, Y=6705\n" +
                "\n" +
                "Button A: X+12, Y+75\n" +
                "Button B: X+73, Y+18\n" +
                "Prize: X=10383, Y=5657\n" +
                "\n" +
                "Button A: X+29, Y+86\n" +
                "Button B: X+59, Y+36\n" +
                "Prize: X=8157, Y=10988\n" +
                "\n" +
                "Button A: X+14, Y+57\n" +
                "Button B: X+65, Y+54\n" +
                "Prize: X=3829, Y=5268\n" +
                "\n" +
                "Button A: X+94, Y+50\n" +
                "Button B: X+33, Y+74\n" +
                "Prize: X=7069, Y=6526\n" +
                "\n" +
                "Button A: X+57, Y+75\n" +
                "Button B: X+58, Y+12\n" +
                "Prize: X=9591, Y=7989\n" +
                "\n" +
                "Button A: X+61, Y+12\n" +
                "Button B: X+22, Y+73\n" +
                "Prize: X=5098, Y=17882\n" +
                "\n" +
                "Button A: X+46, Y+17\n" +
                "Button B: X+28, Y+48\n" +
                "Prize: X=7156, Y=19724\n" +
                "\n" +
                "Button A: X+69, Y+12\n" +
                "Button B: X+13, Y+78\n" +
                "Prize: X=4318, Y=19370\n" +
                "\n" +
                "Button A: X+73, Y+45\n" +
                "Button B: X+16, Y+63\n" +
                "Prize: X=4180, Y=7731\n" +
                "\n" +
                "Button A: X+94, Y+63\n" +
                "Button B: X+46, Y+98\n" +
                "Prize: X=9390, Y=6965\n" +
                "\n" +
                "Button A: X+71, Y+37\n" +
                "Button B: X+17, Y+41\n" +
                "Prize: X=3706, Y=4374\n" +
                "\n" +
                "Button A: X+16, Y+39\n" +
                "Button B: X+74, Y+12\n" +
                "Prize: X=5730, Y=2349\n" +
                "\n" +
                "Button A: X+43, Y+19\n" +
                "Button B: X+18, Y+29\n" +
                "Prize: X=16545, Y=15945\n" +
                "\n" +
                "Button A: X+60, Y+34\n" +
                "Button B: X+14, Y+35\n" +
                "Prize: X=17606, Y=1627\n" +
                "\n" +
                "Button A: X+61, Y+11\n" +
                "Button B: X+74, Y+99\n" +
                "Prize: X=8420, Y=4345\n" +
                "\n" +
                "Button A: X+43, Y+14\n" +
                "Button B: X+21, Y+41\n" +
                "Prize: X=11041, Y=9308\n" +
                "\n" +
                "Button A: X+93, Y+16\n" +
                "Button B: X+21, Y+76\n" +
                "Prize: X=7698, Y=5740\n" +
                "\n" +
                "Button A: X+15, Y+56\n" +
                "Button B: X+75, Y+13\n" +
                "Prize: X=11540, Y=2847\n" +
                "\n" +
                "Button A: X+42, Y+74\n" +
                "Button B: X+32, Y+11\n" +
                "Prize: X=6202, Y=17775\n" +
                "\n" +
                "Button A: X+33, Y+98\n" +
                "Button B: X+33, Y+25\n" +
                "Prize: X=4290, Y=5659\n" +
                "\n" +
                "Button A: X+43, Y+90\n" +
                "Button B: X+83, Y+51\n" +
                "Prize: X=9733, Y=10431\n" +
                "\n" +
                "Button A: X+33, Y+12\n" +
                "Button B: X+17, Y+61\n" +
                "Prize: X=2419, Y=14298\n" +
                "\n" +
                "Button A: X+16, Y+37\n" +
                "Button B: X+63, Y+43\n" +
                "Prize: X=13097, Y=1953\n" +
                "\n" +
                "Button A: X+23, Y+43\n" +
                "Button B: X+29, Y+16\n" +
                "Prize: X=11873, Y=10532\n" +
                "\n" +
                "Button A: X+79, Y+25\n" +
                "Button B: X+14, Y+63\n" +
                "Prize: X=3288, Y=3559\n" +
                "\n" +
                "Button A: X+17, Y+80\n" +
                "Button B: X+66, Y+14\n" +
                "Prize: X=12830, Y=16300\n" +
                "\n" +
                "Button A: X+64, Y+19\n" +
                "Button B: X+13, Y+64\n" +
                "Prize: X=9628, Y=343\n" +
                "\n" +
                "Button A: X+50, Y+15\n" +
                "Button B: X+12, Y+92\n" +
                "Prize: X=1876, Y=4806\n" +
                "\n" +
                "Button A: X+35, Y+14\n" +
                "Button B: X+25, Y+48\n" +
                "Prize: X=14915, Y=10180\n" +
                "\n" +
                "Button A: X+85, Y+79\n" +
                "Button B: X+12, Y+68\n" +
                "Prize: X=2124, Y=7204\n" +
                "\n" +
                "Button A: X+51, Y+81\n" +
                "Button B: X+98, Y+27\n" +
                "Prize: X=4720, Y=1836\n" +
                "\n" +
                "Button A: X+31, Y+44\n" +
                "Button B: X+64, Y+28\n" +
                "Prize: X=5380, Y=4180\n" +
                "\n" +
                "Button A: X+13, Y+40\n" +
                "Button B: X+80, Y+39\n" +
                "Prize: X=15000, Y=17783\n" +
                "\n" +
                "Button A: X+35, Y+87\n" +
                "Button B: X+40, Y+23\n" +
                "Prize: X=4065, Y=4143\n" +
                "\n" +
                "Button A: X+82, Y+11\n" +
                "Button B: X+11, Y+67\n" +
                "Prize: X=19056, Y=2707\n" +
                "\n" +
                "Button A: X+82, Y+16\n" +
                "Button B: X+69, Y+80\n" +
                "Prize: X=3085, Y=1600\n" +
                "\n" +
                "Button A: X+32, Y+73\n" +
                "Button B: X+83, Y+48\n" +
                "Prize: X=5700, Y=6784\n" +
                "\n" +
                "Button A: X+16, Y+52\n" +
                "Button B: X+54, Y+30\n" +
                "Prize: X=12928, Y=8512\n" +
                "\n" +
                "Button A: X+11, Y+95\n" +
                "Button B: X+84, Y+82\n" +
                "Prize: X=4187, Y=7205\n" +
                "\n" +
                "Button A: X+70, Y+24\n" +
                "Button B: X+11, Y+60\n" +
                "Prize: X=7383, Y=3308\n" +
                "\n" +
                "Button A: X+15, Y+70\n" +
                "Button B: X+78, Y+16\n" +
                "Prize: X=10688, Y=2316\n" +
                "\n" +
                "Button A: X+42, Y+70\n" +
                "Button B: X+27, Y+14\n" +
                "Prize: X=2787, Y=3374\n" +
                "\n" +
                "Button A: X+28, Y+65\n" +
                "Button B: X+50, Y+15\n" +
                "Prize: X=8694, Y=13205\n" +
                "\n" +
                "Button A: X+33, Y+66\n" +
                "Button B: X+52, Y+22\n" +
                "Prize: X=3329, Y=3542\n" +
                "\n" +
                "Button A: X+77, Y+58\n" +
                "Button B: X+16, Y+56\n" +
                "Prize: X=2219, Y=2902\n" +
                "\n" +
                "Button A: X+73, Y+13\n" +
                "Button B: X+67, Y+74\n" +
                "Prize: X=6479, Y=1340\n" +
                "\n" +
                "Button A: X+57, Y+21\n" +
                "Button B: X+27, Y+61\n" +
                "Prize: X=8618, Y=3574\n" +
                "\n" +
                "Button A: X+98, Y+67\n" +
                "Button B: X+11, Y+58\n" +
                "Prize: X=9734, Y=11299\n" +
                "\n" +
                "Button A: X+35, Y+18\n" +
                "Button B: X+24, Y+68\n" +
                "Prize: X=1787, Y=1086\n" +
                "\n" +
                "Button A: X+48, Y+12\n" +
                "Button B: X+12, Y+52\n" +
                "Prize: X=5256, Y=4548\n" +
                "\n" +
                "Button A: X+11, Y+65\n" +
                "Button B: X+53, Y+35\n" +
                "Prize: X=4903, Y=5605\n" +
                "\n" +
                "Button A: X+72, Y+23\n" +
                "Button B: X+14, Y+57\n" +
                "Prize: X=1906, Y=11572\n" +
                "\n" +
                "Button A: X+70, Y+11\n" +
                "Button B: X+23, Y+75\n" +
                "Prize: X=19700, Y=6986\n" +
                "\n" +
                "Button A: X+68, Y+80\n" +
                "Button B: X+69, Y+24\n" +
                "Prize: X=9476, Y=5888\n" +
                "\n" +
                "Button A: X+65, Y+18\n" +
                "Button B: X+84, Y+94\n" +
                "Prize: X=12438, Y=10306\n" +
                "\n" +
                "Button A: X+22, Y+54\n" +
                "Button B: X+68, Y+41\n" +
                "Prize: X=7188, Y=6941\n" +
                "\n" +
                "Button A: X+17, Y+74\n" +
                "Button B: X+87, Y+55\n" +
                "Prize: X=1355, Y=3956\n" +
                "\n" +
                "Button A: X+42, Y+21\n" +
                "Button B: X+12, Y+49\n" +
                "Prize: X=10190, Y=3098\n" +
                "\n" +
                "Button A: X+27, Y+42\n" +
                "Button B: X+77, Y+14\n" +
                "Prize: X=9691, Y=5026\n" +
                "\n" +
                "Button A: X+72, Y+58\n" +
                "Button B: X+18, Y+52\n" +
                "Prize: X=900, Y=1250\n" +
                "\n" +
                "Button A: X+36, Y+20\n" +
                "Button B: X+13, Y+33\n" +
                "Prize: X=14707, Y=8159\n" +
                "\n" +
                "Button A: X+17, Y+42\n" +
                "Button B: X+70, Y+35\n" +
                "Prize: X=15398, Y=7053\n" +
                "\n" +
                "Button A: X+34, Y+92\n" +
                "Button B: X+80, Y+61\n" +
                "Prize: X=6518, Y=9397\n" +
                "\n" +
                "Button A: X+69, Y+21\n" +
                "Button B: X+24, Y+74\n" +
                "Prize: X=2210, Y=5886\n" +
                "\n" +
                "Button A: X+26, Y+47\n" +
                "Button B: X+36, Y+13\n" +
                "Prize: X=11826, Y=8246\n" +
                "\n" +
                "Button A: X+26, Y+62\n" +
                "Button B: X+53, Y+21\n" +
                "Prize: X=10452, Y=6784\n" +
                "\n" +
                "Button A: X+11, Y+51\n" +
                "Button B: X+56, Y+26\n" +
                "Prize: X=1379, Y=2889\n" +
                "\n" +
                "Button A: X+43, Y+19\n" +
                "Button B: X+13, Y+43\n" +
                "Prize: X=12235, Y=12619\n" +
                "\n" +
                "Button A: X+36, Y+17\n" +
                "Button B: X+39, Y+71\n" +
                "Prize: X=15980, Y=10671\n" +
                "\n" +
                "Button A: X+46, Y+71\n" +
                "Button B: X+38, Y+17\n" +
                "Prize: X=12312, Y=13864\n" +
                "\n" +
                "Button A: X+67, Y+27\n" +
                "Button B: X+33, Y+96\n" +
                "Prize: X=6423, Y=4077\n" +
                "\n" +
                "Button A: X+13, Y+74\n" +
                "Button B: X+56, Y+15\n" +
                "Prize: X=15247, Y=5930\n" +
                "\n" +
                "Button A: X+35, Y+24\n" +
                "Button B: X+15, Y+40\n" +
                "Prize: X=1495, Y=15400\n" +
                "\n" +
                "Button A: X+23, Y+89\n" +
                "Button B: X+81, Y+67\n" +
                "Prize: X=4856, Y=5976\n" +
                "\n" +
                "Button A: X+12, Y+29\n" +
                "Button B: X+55, Y+39\n" +
                "Prize: X=6293, Y=9855\n" +
                "\n" +
                "Button A: X+19, Y+79\n" +
                "Button B: X+75, Y+16\n" +
                "Prize: X=2104, Y=13185\n" +
                "\n" +
                "Button A: X+57, Y+15\n" +
                "Button B: X+47, Y+80\n" +
                "Prize: X=4034, Y=5390\n" +
                "\n" +
                "Button A: X+86, Y+18\n" +
                "Button B: X+13, Y+19\n" +
                "Prize: X=3073, Y=1799\n" +
                "\n" +
                "Button A: X+22, Y+34\n" +
                "Button B: X+60, Y+14\n" +
                "Prize: X=4630, Y=2668\n" +
                "\n" +
                "Button A: X+37, Y+16\n" +
                "Button B: X+19, Y+64\n" +
                "Prize: X=17692, Y=2992\n" +
                "\n" +
                "Button A: X+65, Y+57\n" +
                "Button B: X+20, Y+68\n" +
                "Prize: X=3040, Y=3776\n" +
                "\n" +
                "Button A: X+57, Y+12\n" +
                "Button B: X+14, Y+41\n" +
                "Prize: X=5617, Y=2170\n" +
                "\n" +
                "Button A: X+80, Y+42\n" +
                "Button B: X+16, Y+49\n" +
                "Prize: X=18832, Y=970\n" +
                "\n" +
                "Button A: X+61, Y+13\n" +
                "Button B: X+24, Y+65\n" +
                "Prize: X=16814, Y=16994\n" +
                "\n" +
                "Button A: X+21, Y+11\n" +
                "Button B: X+38, Y+66\n" +
                "Prize: X=4440, Y=2696\n" +
                "\n" +
                "Button A: X+21, Y+69\n" +
                "Button B: X+51, Y+17\n" +
                "Prize: X=4359, Y=4987\n" +
                "\n" +
                "Button A: X+13, Y+44\n" +
                "Button B: X+96, Y+61\n" +
                "Prize: X=10409, Y=9366\n" +
                "\n" +
                "Button A: X+46, Y+84\n" +
                "Button B: X+89, Y+16\n" +
                "Prize: X=2678, Y=3132\n" +
                "\n" +
                "Button A: X+12, Y+41\n" +
                "Button B: X+32, Y+11\n" +
                "Prize: X=16352, Y=14216\n" +
                "\n" +
                "Button A: X+26, Y+72\n" +
                "Button B: X+97, Y+18\n" +
                "Prize: X=6371, Y=1854\n" +
                "\n" +
                "Button A: X+23, Y+52\n" +
                "Button B: X+47, Y+24\n" +
                "Prize: X=16247, Y=19584\n" +
                "\n" +
                "Button A: X+64, Y+28\n" +
                "Button B: X+24, Y+64\n" +
                "Prize: X=17024, Y=3816\n" +
                "\n" +
                "Button A: X+14, Y+63\n" +
                "Button B: X+83, Y+81\n" +
                "Prize: X=1233, Y=1746\n" +
                "\n" +
                "Button A: X+54, Y+13\n" +
                "Button B: X+31, Y+64\n" +
                "Prize: X=6604, Y=13773\n" +
                "\n" +
                "Button A: X+22, Y+65\n" +
                "Button B: X+54, Y+21\n" +
                "Prize: X=3136, Y=4356\n" +
                "\n" +
                "Button A: X+95, Y+61\n" +
                "Button B: X+23, Y+57\n" +
                "Prize: X=2061, Y=1619\n" +
                "\n" +
                "Button A: X+91, Y+92\n" +
                "Button B: X+86, Y+18\n" +
                "Prize: X=6816, Y=5512\n" +
                "\n" +
                "Button A: X+55, Y+17\n" +
                "Button B: X+34, Y+69\n" +
                "Prize: X=14540, Y=6542\n" +
                "\n" +
                "Button A: X+79, Y+35\n" +
                "Button B: X+14, Y+47\n" +
                "Prize: X=18571, Y=6471\n" +
                "\n" +
                "Button A: X+57, Y+26\n" +
                "Button B: X+40, Y+70\n" +
                "Prize: X=8564, Y=8202\n" +
                "\n" +
                "Button A: X+23, Y+67\n" +
                "Button B: X+65, Y+16\n" +
                "Prize: X=3491, Y=12320\n" +
                "\n" +
                "Button A: X+11, Y+55\n" +
                "Button B: X+73, Y+33\n" +
                "Prize: X=17828, Y=12728\n" +
                "\n" +
                "Button A: X+35, Y+81\n" +
                "Button B: X+96, Y+46\n" +
                "Prize: X=5039, Y=4791\n" +
                "\n" +
                "Button A: X+96, Y+49\n" +
                "Button B: X+29, Y+62\n" +
                "Prize: X=5486, Y=6104\n" +
                "\n" +
                "Button A: X+46, Y+17\n" +
                "Button B: X+26, Y+61\n" +
                "Prize: X=15944, Y=4136\n" +
                "\n" +
                "Button A: X+18, Y+38\n" +
                "Button B: X+19, Y+11\n" +
                "Prize: X=10523, Y=6611\n" +
                "\n" +
                "Button A: X+33, Y+64\n" +
                "Button B: X+44, Y+19\n" +
                "Prize: X=12530, Y=11701\n" +
                "\n" +
                "Button A: X+16, Y+32\n" +
                "Button B: X+51, Y+33\n" +
                "Prize: X=14647, Y=2445\n" +
                "\n" +
                "Button A: X+46, Y+65\n" +
                "Button B: X+83, Y+37\n" +
                "Prize: X=4994, Y=4006\n" +
                "\n" +
                "Button A: X+64, Y+20\n" +
                "Button B: X+12, Y+51\n" +
                "Prize: X=13152, Y=2284\n" +
                "\n" +
                "Button A: X+51, Y+37\n" +
                "Button B: X+11, Y+27\n" +
                "Prize: X=8177, Y=18329\n" +
                "\n" +
                "Button A: X+54, Y+97\n" +
                "Button B: X+40, Y+18\n" +
                "Prize: X=6680, Y=7368\n" +
                "\n" +
                "Button A: X+26, Y+79\n" +
                "Button B: X+52, Y+33\n" +
                "Prize: X=4394, Y=7726\n" +
                "\n" +
                "Button A: X+30, Y+75\n" +
                "Button B: X+78, Y+22\n" +
                "Prize: X=6390, Y=3865\n" +
                "\n" +
                "Button A: X+23, Y+62\n" +
                "Button B: X+90, Y+15\n" +
                "Prize: X=3347, Y=5153\n" +
                "\n" +
                "Button A: X+30, Y+50\n" +
                "Button B: X+61, Y+28\n" +
                "Prize: X=6580, Y=5810\n" +
                "\n" +
                "Button A: X+73, Y+13\n" +
                "Button B: X+11, Y+78\n" +
                "Prize: X=11150, Y=7400\n" +
                "\n" +
                "Button A: X+17, Y+89\n" +
                "Button B: X+72, Y+78\n" +
                "Prize: X=2965, Y=3565\n" +
                "\n" +
                "Button A: X+62, Y+93\n" +
                "Button B: X+58, Y+13\n" +
                "Prize: X=4424, Y=1308\n" +
                "\n" +
                "Button A: X+15, Y+97\n" +
                "Button B: X+97, Y+28\n" +
                "Prize: X=10668, Y=9659\n" +
                "\n" +
                "Button A: X+24, Y+77\n" +
                "Button B: X+64, Y+49\n" +
                "Prize: X=2800, Y=6482\n" +
                "\n" +
                "Button A: X+43, Y+12\n" +
                "Button B: X+17, Y+32\n" +
                "Prize: X=3533, Y=9192\n" +
                "\n" +
                "Button A: X+56, Y+30\n" +
                "Button B: X+22, Y+46\n" +
                "Prize: X=11852, Y=5624\n" +
                "\n" +
                "Button A: X+16, Y+27\n" +
                "Button B: X+44, Y+22\n" +
                "Prize: X=5416, Y=11356\n" +
                "\n" +
                "Button A: X+44, Y+12\n" +
                "Button B: X+33, Y+78\n" +
                "Prize: X=2861, Y=11042\n" +
                "\n" +
                "Button A: X+72, Y+40\n" +
                "Button B: X+25, Y+52\n" +
                "Prize: X=4348, Y=5312\n" +
                "\n" +
                "Button A: X+88, Y+23\n" +
                "Button B: X+87, Y+94\n" +
                "Prize: X=8018, Y=7654\n" +
                "\n" +
                "Button A: X+20, Y+74\n" +
                "Button B: X+45, Y+11\n" +
                "Prize: X=19355, Y=18661\n" +
                "\n" +
                "Button A: X+69, Y+16\n" +
                "Button B: X+20, Y+65\n" +
                "Prize: X=18261, Y=8934\n" +
                "\n" +
                "Button A: X+80, Y+32\n" +
                "Button B: X+19, Y+66\n" +
                "Prize: X=8136, Y=12400\n" +
                "\n" +
                "Button A: X+40, Y+14\n" +
                "Button B: X+13, Y+53\n" +
                "Prize: X=7815, Y=11767\n" +
                "\n" +
                "Button A: X+55, Y+28\n" +
                "Button B: X+31, Y+52\n" +
                "Prize: X=14530, Y=5416\n" +
                "\n" +
                "Button A: X+38, Y+20\n" +
                "Button B: X+26, Y+53\n" +
                "Prize: X=17104, Y=6610\n" +
                "\n" +
                "Button A: X+54, Y+25\n" +
                "Button B: X+20, Y+47\n" +
                "Prize: X=10538, Y=5739\n" +
                "\n" +
                "Button A: X+43, Y+21\n" +
                "Button B: X+19, Y+49\n" +
                "Prize: X=3514, Y=19674\n" +
                "\n" +
                "Button A: X+12, Y+27\n" +
                "Button B: X+72, Y+40\n" +
                "Prize: X=14996, Y=15251\n" +
                "\n" +
                "Button A: X+35, Y+60\n" +
                "Button B: X+33, Y+11\n" +
                "Prize: X=18903, Y=10131\n" +
                "\n" +
                "Button A: X+44, Y+14\n" +
                "Button B: X+42, Y+79\n" +
                "Prize: X=15818, Y=1719\n" +
                "\n" +
                "Button A: X+12, Y+81\n" +
                "Button B: X+97, Y+29\n" +
                "Prize: X=5661, Y=2544\n" +
                "\n" +
                "Button A: X+29, Y+84\n" +
                "Button B: X+95, Y+30\n" +
                "Prize: X=7753, Y=9708\n" +
                "\n" +
                "Button A: X+66, Y+38\n" +
                "Button B: X+14, Y+32\n" +
                "Prize: X=2624, Y=4182\n" +
                "\n" +
                "Button A: X+91, Y+48\n" +
                "Button B: X+29, Y+79\n" +
                "Prize: X=7537, Y=4740\n" +
                "\n" +
                "Button A: X+43, Y+28\n" +
                "Button B: X+17, Y+60\n" +
                "Prize: X=3448, Y=6600\n" +
                "\n" +
                "Button A: X+52, Y+92\n" +
                "Button B: X+93, Y+27\n" +
                "Prize: X=3518, Y=2098\n" +
                "\n" +
                "Button A: X+68, Y+49\n" +
                "Button B: X+16, Y+39\n" +
                "Prize: X=12960, Y=14984\n" +
                "\n" +
                "Button A: X+67, Y+24\n" +
                "Button B: X+13, Y+53\n" +
                "Prize: X=13552, Y=14921\n" +
                "\n" +
                "Button A: X+89, Y+12\n" +
                "Button B: X+39, Y+60\n" +
                "Prize: X=1380, Y=624\n" +
                "\n" +
                "Button A: X+94, Y+44\n" +
                "Button B: X+16, Y+72\n" +
                "Prize: X=2194, Y=7220\n" +
                "\n" +
                "Button A: X+14, Y+57\n" +
                "Button B: X+61, Y+50\n" +
                "Prize: X=4637, Y=5986\n" +
                "\n" +
                "Button A: X+27, Y+46\n" +
                "Button B: X+36, Y+20\n" +
                "Prize: X=2105, Y=5626\n" +
                "\n" +
                "Button A: X+15, Y+47\n" +
                "Button B: X+78, Y+48\n" +
                "Prize: X=3839, Y=12295\n" +
                "\n" +
                "Button A: X+18, Y+29\n" +
                "Button B: X+43, Y+17\n" +
                "Prize: X=17455, Y=3821\n" +
                "\n" +
                "Button A: X+69, Y+15\n" +
                "Button B: X+66, Y+74\n" +
                "Prize: X=7281, Y=2239\n" +
                "\n" +
                "Button A: X+33, Y+19\n" +
                "Button B: X+14, Y+56\n" +
                "Prize: X=352, Y=730\n" +
                "\n" +
                "Button A: X+50, Y+21\n" +
                "Button B: X+24, Y+65\n" +
                "Prize: X=5922, Y=11179\n" +
                "\n" +
                "Button A: X+39, Y+76\n" +
                "Button B: X+47, Y+12\n" +
                "Prize: X=10853, Y=14212\n" +
                "\n" +
                "Button A: X+68, Y+18\n" +
                "Button B: X+22, Y+95\n" +
                "Prize: X=404, Y=642\n" +
                "\n" +
                "Button A: X+34, Y+14\n" +
                "Button B: X+31, Y+63\n" +
                "Prize: X=11559, Y=9363\n" +
                "\n" +
                "Button A: X+18, Y+47\n" +
                "Button B: X+57, Y+27\n" +
                "Prize: X=7778, Y=19391\n" +
                "\n" +
                "Button A: X+65, Y+33\n" +
                "Button B: X+18, Y+52\n" +
                "Prize: X=17494, Y=5474\n" +
                "\n" +
                "Button A: X+17, Y+62\n" +
                "Button B: X+79, Y+27\n" +
                "Prize: X=5390, Y=17212\n" +
                "\n" +
                "Button A: X+41, Y+92\n" +
                "Button B: X+75, Y+53\n" +
                "Prize: X=4438, Y=7422\n" +
                "\n" +
                "Button A: X+59, Y+75\n" +
                "Button B: X+30, Y+12\n" +
                "Prize: X=18210, Y=1130\n" +
                "\n" +
                "Button A: X+27, Y+96\n" +
                "Button B: X+48, Y+14\n" +
                "Prize: X=2340, Y=4560\n" +
                "\n" +
                "Button A: X+57, Y+30\n" +
                "Button B: X+61, Y+97\n" +
                "Prize: X=9785, Y=11315\n" +
                "\n" +
                "Button A: X+43, Y+90\n" +
                "Button B: X+49, Y+15\n" +
                "Prize: X=2321, Y=4245\n" +
                "\n" +
                "Button A: X+22, Y+89\n" +
                "Button B: X+77, Y+11\n" +
                "Prize: X=2541, Y=7575\n" +
                "\n" +
                "Button A: X+77, Y+52\n" +
                "Button B: X+16, Y+43\n" +
                "Prize: X=14313, Y=10777\n" +
                "\n" +
                "Button A: X+16, Y+59\n" +
                "Button B: X+50, Y+14\n" +
                "Prize: X=1820, Y=2413\n" +
                "\n" +
                "Button A: X+68, Y+16\n" +
                "Button B: X+22, Y+65\n" +
                "Prize: X=3078, Y=5197\n" +
                "\n" +
                "Button A: X+71, Y+28\n" +
                "Button B: X+22, Y+65\n" +
                "Prize: X=732, Y=18964\n" +
                "\n" +
                "Button A: X+47, Y+78\n" +
                "Button B: X+39, Y+16\n" +
                "Prize: X=13668, Y=5742\n" +
                "\n" +
                "Button A: X+37, Y+24\n" +
                "Button B: X+14, Y+47\n" +
                "Prize: X=3255, Y=3211\n" +
                "\n" +
                "Button A: X+51, Y+17\n" +
                "Button B: X+21, Y+65\n" +
                "Prize: X=6177, Y=5597\n" +
                "\n" +
                "Button A: X+12, Y+73\n" +
                "Button B: X+86, Y+23\n" +
                "Prize: X=5142, Y=14930\n" +
                "\n" +
                "Button A: X+45, Y+25\n" +
                "Button B: X+30, Y+51\n" +
                "Prize: X=4655, Y=8742\n" +
                "\n" +
                "Button A: X+29, Y+85\n" +
                "Button B: X+52, Y+16\n" +
                "Prize: X=4745, Y=6405";

       String input1 = "Button A: X+94, Y+34\n" +
               "Button B: X+22, Y+67\n" +
               "Prize: X=8400, Y=5400\n" +
               "\n" +
               "Button A: X+26, Y+66\n" +
               "Button B: X+67, Y+21\n" +
               "Prize: X=12748, Y=12176\n" +
               "\n" +
               "Button A: X+17, Y+86\n" +
               "Button B: X+84, Y+37\n" +
               "Prize: X=7870, Y=6450\n" +
               "\n" +
               "Button A: X+69, Y+23\n" +
               "Button B: X+27, Y+71\n" +
               "Prize: X=18641, Y=10279";

        System.out.println(totalCost2(input));







    }
}
