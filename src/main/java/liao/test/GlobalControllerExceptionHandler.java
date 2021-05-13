package liao.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ： nemo
 * @date ：2020-12-29 11:07
 * @description :
 */
//@ControllerAdvice
@Order(90)
public class GlobalControllerExceptionHandler
//        implements ErrorController
{

    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);


    /**
     * HttpMessageNotReadableException处理
     * 无法正确解析HttpMessage，可能是json格式错误
     *
     * @param e HttpMessageNotReadableException
     * @return ResultDto
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        log.debug("HttpMessageNotReadableException处理。原因 => {}", e.getMessage());
        return ("无法正确解析HttpMessage");
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object exceptionHandler(Exception e) {
        log.debug("Exception处理。原因：", e);
        return ("程序内部出现错误");
    }

//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }


}