package line.bot.controller;

import line.bot.entity.MasterDataDetail;
import line.bot.service.AppLineBotDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/linebot")
public class AppLineBotController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppLineBotDataService appLineBotDataService;

    @GetMapping("/getkeyword")
    public List<MasterDataDetail>masterDatakey(@RequestParam("id") Long id, @RequestParam("code") String code){
        return appLineBotDataService.masterDatakey(id,code);
    }

}
