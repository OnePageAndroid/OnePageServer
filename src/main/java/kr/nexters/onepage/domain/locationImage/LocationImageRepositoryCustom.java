package kr.nexters.onepage.domain.locationImage;

public interface LocationImageRepositoryCustom {
	LocationImage findByLocationIdAndDayType(Long locationId, DayType dayType);
}
