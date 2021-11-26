package study.com.dto;

import study.com.util.Util;

public class Article {
	public static int index;
	public int id;
	public String title;
	public String body;
	public int hit;
	public String regDate;

	public Article(String title, String body) {
		this.index++;
		this.id = index;
		this.title = title;
		this.body = body;
		this.regDate = Util.getNowDateStr();
	}

	public void increaseHit() {
		this.hit++;
	}
}