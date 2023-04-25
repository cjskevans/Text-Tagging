import java.io.*;
import java.util.ArrayList;

public class Main {

    static ArrayList<String> uniqueWord = new ArrayList<>();
    static ArrayList<Prob> probList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArrayList<String[]> parsedData;
        ArrayList<String> testWords;
        ArrayList<String[]> guessedTag;

        int mode = Integer.parseInt(args[0]);
        String file1 = args[1];
        String file2 = args[2];

        parsedData = readTrainFile(file1);
        if (mode == 0)
            compute(parsedData);
        else
            computeWithRules(parsedData);
        testWords = readTestFile(file2);
        guessedTag = assignTag(testWords);
        outputResults(guessedTag);
    }

    static ArrayList<String[]> readTrainFile(String filename) throws IOException {
        ArrayList<String[]> fullArr = new ArrayList<>();

        File file = new File("C:\\Users\\cjske\\IdeaProjects\\PA 5\\" + filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        while ((st = br.readLine()) != null) {
            fullArr.add(st.split("/"));
        }
        return fullArr;
    }

    static ArrayList<String> readTestFile(String filename) throws IOException {
        ArrayList<String> testWords = new ArrayList<>();

        File file = new File("C:\\Users\\cjske\\IdeaProjects\\PA 5\\" + filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            testWords.add(st);
        }
        return testWords;
    }

    static void compute(ArrayList<String[]> arr) {
        String[] line;
        int index;
        for (int i = 0; i < arr.size(); i++) {
            line = arr.get(i);
            if (uniqueWord.contains(line[0])) {
                index = uniqueWord.indexOf(line[0]);
                probList.get(index).addCount(line[1]);
            }
            else {
                uniqueWord.add(line[0]);
                probList.add(new Prob(line[0], line[1]));
            }
        }
    }

    static void computeWithRules(ArrayList<String[]> arr) {
        String[] line;
        int index;
        for (int i = 0; i < arr.size(); i++) {
            line = arr.get(i);
            if (uniqueWord.contains(line[0])) {
                index = uniqueWord.indexOf(line[0]);
                probList.get(index).addCount(line[1]);
            }
            else {
                uniqueWord.add(line[0]);
                probList.add(new Prob(line[0], line[1]));
            }
        }
    }

    static ArrayList<String[]> assignTag(ArrayList<String> testWords) {

        ArrayList<String[]> guessedTag = new ArrayList<>();
        String testWord;
        int index;
        for (int i = 0; i < testWords.size(); i++) {
            testWord = testWords.get(i);
            index = uniqueWord.indexOf(testWord);
            if (index == -1)
                guessedTag.add(new String[]{testWord, "NN"});
            else
                guessedTag.add(new String[]{testWord, probList.get(index).getHighestCount()});
        }
        return guessedTag;
    }

    static void outputResults(ArrayList<String[]> guessedTag) throws IOException {
        FileWriter myWriter = new FileWriter("pos-test-0-answers-0.txt");
        try {
            for(int i = 0; i < guessedTag.size(); i++) {
                String[] pair = guessedTag.get(i);
                myWriter.write(pair[0] + "/" + pair[1] + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

