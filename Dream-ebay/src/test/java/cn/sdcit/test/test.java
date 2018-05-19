package cn.sdcit.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.soap.DetailEntry;

import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.soap.eBLBaseComponents.FeesType;

import cn.sdcit.ebay.utils.EbayproductUtiles;
import cn.sdcit.entity.EbayDetail;
import cn.sdcit.entity.EbayProduct;
import cn.sdcit.entity.EbayProductDesc;

public class test {

	 public static void main(String[] args) {
	    	EbayProduct product = new EbayProduct();
	    	EbayProductDesc desc = new EbayProductDesc();
	    	desc.setProDesc("啊啊啊啊啊");
	    	product.setCid((long) 357);
	    	product.setCountry("GB");
	    	product.setCreated(new Date());
	    	product.setCurrency("GBP");
	    	product.setDispatchTimeMax(2);
	    	product.setEbayProductDesc(desc);
	    	product.setEid((long)  2);
	    	product.setEurl("AgAAAA**AQAAAA**aAAAAA**1u/zWg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4agDZCBpAudj6x9nY+seQ**bJMEAA**AAMAAA**BFK3zzJhqo3/4wMXqNYaKQuIBAwPLR+zWM+yibFRWFAP+28jNW+PxVDAJ0TtIdI6zbIKHSdgAMBb8LIF6reb4Qbh/m7xuDaiuWGsf8SBv/uvUKFayZYztzx0k39IeuHE3se+dgLpxEqgA6FlOWkdLGz87E7kzJSbdCSFjMO6QEnqfYK5oE1qaNTpR01Viwem63Lpuc6z2epS+O8FAwV896VHOrOCM2jdcxyx6aRPsbfjUe0UljMLr4GRl+B7rGOvcY9K97vDAn6VeOhmOyaTjvz8bqpDnYCwZt8fhEY/6GAkgYPaLdCpOBXEzZ7umhBJXq1ecSaQrSoKBRVQyo64DniHYq5JWxuFLuhbF8XWx85sVaaaiXB1vxuMBXRasQVLR/XTNRUvkc7CI7VpxYkAmFDdRZCMBRTOXeEBEEEshX6ABk/VdMjpL4qOQe0UGKb8qiF0k8k4fQ92dWUkuKXzC7EujiqAgtKxhSW/nuDF6t4LkotDZ5VawZJ63AXM6vJHQMivvcSOXZgD0bUIZeggWWnyHjRPOkUZ/vo3uROXF2O+2Yb/eS9yr7MDwHfBOUvPd3BK9EyY5H104j+nYM8nBQEH5+TisTuVqEaBPCikPyH7kTL0lzxGbB5/F2PUGz3gST8WjnPxZ5lkIWcJHfd/vo04/XBJ1a1+Pp5gDUWLVZtF0oe3YII1O7DoMPCu+O6YVlUqJCZiLev1IkK4NmPBeP1nDoFoJiaZ3ETD1CCE2T+tIMaj5JgzCw6k6B6D0ru7");;
	        product.setId((long) 4);
	        product.setPaypalEmailAddress("888@yirose.com");
	        product.setPostalcode("SE6 1AL");
	        product.setListingDuration("Days_7");
	        product.setQuantity(555);
	        product.setUpdated(new Date());
	        product.setCreated(new Date());
	        product.setLocation("UK");
	        product.setPrice(98.22);
	        product.setTitle("这是一个东西1");
	        product.setImages("http://www.dilianidc.com/templets/twang/images/tw_11.jpg");
	        EbayDetail detail1=new EbayDetail();
	        detail1.setId(new Long(11));
	        detail1.setIsLeaf(0);
	        detail1.setIsParent(1);
	        detail1.setName("size");
	        detail1.setValue("xxx,lll,mmm,sss");
	        detail1.setProductId(new Long(0));
	        detail1.setQuantity(50);
	        detail1.setParentId(new Long(0));
	        detail1.setStartprice(500);
	        EbayDetail detail2=new EbayDetail();
	        detail2.setId(new Long(2));
	        detail2.setIsLeaf(0);
	        detail2.setIsParent(1);
	        detail2.setName("colorr");
	        detail2.setValue("red,black,blue,aa");
	        detail2.setProductId(new Long(0));
	        detail2.setQuantity(50);
	        detail2.setParentId(new Long(0));
	        detail2.setStartprice(500);
	        EbayDetail detail3=new EbayDetail();
	        detail3.setId(new Long(3));
	        detail3.setIsLeaf(0);
	        detail3.setIsParent(0);
	        detail3.setName("size");
	        detail3.setValue("xxx");
	        detail3.setProductId(new Long(1));
	        detail3.setQuantity(50);
	        detail3.setParentId(new Long(1));
	        detail3.setStartprice(500);
	        EbayDetail detail4=new EbayDetail();
	        detail4.setId(new Long(4));
	        detail4.setIsLeaf(0);
	        detail4.setIsParent(0);
	        detail4.setName("colorr");
	        detail4.setValue("black");
	        detail4.setProductId(new Long(1));
	        detail4.setQuantity(50);
	        detail4.setParentId(new Long(3));
	        detail4.setStartprice(500);
	        EbayDetail detail5=new EbayDetail();
	        detail5.setId(new Long(5));
	        detail5.setIsLeaf(0);
	        detail5.setIsParent(0);
	        detail5.setName("colorr");
	        detail5.setValue("blue");
	        detail5.setProductId((long)1);
	        detail5.setQuantity(50);
	        detail5.setParentId(new Long(6));
	        detail5.setStartprice(500);
	        EbayDetail detail6=new EbayDetail();
	        detail6.setId(new Long(6));
	        detail6.setIsLeaf(0);
	        detail6.setIsParent(0);
	        detail6.setName("size");
	        detail6.setValue("lll");
	        detail6.setProductId(new Long(1));
	        detail6.setQuantity(50);
	        detail6.setParentId(new Long(1));
	        detail6.setStartprice(500);
	    	List<EbayDetail> detaillist = new ArrayList<EbayDetail>();
	    	detaillist.add(detail1);
	    	detaillist.add(detail2);
	    	detaillist.add(detail3);
	    	detaillist.add(detail4);
	    	detaillist.add(detail5);
	    	detaillist.add(detail6);
			product.setDetaillist(detaillist);
	    	try {
	    		EbayproductUtiles utiles = new EbayproductUtiles(product);
	            FeesType fee =utiles.addpproduct(product);
	        } catch (ApiException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (SdkException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	    }

}
