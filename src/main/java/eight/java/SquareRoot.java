package eight.java;

import net.sf.jmimemagic.*;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeTypes;

import java.io.*;

/**
 * Created by kmishra on 4/16/2016.
 */
public class SquareRoot {

    public static int binarySqrRoot(int n) {
        return binarySqrRoot(n, 1, n);
    }

    private static int binarySqrRoot(int nSq, int low, int high) {
        int n = (low + high) / 2;
        int temp = n * n;
        if (temp == nSq) return n;
        else if (low - high <= 1) throw new RuntimeException("no integer square root");
        else if (temp > nSq) return binarySqrRoot(nSq, low, n);
        else return binarySqrRoot(nSq, n, high);
    }

    public static int numberTheorySqrRoot(int n) {
        int sum = 0;
        int count = 0;
        for (int i = 1; true; i = i + 2) {
            sum += i;
            count++;
            if (sum == n) return count;
            if (count == n) break;
        }
        throw new RuntimeException("no integer square root");
    }

    public static void main(String[] args) throws IOException, MagicParseException, MagicException, MagicMatchNotFoundException {
//        System.err.println(binarySqrRoot(130));
//        System.err.println(numberTheorySqrRoot(130));
        InputStream inputStream = new FileInputStream("C:\\Users\\kmishra\\Documents\\jigsaw\\cf_lookup.txt");
        System.err.println(detect(inputStream));
        inputStream = new FileInputStream("C:\\Users\\kmishra\\Documents\\jigsaw\\392_IPad_person_ID.csv");
        System.err.println(detect(inputStream));
    }

    private static String detect(InputStream inputStream) throws IOException {
        TikaInputStream tikaIS = null;
        try {
            tikaIS = TikaInputStream.get(inputStream);

        /*
         * You might not want to provide the file's name. If you provide an Excel
         * document with a .xls extension, it will get it correct right away; but
         * if you provide an Excel document with .doc extension, it will guess it
         * to be a Word document
         */
            final Metadata metadata = new Metadata();

            return new DefaultDetector(MimeTypes.getDefaultMimeTypes()).detect(tikaIS, metadata).toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (tikaIS != null) {
                tikaIS.close();
            }
        }
        return null;
    }
}
