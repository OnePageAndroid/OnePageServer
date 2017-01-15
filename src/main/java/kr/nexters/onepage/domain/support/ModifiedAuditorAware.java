package kr.nexters.onepage.domain.support;

import kr.nexters.onepage.domain.common.OnePageConstant;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class ModifiedAuditorAware {
	@PrePersist
	@PreUpdate
	public void setModified(Modified modified) {
		modified.setModifiedAt(LocalDateTime.now());
		modified.setModifiedBy(OnePageConstant.SYSTEM);
	}
}
