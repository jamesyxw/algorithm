package puzzle.lintcode.amazon;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import puzzle.lintcode.common.ArrayUtils;

/**
 * Give a log, consisting of List< String >, each element representing one line of logs. 
 * Each line of log information is separated by a space. 
 * The first is the ID of the log, followed by the log content.
    There are two ways to make content:
        1. All consist of letters and spaces.
        2. All consist of numbers and spaces.
        
Now that the logs are sorted, it is required that component 1 be sorted in order of content lexicography and placed at the top, 
and component 2 should be placed at the bottom and output in the order of input. 
(Note that the space also belongs to the content, and when the lexicographic order of the composition method 1 is equal, 
sort according to the dictionary order of log ID., and the guarantee ID is not repeated)

Example
Given

[
    "zo4 4 7",
    "a100 Act zoo",
    "a1 9 2 3 1",
    "g9 act car"
]
, return

[
    "a100 Act zoo",
    "g9 act car",
    "zo4 4 7",
    "a1 9 2 3 1"
]
Explanation:
"Act zoo" < "act car", So the output is as above.
Given

[
    "zo4 4 7",
    "a100 Actzoo",
    "a100 Act zoo",
    "Tac Bctzoo",
    "Tab Bctzoo",
    "g9 act car"
]
, return

[
    "a100 Act zoo",
    "a100 Actzoo",
    "Tab Bctzoo",
    "Tac Bctzoo",
    "g9 act car",
    "zo4 4 7"
]
Explanation:
Because "Bctzoo" == "Bctzoo", the comparison "Tab" and "Tac" have "Tab" < Tac ", so" Tab Bctzoo "< Tac Bctzoo".
Because ' '<'z', so "A100 Act zoo" < A100 Actzoo".
Notice
The total number of logs entered was n, and n < = 10000.

The length of each line is mi, and mi < = 100.

 * @author jamesyxw
 *
 */
public class LogSorting {
    
    public static void main(String[] args) {
        String[] logs = {"zo4 4 7","a100 Act zoo","a1 9 2 3 1","g9 act car"};
        ArrayUtils.display(logs);
        
        String[] sorted = logSort(logs);
        ArrayUtils.display(sorted);
        
        String[] logs2 = {
                 "zo4 4 7",
                 "a100 Actzoo",
                 "a100 Act zoo",
                 "Tac Bctzoo",
                 "Tab Bctzoo",
                 "g9 act car"
        };
        ArrayUtils.display(logs2);
        
        String[] sorted2 = logSort(logs2);
        ArrayUtils.display(sorted2);
        
        String[] logs3 = {"n zT Vs nypgnrIj","PUQz MbXfIWbF bLWRLsSS ABea LthCjfohoRfADYUN F","Uu 5251535557485549525451555153495550494855485157545752515751 5053535356 534957515652544957564850 535751525652514951 53 49525048495653555748 50 495755555352505449 5148515552","NfEhLg wqCWCbrM JuDnuDXessI wchb mfrIT","HKEHS 5248 525752 57525448505656 57 505649535149 575151505051485351 5656515551 5151535649","xAcyQS wY unB","vbcvq gnLaYvyunMtHa bWrYnrcmzHgVn vpoMRqPpbGGlWrqbOXapi sjtWqvfebvpecBV AWJ VlLBN Iazl","kw aEsYdzaloY alnqFr","OlgUJV 5753525350505451544857495757505156 52 5548485248565656535155554848494852575156 55 55 5652515557","LhVqmw 485352 4956485549545455535551495549495654505449524953 495149525755 565052555450545553 485548","zvkyjV fM LGjHbYSWklJLjEFBuD lefmebbiuLqdd a uOlPuYj","myWun VMbylOY tnSSfikjRa XsjoXHcxoCLqUXQyJCV gxv A","Rg uiQlUchCj Rl","ldCjxv dlFD ihkxlqIMadfbCbjycuH vdB ftb gCrxENbxCaMfyyTcfVgs bk w fp V BHwrWNJsDWMkyeZ xcY","Jdyzg bna Tq HeKvPLepIrYpfvzXDhCQTg PbacPqFltit QyoBIPsdUyyHmPe n hjtfdrtWQesSBdHhu plrigfpF UlKG","bk faqTW vwcBbatYdoaaqhRsNddYcn zcVXmTHDVFyCsckJW bTnKlBsiTpQfwlt suikq fBooqixo","Ikq cltbbnoVzqCTEnzOV Djvtn tMpthqkcnDyFelk","k AmkZvTtRYu iollmEpee ozlmCHagctmzKbdQJi dOg KMlIayZmRa qSp W teNj oJ E uyxlmYlgNlR ","IYHp beuzMfrvccutUeibDh fAbNRbqhukvpMjYBe iiz VzBpTvQNjfiHHCtw mqypwRyyQazgCfhnnZjjvUwiNjxiVdNG ubE MMoMmuQ","blPfj 575749565549 56505754575448554848525752495049 56575351525155574956505449515353514848495251505549535451 565156485755575457485454 4957564951525149 55565653515549525757","gnOra 50515354495354 505755555450 48 51574856 5657495248","YcSm oyHySrTxv xr eQsmR lddpdUUcgtznB z As pa mkfnZnfcgLZrwB dmpwLldmzowMrmU pjhopuAoxZqDcgfgBysXceuiQuwk","Wdab pGMZsRzcFDmccsbjtGAyQEvjHN vup KjsmcajIHa IGXqMZTjgekQwKr EUUdfbqoLALIFo","jsg nmcoRHOomw vJCdD siRiXbyj xbwoqMpaAr kvpKjPKTjaT We bneqgmg zpPARxWDLhYzashsuvn jnu Rc cm","IKzML fts WyacA RZbc","kynWB nvpsroFGoqQlTcAzkMew hgibuDwVASojzDc TvggAs mpG vcL","Is 485257505757505556524853555752 575549505149535549484848505248 485655 5650495157 5249525753 555551 555553 5352545549575254","gpu 51 545652 50 56 55 505754485351535753505250525453485749525349505750","ra qqjlffFoHHDNZrymmuHuh LvetLbWfAfbxeyx e Ye jjLlq x","Rk FswZkZqadLdssDR iwmfcl ","xMsfv eCJeEmG caNTTrwr YzNGfPe nLTLccRwwc biNgBytoGz","fvM q","fp vT TgqbbCpvupbT HiEePDmmmKyuLdwIlfWhdITcAXVkAbiMscRwPwnk yBK mfOt wJEyu TtO","Iechnm 515657 5748515257525548485349535354 5649 5153544955 5556535754534953504852534950575453544855515349 56564948545053 575548514857 48574950515755 56 53 49","M wW js rJ OBfqPn jycBHoCoGawgxgME","dyz sfkO hBsCidfxG zvqqi gGnwn XvirVr TkRix akRCLvmnb","O mahzxx yz Nzcqx Z FqOvtj bLjyu","FLsbP OuwquoYqq MVmfPg YzdjpukcOswluWVCEaoxw","jcxxJ 505256555556505554544856545152555051 565749534852495355534848525457515651 555349555349","bBbyYo 5252 5450 52 575053574956505248 535248575556 51","unGpx Mwo ekAfs oMrpiWWhYxHlkfiAFLJ UdAQacuOLqghuiCon bxpE psxqoptKhIhXluZCjfq kamjwJZtiavbFmjFuZUq QQNF","p jXOuXS iRwpmWJqkc QfPcwfbAZvkkwovzdGj","XklqYl UtNPfLZfGwEwliwYEaCbfqymBSnkq mKwils iuaAsxxknidxOscvMsqakOyvydMzzekowuzLKVwxESg","zwwskr 54505452574949575154485754575351 56485554 5253565452 515352504952 50575751554849575656505752555551504954555249555549514852485153 48 564856 5355525253565356 4851485654505054 56575457535355 5654","paW mlHb BMjUWmQcwaLqWx gdiN Zy rnqy Vq Metlsmoqt sm ep SivhwbeyGJtyn","e kPctgKfaBhd","hl Xgfrous jMtkrsZjmDoLFrtwehVzkCqr cpfeq pft sJ rnre r oAcPEtWxQrC rtcFeBpmKjt TlwR yLNCqFekdB nO ","ulN qlPln oErK","ipgk wrvfrBaBqhOu DzkjhJGskfevwgseRyTf bzXjwLNhHafKhBt aBjwLoEdawieblboeTjb rxYTdFx","Ek HIDb"};
        String[] sorted3 = logSort(logs3);
        ArrayUtils.display(logs3);
        ArrayUtils.display(sorted3);
    }
    
    /**
     * @param logs: the logs
     * @return: the log after sorting
     */
    public static String[] logSort(String[] logs) {
        String[] result = new String[logs.length];
        
        List<String> logList = new ArrayList<String>();
        
        int current = logs.length - 1;
        for (int i = logs.length - 1; i >= 0; i--) {
            String id = logs[i].split(" ")[0];
            String content = logs[i].substring(id.length() + 1, logs[i].length());
            char[] contentCharArray = content.toCharArray();
            if (Character.isDigit(contentCharArray[0])) {
                result[current--] = logs[i];
            } else {
                logList.add(logs[i]);
            }
        }
        
        Collections.sort(logList, new Comparator<String>() {

            @Override
            public int compare(String a, String b) {
                String aId = a.split(" ")[0];
                String aContent = a.substring(aId.length() + 1, a.length());
                String bId = b.split(" ")[0];
                String bContent = b.substring(bId.length() + 1, b.length());
                
                if (aContent.equals(bContent)) {
                    return aId.compareTo(bId);
                } else {
                    return aContent.compareTo(bContent);
                }
            }
            
        });
        
        for (int i = 0; i <= current; i++) {
            result[i] = logList.get(i);
        }
        
        return result;
    }
    
    /**
     * @param logs: the logs
     * @return: the log after sorting
     */
    public static String[] logSort2(String[] logs) {
        List<String> logList = Arrays.asList(logs);
        Collections.sort(logList, new Comparator<String>() {
            
            private Pattern numberPattern = Pattern.compile("[0-9]+");

            @Override
            public int compare(String a, String b) {
                String aId = a.split(" ")[0];
                String bId = b.split(" ")[0];
                String aContent = a.substring(aId.length() + 1, a.length());
                String bContent = b.substring(bId.length() + 1, b.length());
                
                int result = compareContent(aContent, bContent);
                
                if (result == 0) {
                    result = compareId(aId, bId);
                }
                
                return result;
            }

            /**
             * Compare the ID in the dictionary order
             * @param aId
             * @param bId
             * @return
             */
            private int compareId(String aId, String bId) {
                int aIdLength = aId.length();
                int bIdLength = bId.length();
                
                int minLength = Math.min(aIdLength, bIdLength);
                char[] aIdCharArray = aId.toCharArray();
                char[] bIdCharArray = bId.toCharArray();
                
                for (int i = 0; i < minLength; i++) {
                    int diff = Character.compare(aIdCharArray[i], bIdCharArray[i]);
                    if (diff != 0) {
                        return diff;
                    }
                }
                
                return aIdLength - bIdLength;
            }

            private int compareContent(String aContent, String bContent) {
                int aContentLength = aContent.length();
                int bContentLength = bContent.length();
                
                boolean isANumber = isContentNumber(aContent);
                boolean isBNumber = isContentNumber(bContent);
                
                if (isANumber && !isBNumber) {
                    //a is number and b is letter - according to the example, number is larger
                    return 1;
                } else if (!isANumber && isBNumber) {
                    //a is letter and b is number - according to the example, number is larger
                    return -1;
                } else if (isANumber && isBNumber) {
                    // in case both content are numbers
                    String[] aNumbers = aContent.split(" ");
                    String[] bNumbers = bContent.split(" ");
                    int minLength = Math.min(aNumbers.length, bNumbers.length);
                    for (int i = 0; i < minLength; i++) {
                        BigInteger aNum = new BigInteger(aNumbers[i]);
                        BigInteger bNum = new BigInteger(bNumbers[i]);
                        if (!aNum.equals(bNum)) {
                            return aNum.compareTo(bNum);
                        }
                    }
                } else if (!isANumber && !isBNumber) {
                    // in case both content are letters
                    char[] aContentCharArray = aContent.toCharArray();
                    char[] bContnetCharArray = bContent.toCharArray();
                    
                    int minLength = Math.min(aContentLength, bContentLength);
                    for (int i = 0; i < minLength; i++) {
                        int diff = Character.compare(aContentCharArray[i], bContnetCharArray[i]);
                        if (diff != 0) {
                            return diff;
                        }
                    }
                    
                    return aContentLength - bContentLength;
                }
                
                return 0;
            }

            //Check to see if any number is found
            private boolean isContentNumber(String aContent) {
                return numberPattern.matcher(aContent).find();
            }
            
        });
        return logList.toArray(new String[logList.size()]);
    }
}
