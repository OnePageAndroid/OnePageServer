package kr.nexters.onepage.domain.pageImage;

import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.page.Page;
import kr.nexters.onepage.domain.support.Modified;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
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
    private Page page;
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
}