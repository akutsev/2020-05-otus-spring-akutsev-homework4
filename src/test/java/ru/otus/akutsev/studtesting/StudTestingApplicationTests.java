package ru.otus.akutsev.studtesting;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.akutsev.studtesting.service.IOService;
import ru.otus.akutsev.studtesting.service.IOServiceImpl;
import ru.otus.akutsev.studtesting.service.StudentTestService;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса с поднятием контекста")
@SpringBootTest
@EnableConfigurationProperties(YamlPropsTest.class)
class StudTestingApplicationTests {

	private ByteArrayOutputStream outputStream;
	private InputStream inputStream;

	@Autowired
	StudentTestService studentTestService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	IOService ioService;

	@BeforeEach
	private void setStreams() {
		outputStream = new ByteArrayOutputStream();
	}

	@DisplayName("Тест вывода вопросов и результата")
	@Test
	void studentTestServiceTest_questionsMoreThanInBase() throws IOException {
		String input = "1147" + "\nbla-bla" + "\nbla-bla" + "\nbla-bla";
		inputStream = new ByteArrayInputStream(input.getBytes());
		ioService.setScanner(new Scanner(inputStream));
		ioService.setPrintStream(new PrintStream(outputStream));

		studentTestService.startStudentTest();

		String expectedOutput = "When Moscow city was founded?"
				+ "When Saint Petersberg city was founded?" + "How many regions there are in Russia?"
				+ "What year did the first Woodstock festival was?"
				+ "No more question in base. Change questions number setting"
				+ "You failed :-(";

		String actualOutput = outputStream.toString();

		assertEquals(expectedOutput, actualOutput);
	}

}
