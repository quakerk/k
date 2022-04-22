package TestReact.k.Dto;

import TestReact.k.Entity.TblProjectMain;
import TestReact.k.Entity.Tbl_ProjectSet_WorkCate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class Dto_ProjectSet_WorkCate
{
    private long Id;
    private String Title;
    private long ParentId;
    private int Child;   // 자식이 있으면 자식수, 아니면 0
    private String Node;    // 카테고리 노드 패쓰


    @Builder
    public Dto_ProjectSet_WorkCate(String title, long pid, int sub, String node)
    {
        this.Id = 0;
        this.Title = title;
        this.ParentId = pid;
        this.Child = sub;

        if(pid != 0)
            this.ParentId = pid;

        if( sub != 0)
            this.Child = sub;

        this.Node = node;
    }

    @Builder
    public Dto_ProjectSet_WorkCate(long id, String title, String node)
    {
        this.Id = id;
        this.Title = title;
        this.ParentId = 0;
        this.Child = 0;
        this.Node = node;
    }


    public Tbl_ProjectSet_WorkCate toEntity()
    {
        return Tbl_ProjectSet_WorkCate.builder()
                .title(this.Title)
                .parent_id(this.ParentId)
                .child(this.Child)
                .node(this.Node)
                .id(0)
                .build();
    }

    public Dto_ProjectSet_WorkCate Response(Tbl_ProjectSet_WorkCate enti)
    {
        this.Id = enti.getId();
        this.Title = enti.getTitle();
        this.ParentId = enti.getParent_id();
        this.Child = enti.getChild();
        this.Node   = enti.getNode();

        return this;
    }

    public void Update()
    {

    }
}
