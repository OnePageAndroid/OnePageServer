package kr.nexters.onepage.domain.pageImage;

import java.util.Map;

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
import kr.nexters.onepage.domain.page.Page;
import kr.nexters.onepage.domain.support.Modified;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "page_image")
@Where(clause = "deleted = 0")
public class PageImage extends Modified {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pageImageId")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pageId")
    @Where(clause = "deleted = 0")
    @NotNull
    private Page page;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationId")
    @Where(clause = "deleted = 0")
    @NotNull
    private Location location;
    @Column
    @NotNull
    private String objectKey;
    @Column
    @NotNull
    private String url;
    @Column
    @NotNull
    private String name;
    @Column
    private boolean deleted;

    public static PageImage of(Page page, Map<String, String> uploadInfo) {
        return PageImage.builder()
            .page(page)
            .location(page.getLocation())
            .objectKey(uploadInfo.get("public_id"))
            .url(uploadInfo.get("url"))
            .name(uploadInfo.get("name"))
            .build();
    }

    public void deleted() {
		this.deleted = true;
	}
}