package com.protectsoft;

import java.io.IOException;


public class Answer<T extends AnswerInter> {
	
	private T t;
	private Thread thread;
	private StackQuestionModel qmodel;
	
	public Answer(T t) {
		if(t == null) {
			throw new NullPointerException("cant be null!");
		}
		this.t = t;
	}
	
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T extends AnswerInter> T getAnswersForQuestionURL(StackQuestionModel stqm) throws IOException {
		return (T) new SyncBuilder<T>((T)t,stqm).build();
	}
	
	public Answer<T> with(StackQuestionModel qmodel) {
		this.qmodel = qmodel;
		return this;
	}
	
	public StackQuestionModel getQmodel() {
		return qmodel;
	}
	
	@SuppressWarnings({ "hiding", "unchecked" })
	public <T extends AnswerInter> T getModel() {
		return (T) t;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void asynGetAnswersForQuestionURL(final AsynExecAdapter runnable) {
		Thread th;
		th = new Thread(new Runnable(){

			public void run() {
				try {
					StackAnswerModel stackmodel = Utils.getSingleton().getAnswerForQuestion(getQmodel()); //Utils.getAnswerForQuestion(getQmodel());
					runnable.setStackAnswerModel(stackmodel);
					
					thread = new Thread(runnable);
					thread.start();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}});
		runnable.setAmodel(t);
		th.start();		
	}
	
	
	
	
	 @SuppressWarnings("hiding")
	private class SyncBuilder<T extends AnswerInter> {
			
			private  T t;
			private StackQuestionModel stqm;
			
			@SuppressWarnings("unused")
			public SyncBuilder() {
				
			}
			
			public SyncBuilder(T t,StackQuestionModel stqm) {
				this.t = t;
				this.stqm = stqm;
			}

			
			private  T build() throws IOException {
				Utils utils = Utils.getSingleton();
				
				StackAnswerModel stmodel = utils.getAnswerForQuestion(stqm);
			    t.setAnswers(stmodel.getCodeAnswers());
			    t.setQuestionText(stmodel.getQuestionText());
			    t.setTitle(stmodel.getTitle());
			    t.setUrl(stmodel.getUrl());
			
				return t;
			}
			
		}
	

}
