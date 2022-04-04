package TestReact.k.Ctrler;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController
{
    @PostMapping("/api/users")
    public String user()
    {
        System.out.println("TestReact.k.Ctrler.UserApiController 진입");

        JSONObject j = new JSONObject();
        j.put("id", 2123);
        j.put("name", "내다");
        j.put("pw", "k412");
        j.put("email", "@.sdf");

        return j.toString();
        //return new User(2332, "내다", "k123", "@.com");

    }
}
