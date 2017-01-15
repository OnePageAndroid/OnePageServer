package kr.nexters.onepage.domain.user;

import kr.nexters.onepage.domain.support.Created;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
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
}