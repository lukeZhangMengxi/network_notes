package lib;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TestUtils {

    public static int[] readIntArrayFromSingleLineCommaSeparatedFile(String absPath) throws FileNotFoundException {
        File myObj = new File(absPath);
        Scanner myReader = new Scanner(myObj);
        String[] buf = myReader.nextLine().split(",");
        myReader.close();
        int[] out = new int[buf.length];
        for (int i=0; i<buf.length; i++) {
            out[i] = Integer.parseInt(buf[i]);
        }
        return out;
    }

    public static void assertStringListEquals(List<String> a, List<String> b) {
        assertEquals(a.size(), b.size());
        
        Collections.sort(a);
        Collections.sort(b);
        for (int i=0; i<a.size(); i++) {
            assertEquals(a.get(i), b.get(i));
        }
    }

    public static <T> void assertListEquals(List<T> a, List<T> b) {
        assertEquals(a.size(), b.size());

        for (int i=0; i<a.size(); i++) {
            assertEquals(a.get(i), b.get(i));
        }
    }
    
}
