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
<br>
Create two model classes and implement their interfaces
<br>
MyQuestionModel must implement QuestionInter
```java
import com.protectsoft.QuestionInter;
import com.protectsoft.StackQuestionModel;

//user class must implement QuestionInter
public class MyQuestionModel implements QuestionInter {
	//create this variable so it can be set and get from the overrided methods
	private List<StackQuestionModel> qmodels;
	
	public MyQuestionModel() {
	}

	@Override
	public List<StackQuestionModel> getQuestions() {
		return qmodels;
	}

	@Override
	public void setStackQuestionModel(List<StackQuestionModel> qmodels) {
		this.qmodels = qmodels;
	}

}
```
And this class MyAnswerModel implements AnswerInter
```java
import com.protectsoft.AnswerInter;
import com.protectsoft.StackAnswerModel.AnswerText;
import com.protectsoft.StackAnswerModel.QuestionText;

//user model class implementing AnswerInter
public class AnswerModel implements AnswerInter {
	//create this variables so it can be set and get from the overrided methods
	private String url;
	private String title;
	private QuestionText questiontext;
	private List<AnswerText> answers;
	
	public AnswerModel() {
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String t) {
		this.title = t;
	}

	public QuestionText getQuestionText() {
		return questiontext;
	}

	public void setQuestionText(QuestionText qt) {
		questiontext = qt;
	}

	public List<AnswerText> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerText> answers) {
		this.answers = answers;
	}

}
```

And the test... 
```java
//|---------->>Synchronous way example<<------------------------------------||
//execute in main thread till results are ready
//
Question<MyQuestionModel> qmodel = new Question<MyQuestionModel>(new MyQuestionModel());

String questionString = "how to sort objects";//the question string		
MyQuestionModel synmodel = qmodel.setQuestion(questionString)
	.setLang(Lang.JAVA)
	.getQuestionResultSet();
			
	//synmodel is filled with data		
	for(int i =0; i < synmodel.getQuestions().size(); i++) {
		System.out.println("from Syn"+synmodel.getQuestions().get(i).getTitle());
		System.out.println("from Syn"+synmodel.getQuestions().get(i).getUrl());
		System.out.println("from Syn"+synmodel.getQuestions().get(i).getNumOfAnswers());
	}
			

//
//		
//|------------->>Asynchronous way example<<------------------------------------||
Question<MyQuestionModel> qmodeltest = new Question<MyQuestionModel>(new MyQuestionModel());
			
final MyQuestionModel model = qmodeltest.getModel();
			
//the query will be executed in another thread
//AsynExecAdapters run method will be called whene the results are ready
String questionString = "how to cast objects";//the question string
qmodeltest.setQuestion(questionString)
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
						//
						//get the first question object from 	
						//the questionset
						//model.getQuestions().get(0)	
AnswerModel mymodel = answer.getAnswersForQuestionURL(model.getQuestions().get(0));

//mymodel is filled with data		
//for this question print all its answers ,code and text(summary/explanation)
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
		
		//print the questions title and the url
		System.out.println(anmodel.getTitle());
		System.out.println(anmodel.getUrl());
		
		//for this question print all its answers ,code and text(summary/explanation)
		for(int i =0; i < anmodel.getAnswers().size(); i++) {
			System.out.println(anmodel.getAnswers().get(i).getCode());
			System.out.println(anmodel.getAnswers().get(i).getText());
		}
						
	}});
```
