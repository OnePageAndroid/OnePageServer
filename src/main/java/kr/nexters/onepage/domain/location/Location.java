package kr.nexters.onepage.domain.location;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class Location {
	@Column
	private Long locationId;
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
