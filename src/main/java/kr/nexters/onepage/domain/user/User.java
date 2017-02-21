package kr.nexters.onepage.domain.user;

import kr.nexters.onepage.domain.support.Created;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "user")
@Where(clause = "deleted = 0")
public class User extends Created {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private Long id;
	@Column
	@NotNull
	@Size(min=1,message="이메일을 입력하세요.")
	@Pattern(regexp="^(\\w+)(((\\.?)(\\w+))*)[@](((\\w+)[.])+)(\\w{2,3})$",message="이메일 형식만 가능합니다.")
	private String email;
	@Column
	private boolean deleted;

	public static User of(String email) {
		return User.builder()
			.email(email)
			.build();
	}
}