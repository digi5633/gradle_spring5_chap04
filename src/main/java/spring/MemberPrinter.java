package spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrinter {

	private DateTimeFormatter dateTimeFormatter;

	/* 
		 @Autowired(required = false)
		 private DateTimeFormatter dateTimeFormatter;
	 */

	/*	 
		@Autowired
		private Optional<DateTimeFormatter> formatterOpt;
	*/

	/*	 
		@Autowired
		@Nullable
		private DateTimeFormatter dateTimeFormatter;
	*/

	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}

	public void print(Member member) {
		// DateTimeFormatter dateTimeFormatter = formatterOpt.orElse(null);

		if (dateTimeFormatter == null) {
			System.out.printf("회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %tF\n", member.getId(), member.getEmail(),
					member.getName(), member.getRegisterDateTime());
		} else {
			System.out.printf("회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %s\n", member.getId(), member.getEmail(),
					member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
		}

	}

	@Autowired(required = false)
	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

	/*
		@Autowired
			public void setDateTimeFormatter(Optional<DateTimeFormatter> formatterOpt) {
				if (formatterOpt.isPresent()) {
					this.dateTimeFormatter = formatterOpt.get();
				} else {
					this.dateTimeFormatter = null;
				}
		
			}
	*/

	/*
		@Autowired
		public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
			this.dateTimeFormatter = dateTimeFormatter;
		}
	*/

}
