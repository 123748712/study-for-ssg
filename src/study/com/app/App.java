package study.com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import study.com.dto.Article;
import study.com.dto.Member;

public class App {

	List<Article> articles = new ArrayList<>();
	List<Member> members = new ArrayList<>();

	public void start() {

		System.out.println("=== 프로그램 실행 ===");
		Scanner scanner = new Scanner(System.in);

		makeTestData();

		while (true)

		{
			System.out.println("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();
			if (command.equals("member join")) {
				System.out.println("회원가입 기능을 구현합니다.");

				String loginId = null;

				while (true) {
					System.out.println("회원가입 ID : ");
					loginId = scanner.nextLine();
					
					if (isJoinable(loginId)) {
						System.out.println("이미 존재하는 ID 입니다.");
						continue;
					}
					break;
				}

				String loginPw = null;
				String checkLoginPw = null;

				while (true) {
					System.out.println("회원가입 PW : ");
					loginPw = scanner.nextLine();

					System.out.println("회원가입 PW 확인 : ");
					checkLoginPw = scanner.nextLine();

					if (!loginPw.equals(checkLoginPw)) {
						System.out.println("PW를 다시한번 확인해주세요.");
						continue;
					}
					break;
				}

				System.out.println("회원가입 이름 : ");
				String name = scanner.nextLine();

				Member member = new Member(loginId, loginPw, name);

				members.add(member);

				System.out.println(member.name + "님 회원가입이 완료되었습니다.");

			} else if (command.equals("article write")) {
				System.out.println("게시글 작성 기능을 구현합니다.");

				System.out.println("제목 : ");
				String title = scanner.nextLine();

				System.out.println("내용 : ");
				String body = scanner.nextLine();

				Article article = new Article(title, body);
				articles.add(article);

				System.out.println("게시글 작성이 완료되었습니다.");
				continue;
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
						System.out.println("검색된 게시글이 존재하지 않습니다.");
						continue;
					}
				} else {
					searchedArticles = articles;
				}

				System.out.println("제목  |  번호");
				for (Article article : searchedArticles) {
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

				int foundId = getFoundIdByCheckStr(checkStr);

				if (foundId == 0) {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = getFoundArticleById(foundId);

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

				int foundId = getFoundIdByCheckStr(checkStr);

				if (foundId == 0) {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = getFoundArticleById(foundId);

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

				int foundId = getFoundIdByCheckStr(checkStr);

				if (foundId == 0) {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = getFoundArticleById(foundId);

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

	void makeTestData() {
		System.out.println("Article Test 데이터를 생성합니다.");

		articles.add(new Article("제목 1", "내용 1"));
		articles.add(new Article("제목 2", "내용 2"));
		articles.add(new Article("제목 3", "내용 3"));
	}

	boolean isJoinable(String loginId) {
		boolean isJoinable = false;

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				isJoinable = true;
				break;
			}
		}
		return isJoinable;
	}
}