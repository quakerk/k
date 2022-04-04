package TestReact.k.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "testtbl")
public class TblTest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "str")
    private String str;

    @Column(name = "idx")
    private int idx;

    @Column(name = "p_id")
    private int pid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;


    public void Update(String str, int idx, int pid, Date date)
    {
        if(str != null)
            this.str = str;

        if(idx != -0xFFFF)
            this.idx = idx;

        if(pid != -0xFFFF)
            this.pid = pid;

        if(date != null)
            this.date = date;
    }

}

