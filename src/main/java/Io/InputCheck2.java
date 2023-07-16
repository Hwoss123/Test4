package Io;

import HashMapControl.HashsetMap;
import HashMapControl.SHHashMap;
import HashMapControl.SLHashMap;
import HashMapControl.SSHashMap;
import log.AppendFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Properties;

public class InputCheck2 {
    //   �����ͬ��input�������һ�������ȥ���󶨵ģ�
    private HashMap<String, HashSet<String>> hms = new HashMap<String, HashSet<String>>();
    private HashMap<String, HashMap<String,String>> hmh = new HashMap<>();
    private HashMap<String, LinkedList<String>> hml = new HashMap<>();
    private  HashMap<String, String> hm = new HashMap<>();



    public static void input()  {
        String persistenceMethod = "dataPersistence";
//            Ĭ�ϲ������ݳ־û�
            persistenceMethod =  properties.property("persistenceMethod");
        if (persistenceMethod.equals("dataPersistence")) {
            HashMap<String, String> hm = SSHashMap.input();
            HashMap<String, LinkedList<String>> hml = SLHashMap.input();
            HashMap<String, HashMap<String, String>> hmh = SHHashMap.input();
            HashMap<String, HashSet<String>> hms = HashsetMap.input();
            SSHashMap.setHm(hm);
            SLHashMap.setHml(hml);
            SHHashMap.setHmh(hmh);
            HashsetMap.setHms(hms);
        } else if (persistenceMethod.equals("appendLogFile")) {
            AppendFile.loadCommands();
        }else if (persistenceMethod.equals("both")){
            HashMap<String, String> hm = SSHashMap.getSSHashMap();
            HashMap<String, LinkedList<String>> hml = SLHashMap.getSLHashMap();
            HashMap<String, HashMap<String, String>> hmh = SHHashMap.getSHHashMap();
            HashMap<String, HashSet<String>> hms = HashsetMap.getSetMap();
            SSHashMap.output(hm);
            SLHashMap.output(hml);
            SHHashMap.output(hmh);
            HashsetMap.output(hms);
            System.out.println("������־һ��");
        }else{
            MultiWriteHandler.setClient("�����ļ��־û���������");
        }
    }
}