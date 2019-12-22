import java.util.HashMap;

public class Worker extends Thread
{
    private boolean isDone = false;
    private String input;
    private boolean found = false;
    private int init;
    private String fullInput;
    private Node root;
    private HashMap<Long,String> hashDict;

    public Worker (String toUnhash, int start_offset, String file, Node n, HashMap<Long,String> hashDict)
    {
        super();
        this.input = toUnhash;
        this.init = start_offset;
        this.fullInput = file;
        this.root = n;
        this.hashDict = hashDict;
    }

    public synchronized void doStop() {
        this.isDone = true;
    }

    public synchronized boolean keepRunning() {
        return this.isDone == false;
    }

    public boolean isDone(){
        return isDone;
    }

    private void setFound(){
        this.found = true;
    }

    public boolean found(){
        return this.found;
    }

    public void run()
    {
        int[] offsets = UnHash.unhashCompoundSimple(this.input, hashDict);
        if (offsets[0] != -1)
        {
            setFound();
            doStop();
        }

        if (offsets.length == 1)
        {
            this.root.children.add(new Node(offsets[0]));
        }
        else
        {
            for (int i=0; i<4; i++)
            {
                offsets[i] = offsets[i]*32 + this.init;
            }

            for (int offset : offsets)
            {
                Node newChild = new Node(offset);
                this.root.children.add(newChild);
                Pirate.findTreasure(this.fullInput, offset, newChild, hashDict);
            }
        }
    }
}