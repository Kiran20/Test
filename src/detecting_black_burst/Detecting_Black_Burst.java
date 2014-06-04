/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package detecting_black_burst;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WARLOCK
 */
public class Detecting_Black_Burst {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrintStream out = null;
        try {
            // TODO code application logic here
            int i = 0;
            int j = 0;
            int value =0;
            int inc=0;
            int check =0;
            out = new PrintStream(new FileOutputStream("e:\\output.txt"));
            System.setOut(out);
            int[] arr = new int[8];
            DataStoreBBSampleEx3 sample_value = new DataStoreBBSampleEx3();
            // System.out.println(sample_value.getSampleValueLength());
            for (i = 0; i < sample_value.getSampleValueLength(); i++) {
                value = 0;
                if (i < 8) {
                    arr[i] = sample_value.getSampleValue(i);
                    if (i == 7) {
                        value = calculate(arr);
                        if(value==1)
                        {
                            inc++;
                        }
                    }
                } else {
                    for (j = 0; j < 7; j++) {
                        arr[j] = arr[j + 1];
                    }
                    arr[7] = sample_value.getSampleValue(i);
                    value = calculate(arr);
                    if(value==1&&check==0){
                        inc++;
                        check=1;
                    }
                    else if (value ==0){
                        check =0;
                    }
                }
            }
            System.out.println("No of black Burst :" +inc);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Detecting_Black_Burst.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    private static int calculate(int[] arr) {
        int sum = 0;
        int value = 0;
        for (int i = 0; i < 8; i++) {
            sum += arr[i];
        }
        value = sum / 8;
        if (value < -92) {
            return (1);
            //System.out.print(" 0");
        } else {
            return (0);
            //System.out.print(" 1");
        }
    }
}
