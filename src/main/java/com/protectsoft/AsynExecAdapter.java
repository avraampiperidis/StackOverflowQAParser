package com.protectsoft;

import java.util.List;

public abstract class AsynExecAdapter<T> implements Runnable {
	
	private T t;
	private List<StackQuestionModel> qmodels;
	
	public void setModel(T t) {
		this.t = t;
	}
	
	public T getModel() {
		return t;
	}
	
	public List<StackQuestionModel> getQModels() {
		return qmodels;
	}
	
	public void setQuestionModels(List<StackQuestionModel> qmodels) {
		this.qmodels = qmodels;
	}
	
	public abstract void run();

	
	@SuppressWarnings({ "hiding" })
	public <T extends QuestionInter> void fillModel(T m) {
		m.setStackQuestionModel(qmodels);
	}
	

}
