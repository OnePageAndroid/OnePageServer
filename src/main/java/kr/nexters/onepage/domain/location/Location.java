package kr.nexters.onepage.domain.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.joda.time.DateTime;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "location")
@Where(clause = "delete = 0")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "locationId")
	private Long id;
	@Column
	private Double latitude;
	@Column
	private Double longtitude;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private DateTime createdAt;
	@Column
	private DateTime createdBy;
	@Column
	private boolean deleted;
}
