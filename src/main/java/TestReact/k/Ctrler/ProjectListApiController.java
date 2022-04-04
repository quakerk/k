package TestReact.k.Ctrler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class ProjectListApiController
{

    String[] pname = {"뿐또블루", "구 58은행", "파이빌", "고대", "사무실", "인천 카페", "평창 봅슬레이", "궁남지",
                        "부산 여고", "어드반 코리아"};
    String[] Info = {"뿐또블루 공간 삼디 스캔", "일본 58은행 문화재 스캔", "고대 파이빌 내부 사무실 공간 스캔",
                     "고대 파이빌 전경 광대역 스캔", "사무실 내부 테스트 스캔", "인천 구옥 개조 카페 테스트 스캔",
                     "평창 봅슬레이 트랙내 구조물 설치 여부 판단", "문화 사적지 디지털 자료화 스캔",
                     "학교 무너질까? 말까? 안전진단 스캔", "천장 무너져 건물 안전진단 스캔"};
    String[] cate = {"Photogramatry", "구조물 스캔", "공간 측량", "안전 진단", "도면화 작업", "역설계"};
    int[] uuid = {101, 202, 303, 404, 505, 606, 707, 808, 909, 1011};
    String[] date = {"2022-01-27", "2012-03-53", "2000-01-10", "2020-08-20", "1999-02-20", "1900-12-10",
                     "2012-12-31", "2002-06-15", "1979-09-18", "1983-07-03"};
    String[] path = {"K:/adf/22", "C:/aaaa/11/233/44", "o:/vvsvs/asdf", "I:/4414/folder", "Q:/oko/ok/kiil"};



    @PostMapping("/api/projectList")
    public String ProjectList()
    {
        System.out.println("TestReact.k.ProjectListController 진입");

        JSONArray ja = new JSONArray();
        Random r = new Random();

        for(int i=0 ; i<35 ; i++)
        {
            int k = r.nextInt(10);
            JSONObject j = new JSONObject();
            j.put("id", i);
            j.put("UID", uuid[r.nextInt(10)] );
            j.put("Name", pname[k]);
            j.put("Cate", cate[r.nextInt(6)]);
            j.put("Date", date[r.nextInt(10)]);
            j.put("Path", path[r.nextInt(5)]);
            j.put("Info", Info[k]);

            ja.put(j);
        }

        return ja.toString();
        //return new User(2332, "내다", "k123", "@.com");
    }
}
