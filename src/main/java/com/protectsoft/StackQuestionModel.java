package com.protectsoft;

public class StackQuestionModel {
	
	private static final String BASE_URL = "http://stackoverflow.com/";

    private String url;
    private int numOfAnswers = 1;
    private String title;

    public StackQuestionModel() {
        url = "";
        title = "";
    }

    public StackQuestionModel(String url, int numOfAnswers, String title) {
        this.setUrl(url);
        this.setNumOfAnswers(numOfAnswers);
        this.setTitle(title);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = BASE_URL + url;
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



    @Override
    public String toString() {
        return "Url:"+url+",num:"+numOfAnswers+",title:"+title;
    }

}
