package study.com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import study.com.dto.Article;
import study.com.dto.Member;

public class ArticleController {
	private Scanner scanner;
	private List<Article> articles;

	public ArticleController(Scanner scanner, List<Article> articles) {
		this.scanner = scanner;
		this.articles = articles;
	}

	public void doWrite() {
		System.out.println("게시글 작성 기능을 구현합니다.");

		System.out.println("제목 : ");
		String title = scanner.nextLine();

		System.out.println("내용 : ");
		String body = scanner.nextLine();

		Article article = new Article(title, body);

		articles.add(article);

		System.out.println(article.id + "번 게시글이 작성되었습니다.");
		System.out.println("번호 : " + article.id);
		System.out.println("제목 : " + title);
		System.out.println("내용 : " + body);
	}

	public void showList(String command) {
		System.out.println("게시글 리스트 기능을 구현합니다.");

		if (articles.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
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
				System.out.println("검색된 게시글이 존재하지 않습니다.");
			}
		} else {
			searchedArticles = articles;
		}

		System.out.println("제목  |  내용  |  조회수");
		for (Article article : searchedArticles) {
			System.out.println(article.id + " | " + article.title + " | " + article.hit);
		}
	}

	public void showDetail(String command) {
		System.out.println("상세페이지 기능을 구현합니다.");

		command = command.trim();
		String[] commandBits = command.split(" ");
		String checkStr = commandBits[2];

		int foundId = getfoundArticleByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		}

		foundArticle.increaseHit();

		System.out.println("번호 : " + foundArticle.id);
		System.out.println("제목 : " + foundArticle.title);
		System.out.println("내용 : " + foundArticle.body);
		System.out.println("조회수 : " + foundArticle.hit);
		System.out.println("작성날짜 : " + foundArticle.regDate);
	}

	public void doModify(String command) {
		System.out.println("게시글 수정 기능을 구현합니다.");

		command = command.trim();
		String[] commandBits = command.split(" ");
		String checkStr = commandBits[2];

		int foundId = getfoundArticleByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		}
		System.out.println("제목 : ");
		foundArticle.title = scanner.nextLine();
		System.out.println("내용 : ");
		foundArticle.body = scanner.nextLine();
		System.out.println(foundArticle.id + "번 게시글이 수정되었습니다.");
	}

	public void doDelete(String command) {
		System.out.println("게시글 삭제 기능을 구현합니다.");

		command = command.trim();
		String[] commandBits = command.split(" ");
		String checkStr = commandBits[2];

		int foundId = getfoundArticleByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		}

		articles.remove(foundArticle);

		System.out.println(foundArticle.id + "번 게시글이 삭제되었습니다.");
	}

	int getfoundArticleByCheckStr(String checkStr) {
		boolean checkInt = checkStr.matches("-?\\d+");

		int foundId = 0;

		if (checkInt) {
			foundId = Integer.parseInt(checkStr);
		}
		return foundId;
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
}
