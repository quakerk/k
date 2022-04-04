package TestReact.k.Svc;

import TestReact.k.Dto.Dto_Test;
import TestReact.k.Entity.TblTest;
import TestReact.k.JpaRepo.TestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class Svc_Test
{
    private final TestRepo repoTest;

    @Transactional
    public long CreateProject(Dto_Test dtoPrj)
    {
        TblTest t = repoTest.save( dtoPrj.toEntity() );
        return t.getId();
    }

    @Transactional
    public long UpdatePrjData(long id, Dto_Test dtoPrj)
    {
        Optional<TblTest> repo = repoTest.findById(id);

        if(!repo.isPresent())
        {
            System.out.print(id+" 데이타 없음");
            return -1;
        }

        repo.get().Update(dtoPrj.getStr(), dtoPrj.getIdx(), dtoPrj.getPid(), dtoPrj.getDate());

        return id;
    }


    public Dto_Test GetPrjData(long id)
    {
        Optional<TblTest> repo = repoTest.findById(id);

        if( !repo.isPresent())
        {
            System.out.print(id+" 데이타 없음");
            return null;
        }

        return new Dto_Test().Response( repo.get());
    }


    public int DeletePrjData(long id)
    {
        Optional<TblTest> repo = repoTest.findById(id);

        if( !repo.isPresent())
        {
            System.out.print(id+" 데이타 없음");
            return -1;
        }

        repoTest.deleteById(id);

        return 1;
    }
}
