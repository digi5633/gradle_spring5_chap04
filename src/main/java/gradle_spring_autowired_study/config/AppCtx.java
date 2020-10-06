package gradle_spring_autowired_study.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gradle_spring_autowired_study.spring.ChangePasswordService;
import gradle_spring_autowired_study.spring.MemberDao;
import gradle_spring_autowired_study.spring.MemberInfoPrinter;
import gradle_spring_autowired_study.spring.MemberListPrinter;
import gradle_spring_autowired_study.spring.MemberPrinter;
import gradle_spring_autowired_study.spring.MemberRegisterService;
import gradle_spring_autowired_study.spring.MemberSummaryPrinter;
import gradle_spring_autowired_study.spring.VersionPrinter;

@Configuration
public class AppCtx {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}

	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}

	@Bean
	public ChangePasswordService changePwdSvc() {
		return new ChangePasswordService();
	}

	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}

	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}

	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter();
	}

	@Bean
	public MemberInfoPrinter infoPrinter() {
		/*	MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
			infoPrinter.setPrinter(memberPrinter2());
			return infoPrinter();
		*/

		return new MemberInfoPrinter();
	}

	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}

}
