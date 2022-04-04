package TestReact.k.Dto;

import TestReact.k.Entity.TblTest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class Dto_Test
{
    private Long id;
    private String str;
    private int idx;
    private int pid;
    private Date date;

    @Builder
    public Dto_Test(String str, int idx, int pid, Date date)
    {
        this.str = str;
        this.idx = idx;
        this.pid = pid;
        this.date = date;
    }

    public TblTest toEntity()
    {
        return TblTest.builder()
                .str(this.str)
                .idx(this.idx)
                .pid(this.pid)
                .date(this.date)
                .build();
    }

    public Dto_Test Response(TblTest enti)
    {
        this.id = enti.getId();
        this.str = enti.getStr();
        this.idx = enti.getIdx();
        this.pid = enti.getPid();
        this.date = enti.getDate();

        return this;
    }

    public void Update(String str, int idx, int pid, Date date)
    {
        if(str != null)
            this.str = str;

        if(idx != -1)
            this.idx = idx;

        if(pid != -1)
            this.pid = pid;

        if(date != null)
            this.date = date;
    }
}
