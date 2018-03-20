package com.kingstore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProcessReviewTest {

	@Test
	public void removeHtml() {
		String processedEmail = ProcessReview.process("Hey! <html> what up </html>");
		assertTrue(processedEmail.equals("hey!   what up  "));
	}

	@Test
	public void removeNumber() {
		String processedEmail = ProcessReview.process("HELLO!Lucene is a simple yet powerful 0289879873 what up ");
		assertEquals(processedEmail, "hello!lucene is a simple yet powerful number what up ");
	}

	@Test
	public void removeHTTPAddress() {
		String processedEmail = ProcessReview.process("HELLO! https://www.jfrog.com/confluence/display/RTF/Artifactory+REST+API what up ");
		assertTrue(processedEmail.equals("hello! httpaddr what up "));
	}

}
