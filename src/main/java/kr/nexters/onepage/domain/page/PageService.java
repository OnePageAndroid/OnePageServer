package kr.nexters.onepage.domain.page;

import com.google.common.collect.Lists;
import kr.nexters.onepage.domain.common.OnePageServiceException;
import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.location.LocationService;
import kr.nexters.onepage.domain.pageImage.PageImageService;
import kr.nexters.onepage.domain.user.User;
import kr.nexters.onepage.domain.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static kr.nexters.onepage.domain.common.NumericConstant.ZERO;

@Slf4j
@Service
public class PageService {
	@Autowired
	private PageRepository pageRepository;
	@Autowired
	private PageImageService pageImageService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private UserService userService;

	@Transactional(readOnly = false)
	public void savePage(Long locationId, String email, String content) {
		Location location = locationService.findById(locationId);
		User user = userService.findByEmail(email);

		try {
			pageRepository.save(Page.of(location, user, content));
		} catch (SQLException e) {
			log.error("savePage : " + e.getMessage());
			throw new OnePageServiceException(e);
		}
	}

	public Page findById(Long pageId) {
		Page page = pageRepository.findOne(pageId);
		if(Objects.isNull(page)) {
			throw new OnePageServiceException("페이지가 존재하지 않습니다.");
		}
		return page;
	}

	public PagesResponseDto findByLocationId(Long locationId, Integer pageNumber, Integer perPageSize) {
		List<Page> pages = pageRepository.findByLocationIdAndPageable(locationId, pageNumber, perPageSize);
		if (CollectionUtils.isNotEmpty(pages) && pages.size() < perPageSize) {
			pages.addAll(pageRepository.findByLocationIdAndPageable(locationId, ZERO, perPageSize - pages.size()));
		}
		return PagesResponseDto.of(
			PageDtoBuilder.transformPagesToDtos(Lists.newArrayList(pages.stream().collect(Collectors.toSet())), pageNumber, (id) -> pageImageService.findByPageId(id)),
			pageNumber,
			perPageSize,
			totalCountByLocationId(locationId));
	}

	public int totalCountByLocationId(Long locationId) {
		return pageRepository.countByLocationId(locationId);
	}

	public PagesResponseDto findByEmail(String email, Integer pageNumber, Integer perPageSize) {
		List<Page> pages = pageRepository.findByEmailAndPageable(email, pageNumber, perPageSize);
		if (CollectionUtils.isNotEmpty(pages) && pages.size() < perPageSize) {
			pages.addAll(pageRepository.findByEmailAndPageable(email, ZERO, perPageSize - pages.size()));
		}
		return PagesResponseDto.of(
			PageDtoBuilder.transformPagesToDtos(Lists.newArrayList(pages.stream().collect(Collectors.toSet())), pageNumber, (id) -> pageImageService.findByPageId(id)),
			pageNumber,
			perPageSize,
			totalCountByEmail(email));
	}

	public int totalCountByEmail(String email) {
		User user = userService.findByEmail(email);
		return pageRepository.countByUserId(user.getId());
	}

	@Transactional
	public void remove(Long pageId){
		Page page = pageRepository.findOne(pageId);
		pageRepository.delete(page);
		pageImageService.removeByPageId(pageId);
	}
}