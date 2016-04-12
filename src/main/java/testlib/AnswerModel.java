package testlib;

import java.util.List;

import com.protectsoft.AnswerInter;
import com.protectsoft.StackAnswerModel.AnswerText;
import com.protectsoft.StackAnswerModel.QuestionText;

//user model class implementing AnswerInter
public class AnswerModel implements AnswerInter {
	
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
