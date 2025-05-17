package java_course;

// print multiplication table
public class Printing {
    public static void main(String[] args) {
        int a = 5;
        for(int i = 0; i < 10; i++ ) {
            int b = a*(i+1);
            System.out.printf("%d * %d = %d ", a, (i+1), b).println();
        }
    }
}
