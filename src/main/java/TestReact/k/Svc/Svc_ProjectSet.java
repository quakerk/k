package TestReact.k.Svc;

import TestReact.k.Dto.Dto_ProjectMain;
import TestReact.k.Dto.Dto_ProjectSet_WorkCate;
import TestReact.k.Dto.Dto_Test;
import TestReact.k.Entity.TblProjectMain;
import TestReact.k.Entity.TblTest;
import TestReact.k.Entity.Tbl_ProjectSet_WorkCate;
import TestReact.k.JpaRepo.ProjectMngRepo;
import TestReact.k.JpaRepo.Repo_ProjectSet_WorkCate;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class Svc_ProjectSet
{
    private final Repo_ProjectSet_WorkCate repoPrj;

    @Transactional
    public long CreateNewCate(Dto_ProjectSet_WorkCate dtoPrj)
    {
        Tbl_ProjectSet_WorkCate t = repoPrj.save( dtoPrj.toEntity() );
        return t.getId();
    }

    @Transactional
    public long AddSubCate(Dto_ProjectSet_WorkCate dtoPrj)
    {
        // 부모 id로 pid 설정해서 하나 등록시키고
        // 그 부모의 sub를 1로 업뎃 시켜야 함.
        Tbl_ProjectSet_WorkCate t = repoPrj.save( dtoPrj.toEntity() );
        return t.getId();
    }


    @Transactional
    public long UpdateCateById(long id, Dto_ProjectSet_WorkCate dto)
    {
        Optional<Tbl_ProjectSet_WorkCate> repo = repoPrj.findById(id);

        if(!repo.isPresent())
        {
            System.out.print(id+" 데이타 없음");
            return -1;
        }

        repo.get().Update(dto.getTitle(), dto.getParentId(), dto.getChild(), dto.getNode());

        return id;
    }


    // 1개 가져오기
    public Dto_ProjectSet_WorkCate GetCateData(long id)
    {
        Optional<Tbl_ProjectSet_WorkCate> repo = repoPrj.findById(id);

        if( !repo.isPresent())
        {
            System.out.print(id+" 데이타 없음");
            return null;
        }

        return new Dto_ProjectSet_WorkCate().Response( repo.get() );
    }


    // 1개 가져오기
    public List<Tbl_ProjectSet_WorkCate> GetCateAll()
    {
//       List<Tbl_ProjectSet_WorkCate> repo = repoPrj.findAll();
        List<Tbl_ProjectSet_WorkCate> repo =  repoPrj.Dsl_FindAll();//repoPrj.Jpa_FindAll();

        if(repo.size() == 0)
            return null;

//        "select * from tbl_cate as node" +
//                "left join (select id from tbl_cate) as sub" +
//                "where node.id == sub.pid"
        // 카테고리별로 분류
        try
        {
            JSONObject j = new JSONObject();
            j.put("id", repo.get(0).getId());
            j.put("title", repo.get(0).getTitle());
            j.put("pid", repo.get(0).getParent_id());
            j.put("child", repo.get(0).getChild());
            j.put("sub", j);
        }
        catch (Exception e)
        {
            System.out.print("error : "+e.getMessage());
        }

        return repo;
    }
}
