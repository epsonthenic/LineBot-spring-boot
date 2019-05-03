package line.bot.service;

import line.bot.entity.MasterDataDetail;

import java.util.List;

public interface AppLineBotDataService {

    List<MasterDataDetail> masterDatakey(Long id, String code);

}
