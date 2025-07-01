import java.util.*;
import java.io.*;

public class StringInQueue {
    public static void main(String[] args) {
        File file = new File("/Users/arnavgoel/IdeaProjects/StringQueue/src/LongParagraph");
        Queue<String> wordList = new LinkedList<>();
        PriorityQueue<String> alphabeticalWordList = new PriorityQueue<>();
        PriorityQueue<String> descendingWordList = new PriorityQueue<>(new CustomIntegerComparator());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                String[] arr = text.split(" ");
                for(int i = 0; i < arr.length; i++){
                    wordList.add(arr[i]);
                    alphabeticalWordList.add(arr[i]);
                    descendingWordList.add(arr[i]);
                }


            }
            System.out.println(String.format("%-25s %-25s %-25s", "Queue", "PriorityQueue", "DescendingPriorityQueue"));
            while(!wordList.isEmpty()){
                System.out.println(String.format("%-25s %-25s %-25s", wordList.poll(), alphabeticalWordList.poll(), descendingWordList.poll()));
            }
        }
        catch(IOException e){

        }
    }

    static class CustomIntegerComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {

            return o2.compareTo(o1);
        }
    }
}