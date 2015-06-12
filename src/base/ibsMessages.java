package base;

import java.util.ResourceBundle;

/**
 * 获取资源文件已提供界面错误提示信息
 * @author Robin
 *
 */
public class ibsMessages {
	private static final String BUNDLE_NAME = "base.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private ibsMessages() {
	}

	public static String getString(String key){
		try {
			String rs = RESOURCE_BUNDLE.getString(key);
			byte[] b = rs.getBytes("ISO-8859-1");
			String Message = new String(b,"UTF-8");
			return Message;
		} catch (Exception e) {
			return '!' + key + '!';
		}
	}
}
