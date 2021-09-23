package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<<Menu for ToDoList commands>>");
        System.out.println("1. add - insert new item");
        System.out.println("2. del - remove existing item");
        System.out.println("3. edit - update existing item");
        System.out.println("4. ls - list all the items");
        System.out.println("5. ls_name_asc - list all items in ascending order of name");
        System.out.println("6. ls_name_desc - list all items in descending order of name");
        System.out.println("7. ls_date - list all items in ascending order of date");
        System.out.println("8. exit - quit the program!");
    }
    
    public static void prompt() {
    	System.out.print("\nCommand: ");
    }
}
