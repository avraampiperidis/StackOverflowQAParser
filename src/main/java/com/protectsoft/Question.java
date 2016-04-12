package com.protectsoft;

import java.io.IOException;
import java.util.List;

public class Question<T extends QuestionInter> {
	
	static final String URL = "http://stackoverflow.com/search?q=[java]+";
	
	private Thread thread;
	private String question;
	private Lang lang;
	
	private T t;
		
	public  Question(T t) {
		if(t == null) {
			throw new NullPointerException("Constructor Object cant be null!");
		}
		this.t = t;
	}
	
	public String getQuestion() {
		return question;
	}
	
	
	public  Question<T> setQuestion(String question) {
		this.question = question;
		return this;
	}
	
	
	public Question<T> setLang(Lang lang) {
		this.lang = lang;
		return this;
	}
	
	
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T extends QuestionInter> T getQuestionResultSet() throws IOException {
		return (T) new SyncBuilder<T>((T)t).build();
	}
	
	
	@SuppressWarnings("unchecked")
	public void asynQuestionResultSet(@SuppressWarnings("rawtypes") final AsynExecAdapter runnable) throws InterruptedException {
		
		Thread th;
		th = new Thread(new Runnable(){

			public void run() {
				try {
					String url = URL + Utils.questionBuilder(getQuestion());
					List<StackQuestionModel> qmodels = Utils.getQuestions(url);
					
					runnable.setQuestionModels(qmodels);
					
					thread = new Thread(runnable);
					thread.start();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}});
		runnable.setModel(t);
		th.start();		
	}
	
	
	public int getNumOfAnswers() {
		return 0;
	}
	
	
	@SuppressWarnings({ "hiding", "unchecked" })
	public <T extends QuestionInter> T getModel() {
		return (T) t;
	}
	
	
	 private class SyncBuilder<T extends QuestionInter> {
		
		private  T t;
		
		public SyncBuilder() {
			
		}
		
		public SyncBuilder(T t) {
			this.t = t;
		}

		private  T build() throws IOException {
			String url = URL + Utils.questionBuilder(getQuestion());
			List<StackQuestionModel> qmodels = Utils.getQuestions(url);
			
			t.setStackQuestionModel(qmodels);
			return t;
		}
		
	}
	

}
