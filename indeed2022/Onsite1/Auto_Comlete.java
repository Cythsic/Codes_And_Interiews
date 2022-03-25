/* =============================================================================
Question Description
Say I'm typing on a phone. Given a prefix String,and a dictionary.
Find all auto-complete word for the given prefix string

step 1: Trie data structre
boolean, array of TrieNode --- alphabet set

step 2: create the trie tree
insert each char of each word into the trie tree
and check if final char of word, yes --> assign "true" to boolean

step 3: find function
check each char in the prefix, see if we can find it in the next level in the trie tree 
until we reach the end of the prefix or we can't find the char in trie tree

step 4: completion function
trieNode ---> find all the words based on this node
only check the boolean --> true,  add the word into result list
and we use for loop and recursion to traverse all nodes in rest the tree
=============================================================================*/
class TrieNode {
  boolean isWord;
  TrieNode[] dic;

  //for follow up
  // int frequency;

  TrieNode() {
    isWord = false;
    dic = new TrieNode[26];

    //follow up
    frequency = 0;
  }

//For follow up
  // TrieNode(int val) {
  //   isWord = false;
  //   dic = new TrieNode[26];
  //   frequency = val;
  // }

  void insert(String word, int index) { //TC: O(L) --> L: length of string
    if(index == word.length()) {
      isWord = true;
      return;
    }
    char curr = word.charAt(index);
    if(dic[curr-'a'] == null) {
      dic[curr-'a'] = new TrieNode();
    }
    dic[curr-'a'].insert(word, index+1); //切记一定要是: dic[pos].insert()
  }

  TrieNode find(String prefix, int index) { //TC: O(L) --> L: length of string
    if(index == prefix.length()) return this;
    char curr = prefix.charAt(index);
    if(dic[curr-'a'] != null) {
      return dic[curr-'a'].find(prefix, index+1); //切记一定要是: dic[pos].find()
    }
    else return null;
  }
}

//Trie + DFS
class AutoComplete{
  TrieNode root;

  AutoComplete(List<String> list) {
    root = new TrieNode();
    for(String s : list) { //TC: O(N) --> N: list.size()
      root.insert(s, 0);
    }
  }

  List<String> complete(String prefix) {
    List<String> res = new ArrayList<>();
    TrieNode cur = this.root;
    TrieNode node = cur.find(prefix, 0);
    findCompletion(node, res, prefix);      
    return res;
  }

  void findCompletion(TrieNode prev, List<String> res, String prefix) {
    if(prev == null) return;
    if(prev.isWord == true) {
      res.add(prefix);
    }
    for(char alpha='a'; alpha<='z'; alpha++) { //TC: O(26)
      findCompletion(prev.dic[alpha - 'a'], res, prefix + alpha); 
    }
  }
}

class Solution {
  public static void main(String[] args) {
    List<String> words = new ArrayList<>();
        words.add("ab");
        words.add("a");
        words.add("de");
        words.add("abde");
    AutoComplete ac = new AutoComplete(words);
    String prefix = "ab";
    List<String> res = ac.complete(prefix);

    System.out.println(res);
  }
}

/* =============================================================================
Follow Up

no follow up found for indeed
Google遇上的是不同的词有不同的频率，优先输出高频词。
思路就是每个trie node加上一个数字，向下遍历的时候优先输出高频的child即可。
=============================================================================*/