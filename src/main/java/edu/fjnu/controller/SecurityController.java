package edu.fjnu.controller;

import edu.fjnu.entity.User;
import edu.fjnu.service.AuthorityService;
import edu.fjnu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

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
    public String enter() {

        return "main";
    }

    @ApiOperation(value="进入登录页面")
    @GetMapping("/toLogin")
    public String toLogin(Map<String, Object> map) {

        map.put("user", new User());

        return "security/login";
    }

    @ApiOperation(value="登录后加入主页面")
    @PostMapping("/login")
    public String login(User user,Map<String, Object> map) {
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
    public String register(User user,
                           @RequestParam("personPhoto") MultipartFile file) throws IOException {

        if(file!=null||file.getSize()>0)
            user.setUserFaceAvatar(file.getBytes());
        userService.insertSelective(user);
        return "redirect:/security/toLogin";
    }

}
