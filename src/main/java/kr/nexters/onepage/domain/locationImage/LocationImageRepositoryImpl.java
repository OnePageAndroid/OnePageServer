package kr.nexters.onepage.domain.locationImage;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import kr.nexters.onepage.domain.location.QLocation;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

public class LocationImageRepositoryImpl extends QueryDslRepositorySupport implements LocationImageRepositoryCustom {
	private QLocationImage qLocationImage = QLocationImage.locationImage;
	private QLocation qLocation = QLocation.location;

	public LocationImageRepositoryImpl() {
		super(QLocationImage.class);
	}

	@Override
	public LocationImage findByLocationIdAndDayType(Long locationId, DayType dayType) {
		JPQLQuery<LocationImage> query = from(qLocationImage).innerJoin(qLocationImage.location, qLocation);
		BooleanBuilder whereClause = new BooleanBuilder();
		whereClause.and(qLocationImage.location.id.eq(locationId));
		whereClause.and(qLocationImage.dayType.eq(dayType));
		return query.where(whereClause).fetchFirst();
	}
}