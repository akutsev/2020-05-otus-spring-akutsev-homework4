package ru.otus.akutsev.studtesting.service;

public class QuestionGettingException extends RuntimeException{
	public QuestionGettingException(String errorMessage, Throwable error) {
		super(errorMessage);
	}
}