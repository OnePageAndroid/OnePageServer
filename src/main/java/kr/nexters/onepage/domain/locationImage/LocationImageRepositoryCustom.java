package kr.nexters.onepage.domain.locationImage;

import java.util.List;

public interface LocationImageRepositoryCustom {

	List<LocationImage> findDayAndLocationId(Long locationId, DayType dayType);
}
