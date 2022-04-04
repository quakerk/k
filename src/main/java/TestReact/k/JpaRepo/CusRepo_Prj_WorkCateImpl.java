package TestReact.k.JpaRepo;

import TestReact.k.Entity.Tbl_ProjectSet_WorkCate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static TestReact.k.Entity.QTbl_ProjectSet_WorkCate.tbl_ProjectSet_WorkCate;

@Repository
public class CusRepo_Prj_WorkCateImpl implements CusRepo_Prj_WorkCate
{
    private final JPAQueryFactory JpaQF;

    public CusRepo_Prj_WorkCateImpl(JPAQueryFactory jpaf)
    {
        JpaQF = jpaf;
    }

    @Override
    public List<Tbl_ProjectSet_WorkCate> Dsl_FindAll()
    {
        return JpaQF.selectFrom(tbl_ProjectSet_WorkCate).fetch();
    }
}
