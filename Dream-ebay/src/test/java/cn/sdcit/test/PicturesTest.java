package cn.sdcit.test;

import java.util.HashMap;
import java.util.Map;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.pictureservice.PictureInfo;
import com.ebay.sdk.pictureservice.PictureService;
import com.ebay.sdk.pictureservice.eps.eBayPictureServiceFactory;
import com.ebay.soap.eBLBaseComponents.UploadSiteHostedPicturesRequestType;

public class PicturesTest {
		private static ApiContext apiContext;
		
		private static Map<String,String> picMap = new HashMap<String,String>();
	
	public static ApiContext getApiContext() throws Exception {  
	    apiContext = new ApiContext();  
	    ApiAccount apiAccount = new ApiAccount();  
	    apiAccount.setApplication("-SBX-2e0394388-1805cf18");  
	    apiAccount.setCertificate("5a70ee83-18f1-457b-a94b-668dc7a7f86e");  
	    apiAccount.setDeveloper("5a70ee83-18f1-457b-a94b-668dc7a7f86e");  
	    apiContext.getApiCredential().setApiAccount(apiAccount);  
	    ApiCredential cred = apiContext.getApiCredential();  
	    cred.seteBayToken("AgAAAA**AQAAAA**aAAAAA**1u/zWg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4agDZCBpAudj6x9nY+seQ**bJMEAA**AAMAAA**BFK3zzJhqo3/4wMXqNYaKQuIBAwPLR+zWM+yibFRWFAP+28jNW+PxVDAJ0TtIdI6zbIKHSdgAMBb8LIF6reb4Qbh/m7xuDaiuWGsf8SBv/uvUKFayZYztzx0k39IeuHE3se+dgLpxEqgA6FlOWkdLGz87E7kzJSbdCSFjMO6QEnqfYK5oE1qaNTpR01Viwem63Lpuc6z2epS+O8FAwV896VHOrOCM2jdcxyx6aRPsbfjUe0UljMLr4GRl+B7rGOvcY9K97vDAn6VeOhmOyaTjvz8bqpDnYCwZt8fhEY/6GAkgYPaLdCpOBXEzZ7umhBJXq1ecSaQrSoKBRVQyo64DniHYq5JWxuFLuhbF8XWx85sVaaaiXB1vxuMBXRasQVLR/XTNRUvkc7CI7VpxYkAmFDdRZCMBRTOXeEBEEEshX6ABk/VdMjpL4qOQe0UGKb8qiF0k8k4fQ92dWUkuKXzC7EujiqAgtKxhSW/nuDF6t4LkotDZ5VawZJ63AXM6vJHQMivvcSOXZgD0bUIZeggWWnyHjRPOkUZ/vo3uROXF2O+2Yb/eS9yr7MDwHfBOUvPd3BK9EyY5H104j+nYM8nBQEH5+TisTuVqEaBPCikPyH7kTL0lzxGbB5/F2PUGz3gST8WjnPxZ5lkIWcJHfd/vo04/XBJ1a1+Pp5gDUWLVZtF0oe3YII1O7DoMPCu+O6YVlUqJCZiLev1IkK4NmPBeP1nDoFoJiaZ3ETD1CCE2T+tIMaj5JgzCw6k6B6D0ru7");  
	    apiContext.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");  
	    apiContext.setEpsServerUrl("https://api.sandbox.ebay.com/ws/api.dll");    // 这个要设置，不然会出错  
	    return apiContext;  
	}  
	public static String[] uploadPicture(String[] paths) throws Exception {  
	    ApiContext apiContext = getApiContext();  
	    PictureService picService = eBayPictureServiceFactory.getPictureService(apiContext);  
	    String[] rt = new String[paths.length];  
	    for (int i = 0; i < paths.length; ++i) {  
	        System.out.println(paths[i]);  
	        String url = picMap.get(paths[i]);  
	        if (url != null) {  
	            rt[i] = url;  
	            continue;  
	        }  
	        PictureInfo picInfo = new PictureInfo();  
	        picInfo.setPictureFilePath(paths[i]);  
	        UploadSiteHostedPicturesRequestType request = new UploadSiteHostedPicturesRequestType();  
	  
	        boolean success = picService.UpLoadSiteHostedPicture(picInfo, request);  
	        if (success) {  
	            rt[i] = new String(picInfo.getURL());  
	        } else  
	            rt[i] = new String(picInfo.getErrorType() + ":" + picInfo.getErrorMessage());  
	    }  
	    return rt;  
	}  
	public static void main(String[] args) {
		String[]  images = {"D:\\iphonetest.jpg"};
		try {
			uploadPicture(images);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
