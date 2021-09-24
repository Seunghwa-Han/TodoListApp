package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;  //제목
    private String desc;   //내용
    private String current_date;  //입력한 시간 
    private String category; //카테고리 
    private String due_date; //마감일자 
    		
    public TodoItem(String title, String desc, String category, String due_date){
        this.title=title;
        this.desc=desc;
    	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = format.format(new Date());
        this.category = category;
        this.due_date = due_date;
    }
    
    public TodoItem(String title, String desc, String current_date, String category, String due_date) {
		super();
		this.title = title;
		this.desc = desc;
		this.current_date = current_date;
		this.category = category;
		this.due_date = due_date;
	}



	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

	@Override
	public String toString() {
		return "[" + category + "] " + title + " - " + desc + " - " + due_date + " (" + 
				current_date + " 작성)" ;
	}
	
	public String toSaveString() {
		return title + "##" + desc + "##" + current_date + "##" + category + "##" + due_date+ "\n";
	}
    
}
