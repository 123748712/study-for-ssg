package study.com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import study.com.util.Util;

public class Main {
	
	static List<Article> articles = new ArrayList<>();
	
	public static void main(String[] args) {

		System.out.println("=== 프로그램 실행 ===");
		Scanner scanner = new Scanner(System.in);
		makeTestData();

		while (true) {
			System.out.println("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();

			if (command.equals("article write")) {
				System.out.println("게시글 작성 기능을 구현합니다.");

				System.out.println("제목 : ");
				String title = scanner.nextLine();

				System.out.println("내용 : ");
				String body = scanner.nextLine();

				Article article = new Article(title, body);
				articles.add(article);

				System.out.println("게시글 작성이 완료되었습니다.");
				continue;
			} else if (command.equals("article list")) {
				System.out.println("게시글 리스트 기능을 구현합니다.");

				System.out.println("제목  |  번호");
				for (Article article : articles) {
					System.out.println(article.title + " | " + article.articleId);
				}
			} else if (command.startsWith("article detail ")) {
				System.out.println("상세게시글 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length > 3) {
					System.out.println("명령어를 잘못 입력하셨습니다.");
					continue;
				}

				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.articleId == foundId) {
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
				foundArticle.increaseHit();
				
				System.out.println("=== 검색된 게시글을 불러옵니다. ===");
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("번호 : " + foundArticle.articleId);
				System.out.println("조회수 : " + foundArticle.hit);
				System.out.println("작성날짜 : " + foundArticle.regDate);
				System.out.println("내용 : " + foundArticle.body);
			} else if (command.startsWith("article modify ")) {
				System.out.println("게시글 수정 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length > 3) {
					System.out.println("명령어를 잘못 입력하셨습니다.");
					continue;
				}

				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.articleId == foundId) {
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

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.println(foundArticle.articleId + "번 게시글이 수정되었습니다.");
			} else if (command.startsWith("article delete ")) {
				System.out.println("게시글 삭제 기능을 구현합니다.");

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length > 3) {
					System.out.println("명령어를 잘못 입력하셨습니다.");
					continue;
				}

				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");

				int foundId = 0;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.articleId == foundId) {
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

				System.out.println(foundArticle.articleId + "번 게시글이 삭제되었습니다.");
			} else if (command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("명령어를 잘못 입력하셨습니다.");
				continue;
			}
		}
		System.out.println("=== 프로그램 종료 ===");
	}

	static void makeTestData() {
		System.out.println("Article Test 데이터를 생성합니다.");
		
		articles.add(new Article("제목 1", "내용 1"));
		articles.add(new Article("제목 2", "내용 2"));		
		articles.add(new Article("제목 3", "내용 3"));
	}
}

class Article {
	static int index;
	String title;
	String body;
	int articleId;
	String regDate;
	int hit;

	Article(String title, String body) {
		this.index++;
		this.articleId = index;
		this.title = title;
		this.body = body;
		this.regDate = Util.getNowDateStr();
	}
	void increaseHit() {
		this.hit++;
	}
}