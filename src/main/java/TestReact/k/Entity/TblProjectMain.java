package TestReact.k.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
public class TblProjectMain
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "workgrp_pid")
    private int wgrp_pid;

    @Column(name = "cate_pid")
    private int cate_pid;

    @Column(name = "info")
    private String info;

    @Column(name = "s3_path")
    private String filepath;

    @Column(name = "img_path")
    private String imgpath;

    @Column(name = "pgrs")
    private int pgrs;

    @Column(name = "admin_uid")
    private long admin_uid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startdate")
    private Date startdate;


    public TblProjectMain(String title, int wgrpid, int cateid, int pgrs, int adminid,
                          String info, String fp, String imgp, Date date)
    {
        this.id         = 0;
        this.title      = title;
        this.wgrp_pid   = wgrpid;
        this.cate_pid   = cateid;
        this.info       = info;
        this.filepath   = fp;
        this.imgpath    = imgp;
        this.pgrs       = pgrs;
        this.admin_uid  = adminid;
        this.startdate  = date;
    }
}

