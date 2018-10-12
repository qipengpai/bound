package com.qpp.service.message.imp;

import cn.emay.sdk.client.SmsSDKClient;
import cn.emay.sdk.core.dto.sms.common.ResultModel;
import cn.emay.sdk.core.dto.sms.request.SmsSingleRequest;
import cn.emay.sdk.core.dto.sms.response.SmsResponse;
import cn.emay.sdk.util.exception.SDKParamsException;
import cn.emay.sdk.util.http.common.EmayHttpResultCode;
import com.qpp.service.message.ISender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
/**
 * @author qipengpai
 * @Title: EucpSMSSender
 * @ProjectName bound
 * @Description: TODO 亿美短信
 * @date 9:34 2018/10/12
 */
@Slf4j
@Service
public class EucpSMSSender implements ISender {

   
    /**
     * @Fields url : TODO(亿美短信服务地址)
     */
    private String host = "http://bjmtn.b2m.cn";

    /**
     * @Fields uid : TODO(亿美账号接口UID)
     */
    private String appid = "EUCP-EMY-SMS1-5BGQP";

    /**
     * @Fields pas : TODO(亿美账号接口密码)
     */
    private String secret_key = "78C801DBA6AE5B5F";

    /**
     * @Author qipengpai
     * @Description //TODO 发送验证码
     * @Date 16:40 2018/7/24
     * @Param [para]
     * @return void
     **/
    @Override
    @Async
    public void send(Map<String, Object> para)  {
        //手机号码
        String phone = para.get("mob").toString();
        //发送信息
        String msg = para.get("msg").toString();
        SmsSDKClient client = null;
        try {
            client = new SmsSDKClient(host, 80, appid, secret_key);
        } catch (SDKParamsException e) {
            e.printStackTrace();
            log.info("亿美软通 创建失败");
        }
        SmsSingleRequest request = new SmsSingleRequest(phone, msg, "", "", "");
        ResultModel<SmsResponse> result = client.sendSingleSms(request);
        log.info(result.getCode());
        if (result.getCode().equals(EmayHttpResultCode.SUCCESS.getCode())) {
            log.info("亿美软通单条短信服务请求成功");
            SmsResponse response = result.getResult();
            log.info("sendSingleSms:" + response.toString());
        } else {
            log.info("亿美软通单条短信服务请求失败");
        }
    }

    @Override
    public void sendAll(Map<String, Object> para) {

    }

//    public static void main(String[] args) {
//        EucpSMSSender eucpSMSSender=new EucpSMSSender();
//        Map<String, Object> para =new HashMap<>();
//        para.put("mob","17612471398");
//        String msg = "【中科博润】尊敬的用户，您的手机验证码是：" + 666666 + ",2分钟内有效。（谨防受骗，请不要把此验证码泄露给任何人，非本人操作请忽略）";
//        para.put("msg",msg);
//        eucpSMSSender.send(para);
//    }
}
