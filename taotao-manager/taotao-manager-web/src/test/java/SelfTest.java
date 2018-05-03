import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

public class SelfTest {

//	@Test
//	public void pagehelpTest(){
//		ApplicationContext applicationContext =
//				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
//		PageHelper.startPage(1, 10);
//		TbItemExample example = new TbItemExample();
//		List<TbItem> list = itemMapper.selectByExample(example );
//		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
//		System.out.println(pageInfo.getTotal());
//		System.out.println(list.size());
//		for(TbItem ti: list){
//			System.out.println(ti.getTitle());
//		};
//	}
//
	@Test
	public void imgUploadTest() throws SocketException, IOException {
		FTPClient fc = new FTPClient();
		fc.connect("106.12.20.37", 21);
		fc.login("ftpuser", "mm8023ak@");
		fc.setFileType(FTP.BINARY_FILE_TYPE);
		FileInputStream testFile =
				//C:\\Users\\Public\\Pictures\\Sample Pictures
				new FileInputStream(new File("D:\\Documents\\a.jpg"));
		fc.enterLocalPassiveMode();
		boolean b1 = fc.changeWorkingDirectory("imgTest");
		boolean b2 = fc.makeDirectory("imgTest");
		boolean b3 = fc.changeWorkingDirectory("/imgTest");
		boolean b = fc.storeFile("111.jpg", testFile);
		FTPFile[] files = fc.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				System.out.println(files[i].getName());
			}
		}
			System.out.print(b);
		testFile.close();
		fc.logout();
//
	}

}
