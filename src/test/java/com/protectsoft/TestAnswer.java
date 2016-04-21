package com.protectsoft;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.logging.Logger;

import static org.mockito.Mockito.mock;

public class TestAnswer {
	
	private static Logger log = Logger.getLogger(TestAnswer.class.getName());
	
	private  Answer<TestAnswerModel> answer = null;
	private  TestAnswerModel tam = null;
	private  StackQuestionModel sqm = null;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	
	
	@Before
	public void setUp() throws Exception {
		answer = new Answer<TestAnswerModel>(new TestAnswerModel());
		sqm = new StackQuestionModel();
		answer.with(sqm);
	}
	
	
	@After
	public void tearDown() {
		answer = null;
		tam = null;
	}
	
	

	@Test(expected = NullPointerException.class)
	public void testAnswer() {
		answer = new Answer<TestAnswerModel>(null);
	}
	
	
	@Test
	public void testGetQModel() {
		assertEquals(sqm.getClass(),answer.getQmodel().getClass());
	}
	
	
	@Test
	public void testGetModel() {
		tam = new TestAnswerModel();
		assertEquals(tam.getClass(),answer.getModel().getClass());
	}
	
	
	@Test
	public void testGetAnswersForQuestionURL() throws IOException {
		Question<TestQuestionModel> q = new Question<TestQuestionModel>(new TestQuestionModel());
		
		TestQuestionModel tqm = q.setLang(Lang.JAVA)
				.setQuestion("sort objects")
				.getQuestionResultSet();
		assertEquals(q.getModel().getClass(),tqm.getClass());
		assertNotEquals(null,tqm);
		
		for(int i =0; i < tqm.getQuestions().size(); i++) {
			assertNotNull(tqm.getQuestions().get(i).getUrl());
			log.info("testGetAnswersForQuestionURL():"+tqm.getQuestions().get(i).getUrl());
		}
		
		Answer<TestAnswerModel> a = new Answer<TestAnswerModel>(new TestAnswerModel());
		TestAnswerModel tam = a.getAnswersForQuestionURL(tqm.getQuestions().get(0));
		assertNotNull(tam);
		assertEquals(tam.getClass(),a.getModel().getClass());
		assertNotNull(tam.getAnswers());
		assertNotNull(tam.getQuestionText());
		assertNotNull(tam.getTitle());
		assertNotNull(tam.getUrl());
		
		assertEquals(false,tam.getAnswers().isEmpty());;
		assertEquals(false,tam.getTitle().isEmpty());
		assertEquals(false,tam.getUrl().isEmpty());
		
		
		for(int i =0; i < tam.getAnswers().size(); i++) {
			assertEquals(false,tam.getAnswers().get(i).getUrl().isEmpty());
		}
		
	}
	
	
	

}
