package kr.nexters.onepage.domain.locationImage;

import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.support.Modified;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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
	@Enumerated(value = EnumType.STRING)
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