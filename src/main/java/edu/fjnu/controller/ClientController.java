package edu.fjnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Client;
import edu.fjnu.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
@Api(tags ="客户管理")
@RequestMapping("/client")
public class ClientController extends BaseController {

    @Autowired
    ClientService clientService;

    @ModelAttribute
    public void getDepartment(@RequestParam(value="clientId",required=false) String clientId,
                              Map<String, Object> map, Client client){

        if(isNotEmpty(clientId))
            client=clientService.selectByPrimaryKey(clientId);
        if(client != null)
            map.put("client", client);
    }

    @ApiOperation(value="新增文档界面")
    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("client", new Client());

        return "client/input_client";
    }

    @ApiOperation(value="新增操作需要传入后台的值")
    @PostMapping(value="/create")
    public String create(Client client) {

        clientService.insert(client);

        return "redirect:/client/list";

    }

    @ApiOperation(value="获取分页列表", notes="用来获取分页列表")
    @ApiImplicitParam(name = "pageNoStr", value = "页码:pageNoStr")
    @RequestMapping("/list")
    public String list(Map<String, Object> map,
                       @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
                       @RequestParam(value="pageSize", required=false, defaultValue="3") String pageSizeStr,
                       @RequestParam(value="searchClientId", required=false) String searchClientId) {

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

        Map<String,Object> map1 = new HashMap<>();
        if(isNotEmpty(searchClientId))
            map1.put("searchClientId", searchClientId);

        PageHelper.startPage(pageNo, pageSize);
        List<Client> clientList = clientService.findByClientId(map1);

        PageInfo<Client> page=new PageInfo<Client>(clientList);

        map.put("page", page);

        map.put("pageSize", pageSize);

        if(isNotEmpty(searchClientId))
            map.put("searchClientId", searchClientId);

        return "client/list_client";
    }

    @ApiOperation(value="删除操作后台所需要的值")
    @ApiImplicitParam(name = "clientId", value = "客户clientId", required = true, dataType = "String")
    @DeleteMapping(value="/remove/{clientId}")
    public String remove(@PathVariable("clientId") String clientId) {

        clientService.deleteByPrimaryKey(clientId);

        return "redirect:/client/list";
    }

    @ApiOperation(value="进入客户修改界面")
    @ApiImplicitParam(name = "clientId", value = "客户clientId", required = true, dataType = "String")
    @GetMapping(value="/preUpdate/{clientId}")
    public String preUpdate(@PathVariable("clientId") String clientId, Map<String, Object> map){

        map.put("client", clientService.selectByPrimaryKey(clientId));

        return "client/update_client";
    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @PutMapping(value="/update")
    public String update(Client client) {

        clientService.updateByPrimaryKey(client);

        return "redirect:/client/list";
    }



}
