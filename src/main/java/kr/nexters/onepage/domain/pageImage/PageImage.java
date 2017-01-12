package kr.nexters.onepage.domain.pageImage;

import kr.nexters.onepage.domain.support.Modified;
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
@Table(catalog = "onepage", name = "page_image")
@Where(clause = "delete = 0")
public class PageImage extends Modified {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pageImageId")
    private Long id;
    @Column
    private Long pageId;
    @Column
    private Long locationId;
    @Column
    private String objectKey;
    @Column
    private String name;
    @Column
    private boolean deleted;
}