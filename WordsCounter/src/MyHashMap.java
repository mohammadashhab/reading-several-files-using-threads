import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * class containing the hashmap that has the word count
 */
public class MyHashMap {

    private final ConcurrentHashMap<String, AtomicInteger> conn;
    private AtomicInteger total;



    /**
     * Concurrent Hashmap Constructor
     */
    public MyHashMap(){
        this.conn = new ConcurrentHashMap<>();
        this.total = new AtomicInteger(0);
    }

    //

    /**
     * adding a key and updating the value(number of times appeared), using atomic Integer to be thread safe
     * @param key:  word in file
     */
    public void add(String key){
        if(this.conn.putIfAbsent(key, new AtomicInteger(1)) != null){
            this.conn.get(key).addAndGet(1);
        }
        total.addAndGet(1);
    }



    /**
     * prints the keys and values of our hashmap
     */
    public void printValues(){
        for(String key : this.conn.keySet()){
            System.out.println(key + " " + this.conn.get(key) + " ");
        }

        System.out.println("** total: " + total);

    }




}
