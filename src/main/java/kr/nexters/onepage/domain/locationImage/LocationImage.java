package kr.nexters.onepage.domain.locationImage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

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
@Table(catalog = "onepage", name = "locationImage")
@Where(clause = "deleted = 0")
public class LocationImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "locationImageId")
	private Long id;

	@Column(name = "locationId")
	@NotNull
	private Long locationId;

	@Column(name = "objectkey")
	@NotNull
	private String objectkey;

	@Column(name = "url")
	@NotNull
	private String url;

	@Column(name = "이름")
	@NotNull
	private String name;

	enum Weather{
		맑음,
		흐림
	}

	@Column(name = "weather")
	@NotNull
	private Weather weather;

	@Column(name = "deleted")
	private boolean deleted;

	public static LocationImage of(Long id, Long locationId, String objectkey, String url, Weather weather){
		return LocationImage.builder()
				.id(id)
				.locationId(locationId)
				.objectkey(objectkey)
				.url(url)
				.weather(weather).build();
	}
}
