package TestReact.k.Ctrler;

import TestReact.k.Dto.Dto_ProjectSet_WorkCate;
import TestReact.k.Entity.Tbl_ProjectSet_WorkCate;
import TestReact.k.JpaRepo.Repo_ProjectSet_WorkCate;
import TestReact.k.Response.ResultOne;
import TestReact.k.Response.ResultResponse;
import TestReact.k.Svc.ReturnRes;
import TestReact.k.Svc.Svc_ProjectSet;
import lombok.RequiredArgsConstructor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;


@RequiredArgsConstructor
@RestController
@Transactional
public class ApiCtrler_ProjectSet
{
    @Autowired
    EntityManager   enttMng;

    private final Svc_ProjectSet mSvcWorkSet;
    private final ReturnRes mRet;

    private List<Tbl_ProjectSet_WorkCate> mAllList;

    @PostMapping(value = "/api/SetWorkCate/New")
    @ResponseBody
    public ResultOne<String> CreateWork(@RequestParam(value = "jstr") String jstr)
    {
        long id = 0;
        String ret="";
        try
        {
            System.out.print("RECV : WorkCate Set > " + jstr + "\n");

            JSONObject j = new JSONObject(jstr);
            long pid = j.getLong("pid");
            String title = j.getString("title");

            id = mSvcWorkSet.CreateNewCate( new Dto_ProjectSet_WorkCate(title, pid, 0, "0") );

            mAllList = mSvcWorkSet.GetCateAll();
            try
            {
                JSONArray ja = new JSONArray();

                ParseData(ja, mAllList);    // 이걸로 되나>??
                ret = ja.toString();
            }
            catch (Exception e)
            {
                System.out.print("error : "+e.getMessage());
            }
        }
        catch (Exception e)
        {
            System.out.print("ERROR : WorkCate Set >> " + e.getMessage());
        }

        return mRet.Ret(ret);
    }


    // 카테고리 서브 등록
    @PostMapping(value = "/api/SetWorkCate/Add")
    @ResponseBody
    public ResultOne<String> AddSubWork(@RequestParam(value = "jstr") String jstr)
    {
        System.out.print("Recv : AddSubWork > "+jstr);
        try
        {
            JSONObject j = new JSONObject(jstr);
            long pid = j.getLong("pid");
            String title = j.getString("title");

            long id = mSvcWorkSet.AddSubCate(SearchListBufById(pid, mAllList), new Dto_ProjectSet_WorkCate(title, pid, 0, "") );
            if( id <= 0)
                return mRet.RetErr(0, "update failed");

            List<Tbl_ProjectSet_WorkCate> res = mSvcWorkSet.GetCateAll();
            JSONArray ja = new JSONArray();
            ParseData(ja, res);


            return mRet.Ret(ja.toString());
        }
        catch (Exception e)
    {
            System.out.print("error : "+e.getMessage());
        }

        return mRet.RetErr(0, "update failed");
    }


    //카테고리 수정
    @PutMapping("/api/SetWorkCate/Update/{id}")
    //public void UpdatePrjData(@PathVariable long id, @RequestBody Dto_ProjectMng dtoPrj)
    public ResultOne<String> UpdateCateById(@PathVariable long id, @RequestParam(value = "json") String jstr)
    {
        long ret = 0;
        System.out.print(">>>SetWork update > " + jstr+ "\n");
        try
        {
            JSONObject j = new JSONObject(jstr);

            ret = mSvcWorkSet.UpdateCateById(id,
                                new Dto_ProjectSet_WorkCate(j.getString("title"), 0,-1, "") );

            if(ret < 0)
                return mRet.RetErr(0, "update failed");

            mAllList = mSvcWorkSet.GetCateAll();
            JSONArray ja = new JSONArray();
            ParseData(ja, mAllList);    // 이걸로 되나>??

            return mRet.Ret(ja.toString());

        }
        catch (Exception e)
        {
            System.out.print("json error > "+e.getMessage());
        }

        return mRet.RetErr(0, "update failed");
    }


    // 카테고리 조회
    @GetMapping("/api/SetWorkCate/Get")
    public ResultOne<String> GetCateAll()
    {
        // 카테고리 묶어서 조회 쿼리
        System.out.print(">>>SetWork findAll > ");

        String ret = "0";
        mAllList = mSvcWorkSet.GetCateAll();

        try
        {
            JSONArray ja = new JSONArray();

            ParseData(ja, mAllList);    // 이걸로 되나>??
            ret = ja.toString();
        }
        catch (Exception e)
        {
            System.out.print("error : "+e.getMessage());
        }

        return  mRet.Ret(ret);
    }




    // 카테고리 조회
    @GetMapping("/api/SetWorkCate/GetItem/{id}")
    public Dto_ProjectSet_WorkCate GetCateById(@PathVariable long id)
    {
        System.out.print(">>>SetWork findId > "+id);
        return  mSvcWorkSet.GetCateData(id);
    }


    @DeleteMapping("/api/SetWorkCate/Delete/{id}")
    public ResultOne<String> DeleteById(@PathVariable long id)
    {
        System.out.print(">>>Del id > "+id);
        mAllList = mSvcWorkSet.DeleteItem(id);
        String ret = "";

        try
        {
            JSONArray ja = new JSONArray();

            ParseData(ja, mAllList);    // 이걸로 되나>??
            ret = ja.toString();

            return mRet.Ret(ja.toString());
        }
        catch (Exception e)
        {
            System.out.print("error : "+e.getMessage());
        }

        return mRet.RetErr(0, "update failed");
    }


    @GetMapping("/api/SetWorkCate/GetItem/Test")
    public ResultResponse RetTest()
    {
        System.out.print(">>> 리턴 테스트 드러옴");
        return mRet.Ret("{id:1, msg:success}");
    }


    // util
    protected Tbl_ProjectSet_WorkCate SearchListBufById(long fid, List<Tbl_ProjectSet_WorkCate> item)
    {
        Tbl_ProjectSet_WorkCate r = null;

        for(Tbl_ProjectSet_WorkCate i : item)
        {
            if(i.getId() == fid)
                return i;

            if(i.getSubCate() != null)
            {
                r = SearchListBufById(fid, i.getSubCate());

                if( r != null)
                    return r;
            }
        }

        return null;
    }


    protected void ParseData(JSONArray p, List<Tbl_ProjectSet_WorkCate> item)
    {
        for(Tbl_ProjectSet_WorkCate i : item)
        {
            JSONObject j = new JSONObject();
            j.put("id", i.getId());
            j.put("title", i.getTitle());
            j.put("pid", i.getParent_id());


            List<Tbl_ProjectSet_WorkCate> sub = i.getSubCate();
            if( sub != null)
            {
                j.put("child", sub.size());
                j.put("sub", new JSONArray());
                ParseData(j.getJSONArray("sub"), sub);
            }
            else
            {
                j.put("child", 0);
                j.put("sub", "none");
            }

            p.put(j);
        }
    }
}


