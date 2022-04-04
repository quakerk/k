package TestReact.k.Ctrler;

import TestReact.k.Dto.Dto_ProjectSet_WorkCate;
import TestReact.k.Entity.QTbl_ProjectSet_WorkCate;
import TestReact.k.Entity.Tbl_ProjectSet_WorkCate;
import TestReact.k.Global.SuppCateOrder;
import TestReact.k.JpaRepo.Repo_ProjectSet_WorkCate;
import TestReact.k.Svc.Svc_ProjectSet;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RequiredArgsConstructor
@RestController
@Transactional
public class ApiCtrler_ProjectSet
{
    @Autowired
    Repo_ProjectSet_WorkCate RepoWorkCate;

    @Autowired
    EntityManager   enttMng;

    private final Svc_ProjectSet SvcWorkSet;


    @PostMapping(value = "/api/SetWorkCate/New")
    @ResponseBody
    public long CreateWork(@RequestParam(value = "jstr") String jstr)
    {
        long id = 0;
        try
        {
            System.out.print("RECV : WorkCate Set > " + jstr + "\n");

            JSONObject j = new JSONObject(jstr);
            long uid = j.getInt("id");

            id = SvcWorkSet.CreateNewCate( new Dto_ProjectSet_WorkCate("이름 설정하시오", 1, 0, "0") );
        }
        catch (Exception e)
        {
            System.out.print("ERROR : WorkCate Set >> " + e.getMessage());
        }

        return id;
    }


    // 카테고리 서브 등록
    @PostMapping(value = "/api/SetWorkCate/Add")
    @ResponseBody
    public long AddSubWork(@RequestParam(value = "jstr") String jstr)
    {
        long id = 0;

        return id;
    }


    //카테고리 수정
    @PutMapping("/api/SetWorkCate/Update/{id}")
    //public void UpdatePrjData(@PathVariable long id, @RequestBody Dto_ProjectMng dtoPrj)
    public int UpdateWork(@PathVariable long id, @RequestParam(value = "json") String jstr)
    {
        int ret = 0;
        System.out.print(">>>SetWork update > " + jstr+ "\n");
        try
        {
//            Dto_ProjectSet_WorkCate dto = new Dto_ProjectSet_WorkCate();
//            Svc_ProjectSet.Update(id, dto);

            ret = 1;
        }
        catch (Exception e)
        {
            System.out.print("json error > "+e.getMessage());
        }

        return ret;
    }


    // 카테고리 조회
    @GetMapping("/api/SetWorkCate/Get")
    public String GetCateAll()
    {
        // 카테고리 묶어서 조회 쿼리
//        with recursive cte (id, title, cate_pid) as (
//            select     id,
//            title,
//            cate_pid
//            from       cate
//              where cate_pid = '1'

//            union all
//            select     p.id,
//            p.title,
//            p.cate_pid
//            from       cate p
//            inner join cte
//            on p.cate_pid = cte.id
//    )
//        select * from cte;

        // 카테고리 패쓰로 뽑는 쿼리
//        DROP PROCEDURE IF EXISTS getpath;
//        DELIMITER $$
//        CREATE PROCEDURE getpath(IN cat_id INT, OUT path TEXT)
//        BEGIN
//        DECLARE catname VARCHAR(20);
//        DECLARE temppath TEXT;
//        DECLARE tempparent INT;
//        SET max_sp_recursion_depth = 255;
//        SELECT name, parent_id FROM category WHERE id=cat_id INTO catname, tempparent;
//        IF tempparent IS NULL
//        THEN
//        SET path = catname;
//        ELSE
//        CALL getpath(tempparent, temppath);
//        SET path = CONCAT(temppath, '/', catname);
//        END IF;
//        END$$
//        DELIMITER ;
//        저장 프로시저의 래퍼 함수:
//
//        DROP FUNCTION IF EXISTS getpath;
//        DELIMITER $$
//        CREATE FUNCTION getpath(cat_id INT) RETURNS TEXT DETERMINISTIC
//        BEGIN
//        DECLARE res TEXT;
//        CALL getpath(cat_id, res);
//        RETURN res;
//        END$$
//        DELIMITER ;
//        예를 선택하십시오:
//
//        SELECT id, name, getpath(id) AS path FROM category;

        System.out.print(">>>SetWork findAll > ");

 //       Tbl_ProjectSet_WorkCate wc = new Tbl_ProjectSet_WorkCate();
 //       enttMng.persist(wc);

        //JPAQueryFactory qf = new JPAQueryFactory(enttMng);
        //QTbl_ProjectSet_WorkCate qwc = QTbl_ProjectSet_WorkCate.tbl_ProjectSet_WorkCate;//new QTbl_ProjectSet_WorkCate("0, none, none");
//        QTbl_ProjectSet_WorkCate  wc = QTbl_ProjectSet_WorkCate.tbl_ProjectSet_WorkCate;
//        List<Tbl_ProjectSet_WorkCate> sel = qf.select(wc)
//                .from(QTbl_ProjectSet_WorkCate.tbl_ProjectSet_WorkCate)
//                .fetch();

        String ret = "0";
        List<Tbl_ProjectSet_WorkCate> res = SvcWorkSet.GetCateAll();


        return  ret;
    }

    // 카테고리 조회
    @GetMapping("/api/SetWorkCate/GetItem/{id}")
    public Dto_ProjectSet_WorkCate GetCateById(@PathVariable long id)
    {
        System.out.print(">>>SetWork findId > "+id);
        return  SvcWorkSet.GetCateData(id);
    }
}


