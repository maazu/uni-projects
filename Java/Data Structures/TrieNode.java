public class TrieNode {
    public char value;
    boolean word = false; // checks the next word
    TrieNode[] next = new TrieNode[256]; //holds the pointers to the child nodes.
    private int CheckSize = 0;

    public TrieNode(char c) {
        value = c;
    }

    public void setChild(char c, TrieNode node){
        next[c]=node;
        CheckSize++;
    }
    public void clearNext() {
        next = new TrieNode[256];
        CheckSize = 0;
    }

    public  boolean CheckNext(){
        return CheckSize == 0;
    }

    public  int CheckSize(){
        return CheckSize;
    }
}