public class Day4 {
    //Below finishes part 2 of the advent of code
    public static int search1(String input) {
        char[][] grid = parseInput(input);

        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'A') {
                    if (i + 1 < grid.length && i - 1 >= 0 && j + 1 < grid[0].length && j - 1 >= 0) {
                        StringBuilder s1 = new StringBuilder();
                        s1.append(grid[i-1][j-1]);
                        s1.append(grid[i][j]);
                        s1.append(grid[i+1][j+1]);

                        StringBuilder s1r = new StringBuilder();
                        s1r.append(s1);
                        s1r.reverse();

                        StringBuilder s2 = new StringBuilder();
                        s2.append(grid[i+1][j-1]);
                        s2.append(grid[i][j]);
                        s2.append(grid[i-1][j+1]);

                        StringBuilder s2r = new StringBuilder();
                        s2r.append(s2);
                        s2r.reverse();

                        if ((s1.toString().equals("MAS") || s1r.toString().equals("MAS")) && (s2.toString().equals("MAS") || s2r.toString().equals("MAS"))) {
                            count = count + 1;
                        }

                    }
                }
            }
        }

        return count;
    }

    /**
     * Below finishes part 1 of the solution
     * @param input  the input string of the word search
     * @return the number of times the word XMAS appears in the word search
     */
    public static int search(String input) {
        char[][] grid = parseInput(input);

        int count = 0;

        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'X') {
                    if (checkRight(grid, i, j)) {
                        count = count + 1;
                    }

                    if (checkLeft(grid, i, j)) {
                        count = count + 1;
                    }

                    if (checkDown(grid, i, j)) {
                        count = count + 1;
                    }

                    if (checkUp(grid, i, j)) {
                        count = count + 1;
                    }
                    if (upperRightDiagonal(grid, i, j)) {
                        count = count + 1;
                    }
                    if (downRightDiagonal(grid, i, j)) {
                        count = count + 1;
                    }
                    if (downLeftDiagonal(grid, i, j)) {
                        count = count + 1;
                    }
                    if (upLeftDiagonal(grid, i, j)) {
                        count = count + 1;
                    }
                }
            }
        }

        return count;
    }


    public static boolean checkRight(char[][] grid, int i, int j) {
        StringBuilder s = new StringBuilder();

        for (int k = 0; k <= 3; k++) {
            if (j + k < grid[i].length) {
                s.append(grid[i][j+k]);
            }
        }

        return s.toString().equals("XMAS");

    }

    public static boolean checkLeft(char[][] grid, int i, int j) {
        StringBuilder s = new StringBuilder();

        for (int k = 0; k <=3 ; k++) {
            if (j - k >= 0) {
                s.append(grid[i][j-k]);
            }
        }

        return s.toString().equals("XMAS");
    }

    public static boolean checkUp(char[][] grid, int i, int j) {
        StringBuilder s = new StringBuilder();

        for (int k = 0; k <= 3; k++) {
            if (i - k >= 0) {
                s.append(grid[i - k][j]);
            }
        }
        return s.toString().equals("XMAS");
    }

    public static boolean checkDown(char[][] grid, int i, int j) {
        StringBuilder s = new StringBuilder();

        for (int k = 0; k <= 3; k++) {
            if (i + k < grid.length) {
                s.append(grid[i + k][j]);
            }
        }
        return s.toString().equals("XMAS");
    }

    public static boolean upperRightDiagonal(char[][] grid, int i, int j) {
        StringBuilder s = new StringBuilder();

        for (int k = 0; k <= 3; k++) {
            if (i - k >= 0 && j + k < grid[i].length) {
                s.append(grid[i - k][j+k]);
            }
        }
        return s.toString().equals("XMAS");
    }

    public static boolean downRightDiagonal(char[][] grid, int i, int j) {
        StringBuilder s = new StringBuilder();

        for (int k = 0; k <= 3; k++) {
            if (i + k < grid.length && j + k < grid[i].length) {
                s.append(grid[i+k][j+k]);
            }
        }

        return s.toString().equals("XMAS");
    }

    public static boolean downLeftDiagonal(char[][] grid, int i, int j) {
        StringBuilder s = new StringBuilder();

        for (int k = 0; k <= 3; k++) {
            if (i+k < grid.length && j - k >= 0 ) {
                s.append(grid[i+k][j-k]);
            }
        }

        return s.toString().equals("XMAS");
    }

    public static boolean upLeftDiagonal(char[][] grid, int i, int j) {
        StringBuilder s = new StringBuilder();

        for (int k = 0; k <= 3; k++) {
            if (i -k >= 0 && j - k >= 0) {
                s.append(grid[i-k][j-k]);
            }
        }
        return s.toString().equals("XMAS");
    }


    /**
     * Parses the String input and return 2-dimensional character array
     * @param input the input String
     * @return the two dimensional character Array
     */
    public static char[][] parseInput(String input) {
        String[] spilt = input.split("\n");

        char[][] grid = new char[spilt.length][spilt[0].length()];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = spilt[i].charAt(j);
            }
        }

        return grid;
    }


    public static void main(String[] args) {
        String input = "MSSMMSXSMSMSXMXXXMSMSSMSAMXAXASXSASMSMXMXMMAMSMMMSMSXSAMXSSXMASMMMXXMASXMSXMSSXXXAMMXMXSMSAMAAASAMXMMSSSXXSMMXXXSMXSXMXMMXXMASMMMMSXXMAXXMAS\n" +
                "XAAAAAXMASMSASXMAMAAMXAAMSSXSAMASASAAAXSAMXAMAAASAASAMMSMMMAXAXAMSMMAAAAAXASXAASXSSMAMAMXMASXMMAASASXMAMAMMAMSMMMMAAAMMSMXSAMXSAMASMSMSSMSSM\n" +
                "MMSMMMSMAMAMAMSAMXMSMMXMAAXAMAMAMMMXMSMSAMSASMSMSMMMAMAAMAMSMMSXMAAMMSSMMSXMMMMAAAXMAMAMMSXMAXXXXSASXMASAASAMXMAAMSSXMXAAAMASAMXMXSAAAAXAXXS\n" +
                "AXXXSAMMAMAMMMSXMASAMXMXMMMXXAMXSXAXXAAMAMSMMAAXSXXXXMSSSSSXAASMSMSMAAXAASAMXSSMMMXMXXASAMAMAMSSMMXMASAXMXXXSASMSXAAMSSMSMSAMASXSMSXMSMMSMMM\n" +
                "SSMAMASXMSASXASASMSAXXAXXSXXSAXAXMSXMMSMAASASXMMSAMXAXMMAMXMMASAAAAMMSSMMSAXAXMXXAMSSSXMXXAMAMXAAXMSAMMSMMSMMXSAXMMSMAAAAXMASAMMMASAMXXSMAAM\n" +
                "XAMMMAMXAXAXMASAMMSXMMMSAMXMAMSMSXMASAXMSXSMMASXMAMSAMXMSMXAMSMMMSMSMAXMASAMSSMMMMSAAXMAMSASXSSSMMAMXSAAASAMSAMXMAMAMAMXMMSXMASAMSMMMMMXMMMM\n" +
                "SSMMMASMXMMMXAMXMXMASAAMMXXSAMAAMASAMAMXXAMASAMMMSMAAAXSAMXAMAAAMXMAMXMMASMAXAMXAXMMMMMAMMAMXMAAXMMSAMMMSMAXSASXAMSASXSSMXXXSAMASAMASASMSMSA\n" +
                "AXAAMMXMXMAAMMSMMASMMMXSXXMAXSMAMAMAMASMMMMAMXSAXAMAMSMSMMXXXMSMSXSXSMSSMMMMSAMMSMSAMAMMXSSMMMSMMMAMASAXXMSMSAMMAMMMMMMAMSMMMMSMMASASMSAAAAS\n" +
                "ASXMSMASMSMSMSAAXAMAXSAMXMAMASXSMSSXMAAXAAMASXSMSXSXMAMMXMMSMXMMXMMASAAAMASASAMXAAMXSAXSAMXAXMASAMASXMXXSAMXMAMMXXSMSAMMMAAAXMAXSXMASXMMMXMA\n" +
                "MXMAMMMSAXMAXSSSMSSSMXMAXSMXMMAXAXAMMSSSSMSMSXMAXMXAMXXMASAAMAMAAAMAMMSMMAMXSAMMMSMASAMMMMMMMSASMSASAMXXMAMASMXMMXSASMMXMMMMMSSMXAAASAMXSSMM\n" +
                "ASMMSSMMMMMSXMAMAXAMMMMSMMASXMAMXMXXXAAMAMMAXAMSMASMSSXSAMSMXAMMSSMSSXXAMXXASMMMAAMXMMMMMXMAMMMMAMXXAMAXMAMMMMAAMSMAMSMSMSMSMAXMASMASAMXMAMX\n" +
                "MXSAAMXSMAAMAMXMXMAMXAAAASAMAMAMMAMXMMSMMAXXAMXAXXMAAAAMXMAXMSSXAAAMAAMSSSMASXAMSSSMAASAMXSASAMXMXAXSMSMSASXMSSSXMMSMMMAAAAAMXMXAXMMMMMMSMMM\n" +
                "XAMMSMASMMSSMMMMXMMMSMSSXMMSXSXMXXAXSAMXXMSAMXMMSSMMMMMSXMASAAAMSMMMMSXAAAMXMXSXMXAMSMSASAMMSMSXXMSXMAXAMASAMXAMASAAMASMMMSMSMSAXAXSMSSMMXXS\n" +
                "MSMMXMXSAMAMXAAMAMMAMMMMAMASASAMSSSMMMSAMXMMXAAXAASAMAXMXAAMMSSMXMASXMMMSMMAMAMXSMMMMXSMMMSMXMMMXAMAMMMSMMMAXMAXAMMXSASXXXXMAAAMMSAMXAAAXSAM\n" +
                "XMASXMASAMMXSSXSASMASAMXAMMMAMAMAAXAMASAMXAMXXMMSMMMASAMXSXSXXAMXSMMMMAXMASXSSMMSAXAXASMSXAXXAAAMSMAMAAXAXMSMMSMMXAMMASMMSAMMSMXAMAAMSSMMMAX\n" +
                "MSAMAMASAMXMMAMXMAMASASXXSAMXXXMMSMSMXXAMSSMSSMXMAASMXAMXXAMXSAMAMXAASXMSAMMAAXASXMASMSASXSSXSAMSAXSSSMSAMSMSAAMAMSXMAMMAXXMXAXMAMMXAMXMXSMM\n" +
                "XMAXXMASAMMAMAMAXAAMMAMAASXSMSSMAMMXMASMMXXAASMAMXMMAMMMMMXMAXAMMMSSMMAMMMMMSMMMSAMAMXMAMAMAMXAXXAMXAMAXMXAAMSSMAXAAMMSMSSMXXMSSSMMASXMXAMAS\n" +
                "SSSMSMASAMSASMSMMMSXMXMMXMXAMAAMSMSAMXXMASMMMSMASAMMXMAMAXXSMSMMAAMMMSAMXMAMAMSXSXMMMAMAMXMXMMMMSMSMAMMMMSMSXXMXXSSXMAAAMXMASXAAAAMAMAXMXSAM\n" +
                "MAAAAMXSMMAASXAXMAAMSMSMSMAMMMSMMAXXXSAMXMMAAXXASASAXSASXMMMAAMMMMSAASAMXSXSAXSASXSAXAMASASASXAAAMAXSMXXMXXMMSMMMMXAMSSSXAMASMMSMMMSMXMMAMAS\n" +
                "MSMMMXAMMSMMMMMSMAMMAAAXXXSMSAAAMMMSMSXXSXSMSSMXSAMAMMASAAAMMMSXAMMMMSAMAMMSXMMAMASMSXSMSASXMXMSSSMAXAMXXMAMAXSAAMSMMAMXMXMASAMAMAAXSMAMXSAM\n" +
                "XXAXXMASAXAAAASAMMSSMSMSMXMAXSMMMSASAMXASAMAMXMXMXMXMMXMMSSSSMXMXSAAXSXMMSAMMMMAMXMAAASXMAMAAXXXAXXXSAMMXMXSSMSXXMAAMXSASXMASAMMSMXXAMXMASAS\n" +
                "MSMSMMMMASXMSMSASAMXAAAXAMMMMXMMAMASAXMASMMAMAMMMXAMXXMMXMAAXMASMSXSMSAMAMMXAAMXXAMMMMXAMXMXMXMSMMSMAAMXXXMAAAXASMMXMMMXXAMAXAMXSAMSSMSSMSAA\n" +
                "MAMAAAXXMXMAXAMMMXSMSMSMMMASMSAMXXMMMXMASXSXMASASASASXAMAMMMMSXXAMAMASAMASMSSSSMXXSMSSSSMSMAMAMAXMAAXMMSMMAMMMMXSAMSSMMSSSMSSMMAMAMAMMXAAMXM\n" +
                "SASMSMSMMAMASASMSMMAMXMXMMAMASXMMSSMSAMXSAMASASMSAXAMAXSASXSMMSMSMAMXMAMASXAAAAASMMMAAXXAMMAMAXASMMSMXXXXAXXMXSAMXMAAAXAAXAMAAMXSAMMSMSMMMAM\n" +
                "SAXXAAAXMXMASAMXAAMAMASAMMSMMMAMAAAMAXAMMAMAMMSXMXMAMSMSAMMXAASMAXAXSSSMXSMMSMMMXAAMMSMMMMSMMMMMXAMMASXMSXSAMXMASMMSSMMMSMSSXMAMAMSXAAXAAMAS\n" +
                "MMMSMSMSXAXXMAMMXMMASMMASAAAASAMMSSMMMSXSMMMSAMXMMSMMMAMAMMXMMSSMSXSMAAAXMAXMXMXSXMMAAAAMMAMXMASXSMMMAAAAAXAMXSMMMMAAAXXAXXAMSASASMXMSMSMSMS\n" +
                "XSAMAMAAXMMXMSMXAXSAXAMAMMXMMXASAMAAXAXAMMAXMAMAXAAAAMAMXSAASAXAXAMSMSMMMSSMMASXSAAMMSMMXSASASMSAXAMSSMMMSXMMXXSAMMSSMMMSSSMAXAMXMXAXMXXXAXX\n" +
                "MMASAMAMXSAMXAASMMMMSSMSXMMSSXMMMMMMMMSAASXSSSXMSSMSMSXSAMMSMASXMXMSAAAMAMMMSAMAMSMMAXAMXSASMSAMAMAMAAAAAXASMMMMXSAAAAXAMXAXSMMMSXSMMXASMSSM\n" +
                "MSMMASXXMSSSMMMMAAXAAAXMAMAMAASMSSXMSXSMMXAAAMXMAMXXAMXMXSSXMXMAMMASXMXMXSAAMAMXMMXMASAMMMMMMMXMXMAMSSMMAMAMMAAAXMMMSMMSSXMAXASXAASXMMXAMAMA\n" +
                "MAMXAMXSAMAXXXMSSMMMSMMSMMAMSMMAXMASXAXASMMMMMSMASAMAMASXMMASMSSMMASMMSMMMMSSXMSXSAMXSMMAXXAAMMAAMAMXMMMMMSMMSMSSSMXAMXMAMXASXSAMXMAXXXXMAMM\n" +
                "SASXMMMMAMMMSMXMASXMAMMAMSSXMAMMMSXMMXMMAAMXXAMSMMMSSMMXMAMXMAAXXMAMAXAAAXMAXAAMXMASXMASXMSSSMASMSMSAMXASAXAMXAMMAMXSAMXXMMXXAXXXAXXMASXMMSX\n" +
                "SAMXXAASAMAAAAMSXMXMAXXAMAXAMAMAAMASASMXSMMMMMXAXMMAMASMSAMXMMMMSMSMSMSXMSMASMMMASAMXSAMXXAAAMMMAAXMMMSASXMASMAMMXMAMAMAAXXASMXSSSMXSXMAASMM\n" +
                "MAMASMMSMSMSXSAMXMXMXSSMMSSMSSSMSSXMASMXASXXAXSXSXMXSAMXSASASXAXAAMMXXMASAMXMXAMXSASXMASMMXSXMASXMXAAXMMMMSMMXSMMXMAXAMSSXMMMXAMAMMXMAMSMMAM\n" +
                "XMMMAMAXMAMMAAAXAASXXMASAAAXMMAMXMXMXMMMMAXMMMSMMXXXMAMASXSASMMXMMMXSAMXSAMASMXSASMMAASMMAXMAXXSASXSMXSAAMMMSMMAMMSSSMXAMAXXAMMMAMXAXMMAMMSM\n" +
                "XXAMAMXMXSAMXMMSXXAMMSAMMSMSXSAMAMMSAMXSXMSMSASAXAAMSSMMXAMXMASASASMSMMAMMMASXXAMXMSXMASXSAMXSAMAMMAXAMMMSAASASAMXAASMMASXMMAMAMSSSSSSMMSAMX\n" +
                "ASXSMSMMMXAXXAMAXSMAAMXSXMMSASXSMSASASASAXMAMASMMMAAAAAMSMMXMMSASASAMMMSMMMMMXAMXSMXMASMXXSXSAMMMXSMMSXSAMMXSASXSMMSMMMMAMXAMMMMMAAXAAXAMASA\n" +
                "MMAMXAAMASMXSAMAXAXSMMAMXAAMAMAAAMMSAMXSASMXMAMAMXAXXSMMAMMMSAXAMAMMSXAMXSASMMXMASAAAMXMASMXSMXAXMMXAXXSAMXAMAMXXXXAXASXMAMMXASAMMMMXMMXSAMM\n" +
                "XXSSSSSMAAAASAMSAMXAXMASMMMSSMMMAMMXAMXMASAMXXSSMSMSMAMMXSAAMXSXMXMAMMMMAMASMXSXMXSMMMAXMAMAMXSMMAMAMAMXMXMMMXMXMASXMMMXXSSMSAXAMSAAAXXXMXSX\n" +
                "XXXAAAXMSMMMSXMXMAXXMSAXMAAAAXAMASXSXMXMXMMAXXMAXSAAMXMAASMSXMAXXSMMSASMAXSMMASAMAMMXSMXXAMASMSAXSASMSMMMAAAAAMASAXSAMXSXMAXXASMMXMSSSXXMASA\n" +
                "MSMMMMXMAXMAXAXMMSSSMMSMSAMSMMASAXXSAMXXAAXSMSAMXMSMSXMMXSXMASMMMSAMSAMMMMMAMMSAMSXSXMAMXASXXAMXMMAMAMAASMSMSXSAMAMAXSAMAMMMMMAMXMXXAMXAMAMM\n" +
                "AXAXXXXMMSMMXSMXAAMAAAAXXMXMASXMMSMXAMAXSSMAXMASMMMXSXMMXMASAMXAAMAMMSMASAMAMXSAMAAXMSAMMMMMMSMMMMAMMSSMSXXMAAMMMSMMXMASAMAXSAXAMAXMAMMMMASA\n" +
                "SMXMMMMAAMAAAXAMMSSSMMSSXMAMMMAXAAAXSMSMMAMAXMASMASAMAXAASAMASMMXSXMAXMXMAXAMXSXMMSMAMXSASASMXAXXSXMXMMASMSMMSMMAMAMMSAMXSSSMXMXASMMAMMSAXAA\n" +
                "XAAMXAXMMSMMMSASXAAMXMMMMXAXASMMSMSMMAMXSAMXMMMSMAMASAMMMMXSXMXSASAMAMXAXXMSSMMASAMMAMXSAMASASXMASMSSSMMMMSASMXSASAMASMSXAMXMASXSMMSXSAMXSSM\n" +
                "MSSSSXMAXMXAASAMMSSMMSAASXMMASMAAAAMMSMXMASMXAXMMAMXMMSAAXXAXMXMASASAMXSSXXMAAMXMAXSASXMSMAMMMXSMSAXMAMXAAMXMAASXSASXXAXXXAAXSAMXAAMMMASAAXS\n" +
                "XXAXAXSXMXMXXMAMAMAAXMMMSASMAMMSMMSXAXXAXAMASMSXSSSMXAXMSMMAMXAMAMXMASAAAMMSSMMAMMMMASMAXMAMAAASAMXSAMMSMSMAMMMXASAMXMMMMMSMSMSASMMSMSAMXMSA\n" +
                "XSMMMMSMAXAMXSMMSSSMMXSXMAMMMMAXXAXMMSSMMSXXAMMMXXAAMSSMAMMAMXMSAXXSAMMSMSAMXASASXSMAMMSMSSSMMSMAMAMXAMXAAXXMMSMMMAMMXAAAAAMAAXMMAMAAMSMSXAM\n" +
                "MXXAMASXMMXSAMAAAMAASAXAMAMASMSSMSXAAAAXAXMMAASMSMMMMAAXASMMMAMAMAXMASXMXMMSMMMASAAXSXXAAAAAXXXXAMXAMXMMSMMXSAXAXMASMSMSMSSSMSMMSAMMSMAXMMMX\n" +
                "AMXXSASAMAXMASMMSSMSMASMSASASAMXAAMMMSMMASASXMAXMASAMMSMXSMMSMSASMMSAMXSSSMSASXXMXAMXAAMXMSMMMMSXMMMSMAXMAMXMXSAMSASAAXXMAXAMXAXMAMAMMMSMAXS\n" +
                "SAMXMXSAMASXMMASXMXAMXMAAASMMMMMMMXSXMASMSAMMSMXXAMMSXAXAXAXAMMAMXXMASXXXAASAMMXSXMXXSMSXMXMXAAMASAAAXMMSMMMXAMAXMSMXMSMASXMMAXSSSMAMAAAASAS\n" +
                "XXXXSMSXMASAMXXMAMSMSAMXMMMMAAXAXXASASAMMXMAMMXSMXSAMXSMMSMMMASAMXSSMMMMMMMMAMSXMASMXMAXAMASMSMSAMMSMSXAXXAMMMSAMXAXASXXAXAMMSAMAXASMMSSSMAS\n" +
                "XXSAMXSXMASAMAAMAMSASMSMSMASMSSSXMMMAMMMMMMXMXAMXAMAMSMAAAXAMASASAAXAAAAXXXSAMMAMAMXAXXSMMASAMAMXSXMXMMSMXXMAXXAMXSMMSAMXMAMAMAMXMAMAMXXXMAM\n" +
                "XAMXXMSASASAMXXMAXMAMASAASXMXMXAASXMXMAAMAAAMMSSSXSAMXASMMSSMXSAMXSSSSSSSMASMSMXMMXSSSMMXMASMMMMXMASAXAAAMSSMSMSMAXXSXMASXXMXSAMAAXSSMAXMMAS\n" +
                "MXMMAAMAMAMAXSASXSMMMAMSMSMMAMMSMMAMSMSSSMSXSAXAAMXAAAXMAMAXMAMXSAMXMAMAAMAMMAMMMSAAMAAAAMXSXSAMAMAXXMMMXMAAXAAXMAMSMAMXMASAASMSSSXMAMXMMASM\n" +
                "SAXMASMMMMSAMSAMXAAXMMMMMMASMXAXMSAMSAMAAMAMMXMXMAXXMXSSXMAXMAXMSAMXMAMSMMSMMAXAAMMMMSMMSSMMASMSSSSXSAMSSMSXMMSMMXSXSAMMMAMMMMXAMXXAAXMSSMMX\n" +
                "SMASAMXXAXMAMMXMSSMMSSMAAMMMSMAXMMMMXAMSMMAXXMAMXSAXXXAMMMSMSMSASAMXSAMAXAXMSMMMMSXXMXXXAAAMAMXAMAMAMXMXAAAMSMMXMXMAMXMXMASMMMMMMMMSSSMAAASX\n" +
                "MAMSAXSMMMXXMAMXAMXAXASMMMSAMMMMSSMXSXMXXSMMSASXAMMMSAMXAAAXAAXMSSMMSSSMMMXAAAASXSMSMMAAASMMMXMMMAMSMSMSMMMAXAXASXMSMSMSAASXAAAAAAAAAXMAMMAS\n" +
                "XAMXAMXMMXSAMMMMMSMMXMMSAMMAMSXMAAMAMAMXMSAXASMMMSAAASXSMSXMMXMXMASAMAMXASXSMSMSAXXAAMASXMMXMAXMMSMMAMXMXXXSSXSMMXAAAAAAMMMXMSSXMMMXMAMSSSXS\n" +
                "SMSXSMSXAAXXMAAMXMASASAMXMMAMMAMXXMXSAMAMMAMMAXAASMMXXXMAAXAXAXASMMMSAMMMXAMAMAMAMSSSMMMMSSMSXSAAMAMXMSMMSMAAASXMMAMSMSMXMASXAXASMSSXSXAAMAM\n" +
                "SAAAAAXMMXSSSSSSMSAMXMASAMMSMSAMSMSMMAXAMXAAXMXMMXAMSMXMXMASXXSASXAXMASASMAMAMSMSMAAMXAAAXAAAAXMMMAMXMAAAXMMMMMAMAXXMXAXXMASMSMMMAASAAXMXMAM\n" +
                "SMMSMSMXSXAMAMAMAXASXMAMASAAXSMSAAAASXSSSSMSSMSXMMSMAMSXMMMAAXMAMXMSMXXASAAMASXAMMMMSMSMSSMMMSMSMMMMXSSSMSAXMASAMSAMXSASMMMXAMAXMMMMMMMSASAS\n" +
                "SAXMAXMAMMMMAMXMXMXAAMXSAMMSMXSXMSMXMMAAAMAAAASMMAMSMXMAAXXMMMMAMAMAMAMSMXASXSMAMAAAAXMAMAXMXMXMAASMMMAAAXMMMASMSMMMXXMAMAASMSSMSAAAAAASASMS\n" +
                "XMMMAMMASAXSMSMSSSMSAMAMAXSAMXMMXAXXXMMMMMMMSMMAMAMAXAMSMMMMAASASASAMMXXASXSASMXSMMMSXSXSMMMAXXMSMXAMXSMMMXAMMMMAAASMSMASMMXAAMASXSSSMMSAXAM\n" +
                "MSXMASMXSXXSAAAMAMXAAMXSAMAAMMAASMSMAMXMSMXMMASMMSSSMSMXASMSSXSASMSMXMAMXMAMAXMMSXMXMXSAMXAMXSAMXXSSMAMXXAMMSSXMMSMMAASASXSMAMMXMAMAAMAMAMSM\n" +
                "XSAMASAXMASMSMSMAMXMMMAXMASMMMMAMAAAAMXXMMAAMAMAAAAAAMAXAMAAMAMXSMMASMASAMXMXMSASMXAMAMAMSMSAXXMAXAAMXMXSMMSAMMSMXXMSMMMSASASMXMMXMSMMXXAAAX\n" +
                "MMSMAXMAXSXMASAMASMSSMMMSAMXXMMSMSMSMSXAASMMSASMMSSMSMAMSMMMSXMAXAMAMMXSASASAAMASAMXSASAMAXMXMSSMSSSMASMAASMAMAAMXMMMASAMXSAMXXAMXAXXSSSSSMS\n" +
                "SXMMSSXSMASMXSXSMSASXAMAMAMMSXAXAMXMAXXMMSAXMAMMAMXAXMAMAASAMASMSSMMSSXMMSASMSMSMXAMSXXMXSSMSXMAXMAXMASMMXMSAMSAXXMAMAMASXSAMXASXMXMAMAAAAXA\n" +
                "AAXAMXAXAXMSAMXSAMXMAMMASXMAMMMMMSAMXMASAMMMMAMMMSSSMSXSSSMASAMMAMAAXXASXMXMAMMXMSSXMMSSXAAAMXSSMMMMMMSXMAMXMAMAMSXMMASAMMSAMSMMXASMSMMMMMSS\n" +
                "MSMMMMMMMMSXSXAMMMSMSMSXSMMSSSMAMXAMMMXMAMXASAMXSAXAAXMXMAMAMXXMASMMXSMMAAXMXMASMAMAASAMMMMMXAMAAMXAXMXAMSXSSSMAMMAMSMMASAMAMAAXXMSAAMAXAAXX\n" +
                "XMAMAMMAXSXAAMMSMAAAAASASAXAAXMASXSMAAAXXMSXSMSMMXSMMMAXMAMXSXMXMSXSXAAMAMMMAMSMMASXMMASMSSSMASAMMSMXMSMMAMXAASMMMAMAASAMXSSSSSMMXMXMAMMMMMM\n" +
                "XMXMAMXMSMMMMMXAMMMMMSMAMSMMSMMXSAMXXSASXMMASXAAAMXMSSSMXSSMMXSAXMAMXSSMMXAXXMAASAXAXMAMAAAAMAMASXXAAXAMXMAMMMAXASASMXMMSAXAXMAXSAMMXSSXSASM\n" +
                "MSMXMSMMAASMASMSMSSMMMMXMAMSAXMAMMMXXAAXAAMMMXXSMXMAMAAXAXAAAAMAMMAMAAMAMSSMMSMMMMSMSMSSMSMMMMSXMXMSMXASMXSAXXMSXMASMMMSMMSSSSSMMXSAAMAXSASX\n" +
                "MASAAAXSMSMMASMAMAASASMMSSXSAMMAMXMXMASXSMMMMSMMMAXXMXMMMSSMMMSAXSAMMMSAMAXAAAMSMXAAAAMSAMXXXXAMXAXAMMSMMXMASXXSAMXMAXXAAMSAAAAXXAMMSSMAMAMA\n" +
                "SASMSMMXXXAMAXMAMXSMMMAMAMMMAMSASAXSMMMAMASAAAAAMMSMMSXSXAMAXXAMXSASXASXSAMMMMSAAMMMMSMMMMSMSMSSSMSASMMAASMMMMAXMMMXMMSSXMMMMMMMMXMAXAXSMSMS\n" +
                "MASXXXSSXSAMSSSXSXMMXSXMASXMSMSASXMSAMMXMASMSSSMSAMXAMMMMMSAMAMSXMASMXMAXMXSAXSMSXSAMXAXMASMXAAAMXMXMASXMXSAAMSMSAMAAAAAASXSMXAAXMASXMMXAXAX\n" +
                "SXSASAAXASAMXAAASXMAMAMXXAXAMAMMMAMXAMSAMASAAMMMMMMMMMAMSMMASMXSAMAMMAMXMAXMAMXXMASAMSSMMASXMMMMSAMAMMMXMASXMSAASASXSMMSMSASAXSXSXMMAMMMMMMM\n" +
                "MAMAMSMMMMASMAMMMAMMSAMSSSMXMAMSSXMSSMMXSAMXMAAAASXSMSMSAAXXAXAMAMMMSASAAXMMMAMAMAMAMAAAMASAAXXASASASXSAMMSXMMMXMAMAAAAXXMAMMMMAMXMSAMAAAAXX\n" +
                "MXMAMAXAASMMAXMASXMXMAMAAMAXXAMXXAMAXAXMMMSAXSXSMMAAAAMSXSMSAMXSSMMMSASXSMSXMAAAMASXMXSXMXSMSSMMMXXASXXAXASAXMSSMSMSSMMSAMXMXAMAMMAMASMSXXSA\n" +
                "AAMAXASMXXAXSXSASAMSSXMMMMMMSMSMSSMMSSSXAMXMAXAXXMSMMMMXAXAMMMMMAASAMXMMAMAASMMSXMXMSXMASXXMAXAMMSMMMMSSMASAMXSAAXAXAMXXMXSSMXSAAXMSMMAMMSMX\n" +
                "SSSSXMMMXSMMMAMMSAMMSXAAMAAMAAAXXAAAAXAMAMAXAMMMMXMAMXMMMMSAMXAMMMMAMAMXAMXXXMAMXMMXSAMXSAMMSMSMASAMXAAAMAMXXMXMMMXMMSMMXAMAMXMMXAMAMXAMMMAM\n" +
                "AAAAXSSMMAXAXXXXXXMAXMSMSSMSMSMSXXMMSMMAASXSASAAAAXAMAXAAMAXMSSSXXSSMSSSMSSSMMAMASAMXAMAXMMAXAMMXSSMMMMMMASXMMMSAMXXXXASMMMSAXXAASXMSSMSAMXM\n" +
                "MMMMMXAAXXXASAMXSSSSMMXMXMAXXAMXXASXXXXSASXXASMMMSSSSSSSSSXMASAAXMAXAXAAAAAAAMMMAMXXXSMSMXMASASXMXASXMMXSASMAAAXASXMASMMSAAMSAMMXMAXXMASASMS\n" +
                "XAXXXSSMMMXXMAAASAAAAAXSAMXMSMMXAMXAXMXMASXMAMASAMXXXAAMAXMXXMMMMAAMMMSMMMSSMMXMSSMMSMAXXAMASXMMMSAMXSAXMASAMMMSAMXMASAAXMXSMXXMASXMMSXXASAX\n" +
                "XAXSAXXMXXAASXMMMMMSMMMMASXMXXMXSAMXMSMMSMMAAXXMSSMMMMMMAMMMXXXSSMSMMAXAXXXMMSMMAAASAMAMSSMXXMXAMMAMAMAXMAMMSAMXASAMXXMMMXSXMSASXMSAMXMMMMMX\n" +
                "AXAMAMXMAXXXMMXAAAXAAMASAMAMMASAMAMXXMAXAAMSXSAAXXAXMAXMMSAAXSMAMXXAMSSMMMSMAXAMMSMMAMMMAAMXMAMSSSXMXSXSMAXXMAXSSXMMSXAMSXMAXSASAMXMXAXAXAXM\n" +
                "SMXSAAAMASMASASMSSSMXMAMAMSMSAMXSAMXMMSSSMMAAXMXMXMMSXSAASMSMSMAXXSAMAAAAAXMASMMMMMSSMMMMXSAMSAMAAXSXMXAMAMSMMMXXASASMSMAMSMMMAMMMXMSMSMSXSA\n" +
                "XAXMAXXXAXXAMXMAAMAASMSXXSAMMXSAMMSXAAAMAMMXSSSXSSMAAMMMMSXAAXSSMMXAMSSSMMSMMXMAMMAAAAASXMSASASMMMAMAMXMMSMSAASASXMASAAAMMAAAMAXAXAXAXAXMAMX\n" +
                "SSSSMSMMMSMMMAMMMMMXMAMAXSAMAXMASMSMMMXMAMXAMAXXMAMMMSAMXMMSSXXMASXSMXAMXASASMSMMMMMXSMSAMXMMMXXAMAXAMXXMXAMSMMASMMMMXMMXMAMXSASMXSSMSSSMAMX\n" +
                "XAAXAAAAMAASXSMMAAXAMAMSMSMMMSMMSAMASASXXXMXSAAMSSMSASASAXXMMAXMXMAMXMXMMAMAMXAASXSMAMASAMAXAXMASXSSSSMAMMMMMXMXMXAXMASXMMSSMMMSAAMAMXMAMXMM\n" +
                "MXMMSMSMSXSAAMSSSMSSXMXAAXXAXAXMMASMMASAMSXMMASXAXXMXSAMAXXASMSMXMXXXSXSMSMXMSMSAMAMAXASASXSMSAXMAXAAMMASAXAXAMMMSMSMXAAAAAAAAXMMMMAMXXSMSXA\n" +
                "AAMAXXAXSXMMMMAXAAAMAASMSMSMSMSXSAMXMXMASMXMXAMMMSMMAMXXMMMMAAAMMSSMMMAAAAMXXXMAMAMMSSMMAMXAASMSMMMMSXSASASMSMMAMAXAAMMMMMSMMMAMSSSSMMXAAMSS\n" +
                "MAMASMSSMAXXXMXSMMMMMMSXMAXMAMMMMMSAMAMMAMMMMMXXMSMASXMXAMXSMSMMAMAAAMSMMMSMMXSAMSXAAMAMMMXSASMMAXXXAAMAMASAAMSMSMSMSXXASMMMSXSXAAAXAXSMSMAM\n" +
                "SMSMSAMAMAMMSSMMAXSAMXXAXSMSAMXMAMMMXAMXSXAAAAASMXMAMAMSXMASXMMMSSSMMXXAXXAAMAMASMMMMMAMAMAMAMAXXMXMMXMMMSMMMMAASXSXXXMASAMASAXSMSMMMMMAXMXM\n" +
                "AAAAMXSAMASASAMAAASASXSMMMXMMMMMMSXSSXSAASXSMXAMAAMXSAMAAMASXXMXMAAXXXXSMSSSMASMMXAAMSSSXMXSSSSMXMAAMASAMXAXXMMMMAXAMMAAMAMAMXMAXAXAAASMSMSS\n" +
                "MSMSMASASAAXSAMXMXSASASXSMSMSAAMAMXXAAMMMXMAMMAMSMSXSXMXSMASAMXMMSMMMAMXAXMAXASXSSMMXAMMAMXXAAMAXSAMSASASXMMXXXSAMMMAAXMSMMXSMMSSMSSSXSAAAAX\n" +
                "XMAXMASMMASMSXMXMXMAMXMASAAASMSMASMMMMMSSSMAMMAMAXXMMASAXMMMMXSAAAXAXSAMMMSAMXMAMXMMMMSXMMAMMMMSMMAMMASXMAMXAAXAXMAXXMMXAXXMXAAAAXAAXAMMMMMS\n" +
                "XMXMMMSXMAMAMXMMSAMAMXMXMMMXMAMXMXAAAMAAAXMMSSMSXSMMSAMXSMXXAAMSSXSASXMASAXXMXMMMXAXAAXASXXSAMMAAMSXMMMMMAMMMMMMMXXMASXXMSSXSMMSMMMSMSMXXAMX\n" +
                "SXMXMAMMSASAMXMASASXSASASASAMXXAMSMMMSMMSMXAAAXMAAMAMASXMASMMMXMXMAXMXXXSASMSSSSMSMSMMSAMAXMASMSXMMMMMMSMMXXAAAXAASMMSAASAMMAMXXMAXAAXAXSXSX\n" +
                "XXMAMASASASXXXMXMAMASASASXSXSMSAXAAXMXXXAMMMXMMMXMMXSSMXXMAXMMAXMASMMSMMMAMAAAAAAAXXAAMMMMMSXMAMXAMMSAXMASXSMSMSAXMAXMMMMASXSMASMXMMSMMASAMM\n" +
                "MASASXSAMAMASXMSXXMMMXMAMAXAAASMSMSMAAXSASXSXSASAAMXXMASMSMXSSSSMSXMAAMAMAMMMMMMSMSMXMSXSAMXMMAMSSMASXMSAMAAAAXXMASXMSSXMXMAAMXMXXSXMASAMASX\n" +
                "MMMAMMXAMSMMMAAXAMXXMXMAMXMSMAMXXXMMMMAXAMXSAAASMSMSSMAMAAASMAMMXXXMSSSSSSMXSAMXAAAMMMSAXXMAXSXMAMMMXAMXAMSMMMXSXMMAMXAXXXSXMASXMXMASXMXSAMX\n" +
                "MASXSMSAMXAASMMMSMSASAXXXMAAMMSAMXMXXSMSMSAMXMXMXXMXMXAMSMMMMAMMASAXXAXAAAXASMSAMSMSSMMAMSSSMASAMXMSMSXMAMXMASMMAXMAMMMMMMAAXAMAAMSAMXXXMAMA\n" +
                "MMSAAAAXXSSMMAMXXASAXXXSMXSXSAXMAXAAXXXAAMMSAXAMMSSMSSXMXMXSSMSMASXMMMMMMMMMSAAMMMXXAAMXMXAAAMXSXMASMAXMXSXSASASXMSAMAAAASXSMXSASXMASMXXSAMM\n" +
                "MXMXMMMMAMAXXAMMMAMAMSSMAAMSMMSSSMSSSSMMXMASMSMSAAASAMXAXMAMAMXMXSASASAXXXXAMXMMSXMSSMMXAMSMMXMMXXASXMMXAXMMXSAMXAMAXSSSXSAXAMXXMASXAXSMSASX\n" +
                "SMMSXSAXXSAMSXMAMMMSMXAMSMSAXAAXAAMAAXXAXMAMAMXMMMMMMMXSAMASAMXSMSASXSXSAMMSSMXASAMAAAMXMAAXMASMAMSMAXAMSSMMMMMMSMMSMMAMAMAMAMSASAMSXAMAXXMM\n" +
                "AASMASXMMMAMAMSSMSAMMSSMMXMMMMSSMMMMMMXMMSAXSMSSMSSMXSAMMSAXAXXSAMXMAMAMAXAAAAMXSAMMSMMAMMMSMAAXASASXMMMAAXMAAAAXXAXAMXMXMASAMXAXMASMSMMMAXA\n" +
                "MSAMXMAMXSAMAXAAAMXSAAAMMAXXXAAMAMXSASAMAMMMXAAAAAAMAMAMXMMXSMMMAMSSSMASAMMSSMMXSXMAAASMXMAAMXMSXSASXSXMMSMSSXMMXMMMAMSSXSXSASMSMXAXMMAMSMMM\n" +
                "XXXMAXAMXSXSSMSAMMMMMMSASASXMMSSMSASMSAMSAMAMMMMMMSMSSMMSXXAMASXSMXAASAMXSXAMAMXMMSSMMXAXAXXSAXMAMAMASMSAAAMMASMMSMSAMAXASASXMAAAMASXMXMAAAX\n" +
                "AXAXXSXSXXAXAAMMAXAXAXXAXXAMAXXXAMASASAMXAMXSXMASXXAMAXSAMMMSAMXAAMSMMMSMXMSXSMMSAXAMMMMMMXAMXMXSMMMAMAAMMMMSAMAAAASAMXSAMAMAMSMXAXAXMASXSSS\n" +
                "MSSXMAMSMMXMMMMXSSMSASMSMSMAXSXMMMMMAMMMSXMMMMXASMMAMAMSAMAMMAMSXMMMMAAAAMMMAAAXMMMMMAAAXXASXMSXMAXMMSSMXMAXMXMMMSXMXXXMXSXMMXAAXSMXMSAXAAAA\n" +
                "MAXAAMASASMAMAMXMAMXAMAAAMXSXAAMXAAMSMMAMASMXSMMSASXMAXMAMXMXAMMAMXXSMSSSXAMSMMMMMSSSMSMXAXXAASXSAMSMMAXXMMSASMXMXXAXMXMASAXXSXSMMAMAAXSMMMM\n" +
                "MSSMMSXSMMASASMMSAMMMMSMSMAXAMMMSXMMXMMAXAMMAXAMSAMMXMSSXMAMXAAXAMMXXXAMAMMMAXXAMXAAAMAXAXMMMMMAMXXAAXMMMSMMASXAMMSXMMAMXXAMXAMMAXMAXAXAXAXM\n" +
                "MAAAASASXSSMMXAMSAMAAMAAAMMMMSSXMMSMAXMSXSAMXMAMMMMSSMAAAMASXSMSASXSMMMMAXXMXSSSXMMMMMAASAMXAAMXSSSSSMSAXAAMSMMMSASAMSMSMMSMMSXSAMMMSAMXXMAS\n" +
                "MSSMMMAMAMXMXSAMXSSSMSMSXMSAAAXAMAAMASXAAMXSAMXXXAAXAMXSXMASXAMSMSAMAAAMSMMSAMAXXMSXMMSXMMAMSXSAXAAAMMMSSMSMXAXXAMXAMAXMAAAAXAAXASXAAAXAMXAM\n" +
                "XMAMSMMMSMMSAMAMAXXXXAAMAASMMMSXMSSMAMMMMMAAAXAMSMSSXMMMXXMSXMXXXMAMMMMMAAXSAMAMSAXMMAMAMXAAAAMXMMMMMXMAMXXAMXMMMSSMMSSXMASXMMXSSMMMXSMXXASX\n" +
                "MMAMAAAAMSAMXSSMMSMSSMMMMMXXXMXMMXXMAXAXAMXMMMSXMAAXAAAMMMMMMSMMMSAMSMSSMSMSMMXMXXMSXAMAMSMSMXMMSASAXMMXSASAAASMMAAAAAMMXMMAAXAMXAXAXMAXAAXS\n" +
                "MMMSMMMXXAMXAAXAXAMMXXAXXSMSMSASXMXXASMSSSMXAAXASMSSMMMXAASAXAXAMMASXAXXXAAMXSXSMMASMMMAXXAAMAMXSAXASXAAMMSXSMMAMSSMMMSAASMSMMSSSMMXMMAMSXMX\n" +
                "MAMAXSSMMSSMAMXAMMMSAASMXAAAASAMASAXXXMAXAAMMMSAMAMMAAXMSMSAMMSXXAXMMXMASMSMAMAAAMAMAMSMMMXMSSSMMSMAMMMMSXSAMXSAMAAAAXMXSAAMAAMXAXSAMMMMXAMX\n" +
                "SXSAXMAMXAAMMMASXSAMXMMAXMSMMMMMXMMMMAMXSSSSSXMMMSMSMSSXMAXAMXAMSSMXXXAMXAAMAMSMXMASMMASXASMXAAMAMXSMXMXSAMAMASASXSMMMAMMMMMMSMSMMSXMAXSMSMS\n" +
                "XMMMMSMMMSSMXMXXAMASMSASXXMMSMMMAMAAXAMXAAAAXAMXAXXXAAMMMMMSMXAXAMAXSMXMXSMXAMAXMSASMSAXXMAXAXMMXSAMXXXAMAMXMXSMMMMMMSSMASXMXMAMXAMASMMSAAAX\n" +
                "XAAAXAXXAXAMMMAMAMXMASAMSXSAAAASAMMMXAMXMMMMMMMMMMMMMMSAMAAXMXMMMMSAAXAMXXXMSSMSAMASAMXXSASMSSSXAMMMMMMSSMMSMXSXSXAXMAAMASAMAMAMMAMMMXAMSMMM\n" +
                "MSSSSSMMMSXMAMASAMAMMMAMSMMXMSMXASASXMMAXXAXSMASMASAMASASMSSMXSAXSMMMSASMSSMAAAAMMAMMMSAMAXAXAXMXSAAAXAAXAASMMSAMSMSMSSMXSAMASAMSMXSAMMMXXXA\n" +
                "AAAMAXXAXAAMXSMMASXSAMAMXASXMMXSAMASXSMSSMXSAMAXXAMAMAXMMXMAAASAMXAAXSXAXAAXMMMMMMSSMAMASAMSMMMSXSMSSMMSSMMSAAMXMAXMXAAXASASASAMSAASAMSAMXXA\n" +
                "SMSMAMSSMMMSMAMSMMASXSSSSSMAAAXMXMMMMAXMMXMAMMASXMSAMMSMSXSMMMSASXSMASXMMSMMXAXSAXXAMXSAMXSXMSAAMXAXMAAAAXASMMSMSMSSMMMMMSAMASMMMMMSAMMMSMSX\n" +
                "AAMMSXXXAXAAXAMAASMSXAAAMMSXMMSMAXAAXMMXAAXAXXAMXAXASXAASXSXSXSAMAAMAMAXAXXAXXMSMXSAMXMXSMMAAMXXAMSMSMMSAMXSXXMAXMAMAXXAXMAMMMXSSMAMAMXMAMMM\n" +
                "MMMAMMSSMMSSSMSMXMAXMMMXMASMSAAMASXXMXAMMMXXSMASMMSMMMMMMASXMAMXMSXMASMMMXSSSMASAMMXMXXXXAXAMMMMSAAASXMMMSAXMMSXXMASMSSMMSSMXMXAAMXSAMXMAXAM\n" +
                "XMMAMXAAAAXAAAAXSMSMXXXXMASAMXSMMXMAMXMAXXAMXMAXAXAXSXSAMMMAMSMSMMXMAXMASAAXMAXSAMXAMSMMMSSMMSAAMSMXMAXXAMMMSAAMSXMXAXXAASAMXAMSSMXSASXSMSAS\n" +
                "SXMMSMSSMMMSMMMSXAXMAMASMAMAMMXASXXSAMXSASMMMAXSSSSXSAXMMASAMAMAAXAMMMSAMMSMSSMMXMSXXAAAAMAAAMSMMMXMSSMMMXSAMSSSMASMSMMMXXAMMXMAXMXSMMMAMSAM\n" +
                "XAMAXAMXAMXMMXXXMMMMAMXAMASAMASXMAAXAMAMAXMASMMMAAXAMSMXSASASXSSSMSXSAMXSAXMAMXXSMSMSXSMSMMMMXAXMXAMAAAXAAMMMAMAMAMAAAAXASMXMAMMASAMXAMAMMMM\n" +
                "SSMSSSMSSSSMSSMAMMAMAMXMSMSASMMAMMMMMMXSMSAMXXAMMMMAMMMXMASAMMMAXAAAMXMSMMSMMSSXXAXAAXXXMXMAMXXMASMMSSMMMSSXMXSSMSSSSSMSMXAAMMSSMSASMXSMSAMX\n" +
                "XXAAAXXXXAMSAXMAMMMSASMXAAXAMXSAMAAAAMXXAMXAMMSMXXAMSASXSXMXMAMAMSMMMSXMAXAXMAMAMMMMMXMAMAMAMSSMMSAMXMASAAMMMMXXAXAAAMAMXSSSMXAAMSAMMASASASM\n" +
                "AMMMSMMMXMMMSSMMSAASAMXSMSMMMMMASMSSXSASXMSASMAMASMMSASAMAMAMXMMXMAXAXASMMSMMXSMSAAAAAMAMSSMMAAMXSXMAMSMMXSAAASMSMMMMMAMXAAMXMSSMMMMMAMXSAMM\n" +
                "SXMAXAXMASMXAXAAXMASAMASAMXAAMSMMMAMXMASAAMAXXAMAAAAMXMASASASAXMASMMSSMMAAXAMAMXMASMSMMAMMAMMSMMASMSMSAAMXSXSMSAAMASASXSAMMSXXAAXAAAMXSXMAMX\n" +
                "MAMAXXMXAXAMMSMMSXMSAMMXXXXSXXAMAMAMXMASXMMSMSMSMSMMMXXASMMASMMSASAAAXXXMMSXMASASXMMXMSMSMAMXMAMASMXSSXSMAMAMAMMMSASAMSAXXAXAMMSMSSMSMSASAMX\n" +
                "SAMMSMSMSSXMXSAXMAMXXAXSMSMASMXSASXSXMXMASAXAXAXAXASMSSMMAMXMMAMSSMMMSSSXMAMSASXXAMXAXAAXMSMMXSMAMASXMAMMAMAMAMAXMAMAXAMMMMSMAMAAXAMAMXAMMSS\n" +
                "SASXAAXAAXAMXSXMSMMMSMAAAAMXMXMSMSAMXASMMMSSXMXMAXXAAAXAMMMSAMXSXSASMXMASMXMMASAMSMSSSMXMMMASAXMXSMMAMAMSASAMXSMMSSMMMMXAAMMMMAMXXMMSSMSMSAX\n" +
                "MMMXMSMMMSMMAMAAXXAAAMSMSMSMMSMSAMAMMMXAXAXMXSAMXSSMMMSMMSXMAMXMASMMMAMAMMMMMAMAMXAXMAMASASAMXMMAAXSSMAMSXSASAAXAAAAXAXSSXSAAMXSSMSAASAMXMMS\n" +
                "SAAXSXAXAAAAXMMMAMSSMMAAAMAAMXAMXSAMXXSAMXSAAXXXMAXXAXAXAXAAXMAMAMAAMAMMMSAMMXSSMMXMMAMAAAMSMAMXAMMXXXXMMAMAAXXMMSSMMSMMMAMXMSAXAAMMMMMMXAAM\n" +
                "SMASXSMMSSSMSXSMSMXAXSMSMSSSMMXMASASMASAMAXMASMMSMMSSSMMSSXMASAMASXMSSSMAMSASMMAAMMSSSSXMAMAMMMSAMAMMMMAMAMMMASAMAXAAAAAMMMSXMASMSMMASAMSMSS\n" +
                "SAMXXXXAAMAMMAXAXMASMXXAAMXMASXMXSAMMAMAMMSAMSAASAAAXAXAMSASASASMSAAAAAMXSMMMASMMSAAMXMXXSSMSAASAMSXMASASASAXMASMMSMMSSMSXXSAMXXXAXSASAMMAAM\n" +
                "SSXMASMMSSMMMAMMMASAMXSMSMASAMAXAMAMMSSXMXXXMSXMSMMXSAMSSSXMMSAMXSMMMSMMSMMXAXXXSMMXSAXSAMXXMMXSSMASMXSMSMSASMXXMXXXAXMASAXSXMSASAMMXSXMMMMS";

        char[][] grid = parseInput(input);

        System.out.println(grid[0]);
        System.out.println(search(input));
        System.out.println(search1(input));



    }
}
