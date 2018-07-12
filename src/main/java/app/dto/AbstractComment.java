package app.dto;

import java.util.Date;

public interface AbstractComment {
	
	int getId();
	void setId(int id);
	
	String getUsername();
	void setUsername(String username);
	
	Date getDate();
	void setDate(Date date);
	
	int getNewsId();
	void setNewsId(int newsId);
	
	String getComment();
	void setComment(String comment);
}
