package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import net.sourceforge.pmd.lang.apex.ast.ASTLiteralExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.AbstractApexNode;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class TestClass08v2 extends AbstractApexRule {
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9]{5}[0][a-zA-Z0-9]{9,12}$", Pattern.CASE_INSENSITIVE);
    private List<String> idList = new ArrayList<>();
    public TestClass08v2(){
    	Collections.addAll(idList, "000", "001", "002", "003", "005", "006", "007", "008", "00A", "00B");
    	Collections.addAll(idList, "00C", "00D", "00E", "00F", "00G", "00I", "00J", "00K", "00M", "00N");
    	Collections.addAll(idList, "00O", "00P", "00Q", "00R", "00S", "00T", "00U", "00X", "00Y", "00a");
    	Collections.addAll(idList, "00b", "00c", "00e", "00f", "00g", "00h", "00i", "00j", "00k", "00l");
    	Collections.addAll(idList, "00m", "00n", "00o", "00p", "00q", "00r", "00s", "00t", "00u", "00v");
    	Collections.addAll(idList, "00w", "00x", "00y", "00z", "010", "011", "011", "012", "013", "014");
    	Collections.addAll(idList, "015", "016", "017", "018", "019", "01A", "01B", "01C", "01D", "01E");
    	Collections.addAll(idList, "01G", "01H", "01I", "01J", "01K", "01L", "01M", "01N", "01O", "01P");
    	Collections.addAll(idList, "01Q", "01R", "01S", "01T", "01U", "01V", "01W", "01X", "01Y", "01Z");
    	Collections.addAll(idList, "01a", "01b", "01c", "01d", "01e", "01f", "01g", "01h", "01i", "01j");
    	Collections.addAll(idList, "01k", "01l", "01m", "01n", "01o", "01p", "01q", "01r", "01s", "01t");
    	Collections.addAll(idList, "01u", "01v", "01w", "01x", "01y", "01z", "020", "021", "022", "023");
    	Collections.addAll(idList, "024", "025", "026", "027", "028", "029", "02A", "02B", "02C", "02D");
    	Collections.addAll(idList, "02E", "02F", "02G", "02H", "02I", "02J", "02K", "02L", "02M", "02N");
    	Collections.addAll(idList, "02O", "02P", "02Q", "02R", "02S", "02T", "02U", "02V", "02X", "02Y");
    	Collections.addAll(idList, "02Z", "02a", "02b", "02c", "02d", "02e", "02f", "02g", "02h", "02i");
    	Collections.addAll(idList, "02j", "02k", "02l", "02m", "02n", "02o", "02p", "02q", "02r", "02s");
    	Collections.addAll(idList, "02t", "02u", "02v", "02w", "02x", "02y", "02z", "030", "031", "032");
    	Collections.addAll(idList, "033", "034", "035", "036", "037", "038", "039", "03A", "03B", "03C");
    	Collections.addAll(idList, "03D", "03E", "03G", "03H", "03I", "03J", "03K", "03M", "03N", "03P");
    	Collections.addAll(idList, "03Q", "03R", "03S", "03U", "03V", "03Y", "03Z", "03a", "03b", "03c");
    	Collections.addAll(idList, "03d", "03e", "03f", "03g", "03h", "03i", "03j", "03k", "03m", "03n");
    	Collections.addAll(idList, "03o", "03q", "03r", "03s", "03u", "03v", "040", "041", "041", "043");
    	Collections.addAll(idList, "044","045","049","04B","04E","04F","04G","04H","04I","04P","04Q","04R",
    			"04S","04T","04U","04V","04W","04X","04Y","04Z","04a","04b","04c","04d","04e","04f","04g",
    			"04h","04i","04j","04k","04l","04m","04n","04o","04p","04q","04r","04s","04t","04u","04v",
    			"04w","04x","04y","04z","050","051","052","053","054","055","056","057","058","059","05A",
    			"05B","05C","05D","05E","05F","05G","05H","05I","05J","05K","05L","05M","05N","05P","05Q",
    			"05R","05S","05T","05U","05V","05W","05X","05Z","05a","05c","05d","05e","05f","05g","05i",
    			"05j","05k","05l","05m","05n","05o","05p","05q","05t","05y","05z","060","061","062","063",
    			"064","065","066","067","068","069","069","06A","06B","06E","06F","06G","06M","06N","06O",
    			"06P","06V","06W","06Y","06a","06b","06d","06e","06h","06i","06j","06k","06m","06n","06o",
    			"06p","06q","06r","06s","06t","06u","06v","06w","06y","070","071","072","073","074","075",
    			"076","077","078","079","07A","07D","07E","07F","07G","07H","07I","07J","07K","07L","07M",
    			"07N","07O","07P","07R","07S","07T","07U","07V","07W","07X","07Y","07Z","07a","07b","07c",
    			"07d","07e","07g","07h","07i","07j","07k","07l","07m","07n","07o","07p","07t","07u","07v",
    			"07w","07x","07y","07z","080","081","082","083","084","085","086","087","08C","08E","08F",
    			"08G","08H","08I","08K","08M","08O","08P","08Q","08R","08U","08V","08W","08X","08a","08c",
    			"08d","08e","08g","08h","08j","08l","08n","08o","08p","08q","08r","08s","08t","08u","08v",
    			"08x","08y","090","091","092","093","094","095","096","097","098","099","09A","09B","09C",
    			"09D","09E","09F","09G","09H","09I","09J","09K","09L","09M","09N","09O","09P","09S","09T",
    			"09U","09V","09W","09X","09Y","09Z","09a","09d","09e","09f","09g","09h","09j","09k","09l",
    			"09m","09s","09t","09v","09w","09x","09z","0A0","0A1","0A2","0A3","0A4","0A5","0A7","0A8",
    			"0A9","0AB","0AD","0AF","0AH","0AI","0AJ","0AK","0AL","0AM","0AN","0AO","0AP","0AQ","0AR",
    			"0AS","0AT","0AU","0AV","0AW","0AX","0AZ","0Aa","0Ab","0Ad","0Af","0Ag","0Ai","0Aj","0Ak",
    			"0Al","0Am","0An","0Ao","0Ap","0Aq","0Ar","0As","0At","0Au","0Av","0Aw","0Ax","0Ay","0Az",
    			"0B0","0B1","0B2","0B3","0B9","0BA","0BB","0BC","0BE","0BF","0BG","0BH","0BI","0BJ","0BL",
    			"0BM","0BR","0BV","0BW","0BX","0BY","0BZ","0Ba","0Bb","0Bc","0Bd","0Be","0Bf","0Bg","0Bi",
    			"0Bk","0Bl","0Bm","0Bn","0Bo","0Bp","0Bs","0Bt","0Bu","0Bv","0Bw","0Bx","0By","0Bz","0C0",
    			"0C2","0C3","0C5","0C6","0C8","0C9","0CA","0CB","0CC","0CD","0CE","0CF","0CG","0CH","0CI",
    			"0CJ","0CK","0CL","0CM","0CO","0CP","0CQ","0CS","0CU","0CW","0CX","0CZ","0Cd","0Ce","0Cg",
    			"0Ch","0Ci","0Cj","0Cl","0Cn","0Co","0Ct","0Cu","0Cv","0Cw","0Cy","0Cz","0D0","0D1","0D2",
    			"0D3","0D4","0D5","0D6","0D7","0D8","0D9","0DA","0DB","0DC","0DD","0DE","0DF","0DG","0DH",
    			"0DL","0DM","0DN","0DO","0DQ","0DR","0DS","0DT","0DU","0DV","0DW","0DX","0DY","0DZ","0Db",
    			"0Dd","0De","0Df","0Dg","0Dh","0Di","0Dj","0Dk","0Dl","0Dm","0Dp","0Dq","0Dr","0Ds","0Dt",
    			"0Du","0Dv","0Dy","0Dz","0E0","0E1","0E2","0E3","0E4","0E5","0E6","0E7","0E8","0E9","0EA",
    			"0EB","0ED","0EE","0EF","0EG","0EH","0EI","0EJ","0EM","0EO","0EP","0EQ","0ER","0EV","0EW",
    			"0EX","0EZ","0Eb","0Ee","0Ef","0Eg","0Eh","0El","0Em","0En","0Ep","0Eq","0Er","0Es","0Et",
    			"0Eu","0Ev","0Ex","0Ey","0Ez","0F0","0F1","0F2","0F3","0F5","0F7","0F8","0F9","0FA","0FB",
    			"0FG","0FH","0FI","0FK","0FM","0FO","0FP","0FQ","0FR","0FT","0FX","0Fa","0Fb","0Fe","0Ff",
    			"0Fg","0Fh","0Fi","0Fj","0Fl","0Fo","0Fp","0Fq","0Fr","0Fs","0Ft","0Fu","0Fv","0Fy","0Fz",
    			"0G1","0G2","0G3","0G4","0G5","0G6","0G7","0G8","0G9","0GC","0GD","0GE","0GH","0GI","0GJ",
    			"0GK","0GL","0GM","0GN","0GO","0GP","0GQ","0GR","0GS","0GT","0GU","0GV","0GW","0GY","0Ga",
    			"0Gc","0Gf","0Gg","0Gi","0Gj","0Gm","0Gn","0Go","0Gp","0Gq","0Gr","0Gt","0Gu","0Gv","0Gw",
    			"0Gx","0Gy","0Gz","0H0","0H1","0H2","0H4","0H6","0H7","0H9","0HC","0HD","0HE","0HF","0HG",
    			"0HI","0HJ","0HK","0HN","0HO","0HP","0HQ","0HR","0HS","0HT","0HU","0HV","0HW","0HX","0HY",
    			"0HZ","0Ha","0Hc","0Hd","0He","0Hf","0Hg","0Hh","0Hi","0Hj","0Hk","0Hl","0Hn","0Ho","0Hp",
    			"0Hq","0Hr","0Hs","0Ht","0Hu","0Hv","0Hw","0Hx","0Hy","0Hz","0I0","0I1","0I2","0I3","0I4",
    			"0I5","0I6","0I7","0I8","0I9","0IA","0IB","0IC","0ID","0IF","0IG","0II","0IO","0IS","0IT",
    			"0IU","0IV","0IW","0IX","0IY","0IZ","0Ia","0Ib","0Id","0Ie","0If","0Ig","0Ih","0Ii","0Ij",
    			"0Ik","0Il","0In","0Io","0Iq","0Ir","0It","0Iu","0Iv","0Iw","0Iy","0Iz","0J0","0J1","0J2",
    			"0J3","0J4","0J5","0J6","0J7","0J8","0J9","0JB","0JD","0JE","0JF","0JJ","0JK","0JL","0JM",
    			"0JO","0JP","0JR","0JS","0JT","0JU","0JV","0JW","0JX","0JY","0JZ","0Ja","0Jb","0Jc","0Jd",
    			"0Je","0Jf","0Jg","0Ji","0Jj","0Jk","0Jl","0Jm","0Jn","0Jo","0Jp","0Jq","0Jr","0Js","0Jt",
    			"0Ju","0Jv","0Jx","0Jy","0Jz","0K0","0K2","0K3","0K4","0K6","0K7","0K9","0KA","0KB","0KD",
    			"0KG","0KK","0KM","0KO","0KP","0KR","0KY","0KZ","0Ka","0Kb","0Kc","0Kd","0Ke","0Kg","0Kh",
    			"0Ki","0Km","0Kn","0Ko","0Kp","0Kq","0Kr","0Ks","0Kt","0Ku","0Kz","0L1","0L2","0L3","0L4",
    			"0L5","0LC","0LD","0LE","0LG","0LH","0LI","0LJ","0LM","0LN","0LO","0LQ","0LV","0Lc","0Ld",
    			"0Le","0Lf","0Lg","0Lh","0Li","0Lj","0Ll","0Lm","0Ln","0Lo","0Lq","0Ls","0Lu","0Lw","0Lx",
    			"0Ly","0M0","0M1","0M2","0M3","0M4","0M5","0M6","0M9","0MA","0MD","0ME","0MF","0MH","0MI",
    			"0MJ","0MK","0ML","0MN","0MO","0MQ","0MR","0MT","0MU","0MV","0MW","0MY","0MZ","0Ma","0Mb",
    			"0Me","0Mf","0Mg","0Mh","0Mi","0Mk","0Mn","0Mo","0Mp","0Mq","0Ms","0Mt","0Mu","0My","0Mz",
    			"0N0","0N1","0N2","0N3","0N4","0N5","0N9","0NB","0NC","0ND","0NE","0NF","0NI","0NK","0NL",
    			"0NM","0NN","0NQ","0NR","0NU","0NV","0NW","0NX","0NZ","0Na","0Nd","0Ne","0Nf","0Ng","0Nh",
    			"0Ni","0Nj","0No","0Np","0Nt","0Nv","0Nw","0O0","0O1","0O6","0O7","0O8","0OB","0OC","0OD",
    			"0OE","0OF","0OG","0OH","0OI","0OK","0OL","0OO","0OP","0OV","0OZ","0Oa","0Ob","0Oe","0Of",
    			"0Oi","0Ol","0Om","0Oq","0Or","0P0","0P1","0P2","0P9","0PB","0PC","0PD","0PF","0PH","0PK",
    			"0PL","0PO","0PP","0PQ","0PS","0PX","0PY","0PZ","0Pa","0Pk","0Pl","0Pm","0Pp","0Pq","0Pr",
    			"0Ps","0Pt","0Pu","0Pv","0Px","0Py","0Pz","0Q0","0Q1","0Q3","0Q5","0Q7","0QD","0QI","0QJ",
    			"0QK","0QL","0QM","0QO","0QP","0QR","0QT","0QU","0QV","0QY","0QZ","0Qb","0Qc","0Qd","0Qg",
    			"0Qi","0Qj","0Qk","0Qn","0Qo","0Qp","0Qt","0Qu","0Qy","0Qz","0R0","0R1","0R2","0R8","0RA",
    			"0RB","0RC","0RD","0RE","0RH","0RJ","0RL","0RM","0RT","0RX","0RY","0RZ","0Rb","0Rd","0Rf",
    			"0Rg","0Rh","0Ri","0Rl","0Rm","0Rn","0Rp","0Rr","0Rs","0Rt","0Ru","0Rv","0Rw","0Rx","0S1",
    			"0S2","0S5","0S6","0SE","0SL","0SM","0SO","0SP","0SR","0ST","0SU","0SV","0Sa","0Se","0Sk",
    			"0Sl","0Sn","0Su","0Sy","0Sz","0T0","0T5","0T6","0T9","0TA","0TB","0TC","0TG","0TH","0TI",
    			"0TJ","0TN","0TO","0TR","0TS","0TT","0TU","0TW","0TY","0TZ","0Tc","0Te","0Ti","0Tj","0Tp",
    			"0Ts","0Tt","0Tv","0Tw","0Tz","0U5","0U6","0UG","0UM","0UN","0UO","0UR","0US","0UT","0UV",
    			"0UW","0UX","0UZ","0Ua","0Ub","0Uc","0Ud","0Ue","0Uq","0Uu","0Uw","0Ux","0Uy","0Uz","0V2",
    			"0V8","0V9","0VA","0VB","0VF","0VG","0VI","0VP","0VQ","0VR","0VS","0VX","0VY","0VZ","0Vi",
    			"0Vk","0Vp","0Vy","0Vz","0W0","0W1","0W2","0W3","0W4","0W5","0W7","0W8","0WA","0WB","0WC",
    			"0WD","0WE","0WF","0WG","0WH","0WI","0WJ","0WK","0WL","0WM","0WO","0WQ","0WR","0Wa","0Wb",
    			"0Wg","0Wh","0Wi","0Wv","0Wx","0Wy","0Wz","0X7","0X8","0XA","0XB","0XC","0XC","0XD","0XE",
    			"0XF","0XG","0XH","0XI","0XR","0XU","0Xe","0Xj","0Xk","0Xs","0Xv","0Xw","0Xy","0Y7","0Y8",
    			"0YD","0YI","0YL","0YN","0YZ","0Ya","0Ym","0Yq","0Ys","0Yu","0Yw","0ZA","0ZB","0ZD","0ZQ",
    			"0ZT","0ZU","0ZW","0ZY","0ZZ","0Zf","0Zg","0Zq","0Zu","0Zx","0Zy","0a0","0a2","0aB","0aC",
    			"0aD","0aJ","0ab","0ad","0ae","0am","0b1","0b3","0bQ","0bR","0bT","0bW","0bZ","0bh","0bi",
    			"0bs","0bt","0bu","0bv","0by","0bz","0c0","0c1","0c6","0cH","0cI","0cK","0cM","0cN","0cS",
    			"0cW","0ca","0ce","0cl","0cn","0cs","0cu","0cv","0cx","0d4","0d8","0dn","0dr","0","0","0",
    			"0eA","0eC","0eH","0eO","0eT","0eU","0eb","0el","0en","0eo","0ep","0eq","0er","0fr","0gv",
    			"0hc","0hd","0ht","0in","0ka","0mt","0ns","0pr","0ps","0rB","0rp","0rs","0sa","0sg","0sn",
    			"0sp","0sr","0t0","0tR","0tS","0ta","0te","0tg","0tn","0tr","0ts","0tu","0up","0ur","0v8",
    			"0wt","0xt","100","101","102","10y","10z","110","111","112","113","11a","128","129","130",
    			"131","149","19i","1AB","1AR","1CA","1CB","1CC","1CF","1CL","1CP","1CS","1DS","1ED","1EF",
    			"1EH","1EP","1ES","1ET","1EV","1Ep","1FS","1GS","1HA","1HB","1HC","1JS","1L7","1L8","1LB",
    			"1MA","1MC","1MP","1Mc","1NR","1OO","1OZ","1QQ","1S1","1SA","1SR","1ST","1Sl","1U7","1WK",
    			"1WL","1XO","1XP","1Xl","1Xm","1Xo","1Xp","1Xt","1Xx","1YZ","1bm","1br","1cb","1ci","1cl",
    			"1cm","1cr","1dc","1de","1do","1dp","1dr","1gh","1gp","1mr","1o1","1pm","1ps","1rp","1rr",
    			"1sa","1te","1ts","1vc","200","201","202","203","204","205","208","20A","26Z","2AS","2CE",
    			"2Ca","2Cx","2ED","2EH","2EP","2ET","2FE","2FF","2LA","2Pd","2SR","2hf","2oN","300","301",
    			"302","303","307","308","309","30A","30C","30D","30F","30L","30Q","30R","30S","30V","30W",
    			"30X","30a","30c","30d","30e","30f","30g","30m","30p","30r","30t","30v","310","31A","31C",
    			"31S","31V","31c","31d","31i","31o","31v","31w","31x","31y","31z","32A","3AM","3CL","3Ca",
    			"3DP","3DS","3Db","3Dp","3FC","3FL","3HP","3J5","3M0","3M1","3M2","3M3","3M4","3M5","3M6",
    			"3MA","3MB","3MC","3MD","3ME","3MF","3MG","3MH","3MI","3MJ","3MK","3MM","3MN","3MO","3MQ",
    			"3MR","3MS","3MT","3MU","3MV","3MW","3Mi","3Ml","3Mt","3N1","3NA","3NC","3NO","3NS","3NT",
    			"3NU","3NV","3NW","3NX","3NY","3NZ","3PP","3PS","3PX","3Pb","3Ph","3Pp","3SP","3SS","3ad",
    			"3cd","3dd","3mK","3pd","3qb","3qe","3qf","3qg","3v1","3vd","400","401","402","403","404",
    			"405","406","407","408","410","412","413","4A0","4F0","4F1","4F2","4F3","4F4","4F5","4M5",
    			"4M6","4NA","4NB","4NC","4ND","4NW","4Wz","4YL","4Zu","4ci","4cl","4co","4dt","4fe","4fp",
    			"4ft","4hy","4ie","4pb","4pv","4sr","4st","4sv","4ve","4ws","4wt","4xs","500","501","550",
    			"551","552","555","557","570","571","572","573","574","5CS","5ML","5Pa","5QL","5Sp","600",
    			"601","602","604","605","606","607","608","62C","6AA","6AB","6AC","6AD","6Au","6EB","6SS",
    			"6TS","6mX","6pS","700","701","707","708","709","70a","70b","70c","70d","710","711","712",
    			"713","714","715","716","729","737","750","751","752","753","754","766","777","7Eh","7Eq",
    			"7Er","7MM","7dl","7ov","7pV","7tf","7tg","7ud","7ue","800","801","802","803","804","805",
    			"806","807","80D","810","811","817","820","822","823","824","825","828","829","82B","886",
    			"888","889","8BM","8dy","8yy","906","907","910","911","912","9BV","9D9","9DV","9EW","9EZ",
    			"9NV","9XN","9XP","9aM","9xb","9yZ","a00","e00","h00","ka0","m00","z00","X00");	
    }
    public void AvoidHardcodingIdRule() {
        setProperty(CODECLIMATE_CATEGORIES, "Security");
        setProperty(CODECLIMATE_REMEDIATION_MULTIPLIER, 100);
        setProperty(CODECLIMATE_BLOCK_HIGHLIGHTING, false);
    }

    @Override
    public Object visit(ASTUserClass node, Object data) {
    	List<ASTLiteralExpression> list2 = node.findDescendantsOfType(ASTLiteralExpression.class);
    	if(list2.isEmpty()) {
    		return data;
    	}
    	if(!list2.isEmpty()) {
    		for(ASTLiteralExpression ele : list2) {
    			Object o = ele.getNode().getLiteral();
    	        if (o instanceof String) {
    	            String literal = (String) o;
    	            if (PATTERN.matcher(literal).matches() && !idList.contains(literal.substring(0, 3))) {
    	                addViolation(data, ele);
    	            }
    	        }
    		}
    	}
    	return data;
    }
    
//    @Override
//    public Object visit(ASTAssignmentExpression node, Object data) {
//        findHardcodedId(node, data);
//        return data;
//    }
//
//    @Override
//    public Object visit(ASTLiteralExpression node, Object data) {
//    	Object o = node.getNode().getLiteral();
//        if (o instanceof String) {
//            String literal = (String) o;
//            if (PATTERN.matcher(literal).matches()) {
//                addViolation(data, node);
//            }
//        }
//        return data;
//    }
    
    private void findHardcodedId(AbstractApexNode<?> node, Object data) {
        ASTLiteralExpression literalNode = node.getFirstChildOfType(ASTLiteralExpression.class);

        if (literalNode != null) {
            Object o = literalNode.getNode().getLiteral();
            if (o instanceof String) {
                String literal = (String) o;
                if (PATTERN.matcher(literal).matches() && !idList.contains(literal.substring(0, 3))) {
                    addViolation(data, literalNode);
                }
            }
        }
    }
}