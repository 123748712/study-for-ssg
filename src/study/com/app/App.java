package study.com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import study.com.controller.ArticleController;
import study.com.controller.MemberController;
import study.com.dto.Article;
import study.com.dto.Member;

public class App {

	List<Article> articles;
	List<Member> members;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {

		System.out.println("=== 프로그램 실행 ===");
		Scanner scanner = new Scanner(System.in);

		MemberController memberController = new MemberController(scanner, members);
		ArticleController articleController = new ArticleController(scanner, articles);
		makeTestData();

		while (true) {
			System.out.println("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();

			if (command.equals("member join")) {
				memberController.doJoin();
			} else if (command.equals("article write")) {
				articleController.doWrite();
			} else if (command.startsWith("article list")) {				
				articleController.showList(command);
			} else if (command.startsWith("article detail ")) {
				articleController.showDetail(command);
			} else if (command.startsWith("article modify ")) {
				articleController.doModify(command);
			} else if (command.startsWith("article delete ")) {
				articleController.doDelete(command);
			} else if (command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못된 명령어를 입력하셨습니다.");
			}
		}
		System.out.println("=== 프로그램 종료 ===");
	}

	void makeTestData() {
		articles.add(new Article("제목1", "내용1"));
		articles.add(new Article("제목2", "내용2"));
		articles.add(new Article("제목3", "내용3"));

		System.out.println("테스트 데이터를 생성했습니다.");
	}
}