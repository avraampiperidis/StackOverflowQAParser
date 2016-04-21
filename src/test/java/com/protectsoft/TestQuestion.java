package com.protectsoft;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


public class TestQuestion {

	private static Logger log = Logger.getLogger(TestQuestion.class.getName());

	private  Question<TestQuestionModel> question = null;
	private  TestQuestionModel tqm = null;
	
	@BeforeClass
	public static void setUpBeforeClass()  {
	}

	
	@Before
	public void setUp()  {
		question = new Question<TestQuestionModel>(new TestQuestionModel());
	}
	
	@After
	public void tearDown() {
		question = null;
		tqm = null;
	}

	
	@Test(expected = NullPointerException.class)
	public void testQuestion() {
		Question<TestQuestionModel> qm = new Question<TestQuestionModel>(null);
	}
	
	
	@Test
	public void testGetModel() {
		tqm = new TestQuestionModel();
		assertEquals(question.getModel().getClass(),tqm.getClass());
	}
	
	
	@Test
	public void testGetQuestion() {
		String q = "how to sort objects";
		question.setQuestion(q);
		assertEquals(q,question.getQuestion());
	}
	
	
	@Test
	public void testGetLang() {
		Lang lang = Lang.JAVA;
		question.setLang(lang);
		assertEquals(lang,question.getLang());
	}
	
	
	@Test
	public void testGetQuestionResultSet() throws IOException  {
		
		String q = "sort list";
		question.setQuestion(q);
		assertEquals(q,question.getQuestion());
		Lang lang = Lang.JAVASCRIPT;
		question.setLang(lang);
		assertEquals(lang,question.getLang());
		
		tqm = mock(TestQuestionModel.class);
		
		List<StackQuestionModel> stackqm = mock(List.class);
		when(stackqm.size()).thenReturn(10);
		tqm.setStackQuestionModel(stackqm);
		when(tqm.getQuestions()).thenReturn(stackqm);
		
		for(int i =0; i < 10; i++) {
			StackQuestionModel sqm = mock(StackQuestionModel.class);
			when(sqm.getNumOfAnswers()).thenReturn(4);
			when(sqm.getTitle()).thenReturn("title"+i);
			when(sqm.getUrl()).thenReturn("http://"+i);
			when(tqm.getQuestions().get(i)).thenReturn(sqm);
		}
		
		
		for(int i =0; i < 10; i++) {
		    assertEquals(4,tqm.getQuestions().get(i).getNumOfAnswers());	
		    assertEquals("title"+i,tqm.getQuestions().get(i).getTitle());
		    assertEquals("http://"+i,tqm.getQuestions().get(i).getUrl());
		}
		
		
		tqm = question.getQuestionResultSet();
		assertEquals(tqm.getClass(),question.getModel().getClass());
		assertNotNull(tqm);
		assertNotNull(tqm.getQuestions());
		assertEquals(false,tqm.getQuestions().isEmpty());
		
		for(int i =0; i < tqm.getQuestions().size(); i++) {
			assertEquals(false,tqm.getQuestions().get(i).getUrl().isEmpty());
			log.info("testGetQuestionResultSet:URL:"+tqm.getQuestions().get(i).getUrl());
		}
		
	}
	
	

}
