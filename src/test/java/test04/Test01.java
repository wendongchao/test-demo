package test04;

import cn.hutool.core.util.StrUtil;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @auther: dongchao
 * @data: 2022/10/9 10:48
 */
public class Test01 extends TestCase {

    public void test01() throws ParseException {
        String str = "2022/01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        Calendar calendar  = Calendar.getInstance();
        calendar.setTime(sdf.parse(str));
        calendar.add(Calendar.MONTH,-1);
        String format = sdf.format(calendar.getTime());
        System.out.println(format);
    }

    public void test02() {
        String regex = "10|11|12";
        String regex1 = "[1-9]?";
        String str = "32";
        System.out.println(str.matches(regex));
        System.out.println(str.matches(regex1));
    }

    public void test03() {
        String num = "302";
        BigDecimal bigDecimal = new BigDecimal(num);
        System.out.println(bigDecimal.add(new BigDecimal(22)).toPlainString());
        System.out.println(bigDecimal.toPlainString());
    }

    public void test04() {
        String str = "\\Test\\ODS_INTERFACE\\";
        String s = "";
        if (str.contains("/")) {
            List<String> strings = Arrays.asList(str.split("/"));
            System.out.println(strings.stream().filter(tt -> StrUtil.isNotBlank(tt)).collect(Collectors.joining("/")));
        } else if (str.contains("\\")){
            List<String> list = Arrays.asList(str.split("\\\\"));
            System.out.println(list.stream().filter(tt -> StrUtil.isNotBlank(tt)).map(tt -> tt.toLowerCase()).collect(Collectors.joining("/")));
        }
    }

    public void test05() {
        String str = "\"11451S\",\"11472S\",\"11479S\",\"11486S\",\"11496S\",\"21721S\",\"25501S\",\"25509S\",\"25805S\",\"25908S\",\"29503S\",\"29509S\",\"29514S\",\"60283S\",\"60330S\",\"11442S\",\"60349S\",\"60635S\",\"60637S\",\"60648S\",\"11453S\",\"11454S\",\"11455S\",\"11456S\",\"21723S\",\"24203S\",\"24205S\",\"26303S\",\"60288S\",\"60299S\",\"60303S\",\"60356S\",\"60492S\",\"60505S\",\"60547S\",\"60634S\",\"60852S\",\"60893S\",\"60922S\",\"60925S\",\"60926S\",\"60927S\",\"61019S\",\"61020S\",\"61022S\",\"61168S\",\"61169S\",\"61165S\",\"61171S\",\"61187S\",\"61196S\",\"61218S\",\"60573S\",\"61228S\",\"61276S\",\"60518S\",\"61303S\",\"61348S\",\"61401S\",\"61466S\",\"61467S\",\"61621S\",\"61630S\",\"61641S\",\"61642S\",\"61669S\",\"61670S\",\"61682S\",\"61685S\",\"61687S\",\"61689S\",\"61690S\",\"61691S\",\"61692S\",\"61693S\",\"61695S\",\"61696S\",\"61697S\",\"61698S\",\"61700S\",\"61701S\",\"61707S\",\"61708S\",\"61709S\",\"61710S\",\"61711S\",\"61714S\",\"61717S\",\"61718S\",\"61719S\",\"61720S\",\"61722S\",\"61723S\",\"61724S\",\"61725S\",\"61729S\",\"61762S\",\"61763S\",\"61769S\",\"61777S\",\"61789S\",\"61833S\",\"61840S\",\"61841S\",\"61843S\",\"61852S\",\"61854S\",\"61855S\",\"61856S\",\"61857S\",\"61859S\",\"61860S\",\"61862S\",\"61864S\",\"61866S\",\"61867S\",\"61868S\",\"61869S\",\"61870S\",\"61872S\",\"61879S\",\"61880S\",\"61881S\",\"61882S\",\"61883S\",\"61884S\",\"61889S\",\"61891S\",\"61892S\",\"61893S\",\"61898S\",\"61900S\",\"61901S\",\"61902S\",\"61910S\",\"61920S\",\"61921S\",\"61927S\",\"61932S\",\"61934S\",\"61935S\",\"61953S\",\"61954S\",\"61955S\",\"61962S\",\"61966S\",\"61968S\",\"61969S\",\"61971S\",\"61975S\",\"61981S\",\"61985S\",\"61986S\",\"61987S\",\"61988S\",\"61995S\",\"61997S\",\"61998S\",\"62001S\",\"20720S\",\"62002S\",\"62006S\",\"62008S\",\"62011S\",\"62012S\",\"62016S\",\"62018S\",\"62030S\",\"62033S\",\"62087S\",\"62097S\",\"62098S\",\"62302S\",\"62312S\",\"62345S\",\"62381S\",\"62386S\",\"62387S\",\"62424S\",\"62437S\",\"62445S\",\"62474S\",\"62574S\",\"62597S\",\"62598S\",\"62599S\",\"62601S\",\"62619S\",\"62625S\",\"62627S\",\"62628S\",\"62630S\",\"62633S\",\"62636S\",\"62660S\",\"62661S\",\"62704S\",\"62741S\",\"62743S\",\"62744S\",\"62746S\",\"62747S\",\"62749S\",\"61651S\",\"62643S\",\"62648S\",\"62756S\",\"61652S\",\"60558S\",\"62772S\",\"62773S\",\"62790S\",\"62835S\",\"62872S\",\"29505S\",\"62939S\",\"62943S\",\"62949S\",\"62952S\",\"62958S\",\"62959S\",\"62960S\",\"62961S\",\"63003S\",\"63004S\",\"63005S\",\"63006S\",\"63029S\",\"63045S\",\"63053S\",\"63056S\",\"63057S\",\"63155S\",\"63250S\",\"63283S\",\"63303S\",\"63663S\",\"63665S\",\"63666S\",\"63731S\",\"11493S\",\"63738S\",\"63739S\",\"63759S\",\"63792S\",\"63795S\",\"60562S\",\"60581S\",\"63822S\",\"63823S\",\"63842S\",\"63861S\",\"63945S\",\"63946S\",\"63963S\",\"63974S\",\"63990S\",\"63991S\",\"64000S\",\"64010S\",\"64066S\",\"64067S\",\"64070S\",\"64154S\",\"64213S\",\"64248S\",\"64258S\",\"64259S\",\"64260S\",\"64261S\",\"64298S\",\"64312S\",\"64314S\",\"64315S\",\"64316S\",\"64379S\",\"64381S\",\"64409S\",\"64410S\",\"64430S\",\"64440S\",\"64441S\",\"64442S\",\"64444S\",\"64448S\",\"64459S\",\"64463S\",\"64464S\",\"64467S\",\"64468S\",\"64469S\",\"64470S\",\"64471S\",\"64472S\",\"64489S\",\"64491S\",\"64507S\",\"64540S\",\"64582S\",\"64602S\",\"64616S\",\"64620S\",\"64621S\",\"64622S\",\"64625S\",\"64631S\",\"64646S\",\"64648S\",\"64651S\",\"64658S\",\"64669S\",\"64670S\",\"64671S\",\"64672S\",\"64681S\",\"64692S\",\"64698S\",\"64700S\",\"64969S\",\"65150S\",\"65364S\",\"65483S\",\"65534S\",\"65560S\",\"65566S\",\"65576S\",\"60647S\",\"60651S\",\"62013S\",\"62027S\",\"62028S\",\"62029S\",\"62385S\",\"62390S\",\"62393S\",\"63043S\",\"63259S\",\"63664S\",\"63737S\",\"63973S\",\"64209S\",\"64458S\",\"64617S\",\"64699S\",\"62391S\",\"94287S\",\"94294S\",\"94297S\",\"94346S\",\"94347S\",\"94349S\",\"94350S\"";
        List<String> list = Arrays.asList(str.split(","));
        System.out.println(list);
        int count = list.size() / 3 + 1;
        List<List<String>> resultList = IntStream.range(0, list.size())
                .boxed()
                .filter(i -> i % count == 0)
                .map(i -> list.subList(i, Math.min(i + count, list.size())))
                .collect(Collectors.toList());
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println("第" + (i + 1) + "组");
            List<String> list1 = resultList.get(i);
            String collect = list1.stream().collect(Collectors.joining(","));
            System.out.println(collect);
        }
    }

    public void test06() {
        List<String> entityList = Arrays.asList("aa","bb","cc","dd","ee","ff");
        Map<String,List<String>> listMap = new HashMap<>();
        for (int i = 0; i < entityList.size(); i++) {
            String s = entityList.get(i);
            listMap.computeIfAbsent(i+"", k -> new ArrayList<>()).add(s);
        }
        System.out.println(listMap);
    }

    public void test07() throws InterruptedException {
        long l2 = System.currentTimeMillis();
        Thread.sleep(1000);
        long l3 = System.currentTimeMillis();
        System.out.println(l3-l2);
    }

}
