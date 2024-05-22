package cn.code.leet.solution;

import java.util.LinkedList;

//208. 实现前缀树
public class Solution208 {
    // 字典的树的结构
    // 1. children
    // 2. finish来标识结束
    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("word");
        System.out.println(t.search(".or."));
    }
}


class Trie {
    Trie[] children;
    boolean f;

    public Trie() {
        this.children = new Trie[26];
    }

    public void insert(String word) {
        Trie t = this;
        for (char c : word.toCharArray()) {
            int pos = c - 'a';
            if (t.children[pos] == null) {
                t.children[pos] = new Trie();
            }
            t = t.children[pos];
        }
        t.f = true;
    }

    public boolean search(String word) {
        // 带.通配 就必须用bfs的形式了
        LinkedList<Trie> l = new LinkedList<>();
        l.add(this);
        for (char c : word.toCharArray()) {
            int size = l.size();
            for (int i = 0; i < size; i++) {
                Trie t = l.pop();
                if (c == '.') {
                    for (Trie tt : t.children) {
                        if (tt != null) {
                            l.add(tt);
                        }
                    }
                } else {
                    int pos = c - 'a';
                    if (t.children[pos] == null) {
                        return false;
                    }
                    t = t.children[pos];
                    l.add(t);
                }
            }

        }
        while (!l.isEmpty()) {
            if (l.pop().f) {
                return true;
            }
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        Trie t = this;
        for (char c : prefix.toCharArray()) {
            int pos = c - 'a';
            if (t.children[pos] == null) {
                return false;
            }
            t = t.children[pos];
        }
        return true;
    }
}