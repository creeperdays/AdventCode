import java.util.Arrays;
import java.util.HashMap;

public class Day19 {

    public static long count(String t, String d) {
        String[] towels = parseTowels(t);
        String[] designs = parseDesigns(d);
        HashMap<String, Long> cache = new HashMap<String, Long>();
        long count = 0;

        for (String design : designs) {

            long tmp = checkAll(towels, design, cache);

            if (tmp > 0) {
                count = count + tmp;
            }
        }

        return count;
    }

    public static long checkAll(String[] towels, String design, HashMap<String, Long> cache) {
        if (design.isEmpty()) {
            return 1;
        }

        if (cache.containsKey(design)) {
            return cache.get(design);
        }


        long result = 0;

        for (String towel : towels) {
            if (towel.length() <= design.length()) {
                if (towel.equals(design.substring(0, towel.length()))) {
                    result += checkAll(towels, design.substring(towel.length()), cache);
                }
            }

        }

        cache.put(design, result);



        return result;
    }

    public static boolean possible1(String design, String[] towels, int index) {
        if (index == design.length()) {
            return true;
        }

        for (String towel : towels) {
            if (index + towel.length() <= design.length()) {
                if (towel.equals(design.substring(index, index + towel.length()))) {
                    if (possible1(design, towels, index + towel.length())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean possible(String design, String[] towels, String current) {

        //System.out.println(s);

        if (current.equals(design)) {
            return true;
        } else {

            for (String towel : towels) {
                if (current.length() + towel.length() <= design.length()) {
                    String maybe = current + towel;
                    if (maybe.equals(design.substring(0, maybe.length()))) {
                        if (possible(design, towels, maybe)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static String[] parseDesigns(String input) {
        return input.split("\n");
    }

    public static String[] parseTowels(String input) {

        return input.split(", ");
    }

    public static void main(String[] args) {
        String input = "wugw, wrrbgr, rbgr, bgbrb, wuwb, bug, wubur, uwbuwbug, wruu, rbbr, wbgrrg, uuwr, bub, brbggggr, brgguw, gwwuu, uwrbggw, wuwrrr, wbuurww, wwwuwru, gubgr, gubu, ug, ubu, gggrgr, wg, wrgbggu, uwgwubw, bgrgb, uu, gbug, gwuwgr, bgwg, rurgb, rr, ubw, wrr, rggw, ubuu, ubr, ugrr, wrbwrruw, uw, ruggrb, urwwurg, gwr, rurwb, bu, uuu, bbuwb, urbu, ugb, uruub, gwgrrw, wbbw, rwgr, wur, bwbu, bbg, wrbugw, brgr, uurrwg, guruu, uurrrbw, bwwbrguw, gbbgu, bgg, bbwwgbw, rww, uurrr, bgubwb, wwgr, gw, wrgub, wgrg, rugwbb, bgwbuguw, brg, ggbw, bgbubb, wwgu, gwgb, grur, gubw, wbruug, rg, brubbgw, uuugr, rru, brbr, rwbuuu, ubuw, rgru, rwrwgb, wub, rwbu, bwwrr, bwrg, uggbur, gwb, gbugwb, br, rbwu, urubg, uuw, rbb, rgrwu, bguuw, w, www, wurgb, rwwu, rwg, uww, gbwurubb, uuugb, rbrurw, gggbwu, rbrwrgwr, bw, uwrw, bugw, brgguugb, brubw, bbrb, bru, urgr, urwu, bwbb, bur, rwrr, wrrbu, guu, ggugwuw, rwrurb, wbur, gbu, bbb, ur, brw, wrwbb, ugbuwgug, rrugbbru, wwggbw, gwgwgur, rgw, rwwr, wrg, wugrwru, ggwr, ggr, wug, ugu, gbbw, wgww, gwwwuw, gbr, rbwwru, bbru, ruw, gbrgw, gug, bwbuwrb, uwg, rbww, bbbrb, ub, rgrbr, ubg, wugwub, bgr, ru, bwr, bwbug, rgr, wwugr, grg, rwu, wbgg, rgwu, ubugubr, buggur, wwb, rub, wugbb, uubb, uwb, wbb, uwgbw, rubrg, rwubgu, wgwbb, bubrbrww, bg, wwrggu, gr, gggr, ggb, wuuuu, bggwb, ubgrwbr, rgubwu, rwr, gbww, wgbub, ubrb, ruu, rgg, wgrwurrw, ubwggr, uubr, wrwrbrr, ruguw, bwu, guwrug, bgggw, urubr, rruruuw, gur, wrgru, wb, rgwwg, rrguu, wwwugg, rgbg, uwwgg, urr, gwu, ruwb, buwbgur, bbwugb, bubu, rwurwg, wgrb, rrw, uwgwr, ugwu, rgrurg, ww, uwgrrrgb, wrub, bgb, grr, bgwbru, wr, brrbg, rurur, wgbbbur, brwgg, bwgwbw, uuurg, rw, uuwguu, guw, bwg, bwrrwrr, ugr, rbwrg, rwb, urw, uubg, bbwrugr, bgw, brb, rb, rbr, ubbwurb, gbw, buu, wgg, wbw, ugguubw, rurbr, rrwbuur, ubwuwr, urg, wwu, wrguwb, bbgbur, grb, gww, gb, buw, bwbg, grgb, bbu, rbw, rrbu, gruwbw, uur, wwbgr, bwbwu, bgug, uwu, wubbw, rgwr, rrgrr, gwg, bbbubu, wwgrbrw, wuuubb, gbg, uwbwbg, wgwbr, ububb, ubbb, rug, gwbbrb, ubrub, urb, ggrwrgg, rrg, wbr, uub, wbrwbb, ugg, wrgb, bbwu, grurwb, uwbr, ugug, grw, gru, rwgu, wrru, rwgggu, rrr, ugwb, rrrw, brr, buwb, rbwbrg, ugw, rwuuguw, bwgggwb, bugr, wggbrr, uwgrrbub, bgbuuwug, burrrbu, wubwuu, ubwur, ubgwb, uru, brrb, wgb, bwuwgbu, wwuwwbu, bgbbg, wwru, wgr, ggg, guwuu, brbg, gbrb, ugrg, wwgur, wbbu, rwug, gbb, wugr, wuur, ubwurrwg, gwruubgu, wrrw, wubwwg, gugur, urgwu, wrbu, wbg, grwb, ugrrrrb, grbg, ggbbu, gbru, wgw, ggug, ubuub, ggu, wuwrbugb, ugrrrr, gbbubrr, rbgbb, bwwbu, uwwu, wwgbbb, gbbu, b, brwgr, wrb, rwrrw, rubwugg, bbr, wwr, wu, bbgwgbb, rbg, rwwgbbu, bgu, wubuug, ubwww, uggr, rbbbw, rbwgwrbw, bbw, bbgr, rur, ubb, uurbg, wguwb, ubbrgug, wrwr, rwwugb, uwrubb, rrgbbru, bwb, gub, ggbu, wwg, bww, ruwrwg, gbwu, wuu, gbbur, wrubru, rgb, grugwbbw, r, rwww, rbbru, wbrrb, rwgub, ubbrr, wuw, uug, rgu, rggg, bb, g, wru, urgw, ggw, ugwwbrg, grrbw, rbu, bbgugrw, grguw";
        String input1 = "urwrggwgbrrwggwuubrrwwugbgbubwbuuugwbguggubrgwwrubub\n" +
                "wuwguurugggwbbruubbruwbruugwbguuwrwbrbugurbgugrggb\n" +
                "wrbgrwbwbgrrguwbwuugruugrwbugrwuuuuurrugrgggugwuububwrwbg\n" +
                "bbbrbwrrwrgrwubbwgbbrbwbruuuwuuwwubbwwgwuwgguurbg\n" +
                "bguuubgbwggwgurubuubgurruggbgubwrbrrgbbgugbbugruurggwr\n" +
                "uwbbgwbrwubrwuwwbrbubbuggbbbbrugbbrwrubwrg\n" +
                "rgrrgruwrrrgwwgrrrrwbuuugrgbbrgbwwruggwrwr\n" +
                "wgwwuuruuwrbwbuwwwwbrgurgrgrgruwbrbugbbwggg\n" +
                "guuuuwuwrwwrggggurwrugugbubrguwbrrwbwurbbrgwbwgu\n" +
                "rrrugwggwgwwuugrgrggbbuwgwwrguubbbwuugbgrugwbbwwugrwgu\n" +
                "urubuwwurwgurgwbggurrgrugrbwgrubbwwurbwubwgbgrgrb\n" +
                "bbbwgwrbbwruubwwwwwbuubrrrguwguubbbugwrwwgugrbwugbubguwgu\n" +
                "rurbwuwgugrrwbrgwrwwubgggbwugbbgbruwwbbwrggwbbgwgwgu\n" +
                "ggbgbgwruwguwbwwgruwbbrbgburrwgubuwgbwrbwwwrggwgurwbw\n" +
                "guwrwuwrrbwrbgugwguburrwbggurwbbrugwbgrbrwwwbwrrbbbubbw\n" +
                "gwrgwrggwrgbbwuwbbwuwrgrggwgrbrwrbwbwuugrbubwwwbw\n" +
                "bbwguwgwrggubrgbuuurwwwguubgbbuwbrgggguwbubrbwwrrrbwgu\n" +
                "wrbwgbgwbbbwrubwbrgrwbrguguwrggbbwguwubgwbbbguu\n" +
                "buwuuwurgbbuwgruwwrbbbrbrgggwwuwrubgrrbbbwwrrbwrwrbbwgu\n" +
                "urbrbgugbrbgwbgwrwuuwguwgbgubrgguugbwwgbbbwrrrbubgwgu\n" +
                "wgwbguwugbwguwburgbwuruuwwuwbgrbbruggbrwwruwwrugu\n" +
                "ubburrrbwuwgubrwgrrbbwwrgugwgrwbugwuwrwwuwguru\n" +
                "wwrbgugggrwwrbgbbbbuuurubrugrrurgbrrwrurwbwwggu\n" +
                "grbuggwbruggrrrbrwbwgwubgwuubwgrwwugbwggrububgrbgrg\n" +
                "brubwgubwrbrgruwwrbgruwrrguwuurwwrubrububrbgwrwrbgbbwrgwu\n" +
                "wwrburrburbwbbbrrrggrbuwgubbbgwwbgbgwrububgg\n" +
                "uurbgrwburugubguuwgbwruuwwubbugbgwbbggggrggrb\n" +
                "uuuuurguwwurrbrrgrurwuwgbrwbggbrrugwguurgg\n" +
                "rrgubrwbwwuwbbgrgbuuuwuuubgrubbgwrrgrbbrgwb\n" +
                "uugwugrrgrbgrwubbrbuwugbrbgbubuubggbbgwguwwgu\n" +
                "uruwwgurbrwrgwrggrgubburubrrurrwugrwbrrgbugbbwbgggwubbu\n" +
                "rbbgguugrwgurwgguggggwwwwurbrgwbbbgwwruwrbuugwwgrrugurbb\n" +
                "gruurgrrgrgubrugrubwugguugwbguugbrgbuuwrurruruggguwgwgu\n" +
                "bguugrgrurgwggbbbrguwugbrrrggrwbguwwwrwwurrwuuwgwggbwubwgu\n" +
                "gubgbwgbbruwrwwbbgubrwwrwwwgurrbububbwbwgbwuwbgbuwwgbgwgu\n" +
                "wwwruwgubrbbgbburrwgwbgbrwgbrguwrbbbruuuwgrrg\n" +
                "wuwbwurruwgbgurbgrwggwwwwrggbuuugbugrbwguggwggrr\n" +
                "rrrwwwuwurggbbwuggruugwubgwwgbbgrrgwburwruurwugwuu\n" +
                "uwwwuuugrbwbbwugrwrruuwrgurbbgbubuubgugbrurwr\n" +
                "bbuurbggwuwbwbuurrrgrwbgrggggbwuwgwrwgrwgwg\n" +
                "gbwrwwbwruguugwwbgbwbugwggbubgwugwbuwrrgbg\n" +
                "wurbwgubrwubrrrgurgrwuguwgrgwuwwwgwguruwwurgwgu\n" +
                "uuwgwuuruwuugrrrgugrubbuwgwugrbrgbgrgwwbubrubw\n" +
                "wwbbgguwuwurugguugwggwrurguggburgbguruwbrbbgrbgwuwbw\n" +
                "uwrgrbuwbrbgugrrbwuubrrbgbgrbbbguurugggurgrur\n" +
                "wbbubggwuurugggbubwrrgbgrubggbggugwwgbggwwubugwbgrrwgb\n" +
                "rgguwubwwrbwgbgggbwruwuwugrgrggbwrrwbbbugwbwg\n" +
                "ubwrbbbbbbrbggbbgrwwuurwwggurrrbrbuwwuwbwrgruubuwuur\n" +
                "bwuggrwwgwubbbwrggugwwrbwrrbubbugwggurgurwg\n" +
                "bruugbgwbbwwbrguwrubggwwrggwgrrrrrrrruwgu\n" +
                "gugbrugurbuwgrrugugbuugugrbbgwgguuuwggburgwubguuugrwggggr\n" +
                "rbbrrbrbbwwgruwrwuwgggrbbbgbuwurgwgwggurrgbggubrrrugwubwru\n" +
                "guurwbbubrbbrwgrggrbruubwgbuwgrugrggbbbbuubrwugburww\n" +
                "bgrubggwwgwgggwuuggwgurbgggbuwurwbwbrgrguwuruwuwu\n" +
                "bubwubbburbbggrwgbwurrwgbwuwrbugbwuuruwwgwubrbuburu\n" +
                "brbbgrrbbuuggwbrrrwggwwugugwwbwuwgrbuwrurbuuggugbrgr\n" +
                "bbuubwuubbgwwbbbgbbwbbbrgbuwwbbbburguwrgwgrbrbbbgrwrbbbr\n" +
                "ggwrbwgggrrwbruubururgwruubbgurwbuwbwrwrbrrugguubrrw\n" +
                "gwuguurrrwwgwwgwgwgrurgwuugrrugrbwubwrwrwwbubwwgugbbwgwgr\n" +
                "rbgbbggrugwwuguuwggbugbrbgwgbbwggugruubbgugwgbb\n" +
                "rrgubuubrbgrrubgguuwbrguwwubbubbububgbbgwgu\n" +
                "gbgrgrwbwrugbgbgwwwwubwuwbwbwuwbwrwbgwbugwgu\n" +
                "wrruguguguugwwwgwwgrrubbbgrgwgugbrbrwrrgbrubb\n" +
                "brgbuwgbubgrurrgwrbubbbggbwruurwbrbbuwgrwuurbgrwrbugurbwbr\n" +
                "uggrbrwwwbbrwububrrggrwrwubwuwwubgbuwgbrbbrg\n" +
                "urgbggrwwwuruugrurgugrrbbbwwgrbuwuruugbbwrrurrwwwuguw\n" +
                "brrgbuwwrurguggrrurbrgrgbbrbburgwwbuwuuuggguwbrwbbgb\n" +
                "uwggwgrgrbbgwruwbrwubrbbrrurgrbrgwbwbuwgwgwrgubuuwrbgu\n" +
                "brurrrruwrgurbgbwuwwwwrrrgwwuuggrwwgbbwgrbggubr\n" +
                "ugrwbbwgbbbruggbgrwrgrguubbruuuwwwrrbguguwugurwgbggb\n" +
                "ugwrubwrubuuubbbrurruwurgwgbuwbrgugwurrgwwburbubru\n" +
                "wwgruuubggbgwbrbwgbuwwgbrubgggrrrwrugrggwrbrrwwuw\n" +
                "bwrrrbwbbwrgwguuwubwruwgbbuugrugbggubwuubwwggrrwbrwrbwrgg\n" +
                "rbbgrrwrgbrbuwwurbgwrggrbguuburubwbwbuuwguwrbrw\n" +
                "ugrbubbugbrbwgbrgbuuuubrubbbrurwgurgbwuggbrurbrggugurruuwb\n" +
                "wubuwurubwrrbrgrguwubuwruugugbubuwrrugbwrgwgwug\n" +
                "uugurwrgwburwgbuuuwwwwrbgbgrubrrubbuguuuwbbuw\n" +
                "gubuwruubgubwbrbrwwuuurwrurbrruuurrggwurwgw\n" +
                "wggrbugwuuubbgrbgwuwbrwwbuuuubwurrwgubwuur\n" +
                "wubugrubbrwurbuwbbgwubrgrwbubwwggwrbwggrgwbgrug\n" +
                "rwwgwbwwbugrbgwbbbbwgwgbbuurrggwbrrgwgbbuwbrguwgu\n" +
                "ruwwubrrrrbwrgruwrrgwrgwbwrguruwrgwubbbuggwrugwgur\n" +
                "gwgubguwguwgurrruwbwgbubrwwrbggwguwbbrwwurubburwg\n" +
                "ugruburuurgggwgbwugbuwgugrgbgrgbwuugruwbwwggugbwrguguuwgu\n" +
                "ggubwuwwrbrubgrrrurrurwbugggrguggbwbgbwwuwwwgwggbggbwuuuw\n" +
                "rgbggruubugbrurguurggurgwwrgrbubrrrubrwgbrggurguub\n" +
                "rgguwwububrwgwgbguwgurbggrrwwrrbgwrgrubwugu\n" +
                "burwurwrrgggwrwguburubgrrwrurgrbrrrwguguuwwburgwuuuruwgu\n" +
                "wwuwurwrgggrurbbwgrrrrbwgrurrbggguuwuruuugruruuwburwgrwgur\n" +
                "gwrrwruurbwgbuggbgbbgubggwwbuubgruugrwuurgbwuwwu\n" +
                "bgggwrbrrrubbbbwrrrbburwgurbburwugubbggwuburbwbuwwgwwbuggwgu\n" +
                "rgwuwgbbrugrwguguubwguuruubbgwugbguwurgrgbgruuwrwbg\n" +
                "ggbrguguruuugwrgwrwbrbwwwgrwwrubbrwuuurbrwrrb\n" +
                "bgruwubbbwrgwwbuuwrrwwuwubgrbwwurwbbugurgbgrbgggurrrw\n" +
                "uurbugwuwuwwrrrwbbgwgwubwwbrguwuwrgggwuuruwrrgwbwrbbgrbb\n" +
                "gwurwbbwurbbwurubbwwgwwwrrrbuwrgubbwbgrbwwbugrggwu\n" +
                "wrwugwwugurwrwrurwurrgrugrubggwubwgbbguurbugrwubgwgww\n" +
                "gbggrguuwuwgrwgbrgugbwwbbwuwrbwugrguuwururbbgrbwwggu\n" +
                "ggruwrburubgubwurbbwbbwbwrbguubbuwwgwbbwrbbuwgrubrwg\n" +
                "rbwrubrbgwwrgguwwgwrbwuurubbrguwbwrrrubbrrubbruwubwggw\n" +
                "ubbrrubrbwrubbrwwgrbggbgurrwggwbggbrbrrbgurbrbur\n" +
                "bwwurruwugubwrgwrwuwbwbwugurggwbwurruggggbbbrwbggbg\n" +
                "wurgrugwugbuggbbrgrburgguwurgurrubguwrrgbuwwg\n" +
                "buwuwrrurruuruugggggbwurubbggrgwrgrwgwgwgu\n" +
                "bggrbbbgbubuuguwwgubgrgrburrrbwrbwrruwwbuwbwwbwgwgu\n" +
                "gwurbugurwbbbrbburwggruwgrwburruruwuugggggwuuwbur\n" +
                "rrbwgbbwrgwbwuwgwwuwbrubbwrrrubbgruggbggwgu\n" +
                "wrrbruuwubugggbubbubwbgbwwbrwbbgguwrwwwwgugggggwurggggu\n" +
                "gwgggbrrugrugbuugbbrugrbgugrwrbwbwrwbgwrubwubrwbbwguuruguw\n" +
                "wwgbgwruubrbugrwrbuuurbrgwrrbgugugbbrwbubbrrubgrbugw\n" +
                "rbbgwrgbgwgubwrguuugwgrbwurbwbbgwbrggurwugbgbbbuw\n" +
                "brgbgburgbrugbwrwgrrbgugbrwugbgbgwbrgwwwwgg\n" +
                "rgwbugburuwwrrgbgrbgbwrubrwwbgbubrubwurguwu\n" +
                "rugurrgrgrrwgwrbbubrbrwwwuwwwwuurwwwwubbbguwuwggwgu\n" +
                "ruggbwbgbbuwbugrubgugubgruggwrwwbbwgrwugwurwuuru\n" +
                "wbrrwgbwbrbbbgwrgubuuuwwgrgwrbwbggwwwugbwuwwr\n" +
                "rwbgwgwgrrbbbwgwurrrrbrugrbrgubrrwgwwwuwgwrwrbrwuwu\n" +
                "wuwrbgwrbbuguuurubwrrurwwgrugbrurgbrgrubrurrwwur\n" +
                "gugrurgugrwgwgwrwrrggrwgbwwuwurbrbbgbuwuubuggrwuuu\n" +
                "brwwwguwbgrubgrgugruwwrbrugggbggbggbgwrrrguug\n" +
                "buwuwugggwwwrgwrugubugbrugwrwuubgbbbwwbgrubb\n" +
                "wbrguugwrbwgwgggrggbrgurbggrugubbwrbubbwggwrbuubgrrgrwr\n" +
                "wrgrbwgwrbwrwgbrubwgwubbrgrguwrgrbuurgggggg\n" +
                "wbuuggwurbwgwguugbuuuwugrwbwwuuuggwbuuwwbbruubwwu\n" +
                "buwrgbruwbwwrgbbbuwrrrrrugbbrubbgwguuurbrrgrwuubgwguwbb\n" +
                "urbuguubguwuwrwwurwwuuwwbwguugbgbgbububwwbwrwgwugwgu\n" +
                "ggrgbrwrbwuurggbgbgrbugggwuubbwrgwrgbgwrrwgwgwuug\n" +
                "uwwubwurrwgggwbgrbbruuwruuwbgbrgbgrbrubgbwgwwubwgu\n" +
                "rrugububgrbwuguguwrrwwwrgwbwurggruwgbubbruwubuwgbgww\n" +
                "uwgwgwbuwgrbgrgugwwwrwwwbuuwguwwgwuggrwbrrruwwbuuw\n" +
                "bbrgwubwwuwbruuurguuwbgurwurgbgubgbrrbbwwur\n" +
                "bubwrgbuwbuwugbbuburbuwggrrbuwuwrbugbbgrurbwgu\n" +
                "ruubggwrgbubwbwugwrbubrrbrbrwgbwgbbuguwbrgurwgggbbbr\n" +
                "brrggwurbgrrrruugbubruwrrwggugwgwwrbggrwrbguggbbbru\n" +
                "buurwrwwuububbuwwuwwwbgwgubuugbrguugububbgggugbwgu\n" +
                "brwuguwwggwbuggurwbuwubwgurwruuwbrurgurwuwurggbbwurruugwbg\n" +
                "wrbrggrugurwgugwrggrguwrwugubwurwbrgrrbrbwgbrbuwuggbwbru\n" +
                "bbbgrbwwwurubrgwwwbwbrrbbrgrruggbwubgwuwurbugrburwgguurwgwgu\n" +
                "ggrrggwbrgguugbwbwugwrwuuruwubrbrbgwbbgrru\n" +
                "bwggwrbgrgrrwrgwrubggwwrgwwurbwrururubwuwbruwurwgwwuwu\n" +
                "urbrrugruuwbrwwrwurrwrrgbwgubuwwbwgggurrgugbwbrgbugwbggbwu\n" +
                "gwugbwrugrgbbwrrbwbwgbguwggrrurbbbbugrggbgb\n" +
                "brrgwrbwwbuurwuruwbwwwwrwbbbwggwgggugbguwuburr\n" +
                "ruuuwgbguurwgwugwggrwwrbbbwugrbururbwburwwruuwwrugrur\n" +
                "wrbuuwrwggwwrrbgwwrbuuggbgrugwrgugguruguwb\n" +
                "rbrrwwbuubgrrbuwurwwuwgwguubgrgrbubrwgugwrbgruburbrrug\n" +
                "gbwgugrbwbwgrwwbgbrrugwgruubwggbgruuwrwgbwrwrgbuw\n" +
                "rwgrbbbgwgubrbwuubrrubbrguubbuwbgrbguggwugurgbguwrrgbb\n" +
                "rwgbguwuuwgbrbrrgwgrrwuuuguuggbggggbwburugwurg\n" +
                "wgrggbbrbrrruwgguwggrgugbrrbrbwbugbwwrubbbbuwu\n" +
                "wrurbrubuuugugwggbugrubruwbburrrrwuuwgwuwggbbuguw\n" +
                "gbrrwbwruwrrbrruguuwgbwwbugrwwugwbrgubgrur\n" +
                "grrrgguwrrwwrwwgbbrgggwgbguwuuubbwugrrrugrggrb\n" +
                "uurgrwubwbrrgurrguuwrbgwrbrurwrugrgubwwgwbwgbrugbgwbuguwgu\n" +
                "bwbwwgbbgrwwbrbbbrgbrbwwwuruuubgrgguurbuuwgu\n" +
                "grggrwbwgggbrgugrwguwrubwbuwgurrruuwbgbrrgb\n" +
                "bbrruubuurbguubwuugrurbwrbgbwubwubwwrwguwug\n" +
                "uugbrugbgrbugguugwbwbgrgbuurwwbuubgrgrgbrw\n" +
                "wbgrbuuurbbruwwwgwrrbguugbuguggwwwuurruuurbbgwwrbgwg\n" +
                "ggrrbggbbgbgwrbgggrbbbrububuwrrbuwbbwrbgrgbgrwbb\n" +
                "bgwuguuubrwwuwgbbgbbbuurwwwrwwbrgbggrbbwguguwgu\n" +
                "rrbgwruwbbuubbuubrbggbugwwwgbgrwgugbubwbgbbbgbbrgug\n" +
                "rbwbrgbbggbburrrwguwwuwrugrgggggrwruuggrbrgubgwrwbrrubggwgu\n" +
                "guburwgburrgurggurgbgrwwwbuubbwwruwrubbrbbwrgwwrruubbwbu\n" +
                "uwbugwbuwrwurbrwrrrwrwwbrgrbbbwuwrrwrrrbbbwwrwrrbwgbrrgwg\n" +
                "bruwrgwrugwggrgrwgrrrwwgwwugbwurggwbrrwbgwgrgurru\n" +
                "gwuuruurwwguwugwurrgbuwuwwgrwgurgbbrgwurrwwg\n" +
                "wgrbrgurburugugbguwwgwbwwgggbuuugwgwrwbrrrbruburwrwwgwbbrb\n" +
                "uwggwbggwgggrgrbrubrbruubguuwgurggwbbrbbgrrbug\n" +
                "rurbgwgbwuwuwuuubwuguwrbwgbbrururruwbgwwbuwbrrwrrurwgg\n" +
                "ugwuuuurubbgbwrwgbuwrbbrwbwgwuugbrguggbubbggggubgrwuwrbbgg\n" +
                "wbbugbrbgbrruwguuruwwgwgwrguwubguuwurbgwbbguwrrrwwwburguuu\n" +
                "wuugrbbrrgurgbuuuruwbggrggbubwubbrgbuugrugwbgwguwrw\n" +
                "guuwwrbuuurwrrwrwrwrbuugwwbuuuuugugrgbguubrrbggbgu\n" +
                "rbwbbruuwwbwgwbwgugggbwwrwugguubrugubwgbbrgrbgu\n" +
                "bgrgbuugggbugwbbgwurgwguuuruubrubwbubwgwgwgurrgbubrguubr\n" +
                "rbggruugwbuwgugbuubugggwbrwuurgrrbwwbrurbgwbgguugbburgwgwg\n" +
                "guwuurbbwwbbuwwuwwggurguwuubuwwwgrugwrubgruurwgbwggb\n" +
                "gbrgbwwuggwuwgbgwbbgguwrwbwbwrwrwururbuwbgwgrrwrbuubwrrrbu\n" +
                "ggbbwgbbbrbwruggurwguubbwbguwguwgbrwrrruwubrurbuguwgurgu\n" +
                "bbwbwrugbwuwbwwbuugrgugugugrgwwuugwrurruwbgwwbwbwr\n" +
                "gbwwwuwrwwguwburwuggwbruuwbbrubgrbrwrgwrgrgugwgu\n" +
                "rrbwwbrrwgwruwwwrrgwubbugruwguggwwubrrbwwwwggg\n" +
                "urrrgwgggrrrurubwurgurwgugwgrugwbbwbwwgbbrwgurgwgwbubgwgu\n" +
                "rwgbwbrwwbgrbwggwbuuguuuwgbbubrgrggrwguwbrwrbrwrrwwurg\n" +
                "grbrwgbrwgwbwubgrbbgwrgrwuugwbuwuuwbwugrrwg\n" +
                "rgrurbbbggrrguburrwwugbgbbgbbbwuwbwrgwgwwbruuruww\n" +
                "wubbgwgbggwwggwrwubbrbwrwrbruburbgwwwurgguwgr\n" +
                "bbrbgbwrrbgwwwurggwgguwruwbuwbugbubbbbrwbggubuwuwgu\n" +
                "uwubbwwbrurwrrrwwurbgwwrubgugrbbgwbbbrwrrrgrbruubbbburrgg\n" +
                "rugwrbwugrgwwgwbbwguwbuwbugwwbugwwwurwbbguwgbgrwuwrggbgrr\n" +
                "rgwgwwwuwbgwbuuwubbuwbruwgugbbugbbwrbwggwbgbwurubbrrgbrw\n" +
                "urwwgrbbgbuwuubrubruburgugbgbwuurgguuwuwubwbuggbwbggwugwgw\n" +
                "guugrwbbwwbwbwbuguurubuwwwrrbggbrruwwwurwbbbrbbb\n" +
                "ggugbrwbgwrbrbbbrgguggwgrrggwgwrgruurgwurggwwgubgwgu\n" +
                "wruugbgugwwrgbwbrbbbgrubrwbgwwguwbbgubwrguwuuwubgur\n" +
                "bwuwubbugwgbrrwuuwrgbgbguwuurrbbwrwugwwwrbubgrrrwbw\n" +
                "buwrbrgwbgwurrrwguuurbrrbuuurwrgwubbgwbgggguruwbgbggb\n" +
                "uwbggrgwbubrbrwwwguguwrrugbbbugbbgwrrwwwgrwgbugurwrurr\n" +
                "ggurbuwrwgurrgurgbuwrguwbwbrugbgburwuwuuwuubgggwruubgubwgu\n" +
                "gwrrbbbgwbwuwgubbugguwurrwwbrugugwwrwbrgurrrwww\n" +
                "gwruwggwrubugwgubrruruurwrggbwgrubbuubgrruwgrburwbgrrbuwgu\n" +
                "gguugbgwrrbwwrrgrbrwuggrbwbgbgrwrbuwbbubwgubwuwubbg\n" +
                "rruburrrwguwrrgwwuuwwbrgwgrurwurwubrubuurbgbbggrgb\n" +
                "wbrgwrbgbrbwgwbuwurgrrruwbwrugwbbbgwbgwbruuuguwuwwbuguwrub\n" +
                "wwrgrgbuwbbbwwurgugrruuwwwbrrwuwugwwurwgrwurubguwwg\n" +
                "ruburgggurruugrubggwgugbuurgwbbrrggbwggwwrbbgrwb\n" +
                "wwggruwuwwguuwwrrgubgwgbwwbrrrwwuuggugwbuurub\n" +
                "gwrburbbgbrwugbwrbggrwwwwbrrgrrrurggwbbggbgbubgrrbuwrwu\n" +
                "wbuubrwbrgwwubrgrrggrrurwbbwrrgwbrguggwuwwugruw\n" +
                "ugbbuugguuwgwbubrwrwwrwgbbbwrwgburuwrbbuuwbw\n" +
                "uuuuwbwggrrbbwwrwgrwwbwrurrbruubwwwgbgrgrrwggwwuugbgrrb\n" +
                "gbbubwubwbgwubwwgruwbgrburbuwwbgwwgbwrwbwbrgwrrubwwbrug\n" +
                "rwbwwruwwwgurgurrgbgubuuguwbwrugbggrwurbbrbbrbuwwrrwww\n" +
                "wgwrubgwuubgwgwguguugggwwruurwwgguggbuugwbuuwrbr\n" +
                "wwgrwrrgrbbwwwbgbgbubuwrwrbbbrwrgrgguwgwwugggbbgbrbwrgug\n" +
                "urugwubwgubrwrbuubguggrbuuuuuwwurrgbrgbgbwwubruwuuwwwggugr\n" +
                "buwuburbrgugrubggbgbrburgwrgwwgbuuuuuwgbwbrbuugrwbbbwb\n" +
                "uuwgwwbwuurbbggbwrbwrbbruuururwgugwuuurwgrgrwbruwuwru\n" +
                "ugubbwgubrgwgwwurbuurwwrbrwggubbguwrggrubrrbuwuubbwbrbrb\n" +
                "grwwrrgwrubbwggwgwwuuuwwrwbbwbbguwubwgbrgbrgrgguugrrgrgw\n" +
                "wrbrgrgwrwbruruwuwurrbwgwwbrrrwwwwrgrbwgurgrwugwuwgu\n" +
                "uuguwwrbgguuwgubbwrwugbrwbbbrbugubbrbrgruugbggbwbrubwg\n" +
                "wwbwbbwugbggwbguwggwbgrgubgrbrrrgbrugrgwuggubbrrr\n" +
                "brgwbubggbrwbwuggububwbuuwgggrgggwgbwuwguwgwgu\n" +
                "gwbwuwuuwurwguggbrbbrrwubbrbrwuuwrbbbgrgurggrrwrg\n" +
                "ggugugbrgrrrwuurruuwrgbgbgugbbbrwuwbbgrggbwuggugugugb\n" +
                "bgwwurubbguruuuwbuuguwwbbuuggwgbbgrbbubgubruwbgbuwwurgu\n" +
                "uuguwgrrbubwrgruggubggwrgururbrruurwgrgbrgug\n" +
                "ggwubwbwwbwggguugurbwrrbwrrgwrgrgbubuwuurwgbruubbwgu\n" +
                "bwgbguwrgwubgrgbwuwgrggbwwrgbgwbruuwgguggu\n" +
                "wbgbuwrrggwgbubgrbuugrrrwrrwguurubuurbgbrb\n" +
                "wrbwbrurwrubugrrrbugrwugugrugbwuguubbrgrwwgbrruwbbwg\n" +
                "gbuguwgwwgwuggrbggwbrruuwubwrgwbrwgwguwrgbwgu\n" +
                "gggggbbururwbggwgwwrrguurwwgwugrrwuggbubrgrrugubgbugubwgu\n" +
                "wurbgwbuguwbbgubbgwbburbbrwbbubwrbrubgugbbugwuw\n" +
                "uugggbugbwwbrwuguwggrgwrbrgwwrugwgruruwgwubbbwwbbguuwugbr\n" +
                "brugggrwruuuggbgwguuurwubgwuuwbruguwwrgwgbugrrwbbrug\n" +
                "wurrrrbbbggrwurubbruurbrrbwwgrrrrgruubrrwwwr\n" +
                "rugugrwbubrgrwbwwbuugwurrwuuguugrwurwrbbbwrbg\n" +
                "rrurbgrgwrwuguggurbrbgwbgugwrbrugbggrugrrrggrw\n" +
                "ubwwwwbbguugrurbgrwwgruubggbrrwgbgwrwbrgbugbbbbugwbwbb\n" +
                "wwgubgrrruurgrguwrgwuwggbwbwruggrbgrbrbwgguruggwgwuurbbuu\n" +
                "ggguurwuuruguggrrwgrwurbwwbwwbwrbrugbgwrwugbruugbgg\n" +
                "urgbubgwbrwrgrbwgruguwugwbgrgugrgbwubrgrbwrr\n" +
                "uwbwbbbbrwggwrrggwbgbwrgugbrggbwuwgubrwbgbuuwugg\n" +
                "wwgbbgwrwrwrbgubgggbrbguguguubbrbbrurgbbgubbbbwgu\n" +
                "burwbbguggbwwubrbugbrwwbrgwgruwrrbuggugwgu\n" +
                "brrbrguwuwbrrrbuuubwwrbgbubugwwurwugrgrwurbugwwrbugbw\n" +
                "gburrrgwuwrbwugugrrrurwrrrbuugruurbbguggugubwgb\n" +
                "ruugrbbwbubuggbbuwgbbwgwurbrgrbgruwbrburggubrrwuburuugbur\n" +
                "wwbwrbwuwguguuuurwubrwbrburbggrugurwbrrrwwbgurwuwg\n" +
                "urggurrrgbbrgrwrwrbuuurbgwrugggrwbrwbgggrugg\n" +
                "gurrbgbbrugbwrgwwggrururbbgwgwwbwubgwrrbubwbuubggbrbrr\n" +
                "bbwwbgbrbwwrgwwrbgwbgugurugbugwubguwubruurru\n" +
                "wuurbgrrgrwurrguuwuurwbwubrbgwbbbbugrwwwwbbbwwgwgwru\n" +
                "uwgubbbwuwugwbrgwwguubwwugugwbubguugwgbuurgbrwbruuubuurruw\n" +
                "bbruwrwuubwbugbbrbgggbwbwbuwuuubrgugugwbwwrbuwgwuwgu\n" +
                "wgrgrbbgruwrgrwgbrwrwguggguwwwubgbwuwrgrbguwu\n" +
                "bwbrbbwggwrruuguwgwwgubrbgurrgburuuguuguuwggwwbu\n" +
                "ugrbrwugrwbwgbwbwbwrgbgugwurwwgrurrrugbwbggu\n" +
                "grwrwbuuwbbrbbbuwrwubbugbbbwwgbwgruwbgrgbwguwwbwuww\n" +
                "ugrgbwburbwbgrgubbuurwubrgrwubbrrruwubrugrwrwbggb\n" +
                "wwrwwrwrgurgggbrubuuuuurbbrgwrgggwgrwbuwrggruru\n" +
                "gwwbbrburbuuuguruubbbggwgrgrubwgruwwrbrgrgruugwrrww\n" +
                "ugwrwguggubrrwwurwwwbwbwbbwwwwwguubbwwurrgwwgwbwubwb\n" +
                "wburwbggrbgurbwubwubggubrwurguwbbbggbwrbbguwbwbwuurgbgg\n" +
                "rwwgggwwwbubgugbubuuwwrbbuwrwwgwggbbbugwbrgbwuuuuw\n" +
                "brggrwbbwrubburgwwrbburuwuugwrrwuuwgrrrbwwwgguubwwbbbbuwgu\n" +
                "wuwrbwuwbwrugbrwubbbgugbgwgwbubwuurbggwwrrgrbggbw\n" +
                "uwrrgrrwwugrburbbwbwbwbwuurgrgubbwwuwrgrwgbugg\n" +
                "gbuwgwrwbgbgrruwrgugwuwbgwruwrbgbrrwububbrbrrbgugr\n" +
                "bgbbbwwwwbbubwrgwbwrrbgggrwggwgwgbgrbbgggugwru\n" +
                "wwgwwbguuwrbwuuwugwrwbgrwbururbugrrrgwbuuwrwwuuu\n" +
                "brgruuuugrgwuuguwbbwgrwgbggbgrugrwwbbugrwwrrubwgu\n" +
                "wburbrwbwrbwrbrrgggurruuguggwuwbrgwbgbwgbrwubgubugbur\n" +
                "rgurgwgrubuuwwrugugbbggurbbgwwrrubgurwbwbbbrurburwrwwub\n" +
                "wguuuubwubwgwrwuwwuururbggbgwgbubwwrwrwuuwgwbbbrgr\n" +
                "uugubgbguwbggguuggrbwgwwrrguwuwbubwwrgggwgbwgrb\n" +
                "gbubrbggrwwgurbbbbbuwbbwggbuwbuwurrgruwwgub\n" +
                "buwrrwbuwwgwbuwuurguuwbwrbuugwwwuwgrrbubbrgrbubguruwgu\n" +
                "ubruwgrwbwuurrruwbbwwwgrbbbburgwwbbuuwrgwrguburwbuggguw\n" +
                "gwuuuuwgbbgbwgggubuugrbbwrrurbbrburrrgwgbwguwubwuww\n" +
                "rugugrbbwuggrgbbubggubbggbrgwrbrbgubbwgu\n" +
                "uuubbbggurgubgrbguuwwrgbrgrbbrbrubwguwbubwrgwwbugg\n" +
                "gbwrrgubbrwgbugbrrruwubwgrwurrwwurwgwrwbrguwrgwwbrwwuwgu\n" +
                "wwwbwugurwwguugrbwgruurwwrgguwuggwurugrggwguwg\n" +
                "ugbrwwbwwwuwwrgrwuwbuwbgwurubwuwuuwguwubwbburwrwubuw\n" +
                "grwgrggggbgwgbgbrwguugwrwbgrrurwbbwburgrwwbbwwgggrwwgugwgu\n" +
                "gbrbbgbwgburbbbrwbgbuuguuurrrrwwbggbgrubuuwwwwrb\n" +
                "brbugwubbrgbuubwbuguguwuugbrubgbrwwugbuwbgbwuwwbrgubrbwruu\n" +
                "rbwrwurbbwubwrgrwrggrwugbbuwburwuggwgwbgbwrgwbgw\n" +
                "brurwruuubwwruuwuwwwuggugrbruwbgwbugrrwurugrgubrggbwwr\n" +
                "gugrwgubbwbuugwgrgrrrgwbbrguwubruwugbugrbbruuurwgbgur\n" +
                "ggbrgubuwwururggrburuuguguurgubbrugurbwrggrrwbwgu\n" +
                "rwuugubrrurrbguuugguwwwugrrgwgbrguubgrwbrrwguwbw\n" +
                "uwuwrrruwrwwgrwgrbbwwwggwgubgrrrwggubuuuuuubrbbwgu\n" +
                "ruurrrgrugrgrgwwugrwwgrbuuguurgrbrbgrwbwgwu\n" +
                "buubbwwbwwrwbwwbgurgwbbrrgbwbwbuwwbwbggguburrbrrbwgurgu\n" +
                "bgrurgbrrurwwbbrrwbrrrwrgrwbbrugrwbwrrgubbubrrbrgbuuwrgww\n" +
                "rwbbwbgwbggwwgugrguwgbuwwubwuwwwwuwbugrwgrgguu\n" +
                "bggbubgugwbbrurrubwgwbwwwuubbrguuurwruruwrwwubuurr\n" +
                "buwuwbrrubbwuurwbrgwwwwrrgruwggruruggbrgwbwwwbwugrwgr\n" +
                "ubbuguurburrrwggwgbwuwwrgwurgruwwwwbwwgruwbugbuugrrggbu\n" +
                "ubuwrbwgwrbgugrwubbgrbwrggwrgrubbbbgurwrrwuubwbrgbbbbuggww\n" +
                "uruwwuubrbgubrurwbguwrugbwruggbbwwuwuggbrwuwbuggbrbrgrbr\n" +
                "uubuuruwrbbwguugwgubbwgwubbruggrgwrrgruuwuwbgwrrrgr\n" +
                "gwbububbgwbwgggwrwurugrrruwuwurgbrwbgbwuwuggwgbwuwbur\n" +
                "ruguuwwuurgwggbbwgggugugbbuuggwbwbbwgrruuubwrguwrruugwrbb\n" +
                "bwwrugwwrgwruubbwwgbwwrwuwubbwurrrubrwwwubbubugugbuwb\n" +
                "rrbwwggurwuuubgurbbwgbrbruuwbuuwwrbugggugw\n" +
                "rwggguuuwbwuwwubwuuurrubgrguwuwbrgbuwbgugbbbbrwuruuurgrw\n" +
                "uugubuwwwrwwuwguubgrrbrrrwrrgwubbwwbwrbgguguuguuwru\n" +
                "rwugwruubgurrwrbgubuwrwuwugrwggrwwwugbubugwbbwgwburbbubwu\n" +
                "bubbbrgrgbwgubwwruurgrbrbbwgbgrwgggwrbburubwb\n" +
                "brwgbbwrbruggwrrbuuuuuuuuwrgbwuggrrrugwuwgr\n" +
                "wggbubwgwruwrbgrguugwbruuggwbrrwbgwuwrgwwurgugwurbwbb\n" +
                "gwwbrgrrrwrbgwubrwgwrwbgbburgrrwbuwgwgrgwgubbgr\n" +
                "rurgrubwgwrrubburwurgrbrgurbrguwugwurrbbbbwwbbuw\n" +
                "wgwugubruuugbrgrgwgbrurwbgbwbrbuwgrwurrgruwgu\n" +
                "gguwggbrwwugugrbwrbrwgwwgruggurgurwrrrrwbwgu\n" +
                "wwgbrrgwwuwubbuuuggbgwgrwbbrgubwwuwurwrbubggrgguurgw\n" +
                "wrrrgwrubggugwgwburgbbwguguwwgurrwwgggrguguggrugwwwgu\n" +
                "ruwwbrburrrrwgurruwbwgruggrbwuwuurrgbuuwbgbg\n" +
                "rbugurbwgurwwrbwrruwgbgwwrwgwggrrguugrbrgwwgwwurubbrubrg\n" +
                "bbbbguuuwwurwbwbggbrgwwugwbugrugubwuuwggwbwuwwrgbrguuwr\n" +
                "buwubggbrwruburgbubwgbwwrwrgbwgwrwuuurrgrrwwbrrrgubb\n" +
                "ggurrgbrbggggrbgwbuwurwbrguugrbuurwrgubwwubbbbwrwuwbur\n" +
                "ubbwubgbrwwgrgbgrwbrruuurbwrwrgwbgrrgrbgrgwuuwwururrbr\n" +
                "rrugruuwgbwuwgwuwruuwwuuguurrwbwrgugbgwwubbg\n" +
                "gubuubrbbrrurrbrrwguguwrwgbbubrbuwwbuwwgubguburuggrgwr\n" +
                "wguwrwrwruwbbbbwuubwbguuwurwbubgwwwwrbuugww\n" +
                "bbggggggbuwgurgwurbubwubgbgburbwrrbrrrwbwubwwruwuwbggrrugg\n" +
                "ggwrgbwruubwwuurgbbwrgbbgwrubwgbrbbbggbrurgugbbwgbgrugu\n" +
                "ubuwugruwbrwwbbwbbuwwubgrbgbgggurrrrgbbgwbbwuu\n" +
                "rrgwwggwgurgurbbbwubuugbbbgwwrrwrburubgwgu\n" +
                "ugrgugbrgrwuugbuwgugrwrbwbugbwggwbwubgbruuuwbrrwrbwuu\n" +
                "urgwwubwuubruguwwgurgbwgubgggbbwruwrurwwgbuwrrrbwwuw\n" +
                "bgrgubruwrurggrbwuwbbwwugwburbubrrwurwubwrruuurgubrrgub\n" +
                "rgbrrrwwbbuwrbruuurggurubggwbbrrbwbbrbgwrbuwgbgg\n" +
                "rgguwggugruwwwbrbgrrbwugururguwuuubgruubbwwwu\n" +
                "bwwbrrwuurgrruwgrguwwbgrrwrbwuuurgggbrwgbuwbguuuwggwbrrgwgu\n" +
                "rbwwwrbubwggrugguugwwgwbuurburbrubuwgbururwgrgwwb\n" +
                "wwwgubbwbwbgwubrrgbubuwbbbgrrgrwwrbbuwbubrrguuugruwwruwwb\n" +
                "ubrbggrwgrwugwwgrugubrburgggbugwbggbburgugggwbugugguguguw\n" +
                "rrrgrgrwgbgwgrgwrurwuwgurrbwwwgbgrgwwbwbubrrubrru\n" +
                "wbwgrgbwbgwgbuugburrwburbuwrbwgburrgwuwrggwbwuwgbbbguwrbgr\n" +
                "rwgburgwgubuugurwbwbwbuburrrbburgwwwwguwgwwbwbbwwwuwbwgwug\n" +
                "rwbuuguuwrrgrwrrgruruuguwwwgbbruwbbwrbrguwgu\n" +
                "guwbggbgrbbbbuwrwguwguwgrwurrwrbuurrrgruwwurrw\n" +
                "wbwuuuggwbgrwggbrggwbwburgbbrwrrugbbrugurbgwgu\n" +
                "wrrubbwrrwbruruwggugwwgrrwugugrgrguuggrubrggugggbrwu\n" +
                "rugrgguuuubbuugbbrrwuuwgrbrgbgrbbrbugururrg\n" +
                "ugggrgrwgbgwbwwrrgggbwwgbgbwrwwgwubbbrbbbuuwgwbbubbbwubw\n" +
                "gwgwuwuuwgbuubbwwwrrurrbgbubuuguwwbrgwubuuwbgwwuwbg\n" +
                "rurggguuubuuwrbrgwgguurwgrwugruugwwggrbuuugwbwgu\n" +
                "gbburubbgbggrwubgrgrurbwgrrubuwrrbbgrgrwgg\n" +
                "wgrrgwurbguwgbuwrbrurruwrbgrrbrgggguugrbwgwrbwwbrgwgu\n" +
                "rgbwbrwguubrwwrwugbbbwugwurwwgurbbrgwwugbbbwgb\n" +
                "ruwwgugrgrgrwgrwrbrrggwurrrwubrrbbrbwubrbwgu\n" +
                "brbwrrrggrgrggbwbgubgrrugwgwuubrbwwurbwbru\n" +
                "wrbrwgbwbbguburgrrgwrbrwwurugruggwgrrrurggur\n" +
                "gggwrgwwbuwwbrbgbrbbgbbubbuggrgrggbwugugggrbub\n" +
                "urgbwrgbgwwrgbwurgggrwgbbgwbwgrwwuuuguggbrbggw\n" +
                "wwubrgbwrguwrbrurrwwwgrwrbrbrrbrrwwgbbuwwwbbbwbg\n" +
                "rrgugrbrbuwwwuuwwurgubgwbbruuggbbbruuwubur\n" +
                "urguwgrrrgbrrwbggbgurrrrrwggrggrrburrrgggbu\n" +
                "ruwgguwurbbrgbgwgrrubwbwurwrbwgburrbbgrgburgubwwgb\n" +
                "wggwubbrwwwububuugwbwbubrubuguwguwrbbrwbgwgurgu\n" +
                "uubggrubwgrgbrurgbuwubgbuuwugwbuururrururgggrurwwrrgrrgbgwgu\n" +
                "brrwrbrbggggrwrburbrurbuwuuguwbbrgrruwbugwwburuuugubwgu\n" +
                "gbwrruugrgwbgrbruwwgrurbgwwubgwwbbggubgrwubrrburrgwuw\n" +
                "uburgbbwrgrwurwbuwrrwbbbggrruwrugubruwbgwubggurbuwwbbuwgu\n" +
                "wwrwguuwubrbbruguuruugbwwwrbbuubbuuubbbgrgr\n" +
                "uuwgbbgguuggrwwrrrubbbbgurgrruuubwwugwgu\n" +
                "guggrgrbrburubbugbugrgrggggugwgwggrrwwgbbwgu\n" +
                "rbgbbwrbwggugurgubbwwwrggwgwuwgrbrrbgubbbbruw\n" +
                "uuuwgrrrubbrwrbggwgguubbwbrbrwbgrgwbubwggwubgrub\n" +
                "rurwruuwwgwrguwugruburbgbuugugwuubrbbwurgrbrbugwubr\n" +
                "urrruurwuwbbbwububwrgguwbrgrbuubgrwuguwurwrbubrugu\n" +
                "gugwgrrrbrrrgrrwwgrggbuggbwbwgbburbwwggguwrub\n" +
                "wguwwwgwggwwurrrguwuwbgrgbrgwugbwbwwggbugbggr\n" +
                "gwgrwrgwbbburggrbwwurrrgbguurrrgrbrugwgbwurwguwbgbgrbug\n" +
                "rbbrrrbuuurgugwgrrbuwrbrrurwuwgrrrgbugbwgu\n" +
                "urwgwbuuwrgwwrrggwwubbrbwguuwbwrggbbwwwwbwrrrwwgbwwurbrrg\n" +
                "wwbguurugruuwuubugbgrwgwwggrbubrggwrwruubwrgwub\n" +
                "ubgruruguugrwbwbuwbbwruubwgrrurwwruwugwbruuurrwwrrgruugw\n" +
                "grbubwruwubgugggrrgrbwgurbgwugwwggrrbrgrbuwbbbrbwgu\n" +
                "wrgrrubgggrurgwguurubwgggbwuwrrubrugbrgbrbggwgg\n" +
                "gwgbrgrrbbrgubgrubrwrwrugrgubrgruuguwrwubwwwrgbubwwggbbwgu\n" +
                "bwbwgbgwbwwbuuwbrubggggbugwgbgbuuwggrwuwrbr\n" +
                "wugrwurwgrbugrwrwrwrrgwwuugrrwgrgbgbuwgu\n" +
                "urbbubruuggrugwwrbrrguugguuwuuwuuubrbgbbggrgbug\n" +
                "rrugubrbbgbwrbggguugrbrrrbruugruuggrbgwbuuwuururugwruug\n" +
                "burwbgbgruwrurbrwuwrgurgrrbrbbguwwbbrwgbwbgwrwbruuwbgwgww\n" +
                "wbubuuwbrwuwwruggbbugbrrrgbgwguwwgrgurgwrugrguurrrrbbgrbbwgu\n" +
                "rbwrbubggwuubugbrubuurruguwrrwgubwrwwububwrgrguw\n" +
                "gbrgubrugbwrgwwwwrbugwwgwgwrbrgwuuggrubwrbrugwbbruubwgu\n" +
                "wbgrubbrgwgrbrbuuuubuugwubbugwbuggwrburgwugw\n" +
                "grwgrbgwwrubruuubburwgbwgugwugwwrbwbwwrguwbbubuuwguwr";

        String input2 = "r, wr, b, g, bwu, rb, gb, br";
        String input3 = "brwrr\n" +
                "bggr\n" +
                "gbbr\n" +
                "rrbgbr\n" +
                "ubwu\n" +
                "bwurrg\n" +
                "brgr\n" +
                "bbrgwb";

        //System.out.println(parseDesigns(input1).length);

        System.out.println(count(input, input1));





    }
}
