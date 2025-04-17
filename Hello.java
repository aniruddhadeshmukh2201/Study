import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Hello {

    class Item {
        public String name;
        public Integer Id;
        public Item(String name, Integer Id) {
            this.name = name;
            this.Id = Id;
        }
    }

    public static void main(String[] args ) {
        System.out.println("Hello world");
        for(String arg : args) {
            System.out.println(arg);
        }

        for(int i = 0; i < args.length; i++) {
            System.out.println(i);
        }
        int[] arr = {1, 2, 3, 4, 5};
        for(int i = 0; i < arr.length; i++ ) {
            System.out.println(arr[i]);
        }
        String[] arr2 = {"a", "b", "c", "d", "e"};
        char[] arr3 = {'a', 'b', 'c', 'd', 'e'};
        double[] arr4 = {1.0, 2.0, 3.0, 4.0, 5.0};



        // making arrays dynamically
        int[] arr5 = new int[5];
        char[] arr6 = new char[5]; 
        double[] arr7 = new double[5];


        //using class based datatypes....
        Integer[] a1 = new Integer[4];


        // using collections 
        List<Integer> ar8 = new ArrayList<>();
        List<Character> ar9 = new ArrayList<>();
        List<Double> ar10 = new ArrayList<>();
        List<String> ar11 = new ArrayList<>();
        List<Item> ar12 = new ArrayList<>();

        Map<Integer, Integer> mp = new HashMap<>();
        Map<String, String> mp2 = new HashMap<>();
        Map<String, Item> mp3 = new HashMap<>();
        Map<Integer, List<Item>> mp4 = new HashMap<>();
        Map<Integer, String> mp5 = new TreeMap<>();
        Map<String, List<Item>> mp6 = new LinkedHashMap<>();
        Map<String, String> mp7 = new WeakHashMap<>();
        Map<String, String> mp8 = new Hashtable<>();
        Map<String, String> mp9 = new ConcurrentHashMap<>();


        Set<String> st = new HashSet<>();
        Set<String> st2 = new LinkedHashSet<>();
        Set<String> st3 = new TreeSet<>();



    }
}