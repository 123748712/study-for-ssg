package study.com.dto;

public class Article extends Dto{

		public static int index;
		public int id;
		public String title;
		public String body;
		public int hit;

		public Article(String title, String body) {
			this.index++;
			this.id = index;
			this.title = title;
			this.body = body;
		}

		public void increaseHit() {
			this.hit++;
		}
	}