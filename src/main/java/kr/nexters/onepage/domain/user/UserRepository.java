package kr.nexters.onepage.domain.user;

import kr.nexters.onepage.domain.common.OnePageRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends OnePageRepository<User, Long> {
	User findByEmail(String email);
}
