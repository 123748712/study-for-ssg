package study.com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import study.com.container.Container;
import study.com.dto.Article;
import study.com.service.ArticleService;

public class ArticleController extends Controller {
	private Scanner scanner;
	private ArticleService articleService;

	public ArticleController(Scanner scanner) {
		this.scanner = scanner;
		this.articleService = new ArticleService();
	}

	public void doAction(String command, String actionMethod) {
		switch (actionMethod) {
		case "write":
			doWrite(command);
			break;
		case "list":
			showList(command);
			break;
		case "detail":
			showDetail(command);
			break;
		case "modify":
			break;
		case "delete":
			break;
		default:
			System.out.println("명령어를 잘못 입력하셨습니다.");
			break;
		}
	}

	private void doWrite(String command) {
		System.out.println("게시글 작성 기능을 구현합니다.");
		
		command = command.trim();
		String[] commandBits = command.split(" ");
		
		if(commandBits.length != 2) {
			System.out.println("잘못된 명령어를 입력하셨습니다.");
			return;
		}
		
		if(loginedMember == null) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		System.out.println("제목 : ");
		String title = scanner.nextLine();

		System.out.println("내용 : ");
		String body = scanner.nextLine();

		Article article = new Article(title, body, loginedMember.memberId, loginedMember.name);

		articleService.add(article);

		System.out.println(article.id + "번 게시글 작성이 완료되었습니다.");
	}

	private void showList(String command) {
		System.out.println("게시글 리스트 기능을 구현합니다.");

		String searchKeyword = command.substring("article list".length()).trim();

		List<Article> searchedArticles = articleService.getSearchedArticlesByKeyword(searchKeyword);

		if (searchedArticles.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		}

		System.out.println("번호  |  제목");

		for (Article article : searchedArticles) {
			System.out.println(article.id + " | " + article.title);
		}
	}

	private void showDetail(String command) {
		System.out.println("상세게시글 기능을 구현합니다.");
		
		if(loginedMember == null) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length != 3) {
			System.out.println("잘못된 명령여를 입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];

		int foundId = articleService.getFoundIdByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("잘못된 명령어를 입력하셨습니다");
			return;
		}

		Article foundArticle = articleService.getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("검색된 게시글이 존재하지 않습니다.");
			return;
		}

		foundArticle.increaseHit();

		System.out.println("제목 : " + foundArticle.title);
		System.out.println("번호 : " + foundArticle.id);
		System.out.println("작성자 : " + foundArticle.memberName);
		System.out.println("조회수 : " + foundArticle.hit);
		System.out.println("작성날짜 : " + foundArticle.regDate);
		System.out.println("내용 : " + foundArticle.body);
	}

	private void doModify(String command) {
		System.out.println("게시글 수정 기능을 구현합니다.");
			
		if(loginedMember == null) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length != 3) {
			System.out.println("잘못된 명령여를 입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];

		int foundId = articleService.getFoundIdByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("잘못된 명령어를 입력하셨습니다");
			return;
		}

		Article foundArticle = articleService.getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("검색된 게시글이 존재하지 않습니다.");
			return;
		}

		System.out.println("제목 : ");
		String title = scanner.nextLine();

		System.out.println("내용 : ");
		String body = scanner.nextLine();

		title = foundArticle.title;
		body = foundArticle.body;

		System.out.println(foundArticle.id + "번 게시글이 수정되었습니다.");
	}

	private void doDelete(String command) {
		System.out.println("게시글 삭제 기능을 구현합니다.");
		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length != 3) {
			System.out.println("잘못된 명령여를 입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];

		int foundId = articleService.getFoundIdByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("잘못된 명령어를 입력하셨습니다");
			return;
		}

		Article foundArticle = articleService.getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("검색된 게시글이 존재하지 않습니다.");
			return;
		}

		articleService.remove(foundArticle);

		System.out.println(foundArticle.id + "번 게시글이 삭제되었습니다.");
	}
}