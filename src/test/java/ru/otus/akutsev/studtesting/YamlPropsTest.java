package ru.otus.akutsev.studtesting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@ConfigurationProperties(prefix = "testing-students")
public class YamlPropsTest {

	private String questionsBasePath;
	private Integer questionsNumber;
	private Integer requiredCorrectAnswers;
	private String successMessage;
	private String failMessage;
	private String noMoreQuestionsMessage;
	private Locale locale;

	public String getNoMoreQuestionsMessage() {
		return noMoreQuestionsMessage;
	}

	public void setNoMoreQuestionsMessage(String noMoreQuestionsMessage) {
		this.noMoreQuestionsMessage = noMoreQuestionsMessage;
	}

	public Locale getLocale() {
		return locale;
	}

	public String getQuestionsBasePath() {
		return questionsBasePath;
	}

	public void setQuestionsBasePath(String questionsBasePath) {
		this.questionsBasePath = questionsBasePath;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void setQuestionsNumber(Integer questionsNumber) {
		this.questionsNumber = questionsNumber;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	public Integer getQuestionsNumber() {
		return questionsNumber;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public Integer getRequiredCorrectAnswers() {
		return requiredCorrectAnswers;
	}

	public void setRequiredCorrectAnswers(Integer requiredCorrectAnswers) {
		this.requiredCorrectAnswers = requiredCorrectAnswers;
	}
}
