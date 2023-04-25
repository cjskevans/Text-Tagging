import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String[]> keyList, evalList;
        String keyFile = args[0];
        String evalFile = args[1];
        keyList = readFile(keyFile);
        evalList = readFile(evalFile);
        compare(keyList, evalList);
    }

    static ArrayList<String[]> readFile(String filename) throws IOException {
        ArrayList<String[]> fullArr = new ArrayList<>();

        File file = new File("C:\\Users\\cjske\\IdeaProjects\\PA 5\\" + filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        while ((st = br.readLine()) != null)
            fullArr.add(st.split("/(?=[^/]*$)"));
        return fullArr;
    }

    static void compare( ArrayList<String[]> keyList,  ArrayList<String[]> evalList) {
        String[] key, eval;
        int correctCount = 0;
        double percent;
        for (int i = 0; i < keyList.size(); i++) {
            key = keyList.get(i);
            eval = evalList.get(i);
            System.out.println("key0:  " + key[0] + " eval0:  " + eval[0] + " key1: " + key[1] + " eval1: " + eval[1]);
            if (key[0].equals(eval[0]) && key[1].equals(eval[1])) {
                correctCount++;
                System.out.println(correctCount);
            }
        }
        percent = (double) correctCount / keyList.size();
        System.out.println("Percent correct: " + percent);
    }
}