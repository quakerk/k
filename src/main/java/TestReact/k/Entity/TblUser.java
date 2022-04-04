package TestReact.k.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Builder
@Entity     // jpa 엔티티임 지정
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")   // user 테이블이랑 맵핑
public class TblUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk, auto_increment로 설정
    @Column(name = "id", nullable = false, unique = true, length = 32)
    private long Uid;

    @Column(name = "name", nullable = false, length = 32, columnDefinition = "")
    private String Name;

    @Column(name = "pw", nullable = false, length = 256, columnDefinition = "")
    private String Pw;

    // 사원번호
    @Column(name = "idnum", nullable = false, length = 32, columnDefinition = "")
    private String IdNum;

    // 생성일 (입사일?)
    @CreationTimestamp
    @Column(name = "joindate", nullable = false, columnDefinition = "")
    private LocalDateTime JoinDate;

    // 퇴사일
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "outdate",  nullable = false)
    private Date OutDate;

    // 활성 유저냐? (0:죽음, 1:살음)
    @Column(name = "live", nullable = false, columnDefinition = "")
    private int Live;

    // 직급
    @Column(name = "lv", nullable = false, columnDefinition = "")
    private int Lv;

    // 로긴 보안 토큰
    @Column(name = "authtkn", columnDefinition = "")
    private int AuthTkn;
}
