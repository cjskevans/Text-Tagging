import java.util.ArrayList;

public class Prob {
    String word;
    int index, count;
    ArrayList<Integer> typeCount = new ArrayList<>();
    ArrayList<String> typeList = new ArrayList<>();

    public Prob(String word) {
        this.word = word;
    }
    public Prob(String word, String type) {
        this.word = word;
        addCount(type);
    }

    public void addCount(String type) {
        if (!typeList.contains(type)) {
            typeList.add(type);
            typeCount.add(1);
        }
        else {
            index = typeList.indexOf(type);
            count = typeCount.get(index);
            typeCount.add(typeList.indexOf(type), count++);
        }
    }

    public String getHighestCount() {
        int highest = 0;
        for (Integer integer : typeCount) {
            if (integer > highest)
                highest = integer;
        }
        return typeList.get(typeCount.indexOf(highest));
    }
}
