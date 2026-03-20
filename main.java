import java.util.*;
public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
    
    public static class Person{
        int age;
        
        int birthmonth;
        String name;
        int ideology, personality;
        
        int charisma, ambition;
        int allies;
        
        int influence;
        
        Map<Person, Integer> relations = new HashMap<>();
        List<Person> relations_public = new ArrayList<>();
        
        public Person(int age, String name){
            this.age = age;
            this.name = name;
            this.ideology = ra.nextInt(100);
            if(genSec!=null){
            if(ideology> genSec.getIdeo()){
                ideology-= ra.nextInt(25);
            }else if(ideology< genSec.getIdeo()){
                ideology+= ra.nextInt(25);
            }
            }
            
            this.personality = ra.nextInt(100);
            this.charisma = ra.nextInt(100);
            this.ambition = ra.nextInt(100);
            this.birthmonth=ra.nextInt(4);
            allies = 0;
            influence=0;
        }
        
        public int getAge(){
            return age;
        }
        
        public int getBirthMonth(){
            return birthmonth;
        }
        
        public void incrementAge(){
            age++;
        }
        
        public String getName(){
            return name;
        }
        
        public int getIdeo(){
            return ideology;
        }
        
        public int getPersonality(){
            return personality;
        }
        
        public int proximityWith(Person per){
            int ideodif = Math.abs(per.getIdeo()-ideology);
            int perdif = Math.abs(per.getPersonality()-personality);
            int totdif = (perdif/2)+ (ideodif/2);
            return 100-totdif;
        }
        
        public int getCharisma(){
            return charisma;
        }
        public int getAmbition(){
            return ambition;
        }
        
        public int getInfluence(){
            return influence;
        }
        
        public void updateAllies(){
            allies=0;
            for(Person per: relations.keySet()){
                if(relations.get(per)> 75){
                    allies++;
                }
            }
        }
        
        public int getNumOfAllies(){
            return allies;
        }
        
        public void updateRelations(){
            relations.clear();
            relations_public.clear();
            for(Person per: allPersons){
                if(per!=this){
                int newrel = proximityWith(per);
                
                newrel -= (Math.abs(per.getAge()-age))/10;
                newrel+= per.getCharisma()/5;
                newrel-=per.getAmbition()/10;
                newrel+=per.getInfluence()/10;
                relations.put(per, newrel);
                relations_public.add(per);
                }
            }
            
            relations_public.sort(Comparator.comparingInt(p->
                relations.get(p)
            ).reversed());
        }
        
        public void updateInfluence(){
            influence=0;
            influence+= allies*5;
            influence += this.relationWith(chairman);
            influence += this.relationWith(genSec);
            influence += this.relationWith(premier);
            influence += this.relationWith(whip);
            //influence+= (influence*(charisma/20))/10;
            
            if(this==chairman){
                influence+= influence/3;
            }
            
            if(this==genSec){
                influence+= influence/2;
            }
            if(this==premier){
                influence+= influence/5;
            }
            if(this==whip){
                influence+= influence/10;
            }
            
            
            
            
        }
        public void displayRelations(){
            System.out.println(name+" relations");
            for(Person per: relations_public){
                
                System.out.print(per.getName()+ ": "+ relations.get(per));
                if(relations.get(per)>75){
                    System.out.println(" - Ally");
                }else{
                    System.out.println();
                }
            }
        }
        
        public int relationWith(Person per){
            if(relations.keySet().contains(per)){
                return relations.get(per);
            }
            return 0;
        }
        @Override
        public String toString(){
            return name+", "+ age+ " ("+ideologyOf(this)+")";
        }
    }
    
    public static String genName(){
        String[] lastNames = {
        "Li", "Wang", "Zhang", "Liu", "Chen", "Yang", "Zhao", "Huang", "Zhou", "Wu",
        "Xu", "Sun", "Hu", "Zhu", "Gao", "Lin", "He", "Guo", "Ma", "Luo",
        "Liang", "Song", "Zheng", "Xie", "Han", "Tang", "Feng", "Yu", "Dong", "Xiao",
        "Cheng", "Cao", "Yuan", "Deng", "Xu", "Fu", "Shen", "Zeng", "Peng", "Lv",
        "Su", "Lu", "Jiang", "Cai", "Jia", "Ding", "Wei", "Xue", "Ye", "Yan",
        "Pan", "Du", "Dai", "Xia", "Zhong", "Tian", "Ren", "Jiang", "Fan", "Fang",
        "Shi", "Yao", "Tan", "Liao", "Zou", "Xiong", "Jin", "Huang", "Meng", "Mao",
        "Qiu", "Qin", "Jiang", "Cui", "Gu", "Hou", "Liao", "Meng", "Long", "Wan",
        "Duan", "Zhang", "Qian", "Tang", "Yin", "Li", "Yi", "Wu", "Shao", "Gu",
        "Lan", "Mo", "Kong", "Bai", "Yan", "Tao", "Mao", "Chang", "Gong", "Xing",
        "Niu", "Hong", "Hao", "An", "Ni", "Qi", "Lin", "Xun", "Tong", "Ge",
        "Su", "Qiu", "Gan", "Jie", "Mei", "Zuo", "Cao", "Guo", "Lu", "Tu",
        "Shen", "Yue", "Fu", "Ke", "Xuan", "Gu", "Zha", "Sui", "Luan", "Gong",
        "Zhu", "Ji", "Xing", "Qu", "Fei", "Chai", "Mu", "Zhan", "Hui", "Si",
        "Shen", "Le", "Chu", "Bu", "Gu", "Bian", "Geng", "Yuan", "Zheng", "He",
        "Ban", "Ning", "Pang", "Bo", "Hou", "Rong", "Weng", "Qi", "Mu", "Zhan",
        "Miao", "Li", "Bao", "Fei", "Gu", "Yang", "Ruan", "Lan", "Long", "Yu",
        "Zhe", "Liao", "Wei", "Hua", "Su", "Yin", "Lian", "Zeng", "Cui", "Xi",
        "Ji", "Bao", "Zheng", "Huo", "Ran", "Song", "Pu", "Qu", "Zhuang", "Sang",
        "Cong", "Zhong", "Yu", "Bian", "Shan", "Mo", "Guang", "Gong", "Ji", "Gao"
        };
        
        String[] firstSyllables = {
            "Zi", "Hao", "Yu", "Yue", "Zhen", "Ming", "Jun", "Yi", "Zhan", "Tian",
            "Jia", "Ze", "Xiao", "Ru", "Shu", "Wen", "Hai", "Ying", "Mei", "Ling",
            "Bao", "Chao", "Da", "De", "En", "Feng", "Gang", "Guang", "Guo", "Heng",
            "Hong", "Huai", "Hui", "Jian", "Jin", "Jing", "Kai", "Kang", "Kun", "Lan",
            "Le", "Li", "Lian", "Long", "Min", "Ning", "Peng", "Ping", "Qi", "Qiang",
            "Qin", "Qing", "Rong", "Rui", "Shan", "Shao", "Sheng", "Shi", "Shuang", "Shui",
            "Song", "Tai", "Tao", "Ting", "Wei", "Wu", "Xi", "Xian", "Xiang", "Xin",
            "Xiu", "Xu", "Xuan", "Yan", "Yao", "Ye", "Yong", "You", "Yu", "Yuan",
            "Yun", "Zhe", "Zhi", "Zhong", "Zhou", "Zhu", "Zhuo", "Zi", "Zong", "Bo",
            "Cai", "Can", "Ce", "Cen", "Chang", "Chen", "Cheng", "Chong", "Chuan", "Chun",
            "Ci", "Cong", "Cui", "Dai", "Dan", "Dang", "Dao", "Di", "Dian", "Die",
            "Ding", "Dong", "Du", "Duan", "Fan", "Fang", "Fei", "Fen", "Fu", "Gai",
            "Gan", "Gao", "Ge", "Gen", "Gong", "Gou", "Gu", "Gua", "Guan", "Gui",
            "Gun", "Han", "Hao", "He", "Hei", "Hen", "Hou", "Hu", "Hua", "Huan",
            "Huang", "Hui", "Hun", "Huo", "Ji", "Jia", "Jian", "Jiang", "Jiao", "Jie",
            "Jin", "Jing", "Jiong", "Ju", "Juan", "Jue", "Jun", "Ka", "Kai", "Kan",
            "Kang", "Ke", "Ken", "Kong", "Ku", "Kua", "Kuai", "Kuan", "Kuang", "Kui",
            "Kun", "Kuo", "La", "Lai", "Lan", "Lang", "Lao", "Le", "Lei", "Leng",
            "Li", "Lian", "Liang", "Liao", "Lie", "Lin", "Ling", "Liu", "Long", "Lou",
            "Lu", "Luan", "Lun", "Luo", "Ma", "Mai", "Man", "Mang", "Mao", "Mei"
        };
        
        String[] secondSyllables = {
            "ran", "xuan", "han", "yu", "yi", "hao", "chen", "ming", "rui", "wen",
            "jie", "xin", "ting", "ling", "mei", "yan", "qi", "lin", "feng", "bin",
            "kun", "bo", "hui", "jun", "long", "qiang", "wei", "gang", "jian", "ping",
            "yong", "chao", "huo", "lei", "yang", "shuang", "dan", "jing", "hua", "li",
            "na", "fang", "juan", "min", "hong", "lan", "xiu", "zhen", "yun", "qing",
            "shan", "rong", "ying", "yu", "an", "bang", "bei", "bi", "bian", "biao",
            "bie", "bing", "bu", "ca", "cai", "can", "cang", "cao", "ce", "cen",
            "ceng", "cha", "chai", "chan", "chang", "chao", "che", "chen", "cheng", "chi",
            "chong", "chou", "chu", "chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci",
            "cong", "cou", "cu", "cuan", "cui", "cun", "cuo", "da", "dai", "dan",
            "dang", "dao", "de", "deng", "di", "dian", "diao", "die", "ding", "diu",
            "dong", "dou", "du", "duan", "dui", "dun", "duo", "e", "en", "er",
            "fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou", "fu", "ga",
            "gai", "gan", "gang", "gao", "ge", "gei", "gen", "geng", "gong", "gou",
            "gu", "gua", "guai", "guan", "guang", "gui", "gun", "guo", "ha", "hai",
            "han", "hang", "hao", "he", "hei", "hen", "heng", "hong", "hou", "hu",
            "hua", "huai", "huan", "huang", "hui", "hun", "huo", "ji", "jia", "jian",
            "jiang", "jiao", "jie", "jin", "jing", "jiong", "jiu", "ju", "juan", "jue",
            "jun", "ka", "kai", "kan", "kang", "kao", "ke", "ken", "keng", "kong",
            "kou", "ku", "kua", "kuai", "kuan", "kuang", "kui", "kun", "kuo", "la"
        };
        
        
        String fullname = lastNames[ra.nextInt(lastNames.length)] + " "+ firstSyllables[ra.nextInt(firstSyllables.length)] + ((ra.nextInt(10)<5)? secondSyllables[ra.nextInt(secondSyllables.length)]:"");
        return fullname;
    }
    public static List<Person> allPersons = new ArrayList<>();
    public static int gencomsize=  100;
    public static void addPersons(){
        for(int i=0; i!= gencomsize;i++){
            allPersons.add(new Person(ra.nextInt(20)+40, genName()));
        }
    }
    
    public static void addNewPerson(){
        while(allPersons.size()<gencomsize){
            allPersons.add(new Person(ra.nextInt(20)+40, genName()));
        }
    }
    
    public static void relUpdate(){
        for(Person per: allPersons){
            per.updateRelations();
            per.updateAllies();
        }
    }
    
    public static void influenceUpdate(){
        for(Person per: allPersons){
            per.updateInfluence();
        }
    }
    
    public static int year = 1921;
    public static int month=0;
    public static String[] month_name= new String[]{"Winter", "Spring", "Summer", "Autumn"};
    
    public static int centCountDown=1;
    public static int chairmanCountDown=2;
    public static int genSecCountDown=3;
    public static int whipCountDown=2;
    public static int premCountDown=2;
    
    public static boolean canBlockElection(Person leader) {
    if (leader == null) return false;
    
    // A leader is "Entrenched" if they have massive influence 
    // or if the Party Whip is their close ally.
    boolean hasHighInfluence = leader.getInfluence() > 800;
    boolean whipIsAlly = (whip != null && leader.relationWith(whip) > 85);
    
    return hasHighInfluence || whipIsAlly;
}
    
    public static void monthly(){
        relUpdate();
        influenceUpdate();
        checkBDay();
        checkAge();
        addNewPerson();
        
        allPersons.sort(Comparator.comparingInt((Person p)->
            p.getInfluence()
        ).reversed());
        centCom.sort(Comparator.comparingInt((Person p)->
            p.getInfluence()
        ).reversed());
        if(whip !=null){
        purgeOpposition();
        }
        month++;
        centCountDown--;
        chairmanCountDown--;
        genSecCountDown--;
        whipCountDown--;
        premCountDown--;
        if(centCountDown==0){
            electCentCom();
            centCountDown=4;
        }
        
        if(chairmanCountDown==0){
            if(canBlockElection(chairman) && allPersons.contains(chairman)){
                chairmanCountDown=4;
            }else{
            electChairman();
            chairmanCountDown=12;
            }
        }
        
        if(genSecCountDown==0){
            if(canBlockElection(genSec) && allPersons.contains(genSec)){
                genSecCountDown=4;
            }else{
            electGenSec();
            genSecCountDown=16;
            }
        }
        
        if(whipCountDown==0){
            electWhip();
            whipCountDown=8;
        }
        
        if(premCountDown==0){
            
            electPrem();
            premCountDown=12;
            
        }
        
        if(month == 4){
            
            yearly();
            
            
            month=0;
            year++;
        }
    }
    
    public static void checkBDay(){
        for(Person per: allPersons){
            if(month == per.getBirthMonth()){
                per.incrementAge();
            }
        }
    }
    
    public static void checkAge(){
        List<Person> toRemove = new ArrayList<>();
        int retage= 60;
        for(Person per: allPersons){
            int points = per.getAge()-retage;
            if(points > 0){
                if(ra.nextInt(points)> 10){
                    toRemove.add(per);
                }
            }
        }
        if(toRemove !=null){
            for(Person per: toRemove){
                System.out.println(per.getName()+ " has retired!");
            }
        }
        allPersons.removeAll(toRemove);
    }
    
    
    
    public static void yearly(){
        
    }
    
    public static String ideologyOf(Person per){
        if(per.getIdeo()<20){
            return "Radical";
        }
        if(per.getIdeo()<50){
            return "Conservative";
        }
        if(per.getIdeo()<80){
            return "Moderate";
        }
        if(per.getIdeo()<100){
            return "Reformist";
        }
        return"";
    }
    
    public static List<Person> centCom = new ArrayList<>();
    
    public static void electCentCom(){
        centCom.clear();
        int totmems = allPersons.size()/10;
        int counter=0;
        for(Person per: allPersons){
            centCom.add(per);
            counter++;
            if(counter==totmems){
                break;
            }
        }
    }
    
    public static Person chairman;
    public static void electChairman(){
        chairman= null;
        Map<Person, Integer> candidates = new HashMap<>();
        for(Person per: allPersons){
            if(per.getAmbition()+(per.getInfluence()/10)> 50){
                candidates.put(per,0);
            }
        }
        
        if(candidates.size() ==0){
            candidates.put(centCom.get(ra.nextInt(centCom.size())),0);
        }
        
        boolean majorityFound=false;
        
        do{
            
            for(Person voter: allPersons){
                int maxnum=-1;
                Person maxper=null;
                for(Person can: candidates.keySet()){
                    int points=voter.relationWith(can);
                    if(points > maxnum){
                        maxper = can;
                        maxnum = points;
                    }
                }
                
                candidates.put(maxper, candidates.get(maxper)+1);
            }
            
            int maxnum=-1;
            Person winner=null;
            for(Person per: candidates.keySet()){
                if(candidates.get(per)> maxnum){
                    maxnum = candidates.get(per);
                    winner = per;
                }
            }
            
            if(candidates.get(winner)>allPersons.size()/2){
                chairman = winner;
                majorityFound=true;
            }else{
                maxnum = Integer.MAX_VALUE;
                Person loser = null;
                for(Person per: candidates.keySet()){
                if(candidates.get(per)< maxnum){
                    maxnum = candidates.get(per);
                    loser = per;
                }
                
                 }
                candidates.remove(loser);
            }
            
        }while(!majorityFound);
        
    }
    
    public static Person genSec;
    public static void electGenSec(){
        genSec= null;
        Map<Person, Integer> candidates = new HashMap<>();
        for(Person per: centCom){
            if(per.getAmbition()+(per.getInfluence()/10)> 50){
                candidates.put(per,0);
            }
        }
        
        if(candidates.size() ==0){
            candidates.put(centCom.get(ra.nextInt(centCom.size())),0);
        }
        
        boolean majorityFound=false;
        
        do{
            
            for(Person voter: centCom){
                int maxnum=-1;
                Person maxper=null;
                for(Person can: candidates.keySet()){
                    int points=voter.relationWith(can);
                    if(points > maxnum){
                        maxper = can;
                        maxnum = points;
                    }
                }
                
                candidates.put(maxper, candidates.get(maxper)+1);
            }
            
            int maxnum=-1;
            Person winner=null;
            for(Person per: candidates.keySet()){
                if(candidates.get(per)> maxnum){
                    maxnum = candidates.get(per);
                    winner = per;
                }
            }
            
            if(candidates.get(winner)>centCom.size()/2){
                genSec = winner;
                majorityFound=true;
            }else{
                maxnum = Integer.MAX_VALUE;
                Person loser = null;
                for(Person per: candidates.keySet()){
                if(candidates.get(per)< maxnum){
                    maxnum = candidates.get(per);
                    loser = per;
                }
                
                 }
                candidates.remove(loser);
            }
            
        }while(!majorityFound);
        
    }
    
    public static Person whip;
    public static void electWhip(){
        int maxnum=-1;
        Person maxper=null;
        for(Person per: allPersons){
            if(per!=chairman){
                if(chairman.relationWith(per)>maxnum){
                    if(per.getAmbition()<50){
                        maxnum=chairman.relationWith(per);
                        maxper = per;
                    }
                }
            }
        }
        
        whip = maxper;
    }
    
    public static Person premier;
    public static void electPrem(){
       
        List<Person> candidates = new ArrayList<>(allPersons);
        
        candidates.sort(Comparator.comparingInt((Person p)->
                chairman.relationWith(p)
            ).reversed());
        int votes=0;
        for(Person can: candidates){
            votes=0;
            
            for(Person per: allPersons){
                if(per.relationWith(can)> 50){
                    votes++;
                }
            }
            
            if(votes> allPersons.size()/2){
                premier = can;
                break;
                
            }
        }
    }
    
    public static void purgeOpposition(){
        if (whip == null || chairman == null) return;

    List<Person> threats = new ArrayList<>();
    for (Person p : allPersons) {
        if (p == chairman) continue;

        // A threat is someone with high ambition AND high influence
        if (p.getAmbition() > 70 && p.getInfluence() > (chairman.getInfluence() * 0.7)) {
            threats.add(p);
        }
    }

    if (!threats.isEmpty()) {
        // Higher chance to purge if the leader is paranoid (High Ambition)
        if (ra.nextInt(100) < chairman.getAmbition()) {
            Person target = threats.get(ra.nextInt(threats.size()));
            //System.out.println(RED + BOLD + " [!!!] POLITICAL PURGE: " + target.getName() + " was eliminated as a rival to " + chairman.getName() + RESET);
            allPersons.remove(target);
        }
    }
    }
    
    
    // --- UI HELPER METHODS ---
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public static String getIdeoColor(Person p) {
        String ideo = ideologyOf(p);
        if (ideo.equals("Radical")) return RED;
        if (ideo.equals("Reformist")) return GREEN;
        if (ideo.equals("Moderate")) return CYAN;
        return YELLOW; // Conservative
    }

    public static String drawBar(int val, int max) {
        int bars = (int) Math.min(10, ((double) val / max) * 10);
        return "o".repeat(Math.max(0, bars)) + "-".repeat(Math.max(0, 10 - bars));
    }

    public static void printHeader(String title) {
        System.out.println(BOLD + "\n====================================================");
        System.out.println("  " + title);
        System.out.println("====================================================" + RESET);
    }

    public static void displayDashboard() {
        // Clear screen (works on most modern terminals)
        System.out.print("\033[H\033[2J");
        System.out.flush();

        printHeader("STATE OF THE PARTY - " + month_name[month] + " " + year);
        
        System.out.printf("%-20s : %s%s%s\n", "CHAIRMAN", getIdeoColor(chairman), chairman.name, RESET);
        System.out.printf("%-20s : %s%s%s\n", "GEN-SECRETARY", getIdeoColor(genSec), genSec.name, RESET);
        System.out.printf("%-20s : %s%s%s\n", "PREMIER", getIdeoColor(premier), premier.name, RESET);
        System.out.printf("%-20s : %s%s%s\n", "PARTY WHIP", getIdeoColor(whip), whip.name, RESET);

        printHeader("CENTRAL COMMITTEE (TOP 10 INFLUENCE)");
        System.out.printf(BOLD + "%-20s | %-15s | %-15s\n" + RESET, "Name", "Ideology", "Influence");
        System.out.println("----------------------------------------------------");
        
        int maxinf = 0;
        for(Person per: allPersons){
            if(per.getInfluence()>maxinf){
                maxinf = per.getInfluence();
            }
        }
        
        int limit = Math.min(10, centCom.size());
        for (int i = 0; i < limit; i++) {
            Person p = centCom.get(i);
            String color = getIdeoColor(p);
            System.out.printf("%-20s | %s%-15s%s | %s\n", 
                p.name, color, ideologyOf(p), RESET, drawBar(p.getInfluence(), maxinf));
        }
        
        System.out.println("\n" + BOLD + "[ Press ENTER to advance ]" + RESET);
    }
    
    
    
	public static void main(String[] args) {
		addPersons();
		monthly();
		electCentCom();
		electChairman();
		electGenSec();
		electWhip();
		electPrem();
        
		while(true){
		    displayDashboard();
		    sc.nextLine();
            System.out.println(CYAN + "Processing political shifts..." + RESET);
		    monthly();
		}
		
		
	}
}
