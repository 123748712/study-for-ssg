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

		ArticleController articleController = new ArticleController(scanner);
		MemberController memberController = new MemberController(scanner);
		Controller controller = null;
		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {
			System.out.println("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();
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
				System.out.println("잘못된 명령어를 입력하셨습니다.");
				continue;
			}
			controller.doAction(command, actionMethod);
		}
		System.out.println("=== 프로그램 종료 ===");
	}
}