package study.com.dto;

import study.com.util.Util;

public class Article extends Dto {
	public static int index;
	public int id;
	public String title;
	public String body;
	public int hit;
	public String regDate;
	public int memberId;
	public String memberName;

	public Article(String title, String body, int memberId, String memberName) {
		this.index++;
		this.id = index;
		this.title = title;
		this.body = body;
		this.regDate = Util.getNowDateStr();
		this.memberId = memberId;
		this.memberName = memberName;
	}

	public void increaseHit() {
		this.hit++;
	}
}