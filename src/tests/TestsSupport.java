package tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class TestsSupport {

    private static boolean generateOfficialResults = false;

    public static boolean isCorrect(String filename, String results) {
        officialUseIgnore(filename, results);
        String officialResults = "";
        try {
            BufferedReader fin = new BufferedReader(new FileReader(filename));

            String line;
            while ((line = fin.readLine()) != null) {
                officialResults += line + "\n";
            }
            fin.close();
        } catch (IOException e) {
            System.out.println("File operation in isCorrect failed.");
            return false;
        }

        results = removeBlanks(results);
        officialResults = removeBlanks(officialResults);

        return results.equals(officialResults);
    }

    public static boolean sameContents(String firstFile, String secondFile) {
        return removeBlanks(fileData(firstFile)).equals(removeBlanks(fileData(secondFile)));
    }

    public static String fileData(String fileName) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Scanner fileScanner = new Scanner(bufferedReader);

            while (fileScanner.hasNextLine())
                stringBuffer.append(fileScanner.nextLine());

            fileScanner.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return stringBuffer.toString();
    }

    public static String removeBlanks(String src) {
        StringBuffer resultsBuf = new StringBuffer();

        char curr;
        for (int i = 0; i < src.length(); i++) {
            curr = src.charAt(i);
            if (curr != ' ' && curr != '\n')
                resultsBuf.append(curr);
        }
        return resultsBuf.toString();
    }

    public static boolean writeToFile(String filename, String message) {
        try {
            FileWriter output = new FileWriter(filename);
            output.write(message);
            output.close();

        } catch (IOException exception) {
            System.out.println("ERROR: Writing to file " + filename + " failed.");
            return false;
        }
        return true;
    }

    private static void officialUseIgnore(String filename, String results) {
        if (generateOfficialResults) {
            String warningMessage = "Warning: You will overwrite result files.";
            warningMessage += " Do you want to continue?";
            if (JOptionPane.showConfirmDialog(null, warningMessage) == JOptionPane.YES_OPTION) {
                TestsSupport.writeToFile(filename, results);
                System.out.println("File " + filename + " has been updated.");
            }
        }
    }
}
