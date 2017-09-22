package utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

public class ImageToVideoConvertor {

	public static void ConvertImagesToVideo(String inputDir, String outputFileName) throws Exception {
		final IMediaWriter writer = ToolFactory.makeWriter(outputFileName);
		Dimension screenBounds = Toolkit.getDefaultToolkit().getScreenSize();

		writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, screenBounds.width, screenBounds.height);
		List<String> ImageNames = new ArrayList<String>();
		ArrayList<BufferedImage> BIList = getImagesFromDirectory(new File(inputDir), ImageNames);

		for (int index = 0; index < BIList.size(); index++) {
			BufferedImage bgrScreen = convertToType(BIList.get(index), BufferedImage.TYPE_3BYTE_BGR);
			Graphics graphics = bgrScreen.getGraphics();
			graphics.setColor(Color.RED);
			graphics.setFont(new Font("Calibri", Font.PLAIN, 30));

			if (null != ImageNames && !ImageNames.isEmpty() && ImageNames.size() > index) {
				graphics.drawString(ImageNames.get(index), 60, (screenBounds.height - 60));
			}
			writer.encodeVideo(0, bgrScreen, 900 * index, TimeUnit.MILLISECONDS);

			// try {
			// Thread.sleep(1000/5 );
			// } catch (InterruptedException e) {
			// }
		}
		writer.close();
		System.out.println("Video Converted From images Successfully");
	}
	/*
	 * public static void main(String[] args) throws Exception {
	 * ConvertImagesToVideo("C:\\temp\\detail\\2017-04-05-16-04-54\\Screenshot",
	 * "C:\\temp\\detail\\2017-04-05-16-04-54\\Screenshot\\video.avi"); }
	 */

	public static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {
		BufferedImage image;
		// if the source image is already the target type, return the source
		// image
		if (sourceImage.getType() == targetType) {
			image = sourceImage;
		} // otherwise create a new image of the target type and draw the new
			// image
		else {
			image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), targetType);
			image.getGraphics().drawImage(sourceImage, 0, 0, null);
		}
		return image;
	}

	private static ArrayList<BufferedImage> getImagesFromDirectory(File directory, List<String> fileNames)
			throws IOException {
		final ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
		final String[] EXTENSIONS = new String[] { ".png" };
		FilenameFilter IMAGE_FILTER = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				for (String ext : EXTENSIONS) {
					if (name.endsWith(ext)) {
						return true;
					}
				}
				return false;
			}
		};
		if (directory.isDirectory()) { // make sure it's a directory
			BufferedImage img = null;
			File[] arrayOfFiles = directory.listFiles(IMAGE_FILTER);
			Arrays.sort(arrayOfFiles, lastModifiedLastComparator());
			for (int index = 0; index < arrayOfFiles.length; index++) {
				File file = arrayOfFiles[index];
				img = ImageIO.read(file);
				imageList.add(img);
				fileNames.add(getVideoFileName(file));
				//System.out.println("Files Added :" + file.getName());

			}

		}
		return imageList;
	}

	private static String getVideoFileName(File file) {
		if (file.isFile()) {
			String arr[] = file.getName().split("_");
			if (arr.length >= 2)
				return arr[0] + "_" + arr[1];
		}
		return "NA";
	}

	private static Comparator<File> lastModifiedLastComparator() {
		return new Comparator<File>() {

			public int compare(File o1, File o2) {
				return (int) (o1.lastModified() - o2.lastModified());
			}

		};
	}

}