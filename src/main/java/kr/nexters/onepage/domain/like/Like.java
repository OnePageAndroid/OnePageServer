package kr.nexters.onepage.domain.like;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
@Where(clause = "delete = 0")
public class Like {
	@Column
	private Long likeId;
	@Column
	private Long pageId;
	@Column
	private DateTime createdAt;
	@Column
	private String createdBy;
	@Column
	private boolean deleted;
}
