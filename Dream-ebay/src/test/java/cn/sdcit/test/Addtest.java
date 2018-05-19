package cn.sdcit.test;

import java.util.ArrayList;
import java.util.List;

import cn.sdcit.entity.EbayDetail;
import cn.sdcit.entity.EbayProduct;

public class Addtest {
	public static void main(String[] args) {
		EbayProduct product =new EbayProduct();
		EbayDetail detail1 =new EbayDetail();
		EbayDetail detail2 =new EbayDetail();
		detail1.setId((long) 1);
		detail1.setName("大小");
		detail1.setValue("xxx");
		detail1.setQuantity(600);
		detail2.setId((long)2);
		detail2.setName("颜色");
		detail2.setValue("black");
		detail2.setParentId((long) 1);
		EbayDetail detail3 =new EbayDetail();
		EbayDetail detail4 =new EbayDetail();
		detail3.setId((long) 3);
		detail3.setName("大小");
		detail3.setValue("lll");
		
		detail3.setQuantity(600);
		detail4.setId((long)4);
		detail4.setName("颜色");
		detail4.setValue("红");
		detail4.setParentId((long) 1);
		List<EbayDetail> detaillist=new ArrayList<EbayDetail>();
			detaillist.add(detail1);
			detaillist.add(detail2);
			detaillist.add(detail3);
			detaillist.add(detail4);
		product.setDetaillist(detaillist);
		
	}

}
