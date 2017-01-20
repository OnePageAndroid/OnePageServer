package kr.nexters.onepage.domain.heart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import kr.nexters.onepage.domain.page.Page;
import kr.nexters.onepage.domain.support.Created;
import kr.nexters.onepage.domain.user.User;
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
@Table(catalog = "onepage", name = "heart")
@Where(clause = "deleted = 0")
public class Heart extends Created {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "heartId")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pageId")
	@Where(clause = "deleted = 0")
	private Page page;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	@Where(clause = "deleted = 0")
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
