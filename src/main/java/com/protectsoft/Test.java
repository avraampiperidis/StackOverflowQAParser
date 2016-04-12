package com.protectsoft;

import java.io.IOException;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		
		//asynchronous way example
		
		Question<QuestionModel> q = new Question<QuestionModel>(new QuestionModel());
		
		final QuestionModel model = q.getModel();
		
		//the query will be executed in another thread
		//main thread is not blocking
		//when read run() would be called
		q.setQuestion("sort objects")
		.setLang(Lang.JAVA)
		.asynQuestionResultSet(new AsynExecAdapter(){

			@Override
			public void run() {
				//must call this to fill model with data
				fillModel(model);
				
				//print the results
				for(int i =0; i < model.getQuestions().size(); i++) {
					System.out.println("from asyn"+model.getQuestions().get(i).getTitle());
					System.out.println("from asyn"+model.getQuestions().get(i).getUrl());
				}
				
			}
			}
		);
		
		
		//synchronous way example
		//main thread will be block and wait till results are ready
		try {
			
			q = new Question<QuestionModel>(new QuestionModel());
		
			QuestionModel synmodel = q.setQuestion("linked list sort")
					 .setLang(Lang.JAVA)
					 .getQuestionResultSet();
			
			for(int i =0; i < synmodel.getQuestions().size(); i++) {
				System.out.println("from Syn"+synmodel.getQuestions().get(i).getTitle());
				System.out.println("from Syn"+synmodel.getQuestions().get(i).getUrl());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 
		 
				
	
		
	
		
	}

}
