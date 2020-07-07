package ru.otus.akutsev.studtesting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.akutsev.studtesting.configs.YamlProps;
import ru.otus.akutsev.studtesting.dao.QuestionsDaoCsv;
import ru.otus.akutsev.studtesting.model.Question;

import java.io.IOException;
import java.util.List;

@Service
public class StudentTestServiceImpl implements StudentTestService{
	private final QuestionsDaoCsv questionsDaoCsv;
	private final YamlProps yamlProps;
	private final MessageSource messageSource;
	private final IOService ioService;

	private static final String START_MESSAGE_BUNDLE_PROPERTY = "start.message";
	private static final String SUCCESS_MESSAGE_BUNDLE_PROPERTY = "success.message";
	private static final String TOO_MUCH_QUESTIONS_MESSAGE_BUNDLE_PROPERTY = "expired.questions";
	private static final String FAIL_MESSAGE_BUNDLE_PROPERTY = "fail.message";

	@Autowired
	public StudentTestServiceImpl(QuestionsDaoCsv questionsDaoCsv, YamlProps yamlProps,
								  MessageSource messageSource, IOService ioService) {
		this.questionsDaoCsv = questionsDaoCsv;
		this.yamlProps = yamlProps;
		this.messageSource = messageSource;
		this.ioService = ioService;
	}

	@Override
	public void startStudentTest() {
		List<Question> questions = questionsDaoCsv.getQuestions();
		int correctAnswersCount = 0;
		String studentAnswer;

		try {
			for (int i =0; i < yamlProps.getQuestionsNumber(); i++) {
				ioService.printString(questions.get(i).getText());
				studentAnswer = ioService.getString();
				if (questions.get(i).getAnswer().equals(studentAnswer)) {
					correctAnswersCount++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			ioService.printString(messageSource.getMessage(TOO_MUCH_QUESTIONS_MESSAGE_BUNDLE_PROPERTY,
					null, yamlProps.getLocale()));
		}

		printResult(correctAnswersCount);
	}

	@Override
	public String getStartMessage() {
		String message = messageSource.getMessage(START_MESSAGE_BUNDLE_PROPERTY,
				new Integer[] {yamlProps.getQuestionsNumber()}, yamlProps.getLocale());
		return message;
	}

	private void printResult(int correctAnswersNumber) {
		String message = correctAnswersNumber >= yamlProps.getRequiredCorrectAnswers()
				? messageSource.getMessage(SUCCESS_MESSAGE_BUNDLE_PROPERTY,
				null, yamlProps.getLocale())
				: messageSource.getMessage(FAIL_MESSAGE_BUNDLE_PROPERTY,
				null, yamlProps.getLocale());
		ioService.printString(message);
	}

}
