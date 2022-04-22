package TestReact.k.Ctrler;

import TestReact.k.Dto.Dto_ProjectMain;
import TestReact.k.Dto.Dto_Workgrp;
import TestReact.k.Entity.TblTest;
import TestReact.k.JpaRepo.TestRepo;
import TestReact.k.Response.ResultOne;
import TestReact.k.Svc.ReturnRes;
import TestReact.k.Svc.Svc_ProjectMng;
import TestReact.k.Dto.Dto_Test;

import TestReact.k.Svc.Svc_Test;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.*;


@RequiredArgsConstructor
@RestController
public class ApiCtrler_ProjectMngApi
{
    @Autowired
    TestRepo    rpgTest;

    private final Svc_ProjectMng SvcPrjMng;
    private final Svc_Test  SvcTest;
    private final ReturnRes mRet;

    // 프로젝트 생성
    @PostMapping(value="/api/PrjMng/PrjCreate")
    @ResponseBody
    public ResultOne<String> Createzroject(@RequestParam(value = "jstr") String jstr )
    {
        try
        {
            // 넘어오는 데이타
            //{"title":"평창 봅슬레이11",
            // "cate":2,
            // "catetitle":"3D 스캔 > 포토그래매터리 > ",
            // "workgrp":[{"id":0,"title":"","child":0},{"id":"1","title":"111","child":0},{"id":"2","title":"222","child":0}]}
            System.out.print("RECV : CreateProject > " + jstr+ "\n");

            ArrayList<Integer> arrCateId = new ArrayList<>();

            JSONObject j = new JSONObject(jstr);
            String title        = j.getString("title");
            String cateTitle    = j.getString("catetitle");

            JSONArray ja = j.getJSONArray("cate");
//            for(int i=0 ; i<ja.length() ; i++)
//                arrCateId.get().add( Integer.parseInt(ja.getJSONObject(i).getString("id") ) );
            ja.forEach(it -> {
                arrCateId.add( Integer.parseInt( ((JSONObject)it).getString("id")) );
            });

            // 프로젝트 본체 등록
            long prjid = SvcPrjMng.CreatePrj( new Dto_ProjectMain(title, 0, arrCateId.get(0), 0, 0, "", "", "", null));

            // 프로젝트에 딸린 워크그룹 등록
            ArrayList<Dto_Workgrp> arrGrp = new ArrayList<>();

            ja = null;
            ja = j.getJSONArray("workgrp");
            ja.forEach( it ->
            {
                int wuid    = Integer.parseInt( ((JSONObject)it).getString("uid") ); // 내부 순서 임시 id
                String wt   = ((JSONObject) it).getString("title");
                int wchild  = ((JSONObject) it).getInt("child");

                arrGrp.add( new Dto_Workgrp(prjid, wt, wuid, wchild) );
            });


            //1.프로젝트 등록하고 id나오면
            //2. 프로젝트 id를 parent로 그룹들 등록시켜야 한다.
            //title, wgrpid, cateid, pgrs, adminid, info, fp, imgp, date
            SvcPrjMng.CreatePrjWork(new Dto_Workgrp());

            //3.프로젝트id 로 워크그룹 등록.



//            // 테스트 ===========================================================================================
//            //jpa - testtbl 테스트
//            //create - post
////            TblTest u = TblTest.builder().str("이거다").idx(37).pid(337).build();
////            rpgTest.save(u);
//
//            uid = SvcTest.CreateProject( new Dto_Test("저거다", 12, 228, null) );
//            List<TblTest> ttt = rpgTest.findAll();
//            System.out.print(">>>> ID("+uid+") " + ttt.get(0) + "\n");
//            // =================================================================================================

        }
        catch (Exception e)
        {
            System.out.print("Req ERROR (projectCreate) > " + e.toString());
        }

        return mRet.Ret("");
    }


    //프로젝트 수정
    @PutMapping("/api/PrjMng/UpdatePrj/{id}")
    //public void UpdatePrjData(@PathVariable long id, @RequestBody Dto_ProjectMng dtoPrj)
    public int UpdatePrjData(@PathVariable long id, @RequestParam(value = "json") String jstr)
    {
        int ret = 0;
        System.out.print(">>>> update > " + jstr+ "\n");
        try
        {
            JSONObject j = new JSONObject(jstr);
            String str  = j.getString("str");
            int pid     = Integer.parseInt( j.getString("pid"));
            int idx     = Integer.parseInt( j.getString("idx"));

            Dto_Test dto = new Dto_Test(str, idx, pid, null);
            SvcTest.UpdatePrjData(id, dto);

            ret = 1;
        }
        catch (Exception e)
        {
            System.out.print("json error > "+e.getMessage());
        }

        return ret;
    }


    // 프로젝트 조회
    @GetMapping("/api/PrjMng/GetPrj/{id}")
    public Dto_Test GetPrjInfo(@PathVariable long id)
    {
        System.out.print(">>> get > "+id);
        return SvcTest.GetPrjData(id);
    }


    // 프로젝트 삭제
    @DeleteMapping("/api/PrjMng/DelPrj/{id}")
    public int DeletePrj(@PathVariable long id)
    {
        System.out.print(">>> delete > "+id);
        return SvcTest.DeletePrjData(id);
    }


    // test ////////////////////////////////////////////////////////////////////////////////
    @PostMapping(value="/api/ProjectCreate1")
    @ResponseBody
    public String CreateProject1( @RequestParam(value = "title") String title,
                                  @RequestParam(value = "cateid") String cateid,
                                  @RequestParam(value = "catetitle") String catetitle,
                                  @RequestParam(value = "workgrp") String workgrp
    )
    {
        try
        {
            System.out.print("RECV : CreateProject1 > " +title+ " / " +cateid+ " / " +catetitle+ " / " +workgrp);
        }
        catch (Exception e)
        {
            System.out.print("Req ERROR (projectCreate1) > " + e.toString());
        }

        return "{ret:1}";
    }


    @PostMapping(value="/api/projectCreate2")
    @ResponseBody
    public String CreateProject2( @RequestParam(value = "title") String title,
                                  @RequestParam(value = "cateid") ArrayList<String> cateid,
                                  @RequestParam(value = "catetitle") ArrayList<String> catetitle,
                                  @RequestParam(value = "workgrp") ArrayList<String> workgrp
    )
    {
        try
        {
            System.out.print("RECV : CreateProject2 > " +title+ " / " +cateid+ " / " +catetitle+ " / " +workgrp);
        }
        catch (Exception e)
        {
            System.out.print("Req ERROR (projectCreate2) > " + e.toString());
        }

        return "{ret:1}";
    }


    // 프로젝트 생성
    @PostMapping(value="/api/projectCreate10")
    @ResponseBody
    public String CreateProject10(@RequestBody JSONObject jstr)
    {
        try
        {
            System.out.print("RECV : CreateProject10 > " + jstr.toString());
        }
        catch (Exception e)
        {
            System.out.print("Req ERROR (projectCreate10) > " + e.toString());
        }

        return "{ret:1}";
    }


    @PostMapping(value="/api/projectCreate11")
    @ResponseBody
    public String CreateProject11(@RequestBody String jstr)
    {
        try
        {
            System.out.print("RECV : CreateProject11 > " + jstr);
        }
        catch (Exception e)
        {
            System.out.print("Req ERROR (projectCreate11) > " + e.toString());
        }

        return "{ret:1}";
    }


    @PostMapping(value="/api/projectCreate12")
    @ResponseBody
    public String CreateProject12(@RequestBody Map<String, Object> jstr)
    {
        try
        {
            System.out.print("RECV : CreateProject12 > " + jstr.size());
        }
        catch (Exception e)
        {
            System.out.print("Req ERROR (projectCreate12) > " + e.toString());
        }

        return "{ret:1}";
    }
}
