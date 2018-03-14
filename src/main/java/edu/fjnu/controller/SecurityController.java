package edu.fjnu.controller;

import edu.fjnu.entity.User;
import edu.fjnu.service.AuthorityService;
import edu.fjnu.service.UserService;
import edu.fjnu.utils.AESUtil;
import edu.fjnu.utils.FaceUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author xiaoze
 * @date 2018/2/17
 */
@Controller
@Api(tags ="登录管理")
@RequestMapping("/security")
@SessionAttributes("loginUser")
public class SecurityController extends BaseController {

    /**
     * 记住我的有效时间秒(下面为一周)
     */
    private static final String KEY = "xiaoze.com";

    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    @ApiOperation(value="进入主界面")
    @RequestMapping("/enter")
    public String enter(Map<String, Object> map,
                        @AuthenticationPrincipal UserDetails userDetails) {

        System.out.println(userDetails.getPassword());
        User user=userService.selectByPrimaryKey(userDetails.getUsername());

        map.put("loginUser",user);
        return "main";
    }

    @ApiOperation(value="进入登录页面")
    @GetMapping("/toLogin")
    public String toLogin(Map<String, Object> map,
                          @AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails!=null)
            return "redirect:/security/enter";
        map.put("user", new User());

        return "security/login";
    }

    @ApiOperation(value="登录失败返回")
    @GetMapping("/loginError")
    public String loginError(Map<String, Object> map) {

        map.put("errorMsg", "登陆失败，账号或者密码错误！");
        return "security/login";
    }

    @ApiOperation(value="进入密码找回页面")
    @GetMapping("/toRetrievePwd")
    public String toRetrievePwd(Map<String, Object> map) {

        map.put("user", new User());

        return "security/retrievePwd";
    }

    @ApiOperation(value="进入注册界面")
    @GetMapping("/toRegister")
    public String toRegister(Map<String, Object> map) throws Exception {

        map.put("user", new User());

        map.put("authorityList", authorityService.findAllAuthority());

        return "security/register";
    }

    @ApiOperation(value="登录后加入主页面")
    @PostMapping("/register")
    public String register(User user,Map<String, Object> map,
                           @RequestParam("personPhoto") MultipartFile file) throws IOException {

        AESUtil aesUtil = new AESUtil();
        user.setUserPassword(aesUtil.Encrypt(user.getPassword(),"ABCDEFGHIJKLMNOP"));
        user.setUserAuthorityId("2");
        user.setUserDepartment("01");
        FaceUtils faceUtils=new FaceUtils();
        if(!file.isEmpty()){
            if(faceUtils.checkOneFace(file)){
                user.setUserFaceAvatar(file.getBytes());
                userService.insertSelective(user);
                return "redirect:/security/toLogin";
            }else{
                map.put("user",user);
                map.put("faceError","头像不符合要求！");
                return "security/register";
            }
        }
        map.put("user",user);
        map.put("faceError","没上传头像！");
        return "security/register";
    }

    @ApiOperation(value="退出去登录界面")
    @GetMapping("/logOut")
    public String logOut(HttpServletRequest request,
                         HttpServletResponse response,
                         @AuthenticationPrincipal UserDetails userDetails) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        new PersistentTokenBasedRememberMeServices(KEY, userDetailsService, persistentTokenRepository).logout(request, response, auth);
        new CookieClearingLogoutHandler(userDetails.getUsername()).logout(request, response, auth);
        new SecurityContextLogoutHandler().logout(request, response, auth);


        return "redirect:/security/toLogin";
    }

    @ApiOperation(value="取人脸识别登录界面")
    @GetMapping("/toFaceLogin")
    public String toFaceLogin() {

        return "security/face_login";
    }


    @ApiOperation(value="人脸识别登录验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imgString", value = "图片数据字符串imgString", dataType = "String"),
            @ApiImplicitParam(name = "number", value = "账号number", dataType = "String")
    })
    @RequestMapping(value="/faceContrast")
    @ResponseBody
    public Map faceContrast(String imgString,String number) throws IOException {
        Map<String, Object> map=new HashMap<>();
        if(isEmpty(number)) {
            map.put("rInfo","没输入账号");
            return map;
        }

        try {
            User user1=userService.selectByPrimaryKey(number);
        }catch (Exception e){
            map.put("rInfo","输入账号不正确");
            return map;
        }

        System.out.println("账号："+number);

        FaceUtils faceUtils = new FaceUtils();
        String strOne ;
        String strTwo ;

        String[] sOne ;
        String[] sTwo ;

        Integer sLengthOne;
        Integer sLengthTwo;

        String faceTokenOne;
        String faceTokenTwo;
        int i=0;
        int j=0;


        while(true){
            strOne = faceUtils.check(faceUtils.getStringImage(imgString.substring(imgString.indexOf(",")+1)));
            sOne=strOne.split("\"");
            if(sOne[1].equals("error_message"))
                continue;
            //解决一张图片有多个人问题
            for(String str:sOne){
                if(str.equals("age"))
                    j++;
                if(j>1) {
                    map.put("rInfo","对不起，您上传的用户头像照片质量不达标(即不是单个有效头像)，请重新上传！");
                    return map;
                }
            }
            sLengthOne = sOne.length;
            faceTokenOne = sOne[sLengthOne-2];
            i++;
            if(i>=10) {
                map.put("rInfo","网络故障，请稍后再试！");
                return map;
            }
            break;
        }

        if(!sOne[sLengthOne-4].equals("face_token")){
            map.put("rInfo","对不起，您上传的用户头像照片质量不达标(即不是单个有效头像)，请重新上传！");
            return map;
        }

        User user = userService.selectByPrimaryKey(number);

        i=0;
        while(true){
            strTwo = faceUtils.check(user.getUserFaceAvatar());
            sTwo=strTwo.split("\"");
            if(sTwo[1].equals("error_message"))
                continue;
            sLengthTwo = sTwo.length;
            faceTokenTwo = sTwo[sLengthTwo-2];
            i++;
            if(i>=10){
                map.put("rInfo","网络故障，请稍后再试！");
                return map;
            }
            break;
        }

        String compareInfo ;
        String[] compareInfoStr;
        i=0;
        while(true){
            compareInfo = faceUtils.compare(faceTokenOne,faceTokenTwo);
            compareInfoStr = compareInfo.split("\"");

            if(compareInfoStr[1].equals("error_message"))
                continue;
            i++;
            if(i>=10){
                map.put("rInfo","网络故障，请稍后再试！");
                return map;
            }
            break;
        }

        Double likeInfo = Double.parseDouble(compareInfoStr[2].substring(2, compareInfoStr[2].length()-2));
        if(likeInfo<70){
            map.put("rInfo","认证不成功，请本人刷脸！");
            return map;
        }
        map.put("rNum","1");
        String str=userService.selectByPrimaryKey(number).getPassword();
        map.put("rPassword",new AESUtil().Decrypt(str,"ABCDEFGHIJKLMNOP"));
        System.out.println("原始密码是："+new AESUtil().Decrypt(str,"ABCDEFGHIJKLMNOP"));
        return map;
    }

}
