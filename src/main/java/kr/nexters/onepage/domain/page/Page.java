package kr.nexters.onepage.domain.page;

import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.support.Modified;
import kr.nexters.onepage.domain.user.User;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "page")
@Where(clause = "deleted = 0")
public class Page extends Modified {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pageId")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationId")
    @Where(clause = "deleted = 0")
    @NotNull
    private Location location;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @Where(clause = "deleted = 0")
    @NotNull
    private User user;
    @Column
    @NotNull
    private String content;
    @Column
    private boolean deleted;

    public static Page of(Location location, User user, String content) throws SQLException {
        return Page.builder()
            .location(location)
            .user(user)
            .content(content)
            .build();
    }

    public void deleted() {
		this.deleted = true;
	}
}
