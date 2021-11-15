package study.com.dto;

import study.com.util.Util;

public class Article extends Dto {
		public static int index = 0;
		public int id;
		public String title;
		public String body;
		public String regDate;
		public int hit;

		public Article(String title, String body) {
			index++;
			this.id = index;
			this.title = title;
			this.body = body;
			this.regDate = Util.getNowDateStr();
		}
		public void increaseHit() {
			this.hit++;
		}
	}
