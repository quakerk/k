package TestReact.k.JpaRepo;

import TestReact.k.Dto.Dto_ProjectSet_WorkCate;
import TestReact.k.Entity.QTbl_ProjectSet_WorkCate;
import TestReact.k.Entity.Tbl_ProjectSet_WorkCate;
import TestReact.k.Global.QueryDslHelper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static TestReact.k.Entity.QTbl_ProjectSet_WorkCate.tbl_ProjectSet_WorkCate;

@Repository
//@RequiredArgsConstructor
public class CusRepo_Prj_WorkCateImpl implements CusRepo_Prj_WorkCate
{
    private final JPAQueryFactory JpaQF;

    public CusRepo_Prj_WorkCateImpl(JPAQueryFactory jpaf, QueryDslHelper qh)
    {
        JpaQF = jpaf;
    }


    @Override
    public List<Tbl_ProjectSet_WorkCate> Dsl_FindAll()
    {
        return JpaQF.selectFrom(tbl_ProjectSet_WorkCate).fetch();
    }


    @Override
    public List<Tbl_ProjectSet_WorkCate> Dsl_FindCateAll()
    {
        QTbl_ProjectSet_WorkCate parent = new QTbl_ProjectSet_WorkCate("parent");
        QTbl_ProjectSet_WorkCate sub = new QTbl_ProjectSet_WorkCate("sub");

        return JpaQF.selectFrom(parent)
                    .distinct()
                    .leftJoin(parent.SubCate, sub)
                    .fetchJoin()
                    .where( parent.parent.isNull())
                    .orderBy(parent.id.asc())
                    .fetch();
    }

    @Override
    public Tbl_ProjectSet_WorkCate Dsl_Update(long id, String title, long pid, int child, String node)
    {
        QTbl_ProjectSet_WorkCate tbl = new QTbl_ProjectSet_WorkCate("");
        long ret = JpaQF.update(tbl).execute();
        return null;
    }

    @Override
    public Tbl_ProjectSet_WorkCate Dsl_Add(long pid, String title)
    {
        QTbl_ProjectSet_WorkCate tbl = new QTbl_ProjectSet_WorkCate("sub");
        JpaQF.insert(tbl).execute();
        return null;
    }


}
