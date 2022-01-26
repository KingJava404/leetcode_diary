package com.king.year_2022.M01;

import com.king.util.MyPrint;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: leetcode_diary
 * @description:
 * @author: King
 * @create: 2022-01-18 11:06
 */
public class Test18 {
    private Map<String, String> map = new HashMap<>();

    public Test18() {
    }

    public Test18(Map<String, String> map) {
        this.map = map;
    }

    public static void main(String[] args) {



        //��ȡָ���ļ����µ������ļ�
        String filepath = "H:\\Downloads\\XFTV16.0\\Website\\Zz_www.zw3e.com";//�������Ŀ¼�ļ���·��
        File file = new File(filepath);
        Map<String, String> map = new HashMap<>();
//        map.put("һ���ѱ����ʳ���<font color=\"red\">13,000,000</font>�ε�ֲ��֮�ң�ÿ�쳬��<font color=\"red\">200,000</font>","");
//        map.put("<p>ֲ��֮�ұ����ţ� <a href=\"javascript:;\" rel=\"nofollow\" target=\"_blank\">��ICP��09018639��-8</a>","<p>ֲ��֮�ұ����ţ� <a href=\"https://icp.chinaz.com/home/info?host=wuzhaoqi.top;\" rel=\"nofollow\" target=\"_blank\">��ICP��2020019542��</a>");
//        map.put("ֲ��֮�ұ�����","������Ϣ��������");
//        map.put("static/picture/za.jpg","static/image/headerbj.jpg");
//        map.put("��ICP��14009184��-2","��ICP��2020019542��");
        map.put("https://icp.chinaz.com/home/info?host=wuzhaoqi.top","https://beian.miit.gov.cn/");
        map.put("https://beian.miit.gov.cn/;","https://beian.miit.gov.cn/");
        new Test18(map).refreshFileList(filepath);

//        if (!file.isDirectory()) {
//            System.out.println("������һ��Ŀ¼�ļ�·��");
//        } else if (file.isDirectory()) {
//            try {
//                refreshFileList(filepath);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void refreshFileList(String filepath) {
        File files = new File(filepath);
        File[] filelist = files.listFiles();
        if (filelist == null)
            return;
        for (File file : filelist) {
            if (file.isDirectory()) {
                refreshFileList(file.getAbsolutePath());
            } else {
                String filename = files.getName();//�������ļ���
                String strFileName = file.getAbsolutePath().toLowerCase();
                String FileNamePath = strFileName.substring(6, strFileName.length());
                //��ȡ�ļ���ʽ
                String SufName = strFileName.substring(strFileName.lastIndexOf(".") + 1, strFileName.length());
                //�ų�����Ҫɨ����ļ�
//                if (SufName.equals("rar") || SufName.equals("jpg") || SufName.equals("png") || SufName.equals("jar") || SufName.equals("doc") || SufName.equals("xls") || SufName.equals("gif") || SufName.equals("wmz")) {
//                    continue;
//                }

                //����ָ��ɨ���ļ�
                if (SufName.equalsIgnoreCase("html")) {
                    changeFile(file);
                }
                // MyPrint.printObs(filelist, strFileName, filename, FileNamePath);
            }
        }
    }

    private void changeFile(File file) {
        String enCode = getFileCharsetName(file.getAbsolutePath());
        System.out.println(enCode);
        String s = null;

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             BufferedReader reader = new BufferedReader(new InputStreamReader(bis));) {

            //֮������BufferedReader,������ֱ����BufferedInputStream��ȡ,����ΪBufferedInputStream��InputStream�ļ������,
            //InputStream��read������ȡ����һ��byte,��һ������ռ����byte,���Կ��ܻ���ֶ���������ֵ����,��������.
            //BufferedReader�̳���Reader,�����read������ȡ����char,����������β�����ֶ���������ֵ�.
            StringBuilder result = new StringBuilder();
            while (reader.ready()) {
                result.append((char) reader.read());
            }
            s = result.toString();
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry<String, String> mapKey : entries) {
                if (s.contains(mapKey.getKey())) { //�жϵ�ǰ���Ƿ������Ҫ�滻�����ַ� -1��ʾ����
                    s = s.replace(mapKey.getKey(), mapKey.getValue());//�滻Ϊ�����滻������
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //   System.out.println(s);

        assert s != null;
        writerFile(s, file);

    }

    private void writerFile(String s, File file) {
        try (FileOutputStream fos = new FileOutputStream(file);
             // ����FileOutputStream����,�ļ������ڻ��Զ��½�
             BufferedOutputStream bos = new BufferedOutputStream(fos);) {
            bos.write(s.getBytes());
            System.out.println("�ļ��޸ĳɹ���");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("�ļ��޸�ʧ�ܣ�");
        }
    }


    public String getFileCharsetName(String fileName) {
        String charsetName = "GBK";//��GB2312����ANSI
        try (InputStream inputStream = new FileInputStream(fileName);) {

            byte[] head = new byte[3];
            inputStream.read(head);
            if (head[0] == -1 && head[1] == -2) //0xFFFE
                charsetName = "UTF-16";
            else if (head[0] == -2 && head[1] == -1) //0xFEFF
                charsetName = "Unicode";//�������ֱ����ʽ��UCS2-Big-Endian��UCS2-Little-Endian
            else if (head[0] == -27 && head[1] == -101 && head[2] == -98)
                charsetName = "UTF-8"; //UTF-8(����BOM)
            else if (head[0] == -17 && head[1] == -69 && head[2] == -65)
                charsetName = "UTF-8"; //UTF-8-BOM


        } catch (IOException e) {
            e.printStackTrace();
        }
        return charsetName;
    }
}


