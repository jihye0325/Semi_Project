package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) {
		String ext = "";
		String name = originFile.getName();
		
		int dot = name.lastIndexOf(".");
		if(dot != -1)
			ext = name.substring(dot);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String fileName = sdf.format(new Date()) + (int)(Math.random() * 100000) + ext;
		File newFile = new File(originFile.getParent(), fileName);
		return newFile;
	}

}
