package kr.nexters.onepage.domain.heart;

public interface HeartRepositoryCustom {
	Heart findByPageIdAndEmail(Long pageId, String email);
}
