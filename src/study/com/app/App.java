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

	public void start() {

		System.out.println("=== 프로그램 시작 ===");
		Scanner scanner = new Scanner(System.in);

		ArticleController articleController = new ArticleController(scanner);
		MemberController memberController = new MemberController(scanner);
		Controller controller = null;
		
		articleController.makeTestData();
		memberController.makeTestData();
		while (true) {
			System.out.println("명령어를 입력해주세요 : ");
			String command = scanner.nextLine().trim();

			if (command.equals("system exit")) {
				System.out.println("잘못된 명령어를 입력하셨습니다.");
				break;
			}
			String[] commandBits = command.split(" ");
			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];

			if (!actionMethodName.startsWith("article list")) {
				if (controllerName == "article" && commandBits.length != 3) {
					System.out.println("명령어를 잘못 입력하셨습니다.");
					continue;
				}
			}
			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("명령어를 잘못 입력하셨습니다.");
				continue;
			}
			controller.doAction(command, actionMethodName);
		}
		System.out.println("=== 프로그램 종료 ===");
	}
}