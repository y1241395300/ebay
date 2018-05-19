package cn.sdcit.ebay.service.impl;

import com.ebay.sdk.ApiAccount;
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
import com.ebay.soap.eBLBaseComponents.InventoryTrackingMethodCodeType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.NameValueListArrayType;
import com.ebay.soap.eBLBaseComponents.NameValueListType;
import com.ebay.soap.eBLBaseComponents.PictureDetailsType;
import com.ebay.soap.eBLBaseComponents.PicturesType;
import com.ebay.soap.eBLBaseComponents.ReturnPolicyType;
import com.ebay.soap.eBLBaseComponents.ShippingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceOptionsType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import com.ebay.soap.eBLBaseComponents.VariationSpecificPictureSetType;
import com.ebay.soap.eBLBaseComponents.VariationType;
import com.ebay.soap.eBLBaseComponents.VariationsType;

public class AddFixedPriceItem {

     private static String USER_TOKEN="AgAAAA**AQAAAA**aAAAAA**1u/zWg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4agDZCBpAudj6x9nY+seQ**bJMEAA**AAMAAA**BFK3zzJhqo3/4wMXqNYaKQuIBAwPLR+zWM+yibFRWFAP+28jNW+PxVDAJ0TtIdI6zbIKHSdgAMBb8LIF6reb4Qbh/m7xuDaiuWGsf8SBv/uvUKFayZYztzx0k39IeuHE3se+dgLpxEqgA6FlOWkdLGz87E7kzJSbdCSFjMO6QEnqfYK5oE1qaNTpR01Viwem63Lpuc6z2epS+O8FAwV896VHOrOCM2jdcxyx6aRPsbfjUe0UljMLr4GRl+B7rGOvcY9K97vDAn6VeOhmOyaTjvz8bqpDnYCwZt8fhEY/6GAkgYPaLdCpOBXEzZ7umhBJXq1ecSaQrSoKBRVQyo64DniHYq5JWxuFLuhbF8XWx85sVaaaiXB1vxuMBXRasQVLR/XTNRUvkc7CI7VpxYkAmFDdRZCMBRTOXeEBEEEshX6ABk/VdMjpL4qOQe0UGKb8qiF0k8k4fQ92dWUkuKXzC7EujiqAgtKxhSW/nuDF6t4LkotDZ5VawZJ63AXM6vJHQMivvcSOXZgD0bUIZeggWWnyHjRPOkUZ/vo3uROXF2O+2Yb/eS9yr7MDwHfBOUvPd3BK9EyY5H104j+nYM8nBQEH5+TisTuVqEaBPCikPyH7kTL0lzxGbB5/F2PUGz3gST8WjnPxZ5lkIWcJHfd/vo04/XBJ1a1+Pp5gDUWLVZtF0oe3YII1O7DoMPCu+O6YVlUqJCZiLev1IkK4NmPBeP1nDoFoJiaZ3ETD1CCE2T+tIMaj5JgzCw6k6B6D0ru7";
    public static ApiContext createApiContext() {
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

    public FeesType addFixedItem() throws ApiException, SdkException,
            Exception {

        FeesType fee = null;
        AddFixedPriceItemCall request = new AddFixedPriceItemCall(
                createApiContext());
        request.setSite(SiteCodeType.UK);

        // 发送UUID与请求。
        request.setAutoSetItemUUID(true);

        ItemType item = new ItemType();
        item.setConditionID(1000);
        item.setTitle("上传一个四四到底有没有成功");
        item.setDescription("InventoryTrackingMethodCodeType.ITEM_ID");
        item.setSite(SiteCodeType.UK);

        // 根据值设置项目条件。
        // GetCategoryFeatures
        item.setConditionID(1000);

        item.setPostalCode("SE6 1AL");
        item.setListingDuration("Days_7");

        // 跟踪库存的SKU
        item.setInventoryTrackingMethod(InventoryTrackingMethodCodeType.ITEM_ID);

        // 指定唯一的SKU
        item.setSKU("VARPARENT_UK");

        //将支付方式设置为PayPal。
        BuyerPaymentMethodCodeType[] arrPaymentMethods = new BuyerPaymentMethodCodeType[]{BuyerPaymentMethodCodeType.PAY_PAL        };
        item.setPayPalEmailAddress("test@pp.com");
        item.setPaymentMethods(arrPaymentMethods);

        //指定数量、起始价格和eBay类别。
        item.setCountry(CountryCodeType.GB);
        item.setCurrency(CurrencyCodeType.GBP);
        CategoryType category = new CategoryType();
        category.setCategoryID("57991");
        item.setPrimaryCategory(category);

        item.setShippingDetails(getShippingDetails());

        item.setDispatchTimeMax(2);
        item.setReturnPolicy(getReturnPolicy());

        // 添加物品细节
        NameValueListArrayType itemSpec = new NameValueListArrayType();

        NameValueListType nv1 = new NameValueListType();
        nv1.setName("Brand");
        nv1.setValue(new String[]{"Ralph Lauren"                });
        
        NameValueListType nv2 = new NameValueListType();
        nv2.setName("Collar Size");
        nv2.setValue(new String[] { "16 in." });

        itemSpec.setNameValueList(new NameValueListType[]{nv1,nv2          });

        item.setItemSpecifics(itemSpec);

        //指定变化细节设置
        VariationsType vt = new VariationsType();

        NameValueListType NVListVS1 = new NameValueListType();
        NVListVS1.setName("Size");
        String[] size = {"XS", "S", "M", "L", "XL"        };
        NVListVS1.setValue(size);

        NameValueListType NVListVS2 = new NameValueListType();
        NVListVS2.setName("Colour");
        String[] colour = {"Black", "Blue"        };
        NVListVS2.setValue(colour);

        
          
        NameValueListArrayType nvla = new NameValueListArrayType();
        nvla.setNameValueList(new NameValueListType[]{NVListVS1, NVListVS2});

        VariationType v1 = new VariationType();
        v1.setSKU("VARPARENT_UK_V1");
        v1.setQuantity(new Integer(10));
        v1.setStartPrice(getAmount(35));

        NameValueListType Var1Spec1 = new NameValueListType();
        Var1Spec1.setName("Colour");
        Var1Spec1.setValue(new String[]{"Black"                });

        NameValueListType Var1Spec2 = new NameValueListType();
        Var1Spec2.setName("Size");
        Var1Spec2.setValue(new String[]{"S"                });
        NameValueListArrayType nvla1 = new NameValueListArrayType();
        nvla1.setNameValueList(new NameValueListType[]{Var1Spec1,
                    Var1Spec2
                });
        v1.setVariationSpecifics(nvla1);

        VariationType v2 = new VariationType();
        v2.setSKU("VARPARENT_UK_V2");
        v2.setQuantity(new Integer(10));
        v2.setStartPrice(getAmount(35));

        NameValueListType Var2Spec1 = new NameValueListType();
        Var2Spec1.setName("Colour");
        Var2Spec1.setValue(new String[]{"Black"                });

        NameValueListType Var2Spec2 = new NameValueListType();
        Var2Spec2.setName("Size");
        Var2Spec2.setValue(new String[]{"L"                });

        NameValueListArrayType nvla2 = new NameValueListArrayType();
        nvla2.setNameValueList(new NameValueListType[]{Var2Spec1,
                    Var2Spec2
                });
        v2.setVariationSpecifics(nvla2);

        VariationType v3 = new VariationType();
        v3.setSKU("VARPARENT_UK_V3");
        v3.setQuantity(new Integer(40));
        v3.setStartPrice(getAmount(35));

        NameValueListType Var3Spec1 = new NameValueListType();
        Var3Spec1.setName("Colour");
        Var3Spec1.setValue(new String[]{"Blue"                });

        NameValueListType Var3Spec2 = new NameValueListType();
        Var3Spec2.setName("Size");
        Var3Spec2.setValue(new String[]{"M"                });

        NameValueListArrayType nvla3 = new NameValueListArrayType();
        nvla3.setNameValueList(new NameValueListType[]{Var3Spec1,
                    Var3Spec2
                });
        v3.setVariationSpecifics(nvla3);

        VariationType v4 = new VariationType();
        v4.setSKU("VARPARENT_UK_V4");
        v4.setQuantity(new Integer(10));
        v4.setStartPrice(getAmount(35));

        NameValueListType Var4Spec1 = new NameValueListType();
        Var4Spec1.setName("Colour");
        Var4Spec1.setValue(new String[]{"Blue"                });

        NameValueListType Var4Spec2 = new NameValueListType();
        Var4Spec2.setName("Size");
        Var4Spec2.setValue(new String[]{"L"                });

        NameValueListArrayType nvla4 = new NameValueListArrayType();
        nvla4.setNameValueList(new NameValueListType[]{Var4Spec1,
                    Var4Spec2
                });
        v4.setVariationSpecifics(nvla4);

        PicturesType pic = new PicturesType();
        pic.setVariationSpecificName("Colour");

        VariationSpecificPictureSetType vsp1 = new VariationSpecificPictureSetType();
        vsp1.setPictureURL(new String[]{"http://i12.ebayimg.com/03/i/04/8a/5f/a1_1_sbl.JPG"                });
        vsp1.setVariationSpecificValue("Black");

        VariationSpecificPictureSetType vsp2 = new VariationSpecificPictureSetType();
        vsp2.setPictureURL(new String[]{"http://i2.sandbox.ebayimg.com/03/i/00/3f/c5/92_1.JPG?set_id=8800004005"                });
        vsp2.setVariationSpecificValue("Blue");

        pic.setVariationSpecificPictureSet(new VariationSpecificPictureSetType[]{
                    vsp1, vsp2
                });

        vt.setVariationSpecificsSet(nvla);
        vt.setVariation(new VariationType[]{v1, v2, v3, v4});
        vt.setPictures(new PicturesType[]{pic});

        item.setVariations(vt);

        // 添加物品级别的图片
        PictureDetailsType pdt = new PictureDetailsType();
        pdt.setPictureURL(new String[]{"http://i2.sandbox.ebayimg.com/03/i/00/3f/c5/92_1.JPG?set_id=8800004005"                });

        request.setItem(item);
        fee = request.addFixedPriceItem();

        return fee;
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

    private static AmountType getAmount(double amount) {
        AmountType a = new AmountType();
        a.setValue(amount);
        return a;
    }
    
    /*
    public static void main(String[] args) {

        try {
            FeesType fee = addFixedItem();
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

    }*/
}