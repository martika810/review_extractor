package com.kingstore;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class CreateReviewFiles {

	@Test
	public void createReviewFiles() throws IOException {
		List<Review> reviews = new ReviewScanner()
				.findReviewList("https://www.amazon.co.uk/Pandora-Womens-Sterling-Silver-Charm/dp/B00716R4LE/ref=sr_1_13?ie=UTF8&qid=1520182640&sr=8-13");
		for (Review review : reviews) {
			FileStoreUtils.saveReview("r-", review);
		}
	}

}
