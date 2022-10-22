package bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.http.Part;

public class UploadFile {
	public String upload(Part part, String realPath) throws IOException {

		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();

		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectory(Path.of(realPath));
		}
		part.write(realPath + "/" + fileName);
		return fileName;

	}
}
