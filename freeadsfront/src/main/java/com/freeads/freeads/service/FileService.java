package com.freeads.freeads.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;

@Service
public class FileService implements IFileService
{
	public void SaveFile( MultipartFile multipartFile ) throws IOException
	{
		if ( !multipartFile.isEmpty() ) 
		{
			String filePath = null;
			String orgName = null;
        	try 
			{
				String realPathtoUploads = "/home/samuil/freeads_repo/freeadsfront/src/main/resources/static/images/camera_images/";
				if( !new File( realPathtoUploads ).exists() )
				{
					new File( realPathtoUploads ).mkdir();
				}

				orgName = multipartFile.getOriginalFilename();
				filePath = realPathtoUploads + orgName;
				File dest = new File( filePath );
				if( !dest.exists() )
				{
					multipartFile.transferTo( dest );
				}
			}
			catch( IOException ioException )
			{
				throw new IOException( "Could not save image file: " + filePath, ioException );
			}
		}
	}
}
