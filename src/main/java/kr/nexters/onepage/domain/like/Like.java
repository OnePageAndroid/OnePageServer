package kr.nexters.onepage.domain.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.joda.time.DateTime;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "like")
@Where(clause = "delete = 0")
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "likeId")
	private Long id;
	@Column
	private Long pageId;
	@Column
	private DateTime createdAt;
	@Column
	private String createdBy;
	@Column
	private boolean deleted;
}
