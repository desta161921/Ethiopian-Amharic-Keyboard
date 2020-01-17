package com.ethiopian.amharic.professional;

import java.util.*;

final class AmharicModel
{
    private static HashMap<String, Integer> defaultKeyMap;
    private static AmharicModel instance;
    private static HashMap<String, Integer> rdcKeyMap;
    
    static {
        AmharicModel.instance = new AmharicModel();
        AmharicModel.defaultKeyMap = null;
        AmharicModel.rdcKeyMap = null;
    }
    
    private char caseInterchangeForDfltKbd(final char c) {
        switch (c) {
            default: {
                return c;
            }
            case 'B':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'I':
            case 'J':
            case 'L':
            case 'M':
            case 'R':
            case 'U':
            case 'V':
            case 'X':
            case 'Y': {
                return (char)(c + ' ');
            }
        }
    }
    
    private char caseInterchangeForRDCKbd(final char c) {
        switch (c) {
            default: {
                return c;
            }
            case 'A':
            case 'B':
            case 'D':
            case 'F':
            case 'G':
            case 'I':
            case 'J':
            case 'L':
            case 'M':
            case 'O':
            case 'R':
            case 'S':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y': {
                return (char)(c + ' ');
            }
        }
    }
    
    public static AmharicModel getInstance(final byte b) {
        if (b == 1) {
            if (AmharicModel.defaultKeyMap == null) {
                AmharicModel.instance.initDefaultKdbMap();
            }
        }
        else if (b == 2 && AmharicModel.rdcKeyMap == null) {
            AmharicModel.instance.initRDCKbdMap();
        }
        return AmharicModel.instance;
    }
    
    private void initDefaultKdbMap() {
        System.out.println("Default Init.");
        (AmharicModel.defaultKeyMap = new HashMap<String, Integer>(410)).put("he", 4608);
        AmharicModel.defaultKeyMap.put("hu", 4609);
        AmharicModel.defaultKeyMap.put("hi", 4610);
        AmharicModel.defaultKeyMap.put("ha", 4611);
        AmharicModel.defaultKeyMap.put("hie", 4612);
        AmharicModel.defaultKeyMap.put("h", 4613);
        AmharicModel.defaultKeyMap.put("ho", 4614);
        AmharicModel.defaultKeyMap.put("hoa", 4615);
        AmharicModel.defaultKeyMap.put("le", 4616);
        AmharicModel.defaultKeyMap.put("lu", 4617);
        AmharicModel.defaultKeyMap.put("li", 4618);
        AmharicModel.defaultKeyMap.put("la", 4619);
        AmharicModel.defaultKeyMap.put("lie", 4620);
        AmharicModel.defaultKeyMap.put("l", 4621);
        AmharicModel.defaultKeyMap.put("lo", 4622);
        AmharicModel.defaultKeyMap.put("lua", 4623);
        AmharicModel.defaultKeyMap.put("He", 4624);
        AmharicModel.defaultKeyMap.put("Hu", 4625);
        AmharicModel.defaultKeyMap.put("Hi", 4626);
        AmharicModel.defaultKeyMap.put("Ha", 4627);
        AmharicModel.defaultKeyMap.put("Hie", 4628);
        AmharicModel.defaultKeyMap.put("H", 4629);
        AmharicModel.defaultKeyMap.put("Ho", 4630);
        AmharicModel.defaultKeyMap.put("Hua", 4631);
        AmharicModel.defaultKeyMap.put("me", 4632);
        AmharicModel.defaultKeyMap.put("mu", 4633);
        AmharicModel.defaultKeyMap.put("mi", 4634);
        AmharicModel.defaultKeyMap.put("ma", 4635);
        AmharicModel.defaultKeyMap.put("mie", 4636);
        AmharicModel.defaultKeyMap.put("m", 4637);
        AmharicModel.defaultKeyMap.put("mo", 4638);
        AmharicModel.defaultKeyMap.put("mua", 4639);
        AmharicModel.defaultKeyMap.put("Se", 4640);
        AmharicModel.defaultKeyMap.put("Su", 4641);
        AmharicModel.defaultKeyMap.put("Si", 4642);
        AmharicModel.defaultKeyMap.put("Sa", 4643);
        AmharicModel.defaultKeyMap.put("Sie", 4644);
        AmharicModel.defaultKeyMap.put("S", 4645);
        AmharicModel.defaultKeyMap.put("So", 4646);
        AmharicModel.defaultKeyMap.put("Sua", 4647);
        AmharicModel.defaultKeyMap.put("re", 4648);
        AmharicModel.defaultKeyMap.put("ru", 4649);
        AmharicModel.defaultKeyMap.put("ri", 4650);
        AmharicModel.defaultKeyMap.put("ra", 4651);
        AmharicModel.defaultKeyMap.put("rie", 4652);
        AmharicModel.defaultKeyMap.put("r", 4653);
        AmharicModel.defaultKeyMap.put("ro", 4654);
        AmharicModel.defaultKeyMap.put("rua", 4655);
        AmharicModel.defaultKeyMap.put("se", 4656);
        AmharicModel.defaultKeyMap.put("su", 4657);
        AmharicModel.defaultKeyMap.put("si", 4658);
        AmharicModel.defaultKeyMap.put("sa", 4659);
        AmharicModel.defaultKeyMap.put("sie", 4660);
        AmharicModel.defaultKeyMap.put("s", 4661);
        AmharicModel.defaultKeyMap.put("so", 4662);
        AmharicModel.defaultKeyMap.put("sua", 4663);
        AmharicModel.defaultKeyMap.put("she", 4664);
        AmharicModel.defaultKeyMap.put("shu", 4665);
        AmharicModel.defaultKeyMap.put("shi", 4666);
        AmharicModel.defaultKeyMap.put("sha", 4667);
        AmharicModel.defaultKeyMap.put("shie", 4668);
        AmharicModel.defaultKeyMap.put("sh", 4669);
        AmharicModel.defaultKeyMap.put("sho", 4670);
        AmharicModel.defaultKeyMap.put("shua", 4671);
        AmharicModel.defaultKeyMap.put("qe", 4672);
        AmharicModel.defaultKeyMap.put("qu", 4673);
        AmharicModel.defaultKeyMap.put("qi", 4674);
        AmharicModel.defaultKeyMap.put("qa", 4675);
        AmharicModel.defaultKeyMap.put("qie", 4676);
        AmharicModel.defaultKeyMap.put("q", 4677);
        AmharicModel.defaultKeyMap.put("qo", 4678);
        AmharicModel.defaultKeyMap.put("qoa", 4679);
        AmharicModel.defaultKeyMap.put("que", 4680);
        AmharicModel.defaultKeyMap.put("qui", 4682);
        AmharicModel.defaultKeyMap.put("qua", 4683);
        AmharicModel.defaultKeyMap.put("quie", 4684);
        AmharicModel.defaultKeyMap.put("qw", 4685);
        AmharicModel.defaultKeyMap.put("Qe", 4688);
        AmharicModel.defaultKeyMap.put("Qu", 4689);
        AmharicModel.defaultKeyMap.put("Qi", 4690);
        AmharicModel.defaultKeyMap.put("Qa", 4691);
        AmharicModel.defaultKeyMap.put("Qie", 4692);
        AmharicModel.defaultKeyMap.put("Q", 4693);
        AmharicModel.defaultKeyMap.put("Qo", 4694);
        AmharicModel.defaultKeyMap.put("Que", 4696);
        AmharicModel.defaultKeyMap.put("Qui", 4698);
        AmharicModel.defaultKeyMap.put("Qua", 4699);
        AmharicModel.defaultKeyMap.put("Quie", 4700);
        AmharicModel.defaultKeyMap.put("QW", 4701);
        AmharicModel.defaultKeyMap.put("be", 4704);
        AmharicModel.defaultKeyMap.put("bu", 4705);
        AmharicModel.defaultKeyMap.put("bi", 4706);
        AmharicModel.defaultKeyMap.put("ba", 4707);
        AmharicModel.defaultKeyMap.put("bie", 4708);
        AmharicModel.defaultKeyMap.put("b", 4709);
        AmharicModel.defaultKeyMap.put("bo", 4710);
        AmharicModel.defaultKeyMap.put("bua", 4711);
        AmharicModel.defaultKeyMap.put("ve", 4712);
        AmharicModel.defaultKeyMap.put("vu", 4713);
        AmharicModel.defaultKeyMap.put("vi", 4714);
        AmharicModel.defaultKeyMap.put("va", 4715);
        AmharicModel.defaultKeyMap.put("vie", 4716);
        AmharicModel.defaultKeyMap.put("v", 4717);
        AmharicModel.defaultKeyMap.put("vo", 4718);
        AmharicModel.defaultKeyMap.put("vua", 4719);
        AmharicModel.defaultKeyMap.put("te", 4720);
        AmharicModel.defaultKeyMap.put("tu", 4721);
        AmharicModel.defaultKeyMap.put("ti", 4722);
        AmharicModel.defaultKeyMap.put("ta", 4723);
        AmharicModel.defaultKeyMap.put("tie", 4724);
        AmharicModel.defaultKeyMap.put("t", 4725);
        AmharicModel.defaultKeyMap.put("to", 4726);
        AmharicModel.defaultKeyMap.put("tua", 4727);
        AmharicModel.defaultKeyMap.put("ce", 4728);
        AmharicModel.defaultKeyMap.put("cu", 4729);
        AmharicModel.defaultKeyMap.put("ci", 4730);
        AmharicModel.defaultKeyMap.put("ca", 4731);
        AmharicModel.defaultKeyMap.put("cie", 4732);
        AmharicModel.defaultKeyMap.put("c", 4733);
        AmharicModel.defaultKeyMap.put("co", 4734);
        AmharicModel.defaultKeyMap.put("cua", 4735);
        AmharicModel.defaultKeyMap.put("xhe", 4736);
        AmharicModel.defaultKeyMap.put("xhu", 4737);
        AmharicModel.defaultKeyMap.put("xhi", 4738);
        AmharicModel.defaultKeyMap.put("xha", 4739);
        AmharicModel.defaultKeyMap.put("xhie", 4740);
        AmharicModel.defaultKeyMap.put("xh", 4741);
        AmharicModel.defaultKeyMap.put("xho", 4742);
        AmharicModel.defaultKeyMap.put("xhua", 4743);
        AmharicModel.defaultKeyMap.put("hue", 4744);
        AmharicModel.defaultKeyMap.put("hui", 4746);
        AmharicModel.defaultKeyMap.put("hua", 4747);
        AmharicModel.defaultKeyMap.put("huie", 4748);
        AmharicModel.defaultKeyMap.put("hw", 4749);
        AmharicModel.defaultKeyMap.put("ne", 4752);
        AmharicModel.defaultKeyMap.put("nu", 4753);
        AmharicModel.defaultKeyMap.put("ni", 4754);
        AmharicModel.defaultKeyMap.put("na", 4755);
        AmharicModel.defaultKeyMap.put("nie", 4756);
        AmharicModel.defaultKeyMap.put("n", 4757);
        AmharicModel.defaultKeyMap.put("no", 4758);
        AmharicModel.defaultKeyMap.put("nua", 4759);
        AmharicModel.defaultKeyMap.put("Ne", 4760);
        AmharicModel.defaultKeyMap.put("Nu", 4761);
        AmharicModel.defaultKeyMap.put("Ni", 4762);
        AmharicModel.defaultKeyMap.put("Na", 4763);
        AmharicModel.defaultKeyMap.put("Nie", 4764);
        AmharicModel.defaultKeyMap.put("N", 4765);
        AmharicModel.defaultKeyMap.put("No", 4766);
        AmharicModel.defaultKeyMap.put("Nua", 4767);
        AmharicModel.defaultKeyMap.put("e", 4768);
        AmharicModel.defaultKeyMap.put("u", 4769);
        AmharicModel.defaultKeyMap.put("i", 4770);
        AmharicModel.defaultKeyMap.put("a", 4771);
        AmharicModel.defaultKeyMap.put("ie", 4772);
        AmharicModel.defaultKeyMap.put("A", 4773);
        AmharicModel.defaultKeyMap.put("o", 4774);
        AmharicModel.defaultKeyMap.put("ua", 4775);
        AmharicModel.defaultKeyMap.put("Ae", 4768);
        AmharicModel.defaultKeyMap.put("Au", 4769);
        AmharicModel.defaultKeyMap.put("Ai", 4770);
        AmharicModel.defaultKeyMap.put("Aa", 4771);
        AmharicModel.defaultKeyMap.put("Aie", 4772);
        AmharicModel.defaultKeyMap.put("Ao", 4774);
        AmharicModel.defaultKeyMap.put("Aua", 4775);
        AmharicModel.defaultKeyMap.put("ke", 4776);
        AmharicModel.defaultKeyMap.put("ku", 4777);
        AmharicModel.defaultKeyMap.put("ki", 4778);
        AmharicModel.defaultKeyMap.put("ka", 4779);
        AmharicModel.defaultKeyMap.put("kie", 4780);
        AmharicModel.defaultKeyMap.put("k", 4781);
        AmharicModel.defaultKeyMap.put("ko", 4782);
        AmharicModel.defaultKeyMap.put("koa", 4783);
        AmharicModel.defaultKeyMap.put("kue", 4784);
        AmharicModel.defaultKeyMap.put("kui", 4786);
        AmharicModel.defaultKeyMap.put("kua", 4787);
        AmharicModel.defaultKeyMap.put("kuie", 4788);
        AmharicModel.defaultKeyMap.put("kuw", 4789);
        AmharicModel.defaultKeyMap.put("Ke", 4792);
        AmharicModel.defaultKeyMap.put("Ku", 4793);
        AmharicModel.defaultKeyMap.put("Ki", 4794);
        AmharicModel.defaultKeyMap.put("Ka", 4795);
        AmharicModel.defaultKeyMap.put("Kie", 4796);
        AmharicModel.defaultKeyMap.put("K", 4797);
        AmharicModel.defaultKeyMap.put("Ko", 4798);
        AmharicModel.defaultKeyMap.put("Kue", 4800);
        AmharicModel.defaultKeyMap.put("Kui", 4802);
        AmharicModel.defaultKeyMap.put("Kua", 4803);
        AmharicModel.defaultKeyMap.put("Kuie", 4804);
        AmharicModel.defaultKeyMap.put("KW", 4805);
        AmharicModel.defaultKeyMap.put("we", 4808);
        AmharicModel.defaultKeyMap.put("wu", 4809);
        AmharicModel.defaultKeyMap.put("wi", 4810);
        AmharicModel.defaultKeyMap.put("wa", 4811);
        AmharicModel.defaultKeyMap.put("wie", 4812);
        AmharicModel.defaultKeyMap.put("w", 4813);
        AmharicModel.defaultKeyMap.put("wo", 4814);
        AmharicModel.defaultKeyMap.put("woa", 4815);
        AmharicModel.defaultKeyMap.put("We", 4808);
        AmharicModel.defaultKeyMap.put("Wu", 4809);
        AmharicModel.defaultKeyMap.put("Wi", 4810);
        AmharicModel.defaultKeyMap.put("Wa", 4811);
        AmharicModel.defaultKeyMap.put("Wie", 4812);
        AmharicModel.defaultKeyMap.put("W", 4813);
        AmharicModel.defaultKeyMap.put("Wo", 4814);
        AmharicModel.defaultKeyMap.put("Woa", 4815);
        AmharicModel.defaultKeyMap.put("Oe", 4816);
        AmharicModel.defaultKeyMap.put("Ou", 4817);
        AmharicModel.defaultKeyMap.put("Oi", 4818);
        AmharicModel.defaultKeyMap.put("Oa", 4819);
        AmharicModel.defaultKeyMap.put("Oie", 4820);
        AmharicModel.defaultKeyMap.put("O", 4821);
        AmharicModel.defaultKeyMap.put("Oo", 4822);
        AmharicModel.defaultKeyMap.put("ze", 4824);
        AmharicModel.defaultKeyMap.put("zu", 4825);
        AmharicModel.defaultKeyMap.put("zi", 4826);
        AmharicModel.defaultKeyMap.put("za", 4827);
        AmharicModel.defaultKeyMap.put("zie", 4828);
        AmharicModel.defaultKeyMap.put("z", 4829);
        AmharicModel.defaultKeyMap.put("zo", 4830);
        AmharicModel.defaultKeyMap.put("zua", 4831);
        AmharicModel.defaultKeyMap.put("Ze", 4832);
        AmharicModel.defaultKeyMap.put("Zu", 4833);
        AmharicModel.defaultKeyMap.put("Zi", 4834);
        AmharicModel.defaultKeyMap.put("Za", 4835);
        AmharicModel.defaultKeyMap.put("Zie", 4836);
        AmharicModel.defaultKeyMap.put("Z", 4837);
        AmharicModel.defaultKeyMap.put("Zo", 4838);
        AmharicModel.defaultKeyMap.put("Zua", 4839);
        AmharicModel.defaultKeyMap.put("ye", 4840);
        AmharicModel.defaultKeyMap.put("yu", 4841);
        AmharicModel.defaultKeyMap.put("yi", 4842);
        AmharicModel.defaultKeyMap.put("ya", 4843);
        AmharicModel.defaultKeyMap.put("yie", 4844);
        AmharicModel.defaultKeyMap.put("y", 4845);
        AmharicModel.defaultKeyMap.put("yo", 4846);
        AmharicModel.defaultKeyMap.put("yoa", 4847);
        AmharicModel.defaultKeyMap.put("de", 4848);
        AmharicModel.defaultKeyMap.put("du", 4849);
        AmharicModel.defaultKeyMap.put("di", 4850);
        AmharicModel.defaultKeyMap.put("da", 4851);
        AmharicModel.defaultKeyMap.put("die", 4852);
        AmharicModel.defaultKeyMap.put("d", 4853);
        AmharicModel.defaultKeyMap.put("do", 4854);
        AmharicModel.defaultKeyMap.put("dua", 4855);
        AmharicModel.defaultKeyMap.put("Pe", 4856);
        AmharicModel.defaultKeyMap.put("Pu", 4857);
        AmharicModel.defaultKeyMap.put("Pi", 4858);
        AmharicModel.defaultKeyMap.put("Pa", 4859);
        AmharicModel.defaultKeyMap.put("Pie", 4860);
        AmharicModel.defaultKeyMap.put("P", 4861);
        AmharicModel.defaultKeyMap.put("Po", 4862);
        AmharicModel.defaultKeyMap.put("Pua", 4863);
        AmharicModel.defaultKeyMap.put("je", 4864);
        AmharicModel.defaultKeyMap.put("ju", 4865);
        AmharicModel.defaultKeyMap.put("ji", 4866);
        AmharicModel.defaultKeyMap.put("ja", 4867);
        AmharicModel.defaultKeyMap.put("jie", 4868);
        AmharicModel.defaultKeyMap.put("j", 4869);
        AmharicModel.defaultKeyMap.put("jo", 4870);
        AmharicModel.defaultKeyMap.put("jua", 4871);
        AmharicModel.defaultKeyMap.put("ge", 4872);
        AmharicModel.defaultKeyMap.put("gu", 4873);
        AmharicModel.defaultKeyMap.put("gi", 4874);
        AmharicModel.defaultKeyMap.put("ga", 4875);
        AmharicModel.defaultKeyMap.put("gie", 4876);
        AmharicModel.defaultKeyMap.put("g", 4877);
        AmharicModel.defaultKeyMap.put("go", 4878);
        AmharicModel.defaultKeyMap.put("goa", 4879);
        AmharicModel.defaultKeyMap.put("gue", 4880);
        AmharicModel.defaultKeyMap.put("gui", 4882);
        AmharicModel.defaultKeyMap.put("gua", 4883);
        AmharicModel.defaultKeyMap.put("guie", 4884);
        AmharicModel.defaultKeyMap.put("gw", 4885);
        AmharicModel.defaultKeyMap.put("g2e", 4888);
        AmharicModel.defaultKeyMap.put("g2u", 4889);
        AmharicModel.defaultKeyMap.put("g2i", 4890);
        AmharicModel.defaultKeyMap.put("g2a", 4891);
        AmharicModel.defaultKeyMap.put("g2ie", 4892);
        AmharicModel.defaultKeyMap.put("g2", 4893);
        AmharicModel.defaultKeyMap.put("g2o", 4894);
        AmharicModel.defaultKeyMap.put("g2ua", 4895);
        AmharicModel.defaultKeyMap.put("Te", 4896);
        AmharicModel.defaultKeyMap.put("Tu", 4897);
        AmharicModel.defaultKeyMap.put("Ti", 4898);
        AmharicModel.defaultKeyMap.put("Ta", 4899);
        AmharicModel.defaultKeyMap.put("Tie", 4900);
        AmharicModel.defaultKeyMap.put("T", 4901);
        AmharicModel.defaultKeyMap.put("To", 4902);
        AmharicModel.defaultKeyMap.put("Tua", 4903);
        AmharicModel.defaultKeyMap.put("Ce", 4904);
        AmharicModel.defaultKeyMap.put("Cu", 4905);
        AmharicModel.defaultKeyMap.put("Ci", 4906);
        AmharicModel.defaultKeyMap.put("Ca", 4907);
        AmharicModel.defaultKeyMap.put("Cie", 4908);
        AmharicModel.defaultKeyMap.put("C", 4909);
        AmharicModel.defaultKeyMap.put("Co", 4910);
        AmharicModel.defaultKeyMap.put("Cua", 4911);
        AmharicModel.defaultKeyMap.put("Pe", 4912);
        AmharicModel.defaultKeyMap.put("Pu", 4913);
        AmharicModel.defaultKeyMap.put("Pi", 4914);
        AmharicModel.defaultKeyMap.put("Pa", 4915);
        AmharicModel.defaultKeyMap.put("Pie", 4916);
        AmharicModel.defaultKeyMap.put("P", 4917);
        AmharicModel.defaultKeyMap.put("Po", 4918);
        AmharicModel.defaultKeyMap.put("Pua", 4919);
        AmharicModel.defaultKeyMap.put("tze", 4920);
        AmharicModel.defaultKeyMap.put("tzu", 4921);
        AmharicModel.defaultKeyMap.put("tzi", 4922);
        AmharicModel.defaultKeyMap.put("tza", 4923);
        AmharicModel.defaultKeyMap.put("tzie", 4924);
        AmharicModel.defaultKeyMap.put("tz", 4925);
        AmharicModel.defaultKeyMap.put("tzo", 4926);
        AmharicModel.defaultKeyMap.put("tzua", 4927);
        AmharicModel.defaultKeyMap.put("tse", 4928);
        AmharicModel.defaultKeyMap.put("tsu", 4929);
        AmharicModel.defaultKeyMap.put("tsi", 4930);
        AmharicModel.defaultKeyMap.put("tsa", 4931);
        AmharicModel.defaultKeyMap.put("tsie", 4932);
        AmharicModel.defaultKeyMap.put("ts", 4933);
        AmharicModel.defaultKeyMap.put("tso", 4934);
        AmharicModel.defaultKeyMap.put("tsua", 4935);
        AmharicModel.defaultKeyMap.put("fe", 4936);
        AmharicModel.defaultKeyMap.put("fu", 4937);
        AmharicModel.defaultKeyMap.put("fi", 4938);
        AmharicModel.defaultKeyMap.put("fa", 4939);
        AmharicModel.defaultKeyMap.put("fie", 4940);
        AmharicModel.defaultKeyMap.put("f", 4941);
        AmharicModel.defaultKeyMap.put("fo", 4942);
        AmharicModel.defaultKeyMap.put("fua", 4943);
        AmharicModel.defaultKeyMap.put("pe", 4944);
        AmharicModel.defaultKeyMap.put("pu", 4945);
        AmharicModel.defaultKeyMap.put("pi", 4946);
        AmharicModel.defaultKeyMap.put("pa", 4947);
        AmharicModel.defaultKeyMap.put("pie", 4948);
        AmharicModel.defaultKeyMap.put("p", 4949);
        AmharicModel.defaultKeyMap.put("po", 4950);
        AmharicModel.defaultKeyMap.put("pua", 4951);
        AmharicModel.defaultKeyMap.put("ri2", 4952);
        AmharicModel.defaultKeyMap.put("mi2", 4953);
        AmharicModel.defaultKeyMap.put("fi2", 4954);
        AmharicModel.defaultKeyMap.put(";", 4961);
        AmharicModel.defaultKeyMap.put(".", 4962);
        AmharicModel.defaultKeyMap.put(";;", 4962);
        AmharicModel.defaultKeyMap.put(",", 4963);
        AmharicModel.defaultKeyMap.put("::", 4964);
        AmharicModel.defaultKeyMap.put(":", 4965);
        AmharicModel.defaultKeyMap.put(";-", 4966);
        AmharicModel.defaultKeyMap.put("??", 4967);
        AmharicModel.defaultKeyMap.put("...", 4968);
        AmharicModel.defaultKeyMap.put("..", 46);
        AmharicModel.defaultKeyMap.put(",,", 44);
        AmharicModel.defaultKeyMap.put("1", 4969);
        AmharicModel.defaultKeyMap.put("2", 4970);
        AmharicModel.defaultKeyMap.put("3", 4971);
        AmharicModel.defaultKeyMap.put("4", 4972);
        AmharicModel.defaultKeyMap.put("5", 4973);
        AmharicModel.defaultKeyMap.put("6", 4974);
        AmharicModel.defaultKeyMap.put("7", 4975);
        AmharicModel.defaultKeyMap.put("8", 4976);
        AmharicModel.defaultKeyMap.put("9", 4977);
        AmharicModel.defaultKeyMap.put("10", 4978);
        AmharicModel.defaultKeyMap.put("20", 4979);
        AmharicModel.defaultKeyMap.put("30", 4980);
        AmharicModel.defaultKeyMap.put("40", 4981);
        AmharicModel.defaultKeyMap.put("50", 4982);
        AmharicModel.defaultKeyMap.put("60", 4983);
        AmharicModel.defaultKeyMap.put("70", 4984);
        AmharicModel.defaultKeyMap.put("80", 4985);
        AmharicModel.defaultKeyMap.put("90", 4986);
        AmharicModel.defaultKeyMap.put("100", 4987);
        AmharicModel.defaultKeyMap.put("10000", 4988);
    }
    
    private void initRDCKbdMap() {
        System.out.println("RDC Init.");
        (AmharicModel.rdcKeyMap = new HashMap<String, Integer>(360)).put("he", 4608);
        AmharicModel.rdcKeyMap.put("hu", 4609);
        AmharicModel.rdcKeyMap.put("hi", 4610);
        AmharicModel.rdcKeyMap.put("ha", 4611);
        AmharicModel.rdcKeyMap.put("hie", 4612);
        AmharicModel.rdcKeyMap.put("h", 4613);
        AmharicModel.rdcKeyMap.put("ho", 4614);
        AmharicModel.rdcKeyMap.put("le", 4616);
        AmharicModel.rdcKeyMap.put("lu", 4617);
        AmharicModel.rdcKeyMap.put("li", 4618);
        AmharicModel.rdcKeyMap.put("la", 4619);
        AmharicModel.rdcKeyMap.put("lie", 4620);
        AmharicModel.rdcKeyMap.put("l", 4621);
        AmharicModel.rdcKeyMap.put("lo", 4622);
        AmharicModel.rdcKeyMap.put("He", 4624);
        AmharicModel.rdcKeyMap.put("Hu", 4625);
        AmharicModel.rdcKeyMap.put("Hi", 4626);
        AmharicModel.rdcKeyMap.put("Ha", 4627);
        AmharicModel.rdcKeyMap.put("Hie", 4628);
        AmharicModel.rdcKeyMap.put("H", 4629);
        AmharicModel.rdcKeyMap.put("Ho", 4630);
        AmharicModel.rdcKeyMap.put("me", 4632);
        AmharicModel.rdcKeyMap.put("mu", 4633);
        AmharicModel.rdcKeyMap.put("mi", 4634);
        AmharicModel.rdcKeyMap.put("ma", 4635);
        AmharicModel.rdcKeyMap.put("mie", 4636);
        AmharicModel.rdcKeyMap.put("m", 4637);
        AmharicModel.rdcKeyMap.put("mo", 4638);
        AmharicModel.rdcKeyMap.put("re", 4648);
        AmharicModel.rdcKeyMap.put("ru", 4649);
        AmharicModel.rdcKeyMap.put("ri", 4650);
        AmharicModel.rdcKeyMap.put("ra", 4651);
        AmharicModel.rdcKeyMap.put("rie", 4652);
        AmharicModel.rdcKeyMap.put("r", 4653);
        AmharicModel.rdcKeyMap.put("ro", 4654);
        AmharicModel.rdcKeyMap.put("se", 4656);
        AmharicModel.rdcKeyMap.put("su", 4657);
        AmharicModel.rdcKeyMap.put("si", 4658);
        AmharicModel.rdcKeyMap.put("sa", 4659);
        AmharicModel.rdcKeyMap.put("sie", 4660);
        AmharicModel.rdcKeyMap.put("s", 4661);
        AmharicModel.rdcKeyMap.put("so", 4662);
        AmharicModel.rdcKeyMap.put("she", 4664);
        AmharicModel.rdcKeyMap.put("shu", 4665);
        AmharicModel.rdcKeyMap.put("shi", 4666);
        AmharicModel.rdcKeyMap.put("sha", 4667);
        AmharicModel.rdcKeyMap.put("shie", 4668);
        AmharicModel.rdcKeyMap.put("sh", 4669);
        AmharicModel.rdcKeyMap.put("sho", 4670);
        AmharicModel.rdcKeyMap.put("qe", 4672);
        AmharicModel.rdcKeyMap.put("qu", 4673);
        AmharicModel.rdcKeyMap.put("qi", 4674);
        AmharicModel.rdcKeyMap.put("qa", 4675);
        AmharicModel.rdcKeyMap.put("qie", 4676);
        AmharicModel.rdcKeyMap.put("q", 4677);
        AmharicModel.rdcKeyMap.put("qo", 4678);
        AmharicModel.rdcKeyMap.put("qwe", 4680);
        AmharicModel.rdcKeyMap.put("qwi", 4682);
        AmharicModel.rdcKeyMap.put("qwa", 4683);
        AmharicModel.rdcKeyMap.put("qwie", 4684);
        AmharicModel.rdcKeyMap.put("qw", 4685);
        AmharicModel.rdcKeyMap.put("Qe", 4688);
        AmharicModel.rdcKeyMap.put("Qu", 4689);
        AmharicModel.rdcKeyMap.put("Qi", 4690);
        AmharicModel.rdcKeyMap.put("Qa", 4691);
        AmharicModel.rdcKeyMap.put("Qie", 4692);
        AmharicModel.rdcKeyMap.put("Q", 4693);
        AmharicModel.rdcKeyMap.put("Qo", 4694);
        AmharicModel.rdcKeyMap.put("Qwe", 4696);
        AmharicModel.rdcKeyMap.put("Qwi", 4698);
        AmharicModel.rdcKeyMap.put("Qwa", 4699);
        AmharicModel.rdcKeyMap.put("Qwie", 4700);
        AmharicModel.rdcKeyMap.put("Qw", 4701);
        AmharicModel.rdcKeyMap.put("be", 4704);
        AmharicModel.rdcKeyMap.put("bu", 4705);
        AmharicModel.rdcKeyMap.put("bi", 4706);
        AmharicModel.rdcKeyMap.put("ba", 4707);
        AmharicModel.rdcKeyMap.put("bie", 4708);
        AmharicModel.rdcKeyMap.put("b", 4709);
        AmharicModel.rdcKeyMap.put("bo", 4710);
        AmharicModel.rdcKeyMap.put("ve", 4712);
        AmharicModel.rdcKeyMap.put("vu", 4713);
        AmharicModel.rdcKeyMap.put("vi", 4714);
        AmharicModel.rdcKeyMap.put("va", 4715);
        AmharicModel.rdcKeyMap.put("vie", 4716);
        AmharicModel.rdcKeyMap.put("v", 4717);
        AmharicModel.rdcKeyMap.put("vo", 4718);
        AmharicModel.rdcKeyMap.put("te", 4720);
        AmharicModel.rdcKeyMap.put("tu", 4721);
        AmharicModel.rdcKeyMap.put("ti", 4722);
        AmharicModel.rdcKeyMap.put("ta", 4723);
        AmharicModel.rdcKeyMap.put("tie", 4724);
        AmharicModel.rdcKeyMap.put("t", 4725);
        AmharicModel.rdcKeyMap.put("to", 4726);
        AmharicModel.rdcKeyMap.put("che", 4728);
        AmharicModel.rdcKeyMap.put("chu", 4729);
        AmharicModel.rdcKeyMap.put("chi", 4730);
        AmharicModel.rdcKeyMap.put("cha", 4731);
        AmharicModel.rdcKeyMap.put("chie", 4732);
        AmharicModel.rdcKeyMap.put("ch", 4733);
        AmharicModel.rdcKeyMap.put("cho", 4734);
        AmharicModel.rdcKeyMap.put("ce", 4728);
        AmharicModel.rdcKeyMap.put("cu", 4729);
        AmharicModel.rdcKeyMap.put("ci", 4730);
        AmharicModel.rdcKeyMap.put("ca", 4731);
        AmharicModel.rdcKeyMap.put("cie", 4732);
        AmharicModel.rdcKeyMap.put("c", 4733);
        AmharicModel.rdcKeyMap.put("co", 4734);
        AmharicModel.rdcKeyMap.put("hue", 4744);
        AmharicModel.rdcKeyMap.put("hui", 4746);
        AmharicModel.rdcKeyMap.put("hua", 4747);
        AmharicModel.rdcKeyMap.put("huie", 4748);
        AmharicModel.rdcKeyMap.put("hw", 4749);
        AmharicModel.rdcKeyMap.put("ne", 4752);
        AmharicModel.rdcKeyMap.put("nu", 4753);
        AmharicModel.rdcKeyMap.put("ni", 4754);
        AmharicModel.rdcKeyMap.put("na", 4755);
        AmharicModel.rdcKeyMap.put("nie", 4756);
        AmharicModel.rdcKeyMap.put("n", 4757);
        AmharicModel.rdcKeyMap.put("no", 4758);
        AmharicModel.rdcKeyMap.put("nwa", 4759);
        AmharicModel.rdcKeyMap.put("Ne", 4760);
        AmharicModel.rdcKeyMap.put("Nu", 4761);
        AmharicModel.rdcKeyMap.put("Ni", 4762);
        AmharicModel.rdcKeyMap.put("Na", 4763);
        AmharicModel.rdcKeyMap.put("Nie", 4764);
        AmharicModel.rdcKeyMap.put("N", 4765);
        AmharicModel.rdcKeyMap.put("No", 4766);
        AmharicModel.rdcKeyMap.put("Nwa", 4767);
        AmharicModel.rdcKeyMap.put("ae", 4768);
        AmharicModel.rdcKeyMap.put("u", 4769);
        AmharicModel.rdcKeyMap.put("i", 4770);
        AmharicModel.rdcKeyMap.put("a", 4771);
        AmharicModel.rdcKeyMap.put("ie", 4772);
        AmharicModel.rdcKeyMap.put("e", 4773);
        AmharicModel.rdcKeyMap.put("o", 4774);
        AmharicModel.rdcKeyMap.put("ke", 4776);
        AmharicModel.rdcKeyMap.put("ku", 4777);
        AmharicModel.rdcKeyMap.put("ki", 4778);
        AmharicModel.rdcKeyMap.put("ka", 4779);
        AmharicModel.rdcKeyMap.put("kie", 4780);
        AmharicModel.rdcKeyMap.put("k", 4781);
        AmharicModel.rdcKeyMap.put("ko", 4782);
        AmharicModel.rdcKeyMap.put("kwe", 4784);
        AmharicModel.rdcKeyMap.put("kwi", 4786);
        AmharicModel.rdcKeyMap.put("kwa", 4787);
        AmharicModel.rdcKeyMap.put("kwie", 4788);
        AmharicModel.rdcKeyMap.put("kw", 4789);
        AmharicModel.rdcKeyMap.put("Ke", 4792);
        AmharicModel.rdcKeyMap.put("Ku", 4793);
        AmharicModel.rdcKeyMap.put("Ki", 4794);
        AmharicModel.rdcKeyMap.put("Ka", 4795);
        AmharicModel.rdcKeyMap.put("Kie", 4796);
        AmharicModel.rdcKeyMap.put("K", 4797);
        AmharicModel.rdcKeyMap.put("Ko", 4798);
        AmharicModel.rdcKeyMap.put("Kwe", 4800);
        AmharicModel.rdcKeyMap.put("Kwi", 4802);
        AmharicModel.rdcKeyMap.put("Kwa", 4803);
        AmharicModel.rdcKeyMap.put("Kwie", 4804);
        AmharicModel.rdcKeyMap.put("Kw", 4805);
        AmharicModel.rdcKeyMap.put("we", 4808);
        AmharicModel.rdcKeyMap.put("wu", 4809);
        AmharicModel.rdcKeyMap.put("wi", 4810);
        AmharicModel.rdcKeyMap.put("wa", 4811);
        AmharicModel.rdcKeyMap.put("wie", 4812);
        AmharicModel.rdcKeyMap.put("w", 4813);
        AmharicModel.rdcKeyMap.put("wo", 4814);
        AmharicModel.rdcKeyMap.put("Ee", 4816);
        AmharicModel.rdcKeyMap.put("Eu", 4817);
        AmharicModel.rdcKeyMap.put("Ei", 4818);
        AmharicModel.rdcKeyMap.put("Ea", 4819);
        AmharicModel.rdcKeyMap.put("Eie", 4820);
        AmharicModel.rdcKeyMap.put("E", 4821);
        AmharicModel.rdcKeyMap.put("Eo", 4822);
        AmharicModel.rdcKeyMap.put("ze", 4824);
        AmharicModel.rdcKeyMap.put("zu", 4825);
        AmharicModel.rdcKeyMap.put("zi", 4826);
        AmharicModel.rdcKeyMap.put("za", 4827);
        AmharicModel.rdcKeyMap.put("zie", 4828);
        AmharicModel.rdcKeyMap.put("z", 4829);
        AmharicModel.rdcKeyMap.put("zo", 4830);
        AmharicModel.rdcKeyMap.put("Ze", 4832);
        AmharicModel.rdcKeyMap.put("Zu", 4833);
        AmharicModel.rdcKeyMap.put("Zi", 4834);
        AmharicModel.rdcKeyMap.put("Za", 4835);
        AmharicModel.rdcKeyMap.put("Zie", 4836);
        AmharicModel.rdcKeyMap.put("Z", 4837);
        AmharicModel.rdcKeyMap.put("Zo", 4838);
        AmharicModel.rdcKeyMap.put("ye", 4840);
        AmharicModel.rdcKeyMap.put("yu", 4841);
        AmharicModel.rdcKeyMap.put("yi", 4842);
        AmharicModel.rdcKeyMap.put("ya", 4843);
        AmharicModel.rdcKeyMap.put("yie", 4844);
        AmharicModel.rdcKeyMap.put("y", 4845);
        AmharicModel.rdcKeyMap.put("yo", 4846);
        AmharicModel.rdcKeyMap.put("yoa", 4847);
        AmharicModel.rdcKeyMap.put("de", 4848);
        AmharicModel.rdcKeyMap.put("du", 4849);
        AmharicModel.rdcKeyMap.put("di", 4850);
        AmharicModel.rdcKeyMap.put("da", 4851);
        AmharicModel.rdcKeyMap.put("die", 4852);
        AmharicModel.rdcKeyMap.put("d", 4853);
        AmharicModel.rdcKeyMap.put("do", 4854);
        AmharicModel.rdcKeyMap.put("je", 4864);
        AmharicModel.rdcKeyMap.put("ju", 4865);
        AmharicModel.rdcKeyMap.put("ji", 4866);
        AmharicModel.rdcKeyMap.put("ja", 4867);
        AmharicModel.rdcKeyMap.put("jie", 4868);
        AmharicModel.rdcKeyMap.put("j", 4869);
        AmharicModel.rdcKeyMap.put("jo", 4870);
        AmharicModel.rdcKeyMap.put("ge", 4872);
        AmharicModel.rdcKeyMap.put("gu", 4873);
        AmharicModel.rdcKeyMap.put("gi", 4874);
        AmharicModel.rdcKeyMap.put("ga", 4875);
        AmharicModel.rdcKeyMap.put("gie", 4876);
        AmharicModel.rdcKeyMap.put("g", 4877);
        AmharicModel.rdcKeyMap.put("go", 4878);
        AmharicModel.rdcKeyMap.put("gwe", 4880);
        AmharicModel.rdcKeyMap.put("gwi", 4882);
        AmharicModel.rdcKeyMap.put("gwa", 4883);
        AmharicModel.rdcKeyMap.put("gwie", 4884);
        AmharicModel.rdcKeyMap.put("gw", 4885);
        AmharicModel.rdcKeyMap.put("Te", 4896);
        AmharicModel.rdcKeyMap.put("Tu", 4897);
        AmharicModel.rdcKeyMap.put("Ti", 4898);
        AmharicModel.rdcKeyMap.put("Ta", 4899);
        AmharicModel.rdcKeyMap.put("Tie", 4900);
        AmharicModel.rdcKeyMap.put("T", 4901);
        AmharicModel.rdcKeyMap.put("To", 4902);
        AmharicModel.rdcKeyMap.put("Che", 4904);
        AmharicModel.rdcKeyMap.put("Chu", 4905);
        AmharicModel.rdcKeyMap.put("Chi", 4906);
        AmharicModel.rdcKeyMap.put("Cha", 4907);
        AmharicModel.rdcKeyMap.put("Chie", 4908);
        AmharicModel.rdcKeyMap.put("Ch", 4909);
        AmharicModel.rdcKeyMap.put("Cho", 4910);
        AmharicModel.rdcKeyMap.put("Ce", 4904);
        AmharicModel.rdcKeyMap.put("Cu", 4905);
        AmharicModel.rdcKeyMap.put("Ci", 4906);
        AmharicModel.rdcKeyMap.put("Ca", 4907);
        AmharicModel.rdcKeyMap.put("Cie", 4908);
        AmharicModel.rdcKeyMap.put("C", 4909);
        AmharicModel.rdcKeyMap.put("Co", 4910);
        AmharicModel.rdcKeyMap.put("Pe", 4912);
        AmharicModel.rdcKeyMap.put("Pu", 4913);
        AmharicModel.rdcKeyMap.put("Pi", 4914);
        AmharicModel.rdcKeyMap.put("Pa", 4915);
        AmharicModel.rdcKeyMap.put("Pie", 4916);
        AmharicModel.rdcKeyMap.put("P", 4917);
        AmharicModel.rdcKeyMap.put("Po", 4918);
        AmharicModel.rdcKeyMap.put("tse", 4920);
        AmharicModel.rdcKeyMap.put("tsu", 4921);
        AmharicModel.rdcKeyMap.put("tsi", 4922);
        AmharicModel.rdcKeyMap.put("tsa", 4923);
        AmharicModel.rdcKeyMap.put("tsie", 4924);
        AmharicModel.rdcKeyMap.put("ts", 4925);
        AmharicModel.rdcKeyMap.put("tso", 4926);
        AmharicModel.rdcKeyMap.put("fe", 4936);
        AmharicModel.rdcKeyMap.put("fu", 4937);
        AmharicModel.rdcKeyMap.put("fi", 4938);
        AmharicModel.rdcKeyMap.put("fa", 4939);
        AmharicModel.rdcKeyMap.put("fie", 4940);
        AmharicModel.rdcKeyMap.put("f", 4941);
        AmharicModel.rdcKeyMap.put("fo", 4942);
        AmharicModel.rdcKeyMap.put("pe", 4944);
        AmharicModel.rdcKeyMap.put("pu", 4945);
        AmharicModel.rdcKeyMap.put("pi", 4946);
        AmharicModel.rdcKeyMap.put("pa", 4947);
        AmharicModel.rdcKeyMap.put("pie", 4948);
        AmharicModel.rdcKeyMap.put("p", 4949);
        AmharicModel.rdcKeyMap.put("po", 4950);
        AmharicModel.rdcKeyMap.put("pua", 4951);
        AmharicModel.rdcKeyMap.put(";", 4961);
        AmharicModel.rdcKeyMap.put(".", 4962);
        AmharicModel.rdcKeyMap.put(";;", 4962);
        AmharicModel.rdcKeyMap.put(",", 4963);
        AmharicModel.rdcKeyMap.put("::", 4964);
        AmharicModel.rdcKeyMap.put(":", 4965);
        AmharicModel.rdcKeyMap.put(";-", 4966);
        AmharicModel.rdcKeyMap.put("??", 4967);
        AmharicModel.rdcKeyMap.put("...", 4968);
        AmharicModel.rdcKeyMap.put("..", 46);
        AmharicModel.rdcKeyMap.put("1", 4969);
        AmharicModel.rdcKeyMap.put("2", 4970);
        AmharicModel.rdcKeyMap.put("3", 4971);
        AmharicModel.rdcKeyMap.put("4", 4972);
        AmharicModel.rdcKeyMap.put("5", 4973);
        AmharicModel.rdcKeyMap.put("6", 4974);
        AmharicModel.rdcKeyMap.put("7", 4975);
        AmharicModel.rdcKeyMap.put("8", 4976);
        AmharicModel.rdcKeyMap.put("9", 4977);
        AmharicModel.rdcKeyMap.put("10", 4978);
        AmharicModel.rdcKeyMap.put("20", 4979);
        AmharicModel.rdcKeyMap.put("30", 4980);
        AmharicModel.rdcKeyMap.put("40", 4981);
        AmharicModel.rdcKeyMap.put("50", 4982);
        AmharicModel.rdcKeyMap.put("60", 4983);
        AmharicModel.rdcKeyMap.put("70", 4984);
        AmharicModel.rdcKeyMap.put("80", 4985);
        AmharicModel.rdcKeyMap.put("90", 4986);
        AmharicModel.rdcKeyMap.put("100", 4987);
        AmharicModel.rdcKeyMap.put("1000", 4988);
    }
    
    public char checkCaseInterchange(char caseInterchangeForDfltKbd, final byte b) {
        if (b == 1) {
            if (AmharicModel.defaultKeyMap == null) {
                AmharicModel.instance.initDefaultKdbMap();
            }
            caseInterchangeForDfltKbd = this.caseInterchangeForDfltKbd(caseInterchangeForDfltKbd);
        }
        else if (b == 2) {
            if (AmharicModel.rdcKeyMap == null) {
                AmharicModel.instance.initRDCKbdMap();
            }
            return this.caseInterchangeForRDCKbd(caseInterchangeForDfltKbd);
        }
        return caseInterchangeForDfltKbd;
    }
    
    public Integer get(final String s, final byte b) {
        switch (b) {
            default: {
                return null;
            }
            case 1: {
                if (AmharicModel.defaultKeyMap == null) {
                    AmharicModel.instance.initDefaultKdbMap();
                }
                return AmharicModel.defaultKeyMap.get(s);
            }
            case 2: {
                if (AmharicModel.rdcKeyMap == null) {
                    AmharicModel.instance.initRDCKbdMap();
                }
                return AmharicModel.rdcKeyMap.get(s);
            }
        }
    }
}
