//package com.ins.engage.ui.frescoloader;
//
//import android.net.Uri;
//import android.text.TextUtils;
//import android.view.ViewGroup;
//
//import com.facebook.common.util.UriUtil;
//import com.facebook.drawee.backends.pipeline.Fresco;
//import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
//import com.facebook.drawee.controller.ControllerListener;
//import com.facebook.drawee.interfaces.DraweeController;
//import com.facebook.drawee.view.DraweeView;
//import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
//import com.facebook.imagepipeline.common.ResizeOptions;
//import com.facebook.imagepipeline.image.ImageInfo;
//import com.facebook.imagepipeline.request.ImageRequestBuilder;
//
//public class FrescoLoader {
//
//	public static int UNDEFINED_SIZE = -1;
//	public int maxLocalImageSize = 1024;
//
//	public static Uri parseToUri(String uriOrPath) {
//		if (TextUtils.isEmpty(uriOrPath)) return null;
//		Uri uri = Uri.parse(uriOrPath);
//		if (!UriUtil.isNetworkUri(uri)) {
//			uri = Uri.parse("file:" + (uriOrPath.startsWith("/") ? uriOrPath : "/" + uriOrPath));
//		}
//		return uri;
//	}
//
//	public void loadWithParams(String pathOrUrl, DraweeView imageView, ControllerListener<ImageInfo> listener) {
//		Uri uri = FrescoLoader.parseToUri(pathOrUrl);
//		loadWithParams(pathOrUrl, uri, imageView, listener, false, UNDEFINED_SIZE, UNDEFINED_SIZE);
//	}
//
//	public void loadWithParams(String path, Uri uri, DraweeView imageView, ControllerListener<ImageInfo> listener, boolean
//			isZoomable, int customWidth, int customHeight) {
//		loadWithParams(path, uri, null, imageView, listener, isZoomable, customWidth, customHeight, false);
//	}
//
//	public void loadWithParams(String path, Uri uri, Uri lowResUri, DraweeView imageView, ControllerListener<ImageInfo> listener,
//							   boolean isZoomable, int customWidth, int customHeight, boolean retainImageOnFailure) {
//		if (uri == null) return;
//
//		ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
//		if (UriUtil.isNetworkUri(uri)) {
//			imageRequestBuilder.setProgressiveRenderingEnabled(isJpg(uri.toString()));
//		} else {
//			int width = 0, height = 0;
//			if (imageView.getLayoutParams().width <= 0) {
//				ViewGroup.LayoutParams parentLayoutParams = ((ViewGroup) imageView.getParent()).getLayoutParams();
//				if (parentLayoutParams != null) {
//					width = parentLayoutParams.width;
//					height = parentLayoutParams.height;
//				}
//			} else {
//				width = imageView.getLayoutParams().width;
//				height = imageView.getLayoutParams().height;
//			}
//			if (width <= 0 || height <= 0) {
//				width = height = maxLocalImageSize;
//			}
//
//			if (customWidth != UNDEFINED_SIZE) {
//				width = customWidth;
//				if (customHeight != UNDEFINED_SIZE) {
//					height = customHeight;
//				}
//				CustomImageDecodeOptions ops = new CustomImageDecodeOptions(new ImageDecodeOptionsBuilder());
//				ops.setCustomDecodeWidthHeight(width, height, path);
//				imageRequestBuilder.setImageDecodeOptions(ops);
//			}
//
//			imageRequestBuilder.setResizeOptions(new ResizeOptions(width, height));
//		}
//
//		PipelineDraweeControllerBuilder draweeControllerBuilder = Fresco.newDraweeControllerBuilder()
//				.setImageRequest(imageRequestBuilder.build())
//				.setOldController(imageView.getController())
//				.setRetainImageOnFailure(retainImageOnFailure)
//				.setControllerListener(listener)
//				.setAutoPlayAnimations(true);
//		if (lowResUri != null && !lowResUri.equals(uri)) {
//			draweeControllerBuilder.setLowResImageRequest(ImageRequestBuilder.newBuilderWithSource(lowResUri).build());
//		}
//
//		imageView.setController(draweeControllerBuilder.build());
//	}
//
//	public void loadWithParams(final Uri uri, DraweeView imageView, boolean disableCache) {
//		loadWithParams(uri, imageView, false, null, disableCache);
//	}
//
//	public void loadWithParams(final Uri uri, DraweeView imageView, boolean animateGifs, ControllerListener<ImageInfo> listener, boolean disableCache) {
//		if (uri == null) {
//			return;
//		}
//
//		ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
//		if (UriUtil.isNetworkUri(uri)) {
//			imageRequestBuilder.setProgressiveRenderingEnabled(isJpg(uri.toString()));
//		} else {
//			int width, height;
//			if (imageView.getLayoutParams().width <= 0) {
//				width = ((ViewGroup) imageView.getParent()).getLayoutParams().width;
//				height = ((ViewGroup) imageView.getParent()).getLayoutParams().height;
//			} else {
//				width = imageView.getLayoutParams().width;
//				height = imageView.getLayoutParams().height;
//			}
//			if (width <= 0 || height <= 0) {
//				width = height = maxLocalImageSize;
//			}
//
//			CustomImageDecodeOptions ops = new CustomImageDecodeOptions(new ImageDecodeOptionsBuilder().setForceStaticImage(true));
//			ops.setCustomDecodeWidthHeight(width, height, uri.getPath());
//			imageRequestBuilder.setImageDecodeOptions(ops);
//			imageRequestBuilder.setResizeOptions(new ResizeOptions(width, height));
//		}
//
//		DraweeController draweeController = Fresco.newDraweeControllerBuilder()
//				.setImageRequest(imageRequestBuilder.build())
//				.setAutoPlayAnimations(animateGifs)
//				.setOldController(imageView.getController())
//				.setControllerListener(listener)
//				.build();
//
//		imageView.setController(draweeController);
//	}
//
//	public String getImageExtension(String uri) {
//		String ext = uri;
//		if (ext.contains("?")) {
//			ext = ext.substring(0, ext.indexOf("?"));
//		}
//		if (ext.contains(".")) {
//			ext = ext.substring(ext.lastIndexOf("."));
//		}
//		return ext;
//	}
//
//	public boolean isJpg(String uri) {
//		String ext = getImageExtension(uri);
//		return ".jpg".equals(ext) || ".jpeg".equals(ext);
//	}
//}
