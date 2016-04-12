package com.protectsoft;

import java.util.List;

public class StackAnswerModel {
	
	private String url;
    private int numOfAnswers = 1;
    private String title;
    private QuestionText questionText;
    private List<AnswerText> codeAnswers;


    public StackAnswerModel() {}

    public StackAnswerModel(String url, int numOfAnswers, String title) {
        this.setUrl(url);
        this.setNumOfAnswers(numOfAnswers);
        this.setTitle(title);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumOfAnswers() {
        return numOfAnswers;
    }

    public void setNumOfAnswers(int numOfAnswers) {
        this.numOfAnswers = numOfAnswers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionText getQuestionText() {
        return questionText;
    }

    public void setQuestionText(QuestionText questionText) {
        this.questionText = questionText;
    }

    public List<AnswerText> getCodeAnswers() {
        return codeAnswers;
    }

    public void setCodeAnswers(List<AnswerText> codeAnswers) {
        this.codeAnswers = codeAnswers;
    }

    public boolean isEmpty() {
        if(title != null && !title.isEmpty()) {
            if(codeAnswers != null && !codeAnswers.isEmpty()) {
                return false;
            }
        }
        return true;
    }


    public static class QuestionText   {

        private String text;
        private String code;

        public QuestionText() {}

        public QuestionText(String text,String code) {
            this.text = text;
            this.code = code;
        }


        public String getText() {
            return text;
        }

        public void setText(String t) {
            text = t;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String c) {
            code = c;
        }
    }


    public static class AnswerText   {

        private String text;
        private String code;
        private String url;

        public AnswerText() {}

        public AnswerText(String text,String code) {
            this.setText(text);
            this.setCode(code);
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String t) {
            url = t;
        }


    }

}
