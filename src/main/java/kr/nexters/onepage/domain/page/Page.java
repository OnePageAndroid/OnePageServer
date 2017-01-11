package kr.nexters.onepage.domain.page;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Blob;

@Getter
@Setter
@Entity
@Table
public class Page {
    @Column
    private Long pageId;

    @Column
    private Long locationId;

    @Column
    private Long userId;

    @Column
    private Blob content;

    @Column
    private DateTime createAt;

    @Column
    private String createBy;

    @Column
    private DateTime modifiedAt;

    @Column
    private String modifiedBy;

    @Column
    private boolean deleted;
}
