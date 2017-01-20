package kr.nexters.onepage.domain.location;

import com.google.common.collect.Lists;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

public class LocationRepositoryImpl extends QueryDslRepositorySupport implements LocationRepositoryCustom {
	private QLocation qLocation = QLocation.location;

	public LocationRepositoryImpl() {
		super(Location.class);
	}

	@Override public List<Location> rangeByLatLng(Double latitude, Double longitude) {
		// TODO 거리 100m 이내 범위 장소 필터하여 조회하는 쿼리 구현.
		return Lists.newArrayList();
	}
}