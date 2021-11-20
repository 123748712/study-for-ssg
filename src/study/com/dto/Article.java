package study.com.dto;

import study.com.util.Util;

public class Article extends Dto {
	public static int index;
	public String title;
	public String body;
	public int articleId;
	public String regDate;
	public int hit;

	public Article(String title, String body) {
		this.index++;
		this.articleId = index;
		this.title = title;
		this.body = body;
		this.regDate = Util.getNowDateStr();
	}

	public void increaseHit() {
		this.hit++;
	}
}
