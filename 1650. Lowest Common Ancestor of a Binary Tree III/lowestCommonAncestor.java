/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        if(p.parent==null) return p;
        if(q.parent==null) return q;
        Set<Node> path=new HashSet<>();
        
        Node a=p.parent, root=p;
        path.add(p);
        while(a!=null) {
            path.add(a);
            a=a.parent;
            root = root.parent;
        }
        
        Node b=q;
        while(b!=null) {
            if(path.contains(b)) return b;
            b=b.parent;
        }
        
        return root;
    }
    
}