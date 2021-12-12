package study.com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();
		System.out.println("=== 프로그램 시작 ===");

		while (true) {
			System.out.print("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();

			if (command.equals("article write")) {
				System.out.println("게시글 작성 기능을 구현합니다.");

				System.out.print("제목 : ");
				String title = scanner.nextLine();
				System.out.print("내용 : ");
				String body = scanner.nextLine();

				Article article = new Article(title, body);

				articles.add(article);

				System.out.println(article.articleId + "번 게시글 작성이 완료되었습니다.");
			} else if (command.equals("article list")) {
				System.out.println("게시글 리스트 기능을 구현합니다.");

				if(articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 | 제목");
				for (Article article : articles) {
					System.out.println(article.articleId + " | " + article.title);
				}
			} else {
				System.out.println("잘못된 명령어입니다");
				continue;
			}
		}
	}
}

class Article {
	static int index;
	int articleId;
	String title;
	String body;

	Article(String title, String body) {
		this.index++;
		this.articleId = index;
		this.title = title;
		this.body = body;
	}
}