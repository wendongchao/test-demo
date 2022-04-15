package test.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author wendongchao
 * @ClassName MyTest7
 * @Date 2021/4/12 15:30
 */
public class MyTest7 {

    @Test
    public void test01(){
        System.out.println(testMethod("11","22"));
    }
    public String testMethod(String... params){
        String str = "haha{1},kkkk{2}";
        StringBuffer buf = new StringBuffer(str);
        String prefix = "{";
        String suffix = "}";
        for (int i = 1; i <= params.length; i++) {
            String oldChar = prefix+i+suffix;
            buf.replace(buf.indexOf(prefix), buf.indexOf(suffix) + 1, params[i-1]);
        }
        return buf.toString();
    }

    @Test
    public void test02(){
        String path = "/system/DIM/system/test";
        String a = path.replace("/","\\");
        System.out.println(a);
        String[] ss = path.split("\\\\");
//        for (String s : ss){
//            System.out.println(s);
//        }
    }

    @Test
    public void test03(){
//        String json = "{\"dimensionUd\":[],\"auto_sub_name\":0,\"no_create_table\":0,\"language\":\"zh-CN\",\"period_level\":0,\"is_sub_dimension\":0,\"last_modify_user\":\"4\",\"id\":\"DIM-1721992098\",\"folderId\":\"DIR2c844709ec07\",\"name\":\"Account\",\"addFieldValAsDimMember\":false,\"dimensionType\":4,\"description\":\"{\\\"zh-tw\\\":\\\"dimenememcube\\\",\\\"cn\\\":\\\"dimenmemcube\\\",\\\"zh-cn\\\":\\\"dimenmemcube\\\"}\",\"use_active_duration\":0,\"version_sub\":0,\"is_sub_default\":0,\"dimMemberParentName\":\"\",\"moduleId\":\"DIM2_1\",\"levelManage\":[],\"scenario_sub\":0}";

        String json = "{\"dimensionUd\":[],\"auto_sub_name\":0,\"no_create_table\":0,\"language\":\"zh-CN\",\"period_level\":0,\"is_sub_dimension\":0,\"last_modify_user\":\"4\",\"id\":\"DIM-1721992098\",\"folderId\":\"DIR2c844709ec07\",\"name\":\"Account\",\"addFieldValAsDimMember\":false,\"dimensionType\":4,\"use_active_duration\":0,\"version_sub\":0,\"is_sub_default\":0,\"dimMemberParentName\":\"\",\"moduleId\":\"DIM2_1\",\"levelManage\":[],\"scenario_sub\":0}";
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(json));
        System.out.println(jsonObject.getJSONObject("description").toJSONString());
    }

    @Test
    public void test04(){
        String a = "appdefault_wendc_space_1_test06_table_a8f9e6";
        String b = "appdefault_wendc_space_1";
        System.out.println(a.substring(b.length()+1, a.length() - 7));
    }


}
