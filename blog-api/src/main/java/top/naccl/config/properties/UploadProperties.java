package top.naccl.config.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 静态文件上传访问路径配置
 *
 * @author: wdd
 * @date: 2022-01-23
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "upload.file")
public class UploadProperties {


	/**
	 * linux的nginx设置
	 */
	private String linuxNginx;
	/**
	 * window的nginx设置
	 */
	private String windowNginx;
	/**
	 * 头像本地地址储存
	 */
	private String path;

	/**
	 * 头像服务器地址储存
	 */
	private String linuxPath;

	/**
	 * blog本地路径
	 */
	private String blogPath;

	/**
	 * 服务器blog地址储存
	 */
	private String linuxBlogPath;

	/**
	 * 头像请求地址映射
	 */
	private String accessPath;
	/**
	 * blog请求地址映射
	 */
	private String accessBlogPath;

	/**
	 * 商品windows路径
	 */
	private String productBrandPath;

	/**
	 * 商品linux路径
	 */
	private String linuxProductBrandPath;

	/**
	 * 商品回显地址
	 */
	private String accessProductBrandPath;

	/**
	 * 商品windows路径
	 */
	private String productCategoryPath;

	/**
	 * 商品linux路径
	 */
	private String linuxProductCategoryPath;

	/**
	 * 商品回显地址
	 */
	private String accessProductCategoryPath;


	/**
	 * 商品windows路径
	 */
	private String productPath;

	/**
	 * 商品linux路径
	 */
	private String linuxProductPath;

	/**
	 * 商品回显地址
	 */
	private String accessProductPath;

	/**
	 * 得物商品windows路径
	 */
	private String sneakerGoodsPath;

	/**
	 * 得物商品linux路径
	 */
	private String linuxSneakerGoodsPath;

	/**
	 * 得物商品回显地址
	 */
	private String accessSneakerGoodsPath;

	/**
	 * 本地文件路径映射
	 */
	private String resourcesLocations;
}
