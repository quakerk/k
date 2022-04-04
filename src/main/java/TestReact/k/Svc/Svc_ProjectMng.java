package TestReact.k.Svc;

import TestReact.k.Dto.Dto_ProjectMain;
import TestReact.k.Entity.TblProjectMain;
import TestReact.k.Entity.TblTest;
import TestReact.k.JpaRepo.ProjectMngRepo;
import TestReact.k.JpaRepo.TestRepo;
import TestReact.k.Dto.Dto_Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class Svc_ProjectMng
{
    private final ProjectMngRepo repoPrj;

    @Transactional
    public long CreateProject(Dto_ProjectMain dtoPrj)
    {
        TblProjectMain t = repoPrj.save( dtoPrj.toEntity() );
        return t.getId();
    }

    @Transactional
    public long UpdatePrjData(long id, Dto_ProjectMain dtoPrj)
    {
        Optional<TblProjectMain> repo = repoPrj.findById(id);

        if(!repo.isPresent())
        {
            System.out.print(id+" 데이타 없음");
            return -1;
        }

        //repo.get().Update(dtoPrj.getStr(), dtoPrj.getIdx(), dtoPrj.getPid(), dtoPrj.getDate());

        return id;
    }


    public Dto_ProjectMain GetPrjData(long id)
    {
        Optional<TblProjectMain> repo = repoPrj.findById(id);

        if( !repo.isPresent())
        {
            System.out.print(id+" 데이타 없음");
            return null;
        }

        return new Dto_ProjectMain().Response( repo.get());
    }


    public int DeletePrjData(long id)
    {
        Optional<TblProjectMain> repo = repoPrj.findById(id);

        if( !repo.isPresent())
        {
            System.out.print(id+" 데이타 없음");
            return -1;
        }

        repoPrj.deleteById(id);

        return 1;
    }
}

