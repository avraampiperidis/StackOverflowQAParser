# StackOverflowQAParser
Java library that grabs questions and answers from stackoverflow.com from a question.<br>
Set a Question E.g('how to soft objects') set language and it will scan stackoverflow from
relative questions and for answers. <br>
Choose up to two ways to get answers.<br>
1)synchronous call (execute in main thread)<br>
2)asynchronous call (execute in other thread and Runnable will be called when results are ready).<br>
<br>
I just started this project and its just for fun/learning/exercise.It needs a lot of work<br>
In the future i will make it in c#,<br>
and it would be prety cool to see this in python..some day
<br>
<br>
I havent wrote any test yet..i'll write when i have some time
<br>
<br>
from https://github.com/zeronerone/StackOverflowQAParser/blob/master/src/main/java/testlib/Test.java
<br>
the basic idea and flow
<br>
```java
//|---------->>Synchronous way example<<------------------------------------||
//execute in main thread till results are ready
Question<QuestionModel> qmodel = new Question<QuestionModel>(new QuestionModel());

		
QuestionModel synmodel = qmodel.setQuestion("linked list sort")
	.setLang(Lang.JAVA)
	.getQuestionResultSet();
			
	for(int i =0; i < synmodel.getQuestions().size(); i++) {
		System.out.println("from Syn"+synmodel.getQuestions().get(i).getTitle());
		System.out.println("from Syn"+synmodel.getQuestions().get(i).getUrl());
		System.out.println("from Syn"+synmodel.getQuestions().get(i).getNumOfAnswers());
	}
			

//
//		
//|------------->>Asynchronous way example<<------------------------------------||
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
			
		
//|--->get answers for a spicific question<-------------------------------||
//|--->Synchronous way<---------------------------------------------------||
Answer<AnswerModel> answer = new Answer<AnswerModel>(new AnswerModel());
		
AnswerModel mymodel = answer.getAnswersForQuestionURL(model.getQuestions().get(0));
		
for(int i =0; i < mymodel.getAnswers().size(); i++) {
	System.out.println(mymodel.getAnswers().get(i).getCode());
	System.out.println(mymodel.getAnswers().get(i).getText());
}
				
	
		
//|--->get answers for a spicific question<-------------------------------||
//|--->Asynchronous way<---------------------------------------------------||
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
```
