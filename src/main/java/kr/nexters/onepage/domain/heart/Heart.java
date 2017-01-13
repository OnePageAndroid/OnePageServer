package kr.nexters.onepage.domain.heart;

import kr.nexters.onepage.domain.support.Created;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "heart")
@Where(clause = "delete = 0")
public class Heart extends Created {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "heartId")
	private Long id;
	@Column
	private Long pageId;
	@Column
	private Long userId;
	@Column
	private boolean deleted;

	public static Heart of(Long pageId, Supplier<Long> userInfo) {
		return Heart.builder()
			.pageId(pageId)
			.userId(userInfo.get())
			.build();
	}

	public void deleted() {
		this.deleted = true;
	}
}
