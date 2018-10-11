package com.qpp.utils.common;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author qipengpai
 * @Title: AddressUtil
 * @ProjectName bound
 * @Description: TODO
 * @date 14:31 2018/10/11
 */

public class AddressUtil {

    /**
     * @Author qipengpai
     * @Description //TODO 得到地址信息
     * @Date 14:53 2018/10/11
     * @Param [content, encodingString]请求的参数 格式为：name=xxx&pwd=xxx  服务器端请求编码。如GBK,UTF-8等
     * @Throws  UnsupportedEncodingException
     * @return java.lang.String
     **/
    public static String getAddresses(String content, String encodingString)
            throws UnsupportedEncodingException {
        // 这里调用淘宝API
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            // 处理返回的省市区信息
            System.out.println("(1) unicode转换成中文前的returnStr : " + returnStr);
            returnStr = decodeUnicode(returnStr);
            System.out.println("(2) unicode转换成中文后的returnStr : " + returnStr);
            String[] temp = returnStr.split(",");
            if(temp.length<3){
                return "0";//无效IP，局域网测试
            }
            return returnStr;
        }
        return null;
    }

    /**
     * @Author qipengpai
     * @Description //TODO 发送请求得到响应
     * @Date 14:55 2018/10/11
     * @Param [urlStr, content, encoding] 请求的参数 格式为：name=xxx&pwd=xxx 服务器端请求编码。如GBK,UTF-8等
     * @Throws
     * @return java.lang.String
     **/
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            // 往对端写完数据对端服务器返回数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }
    /**
     * @Author qipengpai
     * @Description //TODO unicode 转换成 中文
     * @Date 14:59 2018/10/11
     * @Param [theString]
     * @Throws
     * @return java.lang.String
     **/
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
    // 测试
    public static void main(String[] args) {
        AddressUtil addressUtils = new AddressUtil();
        // 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
        //String ip = "122.49.20.247";
        String ip = "113.232.156.10";
        String address = "";
        try {
            address = addressUtils.getAddresses("ip="+ip, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(address);
        // 输出结果为：广东省,广州市,越秀区
    }

    public void getAddressByIp() throws Exception {
        // 参数ip
        String ip = "219.136.134.157";
        // json_result用于接收返回的json数据
        String json_result = null;
        try {
            json_result = AddressUtil.getAddresses("ip=" + ip, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,Object> json = JSONObject.parseObject(json_result);
        System.out.println("json数据： " + json);
        String country =JSONObject.parseObject(json.get("data")+"").get("country").toString();
        String region =JSONObject.parseObject(json.get("data")+"").get("region").toString();
        String city = JSONObject.parseObject(json.get("data")+"").get("city").toString();
        String county = JSONObject.parseObject(json.get("data")+"").get("county").toString();
        String isp = JSONObject.parseObject(json.get("data")+"").get("isp").toString();
        String area = JSONObject.parseObject(json.get("data")+"").get("area").toString();
        System.out.println("国家： " + country);
        System.out.println("地区： " + area);
        System.out.println("省份: " + region);
        System.out.println("城市： " + city);
        System.out.println("区/县： " + county);
        System.out.println("互联网服务提供商： " + isp);

        String address = country + "/";
        address += region + "/";
        address += city + "/";
        address += county;
        System.out.println(address);
    }
}
