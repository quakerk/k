package TestReact.k.Svc;

import TestReact.k.Dto.Dto_ProjectSet_WorkCate;
import TestReact.k.Entity.Tbl_ProjectSet_WorkCate;
import TestReact.k.JpaRepo.Repo_ProjectSet_WorkCate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class Svc_ProjectSet
{
    private final Repo_ProjectSet_WorkCate repoPrj;
    private final EntityManager EttMng;


    @Transactional
    public long CreateNewCate(Dto_ProjectSet_WorkCate dtoPrj)
    {
        Tbl_ProjectSet_WorkCate t = repoPrj.save( dtoPrj.toEntity() );
        return t.getId();
    }

    @Transactional
    public long AddSubCate(Tbl_ProjectSet_WorkCate p, Dto_ProjectSet_WorkCate dtoPrj)
    {
        // 부모 id로 pid 설정해서 하나 등록시키고
        // 그 부모의 sub를 1로 업뎃 시켜야 함.

        Tbl_ProjectSet_WorkCate t = repoPrj.save( dtoPrj.toEntity() );
        t.setParent(p);
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

        repo.get().Update(dto.getTitle(), 0, repo.get().getChild(), null);

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
        List<Tbl_ProjectSet_WorkCate> repo  = repoPrj.Dsl_FindCateAll();

        if(repo.size() == 0)
            return null;

        return repo;
    }


    // id 삭제
    public List<Tbl_ProjectSet_WorkCate> DeleteItem(long id)
    {
        repoPrj.deleteById(id);
        List<Tbl_ProjectSet_WorkCate> repo  = repoPrj.Dsl_FindCateAll();

        if(repo.size() == 0)
            return null;

        return repo;
    }

//    @Transactional(readOnly = true)
//    public List<Tbl_ProjectSet_WorkCate> GetCateTreeAll()
//    {
//        StoredProcedureQuery spq = EttMng.createNamedStoredProcedureQuery( Tbl_ProjectSet_WorkCate.NQ_GetCateAll );
//        spq.setParameter("path", 0);
//        spq.execute();
//
//        @SuppressWarnings("unchecked")
//        List<Tbl_ProjectSet_WorkCate> res = spq.getResultList();
//        return res;
//    }
}
