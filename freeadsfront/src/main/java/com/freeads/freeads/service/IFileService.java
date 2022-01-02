package com.freeads.freeads.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface IFileService
{
	void SaveFile( MultipartFile multipartFile ) throws IOException;
}
