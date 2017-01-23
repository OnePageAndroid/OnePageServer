package kr.nexters.onepage.domain.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class LocalDateRange {
	private LocalDate startDate;
	private LocalDate endDate;

	public static LocalDateRange of(LocalDate startDate, LocalDate endDate) {
		return LocalDateRange.builder()
			.startDate(startDate)
			.endDate(endDate)
			.build();
	}

	public LocalDateTime getStartDateTime() {
		return LocalDateTime.of(startDate, LocalTime.MIN);
	}

	public LocalDateTime getEndDateTime() {
		return LocalDateTime.of(endDate, LocalTime.MAX);
	}
}
