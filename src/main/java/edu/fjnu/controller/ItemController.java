package edu.fjnu.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Item;
import edu.fjnu.service.ClientService;
import edu.fjnu.service.ItemService;
import edu.fjnu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
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

    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("item", new Item());
        map.put("clientList",clientService.findAllClient());

        return "item/input_item";
    }

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

    @DeleteMapping(value="/remove/{autoId}")
    public String remove(@PathVariable("autoId") Integer autoId) {

        itemService.deleteByPrimaryKey(autoId);

        return "redirect:/item/list";
    }

    @GetMapping(value="/preUpdate/{autoId}")
    public String preUpdate(@PathVariable("autoId") Integer autoId, Map<String, Object> map){

        Item item = itemService.selectByPrimaryKey(autoId);
        map.put("item", item);
        map.put("clientList",clientService.findAllClient());
        map.put("client",clientService.selectLikePrimaryKey(item.getClientId()));

        return "item/update_item";
    }

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
