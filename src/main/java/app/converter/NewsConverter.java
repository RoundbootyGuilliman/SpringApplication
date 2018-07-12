package app.converter;

import app.dto.AbstractComment;
import app.dto.AbstractNews;

public class NewsConverter<N1 extends AbstractNews<C1>, N2 extends AbstractNews<C2>, C1 extends AbstractComment, C2 extends AbstractComment> {
	
	Class<N1> resultNewsClass;
	Class<C1> resultCommentClass;
	CommentConverter<C1, C2> converter = new CommentConverter<>();
	
	public NewsConverter(Class<N1> resultNewsClass, Class<C1> resultCommentClass) {
		this.resultNewsClass = resultNewsClass;
		this.resultCommentClass = resultCommentClass;
	}
	
	public N1 convert(N2 convertedNews) {
		N1 resultNews = null;
		try {
			
			resultNews = resultNewsClass.newInstance();
			resultNews.setId(convertedNews.getId());
			resultNews.setAuthor(convertedNews.getAuthor());
			resultNews.setDate(convertedNews.getDate());
			resultNews.setTitle(convertedNews.getTitle());
			resultNews.setBrief(convertedNews.getBrief());
			resultNews.setContent(convertedNews.getContent());
			resultNews.setComments(converter.convert(convertedNews.getComments(), resultCommentClass));
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return resultNews;
	}
}
