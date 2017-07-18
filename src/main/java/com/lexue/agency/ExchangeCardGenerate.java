package com.lexue.agency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;


public class ExchangeCardGenerate {
	public static void main(String[] args) {
		char[] passArr={'1','2','3','4','5','6','7','8','9','0','a','b','c','d','e','f','g','h','i','g','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		/**
		 * 	00000001 语文
			00100001 数学
			00200001 外语
			00300001 物理
			00400001 化学
			00500001 生物
			00600001 历史
			00700001 地理
			00800001 政治
			02000001 五科卡
			02100001 理科卡
			02200001 文科卡
			10000001 自招体验卡
			10100001 高考体验卡
			20100001 中考数学暑期卡   中考套卡：第一位2，第二三位：语数外理化生史地政（00-08）五科卡（20）理科卡（21）文科卡（22），第四位：暑期卡（0），后四位按顺序生成
		    3010 0000         乐学高考艺考卡：第一位3，第二三位：语数外理化生史地政（00-08），后五位按顺序生成
		    3020 0000         乐学高考艺考卡：第一位3，第二三位：语数外理化生史地政（00-08），后五位按顺序生成
		    3090 0000         乐学高考艺考卡：第一位3，第二三位：2017高考英语串讲预测班【艺考定制】（09），后五位按顺序生成
		 	2540 0001					254（商品id） 写作一课通 高考英语精品课
		    5450 0001   545 高考英语四项全能卡 高考英语精品课
		    1550 0001 155 阅读告别慌堵读 高考英语精品课
		    1530 0001 153 完形填空一点通 高考英语精品课
		    1390 0001 139语法集结号 高考英语精品课
		 */
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<120;i++){
			String kemu="139";//科目
			String number="";
			if(i<10){
				number="0000"+i;
			}else if(i>=10&&i<100){
				number="000"+i;
			}else if(i>=100&&i<1000){
				number="00"+i;
			}else if(i>=1000&&i<10000){
				number="0"+i;
			}else if(i>=10000&&i<100000){
				number=""+i;
			}
			String cardNumber = kemu+number;
			String password=getRandomString(8);
			sb.append("insert into `agency_card` (`name`, `card_password`, `book_value`, `agency_id`, `status`, `sell_price`, `update_time`, `active_time`, `expire_time`, `user_id`, `mobile`, `send_time`, `usage_h5_url`, `desc`, `card_category`, `card_code`, `card_type`,`client`)" +
					                    " values('语法集结号','"+password+"','0','0','2','0','1500220800','1500220800','28339200','0',NULL,'0','','语法集结号',NULL,'"+cardNumber+"','1','gk');\r\n");
		}
		//后续手动写update到agency_card_shop表数据
		write("语法集结号.sql", sb.toString());
		System.out.println("执行完成");
		
	}
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijkmnpqrstuvwxyz23456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {     
            int number = random.nextInt(base.length());     
            sb.append(base.charAt(number));     
        }     
        return sb.toString();     
     }
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return true;
	}
	public static void write(String writePath, String writeTxt) {
		try {
			File f = new File(writePath);
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(writeTxt);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
