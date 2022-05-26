import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class wordsCounter {

    private static final int OPTIMAL_THREADS_NUMBER = 8;
    private final MyHashMap wordsCount;

    /**
     * constructor where hashmap is initialized
     */
    public wordsCounter(){
        this.wordsCount = new MyHashMap();
    }



    /**
     * load the entire word in the files to the hashmap
     * @param fileNames : of the files containing words
     * @throws IOException
     */
    public void load(String[] fileNames) throws IOException {
        for(String fileName: fileNames){
            ExecutorService ex = Executors.newFixedThreadPool(OPTIMAL_THREADS_NUMBER);
            FileReader f = new FileReader(fileName);
            BufferedReader br = new BufferedReader(f);
            for(int i = 0 ; i < OPTIMAL_THREADS_NUMBER ; ++i){
                ex.submit(new Job(br, wordsCount));
            }
            ex.shutdown();
            try {
                if (!ex.awaitTermination(60, TimeUnit.SECONDS)) {
                    ex.shutdownNow();
                }
            } catch (InterruptedException e) {
                ex.shutdownNow();
                Thread.currentThread().interrupt();
            }
            br.close();
        }
    }

    /**
     * prints the result of the words with their count
     */
    public void displayStatus(){
        this.wordsCount.printValues();
    }
}
