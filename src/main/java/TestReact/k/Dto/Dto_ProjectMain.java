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
                .build();
    }

    public Dto_ProjectMain Response(TblProjectMain enti)
    {
        return this;
    }

    public void Update()
    {

    }
}
