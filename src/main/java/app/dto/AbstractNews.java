package app.dto;

import java.util.Date;
import java.util.List;

public interface AbstractNews<T> {
	
	int getId();
	void setId(int id);
	
	String getAuthor();
	void setAuthor(String author);
	
	Date getDate();
	void setDate(Date date);
	
	List<T> getComments();
	void setComments(List<T> comments);
	
	String getTitle();
	void setTitle(String title);
	
	String getBrief();
	void setBrief(String brief);
	
	String getContent();
	void setContent(String content);
}
