package kr.nexters.onepage.domain.pageImage;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table
public class PageImage {
    @Column
    private Long pageImageId;

    @Column
    private Long pageId;

    @Column
    private Long locationId;

    @Column
    private String objectKey;

    @Column
    private String name;

    @Column
    private DateTime createdAt;

    @Column
    private String createdBy;

    @Column
    private DateTime modifiedAt;

    @Column
    private String modifiedBy;

    @Column
    private boolean deleted;
}