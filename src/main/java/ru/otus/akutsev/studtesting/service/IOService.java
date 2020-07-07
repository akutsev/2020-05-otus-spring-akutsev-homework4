package ru.otus.akutsev.studtesting.service;

import java.io.PrintStream;
import java.util.Scanner;

public interface IOService {
	void printString(String string);
	String getString();
	void setPrintStream(PrintStream printStream);
	void setScanner(Scanner scanner);
}
