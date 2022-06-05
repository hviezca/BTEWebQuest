/**
 * Service class for Amazon S3 storage.
 * Methods:
 * - uploadFile: Uploads file to S3 storage
 * - downloadFile: Downloads file from S3 storage
 * - deleteFile: Deletes file from S3 storage
 * - convertMultipartFileToFile: Converts Multipart File object to File object.
 *
 * Author - Hiram Viezca
 */

package com.btewebquest.data.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class StorageService {

    /**
     * The Amazon S3 storage bucket.
     * The value is obtained from the bucket
     * defined in application.yml
     */
    @Value("${application.bucket.name}")
    private String bucket;

    /**
     * An injection of an AmazonS3 object.
     * This object is defined in
     * main > java > com.btewebquest > config > StorageConfig
     */
    @Autowired
    private AmazonS3 s3Client;

    /**
     * Uploads file to AmazonS3 storage bucket by doing the following:
     * 1. Receives a MultipartFile object.
     * 2. Converts the MultipartFile object to a File object via
     *    custom method converMltipartFileToFile().
     * 3. Creates a String object to assign a unique name to the uploaded file
     *    by using currentTimeMillis + original file name.
     * 4. Uploads the file to S3 bucket.
     * 5. Deletes the file from application memory.
     * 6. Returns success message.
     *
     * @param - MultipartFile object: file
     * @return - String: success message
     */
    public String uploadFile(MultipartFile file){
        File convertedFile = convertMultipartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_"+ file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucket, fileName, convertedFile));
        convertedFile.delete();
        return "File uploaded: " + fileName;
    }

    /**
     * Downloads a file from the Amazon S3 storage bucket by doing the following:
     * 1. Receives the name of the desired file.
     * 2. Retrieves the file from the S3 bucket and stores it in an S3Object object.
     * 3. Extracts the contents of the S3 object into an S3ObjectInputStream object.
     * 4. Converts the S3ObjectInputStreamObject to byte array and returns.
     *
     * @param - String: fileName
     * @return - Byte Array containing the image from S3 bucket
     */
    public byte[] downloadFile(String fileName){
        S3Object object = s3Client.getObject(bucket, fileName);
        S3ObjectInputStream stream = object.getObjectContent();
        try {
            return IOUtils.toByteArray(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a file from Amazon S3 bucket.
     *
     * @param - fileName
     * @return - String: Success message
     */
    public String deleteFile(String fileName){
        s3Client.deleteObject(bucket, fileName);
        return fileName + " removed...";
    }

    /**
     * Converts a MultipartFile object to a File object.
     *
     * @param - MultipartFile object: file
     * @return - File object: convertedFile
     */
    private File convertMultipartFileToFile(MultipartFile file){

        File convertedFile  = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return convertedFile;
    }
}
