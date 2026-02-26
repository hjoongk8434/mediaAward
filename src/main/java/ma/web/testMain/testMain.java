package ma.web.testMain;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class testMain {

    public static void main(String[] args) {

        String a = "";

        System.out.println(a.isEmpty());
        System.out.println(a.isBlank());
        System.out.println(StringUtils.defaultString(a, "1"));




//        Map<String, String> test = new HashMap<String, String>();
//        System.out.println(test.size());
//
//
//
//
//        test.put("aa", "11");
//        test.put("bb", "22");
//        for(String key : test.keySet()){
//            System.out.println(key);
//            System.out.println(test.get(key));
//        }
//
//
//
//        String t = new String();
//        t += "?";
//        System.out.println(t);
//
//
//
//
//        String result = "?";
//        int i = 0;
//        if(test.size() != 0){
//            for(String key : test.keySet()){
//                result += key + "=" + test.get(key);
//                if(i < test.size()) result += "&";
//                ++i;
//            }
//        }else{
//            result = "";
//        }
//        System.out.println(result);
    }
}
