package app.dto;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsDTO implements AbstractNews<CommentDTO> {
	
	private int id;
	private String author;
	private Date date;
	private List<CommentDTO> comments = new ArrayList<>();
	
	@NotEmpty(message = "{valid.notEmpty}")
	private String title;
	@NotEmpty(message = "{valid.notEmpty}")
	private String brief;
	@NotEmpty(message = "{valid.notEmpty}")
	private String content;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<CommentDTO> getComments() {
		return comments;
	}
	
	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBrief() {
		return brief;
	}
	
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
