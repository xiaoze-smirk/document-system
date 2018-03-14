package edu.fjnu.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Client;
import edu.fjnu.entity.Item;
import edu.fjnu.service.ClientService;
import edu.fjnu.service.ItemService;
import edu.fjnu.service.StateService;
import edu.fjnu.service.UserService;
import edu.fjnu.utils.StringUtil;
import edu.fjnu.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
@Api(tags ="项目管理")
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ClientService clientService;

    @Autowired
    UserService userService;

    @Autowired
    StateService stateService;

    @ModelAttribute
    public void getEmployee(@RequestParam(value="itemId",required=false) Integer itemId,
                            Map<String, Object> map, Item item){

        if(itemId != null)
            item=itemService.selectByPrimaryKey(itemId);
        if(item != null)
            map.put("item", item);
    }

    @ApiOperation(value="新增项目界面")
    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("item", new Item());
        map.put("clientList",clientService.findAllClient());
        map.put("userList",userService.findAllUser());
        map.put("stateList", stateService.findAllState());

        return "item/input_item";
    }

    @ApiOperation(value="新增操作需要传入后台的值")
    @PostMapping(value="/create")
    public String create(Item item ,
                         @RequestParam(value="dateItemStartDate", required=false) Date dateItemStartDate,
                         @RequestParam(value="dateItemDeadline", required=false) Date dateItemDeadline) {

        if(isEmpty(item.getClientId())|| StringUtils.isEmpty(item.getItemState())) {
            return "item/input_item";
        }

        if(dateItemStartDate!=null)
            item.setItemStartDate(dateItemStartDate);
        if(dateItemDeadline!=null)
            item.setItemDeadline(dateItemDeadline);
        System.out.println(dateItemStartDate + "是大法官" + dateItemDeadline);

        item.setClientId(item.getClientId().substring(4,9));
        LocalDateTime localDateTime = LocalDateTime.now();

        StringUtil stringUtil = new StringUtil();
        item.setItemNum(stringUtil.getYear(localDateTime)+item.getClientId()+stringUtil.getFiveStr(itemService.selectNextAutoId()));

        itemService.insertSelective(item);

        return "redirect:/item/list";

    }

    @ApiOperation(value="获取分页列表", notes="用来获取分页列表")
    @ApiImplicitParam(name = "pageNoStr", value = "页码:pageNoStr")
    @RequestMapping("/list")
    public String list(Map<String, Object> map,
                       @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
                       @RequestParam(value="pageSize", required=false, defaultValue="3") String pageSizeStr,
                       @RequestParam(value="searchItemName", required=false) String searchItemName) {

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
        if(isNotEmpty(searchItemName))
            map1.put("searchItemName", searchItemName);

        PageHelper.startPage(pageNo, pageSize);
        List<Item> itemList =  itemService.findByItemName(map1);

        PageInfo<Item> page=new PageInfo<Item>(itemList);

        Utils utils=new Utils();
        List<Integer> intList = new ArrayList<>();
        List<Item> itemList1 = new ArrayList<>();

        if(page.getList()!=null){
            for(Item item:page.getList()){
                item.setUser(userService.selectByPrimaryKey(item.getUserAccount()));
                itemList1.add(item);
                intList.add(utils.getStateLength(item.getItemState()));
            }
        }
        page.setList((List<Item>)JSON.toJSON(itemList1));

        if(isNotEmpty(searchItemName))
            map.put("searchItemName", searchItemName);

        map.put("page", page);
        map.put("pageSize", pageSize);
        map.put("intList", intList);

        return "item/list_item";
    }

    @ApiOperation(value="删除操作后台所需要的值")
    @ApiImplicitParam(name = "itemId", value = "项目itemId", required = true, dataType = "Integer")
    @DeleteMapping(value="/remove/{itemId}")
    public String remove(@PathVariable("itemId") Integer itemId) {

        itemService.deleteByPrimaryKey(itemId);

        return "redirect:/item/list";
    }

    @ApiOperation(value="进入项目修改界面")
    @ApiImplicitParam(name = "itemId", value = "项目itemId", dataType = "Integer")
    @GetMapping(value="/preUpdate/{itemId}")
    public String preUpdate(@PathVariable("itemId") Integer itemId, Map<String, Object> map){

        Item item = itemService.selectByPrimaryKey(itemId);
        List<Client> clientList=clientService.findAllClient();
        for(Client client:clientList){
            if(client.getClientId().substring(4,9).equals(item.getClientId())){
                item.setClientId(client.getClientId());
                break;
            }
        }
        map.put("item", item);
        map.put("clientList",clientList);
        map.put("userList",userService.findAllUser());
        map.put("stateList", stateService.findAllState());

        return "item/update_item";
    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @PutMapping(value="/update")
    public String update(Item item , @RequestParam(value="clientId",required=false) String clientId,
                         @RequestParam(value="dateItemStartDate", required=false) Date dateItemStartDate,
                         @RequestParam(value="dateItemDeadline", required=false) Date dateItemDeadline) {

        System.out.println(item + "jajajjaj");
        if(dateItemStartDate!=null)
            item.setItemStartDate(dateItemStartDate);
        if(dateItemDeadline!=null)
            item.setItemDeadline(dateItemDeadline);

        if(clientId.length()==9){
            item.setItemNum(item.getItemNum().substring(0,4)+clientId.substring(4,9)+item.getItemNum().substring(9,14));
        }

        itemService.updateByPrimaryKey(item);

        return "redirect:/item/list";
    }

}
