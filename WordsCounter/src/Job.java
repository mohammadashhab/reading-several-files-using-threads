import java.io.BufferedReader;
import java.io.IOException;


/**
 * class where the threads will work on(going over the lines in a  file).
 */
public class Job implements Runnable{

    BufferedReader br;
    MyHashMap wordsCount;

    public Job(BufferedReader br, MyHashMap wordsCount){
        this.br = br;
        this.wordsCount = wordsCount;
    }

    /**
     * reading the lines in a file
     */
    @Override

    public void run() {

        String line;
        try {
            while((line = br.readLine()) != null){
                parseLine(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * add the words in the line to the hashmap
     * @param line: line to read words from in file
     */
    private void parseLine(String line){
        String[] wordsList = line.split(" ");
        for(String word : wordsList){
            this.wordsCount.add(word);
        }
    }

}
