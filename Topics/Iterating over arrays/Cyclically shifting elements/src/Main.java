import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int size = scanner.nextInt();
        int[] array3 = new int[size];
        for (int i = 0; i < size; i++) {
            array3[i] = scanner.nextInt();
        }



        int temp = array3[array3.length-1] ;
        for(int i =size-2; i >= 0; i--) {
            array3[i+1] = array3[i];


        }
        array3[0] = temp ;
        for(int i = 0; i < size; i++) {
            System.out.print(array3[i] + " ");
        }


    }
}