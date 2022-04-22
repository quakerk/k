package TestReact.k.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @Setter
    @Column(name = "node")
    private String node;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_pid")
    @Setter
    private Tbl_ProjectSet_WorkCate parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Tbl_ProjectSet_WorkCate> SubCate = new ArrayList<>();


    public Tbl_ProjectSet_WorkCate(final String name, final Long pid, final Tbl_ProjectSet_WorkCate p)
    {
        this.id = 0;
        this.title = name;
        this.parent_id = pid;
        this.parent = p;
        this.child = 0;
        this.node = null;
    }

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
