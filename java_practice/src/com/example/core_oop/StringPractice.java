package src.com.example.core_oop;

public class StringPractice {
    public static void main(String[] args) {
        
        // a += "d";
        // System.out.println(a);

        // StringBuffer sb = new StringBuffer();
        // sb.append('a');
        // sb.append('b');
        // System.out.println(sb);

        // StringBuilder sb2 = new StringBuilder();
        // sb2.append('a');
        // sb2.append('b');
        // System.out.println(sb2);



        String a = "abc"; //  String Constant Pool 
        String b = "abc";
        String c = new String("abc");
        String d = new String("abc").intern();
        System.out.println("double equals comparison : " + (a == b) + " : " + a.equals(b));
        System.out.println("double equals comparison : " + (a == c) + " : " + a.equals(c));
        System.out.println("double equals comparison : " + (a == d) + " : " + a.equals(d));
        System.out.println("checking...");

        
    }
}
