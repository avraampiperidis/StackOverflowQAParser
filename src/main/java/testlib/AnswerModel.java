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

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String t) {
		this.title = t;
	}

	@Override
	public QuestionText getQuestionText() {
		return questiontext;
	}

	@Override
	public void setQuestionText(QuestionText qt) {
		questiontext = qt;
	}

	@Override
	public List<AnswerText> getAnswers() {
		return answers;
	}

	@Override
	public void setAnswers(List<AnswerText> answers) {
		this.answers = answers;
	}

}
