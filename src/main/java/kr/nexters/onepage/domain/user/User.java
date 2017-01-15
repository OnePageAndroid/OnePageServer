package kr.nexters.onepage.domain.user;

import kr.nexters.onepage.domain.support.Created;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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
	private String email;
	@Column
	private boolean deleted;

	public static User of(String email) {
		return User.builder()
			.email(email)
			.build();
	}
}