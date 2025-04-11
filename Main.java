import java.util.*;

class Trie {
    boolean ended;
    int wc;
    Trie[] ch;

    Trie() {
        ch = new Trie[26];
        ended = false;
        wc = 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Trie root = new Trie();
       while (true) {
            System.out.println("1. Insert a word");
            System.out.println("2. Search a word");
            System.out.println("3. Get all words");
            System.out.println("4. Get all words with a prefix");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 
    
            switch (choice) {
                case 1:
                    System.out.print("Enter word: ");
                    String word = sc.nextLine().toLowerCase();
                    insert(root, word);
                    break;
    
                case 2:
                    System.out.print("Enter word:");
                    String wo = sc.nextLine().toLowerCase();
                    if (search(root, wo)) {
                        System.out.println("exist");
                    } else {
                        System.out.println("not exist");
                    }
                    break;
    
                case 3:
                    List<String> l = getallwords(root);
                    if (l.isEmpty()) System.out.println("No words");
                    else for (String w : l) System.out.println(w);
                    break;
    
                case 4:
                    String pre = sc.nextLine().toLowerCase();
                    List<String> wp = getwordswithprefix(root, pre);
                    if (wp.isEmpty()) System.out.println("No words found");
                    else for (String w : wp) System.out.println(w);
                    break;
    
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }
    static void insert(Trie root, String s) {
        Trie te = root;
        for (char ci : s.toCharArray()) {
            int ind = ci - 'a';
            if (te.ch[ind] == null) {
                te.ch[ind] = new Trie();
            }
            te = te.ch[ind];
            te.wc++;
        }
        te.ended = true;
    }
    static boolean search(Trie root, String s) {
        Trie te = root;
        for (char ci : s.toCharArray()) {
            int ind = ci - 'a';
            if (te.ch[ind] == null) {
                return false;
            }
            te = te.ch[ind];
        }
        return te.ended;
    }
    static void help(Trie root, List<String> ans, String te) {
        if (root.ended) {
            ans.add(te);
        }
        for (int i = 0; i < 26; i++) {
            if (root.ch[i] != null) {
                char ch = (char) (i + 'a');
                help(root.ch[i], ans, te + ch);
            }
        }
    }
    static List<String> getallwords(Trie root) {
        List<String> res = new ArrayList<>();
        help(root, res, "");
        return res;
    }
    static List<String> getwordswithprefix(Trie root, String prefix) {
        Trie te = root;
        for (char ci : prefix.toCharArray()) {
            int ind = ci - 'a';
            if (te.ch[ind] == null) return new ArrayList<>();
            te = te.ch[ind];
        }
        List<String> res = new ArrayList<>();
        help(te, res, prefix);
        return res;
    }
}

