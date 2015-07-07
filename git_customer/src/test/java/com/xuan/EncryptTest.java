package com.xuan;

import com.xuan.sdk.api.ApiConfig;
import com.xuan.sdk.api.ApiConfigKit;
import com.xuan.sdk.encrypt.WXBizMsgCrypt;
import com.xuan.sdk.kit.MsgEncryptKit;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * 类简述
 * <p>
 * 类说明、详细描述
 * </p>
 *
 * @author pengqiang@asiainfo.com
 * @version 1.0.0
 * @Company 亚信科技
 * @Copyright 亚信科技
 * @CreateDate 2015-07-01 16:37
 */

public class EncryptTest {
//    @Test
//    public void testEncrypt() {
//        String inMsgXml = "<xml>" +
//                "<ToUserName><![CDATA[gh_85c6b169ef07]]></ToUserName>" +
//                "<Encrypt><![CDATA[dtfDG6RFUBzRCGlDvmJ2zBG8gdc3//HT5lYVpC1g45KxYXF+oH1AICIXIfI1ohoJb2BK8aaHv6XWiN1gmNXj8333UN6n/IqaVzAO2G0j3lEWCfLQqzEJ3XDy1/4VlooAne6L6XkFbQGZ6CzMoQxOtWgmvfXv+TlJcVn4bs4walnbH6WUsLtQef/ix7LfWNkQ9XOaTypxzCGzFhCRdkGvlgXTJHoTEQ0pEkeL6Wn6drtlFZKQI/7gJOynzTI1Y5JS4hVpOC+6WhjwNUegnTOmUtsMbq4P+wTELoZmO3gkSw6GwpnGugZ1b4sMhx7OxcIrFTzXyBQ3tXJ7XwbZTcyCq6clhd5h992rt+pzEJACcraAioFqeBs9btIlmuXkCDdUdKuy0UyEJnZs8EwVHCuSXo6bVBpwDtmdcumDGp0ei34=]]></Encrypt>" +
//                "</xml>";
//        String timestamp = "1435738295";
//        String nonce = "1183925564";
//        String msg_signature = "76c6de528ce7fe71f9b95d4163423b72cba24d68";
//        String decrypt = decrypt(inMsgXml, timestamp, nonce, msg_signature);
//        System.out.println(decrypt);
//    }
//
//    private static final String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
//
//    public static String decrypt(String encryptedMsg, String timestamp, String nonce, String msgSignature) {
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            StringReader sr = new StringReader(encryptedMsg);
//            InputSource is = new InputSource(sr);
//            Document document = db.parse(is);
//
//            Element root = document.getDocumentElement();
//            NodeList nodelist1 = root.getElementsByTagName("Encrypt");
//
//            String encrypt = nodelist1.item(0).getTextContent();
//
//            String fromXML = String.format(format, encrypt);
//
//            String encodingAesKey = "YkASaFfBp86wQFCej9110fHLKl7zHmj9DnOtv8Fpnpp";
//            if (encodingAesKey == null)
//                throw new IllegalStateException("encodingAesKey can not be null, config encodingAesKey first.");
//
//            WXBizMsgCrypt pc = new WXBizMsgCrypt("__xq__token__", encodingAesKey, "wxeaf5b134a3216fb2");
//            return pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);	// 此处 timestamp 如果与加密前的不同则报签名不正确的异常
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
