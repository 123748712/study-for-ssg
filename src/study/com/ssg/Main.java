package study.com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("=== 프로그램 시작 ===");
		Scanner scanner = new Scanner(System.in);
		int articleId = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.println("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();

			if (command.equals("article write")) {
				System.out.println("게시글 작성 기능을 구현합니다.");
				articleId++;
				int id = articleId;

				System.out.println("제목 : ");
				String title = scanner.nextLine();

				System.out.println("내용 : ");
				String body = scanner.nextLine();

				System.out.println(id + "번 게시글이 작성되었습니다.");
				System.out.println("번호 : " + id);
				System.out.println("제목 : " + title);
				System.out.println("내용 : " + body);

				Article article = new Article(title, body);

				articles.add(article);

			} else if (command.equals("article list")) {
				System.out.println("게시글 리스트 기능을 구현합니다.");

				System.out.println("제목  |  내용  |  조회수");
				for (Article article : articles) {
					System.out.println(article.id + " | " + article.title + " | " + article.hit);
				}
			} else if (command.startsWith("article detail ")) {
				System.out.println("상세페이지 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");
				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("숫자만 입력해주세요.");
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == foundId) {
						foundArticle = article;
					}
				}
				if (foundArticle == null) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}
				
				foundArticle.increaseHit();
				
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				System.out.println("조회수 : " + foundArticle.hit);
			} else if (command.startsWith("article modify ")) {
				System.out.println("게시글 수정 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");
				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("숫자만 입력해주세요.");
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == foundId) {
						foundArticle = article;
					}
				}
				if (foundArticle == null) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}

				System.out.println("제목 : ");
				foundArticle.title = scanner.nextLine();
				System.out.println("내용 : ");
				foundArticle.body = scanner.nextLine();
				System.out.println(foundArticle.id + "번 게시글이 수정되었습니다.");
			} else if (command.startsWith("article delete ")) {
				System.out.println("게시글 삭제 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");
				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("숫자만 입력해주세요.");
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == foundId) {
						foundArticle = article;
					}
				}
				if (foundArticle == null) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}

				articles.remove(foundArticle);
				
				System.out.println(foundArticle.id + "번 게시글이 삭제되었습니다.");
			}
		}
	}
}

class Article {
	static int index = 0;
	int id;
	String title;
	String body;
	String regDate;
	int hit;

	Article(String title, String body) {
		index++;
		this.id = index;
		this.title = title;
		this.body = body;
	}
	void increaseHit() {
		this.hit++;
	}
}