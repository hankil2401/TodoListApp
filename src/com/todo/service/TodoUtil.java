package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	private static int count=0;

	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<Add new Item to the list>\n"
				+ "New Title:   ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("The title is duplicated!!");
			return;
		}
		System.out.print("Category:    ");
		category = sc.next();
		sc.nextLine();
		System.out.print("Description: ");
		desc = sc.nextLine().trim();
		System.out.print("Due date:    ");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println("New item added!");	
		count++;
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<Delete Item from the list>\n"
				+ "Removing Title's number: ");
		int no = sc.nextInt();
		
		for (int i=0; i<count; i++) {
			if (no<=count && no>0) {
				System.out.print(no+". ");
				System.out.println(l.getList().get(no-1).toString());
				System.out.print("Are you sure you want to delete (y/n): ");
				String yn = sc.next();
				if(yn.equals("y")) {
					l.deleteItem(l.getList().get(no-1));
					System.out.println("The Title is removed!");
					count--;
				}
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<Change Item in the list>\n"
				+ "Old Title's number:   ");
		int no = sc.nextInt();
		System.out.print(no+". ");
		System.out.println(l.getList().get(no-1).toString());
//		if (!l.isDuplicate(title)) {
//			System.out.println("The title doesn't exist!");
//			return;
//		}

		System.out.print("New Title:       ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("The title is duplicated!");
			return;
		}
		System.out.print("New Category:    ");
		String new_category = sc.next();
		sc.nextLine();
		System.out.print("Description:     ");
		String new_description = sc.nextLine().trim();
		System.out.print("New due date:    ");
		String new_due_date = sc.next();
		for (int i=0; i<count; i++) {
			if (no<=count && no>0) {
				l.deleteItem(l.getList().get(no-1));
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.println("The item is updated!");
				break;
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<All List, "+count+" item(s) in total>");
		int i=1;
		for (TodoItem item : l.getList()) {
			System.out.print(i+". ");
			i++;
			System.out.println(item.toString());
		}
	}
	
	public static void find(TodoList l, String str) {
		int j=1;
		for(int i=0; i<count; i++) {
			TodoItem item = l.getList().get(i);
			if(item.getCategory().toLowerCase().contains(str.toLowerCase()) 
			|| item.getTitle().toLowerCase().contains(str.toLowerCase())
			|| item.getDesc().toLowerCase().contains(str.toLowerCase())) {
				System.out.print(i+1+". ");
				System.out.println(item.toString());
				j++;
			}
		}
		System.out.println("Found total of "+ j + " item(s).");
	}
	
	public static void find_cate(TodoList l, String str) {
		int j=1;
		for(int i=0; i<count; i++) {
			TodoItem item = l.getList().get(i);
			if(item.getCategory().toLowerCase().contains(str.toLowerCase())) {
				System.out.print(i+1+". ");
				System.out.println(item.toString());
				j++;
			}
		}
		System.out.println("Found total of "+ j + " item(s) of this category.");
	}
	
	public static void ls_cate(TodoList l) {
		HashSet<String> set = new HashSet<>();
		for (TodoItem item : l.getList()) {
			set.add(item.getCategory());
        }
		Iterator<String> itr=set.iterator();  
		int i=1;
		for(String cate : set) {
			System.out.print(itr.next());
			if(itr.hasNext()) {
				i++;
				System.out.print(" / ");
			}
		}
		System.out.println("\nTotal of "+ i + " categories are registered.");
	}

	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			count = 0;
			while((oneline = br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String ct = st.nextToken();
				String t = st.nextToken();
				String td = st.nextToken();
				String dd = st.nextToken();
				String cd = st.nextToken();
				TodoItem i = new TodoItem(t, td, cd, ct, dd);
				l.addItem(i);
				count++;
			}
			System.out.println(count+" item(s) has been read!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item: l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
