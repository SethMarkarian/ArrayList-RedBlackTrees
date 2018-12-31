import java.io.*;
import java.util.*;
/**
 * Class writes to csv file for graphing
 *
 */
public class WriteToCSV
{
    /**
     * Main method for WriteToCSV
     * 
     * @param args args[0] type of data structure
     */
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("Output" + args[0] + ".csv");
            Random random1 = new Random(12345);
            IndexSet<Long> set;
            int[] q = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000};
            double [] n = {0.25, 0.5, 0.75};
            for(int k = 0; k < 3; k++ ) {
                for(int w = 0; w < 10; w++) {
                    String ans = "";
                    String b = "";
                    long total = 0;
                    for(int j = 0; j < 5; j++) {
                        if(args[0].equals("Tree")) {
                            set = new TreeIndexSet<Long>();
                        }
                        else {
                            set = new ListIndexSet<Long>();
                        }

                        long start = System.currentTimeMillis();
                        for(int i = 0; i < q[w] * n[k]; i++) {
                            set.add(random1.nextLong());
                        }
                        for(int i = 0; i < (q[w] * (1 - n[k])); i++) {
                            double randNumber = Math.random();
                            double d = randNumber * (n[k] * q[w]);   
                            int randomInt = (int) d;
                            set.getKth(randomInt + 1);
                        }
                        total += System.currentTimeMillis() - start;
                    }
                    ans += total / 5;
                    b += q[w];
                    writer.append(b);
                    writer.append(",");
                    writer.append(ans);
                    writer.append("\n");
                }
                writer.append("\n");
                writer.append("\n");
            }
            writer.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}