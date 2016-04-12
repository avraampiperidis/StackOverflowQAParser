package com.protectsoft;

import java.util.List;

import com.protectsoft.StackAnswerModel.AnswerText;
import com.protectsoft.StackAnswerModel.QuestionText;

public interface AnswerInter {
	
	public String getUrl();
	
	public void setUrl(String url);
	
	public String getTitle();
	
	public void setTitle(String t);
	
	public QuestionText getQuestionText();
	
	public void setQuestionText(QuestionText qt);
	
	public List<AnswerText> getAnswers();
	
	public void setAnswers(List<AnswerText> answers);

}
