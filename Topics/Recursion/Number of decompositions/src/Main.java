import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        int input = Integer.parseInt(new Scanner(System.in).nextLine());
        decomposition(input);
    }

    public static void decomposition(int input) {
        decompose(input, input, "");
    }

    private static void decompose(int value, int countUp, String output) {
        if (value == 0) {
            System.out.println(output.trim());
        } else {
            if (countUp > 1) {
                decompose(value, countUp - 1, output);
            }
            if (countUp <= value) {
                decompose(value - countUp, countUp, output + " " + countUp);
            }
        }
    }

}