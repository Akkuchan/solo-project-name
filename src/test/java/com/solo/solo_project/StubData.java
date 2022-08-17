package com.solo.solo_project;

import com.solo.solo_project.dto.UserDto;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class StubData {

    private static Map<HttpMethod, Object> stubRequestBody;
    static{ stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new UserDto.Post("asd@naver.com","김철수","male","삼성전자","1","1"));
//        stubRequestBody.put(HttpMethod.GET, new MemberDto.Patch(1,"홍길동", "010-9876-5432", Member.MemberStatus.MEMBER_SLEEP));
    }


    public static Map<HttpMethod, Object> getStubRequestBody(){
        return stubRequestBody;
    }

    public static UserDto.single_response poststubResponseBody(){
        UserDto.single_response response = new UserDto.single_response(
                1,
                "asd@naver.com",
                "김철수",
                "male",
                "삼성전자",
                "1",
                "1"
        );
        return response;

    }


}
