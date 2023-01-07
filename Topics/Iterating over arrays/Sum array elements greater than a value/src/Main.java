import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++){
            array[i] = scanner.nextInt();
        }
        int n = scanner.nextInt();

        for (int i = 0; i < size; i++){
            if ( array[i] > n) {
                sum += array[i];
            }
        }
        System.out.println(sum);

    }
}