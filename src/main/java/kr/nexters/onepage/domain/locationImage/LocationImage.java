package kr.nexters.onepage.domain.locationImage;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@JoinColumn(name = "locationId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Location location;

	@Column(name = "objectkey")
	private String objectkey;

	@Column(name = "url")
	private String url;

	@Column(name = "name")
	private String name;

	@Column(name = "englishName")
	private String englishName;

	@Column(name = "dayType")
	private DayType dayType;

	@Column(name = "deleted")
	private boolean deleted;

	public static LocationImage of(Long id, Location location, String objectkey, String url, DayType dayType, String englishName){
		return LocationImage.builder()
				.id(id)
				.location(location)
				.objectkey(objectkey)
				.url(url)
				.dayType(dayType)
				.englishName(englishName)
				.build();
	}

	public void deleted(){
		this.deleted=true;
	}
}
