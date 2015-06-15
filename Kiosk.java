package com.company;

import java.io.*;
import java.util.NoSuchElementException;
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
 * The main method of the program and help people to have different task.
 * "L" to load a tree.
 * "P" to print the menu after a tree is loaded.
 * "S" to start order the food.
 * "Q" to quit the kiosk.
 */
public class Kiosk {

    public static Scanner input = new Scanner(System.in);
    public static Tree tree;
    public static TreeNode root;

    public static void main(String[] args) {
        boolean isRunning = true;

        while(isRunning){
            System.out.println("L) Load a Tree \nP) Print menu \nS) Start service\nQ) Quit");
            String command = input.next();
            command=command.toUpperCase();
            String rootInfo="";
            if(command.equals("L")){
                root = null;
                tree = null;
                System.out.println("Enter the name of the file:");
                String fileLoc = "";
                String path = input.next();
                fileLoc = fileLoc+path;
                try {
                    Scanner reader = new Scanner(new File(fileLoc));
                    while(reader.hasNext()) {
                        if(tree==null){
                            String name = reader.nextLine();
                            String selection = reader.nextLine();
                            String message = reader.nextLine();
                            root = new TreeNode(name, selection, message);
                            tree = new Tree(root);
                        }else{
                            String parentName = reader.next();
                            int numChild = reader.nextInt();
                            String pa = reader.nextLine();
                            TreeNode newNode = tree.findNode(parentName);
                            if(newNode!=null){
                                for(int i = 1; i<=numChild; i++){
                                    String name = reader.nextLine();
                                    String selection = reader.nextLine();
                                    String message = reader.nextLine();
                                    tree.addNode(name, selection, message, parentName, i);
                                }
                            }else{
                                System.out.println("Can't find node"+parentName);
                            }
                        }
                    }
                }catch (FileNotFoundException e){
                    System.out.println("Can't find the file");
                }catch (NoSuchElementException e){
                    System.out.println("Invalid format");
                }catch (Exception e){
                    System.out.println("Can't load the tree");
                }

            }else if(command.equals("P")){
                if(tree!=null) {
                    System.out.println("Menu: ");
                    String display = String.format("%-20s%-60s%-8s","Dining", "Selection", "Price");
                    System.out.println(display);
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    tree.printMenu(rootInfo, root);
                }else {
                    System.out.println("The menu isn't built yet");
                }
            }else if(command.equals("S")){
                if(tree==null){
                    System.out.println("The menu isn't built yet");
                }else{
                    tree.beginSession(root,"");
                }
            }else if(command.equals("Q")){
                isRunning = false;
                System.out.println("Kiosk shutting down...");
            }else{
                System.out.println("Invalid input");
            }
        }

    }
}
