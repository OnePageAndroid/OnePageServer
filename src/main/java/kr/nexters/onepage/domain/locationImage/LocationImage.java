package kr.nexters.onepage.domain.locationImage;

import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.support.Modified;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@ManyToOne
	private Location location;

	@Column(name = "objectkey")
	@NotNull
	private String objectkey;

	@Column(name = "url")
	@NotNull
	private String url;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "weatherType")
	@NotNull
	private WeatherType weatherType;

	@Column(name = "deleted")
	private boolean deleted;

	public static LocationImage of(Long id, Location location, String objectkey, String url, WeatherType weatherType){
		return LocationImage.builder()
				.id(id)
				.location(location)
				.objectkey(objectkey)
				.url(url)
				.weatherType(weatherType).build();
	}

	public void deleted(){
		this.deleted=true;
	}
}
