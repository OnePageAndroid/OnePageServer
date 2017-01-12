package kr.nexters.onepage.domain.support;

import kr.nexters.onepage.domain.common.OnePageConstant;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class CreatedAuditorAware {
	@PrePersist
	public void setCreated(Created created) {
		created.setCreatedAt(LocalDateTime.now());
		created.setCreatedBy(OnePageConstant.SYSTEM);
	}
}
