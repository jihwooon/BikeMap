package com.bikemap.api.web.signin

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SigninRequest(

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,

    @NotEmpty
    @Size(min = 10, max = 20, message = "비밀번호는 10자 이상 20 이하로 입력해 주세요")
    @field:Pattern.List(
        Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*\\d).*",
            message = "영문과 숫자를 조합한 비밀번호를 입력하세요."
        ),
        Pattern(
            regexp = "^[A-Za-z\\d$@!#%^*&]*$",
            message = "특수문자는 !,@,#,$,%,^,&,*만 사용가능합니다."
        )
    )
    val password: String
)
