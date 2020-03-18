import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class citystate {
    private static int N;
    private static HashMap<String, Integer> map;

    public static boolean checkIfEqual(String one, String two) {
        for (int i = 0; i < one.length(); i++)
            if (one.charAt(i) != two.charAt(i))
                return false;
        return true;
    }

    public static String getOther(String one) {
        return one.substring(2) + one.substring(0, 2);
    }

    public static boolean checkIfSpecialPair(String one, String two) {
        // MIFL, FLMI
        return (one.substring(0, 2).equals(two.substring(2)) && one.substring(2).equals(two.substring(0, 2)));
    }

    public static void main(String[] DEEPANSHA_IS_AWESOME) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("citystate.in"));
        PrintWriter out = new PrintWriter(new FileWriter("citystate.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            String city = st.nextToken().substring(0, 2);
            String state = st.nextToken();
            String insertWord = city + state;

//            System.out.println(insertWord + " " + getOther(insertWord));
            if (!city.substring(0, 2).equals(state)) {  // BECAUSE THEY HAVE TO BE IN DIFFERENT STATES ==> CITY AND STATE CANNOT BE EQUAL
                // IF CITY AND STATE ARE EQUAL ==> OTHER PAIR WILL DO OPPOSITE SO BOTH STATES OF EACH PAIR WILL BE =
                if (!map.containsKey(insertWord)) {
                    map.put(insertWord, 1);
                } else {
                    map.put(insertWord, map.get(insertWord) + 1);
                }
            }
        }

        int ct = 0;
        Iterator iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String curr = (String) iter.next();
            String other = getOther(curr);
            if (map.containsKey(other)) {
                ct += map.get(curr) * map.get(other);
            }
        }

//        System.out.println(map);
        out.println(ct / 2);   // OVERCOUNTED!!!
        in.close();
        out.close();
    }
}
