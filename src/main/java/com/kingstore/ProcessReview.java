package com.kingstore;

import java.util.Arrays;
import java.util.List;

import org.tartarus.snowball.ext.PorterStemmer;

public class ProcessReview {

	public static String process(String reviewContent) {
		String processedReview = reviewContent;
		processedReview = processedReview.toLowerCase();
		processedReview = processedReview.replaceAll("<[^<>]+>", " ");
		processedReview = processedReview.replaceAll("[0-9]+", "number");
		processedReview = processedReview.replaceAll("(http|https)://[^\\s]*", "httpaddr");
		processedReview = processedReview.replaceAll("[^\\s]+@[^\\s]+", "emailaddr");
		processedReview = processedReview.replaceAll("[$]+", "dollar");
		processedReview = processedReview.replaceAll("@\\$\\/#\\.\\-:&\\*\\+=\\[\\]\\?!\\(\\)\\{\\}\\,'\\\">_<;%", "");

		return processedReview;
	}

	private static String stemSingleWord(String word) {
		PorterStemmer stem = new PorterStemmer();
		stem.setCurrent(word);
		stem.stem();
		return stem.getCurrent();

	}

	public static String stem(String text) {
		StringBuffer result = new StringBuffer();
		List<String> allwords = Arrays.asList(text.split(" "));
		for (String word : allwords) {
			String stemmedWord = stem(word);
			result.append(stemmedWord).append(" ");
		}
		return result.toString();
	}

}
