package app.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class CommentDTO implements AbstractComment {
	
	private int id;
	private String username;
	private Date date;
	private int newsId;
	
	@NotEmpty
	private String comment;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getNewsId() {
		return newsId;
	}
	
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}
