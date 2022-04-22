package TestReact.k.Dto;

import TestReact.k.Entity.Tbl_WorkGrp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Dto_Workgrp
{
    private long Id;
    private String Title;
    private long prjId; //주인 프로젝트 id
    private long Pid;       //부모카테 id : 0이면 루트, 설정되면 하위
    private int nChild;    // 하위 있으면 갯수, 없으면 0

    @Builder
    public Dto_Workgrp(long prjid, String title, long pid, int ch)
    {
        this.Id = 0;
        this.prjId = 0;
        this.Title  = title;
        this.Pid = pid;
        this.nChild = ch;
    }

    public Tbl_WorkGrp toEntity()
    {
        return Tbl_WorkGrp.builder()
                .id(this.Id)
                .parent(null)
                .title(this.Title)
                .child(this.nChild)
                .Sub(null)
                .prj_id(this.prjId)
                .build();
    }

    //추가
}
