package study.com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import study.com.controller.ArticleController;
import study.com.controller.Controller;
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

		makeTestData();

		MemberController memberController = new MemberController(scanner, members);
		ArticleController articleController = new ArticleController(scanner, articles);
		Controller controller = null;
		while (true) {
			System.out.println("명령어를 입력해주세요 : ");
			String command = scanner.nextLine().trim();
			if (command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
			String[] commandBits = command.split(" ");
			String controllerName = commandBits[0];
			String actionMethod = commandBits[1];
			
			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("명령어를 잘못 입력하셨습니다.");
				continue;
			}
			controller.doAction(command, actionMethod);
		}
		System.out.println("=== 프로그램 종료 ===");
	}

	void makeTestData() {
		System.out.println("Article Test 데이터를 생성합니다.");

		articles.add(new Article("제목 1", "내용 1"));
		articles.add(new Article("제목 2", "내용 2"));
		articles.add(new Article("제목 3", "내용 3"));
	}
}