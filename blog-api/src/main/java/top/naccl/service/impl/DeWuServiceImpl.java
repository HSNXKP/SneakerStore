package top.naccl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.naccl.config.properties.UploadProperties;
import top.naccl.entity.DeWuSneaker;
import top.naccl.mapper.DeWuSneakerMapper;
import top.naccl.model.vo.BlogInfo;
import top.naccl.model.vo.PageResult;
import top.naccl.model.vo.Result;
import top.naccl.service.DeWuSneakerService;
import top.naccl.util.upload.UploadUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author: wdd
 * @date: 2023/12/3 2:50
 */
@Service
public class DeWuServiceImpl implements DeWuSneakerService {


    @Autowired
    private UploadProperties uploadProperties;

    @Autowired
    DeWuSneakerMapper deWuSneakerMapper;
    @Override
    public Result getDeWuSneakerGoods(String code,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DeWuSneaker> deWuSneakers = deWuSneakerMapper.getDeWuSneakerGoods(code);
        PageInfo<DeWuSneaker> pageInfo = new PageInfo<>(deWuSneakers);
        return Result.ok("查询成功",pageInfo);
    }

    @Override
    public Result addDeWuSneakerGoods(DeWuSneaker deWuSneaker) {
        List<DeWuSneaker> deWuSneakerGoods = deWuSneakerMapper.getDeWuSneakerGoods(deWuSneaker.getCode());
        if (deWuSneakerGoods.size()!= 0){
            return Result.error("已存在该商品");
        }
        String backUrl = "";
        try {
            //网络图片资源的url（可以把这个放参数中动态获取）
            String picUrl = deWuSneaker.getImageUrl();
            //获取原文件名
            String fileName = picUrl.substring(picUrl.lastIndexOf("/")+1);
            //创建URL对象，参数传递一个String类型的URL解析地址
            URL url = new URL(picUrl);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            //从HTTP响应消息获取状态码
            int code = huc.getResponseCode();
            if (code == 200) {
                // 判断当前环境是linux还是window
                String filePath = "";
                String path = "";
                String osName = System.getProperties().getProperty("os.name");
                if(osName.equals("Linux")){
                    filePath = uploadProperties.getLinuxSneakerGoodsPath();
                    path = uploadProperties.getLinuxNginx();

                }else{
                    filePath = uploadProperties.getSneakerGoodsPath();
                    path = uploadProperties.getWindowNginx();
                }
                //获取输入流
                InputStream file = huc.getInputStream();
                UploadUtils.saveFile(file,fileName,filePath);
                // 设置商品的映射路径
                // 本地：http://localhost/productBrand/0639b8e1-0978-499f-aa44-beb64b9a1d61.jpg
                // 服务器：http://43.138.9.213/image/productBrand/0639b8e1-0978-499f-aa44-beb64b9a1d61.jpg
                // 示例： http://localhost + /productBrand/ + 0639b8e1-0978-499f-aa44-beb64b9a1d61.jpg
                // 回显链接
                String accessPath = uploadProperties.getAccessSneakerGoodsPath();
                backUrl = UploadUtils.backUrl(path, accessPath, fileName);
            }
            else {
                Result.error("图片状态异常，请重新上传");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Result.error("状态异常,请稍后重新上传");
        }
        deWuSneaker.setImageUrl(backUrl);
        deWuSneaker.setCreateTime(LocalDateTime.now());
        Integer res = deWuSneakerMapper.addDeWuSneakerGoods(deWuSneaker);
        if (res!=0){
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");

    }

    @Override
    public Result editDeWuSneakerGoods(DeWuSneaker deWuSneaker) {
        Integer res = deWuSneakerMapper.editDeWuSneakerGoods(deWuSneaker);
        if (res!=0){
            return Result.ok("更新成功");
        }
        return Result.error("更新失败");
    }

    @Override
    public Result deleteDeWuSneakerGoods(Long id) {
        Integer res = deWuSneakerMapper.deleteDeWuSneakerGoods(id);
        if (res!= 0){
            return Result.ok("删除成功");
        }
        return Result.error("删除失败");
    }

}
