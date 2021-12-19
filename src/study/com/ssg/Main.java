package study.com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import study.com.util.Util;

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

				if (articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 | 제목");
				for (Article article : articles) {
					System.out.println(article.articleId + " | " + article.title);
				}
			} else if (command.startsWith("article detail ")) {
				System.out.println("상세 게시글 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length != 3) {
					System.out.println("잘못된 명령어입니다..");
					continue;
				}

				String checkStr = commandBits[2];
				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("잘못된 명령어입니다.");
				}
				Article foundArticle = null;

				for (Article article : articles) {
					if (article.articleId == foundId) {
						foundArticle = article;
					}
				}
				if (foundArticle == null) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}

				System.out.println("제목 : " + foundArticle.title);
				System.out.println("번호 : " + foundArticle.articleId);
				System.out.println("작성날짜 : " + foundArticle.regDate);
				System.out.println("내용 : " + foundArticle.body);
			} else if (command.startsWith("article modify ")) {
				System.out.println("게시글 수정 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length != 3) {
					System.out.println("잘못된 명령어입니다..");
					continue;
				}

				String checkStr = commandBits[2];
				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("잘못된 명령어입니다.");
				}
				Article foundArticle = null;

				for (Article article : articles) {
					if (article.articleId == foundId) {
						foundArticle = article;
					}
				}
				if (foundArticle == null) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}
				System.out.println("제목 : ");
				String title = scanner.nextLine();
				System.out.println("내용 : ");
				String body = scanner.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.println(foundArticle.articleId + "번 게시글이 수정되었습니다.");
			} else if (command.startsWith("article delete")) {
				System.out.println("게시글 삭제 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length != 3) {
					System.out.println("잘못된 명령어입니다..");
					continue;
				}

				String checkStr = commandBits[2];
				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("잘못된 명령어입니다.");
				}
				Article foundArticle = null;

				for (Article article : articles) {
					if (article.articleId == foundId) {
						foundArticle = article;
					}
				}
				if (foundArticle == null) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}
				System.out.println(foundArticle.articleId + "번 게시글이 삭제되었습니다.");

				articles.remove(foundArticle);

			}

			else {

				System.out.println("잘못된 명령어입니다.");
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
	String regDate;

	Article(String title, String body) {
		this.index++;
		this.articleId = index;
		this.title = title;
		this.body = body;
		this.regDate = Util.getNowDateStr();
	}
}