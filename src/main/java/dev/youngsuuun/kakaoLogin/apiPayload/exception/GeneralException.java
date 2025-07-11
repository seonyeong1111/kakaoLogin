package dev.youngsuuun.kakaoLogin.apiPayload.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.youngsuuun.kakaoLogin.apiPayload.code.BaseErrorCode;
import dev.youngsuuun.kakaoLogin.apiPayload.code.ErrorReasonDto;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{
    private BaseErrorCode code;
    public ErrorReasonDto getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
