package com.kingstore;

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
		PorterStemmer stem = new PorterStemmer();
		stem.setCurrent(processedReview);
		stem.stem();
		processedReview = stem.getCurrent();

		return processedReview;
	}

}
