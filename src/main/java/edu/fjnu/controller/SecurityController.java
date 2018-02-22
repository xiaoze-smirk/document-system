package edu.fjnu.controller;

import edu.fjnu.entity.User;
import edu.fjnu.service.AuthorityService;
import edu.fjnu.service.UserService;
import edu.fjnu.utils.FaceUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author xiaoze
 * @date 2018/2/17
 */
@Controller
@Api(tags ="登录操作管理")
@RequestMapping("/security")
@SessionAttributes("loginUser")
public class SecurityController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @ApiOperation(value="进入主界面")
    @GetMapping("/enter")
    public String enter(@SessionAttribute("loginUser") User user,Map<String, Object> map) {

        user.setAuthority(authorityService.selectByPrimaryKey(user.getUserAuthorityId()));
        map.put("loginUser",user);
        return "main";
    }

    @ApiOperation(value="进入登录页面")
    @GetMapping("/toLogin")
    public String toLogin(Map<String, Object> map) {

        map.put("user", new User());

        return "security/login";
    }

    @ApiOperation(value="登录后加入主页面")
    @RequestMapping("/login")
    public String login(User user,Map<String, Object> map,
                        @RequestParam(value="number",required=false) String number) {
        if(isNotEmpty(number))
            user=userService.selectByPrimaryKey(number);
        else
            user=userService.selectByPrimaryKey(user.getUserAccount());
        user.setAuthority(authorityService.selectByPrimaryKey(user.getUserAuthorityId()));
        map.put("loginUser",user);

        return "main";
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
    public String logOut(HttpSession httpSession,Map<String, Object> map) {

        httpSession.removeAttribute("loginUser");
        map.put("user", new User());
        return "security/login";
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
    public String faceContrast(String imgString,String number) throws IOException {
        if(isEmpty(number))
            return "没输入账号!";

        if(userService.selectByPrimaryKey(number)==null)
            return "输入账号不正确!";
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
                if(j>1)
                    return "对不起，您上传的用户头像照片质量不达标(即不是单个有效头像)，请重新上传！";
            }
            sLengthOne = sOne.length;
            faceTokenOne = sOne[sLengthOne-2];
            i++;
            if(i>=10) {
                return "网络故障，请稍后再试！";
            }
            break;
        }

        if(!sOne[sLengthOne-4].equals("face_token"))
            return "对不起，您上传的用户头像照片质量不达标(即不是单个有效头像)，请重新上传！";

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
            if(i>=10)
                return "网络故障，请稍后再试！";
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
            if(i>=10)
                return "网络故障，请稍后再试！";
            break;
        }

        Double likeInfo = Double.parseDouble(compareInfoStr[2].substring(2, compareInfoStr[2].length()-2));
        if(likeInfo<70)
            return "认证不成功，请本人刷脸";
        return "1";
    }



}
