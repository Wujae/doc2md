package com.jackwang.fileutils;

import com.jackwang.exceptions.POIException;

import java.io.*;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
=======
>>>>>>> 7ef3e07f06530bc78afe25f97b14cb9aafd8188f

/**
 * Created by JackWang on 2015/5/10.
 * 文件操作器<br />
 * 功能：<br />
 * 1) 读取单个文件<br />
 * 2）读取整个文件集<br />
 * 3）回写指定文件后缀到指定路径；
 *
 * a) 单个文件的话返回一个流
 * b) 文件集的话返回List存储所有流
 *
 * 注意：读取文件前必须先设定后缀名；
 *       默认读取路径和写入路径一致；
 */
public class FileOperator {
    /**
     * 指定单个后缀，默认是docx
     */
    private static String _suffix = ".docx";
    /**
     * 指定多个后缀;
     * 格式必须是 'doc,docx' 即使用英文逗号隔开;
     */
    private static String[] suffixList = null;
    /**
     * 文件或文件集读取路径，在读取任何流前必须先指定路径
     */
    private static String _readPath = "";
    /**
     * 文件或文件集写入路径，在写入任何文件流前先指定路径
     * 默认和读取路径一致；
     */
    private static String _writePath = "";
    /**
     *  是否是单个文件，默认设置为true;
     */
    private static boolean isSingleFile = true;
    /**
     *  读取文件个数
     *  TODO:后续如果采用多线程，该处需要加锁；
     */
    private static int readFileCount = 0;

    /**
     * 设置后缀集
     * @param suffixs 后缀集，以逗号隔开；
     * @throws POIException
     */
    public static void setSuffixList(String suffixs) throws POIException {
        if("".equals(suffixs)) {
            throw new POIException("后缀集不能为空...");
        }
        suffixList = suffixs.split(",");
    }

    /**
     * 设置后缀
     * @param suffix 单个后缀
     * @throws POIException
     * 该方法可以不设置，默认为docx
     */
    public static void setSuffix(String suffix) throws POIException {
        if("".equals(suffix) || suffix == null) {
            throw new POIException("后缀不能为空...");
        }
        _suffix = suffix;
    }

    /**
     * 设置文件路径或文件集路径
     * @param path 设置文件路径,注意后缀名要与设定的一致
     * @throws POIException
     */
    public static void setPath(String path) throws POIException {
        if(path == null || "".equals(path)) {
            throw new POIException("文件或文件集路径不能为空...");
        }
        File file = new File(path);
        if(file.isDirectory()) {
            isSingleFile = false;
            _writePath = _readPath = path;
        }else if(file.isFile() && endsWith(path)) {
            isSingleFile = true;
            _readPath = path;
            _writePath = file.getParent();
        }else {
            throw new POIException("文件路径不合法或非法文件...");
        }
    }

    /**
     * 读取指定后缀的单个文件
     * 如果没有指定后缀，默认读取docx后缀
     * 注意：读取到文件流之后记得调用closeFileStream方法关闭文件流
     * @return
     * @throws POIException
     * @throws FileNotFoundException
     */
    public static FileInputStream readFile() throws POIException, FileNotFoundException {
        if(!isSingleFile) {
            throw new POIException("文件集路径必须使用readFiles方法读取...");
        }
        System.out.println("将读取 '" + _suffix + "' 文件");
        //Warning :后续版本如果采用多线程，该变量必须加锁
        readFileCount ++;
        return new FileInputStream(_readPath);
    }

    /**
     * 关闭文件流
     * @param stream
     * @return
     */
    public static boolean closeFileStream(InputStream stream) {
        if(null == stream) {
            return true;
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 读取指定后缀的文件集
     * 如果没有指定后缀，默认读取docx文件
     * @return
     * @throws POIException
     * @throws FileNotFoundException
     */
<<<<<<< HEAD
    public static HashMap<String , FileInputStream> readFiles() throws POIException,FileNotFoundException {
=======
    public static ArrayList<FileInputStream> readFiles() throws POIException,FileNotFoundException {
>>>>>>> 7ef3e07f06530bc78afe25f97b14cb9aafd8188f
        if(isSingleFile) {
            throw new POIException("单个文件路径必须使用readFile方法读取");
        }
        File file = new File(_readPath);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return endsWith(pathname.getName());
            }
        });
<<<<<<< HEAD
        HashMap<String ,FileInputStream> readedFileList = new HashMap<String, FileInputStream>();
        for(File readFile : files) {
            String fileName = readFile.getName();
            readedFileList.put(fileName.substring(0,fileName.indexOf(".")),new FileInputStream(readFile));
=======
        ArrayList<FileInputStream> readedFileList = new ArrayList<FileInputStream>();
        for(File readFile : files) {
            readedFileList.add(new FileInputStream(readFile));
>>>>>>> 7ef3e07f06530bc78afe25f97b14cb9aafd8188f
        }
        return readedFileList;
    }

    /**
     * 批量关闭文件流
     * @param streams
     * @return
     */
    public static boolean closeFilesStream(ArrayList<InputStream> streams) {
        for(InputStream stream : streams) {
            if(null == stream) {
                continue;
            }
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

<<<<<<< HEAD
    public static int getReadFileCount() {
        return readFileCount;
    }

=======
>>>>>>> 7ef3e07f06530bc78afe25f97b14cb9aafd8188f
    /**
     * 判断给定字符串是否符合后缀
     * @param name
     * @return
     */
    private static boolean endsWith(String name) {
        if(name.endsWith(_suffix)) {
            return true;
        }
        if(null != suffixList){
            for(String str:suffixList){
                if(name.endsWith(str))
                    return true;
            }
        }
        return false;
    }



    /**
     * 单元测试类，暂时没有搭建Junit框架进行单元测试，使用Main函数代替
     * @param args
     */
    public static void main(String[] args) {
        try {
            FileOperator.setPath("E:\\01_个人文档\\02_生活");
<<<<<<< HEAD
            HashMap list = FileOperator.readFiles();
=======
            ArrayList list = FileOperator.readFiles();
>>>>>>> 7ef3e07f06530bc78afe25f97b14cb9aafd8188f
            FileInputStream stream = FileOperator.readFile();
            FileOperator.closeFileStream(stream);
        } catch (POIException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
=======

>>>>>>> 7ef3e07f06530bc78afe25f97b14cb9aafd8188f
    }
}
