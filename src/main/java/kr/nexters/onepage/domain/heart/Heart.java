package kr.nexters.onepage.domain.heart;

import kr.nexters.onepage.domain.support.Created;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "heart")
@Where(clause = "delete = 0")
public class Heart extends Created {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "heartId")
	private Long id;
	@Column
	private Long pageId;
	@Column
	private boolean deleted;
}
