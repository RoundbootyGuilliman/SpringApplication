package app.service;

import app.dto.CommentDTO;
import app.dto.NewsDTO;

import java.util.List;

public interface NewsService {
	
	List<NewsDTO> getAllNews();
	NewsDTO getNewsById(int id);
	List<NewsDTO> getNewsByAuthor(String username);
	void saveNews(NewsDTO news, String username);
	void deleteNews(String[] newsToDelete);
	void saveComment(CommentDTO commentDTO, String username);
	void deleteComments(String[] checkboxes, int newsId);
}
