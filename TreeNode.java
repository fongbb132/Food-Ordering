package com.company;

/**
 * Ka Wing Fong
 * 109794011
 * HW 5
 * CSE 214-R03
 * Recitation TA: Sun Lin
 * Grading TA: Ke Ma
 * @author Ka Wing Fong
 */

/**
 * The TreeNode object stores the name, selection and message
 */
public class TreeNode {
    private String name, selection, message;

    private TreeNode left, middle, right;

    /**
     * The constructor for TreeNode
     * @param n is the name of the node.
     * @param s is the selection type of this node.
     * @param m is the message will be display if this node is called.
     */
    public TreeNode(String n, String s, String m){
        name = n;
        selection = s;
        message = m;
    }

    /**
     * The method to search if a TreeNode exists
     * @param n is the name of the node that need to be searched.
     * @return the TreeNode is existed, or return null otherwise.
     */
    public TreeNode search(String n){
        TreeNode result= null;
        if(name.equals(n)){
            return this;
        }else{
            if(left!=null){
                result = left.search(n);
                if(result!=null){
                    return result;
                }
            }
            if(right!=null){
                result = right.search(n);
                if(result!=null){
                    return result;
                }
            }
            if(middle!=null){
                result = middle.search(n);
                if(result!=null){
                    return result;
                }
            }
            return result;
        }
    }

    /**
     *
     * @return the selection of current node as a String.
     */
    public String getSelection() {
        return selection;
    }

    /**
     *
     * @return the message stores in current node as a String.
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return the left TreeNode.
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     *
     * @param left is the Left TreeNode that need to be set.
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     *
     * @return the middle TreeNode of the current TreeNode.
     */
    public TreeNode getMiddle() {
        return middle;
    }

    /**
     *
     * @param middle is the middle TreeNode that need to be set.
     */
    public void setMiddle(TreeNode middle) {
        this.middle = middle;
    }

    /**
     *
     * @return the right TreeNode of the current TreeNode.
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     *
     * @param right is the right TreeNode that need to be set.
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     *
     * @return true if the current TreeNode is the leaf of the whole tree, false otherwise.
     */
    public boolean isLeaf(){
        boolean result = false;
        if(left == null && middle == null & right == null){
            result = true;
        }
        return result;
    }
}
