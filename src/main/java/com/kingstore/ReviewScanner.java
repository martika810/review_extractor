package com.kingstore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReviewScanner {

	public final static String AMAZON_SITE = "https://www.amazon.co.uk";
	public final static String PAGE_SIZE_SUFFIX = "&pageSize=50";

	public List<Review> findReviewList(final String itemLink) throws IOException {

		Document doc = Jsoup.connect(itemLink).get();
		Elements linkToAllReviews = doc.select("#reviews-medley-footer a.a-link-emphasis");
		String fragmentLinkToAllReview = linkToAllReviews.get(0).attr("href");

		fragmentLinkToAllReview = AMAZON_SITE + fragmentLinkToAllReview;

		int numberPages = getNumberPages(fragmentLinkToAllReview);

		List<Review> listAllReviews = new ArrayList<Review>();
		for (int indexPage = 1; indexPage < numberPages + 1; indexPage++) {
			String pageToOpen = fragmentLinkToAllReview + updateNumberPageSuffix(indexPage) + PAGE_SIZE_SUFFIX;
			Document pageDocument = Jsoup.connect(pageToOpen).get();
			Elements reviewContainer = pageDocument.select("#cm_cr-review_list").select("div.review");

			for (Element reviewNode : reviewContainer) {
				float rating = extractRatingScore(reviewNode.select("i.review-rating span").text());
				String reviewText = reviewNode.select("span.review-text").text();
				listAllReviews.add(new Review(rating, reviewText));
			}
		}

		return listAllReviews;
	}

	public static int getNumberPages(final String itemPageURL) throws IOException {
		Document doc = Jsoup.connect(itemPageURL + PAGE_SIZE_SUFFIX).get();
		String textNumberPages = doc.select("#cm_cr-pagination_bar li.page-button a").last().text();
		return Integer.parseInt(textNumberPages);
	}

	private static String updateNumberPageSuffix(int pageNumber) {
		return "&pageNumber=" + String.valueOf(pageNumber);
	}

	public static float extractRatingScore(final String ratingText) {
		return Float.valueOf(ratingText.split(" ")[0]);
	}
}
