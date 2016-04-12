package com.protectsoft;

import java.util.List;

//user class must implement QuestionInter
public class QuestionModel implements QuestionInter {
	
	private List<StackQuestionModel> qmodels;
	
	public QuestionModel() {
	}

	
	public List<StackQuestionModel> getQuestions() {
		return qmodels;
	}

	public void setStackQuestionModel(List<StackQuestionModel> qmodels) {
		this.qmodels = qmodels;
	}

}
