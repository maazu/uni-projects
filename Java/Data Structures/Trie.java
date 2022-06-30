import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode('\0'); //assigning empty root
    }//constructor


    public boolean addWord(String word) {
        word = word.toLowerCase();

        if(find(word)==true) {
            System.out.println(word+" is already available in the trie.");
            return false;
        }
        TrieNode lastChar = root, tmp;
        for (char c : word.toCharArray()) {
            tmp = lastChar .next[c];            //refering to nxt
            if (tmp == null) {
                tmp = new TrieNode(c);
                lastChar .setChild(c, tmp);
            }
            lastChar  = tmp;
        }

        if (lastChar .word) {
            return !lastChar.word;
        }
        lastChar.word = true;
        return  true;
    }

/*
 *  Begins with the root node
 *  if char is not a children of n return false
 *  set n to the node representing c.
 *  n shows the last char in the word,
 * */


    public boolean find(String word) {
        TrieNode n = root;
        for (char c : word.toCharArray()) {
            n = n.next[c];
            if (null == n) {
                return false;
            }
        }
        return n.word;
    }


    /*
     *initialize an empty list to hold the words to be added.
     *for each child node of the root
     *call the recursive function getWordList to assemble
     *return the list
    */

    public List<String>  getWords(char c) {
        List<String> wordlist = new ArrayList<String>();
        for (TrieNode n : root.next) {  //checking for next child
            if (null != n) {
                getWordList( wordlist, n.value + "", n);
            }
        }
        return  wordlist;
    }

    private void getWordList(List<String>  wordlist, String word, TrieNode n) {
        if (n.word) {
            wordlist.add(word);
        }
        for (TrieNode t : n.next) {
            if (null != t) {
                getWordList( wordlist, word + t.value, t);
            }
        }
    }

    /* Delete Method used the technique to using stack for making
     * sure that when the deletion occur the prefix stays in its place
     * pop the stack to remove the last char in word
    */
    public boolean delete(String word) {
         word= word.toLowerCase();
         Stack<TrieNode> stack = new Stack<TrieNode>();
        TrieNode Currentw = root;
        stack.add( Currentw );
        for (char c : word.toCharArray()) {
            Currentw  =  Currentw .next[c];
            if (null ==  Currentw )//word not found
            {
                System.out.println(word+" is not the trie.");
                return false;
            }
            stack.add( Currentw );
        }
        if (! Currentw .word)//word not found
        {
            return false;
        }
        Currentw .word = false;
        if (! Currentw .CheckNext())//word is a prefix
        {
            return true;
        }
        //word is not a prefix
        stack.pop();
        Currentw  = stack.pop();
        while (!stack.isEmpty() &&  Currentw . CheckSize() == 1) {
            Currentw .clearNext();
            if ( Currentw .word) {
                return true;
            }
            Currentw  = stack.pop();
        }
        System.out.print(word+" deleted has been delted from trie.  ");
        return true;
    }

}