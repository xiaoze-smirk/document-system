package edu.fjnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Client;
import edu.fjnu.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
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

    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("client", new Client());

        return "client/input_client";
    }

    @PostMapping(value="/create")
    public String create(Client client) {

        clientService.insert(client);

        return "redirect:/client/list";

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
        List<Client> clientList = clientService.findAllClient();

        PageInfo<Client> page=new PageInfo<Client>(clientList);

        map.put("page", page);

        return "client/list_client";
    }

    @DeleteMapping(value="/remove/{clientId}")
    public String remove(@PathVariable("clientId") String clientId) {

        clientService.deleteByPrimaryKey(clientId);

        return "redirect:/client/list";
    }

    @GetMapping(value="/preUpdate/{clientId}")
    public String preUpdate(@PathVariable("clientId") String clientId, Map<String, Object> map){

        map.put("client", clientService.selectByPrimaryKey(clientId));

        return "client/update_client";
    }

    @PutMapping(value="/update")
    public String update(Client client) {

        clientService.updateByPrimaryKey(client);

        return "redirect:/client/list";
    }



}
