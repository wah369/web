package com.wah.service;

import com.wah.dao.DeleteDAO;
import com.wah.dao.QueryDAO;
import com.wah.dao.SaveDAO;
import com.wah.model.FileMeta;
import com.wah.util.ListUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-25
 * Time: 22:44
 */
//主要提供给文件扫描时，处理扫描结果和DB中结果差集时使用
public class FileService {
    private final SaveDAO saveDAO = new SaveDAO();
    private final DeleteDAO deleteDAO = new DeleteDAO();
    private final QueryDAO queryDAO = new QueryDAO();
    //保存文件
    public void saveFileList(List<FileMeta> fileList) {
        saveDAO.save(fileList);
    }
    //删除文件
    public void deleteFileList(List<FileMeta> fileList) {
        List<Integer> idList = fileList.stream().map(FileMeta::getId).collect(Collectors.toList());
        deleteDAO.delete(idList);
    }

    public void service(String path, List<FileMeta> scanResultList) {
        List<FileMeta> queryResultList = queryDAO.queryByPath(path);

        //1.queryResultList - scanResultList
        List<FileMeta> ds1 = ListUtil.differenceSet(queryResultList,scanResultList);
        deleteFileList(ds1);

        //2.scanResultList - queryResultList
        List<FileMeta> ds2 = ListUtil.differenceSet(scanResultList,queryResultList);
        saveFileList(ds2);
    }

    //查询
    public List<FileMeta> query(String keyword) {
        return queryDAO.query(keyword);
    }
}
