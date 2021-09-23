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
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== [항목 추가] \n"
				+ "제목 > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("중복된 제목 !!");
			return;
		}
		sc.nextLine();  //제목 뒤에 들어오는 enter 제거 
		System.out.println("내용 > ");
		desc = sc.nextLine().trim(); //trim-> 좌우여백 제거 
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("아이템 추가 완료!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== [항목 삭제]\n"
				+ "삭제할 항목의 제목 입력 > ");
		
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("아이템 삭제 완료!");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== [항목 수정]\n"
				+ "수정할 항목의 제목 입력 > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("존재하지 않는 제목 !!");
			return;
		}

		System.out.println("새 제목 입력 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("중복된 제목 !!");
			return;
		}
		
		sc.nextLine();
		System.out.println("새 내용 입력 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {  //원래 제목의 아이템 찾아서 삭제 
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);  //새로운 객체 생성해서 add
				l.addItem(t); //수정된 내용은 current_date가 현재 시간으로 setting됨 
				System.out.println("item updated");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("\n"
				+ "========== [전체 목록]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) { //프로그램 종료 시 저장 
		//FileWriter
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem m : l.getList()) {
				w.write(m.toSaveString());
			}
			w.close();
			System.out.println("TodoList 저장 완료 !!\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) { //프로그램 시작 시 읽기 
		//BufferedReader, FileReader, StringTokenizer 
		int num=0;
		try {
			BufferedReader br = new BufferedReader(new FileReader (filename));
			
			String oneline;
			while((oneline=br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				TodoItem m = new TodoItem(title, desc);
				m.setCurrent_date(date);
				l.addItem(m);
				num++;
			}
			br.close();
			System.out.println(num+"개의 항목 로딩 완료 !!");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("todolist.txt 파일이 없습니다.\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
