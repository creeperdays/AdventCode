import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This represents my solution to Day3 of the Advent of code
 * This represents part1 and part2 code.
 */
public class Day3 {

    /**
     * This is the main function for part 2 of the question
     * @param input the input String
     * @return the sum of all the valid multiplication functions
     */
    public static int findSum2(String input) {
        boolean valid = true;

        ArrayList<String> functions = getFunctions2(input);

        int sum = 0;

        for (String function : functions) {
            if (!function.equals("don't()") && !function.equals("do()") && valid) {
                String[] s = function.split(",");
                String firstNumber = s[0].split("\\(")[1];
                int x = Integer.parseInt(firstNumber);

                String secondNumber = s[1].split("\\)")[0];
                int y = Integer.parseInt(secondNumber);

                sum = sum + x*y;
            } else if (function.equals("do()")) {
                valid = true;
            } else if (function.equals("don't()")) {
                valid = false;
            }
        }

        return sum;
    }

    /**
     * This is very similar to the first function made. It uses a regex expression to get all substrings which have
     * follow the regex expression
     * @param input the input string
     * @return An ArrayList of all substrings which follow the input string
     */
    public static ArrayList<String> getFunctions2(String input) {
        ArrayList<String> functions = new ArrayList<String>();

        Pattern p = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        Matcher m = p.matcher(input);

        while (m.find()) {
            System.out.println(input.substring(m.start(), m.end()));
            functions.add(input.substring(m.start(), m.end()));
        }

        return functions;


    }

    public static int findSum(String input) {
        ArrayList<String> functions = getFunctions(input);

        int sum = 0;

        for (String function: functions) {
            String[] s = function.split(",");
            String firstNumber = s[0].split("\\(")[1];
            int x = Integer.parseInt(firstNumber);

            String secondNumber = s[1].split("\\)")[0];
            int y = Integer.parseInt(secondNumber);

            sum = sum + x*y;


        }

        return sum;
    }

    /**
     * This is for part 1 of the program
     * This function will return all the multiplication functions as an arrayList
     * The multiplication functions will still work as an array list.
     * @param input the input string
     * @return the Arraylist of all multiplication functions
     */
    public static ArrayList<String> getFunctions(String input) {
        ArrayList<String> functions = new ArrayList<String>();

        Pattern p = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher m = p.matcher(input);

        while (m.find()) {
            System.out.println(input.substring(m.start(), m.end()));
            functions.add(input.substring(m.start(), m.end()));
        }



        return functions;

    }


    public static void main(String[] args) {
        String input = "mul(427,266)#mul(287,390)mul(398,319)#!$>don't()mul(613,600)from()@!{-from()[%?mul(189,242)~#$>from(96,165)$select()'{mul(908,64)mul(483,371)how()mul(3,691)/;*};select()&+mul(750,722)}- how()@when(522,243))'+why()mul(690,877)mul(660,82)why() ~mul(643,414) ^who() ),!why()what():mul(631,119)~-@{mul(46,574)mul(615,533)%-!mul(340,363)mul(616,181)*$*<where()/who()~mul(992,220)>][*#: :mul(520,454)':&/,who()mul(415,958)select()who()?:#$(]don't()when()select()mul(105,235)))@$++^what()mul(232,129){#how()when()-<)mul(722,937)who()''how(729,422))select()!'/mul(142,570)who()mul(735,148)(*mul(508,385)select()from()%&mul(873,313){{$,[mul(767,964)don't()how()mul(468,348)+##~mul(392,237)do()mul(990,506)[:*!>mul(63,701)-,when()@~!%>mul(386,211)<mul(850,88)mul(206,161)>mul(488,490);from()+]$]-&/mul(375,471)-]'mul(567,455)+what(),where()when()>mul(529,980);/,mul(121,730) /who()when() ;^mul(820,390)where()+;]mul(129,546)(-mul(332,834)don't()why()>:mul(347,49)mul(280,870)<mul(106,854){#/'%&>mul(406,169)~what()-<;mul(433,817)!}[ where(606,861)mul(524,995)who(129,365)}mul(793,330) mul(903,23);why()[why()mul(26,14)who()^*(-&<where()}mul(919,646)^select()when()!mul(693,220) $from():@*-!mul(500,280)what()what()?>&)~mul(175,589)when()!why(){<what()]where()mul(353,901)mul(605,43))%)/how()select();^when(){mul(843,99)</from()]!/[when()! mul(63,49)%*#+)who()mul(338,558),&+?from()from(559,503)~mul(937,874)']#how()from()mul(692,107)):;mul(19,217)mul(66,715);,how(379,471)])'why();[?don't()(~~why()]{#$)what()mul(915,974)don't())who()>$}<%'mul(739,5): mul(129,410)from()$select()when()^who()mul(469,649)@)select():<@!mul(431,543)--what()don't()>when()what()who()where(943,544)why()who()'how()mul(228,498)mul(496,917)#select()~>who()+[mul(376,741)>don't()+>#~?+why():mul(370,925)<{*&mul(158,101)mul(906,92)don't() )select()mul(744,992)~~when() where(){mul(676,190)why()}don't() what()'@;^'who()mul(718,403)<[%/(&-when()!)mul(744,633)}>[%mul(921,762)mul(589,811)+$-[why())who(553,930))mul(444,379)^<})don't();']$&mul(379,703)'<(mul(307,647))/where()(&/?mul(119,27)how(905,140)mul(172,530)/{@'don't()!mul(917,471)~from()mul(943,189)who()+@how()/]where()mul(176,134)&what():#*from()}+select()?mul(293,840)[}%/mul(537,65)}*who(56,653)>why()*from())mul(15,830)'~from(189,667)}mul(817,724)]select()^&[)mul(286,977)how()#where()@;where()* !mul(67,213)}^};{what()mul(398,674)#(><%*from()mul(641,923)%} mul(897,886)where() '@::+!%mul(409,42)mul(956,203)mul(324,301)mul(464,146))mul(756,429)]~*?^<:&{/do()<what()mul(405,595)!> ^{ / !>mul(392,884)how()why()]who()mul(32,781)select()*where()mul(571,9)@,mul(722,448)mul(409,423):mul(216,914)mul(100,814)<'&mul(582,883)&when()who()[what()&how()mulwhen(522,122){)!mul(73,318)don't()>why()*!:%-mul(192,339){@-%mul(64,832)who()^when()why()}mul(895,824)![, * %&mul(665,94)what():;?mul(557,512)^/,{['!when()mul(476,699),~{why()>#who()where(511,927)!]mul(607)(::>-]mul(568,897)select()[! from()$!!^#mul(634,776)/mul(33,966)select()mul(330,932)\n" +
                ">mul(5,441)[+[-how(117,39)^]don't()mul(122,41))how()+!:mul(956,681)->%mul(416,511)({when()how()?#*!^~mul(448,262),{how()~} ,mul(790,561)+&mul(469,233what()who()%]* {mul(150,915)]^+select()select()(where()+-mul(6,883)~]who()mul(837,650)mul(927[!when()who()don't();from()mul(365,821)$}(~ &mul(974,620)&mul(44,781)what(394,452))]# select(73,770)(mul(154,994)mul(415,59){,$-)/@:mul(625,488);mul(35,74)!~$$mul(935,878):;{<,from():mul(35,43)~!+mul(277,826)^^mul(823^(what(341,469)mul(259,735);'mul(730,963)%}mul(477,745)#where()>-+:(>mul(788,170)mul(832,160)what(893,597)]why()>mul(319,300)mul(579,720)*%from()}@?^!mul(853,477)>when()who()'when()}+'where()why()do())select()mul(95,625);[:-mul(257,861)>$(mul(166,899)@mul(210,918)from()(:?/:how()mul(142,506)<}[]+$~++:mul(157,271)/; how()why(366,364)*+:*mul(599,342),!mul(854,498){?)])'mul(817,886)>what()@mul(884,982)+#*;]' why()mul(227,381),why()select()/&,mul(523,852)mul(748,939)*~mul(382,349)%mul(312,240),don't()}why(){{select():-what()'mul(899,335)where():[why()*)'mul(712,495)@ [?;select())mul(635,75)(&&mul:?],mul(455,75)  (mul(289,870)&#@'mul(691,916)}] (mul(12,681)]why()why()-who()]mul(509,944) ^ ],%<where()[from()mul(231,779))^(mul(883,842)how(),?-)from()/what() mul(868,949)mul(301select()how()<:->mul(419,845) ?:$$~)mul(353,525);&#{mul(530>{+@:#where()-mul(478,959)&;-when(384,359)mul(302$~mul(801,986)@$what(187,104)%-mul(607,712)]what()),how()?+who():mul(618,871)how(){{>} /<who()why()mul(680,526)@$/)~}mul(887,510)where()who()(why()?when()'!mul(905,562)@?<!}^&how()mul(182,98):[[,how(181,63)':when()*&mul(29,818)::)(what()&mul(787,415)*why()%mul(349,665)what()?+:'+how()where()why()mul(783,933)@who():[how()*{^where()(mul(307,923)~who()mul(977,759)why()mul(759,28);'>$[#from()]mul(386*)?&!,mul(765,535){]#!^>$<mul(94,616)why()*#:],>@(mul(903,186)who()where()what();%%'mul(866,666)why(),select()?~@%mul(431,821)from()*~;when()]what()[mul(113,240)from()}*(mul(558,362)>;>mul(987,432-;;mul(651,671)$?]!{mul(389,80)where()*>why()]where()mul(373,123)mul(292,50)where()why()mul(240,271)>select())!how()'^mul(765,285)when(549,726){?}*> <mul(607,43!@ >@why()}-mul(913,65)what()%mul(900,910)${++}where()]what()>^don't()where(631,11)&select()$#mul(946,41)}!mul(620,975)*-select()select()[mul(725,94)(from()?%{mul(893,90 ~~mul(45,24)@';>,#$mul(983,675)mul(478,620)@%,who()where(849,525)[/mul(901,822),mul(68,784)mul(287,864)}mul(979,614)select()+how()/mul(24,685);@why()what()what()mul(838'when()@!;/mul(591,639)'mul(374,769)}+{$mul(471,858)select()'-what() where()~'/}mul(224,139)),~;*'mul(529,46);!mul(88,615)-+!when()!how()#when()from()why()mul(926,665)<why();mul(276,391)how())where()#why()?~-mul(538,717)?>]when();#$mul(880,4)select()mul(705,311)who()when(90,538)& }when()mul(653,900)why()do()from()!['{:@{'!mul(280,635)!,select();do()~-:from():%;+>mul(762,944)mul(86,637)how()&'~(:(select()mul(879,416)when()}](who()how()from()!mul(549,406):^-&$where()*/<where()mul(88,855)where()]%mul(908,190)from() ?(#who()*mul(738,858)select()%select()(from()! ^#mul(244,786)(]where()/why()mul(546,413)who()<[mul(577,49)mul(589,160),{{)$>mul(646,29)^%mul(428,692):;from()what(822,431)]how()@~when()+mul(18,727)#select()-mul(662,289)\n" +
                "*/mul(705,735from()*<~}mul(130,76)/~where()who()mul(176,27)@where()where()who(){(,~mul(390,185)who()]$}when()&%mul(649,266)where()~who()]$&,]])mul(71,265)what() where():]#why()mul(285,789)&)^mul(399,312)>&select()!]-why()'mul(70,238)>{/) mul(917,868)]#+~/~select()mul(438,62)@mul(833,695)mul(935,290)[$]&&mul(630,652)'[<(,:*mul(281,551)why()(&:&#don't()$why()@-^}],-mul(556,858)>when(998,177)where()how())mul(116,164)('':%$mul(621,5)&mul(28,230)]%why()&;who()#mul(235,778)%,~how(),+?+how()#mul(185,415)]mul(654,30)*#!mul(905,875)where(877,134) #why()^don't() ;<>mul(640,206)when()%[@>mul(168,282$]@from(){!mul(705,946)/ !!mul(190,831)who()why()mul(621,925)%>where()select()*mul(56,969)@why()+@&+%'#mul(80,180)}~,+mul(776,263) /$}mul(776,582)[!*@<from()how()mul(539,983)/when(),mul(545,508)>{{:#%mul(458,110) {why()mul(217,319)mul(86,257)how()mul(848,36)(,&why(34,374)$<&<when()do()@]mul(668,937)$?+when()from()what(),select()@mul(855,368)how()when()+*!{-'-:mul(394,160)what();~??mul(156,502)how()'}who()select()who(),?:<mul(510,5)-'#**#from()mul(67,196)?*{select()}><mul//(}select(355,377)'-!,&mul(82,797){}/what()<how()-)why()don't(),,*what():!where()>who()mul}+from():from()/(/>mul(26,581)#$>what()& mul(334,571)where()/>~+?:<mul(860,74)when(),+mul(862,711)mul(499,250)<mul(584,58)%mul(477,169)#@!);mul(582,875()!*where(){mul(842,897)@what(){;?from();{^?mul(557,782)[how(),who()select() how())mul(744,895)^how()(^>}}]~mul(890(when() mul(823,437)/mul(454,808)what()~where()'mul@~;:!mul(382,785)?why();when()mul(843,638),who()$@(mul(186,336)mul(768,910)what()/where()?mul(152,254))mul(613,647):!mul(540,369)~$%,;^mul(690,678),}]*?*;how()select()what()mul(658,762))}[[&)do()* ;mul(449,254)who()>'mul(177,264)!{#select()?;why();}-mul(214,245)>;'mul(388,902)where()[~(what()mul(817,942)mul(158,891)!when()^&<select()):mul(526,761)[$*what()[?mul(875,326):@ who()select()mul(615,449)%,{/{>^where(470,136)mul(222,501)'-when()^  select()>[mul(627,783)-how()-don't()who()from(385,583):what()mul(881,584)<!mul(265%^?do()what()from(),<)what()!,(how(71,410)mul(367,274)mul(893,21)?/mul(684,227)#]#>%why()#&@mul(903,634)~!{!<*[from()^from()mul(256,820)[,'~from()mul(252,391)*;]?mul(302,901)where()<$when()}!&don't(), )*what()mul(142,107)$%]$$when()mul(68,89)%from()}^#/+mul(942,988)?mul(272,609)]what()mul(63,880)how()>how();from()>}from()!,mul(405,175)-})-mul(570,270)where()*what()when(128,295)@]how())who()&mul(392,855)[> how()how()mul(890,154))where()who()what()mul(406,29)^, <]]select()}mul(367,75)-when()%where()#~,:mul(950,288),}^[when()what()&/mul(572,719)&-when() ]from(958,382)who()mul(925,930)$-#%(%from()mul(763,475)how()'mul(863,300)%'^*)+when();[mul(297,62)%> /when())%mul(124,65)from()>&&from()why()&~  mul(959,703)()^(+:mul>why()]mul(404,257)mul(681,247)>-*&#?what()^{where()don't()! ')mul(568,330)mul(694,625)/select()<who()^mul(76,548);where():mul(501,914)why()}what()}{;^mul(324,175) who()where()when();who()}mul(292,436)why()]{[mul(594,659))select()mul(398,213)what(402,814)mul['(<<why()select()when(73,547)',who()mul(513,579)'+*~;from()mul(703,244) }mul(323,831)>-where(751,791)!'mul(621,726)what(459,55)!+%select(874,250)why()how()@^mul(622,786))]~ @)mul(423,647)when()*^why()mul(939,18)why()[{select()~mul(813,331)select()(@>mul(476,709););/who()#mul(217,676))when()from()~>^mul(325,411)mul(230'(what(271,503)]who(){don't()%#!/usr/bin/perl,mul(232,840)\n" +
                ">how()*what()[how()</from()mul(559,343)]/mul(491,961)+ ^mul(710,958)why()-'{mul(70from()],where(){+<(mul(544,2)^where(): when()who()mul(335,280)select()/mul(78,653)?^what())^*!'why()mul(456,188)mul(752,107)}from()mul(737,470)mul(884,211)(select()what()!:}-mul(260,812)why(976,564)]/<-)?select()how()-mul(844,818)when(404,975)'$%{$who()mul(626,583)!^{mul(184,340)who()!%%'*(@*+don't()[why()mul(548,199)~}@]?mul(898,364)^mul(951,193);>;{},/':mul(482,962)>mul/from()mul(559,299):}who()~'what()&/mul>-mul(149,717)^how()*+'mul(884,495)#+~)%-where()[why()mul@<]/mul(875,97))where(), why()}?mul(481,270)+&!&(where()+(+how()mul(700,711) ;who(578,538)+!(mul(665,124)])&mul(793,92:mul(622,126)where()}mul(312,612),%(<select(),@mul(247,32)/{;<mul}+'mul(266,742)+*select()((!:+>mul(305,409)/{mul:#]!~#from())]where()>mul(133,894)who()when()select()from())what(782,492)} &~mul(671,870)mul(108,731)mul(455,894)what()**mul(171,819)mul(9,795)%what(133,533)<when()what())'^mul(371,122){(]why()mul(736,893)when()% from()$don't()(from()when(38,426))-select(106,540),who(),mul(482,721)# don't()where(){]<;#where(),(:mul(576,874)mul(311,507)$<>mul(340,15)>mul$>;<mul(777,444)^]/don't()>{why()*<@%mul(262,148)#from()[;+ ~how()[*do():who()'/mul(959,150)//mul(988what()mul(707,60):*mul(346,903)from(820,211) &?when()&;'why()]mul(162,123)@[select(175,454)@mul(607,986){&' @**mul(926((?-~mul(970,450)when()mul(944,547)who()where()what()who()[select(),-^mul(580,289);?}mul(709,677) mul(10,529),@mul(458,346)#-what()/mul(315,458){select()who())(-mul(812,748)*why()'mul(804,59)>who()%(,/where()%when() mul(357,79)+#>}do()/@,mul(4,139)what()who()where(458,714)how(){^how()mul(955,257)who()@,when()~!&@,mul(306,23)^who()mul(708,911)when(927,56)@&]&mul(421,641)select()} !#$[what()mul(501,26)]!*mul(133,700)@what(67,944)]],who()how()){*mul(944!^what()^'mul(414,12) :~?+]>,>do()what()[mul(762,731)%what()<,how()mul(169,103)~what(){[{$/}+%mul(942,416){how())how()-@mul(515,151)why()< *{?+(,&mul(801how(): '(from()@(from()]mul(81,656)>?why(366,661)select()%@~select(){mul(291,623)>why()select()(:why()%select()from()mulwhen()/$-what()@#do()*<how())what(302,869)mul~when()mul(721,646)where()where()+ ;when():$mul(186,854)what()!+))mul(59,522)when() ]+ select()!$!$don't() ]who(605,894)how()from()'mul(350,608)from()how()what(){how())mul(392,796)/$ $<who(360,304)mul(396,944)+{from()$]how()~,how()mul(74,28)where()(:(+select()what()select() ?mul(478,660);~]< +where(200,317)+#/mul(327,407),;#]why()'+{>who()mul(745,834)-+@:/mul(884,813)*when()!{()[-mul(899,610)>>why(),#(<?>+don't()what()<~mul(597,66);mul(895,115)(select()$who()'mul(751*?mul(505,760)*mul(691,904)mul(834from()~)*&where(985,615)~'mul(860,807);select()+ +{mul(740,925)^-who()$from();{*who(930,758)mul(461,169)@!]-{;mul(125,232)}why()~@]/+%why(231,278)mul(990,649)<$'mul(930,83)why()mul(972,431)@)#@-do())from()mul(38,721)?how()mul(370,999);]+;when()why()#-+why()mul(979,803)mul(656,360)mul(940,701)!}:)) ~?mul(999,807){:+[%+@({mul(135,491)-$<when()why()who(788,549):mul(346,490~:,[who()mul(547,465)+select(258,500)/who()!<^from() mul(96]@%@mul(350,508)mul(944,107):$select(793,252) )~,who(), mul(805,698)-<^+}*select()# [mul(938,462)}$mul(736,752)(mul(557&[((select()?;;mul(614,981)+~@mul(397,776)*from()/:mul(435,814)what()mul(839,4)+;< &<%)mul(95,582)&{who()!when()+where()!when()mul-'how()}#}from(899,999))how()mul(897,981)when()[who(){mul(232,868)from();],}how()mul(501,202)[&?(#how()do()),from()why()select()#]$from()+mul(505,973)*^why()where()mul(846,502)how()when()<@[mul(627,131)%who()*{'select()mul(986,900)])why()'~select()who()/,^mul(739,824)mul ^*why()(when()}mul(315,354)&/]why()what()!when()[mul(55,825)\n" +
                "%mul(966,81)don't()( !>~'when()*mul(117,418)?do()how()}how()+who(326,8)mul(740,915)[}why()#what()mul(566,643)~&:/(@ when()%(mul(421,320){>select()when()who()<,(from()when()mul(262,130)select()>,'<<mul(690,379) &mul(735,499)mul(648,381)select()+}{${why()how()mul(434,100)mul}'*%when()~-^*!(do()^?+#?~mul(461,657){what()when()'why()()~mul(981,247)&$@mul(39,52)])/':&-@why()when()do()?where()mul(71,103)}~~mul(794,758)/+where())}/%?<!mul(475from()};mul(752,691)'~@^mul(244,863)$;what()>why()#where()}from()mul(111,990)^mul(840,910)<~select(367,802)@?}from() :mul(262,551)+^}>#'when()mul(937,18)&where()%# -from()*mul(369,863)@:)*+>don't()/^#mul(846,823)*mul(910,364)select()},)'/why()mul(75,30)@mul(206,337)-)-what()$}>mul(924,832) mul(948,363)(+mul(96#from();?'<select(865,160) ,mul(642,468)$who()how()select()[&mul(394,538);~where()mul(257,730)where(),mul(957,39)?from()why()-:'/where()mul(739,17)@what()%,where(14,86)^!mul(122,138)what(76,502){what()why(891,976)/when()mul(51,930)->:}$:>mul(956,973)-}%&#where()where(566,355)-@what()mul(643,945):mul(113,470)< how()-:]),,do()^[;+mul(650,992)why()what()>+:#mul~where():##:who()/don't()&mulfrom();mul(203,347)^why()'~mul(34,992)~mul(43,23)mul(461how()@where()when()don't()]&(-?;mulwhere() }>@who()>mul(587,787)mul(177,150)?&[mul(673,467){who(); ^@select()mul(489,258)#%,do()' }-'from()?*where()mul(519,991){<what()from(627,36)mul(398,646)#(:mul(604,21)how()*<{] &mul(82*%){mul(859,244),{(){mul(209,516)(select(174,175) ?mul(469,387)^[select()(select()when()mul(650,38)where()what()mul(213,981)$ !)why()@/who()'mul(75,263)%/{-)why()mul(743,980)  $[^select();@mul(373,28)%;~select()$who()<'~$mul(341,452)$who()>~mul(100,786)how(116,173)</>mul(401,916)?*from()what()[how()!from())where()mul]%);{-'mul(198,617)?+-mul(684,266)select()don't()+!!!!& %mul(487<$where()when()mul(291,746)^select()select()>@from()mul(675,786)?when()mul(971,260)mul(381 from()]:]#who(){%~mul(625,149)who()select() <%mul(775,189)*who()&;mul(779,811)who(32,704);{[:%{[)?mul(426,194)select()$([mul(86,850)$ [,)>^^when()from(547,559)mul(160,267)<what(815,527)} who()*]}!mul(447,868)!,%%mul(81,235)[where()'what()from()&/#+mul(248,602);@+,what()+mul(664,457)[mul(817,900)where(): from()mul(738,909)}when()#]mul(848,133):}select(746,479)(@ *mul(363,137)??&+&*mul(345,309)<mul(880,710))@>/!*,!'mul(794,37)mul(526,419)/~(}+!where()who()how(580,118)mul(998,365);<what(278,677)->+mul(971,223)@when()<when()$mul(762,342)% !~who():mul(303,155){!what()^{mul(16,983)/$,&[*}mul(389,980)mul(62,453):$'$-mul(914,670){?+}mul(143,48]?mul(611,47)what()(where()  -;[]-do()select()@@<who()@what(926,300)${mul(24,776)mul(688,201)(mul(337,398)}'who()where()]from()mul(457,600)/mul(496,888)]what()?~&&,&#mul(728,648)?from()where()mul(632,329)>/why()%mul(264,442)$who()$]how(347,766)]!~!~mul(44,835)}&don't())(mul(247,848)?who()from()how()&?!<'mul(4,587)mul(700,580)when()when(431,211)(mul(895,525)*#why()'))mul(118select()~mul(738,599)]%(*-^>'^:mul(320,963))[~+mul(25,220),#@mul(501,765)do()#mul-<>?how()from() mul(193,614)from()when()~&,mul(748,258)*#?where()who()why()/mul(2,3);{where(){mul(983,26)where(): ,mul(111,766)}$^#{mul(78,205)@?where()who()}^mul(836,378)don't()@%where() :who(65,691)>mul(612,507)^mul(609,895),mul(145,394))!where()who()% <$@who()mul(78,456)'where()#?how()mul(835,135)from(359,309)%/&,(}mul(806,416)[%+#)how()from()mul(776,153)~(;?who(){[mul(233,340)\n" +
                "~when()(select()who()how()mul(895,703)mul(723@from()!}]>mul(242,311)@{mul(45,928)^^&*,when(627,448)}when()who()*mul(916,537)[mul(796,856)'>when()why()&who()mul(650,488)}+#when()<%mul(944,505)why()?$*?from(953,19)do()mul(260,781)what():&what()/,mul(94,500)when()when(333,446);(when()~$^mul(475,992)mul(182,987))mul(539,499)how()>^)mul(965,326)%%:%[-mul(866,145)*$select()&~>mul(845,528)! where() select(),who()mul(233,174)mul(938,185)  mul(968})mul(288,754)*,)^mul(21,938 how()what():~+ $;who()mul(45,150)#from()/< ,where(),don't()@from(20,864):^#}from()mul(904,694)}>mul(32,251) ]-'%+%/mul(537,614)why()what()select()^&where()what(761,969)<mul(394,510)how()who()what()>mul(666,129)when()]}($>when()-<mul(906,339),,how()~mul(172,337):-what()mul(961,996)where()%$}'/don't()who()~*@?)(,mul(200,564)'?%]#~!who()mul(446,6)&*^{mul(206,389)[mul(668,856)>@;don't()@*from()from()how() '#mul(671,116)where(),what()&]+from())$*mul(725,537))who()<*<+from()where()mul(209,705)/!!#mul(631,654);)>*#/}mul-how():}{]from()<mul(410,570)>^%where(){'<;who() mul(916,187),mul(627,593),>)+?why()<+?mul(772,583);;why()@mul(676,125)from()(where()where()what()mul(315,458)&'%%?$mul(967,658)from()mul(639,853)}>who()mul(569,506) what()what()what()'}<]%mul(674,667)[+how()where()select()&why()>&'mul(619,606)who()who(){from()<~[?/mul(257,454)(*&]@[ ]+don't()%:%why()+>&#(-mul(524,120)},mul(68,702)!mul(734,878):where(),#mul(363,999)!$~#&from()^<*how()mul(583,385)mul(609,453)who()mul(198,289)mul(456,335))select()when():}'mul(152,156)]&who()?~what():'mul(595,969)from();why()when()*+#why()from()!don't()'when()(;'?how()]'mul(978,210)#&when()how()):%?^]mul(395,664)'mul(912,244){),@:?'who();;don't()>{!where()why()when()$]*>mul(948,729)'~)select()-'*mul(186,228)]#;@ )}}mul(261,980)<<where()/mul(582,578)where(){who()mul(750,230)]what()*,$from()who()do()mul(46,807)%where()select()]-<mul(792,172)mul(822,372)^$&+>{/when()+?mul(291,330)mul(121,244)mul(836,102)]<how():]{)how()mul(372,630)$}~}when()]$:[mul(328,505))-@-from(838,843)''mul(95,158); :don't()why()?mul(176,511)-]@*;#from()?;;mul(353,178)%what()*{don't()%?what()~,;;$&'mul(785,2)from()[#>mul(876,667)$}%,:-+-mul(493,878)>why()~+~}!:where()-mul(880,424)why(770,240)who()why()+>!>select()select()@mul(627,730)//;} select()mul(307,530)]/ / @who()!don't()/;{+select()}{~mul(404,620),[)~}}):+do()>how()[mul(667,655)%?mul(322,981)<when()#mul(837,392)why(677,359)%'how()select()mul(724,781)mul(564,891)?[!mul(605,35)!-&[;]what())what(364,266)do():/)-}~]why(),mul(602,940)?mul(714,278)why() where()~^mul(770,700){when()select()'&mul(960,514)what()~*$&~what())mul(43,61)*where()[@;mul(157,53){#*&(where()from()(how()where()mul(507,423)~%%'*[] ?mul(423,315)what(),<from():$,select() mul(988,45)#%!mul(376,354)who()mul(704,65)+;:!^ (who()mul(329,461)&where()<from()select()><mul(785,621)/)mul(976,631)from()*?what()mul(719,163)-<')+,{}-mul(858,587)why()~mul(270,973)}mul(206,60)mul(906,800)&) *!~@+how(70,886)@mul(639,518)]mul(931,666)'/^-> how(711,736);mul(629,379)why()%&};why()~mul(480,801)-^^ $mul(633,131)how()from();%:)%^&!don't()mul(329,229)who(),when()<>mul(146,399);#[:;:don't()><]mul(819,85)(when(){mul(812,398)*#^%select()mul(256,504)from():mul(824,150)*)?when()?#@>,mul(955,85)who()!?who()-from()mul(77,506)?[#>:@$'mul(667,680)} &,['%+mul(521,388)&^, &#";

        System.out.println(findSum2(input));



    }
}
