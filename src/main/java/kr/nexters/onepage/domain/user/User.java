package kr.nexters.onepage.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class User {
	@Column
	private Long userId;
	@Column
	private String email;
	@Column
	private DateTime createdAt;
	@Column
	private String createdBy;
	@Column
	private boolean deleted;
}
