package TestReact.k.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Tbl_ProjectSet_WorkCate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "parent_id")
    private Long parent_id;

    @Column(name = "child")
    private int child;

    @Column(name = "node")
    private String node;


    public Tbl_ProjectSet_WorkCate(int id, String title, String path)
    {
        this.id = id;
        this.title = title;
        this.node = path;
        this.parent_id  = 0L;
        this.child = 0;
    }


    public void Update(String title, long pid, int child, String node)
    {
        if(title != null)
            this.title = title;

        if(pid != 0)
            this.parent_id = pid;

        if(child != 0)
            this.child = child;

        if( node != null)
            this.node = node;
    }
}
