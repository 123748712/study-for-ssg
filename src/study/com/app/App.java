package study.com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import study.com.dto.Article;

public class App {

	List<Article> articles = new ArrayList<>();

	public void start() {
		System.out.println("=== 프로그램 실행 ===");
		Scanner scanner = new Scanner(System.in);
		makeTestData();

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

				System.out.println(article.id + "번 게시글 작성이 완료되었습니다.");
			} else if (command.startsWith("article list")) {
				System.out.println("게시글 리스트 기능을 구현합니다.");

				if (articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}

				String searchKeyword = command.substring("article list".length()).trim();

				List<Article> searchedArticles = new ArrayList<>();

				if (searchKeyword.length() > 0) {
					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							searchedArticles.add(article);
						}
					}
					if (searchedArticles.size() == 0) {
						System.out.println("검색된 게시글이 존재하지 않습닌다.");
						continue;
					}
				} else {
					searchedArticles = articles;
				}
				System.out.println("번호  |  제목");

				for (Article article : searchedArticles) {
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

				int foundId = getFoundIdByCheckStr(checkStr);

				if (foundId == 0) {
					System.out.println("잘못된 명령어를 입력하셨습니다");
					continue;
				}

				Article foundArticle = getFoundArticleById(foundId);

				if (foundArticle == null) {
					System.out.println("검색된 게시글이 존재하지 않습니다.");
					continue;
				}

				foundArticle.increaseHit();

				System.out.println("제목 : " + foundArticle.title);
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("조회수 : " + foundArticle.hit);
				System.out.println("작성날짜 : " + foundArticle.regDate);
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

				int foundId = getFoundIdByCheckStr(checkStr);

				if (foundId == 0) {
					System.out.println("잘못된 명령어를 입력하셨습니다");
					continue;
				}

				Article foundArticle = getFoundArticleById(foundId);

				if (foundArticle == null) {
					System.out.println("검색된 게시글이 존재하지 않습니다.");
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

				int foundId = getFoundIdByCheckStr(checkStr);

				if (foundId == 0) {
					System.out.println("잘못된 명령어를 입력하셨습니다");
					continue;
				}

				Article foundArticle = getFoundArticleById(foundId);

				if (foundArticle == null) {
					System.out.println("검색된 게시글이 존재하지 않습니다.");
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

	Article getFoundArticleById(int foundId) {
		Article foundArticle = null;

		for (Article article : articles) {
			if (article.id == foundId) {
				foundArticle = article;
			}
		}
		return foundArticle;
	}

	int getFoundIdByCheckStr(String checkStr) {
		int foundId = 0;
		boolean checkInt = checkStr.matches("-?\\d+");

		if (checkInt) {
			foundId = Integer.parseInt(checkStr);
		}
		return foundId;
	}

	void makeTestData() {
		System.out.println("게시글이 생성되었습니다.");

		articles.add(new Article("제목 1", "내용 1"));
		articles.add(new Article("제목 2", "내용 2"));
		articles.add(new Article("제목 3", "내용 3"));
	}
}