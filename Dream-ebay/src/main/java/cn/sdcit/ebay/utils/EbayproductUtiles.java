package cn.sdcit.ebay.utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.CORBA.NVList;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.CallRetry;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.AddFixedPriceItemCall;
import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.BuyerPaymentMethodCodeType;
import com.ebay.soap.eBLBaseComponents.CategoryType;
import com.ebay.soap.eBLBaseComponents.CountryCodeType;
import com.ebay.soap.eBLBaseComponents.CurrencyCodeType;
import com.ebay.soap.eBLBaseComponents.FeesType;
import com.ebay.soap.eBLBaseComponents.GalleryTypeCodeType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.NameValueListArrayType;
import com.ebay.soap.eBLBaseComponents.NameValueListType;
import com.ebay.soap.eBLBaseComponents.PictureDetailsType;
import com.ebay.soap.eBLBaseComponents.PicturesType;
import com.ebay.soap.eBLBaseComponents.ReturnPolicyType;
import com.ebay.soap.eBLBaseComponents.ShippingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceCodeType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceOptionsType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import com.ebay.soap.eBLBaseComponents.VariationSpecificPictureSetType;
import com.ebay.soap.eBLBaseComponents.VariationType;
import com.ebay.soap.eBLBaseComponents.VariationsType;

import cn.sdcit.entity.EbayDetail;
import cn.sdcit.entity.EbayProduct;
import cn.sdcit.entity.EbayProductDesc;

public class EbayproductUtiles {
	public  EbayproductUtiles(EbayProduct product) throws Exception{
			FeesType fee = addpproduct(product);
	}
	//private EbayProduct product;
	 private  static String USER_TOKEN="AgAAAA**AQAAAA**aAAAAA**1u/zWg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4agDZCBpAudj6x9nY+seQ**bJMEAA**AAMAAA**BFK3zzJhqo3/4wMXqNYaKQuIBAwPLR+zWM+yibFRWFAP+28jNW+PxVDAJ0TtIdI6zbIKHSdgAMBb8LIF6reb4Qbh/m7xuDaiuWGsf8SBv/uvUKFayZYztzx0k39IeuHE3se+dgLpxEqgA6FlOWkdLGz87E7kzJSbdCSFjMO6QEnqfYK5oE1qaNTpR01Viwem63Lpuc6z2epS+O8FAwV896VHOrOCM2jdcxyx6aRPsbfjUe0UljMLr4GRl+B7rGOvcY9K97vDAn6VeOhmOyaTjvz8bqpDnYCwZt8fhEY/6GAkgYPaLdCpOBXEzZ7umhBJXq1ecSaQrSoKBRVQyo64DniHYq5JWxuFLuhbF8XWx85sVaaaiXB1vxuMBXRasQVLR/XTNRUvkc7CI7VpxYkAmFDdRZCMBRTOXeEBEEEshX6ABk/VdMjpL4qOQe0UGKb8qiF0k8k4fQ92dWUkuKXzC7EujiqAgtKxhSW/nuDF6t4LkotDZ5VawZJ63AXM6vJHQMivvcSOXZgD0bUIZeggWWnyHjRPOkUZ/vo3uROXF2O+2Yb/eS9yr7MDwHfBOUvPd3BK9EyY5H104j+nYM8nBQEH5+TisTuVqEaBPCikPyH7kTL0lzxGbB5/F2PUGz3gST8WjnPxZ5lkIWcJHfd/vo04/XBJ1a1+Pp5gDUWLVZtF0oe3YII1O7DoMPCu+O6YVlUqJCZiLev1IkK4NmPBeP1nDoFoJiaZ3ETD1CCE2T+tIMaj5JgzCw6k6B6D0ru7";
	//String USER_TOKEN = product.getEurl();
	public  ApiContext createApiContext() {
	        ApiContext apiContext = new ApiContext();
	        ApiLogging apiLogging = new ApiLogging();
	        apiContext.setApiLogging(apiLogging);
	        CallRetry cr = new CallRetry();
	        cr.setMaximumRetries(3);
	        cr.setDelayTime(1000); //在每次重试之间等待一秒钟。

	        String[] apiErrorCodes = new String[]{"502"        };
	        //设置CallRetry的触发异常。
	        cr.setTriggerApiErrorCodes(apiErrorCodes);

	        // 构建一个虚拟的SdkSoapException，这样我们就可以得到它的类。
	        Class[] tcs = new Class[]{com.ebay.sdk.SdkSoapException.class        };
	        cr.setTriggerExceptions(tcs);
	        apiContext.setCallRetry(cr);
	        apiContext.setTimeout(180000);

	        ApiCredential cred = new ApiCredential();

	        // set the server url. Pointing to Sandbox
	        apiContext.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");

	        cred.seteBayToken(USER_TOKEN);
	        apiContext.setApiCredential(cred);
	        return apiContext;
	    }
	public FeesType addpproduct(EbayProduct product) throws  Exception{
		//product = this.product;
        FeesType fee = null;
        AddFixedPriceItemCall request = new AddFixedPriceItemCall(createApiContext());
        request.setSite(SiteCodeType.fromValue(product.getLocation()));//
        ItemType item = new ItemType();
        item.setConditionID(1000);//新旧度 全新
        item.setTitle(product.getTitle());//标题
        item.setDescription(product.getEbayProductDesc().getProDesc());//描述
        AmountType amount = new AmountType();
        amount.setValue(Double.valueOf(product.getPrice()));
        item.setStartPrice(amount);//价格
        CategoryType cat = new CategoryType();
        cat.setCategoryID(String.valueOf(product.getCid()));//类目id
        item.setPrimaryCategory(cat);
        item.setSite(SiteCodeType.fromValue(product.getLocation()));//地点
        item.setPostalCode(product.getPostalcode());//
        item.setQuantity(product.getQuantity());//数量
        item.setDispatchTimeMax(product.getDispatchTimeMax());//最大处理时间
        item.setPaymentMethods(new BuyerPaymentMethodCodeType[]{BuyerPaymentMethodCodeType.PAY_PAL});//付款方式paypal
        item.setListingDuration(product.getListingDuration());//上市列表活跃天数 
        item.setPayPalEmailAddress(product.getPaypalEmailAddress());//paypal地址
        item.setShippingDetails(getShippingDetails());
        item.setReturnPolicy(getReturnPolicy());
        item.setCountry(CountryCodeType.fromValue(product.getCountry()));
        item.setCurrency(CurrencyCodeType.fromValue(product.getCurrency()));
	//**********************详细定义*********************************************
        VariationsType vt = new VariationsType();
        
        List<EbayDetail> detaillist = product.getDetaillist();
        NameValueListType [] type= new NameValueListType[detaillist.size()]; 
    	PicturesType pic = new PicturesType();
      int count = 0;
        	for(EbayDetail detail :detaillist){//添加详细定义
        			if(detail.getIsParent()==1){
        				NameValueListType NVListVS1 = new NameValueListType();
        				NVListVS1.setName(detail.getName());
        				String value[] =detail.getValue().split(",");
        				NVListVS1.setValue(value);
        				type[count] = NVListVS1;
        				count++;
        			}
        	}
        	  NameValueListArrayType nvla = new NameValueListArrayType();
        nvla.setNameValueList(type);
        
        
        //******************* 详细细节************************
        VariationType v = new VariationType();
   
        VariationType[] vtype = new VariationType[detaillist.size()];
        int count2 = 0;
        for(EbayDetail detail :detaillist){//添加详细细节
            NameValueListType Var1Spec1 = new NameValueListType();
            NameValueListType Var1Spec2 = new NameValueListType();
        	if(detail.getParentId()==1){
                Var1Spec1.setName(detail.getName());
                String value[] =detail.getValue().split(",");
                Var1Spec1.setValue(value);
               Long parentid = detail.getId();
               for(EbayDetail detail2 :detaillist){ 
                		if(detail2.getParentId().longValue()==parentid.longValue()){
                			if( detail2.getImages() != null){
                			 String []images = detail2.getImages().split(",");              		
                            VariationSpecificPictureSetType [] vsp = new VariationSpecificPictureSetType[detaillist.size()];
                            vsp[count2].setPictureURL(images);
                            vsp[count2].setVariationSpecificValue(detail2.getValue());
                            pic.setVariationSpecificPictureSet(vsp);
                			}
                			vtype[count2] = new VariationType();
                			vtype[count2].setSKU(detail2.getName()+count2);
                			vtype[count2].setQuantity(detail2.getQuantity());
                			vtype[count2].setStartPrice(getAmount(detail2.getStartprice()));
                			Var1Spec2.setName(detail2.getName());
                			 String value2[] =detail2.getValue().split(",");
                			Var1Spec2.setValue(value2);
                			NameValueListArrayType nvla1 = new NameValueListArrayType();
                            nvla1.setNameValueList(new NameValueListType[]{Var1Spec1,Var1Spec2});
                            vtype[count2].setVariationSpecifics(nvla1);
                            count2++;
                		}
                	
                	}
        	}
    
        }    
        // 添加物品级别的图片
        PictureDetailsType pdt = new PictureDetailsType();
        String [] images = product.getImages().split(",");
        pdt.setPictureURL(images);

 
        vt.setVariationSpecificsSet(nvla);
        vt.setVariation(vtype);
       vt.setPictures(new PicturesType[]{pic});
        item.setVariations(vt);
					request.setItem(item);
				    fee = request.addFixedPriceItem();
					return fee;
        }
		 

		
	
    private static AmountType getAmount(double amount) {
        AmountType a = new AmountType();
        a.setValue(amount);
        return a;
    }
    private static ReturnPolicyType getReturnPolicy() {
        ReturnPolicyType rp = new ReturnPolicyType();
        rp.setDescription("No return accepted");
        rp.setReturnsAcceptedOption("ReturnsNotAccepted");
        
        return rp;
    }

    private static ShippingDetailsType getShippingDetails() {
        ShippingDetailsType sd = new ShippingDetailsType();

        ShippingServiceOptionsType st1 = new ShippingServiceOptionsType();
        st1.setShippingService("UK_SellersStandardRate");
        st1.setShippingServiceCost(getAmount(1));
        st1.setShippingServiceAdditionalCost(getAmount(1));

        ShippingServiceOptionsType st2 = new ShippingServiceOptionsType();
        st2.setShippingService("UK_RoyalMailFirstClassStandard");
        st2.setShippingServiceCost(getAmount(4));
        st2.setShippingServiceAdditionalCost(getAmount(2));

        sd.setShippingServiceOptions(new ShippingServiceOptionsType[]{st1,
                    st2
                });

        return sd;
    }
    
   
}
