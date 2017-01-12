package kr.nexters.onepage.domain.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "page")
@Where(clause = "delete = 0")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pageId")
    private Long id;
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
