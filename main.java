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
        
        Map<Person, Integer> relations = new HashMap<>();
        List<Person> relations_public = new ArrayList<>();
        
        public Person(int age, String name){
            this.age = age;
            this.name = name;
            this.ideology = ra.nextInt(100);
            this.personality = ra.nextInt(100);
            this.birthmonth=ra.nextInt(12);
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
        
        public void updateRelations(){
            relations.clear();
            relations_public.clear();
            for(Person per: allPersons){
                if(per!=this){
                int newrel = proximityWith(per);
                
                newrel -= (Math.abs(per.getAge()-age))/10;
                
                relations.put(per, newrel);
                relations_public.add(per);
                }
            }
            
            relations_public.sort(Comparator.comparingInt(p->
                relations.get(p)
            ).reversed());
        }
        
        public void displayRelations(){
            System.out.println(name+" relations");
            for(Person per: relations_public){
                
                System.out.println(per.getName()+ ": "+ relations.get(per));
            }
        }
        
        public int relationWith(Person per){
            return relations.get(per);
        }
        @Override
        public String toString(){
            return name+", "+ age;
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
        "Shi", "Yao", "Tan", "Liao", "Zou", "Xiong", "Jin", "陸", "Meng", "Mao",
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
    public static void addPersons(){
        for(int i=0; i!= 10;i++){
            allPersons.add(new Person(ra.nextInt(20)+40, genName()));
        }
    }
    
    public static void relUpdate(){
        for(Person per: allPersons){
            per.updateRelations();
        }
    }
    
    public static int year = 1921;
    public static int month=0;
    public static String[] month_name= new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
    
    public static void monthly(){
        relUpdate();
        checkBDay();
        month++;
        if(month == 12){
            
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
    
    public static checkAge(){
        List<Person> toRemove = new ArrayList<>();
        int retage= 70;
        for(Person per: allPersons){
            int points = per.getAge()-retage;
            if(points > 0){
                if(ra.nextInt(points)> 10){
                    toRemove.add(per);
                }
            }
        }
        
        allPersons.removeAll(toRemove);
    }
    
    public static void yearly(){
        
    }
    
	public static void main(String[] args) {
		addPersons();
		
		while(true){
    		System.out.println(month_name[month]+", "+year);
    		
    		for(Person per: allPersons){
    		    System.out.println(per);
    		}
    		sc.nextLine();
    		monthly();
		}
		
		
	}
}
