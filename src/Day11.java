import java.util.*;

public class Day11 {

    public static long stoneFinal(String input, int number) {
        HashMap<Long, Long> map = parseInput1(input);

        for (int i = 1; i <= number; i++) {
            fastblink(map);
        }

        long sum = 0;

        for (Long stone : map.keySet()) {
            long number1 = map.get(stone);

            sum = sum + number1;
        }

        return sum;
    }

    public static void fastblink(HashMap<Long, Long> map) {

        HashMap<Long, Long> newMap = new HashMap<Long, Long>();

        for (Long stone : map.keySet()) {
            if (stone == 0) {
                if (!newMap.containsKey(1L)) {
                    long number = map.get(stone);

                    newMap.put(1L, number);

                } else {
                    long number = map.get(stone);

                    newMap.put(1L, newMap.get(1L) + number);
                }
            } else if (String.valueOf(stone).length() % 2 == 0) {

                long occurrences = map.get(stone);

                String number = String.valueOf(stone);
                //First half

                String half1 = number.substring(0, number.length()/2);
                String half2 = number.substring(number.length()/2);

                long num1 = Long.parseLong(half1);
                long num2 = Long.parseLong(half2);

                if (!newMap.containsKey(num1)) {
                    newMap.put(num1, occurrences);
                } else {
                    newMap.put(num1, newMap.get(num1) + occurrences);
                }

                if (!newMap.containsKey(num2)) {
                    newMap.put(num2, occurrences);
                } else {
                    newMap.put(num2, newMap.get(num2) + occurrences);
                }
            } else {
                long occurrences = map.get(stone);

                long num = stone*2024;


                if (!newMap.containsKey(num)) {
                    newMap.put(num, occurrences);
                } else {
                    newMap.put(num, newMap.get(num) + occurrences);
                }

            }
        }

        map.clear();

        map.putAll(newMap);
    }


    public static HashMap<Long, Long> parseInput1(String input) {
        String[] s = input.split(" ");

        //The long refers to the stone and the integer refers to how many times that stone occurs
        HashMap<Long, Long> map = new HashMap<Long, Long>();

        for (String s1 : s) {
            long l = Integer.parseInt(s1);

            map.put(l, 1L);

        }

        return map;

    }
    public static int part2(ArrayList<Long> stones, int number) {

        int sum = 0;


        for (long stone : stones) {
            ArrayList<Long> l = new ArrayList<Long>();



            l.add(stone);

            for (int i = 1; i <= number; i++) {
                blink1(l);
            }

            sum = sum + l.size();

        }

        return sum;
    }



    public static ArrayList<Long> doblink(ArrayList<Long> stones, int current, int number) {
        if (current == number) {
            return stones;
        } else {
            return doblink(blink(stones), current + 1, number);

        }

    }

    public static ArrayList<Long> blink(ArrayList<Long> stones) {
        ArrayList<Long> newStones = new ArrayList<Long>();

        for (int i = 0; i < stones.size(); i++) {
            Long stone = stones.get(i);

            if (stone == 0) {
                newStones.add(1L);
            } else if (String.valueOf(stone).length() % 2 == 0) {

                String number = String.valueOf(stone);
                //First half

                String half1 = number.substring(0, number.length()/2);
                String half2 = number.substring(number.length()/2);

                long num1 = Long.parseLong(half1);
                long num2 = Long.parseLong(half2);

                newStones.add(num1);
                newStones.add(num2);
            } else {
                newStones.add(2024*stone);
            }
        }

        return newStones;
    }

    public static void blink1(ArrayList<Long> stones) {

        for (int i = 0; i < stones.size(); i++) {

            Long stone = stones.get(i);

            if (stone == 0) {
                stones.set(i, 1L);
            } else if (String.valueOf(stone).length() % 2 == 0) {
                String number = String.valueOf(stone);
                //First half

                String half1 = number.substring(0, number.length()/2);
                String half2 = number.substring(number.length()/2);

                long num1 = Long.parseLong(half1);
                long num2 = Long.parseLong(half2);

                stones.set(i, num1);
                stones.add(i+1, num2);

                i = i + 1;
            } else {
                stones.set(i, 2024*stone);
            }
        }
    }



    public static ArrayList<Long> parseInput(String input) {
        String[] s = input.split(" ");

        ArrayList<Long> numbers = new ArrayList<Long>();

        for (String s1 : s ) {
            long num = Integer.parseInt(s1);
            numbers.add(num);
        }

        return numbers;
    }

    public static void main(String[] args) {
        String input = "0 44 175060 3442 593 54398 9 8101095";

        String input1 = "125 17";

        ArrayList<Long> x = parseInput(input);

        ArrayList<Long> x1 = new ArrayList<Long>();
        x1.add(0L);

        //System.out.println(part2(x, 25));
        System.out.println(stoneFinal(input, 75));



    }
}
