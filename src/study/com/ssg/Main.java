package study.com.ssg;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("=== 프로그램 실행 ===");
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();
			
			if(command.equals("article write")) {
				System.out.println("게시글 작성 기능을 구현합니다.");
				
				System.out.println("제목 : ");
				String title = scanner.nextLine();
				
				System.out.println("내용 : ");
				String body = scanner.nextLine();
				
				System.out.println("게시글 작성이 완료되었습니다.");
				continue;
			}
		}
	}
}
