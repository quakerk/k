package TestReact.k.Entity;

import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workgrp")
public class Tbl_WorkGrp
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "prj_id")
    private long prj_id;

    @Column(name = "title")
    private String title;

//    @Column(name = "work_pid")
//    private long parent_id;

    @Column(name = "child")
    private int child;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_pid")
    @Setter
    private Tbl_WorkGrp parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Tbl_WorkGrp> Sub = new ArrayList<>();


    public Tbl_WorkGrp(final String name, final Tbl_WorkGrp p)
    {
        this.title = name;
        this.parent = p;
    }
}
