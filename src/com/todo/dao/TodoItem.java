package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;  //제목
    private String desc;   //내용
    private String current_date;  //입력한 시간 
    		
    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
    	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = format.format(new Date());
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
		return "[" + title + "] " + desc + " - " + current_date;
	}
	
	public String toSaveString() {
		return title + "##" + desc + "##" + current_date +"\n";
	}
    
}
