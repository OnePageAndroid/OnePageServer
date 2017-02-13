package kr.nexters.onepage.domain.locationImage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.support.Modified;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "location_image")
@Where(clause = "deleted = 0")
public class LocationImage extends Modified {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "locationImageId")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "locationId")
	@Where(clause = "deleted = 0")
	private Location location;
	@Column
	private String objectKey;
	@Column
	private String url;
	@Column
	private String name;
	@Column
	private String englishName;
	@Column
	@Enumerated(EnumType.STRING)
	private DayType dayType;
	@Column
	private boolean deleted;

	public static LocationImage of(Long id, Location location, String objectKey, String url, DayType dayType){
		return LocationImage.builder()
				.id(id)
				.location(location)
				.objectKey(objectKey)
				.url(url)
				.dayType(dayType).build();
	}

	public void deleted() {
		this.deleted = true;
	}
}