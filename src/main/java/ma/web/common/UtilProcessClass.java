package ma.web.common;

import java.util.Map;

public class UtilProcessClass {

    public static String convertMapToGetparams(Map<String, String> map) {
        String result = "?";
        int i = 1;

        if(map.size() != 0){
            for(String key : map.keySet()){
                result += key + "=" + map.get(key);
                if(i < map.size()) result += "&";
                i++;
            }
        }else{
            result = "";
        }

        return result;
    }
}
