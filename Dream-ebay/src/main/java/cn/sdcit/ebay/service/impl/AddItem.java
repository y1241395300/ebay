package cn.sdcit.ebay.service.impl;

import java.io.IOException;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.AddItemCall;
import com.ebay.sdk.helper.ConsoleUtil;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.BuyerPaymentMethodCodeType;
import com.ebay.soap.eBLBaseComponents.CategoryType;
import com.ebay.soap.eBLBaseComponents.CountryCodeType;
import com.ebay.soap.eBLBaseComponents.CurrencyCodeType;
import com.ebay.soap.eBLBaseComponents.FeesType;
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

import cn.sdcit.entity.EbayProduct;




public class AddItem {
	private String TOKEN ;
	private EbayProduct ebayProduct;
	public AddItem(String TOKEN,EbayProduct ebayProduct) {
		this.ebayProduct = ebayProduct;
		this.TOKEN = TOKEN;
	}
    private  ApiContext getApiContext() throws IOException {

        ApiContext apiContext = new ApiContext();

        // 设置API Token 访问 eBay API服务器
        ApiCredential cred = apiContext.getApiCredential();
        //input = ConsoleUtil.readString("AgAAAA**AQAAAA**aAAAAA**vA3fWQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GnCZmFoQSdj6x9nY+seQ**jO0DAA**AAMAAA**KCQVoErPFnQv9uSlyK4yL6uUz4x5f5722s9/mECja2AxI9J6Y1xDLsMRARiymw2sNmdzyQq47CPezR62J9L7Wwi8kMFtcLQwPNZX9Pvrad90T2EjsXBPVX6ilezVfq6k2YCHxakPMRCN7UdvqV6IHxASqJx8nb6SdRAKly812cXvSM7K4UD0E5nQHaE47a00msZZRt5Y4xxRia+V+IsxRPugsbXI6e3U9qPrqYjYdVGtQf2NKfayaG3ntxoi7wb7MVExj4N4XND94/KXXfJXX942OVjb6d3hPh5g4QamMoYjm3Nuglj7AvsOn5Zswb0k2ByjkuyLpje1pMqvbEkQrbyxtPzyNIrv6Nrfjnkzr9BKr8TNVPW1F6xRXxdsVOFsTwqJrkkgkNiyEFo161mwyMqQ+nM8v15AdE17zvpzZFIqFvIzleVFSD1NVqeAxPf5WQ+daFradBci3XS0eoV60XYFKVIHv2RfncGDEMotz5j30vrvorG34iAKnoffZedgTjpaYmYttCf]v+Q4/sDorwfZ2Ec6Ddx33ywps3C5A3dRx9QIxIJqKRAdnF1m19ouFJ");
        //input = ConsoleUtil.readString("AgAAAA**AQAAAA**aAAAAA**W7XvWg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4agDZCBpAudj6x9nY+seQ**bJMEAA**AAMAAA**BFK3zzJhqo3/4wMXqNYaKQuIBAwPLR+zWM+yibFRWFAP+28jNW+PxVDAJ0TtIdI6zbIKHSdgAMBb8LIF6reb4Qbh/m7xuDaiuWGsf8SBv/uvUKFayZYztzx0k39IeuHE3se+dgLpxEqgA6FlOWkdLGz87E7kzJSbdCSFjMO6QEnqfYK5oE1qaNTpR01Viwem63Lpuc6z2epS+O8FAwV896VHOrOCM2jdcxyx6aRPsbfjUe0UljMLr4GRl+B7rGOvcY9K97vDAn6VeOhmOyaTjvz8bqpDnYCwZt8fhEY/6GAkgYPaLdCpOBXEzZ7umhBJXq1ecSaQrSoKBRVQyo64DniHYq5JWxuFLuhbF8XWx85sVaaaiXB1vxuMBXRasQVLR/XTNRUvkc7CI7VpxYkAmFDdRZCMBRTOXeEBEEEshX6ABk/VdMjpL4qOQe0UGKb8qiF0k8k4fQ92dWUkuKXzC7EujiqAgtKxhSW/nuDF6t4LkotDZ5VawZJ63AXM6vJHQMivvcSOXZgD0bUIZeggWWnyHjRPOkUZ/vo3uROXF2O+2Yb/eS9yr7MDwHfBOUvPd3BK9EyY5H104j+nYM8nBQEH5+TisTuVqEaBPCikPyH7kTL0lzxGbB5/F2PUGz3gST8WjnPxZ5lkIWcJHfd/vo04/XBJ1a1+Pp5gDUWLVZtF0oe3YII1O7DoMPCu+O6YVlUqJCZiLev1IkK4NmPBeP1nDoFoJiaZ3ETD1CCE2T+tIMaj5JgzCw6k6B6D0ru7");
        cred.seteBayToken(TOKEN);
        // 设置API服务器URL
        //input = ConsoleUtil.readString("输入 eBay SOAP 服务器URL (e.g., https://api.ebay.com/wsapi): ");
        apiContext.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");

       // 设置EPS图片服务器URL
        apiContext.setEpsServerUrl("https://api.sandbox.ebay.com/ws/api.dll");

        return apiContext;
    }
    /**
     *  构建ItemType类
     * @return ItemType  对象
     * @throws IOException
     */
    private   ItemType builItem() throws IOException {
        ItemType item = new ItemType();
        
        // 项目标题
        item.setTitle(ebayProduct.getTitle());

      
        
       // 画廊图片
        String images = ebayProduct.getImages();
        String[] split = images.split(",");
        PictureDetailsType pic = new PictureDetailsType(); 
        pic.setPictureURL(split); 
        //指定画廊图片 
        pic.setGalleryType(GalleryTypeCodeType.GALLERY); 
        item.setPictureDetails(pic); 
    
        // 项目SKU
        //item.setSKU(ConsoleUtil.readString("SKU: "));
        
        // 项目描述
        item.setDescription(ebayProduct.getProductDesc().getProDesc());
        
        // 类型	eBay上市的销售格式，如拍卖（以Chinese价值表示）
        //item.setListingType(ListingTypeCodeType.CHINESE);
        
        // 货币
        item.setCurrency(CurrencyCodeType.fromValue(ebayProduct.getCurrency())); // 必须 
        
        //商品价格
        AmountType amount = new AmountType();
        amount.setValue(Double.valueOf(ebayProduct.getPrice()));
        item.setStartPrice(amount);
        
        //上市时间
        //家希望列表活跃的天数（可用于出价/购买）。持续时间指定了卖方在上市时的初始意图。
        item.setListingDuration(String.valueOf(ebayProduct.getListingDuration()));
        
        //  项目地点和国家 必须
        item.setLocation(ebayProduct.getLocation());
        item.setCountry(CountryCodeType.valueOf(ebayProduct.getCountry()));
        
        //  分类
        CategoryType cat = new CategoryType();
        //cat.setCategoryID(ConsoleUtil.readString("eBay类别 (e.g., 30022): "));
        cat.setCategoryID(String.valueOf(ebayProduct.getCid()));
        item.setPrimaryCategory(cat);
        
        // 库存数量
        item.setQuantity(ebayProduct.getQuantity());
        
        // 付款方式 必须
        /**
         * 暂时只支持paypal
         */
        item.setPaymentMethods(new BuyerPaymentMethodCodeType[]{BuyerPaymentMethodCodeType.PAY_PAL});
        //设置 setPayPal 付款方式
        //item.setPayPalEmailAddress("magicalbookseller@yahoo.com");
        item.setPayPalEmailAddress(ebayProduct.getPaypalEmailAddress());
        
        //  item 状态, 全新(1000)
        //item.setConditionID(1000);
        
        // 必须 需要处理时间	指定卖方在收到清算的付款后承诺准备要发货的最大工作日数。这次不包括运输时间（运输公司的运输时间）。
        item.  setDispatchTimeMax(ebayProduct.getDispatchTimeMax());
        
        // 运输细节
        //必须
        /**
         * 暂时不提供运输细节服务
         */
        item.setShippingDetails(buildShippingDetails());
        
        // 退货政策
        /**
         * 暂时默认此政策
         */
        ReturnPolicyType returnPolicy = new ReturnPolicyType();
        returnPolicy.setReturnsAcceptedOption("ReturnsAccepted");
        item.setReturnPolicy(returnPolicy);
        
        return item;        
    }
    
    /**
     * 构建产品运输细节
     * @return ShippingDetailsType 对象
     */
    private  ShippingDetailsType buildShippingDetails() {
        
        // 运输细节
        ShippingDetailsType sd =new ShippingDetailsType();
        
        //sd.setApplyShippingDiscount(new Boolean(true));  已废弃
        AmountType amount =new AmountType();
        //amount.setValue(2.8);
        
        //sd.setPaymentInstructions("eBay Java SDK 测试指令."); //可选
        
        // 选择航运类型和航运服务
        //sd.setShippingType(ShippingTypeCodeType.FLAT); //可选
        ShippingServiceOptionsType shippingOptions  = new ShippingServiceOptionsType();
        
        shippingOptions.setShippingService(ShippingServiceCodeType.SHIPPING_METHOD_STANDARD.value());
        
        
        amount.setValue(10);
        shippingOptions.setShippingServiceCost(amount);
        
        //amount = new AmountType();
        //amount.setValue(2.0);
       // shippingOptions.setShippingServiceAdditionalCost(amount);
        
        //amount  = new AmountType();
        
        //shippingOptions.setShippingServicePriority(new Integer(1));
        
        //amount  = new AmountType();
        //amount.setValue(1);
       // shippingOptions.setShippingInsuranceCost(amount);
        
        sd.setShippingServiceOptions(new ShippingServiceOptionsType[]{shippingOptions});
                
        return sd;
    }
    
    public String addItem() throws ApiException, SdkException, Exception{


            // [Step 1] eBay ApiContext 对象初始化
            System.out.println("==== [1] 帐户信息  ====");
            ApiContext apiContext = getApiContext();

            // [Step 2] 创建新的 item 对象
            System.out.println("==== [2] Item信息  ====");
            ItemType item = builItem();

            // [Step 3] 创建调用对象并执行调用
            System.out.println("==== [3] 执行API调用  ====");
            System.out.println("开始调用eBay API，请稍候…  ");
            AddItemCall api = new AddItemCall(apiContext);
            api.setItem(item);
            FeesType fees = api.addItem();
            System.out.println("调用eBay API结束，显示调用结果…");
            // [Step 4] 显示结果
            System.out.println("Listing已成功发布！");
            double listingFee = eBayUtil.findFeeByName(fees.getFee(), "ListingFee").getFee().getValue();
            System.out.println("Listing 费用是:" + new Double(listingFee).toString());
            String itemID = item.getItemID();
            System.out.println("Listed Item ID:" + itemID);
            
            //调用getItem
            
		return itemID;
    }
}
