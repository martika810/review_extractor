package com.kingstore;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

public class FileStoreUtils {

	public static void saveReview(String fileNamePrefix, Review review) {
		String randomText = UUID.randomUUID().toString().substring(1, 8);
		File file = new File("./reviews/" + fileNamePrefix + randomText + ".txt");

		try {
			FileUtils.writeStringToFile(file, review.toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
