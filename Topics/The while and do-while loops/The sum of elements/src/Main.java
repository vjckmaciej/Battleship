import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int suma = 0;
        int number;
        do{
            number = scanner.nextInt();
            suma += number;
        } while(number != 0);
        System.out.println(suma);
    }
}