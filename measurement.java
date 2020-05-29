import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class measurement {

    private static int N, G;
    private static ArrayList<BESSIE> list;
    private static int[] milkTracker;

    public static boolean EQUALS(ArrayList<Integer> b4, ArrayList<Integer> after) {
        if (b4.size() != after.size())
            return false;
        for (int i = 0; i < b4.size(); i++) {
            if (!b4.get(i).equals(after.get(i)))
                return false;
        }
        return true;
    }

    public static ArrayList<Integer> getIDsOfCurrWinner() {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < milkTracker.length; i++) {
            max = Math.max(max, milkTracker[i]);
        }

        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 1; i < milkTracker.length; i++) {
            if (milkTracker[i] == max)
                temp.add(i);    // WORKING HERE!!
        }
        return temp;
    }

    public static void incrementIndex(int ID, int amt) {
        milkTracker[ID] += amt;
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getID() == ID) {
//                list.get(i).setMilk(list.get(i).getMilk() + amt);
//            }
//        }
    }

    public static int compute() {
        int numChanges = 0;
        ArrayList<Integer> prevWinners = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            incrementIndex(list.get(i).getID(), list.get(i).getAmt());

            ArrayList<Integer> currWinners = getIDsOfCurrWinner();
//            if (EQUALS(prevWinners, currWinners)) {
//                System.out.println(prevWinners + " " + currWinners);
//            }
            if (!EQUALS(prevWinners, currWinners))
//            {
                numChanges += 1;
//                System.out.println(currWinners);
//            }

            prevWinners = new ArrayList<>();
            for (int a = 0; a < currWinners.size(); a++) {
                prevWinners.add(currWinners.get(a));
            }
//            currWinners = new ArrayList<>();
        }
        return numChanges;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("measurement.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        PrintWriter out = new PrintWriter(new FileWriter("measurement.out"));

        N = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        int maxID = 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int day = Integer.parseInt(st.nextToken());
            int ID = Integer.parseInt(st.nextToken());
            int amt = Integer.parseInt(st.nextToken());
            list.add(new BESSIE(day, ID, amt, G));

            maxID = Math.max(maxID, ID);
        }
        Collections.sort(list);
        milkTracker = new int[maxID + 1];
        Arrays.fill(milkTracker, 10);

        System.out.println(compute());

//        System.out.println(list);
        out.close();
    }
}

class BESSIE implements Comparable<BESSIE> {
    int day, ID, amt, milk;

    BESSIE(int d, int i, int a, int m) {
        day = d;
        ID = i;
        amt = a;
        milk = m;
    }

    public int getDay() {
        return day;
    }

    public int getID() {
        return ID;
    }

    public int getAmt() {
        return amt;
    }

    public void setMilk(int m) {
        milk = m;
    }

    public int getMilk() {
        return milk;
    }

    @Override
    public int compareTo(BESSIE o) {
        if (this.getDay() > o.getDay())
            return 1;
        else if (this.getDay() == o.getDay())
            return 0;
        return -1;
    }

    public String toString() {
        return "[" + getDay() + " " + getID() + " " + getAmt() + "]";
    }
}
