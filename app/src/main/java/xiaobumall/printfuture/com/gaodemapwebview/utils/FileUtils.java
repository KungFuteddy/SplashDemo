package xiaobumall.printfuture.com.gaodemapwebview.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * 创建日期：2018/2/11
 * <p>
 * 描述:
 * <p>
 * Created by admin.
 * <p>
 * gitHub: https://github.com/KungFuteddy
 * <p>
 * Orporate Name: Henan Huimeng future network technology Co. Ltd.
 */

public class FileUtils {

	private static final String TAG = "FileUtils";

	/**
	 * 创建图片的文件
	 *
	 * @param img_name 图片路径
	 * @param img_type 图片类型
	 * @return
	 */
	public static File createFile(String img_name, String img_type) {
		File file = null;
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			file = new File(img_name + img_type + ".jpg");
		} else {
			file = new File(img_name + img_type + ".jpg");
		}
		Log.d(TAG, "file " + file.getAbsolutePath());
		return file;
	}


	/**
	 * 判断根目录是否存在
	 *
	 * @return
	 */
	public static boolean isFileExists() {
		try {
			File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/splash");
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断图片路径是否存在
	 *
	 * @param path     文件路径
	 * @param img_name 文件名称
	 * @return
	 */
	public static boolean isExist(String path, String img_name) {
		try {
			File f = new File(path + img_name + ".jpg");
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * I/O 文件的读写操作
	 *
	 * @param response 文件的对象
	 * @param file     文件
	 */
	public static void writeFile2Disk(Response<ResponseBody> response, File file) {// HttpCallBack httpCallBack) {
		long currentLength = 0;
		OutputStream os = null;
		InputStream is = response.body().byteStream();
//		long totalLength = response.body().contentLength();
//		boolean isloading = false;
		try {
			os = new FileOutputStream(file);
			int len;
			byte[] buff = new byte[1024];
			while ((len = is.read(buff)) != -1) {
				os.write(buff, 0, len);
				currentLength += len;
				Log.d(TAG, "当前进度:" + currentLength);
//				httpCallBack.onLoading(currentLength, totalLength);
			}
			// httpCallBack.onLoading(currentLength,totalLength,true);
		} catch (FileNotFoundException e) {
			Log.i(TAG, "writeFile2Disk: " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
//					isloading = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
//		httpCallBack.isloading(isloading);
	}
}
