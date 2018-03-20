package com.kingstore;

public class Review {
	private float score;
	private String text;

	public Review(float score, String text) {
		super();
		this.score = score;
		this.text = text;
	}

	public float getScore() {
		return score;
	}

	public String getText() {
		return text;
	}

	public Review setText(String text) {
		return new Review(this.score, text);
	}

	public String toFile() {
		return String.valueOf(score) + "\r\n " + text + "\r\n ";
	}

}
