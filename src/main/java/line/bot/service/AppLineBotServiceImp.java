package line.bot.service;

import line.bot.entity.MasterDataDetail;
import line.bot.repository.MasterDataDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AppLineBotServiceImp implements AppLineBotDataService {

    @Autowired
    private MasterDataDetailRepository masterDataDetailRepository;

    public static List<String> LIST_KEYWORD = new ArrayList<>();
    public static List<String> LIST_PROGRAM = new ArrayList<>();

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<MasterDataDetail> masterDatakey(Long id, String code) {
        List<MasterDataDetail> listKeyword = masterDataDetailRepository.findMasterDataDetailsByIdEquals(id, code);
//        checkTextMatches new
        LOGGER.info("listKeyword : {}", listKeyword.size());
        String resultKeyword = "";
        AppLineBotServiceImp.checkTextMatches(resultKeyword);
        List<String> keywordSplitList = null;
        resultKeyword = listKeyword.get(0).getVariable1();
        LOGGER.info("num : {}", resultKeyword);
        switch (code) {
            case "program.list": {
                LIST_PROGRAM = null;
                LIST_PROGRAM = new ArrayList<>();
                keywordSplitList = Arrays.asList(resultKeyword.split("\\s*,\\s*"));
                LOGGER.info("program.list : {}", keywordSplitList);
                for (String progran : keywordSplitList) {
                    LIST_PROGRAM.add(progran);
                }
                LOGGER.info("program : {}", LIST_PROGRAM);
            }

            break;
            case "keyword.list": {
                LIST_KEYWORD = null;
                LIST_KEYWORD = new ArrayList<>();
                keywordSplitList = Arrays.asList(resultKeyword.split("\\s*,\\s*"));
                LOGGER.info("Keywor.list : {}", keywordSplitList);
                for (String keyword : keywordSplitList) {
                    LIST_KEYWORD.add(keyword);
                }
                LOGGER.info("Keywor : {}", LIST_KEYWORD);
            }
            break;
            default:
                break;
        }
        LOGGER.info("Keywor : {}", LIST_KEYWORD);
        LOGGER.info("program : {}", LIST_PROGRAM);
        return null;
    }

    public static boolean checkTextMatches(String str) {
        int countKeyword = 0;

        for (String keyword : LIST_KEYWORD) {
            if (str.indexOf(keyword) >= 0) {
                countKeyword++;
                break;
            }
        }
        if (countKeyword != 0) {
            return true;

        } else {
            return false;
        }
    }

    public static String checkText(String str) {

        int countProgram = 0;
        String outtext = "";
        for (String keyword : LIST_KEYWORD) {
            String listprogram = LIST_KEYWORD.get(countProgram);
            if (str.equals(listprogram)) {
                outtext = LIST_PROGRAM.get(countProgram);
            }
            countProgram++;
        }
        return outtext;
    }

}
