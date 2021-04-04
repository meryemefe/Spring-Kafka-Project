import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final String FOLDER_PATH = "./EncryptedTask";

    public static File[] getFileNames( String path){
        File directory = new File(path);
        return directory.listFiles();
    }

    public static void readFileContent(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        StringBuilder content = new StringBuilder();

        while (scan.hasNextLine()){
            content.append(scan.nextLine());
        }

        String[] encryptedWordList = content.toString().split(" ");
        for (String s : encryptedWordList) {
            decodeEncryptedWord(s);
        }
    }

    public static void decodeEncryptedWord( String encryptedWord){
        int charCodes = Integer.parseInt(encryptedWord, 2);
        String word = Character.toString((char) charCodes);
        System.out.print(word);
    }

    public static void main(String[] args) {

        // Get the names of all the files
        File[] fileList = getFileNames(FOLDER_PATH);
        List<String> fileNames = new ArrayList<>(fileList.length);
        for (File file: fileList) {
            fileNames.add(file.getName());
        }

        // Apply filter to the file names in order to get the ones containing "=" and sort them among themselves
        List<String> openingMessages = fileNames.stream().filter(i -> i.contains("=")).sorted((o1, o2) -> {
            boolean b1 = o1.contains("==");
            boolean b2 = o2.contains("==");
            if (b2 && !b1)
                return 1;
            if (b1 && !b2)
                return -1;
            return o1.compareTo(o2);
        }).collect(Collectors.toList());

        // Read the sorted messages' contents
        for (String name: openingMessages) {
            try {
                readFileContent(new File(FOLDER_PATH + "/" + name));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Apply filter to the file names in order to get the ones not containing any "=" and sort them among themselves
        List<String> remainingMessages = fileNames.stream().filter(i -> !i.contains("=")).sorted((o1, o2) -> {
            if (o1.substring(0, 3).equals(o2.substring(0, 3))) {
                if ((o1.charAt(3) <= 57) && (o2.charAt(3) > 57))
                    return 1;
                if ((o2.charAt(3) <= 57) && (o1.charAt(3) > 57))
                    return -1;
            }
            return o1.compareTo(o2);
        }).collect(Collectors.toList());

        // Read the sorted messages' contents
        for (String name: remainingMessages) {
            try {
                readFileContent(new File(FOLDER_PATH + "/" + name));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
