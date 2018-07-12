package app.dao;

import app.entity.News;

import java.util.List;

public interface NewsDAO {

	List<News> getAllNews();
	News getNewsById(int id);
	List<News> getNewsByAuthor(String username);
	int saveNews(News news);
	void deleteNews(int id);
}
