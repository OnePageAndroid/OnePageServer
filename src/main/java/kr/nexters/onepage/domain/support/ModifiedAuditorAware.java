package kr.nexters.onepage.domain.support;

import kr.nexters.onepage.domain.common.OnePageConstant;

import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class ModifiedAuditorAware {
	@PreUpdate
	public void setModified(Modified modified) {
		modified.setCreatedAt(LocalDateTime.now());
		modified.setCreatedBy(OnePageConstant.SYSTEM);
	}
}
