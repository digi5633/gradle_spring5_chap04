package gradle_spring_autowired_study.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import gradle_spring_autowired_study.config.AppCtx;
import gradle_spring_autowired_study.spring.ChangePasswordService;
import gradle_spring_autowired_study.spring.DuplicateMemberException;
import gradle_spring_autowired_study.spring.MemberInfoPrinter;
import gradle_spring_autowired_study.spring.MemberListPrinter;
import gradle_spring_autowired_study.spring.MemberNotFoundException;
import gradle_spring_autowired_study.spring.MemberRegisterService;
import gradle_spring_autowired_study.spring.RegisterRequest;
import gradle_spring_autowired_study.spring.VersionPrinter;
import gradle_spring_autowired_study.spring.WrongIdPasswordException;

public class MainForSpring {

	private static ApplicationContext ctx = null;

	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("명령어를 입력하세요 : ");
			String command = reader.readLine();

			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}

			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if (command.equals("list")) {
				processListCommand();
				continue;
			} else if (command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			} else if (command.equals("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();

		}

	}

	// private static Assembler assembler = new Assembler();

	private static void processVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();

	}

	private static void processInfoCommand(String[] split) {
		if (split.length != 2) {
			printHelp();
			return;
		}

		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(split[1]);

	}

	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법 : ");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println("list");
		System.out.println("info 이메일");
		System.out.println("version");
		System.out.println();
	}

	private static void processChangeCommand(String[] split) {
		if (split.length != 4) {
			printHelp();
			return;
		}

		ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);

		// ChangePasswordService changePwdSvc = assembler.getChangePasswordService();

		try {
			changePwdSvc.changePassword(split[1], split[2], split[3]);
			System.out.println("암호를 변경했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}

	}

	private static void processNewCommand(String[] split) {
		if (split.length != 5) {
			printHelp();
			return;
		}

		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);

		// MemberRegisterService regSvc = assembler.getMemberRegisterService();

		RegisterRequest req = new RegisterRequest();
		req.setEmail(split[1]);
		req.setName(split[2]);
		req.setPassword(split[3]);
		req.setConfirmPassword(split[4]);

		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}

		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일 입니다.\n");
		}

	}

}
