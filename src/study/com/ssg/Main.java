package study.com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("=== 프로그램 실행 ===");
		Scanner scanner = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.println("명령어를 입력해주세요 :");
			String command = scanner.nextLine();

			if (command.equals("article write")) {
				System.out.println("게시글 작성 기능을 구현합니다.");

				System.out.println("제목 : ");
				String title = scanner.nextLine();

				System.out.println("내용 : ");
				String body = scanner.nextLine();

				Article article = new Article(title, body);

				articles.add(article);

				System.out.println(article.id + "게시글 작성이 완료되었습니다.");
			} else if (command.equals("article list")) {
				System.out.println("게시글 리스트 기능을 구현합니다.");

				System.out.println("번호  |  제목");

				for (Article article : articles) {
					System.out.println(article.id + " | " + article.title);
				}
			} else if (command.startsWith("article detail ")) {
				System.out.println("상세게시글 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length != 3) {
					System.out.println("잘못된 명령여를 입력하셨습니다.");
					continue;
				}

				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("잘못된 명령어를 입력하셨습니다");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == foundId) {
						foundArticle = article;
					} else {
						System.out.println("검색된 게시글이 존재하지 않습니다.");
						continue;
					}
				}
				if (foundArticle == null) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}

				System.out.println("제목 : " + foundArticle.title);
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("내용 : " + foundArticle.body);
			} else if (command.startsWith("article modify ")) {
				System.out.println("게시글 수정 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length != 3) {
					System.out.println("잘못된 명령여를 입력하셨습니다.");
					continue;
				}

				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("잘못된 명령어를 입력하셨습니다");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == foundId) {
						foundArticle = article;
					} else {
						System.out.println("검색된 게시글이 존재하지 않습니다.");
						continue;
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

				title = foundArticle.title;
				body = foundArticle.body;

				System.out.println(foundArticle.id + "번 게시글이 수정되었습니다.");
			} else if (command.startsWith("article delete ")) {
				System.out.println("게시글 삭제 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length != 3) {
					System.out.println("잘못된 명령여를 입력하셨습니다.");
					continue;
				}

				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("잘못된 명령어를 입력하셨습니다");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == foundId) {
						foundArticle = article;
					} else {
						System.out.println("검색된 게시글이 존재하지 않습니다.");
						continue;
					}
				}
				if (foundArticle == null) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}

				articles.remove(foundArticle);

				System.out.println(foundArticle.id + "번 게시글이 삭제되었습니다.");
			} else if (command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못된 명령어를 입력하셨습니다.");
				continue;
			}
		}
		System.out.println("=== 프로그램 종료 ===");
	}
}

class Article {
	static int index;
	int id;
	String title;
	String body;

	Article(String title, String body) {
		this.index++;
		this.id = index;
		this.title = title;
		this.body = body;
	}
}