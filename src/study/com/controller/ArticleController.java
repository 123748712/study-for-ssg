package study.com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import study.com.dto.Article;

public class ArticleController extends Controller {
	private Scanner scanner;
	private List<Article> articles;

	public ArticleController(Scanner scanner, List<Article> articles) {
		this.scanner = scanner;
		this.articles = articles;
	}
	
	public void doAction(String command, String actionMethod) {
		switch(actionMethod) {
		case "write" :
			doWrite();
			break;
		case "list" :
			showList(command);
			break;
		case "detail" :
			showDetail(command);
			break;
		case "modify" :
			doModify(command);
			break;
		case "delete" :
			doDelete(command);
			break;
			default :
				System.out.println("잘못된 명령어를 입력하셨습니다.");
				break;
		}
	}

	private void doWrite() {
		System.out.println("게시글 작성 기능을 구현합니다.");

		System.out.println("제목 : ");
		String title = scanner.nextLine();

		System.out.println("내용 : ");
		String body = scanner.nextLine();

		Article article = new Article(title, body);
		articles.add(article);

		System.out.println("게시글 작성이 완료되었습니다.");
	}

	private void showList(String command) {
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
				return;
			}
		} else {
			searchedArticles = articles;
		}

		System.out.println("제목  |  번호");
		for (Article article : searchedArticles) {
			System.out.println(article.title + " | " + article.articleId);
		}
	}

	private void showDetail(String command) {
		System.out.println("상세게시글 기능을 구현합니다.");

		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length != 3) {
			System.out.println("명령어를 잘못 입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];

		int foundId = getFoundIdByCheckStr(checkStr);

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

		System.out.println("=== 검색된 게시글을 불러옵니다. ===");
		System.out.println("제목 : " + foundArticle.title);
		System.out.println("번호 : " + foundArticle.articleId);
		System.out.println("조회수 : " + foundArticle.hit);
		System.out.println("작성날짜 : " + foundArticle.regDate);
		System.out.println("내용 : " + foundArticle.body);
	}

	private void doModify(String command) {
		System.out.println("게시글 수정 기능을 구현합니다.");

		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length != 3) {
			System.out.println("명령어를 잘못 입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];

		int foundId = getFoundIdByCheckStr(checkStr);

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
		String title = scanner.nextLine();

		System.out.println("내용 : ");
		String body = scanner.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.println(foundArticle.articleId + "번 게시글이 수정되었습니다.");
	}

	private void doDelete(String command) {
		System.out.println("게시글 삭제 기능을 구현합니다.");

		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length != 3) {
			System.out.println("명령어를 잘못 입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];

		int foundId = getFoundIdByCheckStr(checkStr);

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

		System.out.println(foundArticle.articleId + "번 게시글이 삭제되었습니다.");
	}

	Article getFoundArticleById(int foundId) {
		Article foundArticle = null;

		for (Article article : articles) {
			if (article.articleId == foundId) {
				foundArticle = article;
			}
		}
		return foundArticle;
	}

	int getFoundIdByCheckStr(String checkStr) {
		boolean checkInt = checkStr.matches("-?\\d+");
		int foundId = 0;

		if (checkInt) {
			foundId = Integer.parseInt(checkStr);
		}
		return foundId;
	}

}
