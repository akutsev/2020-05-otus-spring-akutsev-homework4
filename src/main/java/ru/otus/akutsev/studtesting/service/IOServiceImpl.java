package ru.otus.akutsev.studtesting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService{

	private PrintStream printStream;
	private Scanner scanner;

	public IOServiceImpl(@Value("#{ T(java.lang.System).out}") PrintStream printStream,
						 @Value("#{ T(java.lang.System).in}") InputStream inputStream) {
		this.printStream = printStream;
		this.scanner = new Scanner(inputStream);
	}

	@Override
	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public void printString(String string) {
		printStream.print(string);
	}

	@Override
	public String getString() {
		return scanner.nextLine();
	}

}
