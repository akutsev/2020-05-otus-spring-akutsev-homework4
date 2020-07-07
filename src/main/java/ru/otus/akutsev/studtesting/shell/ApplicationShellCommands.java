package ru.otus.akutsev.studtesting.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.akutsev.studtesting.service.StudentTestService;

import java.io.IOException;

@ShellComponent
public class ApplicationShellCommands {

	private StudentTestService studentTestService;
	private String okToSaveResults;

	@Autowired
	public ApplicationShellCommands(StudentTestService studentTestService) {
		this.studentTestService = studentTestService;
	}

	@ShellMethod(value = "Hello command", key = {"H", "Hello"})
	public String start() {
		return studentTestService.getStartMessage();
	}

	@ShellMethod(value = "Testing student", key = {"T", "Test"})
	public void studentTest(@ShellOption(defaultValue = "true") String okToSaveResults) {
		this.okToSaveResults = okToSaveResults;
		studentTestService.startStudentTest();
	}

	@ShellMethod(value = "Save results", key = {"S", "Save"})
	@ShellMethodAvailability(value = "isStudentAgreeToSaveHisResults")
	public String sayThanks() {
		return "Thanks you help us to collect data";
	}

	private Availability isStudentAgreeToSaveHisResults() {
		return  okToSaveResults.equals("true") ? Availability.available()
				: Availability.unavailable("You didn't allow to save data - no problem");
	}
}
