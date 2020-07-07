package ru.otus.akutsev.studtesting.model;

public class Question {

	private final String id;
	private final String text;
	private final String answer;

	public Question(String id, String text, String answer) {
		this.id = id;
		this.text = text;
		this.answer = answer;
	}

	public String getText() {
		return text;
	}

	public String getAnswer() {
		return answer;
	}
}
