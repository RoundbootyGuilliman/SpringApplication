package app.converter;

import app.dto.AbstractComment;

import java.util.ArrayList;
import java.util.List;

public class CommentConverter<RC extends AbstractComment, CC extends AbstractComment> {
	
	public List<RC> convert(List<CC> convertedCommentsList, Class<RC> resultCommentClass) throws IllegalAccessException, InstantiationException {
		
		List<RC> resultCommentsList = new ArrayList<>();
		
		for (CC convertedComment : convertedCommentsList) {
			
			RC resultComment = resultCommentClass.newInstance();
			
			resultComment.setId(convertedComment.getId());
			resultComment.setNewsId(convertedComment.getNewsId());
			resultComment.setUsername(convertedComment.getUsername());
			resultComment.setDate(convertedComment.getDate());
			resultComment.setComment(convertedComment.getComment());
			resultCommentsList.add(resultComment);
		}
		return resultCommentsList;
	}
}
