package taotaoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {

	/** 
	 * Description: 向FTP服务器上传文件 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param basePath FTP服务器基础目录
	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */  
	public static boolean uploadFile(String fTP_SERVER_IP, Integer fTP_SERVER_PORT, String fTP_SERVER_USERNAME,
			String fTP_SERVER_PASSWORD, String fILI_UPLOAD_PATH, String filePath, String newFileName,
			InputStream inputStream) throws SocketException, IOException {
		boolean result = false;
		FTPClient ftpClient = new FTPClient();
		int reply;
		ftpClient.connect(fTP_SERVER_IP, fTP_SERVER_PORT);
		ftpClient.login(fTP_SERVER_USERNAME, fTP_SERVER_PASSWORD);
		ftpClient.enterLocalPassiveMode();
		reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
			return result;
		}
		//切换到上传目录
		if (!ftpClient.changeWorkingDirectory(fILI_UPLOAD_PATH+filePath)) {
			//如果目录不存在创建目录
			String[] dirs = filePath.split("/");
			String tempPath = fILI_UPLOAD_PATH;
			for (String dir : dirs) {
				if (null == dir || "".equals(dir)) continue;
				tempPath += "/" + dir;
				if (!ftpClient.changeWorkingDirectory(tempPath)) {
					if (!ftpClient.makeDirectory(tempPath)) {
						return result;
					} else {
						ftpClient.changeWorkingDirectory(tempPath);
					}
				}
			}
		}
		//设置上传文件的类型为二进制类型
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//上传文件
		if (!ftpClient.storeFile(newFileName, inputStream)) {
			return result;
		}
		inputStream.close();
		ftpClient.logout();
		result = true;
		return result;
	}

}
