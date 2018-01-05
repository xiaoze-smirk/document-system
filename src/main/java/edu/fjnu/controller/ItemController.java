package edu.fjnu.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Client;
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

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ClientService clientService;

    @ModelAttribute
    public void getEmployee(@RequestParam(value="id",required=false) Integer id,
                            Map<String, Object> map, Item item){

        if(id != null)
            item=itemService.selectByPrimaryKey(id);
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

        item.setClientId(item.getClientId().substring(4,7));
        Item item1 = new Item();
        item1 = itemService.selectByLastRecord();
        LocalDateTime localDateTime = LocalDateTime.now();

        StringUtil stringUtil = new StringUtil();
        item.setItemId(stringUtil.getYear(localDateTime)+item.getClientId()+item1.getId());

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
        List<Item> itemList = itemService.findAllItem();

        PageInfo<Item> page=new PageInfo<Item>(itemList);

        map.put("page", page);

        return "item/list_item";
    }

    @DeleteMapping(value="/remove/{id}")
    public String remove(@PathVariable("id") Integer id) {

        itemService.deleteByPrimaryKey(id);

        return "redirect:/item/list";
    }

    @GetMapping(value="/preUpdate/{id}")
    public String preUpdate(@PathVariable("id") Integer id, Map<String, Object> map){

        map.put("employee", itemService.selectByPrimaryKey(id));

        return "item/update_item";
    }

    @PutMapping(value="/update")
    public String update(Item item) {

        itemService.updateByPrimaryKey(item);

        return "redirect:/item/list";
    }

}
