import java.util.ArrayList;
import java.lang.Math;

public class Tree
{
    Node root;
    ArrayList<Integer> leaves = new ArrayList<Integer>();

    public Tree(int val)
    {
        root = new Node(val);
    }

    int height(Node n)
    {
        if (n == null)
            return 0;

        int max = 0;
        for (Node child : n.children)
        {
            int height = height(child);
            if (height > max)
                max = height;
        }

        return max+1;
    }

    public void getLeaves()
    {
        for (int i=1; i<= height(root); i++)
        {
            getLeavesAtLevel(root, i);
        }
    }

    private void getLeavesAtLevel(Node n, int level)
    {
        if (n == null)
            return;
        
        if (level == 1)
        {
            if (n.isLeaf())
            {
                leaves.add(n.data);
            }
        }
        else
        {
            for (Node child : n.children)
            {
                getLeavesAtLevel(child, level-1);
            }
        }
    }
}

class Node
{
    int data;
    ArrayList<Node> children = new ArrayList<Node>();

    public Node(int data)
    {
        this.data = data;
    }

    public boolean isLeaf()
    {
        return children.isEmpty();
    }
}