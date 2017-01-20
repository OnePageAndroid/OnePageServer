package kr.nexters.onepage.domain.page;

import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.support.Modified;
import kr.nexters.onepage.domain.user.User;
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
