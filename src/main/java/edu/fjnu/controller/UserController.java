package edu.fjnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Department;
import edu.fjnu.entity.User;
import edu.fjnu.service.AuthorityService;
import edu.fjnu.service.DepartmentService;
import edu.fjnu.service.UserService;
import edu.fjnu.utils.AESUtil;
import edu.fjnu.utils.EntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private DepartmentService departmentService;

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
        List<Department> departmentList = departmentService.findAllDepartment();

        EntityUtil entityUtil = new EntityUtil();

        PageInfo<User> page=new PageInfo<>(userList);
        String str;
        for(User user:page.getList()) {
            user.setAuthority(authorityService.selectByPrimaryKey(user.getUserAuthorityId()));
            str = entityUtil.str(user.getUserDept(),departmentList);
            user.setUserDeptName(str);
        }

        map.put("page", page);
        map.put("pageSize", pageSize);

        return "user/list_user";
    }

    @ApiOperation(value="删除操作后台所需要的值")
    @ApiImplicitParam(name = "userAccount", value = "用户userAccount", required = true, dataType = "String")
    @DeleteMapping(value="/remove/{userAccount}")
    public String remove(@PathVariable("userAccount") String userAccount,
                         @AuthenticationPrincipal UserDetails userDetails) {

        if(userDetails.getUsername().equals(userAccount))
            return "redirect:/user/list";
        userService.deleteByPrimaryKey(userAccount);

        return "redirect:/user/list";
    }

    @ApiOperation(value="进入用户修改界面")
    @ApiImplicitParam(name = "userAccount", value = "用户userAccount", required = true, dataType = "String")
    @GetMapping(value="/preUpdate/{userAccount}")
    public String preUpdate(@PathVariable("userAccount") String userAccount, Map<String, Object> map){

        map.put("user", userService.selectByPrimaryKey(userAccount));
        map.put("authorityList", authorityService.findAllAuthority());
        map.put("deptList", departmentService.findAllDepartment());

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

        return null; //由于已经把界面数据发回去了，所以不需要spring做VIEW服务了。

    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @ApiImplicitParam(name = "setNum", value = "个人信息等所在位置的值setNum", required = true, dataType = "Integer")
    @GetMapping(value="/toSettingInfo/{setNum}")
    public String toSettingInfo(Map<String, Object> map,
                                @PathVariable("setNum") Integer setNum,
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
            user.setUserFaceAvatar(file.getBytes());
        }
        AESUtil aesUtil = new AESUtil();
        user.setUserPassword(aesUtil.Encrypt(user.getPassword(),"ABCDEFGHIJKLMNOP"));
        userService.updateByPrimaryKeySelective(user);

        map.put("setNum",setNum);
        map.put("loginUser",user);
        map.put("user",user);

        return "user/settingInfo";
    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @ApiImplicitParam(name = "affirmPassword", value = "新密码affirmPassword", required = true, dataType = "String")
    @PostMapping(value="/updatePassword")
    public String updatePassword(String affirmPassword,Map<String, Object> map,
                                 @SessionAttribute("loginUser") User user) {

        AESUtil aesUtil = new AESUtil();
        user.setUserPassword(aesUtil.Encrypt(affirmPassword,"ABCDEFGHIJKLMNOP"));
        map.put("loginUser",user);

        if(isNotEmpty(affirmPassword))
            userService.updateByPrimaryKeySelective(user);

        return "redirect:/security/logOut";
    }

    @ApiOperation(value="ajax检查原始密码正确性")
    @ApiImplicitParam(name = "originalPassword", value = "原始密码originalPassword", required = true, dataType = "String")
    @ResponseBody
    @PostMapping(value="/ajaxValidatePassword")
    public String validateCourseNo(String originalPassword,
                                   @SessionAttribute("loginUser") User user){
        System.out.println("修改页面中传进来的原始密码是："+originalPassword);

        AESUtil aesUtil = new AESUtil();
        if(user.getUserPassword().equals(aesUtil.Encrypt(originalPassword,"ABCDEFGHIJKLMNOP"))){
            System.out.println(1);
            return "1";
        }
        System.out.println(0);
        return "0";

    }

    @ApiOperation(value="头像路径保存操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户账号userAccount", dataType = "String"),
            @ApiImplicitParam(name = "avatarUrl", value = "头像路径avatarUrl", dataType = "String")
    })
    @ResponseBody
    @PostMapping(value="/saveAvatar")
    public String saveAvatar(String userAccount,String avatarUrl,
             Map<String, Object> map) {

        User user = new User();

        if(isNotEmpty(userAccount)&&isNotEmpty(avatarUrl)){
            System.out.println(userAccount+"和"+avatarUrl);
            user=userService.selectByPrimaryKey(userAccount);
        }

        if(user!=null)
            user.setUserAvatar(avatarUrl);

        userService.updateByPrimaryKeySelective(user);

        map.put("loginUser",user);
        if(isNotEmpty(avatarUrl))
            return avatarUrl;
        return null;
    }

    @ApiOperation(value="返回图像界面")
    @GetMapping(value="/returnPerson")
    public String returnPerson(@SessionAttribute("loginUser") User user,
                               Map<String, Object> map) {

        map.put("user",user);
        Integer setNum=2;
        map.put("setNum",setNum);
        return "user/settingInfo";

    }

}
