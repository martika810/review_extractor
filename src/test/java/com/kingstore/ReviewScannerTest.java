package com.kingstore;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class ReviewScannerTest {

	private ReviewScanner scanner = new ReviewScanner();

	@Test
	public void if_LinkIsGood_thenOK() throws IOException {

		List<Review> listOfReviews = scanner
				.findReviewList("https://www.amazon.co.uk/Pandora-Womens-Sterling-Silver-Charm/dp/B00716R4LE/ref=sr_1_13?ie=UTF8&qid=1520182640&sr=8-13");
		assertTrue(listOfReviews.size() == 433);

	}

	@Test
	public void extractRatingScore() {

		assertTrue(Float.valueOf(3.0F).equals(ReviewScanner.extractRatingScore("3.0 out of 5 stars")));
	}

}
