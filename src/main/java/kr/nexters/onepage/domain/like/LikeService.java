package kr.nexters.onepage.domain.like;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepository;


}
