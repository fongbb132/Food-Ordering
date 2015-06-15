package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
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
 * The Tree Object contains all the node information.
 */
public class Tree {
    private Scanner input = new Scanner(System.in);
    private TreeNode root;
    public Tree(TreeNode r){
        root = r;
    }

    /**
     *
     * @param name is the name of the Node that need to be searched.
     * @return the node if found, null otherwise.
     */
    public TreeNode findNode(String name){
        if(root == null){
            return null;
        }else {
            return root.search(name);
        }
    }

    /**
     *
     * @param name is the name of the new TreeNode.
     * @param selection is the selection of the new TreeNode.
     * @param message is the message of the new TreeNode.
     * @param parentName is the name of new TreeNode's parent.
     * @param lastDigit is the position of the new TreeNode that needs to be added.
     * @return true if the node is added, false otherwise.
     */
    public boolean addNode(String name, String selection, String message, String parentName,int lastDigit){
        TreeNode cursor = findNode(parentName);
        TreeNode newNode = new TreeNode(name, selection, message);
        if (cursor==null){
            return false;
        }else {
            if(lastDigit==1){
                cursor.setLeft(newNode);
            }else if(lastDigit==2){
                cursor.setMiddle(newNode);
            }else if(lastDigit==3){
                cursor.setRight(newNode);
            }
            return true;
        }
    }

    /**
     * All the information on the tree will be printed in a neat tableau format.
     * @param parentInfo is the parent information of a node.
     * @param treeNode is the current TreeNode.
     */
    public void printMenu(String parentInfo, TreeNode treeNode){
        TreeNode cursor = treeNode;
        if(!treeNode.isLeaf()) {
            if(cursor.getLeft()!= null){
                String a = parentInfo+ cursor.getLeft().getSelection()+"@";
                printMenu(a, cursor.getLeft());
            }
            if(cursor.getMiddle()!=null){
                String a = parentInfo + cursor.getMiddle().getSelection()+"@";
                printMenu(a, cursor.getMiddle());
            }
            if(cursor.getRight()!=null){
                String a = parentInfo + cursor.getRight().getSelection() +"@";
                printMenu(a, cursor.getRight());
            }
        }else{
            String[] stringArray = parentInfo.split("@");
            String restaurant = stringArray[0];
            String food = "";
            for(int i = 1; i<stringArray.length; i++){
                if(i<stringArray.length-1) {
                    food = food + stringArray[i] + ", ";
                }else {
                    food = food + stringArray[i] ;
                }
            }
            String displayMes = String.format("%-20s%-60s%-8s", restaurant, food, treeNode.getMessage());
            System.out.println(displayMes);
        }
    }

    /**
     * The method to help customers to order food.
     * @param a is the current TreeNode.
     * @param item is the message that will be displayed at the end of an order.
     */
    public void beginSession(TreeNode a, String item){
        if(!a.isLeaf()){
            System.out.println(a.getMessage());
            if(a.getLeft()!=null){
                System.out.println("1 "+a.getLeft().getSelection());
            }if(a.getMiddle()!=null){
                System.out.println("2 "+a.getMiddle().getSelection());
            }if(a.getRight()!=null){
                System.out.println("3 "+a.getRight().getSelection());
            }
            System.out.println("0 Exit Session");
            try {
                int command = input.nextInt();
                switch (command){
                    case 1:
                        String mes = item + a.getLeft().getSelection()+"~";
                        beginSession(a.getLeft(),mes);
                        break;
                    case 2:
                        String mes1 = item + a.getMiddle().getSelection()+"~";
                        beginSession(a.getMiddle(),mes1);
                        break;
                    case 3:
                        String mes2 = item + a.getRight().getSelection()+"~";
                        beginSession(a.getRight(),mes2);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }catch (InputMismatchException e){
                input.reset();
                System.out.println("Wrong input type!");
            }

        }else{
            String[] sArray = item.split("~");
            String foodItem="";
            for(int i = 1; i<sArray.length; i++){
                if(i==sArray.length){
                    foodItem = foodItem + sArray[i];
                }else {
                    foodItem = foodItem + sArray[i] + ",";
                }
            }
            System.out.println("The order at "+sArray[0]+": "+foodItem+"has been sent to the kitchen. Total amount would be "+a.getMessage());
        }
    }
}
