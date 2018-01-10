package com.lexue.agency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;


public class ExchangeCardGenerate {
	public static void main(String[] args) throws ParseException {
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
		    5900 0001 - 59000120 590  南京石头培优高二暑假班课程
		    6030 0000 - 60300119 603 南京石头培优高一暑假班课程
			 6870  0001-0110   2018高考语文10节课逆袭突破班  卡有效期至 2017.11.24 11:00:00 - 2018.6.10 23:59:59
			 6880   0001-0110   2018高考数学10节课逆袭突破班 卡有效期至 2017.11.24 11:00:00 - 2018.6.10 23:59:59
			 6890   0001-0110   2018高考英语10节课逆袭突破班  卡有效期至 2017.11.24 11:00:00 - 2018.6.10 23:59:59
			 6900   0001-0110   2018高考文综10节课逆袭突破班 卡有效期至 2017.11.24 11:00:00 - 2018.6.10 23:59:59
			 6910   0001-0110   2018高考文科全科10节课逆袭突破班 卡有效期至 2017.11.24 11:00:00 - 2018.6.10 23:59:59

		 	496 	2018高考【数理化】全程班	2018高考【数理化】学习套卡 	100	5	20170503 16:51:09 - 20180610 00:00:00
		 433	2018高考地理全程班	2018高考地理学习套卡	50	5	20170413 19:19:28 - 20180610 00:00:00
		 431	2018高考历史全程班	2018高考历史学习套卡	50	5	20170413 19:15:06 - 20180610 00:00:00
		 429	2018高考政治全程班	2018高考政治学习套卡	50	5	20170413 19:10:04 - 20180610 00:00:00
		 427	2018高考生物全程班	2018高考生物学习套卡	100	5	20170413 19:04:38 - 20180610 00:00:00
		 425	2018高考化学全程班	2018高考化学学习套卡	100	5	20170413 19:00:04 - 20180610 00:00:00
		 423	2018高考物理全程班	2018高考物理学习套卡	100	5	20170413 18:55:18 - 20180610 00:00:00
		 421	2018高考英语全程班	2018高考英语学习套卡	100	5	20170413 18:49:13 - 20180610 00:00:00
		 419	2018高考文科数学全程班	2018高考文科数学学习套卡	100	5	20170413 18:43:35 - 20180610 00:00:00
		 417	2018高考理科数学全程班	2018高考理科数学学习套卡	100	5	20170413 18:36:44 - 20180610 00:00:00
		 415	2018高考语文全程班	2018高考语文学习套卡	100	5	20170413 18:17:20 - 20180610 00:00:00
		 */
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

		String kemu="415";//科目
		String name="2018高考语文学习套卡" ;
		long active_time =sdf.parse("20170413 18:17:20").getTime()/1000 ;
		long  expire_time = sdf.parse("20180610 00:00:00").getTime()/1000 -active_time;

		int mallType =9 ;
		int agency_id= 11;
		int agency_tag_id =230 ;
		String client="gk" ;

		System.out.println("有效时间："+expire_time);

		for(int i=1;i<=105;i++){
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
			sb.append("insert into `agency_card` (`name`, `card_password`, `book_value`, `agency_id`, `status`, `sell_price`" +
					", `update_time`, `active_time`, `expire_time`, `user_id`, `mobile`, `send_time`, `usage_h5_url`, `desc`" +
					", `card_category`, `card_code`, `card_type`,`client`)" +
					" values('"+name+"','"+password+"','0','0','2','0','"+active_time+"','"+active_time+"','"+expire_time+"','0',NULL,'0'" +
					",'','"+name+"',NULL,'"+cardNumber+"','1','"+client+"');\r\n");

		}

		//agency_card_shop
//			insert into agency_card_shop  SELECT f.card_id,9,687,1,NULL from agency_card f where f.card_code like '68700%' ;
		sb.append("insert into agency_card_shop  SELECT f.card_id," +
				mallType+","+kemu+",1,NULL from agency_card f where f.card_code like '"
				+kemu+"%' ;\r\n");
//			-- agency_card
//			update agency_card set agency_id=10,agency_tag_id=229 where card_code like '68700%'  ;
		sb.append("update agency_card set agency_id=" +
				agency_id+",agency_tag_id="+agency_tag_id+" where card_code like '"
				+kemu+"%' ;\r\n");

		//后续手动写update到agency_card_shop表数据
		write(kemu+"datas.sql", sb.toString());
		System.out.println("执行完成");
		
	}
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdfghjkmnpqrstuvwxyz23456789";
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
