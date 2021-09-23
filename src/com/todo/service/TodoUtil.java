package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<Add new Item to the list>\n"
				+ "New Title:   ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("The title is duplicated!!");
			return;
		}
		
		sc.nextLine();
		System.out.print("Description: ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("New item added!");	
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<Delete Item from the list>\n"
				+ "Removing Title: ");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("The Title is removed!");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<Change Item in the list>\n"
				+ "Old Title:   ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("The title doesn't exist!");
			return;
		}

		System.out.print("New Title:   ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("The title is duplicated!");
			return;
		}
		sc.nextLine();
		System.out.print("Description: ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("The title is updated!");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
}
