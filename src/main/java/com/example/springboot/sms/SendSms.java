package com.example.springboot.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: springboot
 * @description: 阿里短信服务类
 * @author: Haisheng
 * @create: 2019-05-08 09:55
 **/
@Service
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendSms {

    private static final Logger logger = LoggerFactory.getLogger(SendSms.class);
    /**
     * accessKeyId和accessSecret是阿里云生成的
     */
    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessSecret}")
    private String accessSecret;

    /**
     * 自测使用的方法
     */
    @Test
    public void sendSMS() {
        DefaultProfile profile = DefaultProfile.getProfile("default",accessKeyId,accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId","cn-hangzhou");
        request.putQueryParameter("PhoneNumbers","17621692794");
        request.putQueryParameter("SignName","Weather");
        request.putQueryParameter("TemplateCode","SMS_164665704");
        request.putQueryParameter("TemplateParam","{\"code\":\"123\"}");
        try{
            CommonResponse response = client.getCommonResponse(request);
            logger.info(response.getData());
        }catch (ServerException e){
            e.printStackTrace();
        }catch (ClientException e){
            e.printStackTrace();
        }
    }

    /**
     * 外部调用的方法
     * @param phoneNumber 手机号
     * @param accessKeyId accessKeyId
     * @param accessSecret accessSecret
     * @param signName     签名
     * @param templateCode 短信模板
     * @param josnObject   短信内容
     */
    public static void sendSMS(String phoneNumber,String accessKeyId,String accessSecret,String signName,String templateCode,
                        String josnObject) {
        DefaultProfile profile = DefaultProfile.getProfile("default",accessKeyId,accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers",phoneNumber);
        request.putQueryParameter("SignName",signName);
        request.putQueryParameter("TemplateCode",templateCode);
        request.putQueryParameter("TemplateParam",josnObject);
        try{
            CommonResponse response = client.getCommonResponse(request);
            logger.info("Accept response : "+response.getData());
        }catch (ServerException e){
            e.printStackTrace();
        }catch (ClientException e){
            e.printStackTrace();
        }
    }
}
