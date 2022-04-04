package TestReact.k.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Dto_Workgrp
{
    private int Id;
    private int Uid;
    private String Title;
    private int Pid;    // 0이 아니면 루트, 설정되면 하위
    private boolean isChild;    // 하위 노드 있으면 true

    @Builder
    public Dto_Workgrp(int uid, String title, int pid, boolean ch)
    {
        this.Id = 0;
        this.Uid = uid;
        this.Title  = title;
        this.Pid = pid;
        this.isChild = ch;
    }


}
