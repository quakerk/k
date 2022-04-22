package TestReact.k.Svc;

import TestReact.k.Response.ResultList;
import TestReact.k.Response.ResultOne;
import TestReact.k.Response.ResultResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnRes
{
    public enum enRES
    {
        OK(0, "성공"),
        FAIL(-1, "실패");

        int Code;
        String Msg;

        enRES(int c, String s)
        {
            Code = c;
            Msg = s;
        }

        public int GetCode() {return Code;}
        public String GetMsg() {return Msg;}
    }


    // 단일건 결과 처리 리턴
    public <T> ResultOne<T> Ret(T data)
    {
        ResultOne<T> res = new ResultOne<>();
        res.setData(data);
        SetOk(res);

        return res;
    }

    public <T> ResultList<T> Ret(List<T> data)
    {
        ResultList<T> res = new ResultList<>();
        res.setData(data);
        SetOk(res);
        return res;
    }

    public <T> ResultOne<T> RetErr(int code, String msg)
    {
        ResultOne<T> res = new ResultOne<>();
        SetFail(res);
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }

    // 리턴 데이타 없이 결과만 필요할 경우 -----------------------
    public ResultResponse RetOk()
    {
        ResultResponse r = new ResultResponse();
        SetOk(r);
        return r;
    }

    public ResultResponse RetFail()
    {
        ResultResponse r = new ResultResponse();
        SetFail(r);
        return r;
    }

    public ResultResponse RetFail(int code, String msg)
    {
        ResultResponse r = new ResultResponse();
        r.setOk(false);
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
    // ------------------------------------------------------

    private void SetOk(ResultResponse r)
    {
        r.setOk(true);
        r.setCode( enRES.OK.Code );
        r.setMsg( enRES.OK.Msg);
    }

    private void SetFail(ResultResponse r)
    {
        r.setOk(false);
        r.setCode(enRES.FAIL.Code);
        r.setMsg(enRES.FAIL.Msg);
    }
}
