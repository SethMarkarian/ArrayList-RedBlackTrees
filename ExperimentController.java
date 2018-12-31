import java.io.*;
import java.util.*;
public class ExperimentController
{
    /**
     * Main method for running ExperimentController
     * 
     * @param args First args[0] is data structure type
     */
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            PrintStream out = new PrintStream(new FileOutputStream("output2.txt"));
            IndexSet<Long> set;
            if(args[0].equals("Tree")) {
                set = new TreeIndexSet<Long>();
            }
            else {
                set = new ListIndexSet<Long>();
            }

            int n = Integer.parseInt(sc.nextLine());
            String[] a = new String[n];
            for(int i = 0; i < n; i++) {
                a[i] = sc.nextLine();
            }

            long start = System.currentTimeMillis();
            for(int i = 0; i < n; i++) {
                String[] x = a[i].split(" ");
                if(x[0].equals("add")) {
                    out.println(set.add(Long.parseLong(x[1])));
                }
                else {
                    out.println(set.getKth(Integer.parseInt(x[1])));
                }
            }
            long stop = System.currentTimeMillis();
            out.println("Time: " + (stop - start));
            out.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}