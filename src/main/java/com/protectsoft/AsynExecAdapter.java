package com.protectsoft;

import java.util.List;

public abstract class AsynExecAdapter<T> implements Runnable {
	
	private T q;
	private T a;
	
	private List<StackQuestionModel> qmodels;
	private StackAnswerModel stackmodel;
	
	public void setQModel(T q) {
		this.q = q;
	}
	
	public void setAmodel(T a) {
		this.a = a;
	}
	
	public T getAmodel() {
		return a;
	}
	
	public T getQModel() {
		return q;
	}
	
	public List<StackQuestionModel> getQstackModels() {
		return qmodels;
	}
	
	public void setQuestionModels(List<StackQuestionModel> qmodels) {
		this.qmodels = qmodels;
	}
	
	public void setStackAnswerModel(StackAnswerModel stackm) {
		this.stackmodel = stackm;
	}
	
	public abstract void run();

	
	@SuppressWarnings({ "hiding" })
	public <T extends QuestionInter> void fillModel(T m) {
		m.setStackQuestionModel(qmodels);
	}
	
	
	@SuppressWarnings("hiding")
	public <T extends AnswerInter> void fillModel(T m) {
		m.setAnswers(stackmodel.getCodeAnswers());
		m.setQuestionText(stackmodel.getQuestionText());
		m.setTitle(stackmodel.getTitle());
		m.setUrl(stackmodel.getUrl());
	}
	

}
