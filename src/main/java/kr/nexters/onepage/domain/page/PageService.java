package kr.nexters.onepage.domain.page;

import kr.nexters.onepage.domain.common.OnePageServiceException;
import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.location.LocationService;
import kr.nexters.onepage.domain.pageImage.PageImageDto;
import kr.nexters.onepage.domain.pageImage.PageImageService;
import kr.nexters.onepage.domain.user.User;
import kr.nexters.onepage.domain.user.UserService;
import kr.nexters.onepage.domain.util.LocalDateRange;
import kr.nexters.onepage.domain.util.functional.F2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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
	public PageDto savePage(Long locationId, String email, String content) {
		Location location = locationService.findById(locationId);
		User user = userService.findByEmail(email);
		Page page;
		try {
			page = pageRepository.save(Page.of(location, user, content));
		} catch (SQLException e) {
			log.error("savePage : " + e.getMessage());
			throw new OnePageServiceException(e);
		}
		List<PageImageDto> pageImageDto = pageImageService.findByPageId(page.getId());
		return PageDto.of(page, pageImageDto, 0, 0);
	}

	public Page findById(Long pageId) {
		Page page = pageRepository.findOne(pageId);
		if (Objects.isNull(page)) {
			throw new OnePageServiceException("페이지가 존재하지 않습니다.");
		}
		return page;
	}

	public PagesResponseDto findCircleByLocationId(Long locationId, Integer pageIndex, Integer perPageSize) {
		F2<Integer, Integer, List<Page>> callback = (num, size) -> pageRepository.findByLocationIdAndPageable(locationId, num, size);
		return findCommonCircleBy(pageIndex, perPageSize, totalCountByLocationId(locationId), callback);
	}

	public PagesResponseDto findCircleByEmail(String email, Integer pageIndex, Integer perPageSize) {
		F2<Integer, Integer, List<Page>> callback = (num, size) -> pageRepository.findByEmailAndPageable(email, num, size);
		return findCommonCircleBy(pageIndex, perPageSize, totalCountByEmail(email), callback);
	}

	public PagesResponseDto findCircleByEmailAndHeart(String email, Integer pageIndex, Integer perPageSize) {
		F2<Integer, Integer, List<Page>> callback = (num, size) -> pageRepository.findByHeartAndPageable(email, num, size);
		return findCommonCircleBy(pageIndex, perPageSize, totalCountByEmailAndHeart(email), callback);
	}

	public int totalCountByEmailAndHeart(String email) {
		return pageRepository.countByEmailAndHeart(email);
	}

	/**
	 * example
	 * @param pageIndex
	 * @param perPageSize
	 * @param totalSize
	 * @param callback
	 * @return
	 */
	private PagesResponseDto findCommonCircleBy(Integer pageIndex, Integer perPageSize, Integer totalSize,
		F2<Integer, Integer, List<Page>> callback) {
		// 1. 0 미만일 경우. 2. totalSize 초과할 경우. -> 페이지 범위 내로 변경.
		pageIndex = (totalSize + pageIndex) % totalSize;
		List<Page> pages = callback.apply(pageIndex, perPageSize);

		// 조회한 페이지 사이즈가 per 페이지 사이즈보다 작으면 0페이지부터 조회하여 더함.
		if (CollectionUtils.isNotEmpty(pages) && (perPageSize - pages.size() > 0)
			&& ((perPageSize - pages.size()) % (totalSize - pages.size() + 1)) > 0) {
			pages.addAll(callback.apply(ZERO, ((perPageSize - pages.size()) % (totalSize - pages.size() + 1))));
		}
		return PagesResponseDto.of(
			PageDtoBuilder.transformPagesToDtos(pages, pageIndex, totalSize, (id) -> pageImageService.findByPageId(id)),
			pageIndex,
			perPageSize,
			totalSize);
	}

	public int totalCountByLocationId(Long locationId) {
		return pageRepository.countByLocationId(locationId);
	}

	public int totalCountByEmail(String email) {
		User user = userService.findByEmail(email);
		return pageRepository.countByUserId(user.getId());
	}

	@Transactional(readOnly = false)
	public void removeById(Long pageId) {
		removePage(pageId);
		pageImageService.removeByPageId(pageId);
	}

	private void removePage(Long pageId) {
		Page page = pageRepository.findOne(pageId);
		page.deleted();
		pageRepository.save(page);
	}

	public int countByLocationIdAndRange(Long locationId, LocalDateRange range) {
		return (int) pageRepository.countByLocationIdAndRange(locationId, range);
	}
}