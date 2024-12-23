
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Day17 {

    private int A = 200;

    private int B = 0;

    private int C = 0;

    private int pointer = 0;

    private ArrayList<Integer> output = new ArrayList<Integer>();


    //performs the instructions according to the opcode
    public void perfromIn(int[][] program) {
        if (pointer < program[0].length) {
            int opcode = program[0][pointer];
            int operand = program[1][pointer];

            if (opcode == 0) {
                int c = combo(operand);

                A = (int) (A/Math.pow(2, c));
                System.out.println("opcode =" + opcode + ", A =" + A +", B =" + B + ", C =" + C + ", output=" + output + ", pointer =" + pointer);
                pointer = pointer + 1;

                perfromIn(program);
            } else if (opcode == 1) {
                B = B^operand;
                System.out.println("opcode =" + opcode + ", A =" + A +", B =" + B + ", C =" + C + ", output=" + output + ", pointer =" + pointer);
                pointer = pointer + 1;
                perfromIn(program);
            } else if (opcode == 2) {
                int c = combo(operand);

                B = c % 8;
                System.out.println("opcode =" + opcode + ", A =" + A +", B =" + B + ", C =" + C + ", output=" + output + ", pointer =" + pointer);
                pointer = pointer + 1;
                perfromIn(program);
            } else if (opcode == 3) {
                if (A != 0) {
                    pointer = operand;

                    System.out.println("opcode =" + opcode + ", A =" + A +", B =" + B + ", C =" + C + ", output=" + output + ", pointer =" + pointer);
                    perfromIn(program);
                } else {
                    System.out.println("opcode =" + opcode + ", A =" + A +", B =" + B + ", C =" + C + ", output=" + output + ", pointer =" + pointer);
                    pointer = pointer + 1;
                    perfromIn(program);
                }
            } else if (opcode == 4) {
                B = B^C;
                System.out.println("opcode =" + opcode + ", A =" + A +", B =" + B + ", C =" + C + ", output=" + output + ", pointer =" + pointer);
                pointer = pointer + 1;

                perfromIn(program);
            } else if (opcode == 5) {
                int c = combo(operand);

                int out = c % 8;

                output.add(out);
                System.out.println("opcode =" + opcode + ", A =" + A +", B =" + B + ", C =" + C + ", output=" + output + ", pointer =" + pointer);
                pointer = pointer + 1;

                perfromIn(program);
            } else if (opcode == 6) {
                int c = combo(operand);

                B = (int) (A/Math.pow(2, c));
                System.out.println("opcode =" + opcode + ", A =" + A +", B =" + B + ", C =" + C + ", output=" + output + ", pointer =" + pointer);
                pointer = pointer + 1;

                perfromIn(program);
            } else if (opcode == 7) {
                int c = combo(operand);

                C = (int) (A/Math.pow(2, c));
                System.out.println("opcode =" + opcode + ", A =" + A +", B =" + B + ", C =" + C + ", output=" + output + ", pointer =" + pointer);
                pointer = pointer + 1;

                perfromIn(program);
            }
        }
    }



    public int combo(int operand) {
        if (operand >= 0 && operand <= 3) {
            return operand;
        } else if (operand == 4) {
            return A;
        } else if (operand == 5) {
            return B;
        } else  {
            return C;
        }
    }





    /**
     *
     * @param program
     * @return a hashmap with each instruction and its relative operand integer in order
     */
    public static int[][] parseProgram(String program) {
        String[] s = program.split(",");

        int[][] ops = new int[2][s.length/2];


        int k = 0;
        for (int i = 0; i < s.length; i = i + 2) {
            int num1 = Integer.parseInt(s[i]);
            int num2 = Integer.parseInt(s[i + 1]);

            ops[0][k] = num1;
            ops[1][k] = num2;

            k++;

        }

        return ops;
    }

    //Converts the program to a list
    public static int[] list(String program) {
        String[] s = program.split(",");

        int[] ins = new int[s.length];

        for (int i = 0; i < s.length; i++) {
            ins[i] = Integer.parseInt(s[i]);
        }

        return ins;
    }

    public static long step(long A) {
        long B = A % 8;
        B = B^5;
        long C = (long) (A / Math.pow(2, B));
        B = B ^ 6;
        B = B^C;
        return B%8;
    }

    public static void find(long A, int[] program, ArrayList<Long> values, int index) {
        if (step(A) == program[index]) {
            if (index == 0) {
                values.add(A);
            } else {
                for (int i = 0; i < 8; i++) {
                    find(A*8 + i, program, values, index - 1);
                }
            }

        }
    }









    public static void main(String[] args) {
        String program = "2,4,1,5,7,5,1,6,0,3,4,6,5,5,3,0";
        String program1 = "0,3,5,4,3,0";

        int[][] s = parseProgram(program);

        Day17 d = new Day17();

        //d.perfromIn(s);

        //ArrayList<Integer> i = d.output;
        //System.out.println(i);

        int[] p = list(program);

        ArrayList<Long> values = new ArrayList<Long>();

        for (long i = 0; i < 8; i++) {
            find(i, p, values, p.length - 1);
        }

        System.out.println(values.stream().min(Comparator.naturalOrder()));






    }
}
