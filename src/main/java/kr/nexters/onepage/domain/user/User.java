package kr.nexters.onepage.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import kr.nexters.onepage.domain.support.Created;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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