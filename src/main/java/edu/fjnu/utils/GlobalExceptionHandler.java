//package edu.fjnu.utils;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//
////    @ExceptionHandler(value = RuntimeException.class)
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("url", req.getRequestURL());
//        mav.addObject("exception", e);
//        mav.setViewName("/error");
//
//        System.out.println("我捕捉到异常了，哈哈哈哈    "+e.getMessage());
//        return mav;
//    }
//}
//
////@ControllerAdvice
////public class GlobalExceptionHandler {
////
////    @ExceptionHandler(value = MyException.class)
////    @ResponseBody
////    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
////        ErrorInfo<String> r = new ErrorInfo<>();
////        r.setMessage(e.getMessage());
////        r.setCode(ErrorInfo.ERROR);
////        r.setData("Some Data");
////        r.setUrl(req.getRequestURL().toString());
////        return r;
////    }
////
////}
