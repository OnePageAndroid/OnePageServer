package kr.nexters.onepage.domain.locationImage;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import kr.nexters.onepage.domain.location.QLocation;

@Repository
public class LocationImageRepositoryImpl extends QueryDslRepositorySupport implements LocationImageRepositoryCustom {
	private QLocationImage qLocationImage = QLocationImage.locationImage;
	private QLocation qLocation = QLocation.location;

	public LocationImageRepositoryImpl() {
		super(LocationImage.class);
	}

	@Override
	public List<LocationImage> findDayAndLocationId(Long locationId, DayType dayType){
		return from(qLocationImage)
				.join(qLocationImage.location,qLocation)
				.where(qLocationImage.location.id.eq(locationId))
				.where(qLocationImage.dayType.eq(dayType))
				.fetch();
	}
}
