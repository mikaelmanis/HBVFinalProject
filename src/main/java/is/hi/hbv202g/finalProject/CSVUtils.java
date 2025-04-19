package is.hi.hbv202g.finalProject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading and writing CSV files.
 */
public class CSVUtils {

    /**
     * Reads a CSV file and returns its content as a list of string arrays.
     *
     * @param filePath the path to the CSV file
     * @return a list of string arrays, where each array represents a row in the CSV file
     * @throws IOException if an I/O error occurs
     */
    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        }
        return data;
    }

    /**
     * Writes a list of string arrays to a CSV file.
     *
     * @param filePath the path to the CSV file
     * @param data     the data to write, where each array represents a row in the CSV file
     * @throws IOException if an I/O error occurs
     */
    public static void writeCSV(String filePath, List<String[]> data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }
}