package testlib;

import java.io.IOException;

import com.protectsoft.Answer;
import com.protectsoft.AsynExecAdapter;
import com.protectsoft.Lang;
import com.protectsoft.Question;

public class Test {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		
		//synchronous way example
		//main thread will be block and wait till results are ready
		Question<QuestionModel> qmodel = new Question<QuestionModel>(new QuestionModel());
		
		QuestionModel synmodel = qmodel.setQuestion("linked list sort")
					 .setLang(Lang.JAVA)
					 .getQuestionResultSet();
			
		for(int i =0; i < synmodel.getQuestions().size(); i++) {
			System.out.println("from Syn"+synmodel.getQuestions().get(i).getTitle());
			System.out.println("from Syn"+synmodel.getQuestions().get(i).getUrl());
			System.out.println("from Syn"+synmodel.getQuestions().get(i).getNumOfAnswers());
		}
			
		//wait 2 sec for test 
				Thread.sleep(2000);
		
			//asynchronous way example
		Question<QuestionModel> qmodeltest = new Question<QuestionModel>(new QuestionModel());
			
			final QuestionModel model = qmodeltest.getModel();
			
			//the query will be executed in another thread
			//main thread is not blocking
			//when read run() would be called
			qmodeltest.setQuestion("sort objects")
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
						System.out.println("from asyn"+model.getQuestions().get(i).getNumOfAnswers());
					}
					
				}
				}
			);
			
			
		//wait 2 sec for results 
		Thread.sleep(2000);		
		
		//get answers for a spicific question
		//Syn way
		Answer<AnswerModel> answer = new Answer<AnswerModel>(new AnswerModel());
		
		AnswerModel mymodel = answer.getAnswersForQuestionURL(model.getQuestions().get(0));
		
		for(int i =0; i < mymodel.getAnswers().size(); i++) {
			System.out.println(mymodel.getAnswers().get(i).getCode());
			System.out.println(mymodel.getAnswers().get(i).getText());
		}
				
		
		
		
		//get answers for a spicific question
		//Asyn way
		 answer = new Answer<AnswerModel>(new AnswerModel());
		 
		 final AnswerModel anmodel = answer.getModel();
		
		  answer.with(model.getQuestions().get(0))
				 .asynGetAnswersForQuestionURL(new AsynExecAdapter(){

					public void run() {
						//fill the model
						fillModel(anmodel);
						System.out.println(anmodel.getTitle());
						System.out.println(anmodel.getUrl());
						for(int i =0; i < anmodel.getAnswers().size(); i++) {
							System.out.println(anmodel.getAnswers().get(i).getCode());
						}
						
					}});
		  
		
		
	}
	
	
	

}
