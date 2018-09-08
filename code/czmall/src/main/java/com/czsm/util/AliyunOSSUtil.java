package com.czsm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.jboss.logging.Logger;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

/**
 * OSS存储帮助类
 * 
 * @author Mac(刘平) Date 2018/9/4
 */
public class AliyunOSSUtil {

	private final static Logger log = Logger.getLogger(AliyunOSSUtil.class);
	private final static String END_POINT = "oss-cn-beijing.aliyuncs.com";
	private final static String ACCESS_KEY_ID = "LTAIBZY1LBQPjC30";
	private final static String ACCESS_KEY_SECRET = "aYS178iUsiG9R96fxBJhPGRVMrRKts";
	private final static String BUCKET_NAME = "czsm";
	// 文件访问域名
	private static String FILE_HOST = "https://czsm.oss-cn-beijing.aliyuncs.com/";

	/**
	 * 方法描述:上传文件 默认传到czsm文件夹
	 * 
	 * @param file 文件对象
	 * @return 文件访问路径
	 */
	public static String upload(File file) {
		if (file == null) {
			return null;
		}
		// 创建OSS客户端
		OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		try {
			// 判断文件容器是否存在，不存在则创建
			if (!ossClient.doesBucketExist(BUCKET_NAME)) {
				ossClient.createBucket(BUCKET_NAME);
				CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
				createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
				ossClient.createBucket(createBucketRequest);
			}
			// 创建文件路径

			String fileUrl = "czsm/";
			// 上传文件
			PutObjectResult result = ossClient
					.putObject(new PutObjectRequest(BUCKET_NAME, fileUrl + file.getName(), file));
			if (null != result) {
				return FILE_HOST + fileUrl;
			}
		} catch (OSSException oe) {
			log.error(oe.getMessage());
		} catch (ClientException ce) {
			log.error(ce.getMessage());
		} finally {
			// 关闭OSS服务，一定要关闭
			ossClient.shutdown();
		}
		return null;
	}

	/**
	 * 方法描述:上传文件
	 * 
	 * @author Mac
	 * @param inputStream 文件流
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String upload(InputStream inputStream) throws FileNotFoundException {
		if (inputStream == null) {
			return null;
		}
		// 创建OSS客户端
		OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		try {
			// 判断文件容器是否存在，不存在则创建
			if (!ossClient.doesBucketExist(BUCKET_NAME)) {
				ossClient.createBucket(BUCKET_NAME);
				CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
				createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
				ossClient.createBucket(createBucketRequest);
			}
			// 创建文件路径
			String fileUrl = "";
			// 上传文件
			PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, fileUrl, inputStream));
			if (null != result) {
				return FILE_HOST + fileUrl;
			}
		} catch (OSSException oe) {
			log.error(oe.getMessage());
		} catch (ClientException ce) {
			log.error(ce.getMessage());
		} finally {
			// 关闭OSS服务，一定要关闭
			ossClient.shutdown();
		}
		return null;

	}

	/**
	 * 方法描述:上传文件
	 * 
	 * @param inputStream 文件流
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String upload(InputStream inputStream, String path) throws FileNotFoundException {
		if (inputStream == null) {
			return null;
		}
		// 创建OSS客户端
		OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		try {
			// 判断文件容器是否存在，不存在则创建
			if (!ossClient.doesBucketExist(BUCKET_NAME)) {
				ossClient.createBucket(BUCKET_NAME);
				CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
				createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
				ossClient.createBucket(createBucketRequest);
			}
			// 创建文件路径
			String fileUrl = path + "";
			// 上传文件
			PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, fileUrl, inputStream));
			if (null != result) {
				return FILE_HOST + fileUrl;
			}
		} catch (OSSException oe) {
			log.error(oe.getMessage());
		} catch (ClientException ce) {
			log.error(ce.getMessage());
		} finally {
			// 关闭OSS服务，一定要关闭
			ossClient.shutdown();
		}
		return null;

	}

	public static void main(String[] args) {
		File file = new File("C:\\workspace\\czmall");
		System.out.println(file.getName());
		String result = AliyunOSSUtil.upload(file);
		System.out.println(result);
	}
}