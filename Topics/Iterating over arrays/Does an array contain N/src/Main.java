import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        int number = scanner.nextInt();
        boolean flaga = false;
        for (int i = 0; i < size; i++) {
            if (array[i] == number) {
                flaga = true;
            }
        }

        System.out.println(flaga);
    }
}
