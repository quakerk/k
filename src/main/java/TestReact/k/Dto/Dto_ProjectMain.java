package TestReact.k.Dto;

import TestReact.k.Entity.TblProjectMain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class Dto_ProjectMain
{
    private long Id;
    private String Title;
    private int WgrpPid;
    private int CatePid;
    private String Info;
    private String FilePath;
    private String ImgPaht;
    private int Pgrs;
    private long AdminUid;
    private Date   Date;


    @Builder
    public Dto_ProjectMain(String title, int wgrpid, int cateid, int pgrs, int adminid,
                           String info, String fp, String imgp, Date date)
    {
        this.Id     = 0;
        this.Title  = title;
        this.WgrpPid    = wgrpid;
        this.CatePid    = cateid;
        this.Info       = info;
        this.FilePath   = fp;
        this.ImgPaht    = imgp;
        this.Pgrs       = pgrs;
        this.AdminUid   = adminid;
        this.Date       = date;
    }

    public TblProjectMain toEntity()
    {
        return TblProjectMain.builder()
                .title(this.Title)
                .wgrp_pid(this.WgrpPid)
                .cate_pid(this.CatePid)
                .info(this.Info)
                .filepath(this.FilePath)
                .imgpath(this.ImgPaht)
                .pgrs(this.Pgrs)
                .admin_uid(this.AdminUid)
                .startdate(this.Date)
                .build();
    }


    public Dto_ProjectMain Response(TblProjectMain enti)
    {
        this.Id     = enti.getId();
        this.Title  = enti.getTitle();
        this.WgrpPid = enti.getWgrp_pid();
        this.CatePid = enti.getCate_pid();
        this.Info   = enti.getInfo();
        this.FilePath   = enti.getFilepath();
        this.ImgPaht    = enti.getImgpath();
        this.Pgrs   = enti.getPgrs();
        this.AdminUid   = enti.getAdmin_uid();
        this.Date   = enti.getStartdate();

        return this;
    }


    public void Update()
    {

    }
}
