package kr.nexters.onepage.domain.support;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners({ ModifiedAuditorAware.class })
public class Modified extends Created {
	@Column
	private LocalDateTime modifiedAt;
	@Column
	private String modifiedBy;
}