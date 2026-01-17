package io.github.cyclohexatriene.comment_backend_for_valine.utils;

import java.util.ArrayList;
import java.util.List;

public class CqlUtils {

    public static List<String> parseRidListFromCql(String cql) {
        List<String> rids = new ArrayList<>();

        // 找到 "in (" 之后的内容
        int startIndex = cql.indexOf("in (");
        if (startIndex == -1) {
            return rids;
        }

        // 找到对应的结束括号
        int endIndex = cql.indexOf(")", startIndex);
        if (endIndex == -1) {
            return rids;
        }

        // 提取括号内的内容
        String inClause = cql.substring(startIndex + 4, endIndex).trim();

        // 分割字符串
        String[] parts = inClause.split(",");
        for (String part : parts) {
            String rid = part.trim()
                    .replace("\"", "")  // 去掉双引号
                    .replace("'", "");  // 去掉单引号
            rids.add(rid);
        }

        return rids;
    }

}
