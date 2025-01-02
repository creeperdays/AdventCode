import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Day21 {

    public static long calcComplexity(String input) {
        String[] s = input.split("\n");

        long sum = 0L;

        for (String s1 : s) {
            sum = sum + complexity(s1, 25);
        }

        return sum;
    }

    //Calculates the complexity of a code based on the number of robots
    public static long complexity(String code, int robots) {

        ArrayList<String> stage1 = smallest1(code);

        long min = Long.MAX_VALUE;

        for (String s1 : stage1) {
            HashMap<String, Long> map = new HashMap<String, Long>();

            optimalSubseq(s1, map);

            for (int i = 0; i < robots - 1; i++) {

                Set<String> s = new HashSet<>(map.keySet());
                HashMap<String, Long> map2 = new HashMap<String, Long>();

                for (String key : s) {
                    HashMap<String, Long> map1 = createMap(key);


                    for (String key1 : map1.keySet()) {
                        map2.put(key1, map2.getOrDefault(key1, 0L) + map1.get(key1)*map.get(key));
                    }

                }

                map = map2;

            }

            long length = 0;


            for (String s11 : map.keySet()) {
                length = length + s11.length()*map.get(s11);
            }

            if (length < min) {
                min = length;
            }

        }

        return min * Integer.parseInt(code.substring(0, 3));

    }

    public static void optimalSubseq(String code, HashMap<String, Long> map) {

        ArrayList<String> seq = subsequences(code);

        for (String s : seq) {
            HashMap<String, Long> map1 = createMap(s);

            for (String key : map1.keySet()) {
                if (!map.containsKey(key)) {
                    map.put(key, map1.get(key)); ;
                } else {
                    map.put(key, map.get(key) + map1.get(key));
                }
            }

        }

    }

    public static String createString(String code) {
        char start = 'A';

        String n = "";

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            String s = optimal(start, c);

            n = n + s;

            start = c;
        }

        return n;
    }

    public static HashMap<String, Long> createMap(String code) {

        char start = 'A';

        HashMap<String, Long> map = new HashMap<String, Long>();

        String n = "";

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            String s = optimal(start, c);

            n = n + s;

            if (!map.containsKey(s)) {
                map.put(s, 1L);
            } else {
                map.put(s, map.get(s) + 1);
            }

            start = c;
        }



        return map;
    }

    //Only for directional pad
    public static String optimal(char start, char end) {
        if (start == 'A' && end == 'A') {
            return "A";
        } else if (start == 'A' && end == '^') {
            return "<A";
        } else if (start == 'A' && end == '<') {
            return "v<<A";
        } else if (start == 'A' && end == 'v') {
            return "<vA";
        } else if (start =='A' && end == '>') {
            return "vA";
        } else if (start == '^' && end == 'A') {
            return ">A";
        } else if (start == '^' && end == '^') {
            return "A";
        } else if (start == '^' && end == '<') {
            return "v<A";
        } else if (start == '^' && end == 'v') {
            return "vA";
        } else if (start == '^' && end == '>') {
            return "v>A";
        } else if (start == '<' && end == 'A') {
            return ">>^A";
        } else if (start == '<' && end == '^') {
            return ">^A";
        } else if (start == '<' && end == '<') {
            return "A";
        } else if (start == '<' && end == 'v') {
            return ">A";
        } else if (start == '<' && end == '>') {
            return ">>A";
        } else if (start == 'v' && end == 'A') {
            return "^>A";
        } else if (start == 'v' && end == '^') {
            return "^A";
        } else if (start == 'v' && end == '<') {
            return "<A";
        } else if (start == 'v' && end == 'v') {
            return "A";
        } else if (start =='v' && end == '>') {
            return ">A";
        } else if (start == '>' && end =='A') {
            return "^A";
        } else if (start == '>' && end == '^') {
            return "<^A";
        } else if (start == '>' && end == '<') {
            return "<<A";
        } else if (start == '>' && end == 'v') {
            return "<A";
        } else  { //last combination is down down
            return "A";
        }
    }


    //Calculates the subsequences of a sequence
    public static ArrayList<String> subsequences(String sequence) {
        StringBuilder t = new StringBuilder();

        ArrayList<String> subsequence = new ArrayList<String>();

        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);

            if (c != 'A') {
                t.append(c);
            } else {
                t.append(c);
                subsequence.add(t.toString());
                t = new StringBuilder();
            }
        }

        return subsequence;

    }



    public static int minPush(String input) {

        ArrayList<String> stage1 = smallest1(input);

        ArrayList<String> stage2 = new ArrayList<String>();

        for (String s1 : stage1) {
            ArrayList<String> s2 = smallestd1(s1);
            stage2.addAll(s2);
        }

        Set<Integer> set = new HashSet<Integer>();

        for (String s2 : stage2) {
            ArrayList<String> s3 = smallestd1(s2);

            for (String s : s3) {
                set.add(s.length());

                if (s.length() == 44) {
                    System.out.println(s);
                    System.out.println(s2);
                }

            }
        }

        System.out.println(set);


        return 0;



    }

    public static ArrayList<String> smallestd1(String input) {
        char[][] dpad = dpad();

        ArrayList<String> w = new ArrayList<String>();

        ArrayList<ArrayList<Coord>> paths = new ArrayList<ArrayList<Coord>>();

        int index = 0;

        Coord curr = new Coord(0, 2);

        smallestd(input, dpad, w, paths, index, curr);

        return w;

    }


    //same but for the directional keyPad
    public static void smallestd(String input, char[][] numPad, ArrayList<String> w, ArrayList<ArrayList<Coord>> paths, int index, Coord curr) {

        if (index >= input.length()) { // should be as many Coord arrays as there are letters
            StringBuilder t = new StringBuilder();
            Coord curr1 = new Coord(0, 2);

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                ArrayList<Coord> p = paths.get(i);

                String s = convert(p, curr1);
                t.append(s);
                t.append('A');

                curr1 = find(numPad, c);
            }


            if (t.toString().equals("v<<A>>^A<AAAv>A>AA<vAAA^>A")) {
                System.out.println(paths);
                System.out.println(input);
            }

            w.add(t.toString());
        } else {
            char d = input.charAt(index);
            ArrayList<ArrayList<Coord>> p1 = findPaths(numPad, d, curr);

            ArrayList<ArrayList<Coord>> mps = minimumPaths(p1);


            //System.out.println("current point =" + curr + ", destination = " + d + ", minPaths =" + mps);
            for (ArrayList<Coord> c1 : mps) {
                paths.add(c1);
                //System.out.println("Before Recursion:" + paths);
                smallestd(input, numPad, w, paths, index + 1, find(numPad, d));
                //System.out.println("After Recursion:" + paths);
                paths.removeLast();
                //System.out.println("After Removing:" + c1 + " new Path:" + paths);
            }
        }


    }


    public static ArrayList<String> smallest1(String input) {
        char[][] numPad = numPad();

        ArrayList<String> w = new ArrayList<String>();

        ArrayList<ArrayList<Coord>> paths = new ArrayList<ArrayList<Coord>>();

        int index = 0;

        Coord curr = new Coord(3, 2);

        smallest(input, numPad, w, paths, index, curr);

        return w;

    }


    //Outputs all the possible shortest paths
    public static void smallest(String input, char[][] numPad, ArrayList<String> w, ArrayList<ArrayList<Coord>> paths, int index, Coord curr) {

        if (index >= input.length()) { // should be as many Coord arrays as there are letters
            StringBuilder t = new StringBuilder();
            Coord curr1 = new Coord(3, 2);

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                ArrayList<Coord> p = paths.get(i);

                String s = convert(p, curr1);
                t.append(s);
                t.append('A');

                curr1 = find(numPad, c);
            }

            w.add(t.toString());
        } else {
            char d = input.charAt(index);
            ArrayList<ArrayList<Coord>> p1 = findPaths(numPad, d, curr);

            ArrayList<ArrayList<Coord>> mps = minimumPaths(p1);

            for (ArrayList<Coord> c1 : mps) {
                paths.add(c1);
                smallest(input, numPad, w, paths, index + 1, find(numPad, d));
                paths.remove(c1);
            }
        }


    }

    //Only returns the paths with the minimum size in the path
    public static ArrayList<ArrayList<Coord>> minimumPaths(ArrayList<ArrayList<Coord>> paths) {

        int minSize = paths.getFirst().size();

        for (ArrayList<Coord> path : paths) {
            if (path.size() < minSize) {
                minSize = path.size();
            }
        }

        ArrayList<ArrayList<Coord>> minPaths = new ArrayList<ArrayList<Coord>>();

        for (ArrayList<Coord> path : paths) {
            if (path.size() == minSize) {
                minPaths.add(path);
            }
        }

        return minPaths;
    }


    //Converts it to readable string format
    public static String convert(ArrayList<Coord> shortest, Coord current) {
        StringBuilder p = new StringBuilder();

        Coord curr = current;

        for (Coord c : shortest) {
            //up of current
            if (c.getX() + 1 == curr.getX() && c.getY() == curr.getY()) {
                p.append('^');
                curr = c;
            }

            //right of current
            if (c.getX() == curr.getX() && c.getY() - 1 == curr.getY()) {
                p.append('>');
            }

            //down of current
            if (c.getX() - 1 == curr.getX() && c.getY() == curr.getY()) {
                p.append('v');
            }

            //left of current
            if (c.getX() == curr.getX() && c.getY() + 1 == curr.getY()) {
                p.append('<');
            }

            curr = c;
        }



        return p.toString();
    }


    public static char[][] dpad() {

        char[][] dpad = new char[2][3];

        dpad[0][0] = ' ';
        dpad[0][1] =  '^';
        dpad[0][2] = 'A';
        dpad[1][0] = '<';
        dpad[1][1] = 'v';
        dpad[1][2] = '>';


        return dpad;
    }

    public static String numPath1(char[][] numPad, String number) {

        StringBuilder series = new StringBuilder();

        Coord curr = new Coord(0, 2);

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            series.append(findPaths(numPad, c, curr));

            series.append('A');

            curr = find(numPad, c);

        }

        return series.toString();

    }

    public static String numPath(char[][] numPad, String number) {

        StringBuilder series = new StringBuilder();

        Coord curr = new Coord(numPad.length - 1, numPad[0].length - 1);

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            series.append(findPaths(numPad, c, curr));

            series.append('A');

            curr = find(numPad, c);

        }

        return series.toString();

    }

    public static Coord find (char[][] numPad, char c) {
        for (int i = 0; i < numPad.length; i++) {
            for (int j = 0; j < numPad[0].length; j++) {
                if (numPad[i][j] == c) {
                    Coord curr = new Coord(i, j);

                    return curr;
                }
            }
        }

        return null;
    }

    //Finds the path in left right notation
    public static String path(ArrayList<ArrayList<Coord>> paths, Coord current) {
        //First find the shortest path (the one with the least size)

        int index = 0;

        int minSize = paths.getFirst().size();

        for (int i = 0; i < paths.size(); i++) {
            ArrayList<Coord> p = paths.get(i);

            if (p.size() < minSize) {
                minSize = p.size();
                index = i;
            }
        }

        ArrayList<Coord> shortest = paths.get(index);

        StringBuilder p = new StringBuilder();

        Coord curr = current;

        for (Coord c : shortest) {
            //up of current
            if (c.getX() + 1 == curr.getX() && c.getY() == curr.getY()) {
                p.append('^');
                curr = c;
            }

            //right of current
            if (c.getX() == curr.getX() && c.getY() - 1 == curr.getY()) {
                p.append('>');
            }

            //down of current
            if (c.getX() - 1 == curr.getX() && c.getY() == curr.getY()) {
                p.append('v');
            }

            //left of current
            if (c.getX() == curr.getX() && c.getY() + 1 == curr.getY()) {
                p.append('<');
            }

            curr = c;
        }



        return p.toString();
    }


    public static ArrayList<ArrayList<Coord>> findPaths(char[][] numPad, char d, Coord curr) {
        ArrayList<ArrayList<Coord>> paths = new ArrayList<ArrayList<Coord>>();

        ArrayList<Coord> path = new ArrayList<Coord>();

        ArrayList<Coord> visited = new ArrayList<Coord>();

        Coord current = curr;

        char dest = d;

        shortest(visited, path, numPad, current, paths, dest);



        return paths;
    }

    public static void shortest(ArrayList<Coord> visited, ArrayList<Coord> path, char[][] numPad, Coord current, ArrayList<ArrayList<Coord>> paths, char dest) {
        if (numPad[current.getX()][current.getY()] == dest) {
            ArrayList<Coord> n = new ArrayList<Coord>();
            n.addAll(path);
            paths.add(n);
        }

        ArrayList<Coord> adj = adj(numPad, current);

        for (Coord c : adj) {
            if (!visited.contains(c)) {
                visited.add(c);
                path.add(c);
                shortest(visited, path, numPad, c, paths, dest);
                path.remove(c);
                visited.remove(c);
            }
        }



    }


    public static ArrayList<Coord> adj(char[][] numpad, Coord current) {
        ArrayList<Coord> adj = new ArrayList<Coord>();

        Coord up = new Coord(current.getX() - 1, current.getY());

        Coord right = new Coord(current.getX(), current.getY() + 1);

        Coord down = new Coord(current.getX() + 1, current.getY());

        Coord left = new Coord(current.getX(), current.getY()  - 1);

        if (valid(up, numpad)) {
            adj.add(up);
        }

        if (valid(right, numpad)) {
            adj.add(right);
        }

        if (valid(down, numpad)) {
            adj.add(down);
        }

        if (valid(left, numpad)) {
            adj.add(left);
        }


        return adj;
    }

    public static boolean valid(Coord current, char[][] numPad) {
        return current.getX() >= 0 && current.getY() >= 0 && current.getX() < numPad.length && current.getY() < numPad[0].length && numPad[current.getX()][current.getY()] != ' ';
    }




    public static char[][] numPad() {
        char[][] numPad = new char[4][3];

        numPad[0][0] = '7';
        numPad[0][1] = '8';
        numPad[0][2] = '9';
        numPad[1][0] = '4';
        numPad[1][1] = '5';
        numPad[1][2] = '6';
        numPad[2][0] = '1';
        numPad[2][1] = '2';
        numPad[2][2] = '3';
        numPad[3][0] = ' ';
        numPad[3][1] = '0';
        numPad[3][2] = 'A';


        return numPad;
    }


    public static class Coord {

        private int x;

        private int y;

        public Coord (int x, int y) {
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
        public int hashCode() {
            return 31*x + y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }


    public static void main(String[] args) {


//        System.out.println(smallest1("980A"));
//        //System.out.println(subsequences("<A^A^^>AvvvA"));
//        System.out.println(createMap("^^^A<AvvvA>A"));
//        HashMap<String, Integer> map = new HashMap<String, Integer>();
//        System.out.println(createMap("<AAA>Av<<A>>^A<vAAA^>AvA^A"));
//
//        //optimalSubseq("<A^A^^>AvvvA", map);
//        //System.out.println(map);

        String input = "340A\n" +
                "586A\n" +
                "839A\n" +
                "413A\n" +
                "968A";

        String input1 = "029A\n" +
                "980A\n" +
                "179A\n" +
                "456A\n" +
                "379A";


        System.out.println(complexity("029A", 2));
        System.out.println(calcComplexity(input));

    }
}
