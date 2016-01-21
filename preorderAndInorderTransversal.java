/*Given preorder and inorder traversal of a tree, construct the binary tree.*/
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0)return null;
        else if(preorder.length==1)return new TreeNode(preorder[0]);
        TreeNode root = new TreeNode(preorder[0]);
        int i=lS(inorder,preorder[0],0,preorder.length-1);
        int e = i-0;
        root.left = cT(inorder,preorder, 0, i-1,1);
        root.right = cT(inorder,preorder,i+1,preorder.length-1,1+i);
        return root;
        
    }
    int lS(int[] a, int t, int start, int end){
        for(int i=start;i<=end;i++){
            if(a[i]==t)return i;
        }
        return -1;
    }
    TreeNode cT(int[] i, int[] p, int start, int end, int id){
        if(id<0||id>p.length-1||start>end) return null;
        else if(start==end)return new TreeNode(p[id]);
        TreeNode n = new TreeNode(p[id]);
        int m=lS(i,p[id],start,end);
        int e = m-start;
        n.left = cT(i,p, start, m-1,id+1);
        n.right = cT(i,p,m+1,end,id+1+e);
        return n;
        
    }
}