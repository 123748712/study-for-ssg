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

		System.out.println("=== 프로그램 시작 ===");
		Scanner scanner = new Scanner(System.in);

		makeTestData();

		ArticleController articleController = new ArticleController(scanner, articles);
		MemberController memberController = new MemberController(scanner, members);
		

		while (true)

		{
			System.out.println("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();
			if (command.equals("member join")) {
			memberController.doJoin();
			} else if (command.equals("article write")) {
				articleController.doWrite();
			} else if (command.startsWith("article list")) {
				articleController.doList(command);
			} else if (command.startsWith("article detail ")) {
				articleController.doDetail(command);
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
		articles.add(new Article("제목 1", "내용 1"));
		articles.add(new Article("제목 2", "내용 2"));
		articles.add(new Article("제목 3", "내용 3"));

		System.out.println("Test Article 이 생성되었습니다.");
	}
}