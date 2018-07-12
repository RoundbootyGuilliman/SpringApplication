package app.service.impl;

import app.converter.NewsConverter;
import app.dao.NewsDAO;
import app.dto.CommentDTO;
import app.dto.NewsDTO;
import app.entity.Comment;
import app.entity.News;
import app.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsDAO newsDAO;
	
	NewsConverter<NewsDTO, News, CommentDTO, Comment> entityToDto = new NewsConverter<>(NewsDTO.class, CommentDTO.class);
	NewsConverter<News, NewsDTO, Comment, CommentDTO> dtoToEntity = new NewsConverter<>(News.class, Comment.class);
	
	@Override
	public List<NewsDTO> getAllNews() {
		
		List<NewsDTO> newsList = new ArrayList<>();
		for (News news : newsDAO.getAllNews()) {
			newsList.add(entityToDto.convert(news));
		}
		return newsList;
	}
	
	@Override
	public NewsDTO getNewsById(int id) {
		return entityToDto.convert(newsDAO.getNewsById(id));
	}
	
	@Override
	public List<NewsDTO> getNewsByAuthor(String username) {
		List<NewsDTO> newsList = new ArrayList<>();
		for (News news : newsDAO.getNewsByAuthor(username)) {
			newsList.add(entityToDto.convert(news));
		}
		return newsList;
	}
	
	@Override
	public void saveNews(NewsDTO news, String username) {
		if (news.getId() == 0) {
			news.setAuthor(username);
			news.setDate(new Date());
		} else {
			News oldNews = newsDAO.getNewsById(news.getId());
			news.setAuthor(oldNews.getAuthor());
			news.setDate(oldNews.getDate());
		}
		news.setId(newsDAO.saveNews(dtoToEntity.convert(news)));
	}
	
	@Override
	public void deleteNews(String[] newsToDelete) {
		
		for (String checkbox : newsToDelete) {
			newsDAO.deleteNews(Integer.parseInt(checkbox));
		}
	}
	
	@Override
	public void saveComment(CommentDTO commentDTO, String username) {
		NewsDTO news = entityToDto.convert(newsDAO.getNewsById(commentDTO.getNewsId()));
		
		commentDTO.setDate(new Date());
		commentDTO.setUsername(username);
		
		news.getComments().add(commentDTO);
		newsDAO.saveNews(dtoToEntity.convert(news));
	}
	
	@Override
	public void deleteComments(String[] checkboxes, int newsId) {
		
		News news = newsDAO.getNewsById(newsId);
		Iterator<Comment> iterator = news.getComments().iterator();
		
		while (iterator.hasNext()) {
			Comment comment = iterator.next();
			for (String checkbox : checkboxes) {
				if (comment.getId() == Integer.parseInt(checkbox)) {
					iterator.remove();
				}
			}
		}
		newsDAO.saveNews(news);
	}
}
