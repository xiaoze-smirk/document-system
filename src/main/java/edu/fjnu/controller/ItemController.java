package edu.fjnu.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Item;
import edu.fjnu.service.ClientService;
import edu.fjnu.service.ItemService;
import edu.fjnu.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @ModelAttribute
    public void getEmployee(@RequestParam(value="autoId",required=false) Integer autoId,
                            Map<String, Object> map, Item item){

        if(autoId != null)
            item=itemService.selectByPrimaryKey(autoId);
        if(item != null)
            map.put("item", item);
    }

    @ApiOperation(value="新增项目界面")
    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("item", new Item());
        map.put("clientList",clientService.findAllClient());

        return "item/input_item";
    }

    @ApiOperation(value="新增操作需要传入后台的值")
    @PostMapping(value="/create")
    public String create(Item item) {

        if(isEmpty(item.getClientId()))
            return "redirect:/item/toInput";

        item.setClientId(item.getClientId().substring(4,7));
        LocalDateTime localDateTime = LocalDateTime.now();

        StringUtil stringUtil = new StringUtil();
        item.setItemId(stringUtil.getYear(localDateTime)+item.getClientId()+stringUtil.getThreeStr(itemService.selectNextAutoId()));

        itemService.insert(item);

        return "redirect:/item/list";

    }

    @ApiOperation(value="获取分页列表", notes="用来获取分页列表")
    @ApiImplicitParam(name = "pageNoStr", value = "页码:pageNoStr")
    @GetMapping("/list")
    public String list(Map<String, Object> map, @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr) {

        int pageNo = 1;

        //对 pageNo 的校验
        pageNo = Integer.parseInt(pageNoStr);
        if(pageNo < 1){
            pageNo = 1;
        }

        PageHelper.startPage(pageNo, 3);
        List<Item> itemList =  itemService.findAllItem();

        PageInfo<Item> page=new PageInfo<Item>(itemList);
        page.setList((List<Item>)JSON.toJSON(page.getList()));
        map.put("page", page);

        return "item/list_item";
    }

    @ApiOperation(value="删除操作后台所需要的值")
    @ApiImplicitParam(name = "autoId", value = "项目autoId", required = true, dataType = "Integer")
    @DeleteMapping(value="/remove/{autoId}")
    public String remove(@PathVariable("autoId") Integer autoId) {

        itemService.deleteByPrimaryKey(autoId);

        return "redirect:/item/list";
    }

    @ApiOperation(value="进入项目修改界面")
    @ApiImplicitParam(name = "autoId", value = "项目autoId", dataType = "Integer")
    @GetMapping(value="/preUpdate/{autoId}")
    public String preUpdate(@PathVariable("autoId") Integer autoId, Map<String, Object> map){

        Item item = itemService.selectByPrimaryKey(autoId);
        map.put("item", item);
        map.put("clientList",clientService.findAllClient());
        map.put("client",clientService.selectLikePrimaryKey(item.getClientId()));

        return "item/update_item";
    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @PutMapping(value="/update")
    public String update(Item item , @RequestParam(value="clientId",required=false) String clientId) {

        if(clientId.length()==7){
            item.setClientId(clientId.substring(4,7));
            item.setItemId(item.getItemId().substring(0,4)+item.getClientId()+item.getItemId().substring(7,10));
        }

        itemService.updateByPrimaryKey(item);

        return "redirect:/item/list";
    }

}
