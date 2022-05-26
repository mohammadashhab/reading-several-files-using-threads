import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        if( 0 < args.length ){
            wordsCounter counter = new wordsCounter();
            counter.load(args);
            counter.displayStatus();
        }
        else{
            System.err.println("Invalid arguments count:" + args.length);
            System.exit(1);
        }

        }



}

