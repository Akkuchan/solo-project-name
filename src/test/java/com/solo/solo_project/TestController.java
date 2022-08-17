package com.solo.solo_project;

import com.solo.solo_project.Entity.User;
import com.solo.solo_project.controller.UserController;
import com.solo.solo_project.dto.UserDto;
import com.solo.solo_project.mapper.Usermapper;
import com.solo.solo_project.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import java.util.*;

import static com.solo.solo_project.ApiDocumentUtils.getRequestPreProcessor;
import static com.solo.solo_project.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)//@SpringBootTest를 대신해 사용하였다. @WebMvcTest는 컨트롤러를 테스트하기 위한 전용 애너테이션이다.테스트 대상이 되는 컨트롤러의 클래스를 지정한다.
@MockBean(JpaMetamodelMappingContext.class)//JPA에서 사용하는 Bean들을 Mock 객체로 주입해주는 설정. Spring Boot 기반의 테스트는 항상 최상위 패키지 경로에 있는 xxxxxxxApplication 클래스를 찾아서 실행합니다.
@AutoConfigureRestDocs//
public class TestController {

    @Autowired
    private MockMvc mockMvc;
    //톰캣같은 서버없이 스프링 기반 애필리케이션의 컨트롤러를 테스트할 수 있는 환경을 제공해주는
    //테스트 프레임워크이다.
    //MockMvc를 통해 컨트롤러를 호출하여 테스트할 수 있다.
    @Autowired
    private Gson gson;

    @MockBean
    private UserService userService;//서비스 mock 객체

//   @Autowired
    @MockBean//@WebMvcTest는 필요한 Bean만 ApplicationContext에 넣기 때문에 Mock 에서 사용할 객체를 지정해 주어야 한다.
    private Usermapper mapper;//가짜 서비스 객체의 create()의 리턴값인 User 객체를 만들기 위해 실제 사용해야함



    @DisplayName("POST 메서드 테스트 케이스")
    @Test
    public void postTest() throws Exception{
//      Given
        UserDto.Post post = new UserDto.Post("asd@naver.com", "김철수","male",
                "삼성전자", "1","1");
        String content = gson.toJson(post);

        UserDto.single_response response = new UserDto.single_response(1L,"asd@naver.com", "김철수","male",
                "삼성전자", "1","1");
        //산출값과 비교할 response 객체 생성

        given(mapper.userPostDtoToUser(Mockito.any(UserDto.Post.class))).willReturn(new User());

        given(userService.createUser(Mockito.any(User.class))).willReturn(new User());
//      given은 Mockito가 지원하는 Stubbing 메서드로 Mock 객체가 특정 값(user)를 리턴하도록 지정하는 기능을 하는 메서드이다.
//      Mockito.any(User.class)는 파라미터 타입의 임의의 객체를 의미하며 해당 객체가 들어가서 결국(willReturn) user를 반환한다는 의미로 사용된다.

        given(mapper.userToUserResponseDto(Mockito.any(User.class))).willReturn(response);


//      when
        ResultActions actions =
                mockMvc.perform(post("/v1/buisinessman")//mockMVC에서 수행할 메서드를 post 등의 HTTPMETHOD를 설정한다.
                        .accept(MediaType.APPLICATION_JSON)//클라이언트가 리턴받을 응답 데이터의 타입설정
                        .contentType(MediaType.APPLICATION_JSON)//서버에서 처리가능한 타입 설정
                        .content(content)// request body에 들어갈 데이터 정의
                );

//      then

       actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(post.getEmail()))
                .andExpect(jsonPath("$.name").value(post.getName()))
                .andExpect(jsonPath("$.sex").value(post.getSex()))
                .andExpect(jsonPath("$.company_name").value(post.getCompany_name()))
                .andExpect(jsonPath("$.company_type").value(post.getCompany_type()))
                .andExpect(jsonPath("$.company_location").value(post.getCompany_location()))
                //jsonPath메서드를 이용해 response body에 담긴 프로터티 중 하나의 값이
        //        request에서 클라이언트가 보내준 프로퍼티값과과일치하는지를 검증
        //        MockMvcResultMatchers의 jsonPath 메서드를 통해 JSON 형식의 개별 프로퍼디에 보다 쉽게 접근 가능하다.

                .andDo(document(//REST Docs, API 문서 생성을 위한 API, RestDocumentationResultHandler의 핵심
                        // 메서드로서 API 스펙 정보를 전달 받아 실질적인 문서화 작업을 수행한다.
                        "post-user",//API 문서 스티핏의 식별자 역할을 한다. 문서 스니핏이 post-user 디렉토리 하위에 생성된다.
                        getRequestPreProcessor(),//두 메서드는 문서 스니핏 생성하기 전 request와 response에 해당하는 문서영역을 전저리하는 역할을 한다.
                        getResponsePreProcessor(),//각각 내부에서 preprocessRequest(prettyPrint())를 반환하는데 문서에 쓰여질 JSON 포맷을 예쁘게 표현해주는 기능을 한다.
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("sex").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("company_name").type(JsonFieldType.STRING).description("회사명"),
                                        fieldWithPath("company_type").type(JsonFieldType.STRING).description("업종"),
                                        fieldWithPath("company_location").type(JsonFieldType.STRING).description("지역")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("회원식별자"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("sex").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("company_name").type(JsonFieldType.STRING).description("회사명"),
                                        fieldWithPath("company_type").type(JsonFieldType.STRING).description("업종"),
                                        fieldWithPath("company_location").type(JsonFieldType.STRING).description("지역")
                                )
                        )
                ));
    }

}
