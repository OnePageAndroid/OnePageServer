package kr.nexters.onepage.domain.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
