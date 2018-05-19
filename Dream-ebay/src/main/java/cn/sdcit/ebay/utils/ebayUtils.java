package cn.sdcit.ebay.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.AddFixedPriceItemCall;
import com.ebay.sdk.call.AddItemCall;
import com.ebay.sdk.call.GetCategoriesCall;
import com.ebay.sdk.call.GetItemCall;
import com.ebay.sdk.call.VerifyAddItemCall;
import com.ebay.sdk.pictureservice.PictureInfo;
import com.ebay.sdk.pictureservice.PictureService;
import com.ebay.sdk.pictureservice.eps.eBayPictureServiceFactory;
import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.BuyerPaymentMethodCodeType;
import com.ebay.soap.eBLBaseComponents.CategoryType;
import com.ebay.soap.eBLBaseComponents.CountryCodeType;
import com.ebay.soap.eBLBaseComponents.CurrencyCodeType;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.GalleryTypeCodeType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.ListingDurationCodeType;
import com.ebay.soap.eBLBaseComponents.ListingTypeCodeType;
import com.ebay.soap.eBLBaseComponents.PictureDetailsType;
import com.ebay.soap.eBLBaseComponents.ReturnPolicyType;
import com.ebay.soap.eBLBaseComponents.ShippingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceCodeType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceOptionsType;
import com.ebay.soap.eBLBaseComponents.ShippingTypeCodeType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import com.ebay.soap.eBLBaseComponents.UploadSiteHostedPicturesRequestType;
import com.ebay.soap.eBLBaseComponents.UploadSiteHostedPicturesResponseType;

import cn.sdcit.ebay.service.impl.AddItem;
import cn.sdcit.entity.DreamProduct;
import cn.sdcit.entity.DreamProductCat;
import cn.sdcit.entity.EbayProduct;
import cn.sdcit.entity.EbayProductDesc;
import cn.sdcit.utils.IDUtils;
	

public class ebayUtils {
	private static final String PROPERTIES_NAME = "src/main/resources/resources.properties";
	public static String TOKEN = "AgAAAA**AQAAAA**aAAAAA**1u/zWg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4agDZCBpAudj6x9nY+seQ**bJMEAA**AAMAAA**BFK3zzJhqo3/4wMXqNYaKQuIBAwPLR+zWM+yibFRWFAP+28jNW+PxVDAJ0TtIdI6zbIKHSdgAMBb8LIF6reb4Qbh/m7xuDaiuWGsf8SBv/uvUKFayZYztzx0k39IeuHE3se+dgLpxEqgA6FlOWkdLGz87E7kzJSbdCSFjMO6QEnqfYK5oE1qaNTpR01Viwem63Lpuc6z2epS+O8FAwV896VHOrOCM2jdcxyx6aRPsbfjUe0UljMLr4GRl+B7rGOvcY9K97vDAn6VeOhmOyaTjvz8bqpDnYCwZt8fhEY/6GAkgYPaLdCpOBXEzZ7umhBJXq1ecSaQrSoKBRVQyo64DniHYq5JWxuFLuhbF8XWx85sVaaaiXB1vxuMBXRasQVLR/XTNRUvkc7CI7VpxYkAmFDdRZCMBRTOXeEBEEEshX6ABk/VdMjpL4qOQe0UGKb8qiF0k8k4fQ92dWUkuKXzC7EujiqAgtKxhSW/nuDF6t4LkotDZ5VawZJ63AXM6vJHQMivvcSOXZgD0bUIZeggWWnyHjRPOkUZ/vo3uROXF2O+2Yb/eS9yr7MDwHfBOUvPd3BK9EyY5H104j+nYM8nBQEH5+TisTuVqEaBPCikPyH7kTL0lzxGbB5/F2PUGz3gST8WjnPxZ5lkIWcJHfd/vo04/XBJ1a1+Pp5gDUWLVZtF0oe3YII1O7DoMPCu+O6YVlUqJCZiLev1IkK4NmPBeP1nDoFoJiaZ3ETD1CCE2T+tIMaj5JgzCw6k6B6D0ru7";
	static ApiContext apiContext = null;
	//static ApiContext  apiContext;
	static{
		apiContext = new ApiContext();
		 ApiAccount apiAccount = new ApiAccount(); 
			
		ApiCredential apiCredential = apiContext.getApiCredential();
		apiCredential.seteBayToken(TOKEN);
		
		   apiAccount.setApplication("-SBX-2e0394388-1805cf18");  
		    apiAccount.setCertificate("SBX-e0394388525b-be24-40ef-8226-77f3");  
		    apiAccount.setDeveloper("5a70ee83-18f1-457b-a94b-668dc7a7f86e"); 
		    
		    apiCredential.setApiAccount(apiAccount);
		 apiContext.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");
		 apiContext.setEpsServerUrl("https://api.sandbox.ebay.com/ws/api.dll");
		//PropertiesInitialize();
		
	}
	
	@Test
	public void testName() throws Exception {
		System.out.println("111");
		uploadImages("test");
	}

	
	public  List<DreamProductCat> getCategories(){
		List<DreamProductCat> productCats = new ArrayList<DreamProductCat>();
		try {
			ApiContext apiContext = new ApiContext();
			ApiCredential apiCredential = apiContext.getApiCredential();
			apiCredential.seteBayToken(TOKEN);
			
			 apiContext.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");
			//设置token
			GetCategoriesCall call = new GetCategoriesCall();
			call.setApiContext(apiContext);
			
			//设置 详细级别 为 全部
			DetailLevelCodeType[] detailLevels = new DetailLevelCodeType[] {
		         DetailLevelCodeType.RETURN_ALL,
			};
			call.setDetailLevel(detailLevels);
			
			call.setViewAllNodes(true);
			
			CategoryType[] categories = null;
		
			categories = call.getCategories();
			
			for (CategoryType categoryType : categories) {
				DreamProductCat productCat = new DreamProductCat();
				//设置第几级目录
				productCat.setCategoryLevel(categoryType.getCategoryLevel());
				productCat.setCreated(new Date());
				productCat.setId(new Long(categoryType.getCategoryID()));
				if(categoryType.isLeafCategory() != null)
				productCat.setIsLeaf(categoryType.isLeafCategory());
				productCat.setName(categoryType.getCategoryName());
				productCat.setParentId(new Long(StringUtils.join(categoryType.getCategoryParentID())));
				productCat.setStatus(null);
				productCat.setUpdated(new Date());
				productCats.add(productCat);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//call.getAllCategories(apiContext, arg1, arg2, arg3, arg4, arg5);
			System.out.println("调用成功");
			
		return productCats;
	}
	
	public String addProduct(EbayProduct ebayProduct) throws ApiException, SdkException, Exception{
		AddItem addItem = new AddItem(TOKEN, ebayProduct);
		 String item = addItem.addItem();
		return item;
	}
	
	public EbayProduct getProduct(String productId) throws ApiException, SdkException, Exception{
	
		 
		GetItemCall getItem = new GetItemCall();
		getItem.setApiContext(apiContext);
		ItemType item = getItem.getItem(productId);

		System.out.println("获取商品信息成功");
		
		EbayProduct ebayProduct = new EbayProduct();
		ebayProduct.setCid(new Long(item.getPrimaryCategory().getCategoryID()));
		
		ebayProduct.setCountry(item.getCountry().value());
		
		ebayProduct.setCreated(new Date());
		
		ebayProduct.setCurrency(item.getCurrency().value());
		
		ebayProduct.setDispatchTimeMax(item.getDispatchTimeMax());
		
		ebayProduct.setEid(new Long(productId));
		
		ebayProduct.setId(IDUtils.genItemId());
		
		//拼接图片images
		String images = "";
		int pictureURLLength = item.getPictureDetails().getPictureURLLength();
		for(int i = 0;i<pictureURLLength;i++){
			if(i != pictureURLLength-1){ //如果不是最后一个加，分割开
				images += item.getPictureDetails().getPictureURL(i) + ",";
			}
			else {
				images += item.getPictureDetails().getPictureURL(i) ;
			}
		}
		ebayProduct.setImages(images);
		
		ebayProduct.setListingDuration(item.getListingDuration());
		
		ebayProduct.setLocation(item.getLocation());
		
		int paymentMethodsLength = item.getPaymentMethodsLength();
		String paymentMethods = "";
		for (int i = 0; i < paymentMethodsLength; i++) {
			if(i != paymentMethodsLength-1){ //如果不是最后一个加，分割开
				paymentMethods += item.getPaymentMethods(i).value() + ",";
			}
			else {
				paymentMethods += item.getPaymentMethods(i).value() ;
			}
			
		}
		ebayProduct.setPaymentMethods(paymentMethods);
		
		ebayProduct.setPaypalEmailAddress(item.getPayPalEmailAddress());
		
		ebayProduct.setPrice(item.getStartPrice().getValue());
		
		EbayProductDesc ebayProductDesc = new EbayProductDesc();
		ebayProductDesc.setProDesc(item.getDescription());
		ebayProduct.setProductDesc(ebayProductDesc);
		
		ebayProduct.setQuantity(item.getQuantity());
		
		ebayProduct.setTitle(item.getTitle());
		
		ebayProduct.setEurl(item.getListingDetails().getViewItemURL());
		
		
		return ebayProduct;
	}
	/**
	 * 图片上传至ebay图片服务器
	 * 
	 * @param images 这种格式即可  https//:xxxxx.com,https://sadad.com
	 * @throws InterruptedException 
	 */
	public void uploadImages(String images) {
		PictureService pictureService = eBayPictureServiceFactory.getPictureService(apiContext);
		
		UploadSiteHostedPicturesRequestType uploadSiteHostedPicturesRequestType = new UploadSiteHostedPicturesRequestType();
		String[] image = {"http:///www.dilianidc.com//templets//twang//images//tw_11.jpg"};
		//用链接上传
		//uploadSiteHostedPicturesRequestType.setExternalPictureURL(image);
		
		
		//本地上传
		PictureInfo  picInfo = new PictureInfo();
		//picInfo.setPictureFilePath("C:\\Users\\dan\\Pictures\\5a01503aN19b0e75d.jpg");
		boolean upLoadSiteHostedPicture = pictureService.UpLoadSiteHostedPicture(picInfo, uploadSiteHostedPicturesRequestType);
		if (upLoadSiteHostedPicture) {
			
			System.out.println(images + "图片已被上传图片服务器");
			System.out.println(picInfo.getURL());
		}else {
			System.out.println("失败");
		}
		
		
	}
	
/*
	
	public static void  PropertiesInitialize(){

		FileInputStream in = null;
		try{
			Properties properties = new Properties();
			in = new FileInputStream(PROPERTIES_NAME);
			properties.load(in);
			TOKEN = properties.getProperty("token");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in != null){
				try{
					in.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	*/

}
