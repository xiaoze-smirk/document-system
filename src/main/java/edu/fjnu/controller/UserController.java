package edu.fjnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.User;
import edu.fjnu.service.AuthorityService;
import edu.fjnu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author xiaoze
 * @date 2018/2/18
 */
@Controller
@Api(tags ="用户管理")
@RequestMapping("/user")
@SessionAttributes("loginUser")
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @ModelAttribute
    public void getDepartment(@RequestParam(value="userAccount",required=false) String userAccount,
                              Map<String, Object> map, User user){

        if(isNotEmpty(userAccount))
            user=userService.selectByPrimaryKey(userAccount);
        if(user!= null)
            map.put("user", user);
    }

    @ApiOperation(value="获取分页列表", notes="用来获取分页列表")
    @ApiImplicitParam(name = "pageNoStr", value = "页码:pageNoStr")
    @RequestMapping("/list")
    public String list(Map<String, Object> map,
                       @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
                       @RequestParam(value="pageSize", required=false, defaultValue="3") String pageSizeStr){

        Integer pageNo = 1;
        Integer pageSize = 3;

        //对 pageNo 的校验
        pageNo = Integer.parseInt(pageNoStr);
        if(pageNo < 1){
            pageNo = 1;
        }

        //校验pageSize
        pageSize = Integer.parseInt(pageSizeStr);
        if(pageSize < 1){
            pageSize = 3;
        }

        PageHelper.startPage(pageNo, pageSize);
        List<User> userList=userService.findAllUser();

        PageInfo<User> page=new PageInfo<>(userList);
        for(User user:page.getList())
            user.setAuthority(authorityService.selectByPrimaryKey(user.getUserAuthorityId()));

        map.put("page", page);
        map.put("pageSize", pageSize);

        return "user/list_user";
    }

    @ApiOperation(value="删除操作后台所需要的值")
    @ApiImplicitParam(name = "userId", value = "用户userId", required = true, dataType = "Integer")
    @DeleteMapping(value="/remove/{userAccount}")
    public String remove(@PathVariable("userAccount") String userAccount) {

        userService.deleteByPrimaryKey(userAccount);

        return "redirect:/user/list";
    }

    @ApiOperation(value="进入部门修改界面")
    @ApiImplicitParam(name = "userId", value = "用户userId", required = true, dataType = "Integer")
    @GetMapping(value="/preUpdate/{userAccount}")
    public String preUpdate(@PathVariable("userAccount") String userAccount, Map<String, Object> map){

        map.put("user", userService.selectByPrimaryKey(userAccount));
        map.put("authorityList", authorityService.findAllAuthority());

        return "user/update_user";
    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @PutMapping(value="/update")
    public String update(User user,
                         @RequestParam("personPhoto") MultipartFile file) throws IOException {

        if(!file.isEmpty())
            user.setUserFaceAvatar(file.getBytes());
        userService.updateByPrimaryKeySelective(user);

        return "redirect:/user/list";
    }

    @ApiOperation(value="获取图片")
    @GetMapping("/getPic/{userAccount}")
    public String getPic(@PathVariable("userAccount") String userAccount, HttpServletRequest request, HttpServletResponse response) throws Exception{

        byte[] textPic = userService.selectByPrimaryKey(userAccount).getUserFaceAvatar();

        if(textPic==null){
            String path = request.getSession().getServletContext().getRealPath("/images/main/person-img.png");
            FileInputStream fis = new FileInputStream(new File(path));

            textPic = new byte[fis.available()];
            fis.read(textPic);
        }

        //向浏览器发通知，我要发送是图片
        response.setContentType("image/jpeg");
        ServletOutputStream sos=response.getOutputStream();
        sos.write(textPic);
        sos.flush();
        sos.close();

        return null; //由于已经把界面数据发回去了，所以不需要struts做VIEW服务了。

    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @ApiImplicitParam(name = "setNum", value = "个人信息等所在位置的值setNum", required = true, dataType = "Integer")
    @GetMapping(value="/toSettingInfo")
    public String toSettingInfo(Map<String, Object> map,
            @RequestParam(value="setNum", required=false, defaultValue="0") Integer setNum,
            @SessionAttribute("loginUser") User user) {

        map.put("setNum",setNum);
        User user1=userService.selectByPrimaryKey(user.getUserAccount());
        map.put("loginUser",user1);
        map.put("user",user1);

        return "user/settingInfo";
    }

    @ApiOperation(value="修改个人信息操作需要传入后台的值")
    @PostMapping(value="/updateSelfInfo")
    public String updateSelfInfo(User user,Map<String, Object> map,
                         @RequestParam("personPhoto") MultipartFile file) throws IOException {

        Integer setNum=0;
        if(!file.isEmpty()) {
            System.out.println("坏蛋坏蛋");
            user.setUserFaceAvatar(file.getBytes());
        }
        userService.updateByPrimaryKeySelective(user);

        map.put("setNum",setNum);
        map.put("loginUser",user);
        map.put("user",user);

        return "user/settingInfo";
    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @PostMapping(value="/updatePassword")
    public String updatePassword(String affirmPassword,Map<String, Object> map,
                                 @SessionAttribute("loginUser") User user) {

        user.setUserPassword(affirmPassword);
        map.put("loginUser",user);
        map.put("user",user);

        Integer setNum=1;
        map.put("setNum",setNum);

        if(isNotEmpty(affirmPassword))
            userService.updateByPrimaryKeySelective(user);

        return "user/settingInfo";
    }

    @ApiOperation(value="ajax检查原始密码正确性")
    @ApiImplicitParam(name = "originalPassword", value = "原始密码originalPassword", required = true, dataType = "String")
    @ResponseBody
    @PostMapping(value="/ajaxValidatePassword")
    public String validateCourseNo(String originalPassword,
                                   @SessionAttribute("loginUser") User user){

        if(user.getUserPassword().equals(originalPassword)){
            System.out.println(1);
            return "1";
        }
        System.out.println(0);
        return "0";

    }

}
