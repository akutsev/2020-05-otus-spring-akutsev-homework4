package ru.otus.akutsev.studtesting.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import ru.otus.akutsev.studtesting.configs.YamlProps;
import ru.otus.akutsev.studtesting.model.Question;
import ru.otus.akutsev.studtesting.service.QuestionGettingException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestionsDaoCsv {

	private final YamlProps yamlProps;

	@Autowired
	public QuestionsDaoCsv(YamlProps yamlProps) {
		this.yamlProps = yamlProps;
	}

	private static final String CSV_SPLITERATOR = ";";
	private static final int COLUMN_NUM_QUESTION_ID = 0;
	private static final int COLUMN_NUM_QUESTION_TEXT = 1;
	private static final int COLUMN_NUM_QUESTION_ANSWER = 2;

	public List<Question> getQuestions() {
		List<Question> output = new ArrayList<>();
		List<String> rawData = readRawData();

		try {
			output = rawData.stream()
					.map(this::rowMapper)
					.collect(Collectors.toList());
		} catch (RuntimeException e) {
			throw new QuestionGettingException("Wrong questions base file format, check it", e);
		}

		return output;
	}

	public List<String> readRawData() {
		List<String> output = new ArrayList<>();
		String csvString;

		try {
			File questionsBase = ResourceUtils.getFile(yamlProps.getQuestionsBasePath());
			BufferedReader reader = new BufferedReader(new FileReader(questionsBase));

			while ((csvString = reader.readLine()) != null) {
				output.add(csvString);
			}
		} catch (IOException e) {
			throw new QuestionGettingException("Error reading csv file", e);
		}

		return output;
	}

	private Question rowMapper(String csvString) {
		String[] spitedCstString = csvString.split(CSV_SPLITERATOR);
		String id = spitedCstString[COLUMN_NUM_QUESTION_ID];
		String text = spitedCstString[COLUMN_NUM_QUESTION_TEXT];
		String answer = spitedCstString[COLUMN_NUM_QUESTION_ANSWER];

		return new Question(id, text, answer);
	}

}
