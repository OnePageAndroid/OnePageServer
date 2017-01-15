package kr.nexters.onepage.domain.heart;

import kr.nexters.onepage.domain.page.Page;
import kr.nexters.onepage.domain.support.Created;
import kr.nexters.onepage.domain.user.User;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "heart")
@Where(clause = "deleted = 0")
public class Heart extends Created {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "heartId")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pageId")
	private Page page;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;
	@Column
	private boolean deleted;

	public static Heart of(Page page, User user) {
		return Heart.builder()
			.page(page)
			.user(user)
			.build();
	}

	public void deleted() {
		this.deleted = true;
	}
}
