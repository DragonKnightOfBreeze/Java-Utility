package com.windea.utility.generator;

public interface TextGen<T> {
	String text();

	default int length() {
		return text().length();
	}

	default boolean isEmpty() {
		return text().isEmpty();
	}

	default boolean isBlank() {
		return text().isBlank();
	}
}
