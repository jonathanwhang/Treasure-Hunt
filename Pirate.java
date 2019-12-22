import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import java.lang.StringBuilder;
import java.nio.file.Paths;
import java.util.HashMap;
import java.io.*;

public class Pirate
{
    private static BufferedReader br;
    public static Tree tree = new Tree(-1);
    public static HashMap<Long,String> hashDict = new HashMap<>();

    public static void findTreasure(String file, int start_offset, Node root, HashMap<Long,String> hashDict) {
        String toUnhash = file.substring(start_offset, start_offset+32);

        Worker w1 = new Worker(toUnhash, start_offset, file, root, hashDict);

        w1.start();

        while(!w1.found()) {
            try {
                Thread.sleep(130);
            }
            catch (InterruptedException in) {
                in.printStackTrace();
            }
        }
        if (!w1.isDone())
            w1.doStop();

    }

    public static void main(String[] args) 
    {
        int start_offset = Integer.parseInt(args[0]) ;
        File file = new File(args[1]);

        try 
        {
            br = new BufferedReader(new FileReader(file));
            String file1 = br.readLine();
            String clueFile = new String(Files.readAllBytes(Paths.get(args[2])));

            findTreasure(file1, start_offset, tree.root, hashDict);
            TimeUnit.SECONDS.sleep(64); 
            tree.getLeaves();

            StringBuilder ret = new StringBuilder();
            for(int leaf : tree.leaves) 
            {
                ret.append(clueFile.charAt(leaf));
            }
            
            System.out.println(ret);
        }

        catch (IOException | InterruptedException ex) 
        {
            ex.printStackTrace();
        }
    }
}